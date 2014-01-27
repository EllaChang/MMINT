/**
 * Copyright (c) 2012-2014 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 */
package edu.toronto.cs.se.modelepedia.operator.patch;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import edu.toronto.cs.se.mmtf.MMTFException;
import edu.toronto.cs.se.mmtf.MultiModelTypeHierarchy;
import edu.toronto.cs.se.mmtf.mid.Model;
import edu.toronto.cs.se.mmtf.mid.ModelElement;
import edu.toronto.cs.se.mmtf.mid.ModelOrigin;
import edu.toronto.cs.se.mmtf.mid.MultiModel;
import edu.toronto.cs.se.mmtf.mid.constraint.MultiModelConstraintChecker;
import edu.toronto.cs.se.mmtf.mid.impl.ModelElementImpl;
import edu.toronto.cs.se.mmtf.mid.library.MultiModelRegistry;
import edu.toronto.cs.se.mmtf.mid.library.MultiModelUtils;
import edu.toronto.cs.se.mmtf.mid.operator.impl.OperatorExecutableImpl;
import edu.toronto.cs.se.mmtf.mid.relationship.BinaryModelRel;
import edu.toronto.cs.se.mmtf.mid.relationship.LinkReference;
import edu.toronto.cs.se.mmtf.mid.relationship.ModelElementReference;
import edu.toronto.cs.se.mmtf.mid.relationship.ModelEndpointReference;
import edu.toronto.cs.se.mmtf.mid.relationship.ModelRel;
import edu.toronto.cs.se.mmtf.mid.relationship.diagram.library.RelationshipDiagramUtils;

public class ModelRelTypeTransformation extends OperatorExecutableImpl {

	private static final String TRANSFORMATION_SUFFIX = "_transformed";

	private EObject tgtRootModelObj;
	private String tgtModelUri;

	protected void init() {

		tgtRootModelObj = null;
	}

	@SuppressWarnings("unchecked")
	private EObject transform(EObject srcModelObj, Map<EObject, ModelElementReference> srcModelObjs, Map<EObject, EObject> tgtModelObjs, ModelRel traceModelRel) throws MMTFException {

		ModelElementReference tgtModelElemTypeRef = srcModelObjs.get(srcModelObj);
		EClass tgtModelTypeObj = (EClass) tgtModelElemTypeRef.getObject().getEMFTypeObject();
		EObject tgtModelObj = tgtModelTypeObj.getEPackage().getEFactoryInstance().create(tgtModelTypeObj);
		EObject srcContainerModelObj = srcModelObj.eContainer();
		if (srcContainerModelObj == null) { // root found
			tgtRootModelObj = tgtModelObj;
		}
		else {
			/*TODO MMTF[TRANSFORMATION] this assumes that the model rel type is well defined with respect to the containment hierarchy:
			 * symmetric containment -> EReference model elements
			 * just one containing EStructuralFeature of the right type -> EReference model elements
			 */
			EObject tgtContainerModelObj = tgtModelObjs.get(srcContainerModelObj);
			if (tgtContainerModelObj == null) {
				// recursion
				tgtContainerModelObj = transform(srcContainerModelObj, srcModelObjs, tgtModelObjs, traceModelRel);
			}
			EReference containmentReference = null;
			for (EReference reference : tgtContainerModelObj.eClass().getEAllContainments()) {
				if (reference.getEType().getName().equals(tgtModelObj.eClass().getName())) {
					containmentReference = reference;
					break;
				}
			}
			Object containment = tgtContainerModelObj.eGet(containmentReference);
			if (containment instanceof EList) {
				((EList<EObject>) containment).add(tgtModelObj);
				tgtContainerModelObj.eSet(containmentReference, containment);
			}
			else {
				tgtContainerModelObj.eSet(containmentReference, tgtModelObj);
			}
		}
		tgtModelObjs.put(srcModelObj, tgtModelObj);

		return tgtModelObj;
	}

	private void transform(ModelRel traceModelRelType, ModelRel traceModelRel, Model srcModel, int srcIndex, int tgtIndex) throws Exception {

		ModelEndpointReference srcModelTypeEndpointRef = traceModelRelType.getModelEndpointRefs().get(srcIndex);
		Map<EObject, ModelElementReference> srcModelObjs = new HashMap<EObject, ModelElementReference>();
		TreeIterator<EObject> srcModelObjsIter = EcoreUtil.getAllContents(srcModel.getEMFRoot().eResource(), true);
		// first pass: get model objects to be transformed
		while (srcModelObjsIter.hasNext()) {
			EObject srcModelObj = srcModelObjsIter.next();
			ModelElement srcModelElemType = MultiModelConstraintChecker.getAllowedModelElementType(traceModelRel.getModelEndpointRefs().get(0), srcModelObj);
			if (srcModelElemType == null) {
				continue;
			}
			ModelElementReference srcModelElemTypeRef = MultiModelTypeHierarchy.getReference(srcModelElemType.getUri(), srcModelTypeEndpointRef.getModelElemRefs());
			ModelElementReference tgtModelElemTypeRef = ((LinkReference) srcModelElemTypeRef.getModelElemEndpointRefs().get(0).eContainer()).getModelElemEndpointRefs().get(tgtIndex).getModelElemRef();
			srcModelObjs.put(srcModelObj, tgtModelElemTypeRef);
		}
		// second pass: transform
		Map<EObject, EObject> tgtModelObjs = new HashMap<EObject, EObject>();
		for (EObject srcModelObj : srcModelObjs.keySet()) {
			if (tgtModelObjs.get(srcModelObj) != null) { // already transformed
				continue;
			}
			transform(srcModelObj, srcModelObjs, tgtModelObjs, traceModelRel);
		}
		//TODO[TRANSFORMATION] review from here
		MultiModelUtils.createModelFile(tgtRootModelObj, tgtModelUri, true);
		// third pass: create model elements and links
		for (Map.Entry<EObject, EObject> x : tgtModelObjs.entrySet()) {
			ModelElementReference srcModelElemRef = ModelElementImpl.createInstanceAndReference(x.getKey(), null, traceModelRel.getModelEndpointRefs().get(0));
			ModelElementReference tgtModelElemRef = ModelElementImpl.createInstanceAndReference(x.getValue(), null, traceModelRel.getModelEndpointRefs().get(1));
			//TODO MMTF[INTROSPECTION] Isn't this worth isolating in a getAllowedLinkTypeReference()?
			LinkReference linkTypeRef = RelationshipDiagramUtils.selectLinkTypeReferenceToCreate(traceModelRel, srcModelElemRef, tgtModelElemRef);
			linkTypeRef.getObject().createInstanceAndReference(true, traceModelRel);
		}
		//TODO MMTF[TRANSFORMATION] fourth pass: non-containment references and attributes
	}

	@Override
	public EList<Model> execute(EList<Model> actualParameters) throws Exception {

		//TODO MMTF[TRANSFORMATION] a simple operator is good for initial testing, later it has to be more integrated and constrained (and a conversion operator)
		Model srcModel = actualParameters.get(0);
		ModelRel traceModelRelType = (ModelRel) actualParameters.get(1).getMetatype();
		MultiModel multiModel = MultiModelRegistry.getMultiModel(srcModel);
		//TODO MMTF[TRANSFORMATION] move this check out of the operator
		if (traceModelRelType.getModelEndpointRefs().size() != 2) {
			throw new MMTFException("The model relationship type must have 2 model endpoints");
		}
		init();

		int srcIndex = (
			traceModelRelType instanceof BinaryModelRel ||
			srcModel.getMetatypeUri().equals(traceModelRelType.getModelEndpointRefs().get(0).getTargetUri())
		) ?
			0 : 1;
		int tgtIndex = 1 - srcIndex;
		Model tgtModelType = traceModelRelType.getModelEndpointRefs().get(tgtIndex).getObject().getTarget();
		tgtModelUri = MultiModelUtils.replaceFileExtensionInUri(
			MultiModelUtils.addFileNameSuffixInUri(srcModel.getUri(), TRANSFORMATION_SUFFIX),
			tgtModelType.getFileExtension()
		);
		Model tgtModel = tgtModelType.createInstanceAndEditor(tgtModelUri, ModelOrigin.CREATED, multiModel);
		ModelRel traceModelRel = (ModelRel) actualParameters.get(1);
		//TODO MMTF[TRANSFORMATION] modify this as part of the integration work?
		//ModelRel traceModelRel = traceModelRelType.createInstance(null, true, ModelOrigin.CREATED, multiModel);
		//traceModelRelType.getModelEndpointRefs().get(srcIndex).getObject().createInstanceAndReference(srcModel, false, traceModelRel);
		traceModelRelType.getModelEndpointRefs().get(tgtIndex).getObject().createInstanceAndReference(tgtModel, false, traceModelRel);
		//TODO MMTF[KLEISLI] make it work, i.e. make all operations invoked here transparent to the user
		transform(traceModelRelType, traceModelRel, srcModel, srcIndex, tgtIndex);

		EList<Model> result = new BasicEList<Model>();
		result.add(tgtModel);
		result.add(traceModelRel);
		return result;
	}

}