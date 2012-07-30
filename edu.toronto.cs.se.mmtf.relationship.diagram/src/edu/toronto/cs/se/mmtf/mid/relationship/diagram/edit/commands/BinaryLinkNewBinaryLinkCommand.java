/*
 * Copyright (c) 2012 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay, Vivien Suen.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 */
package edu.toronto.cs.se.mmtf.mid.relationship.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;

import edu.toronto.cs.se.mmtf.MMTF;
import edu.toronto.cs.se.mmtf.MMTFException;
import edu.toronto.cs.se.mmtf.mid.MultiModel;
import edu.toronto.cs.se.mmtf.mid.diagram.trait.MidDiagramTrait;
import edu.toronto.cs.se.mmtf.mid.relationship.BinaryLink;
import edu.toronto.cs.se.mmtf.mid.relationship.Link;
import edu.toronto.cs.se.mmtf.mid.relationship.ModelRel;
import edu.toronto.cs.se.mmtf.mid.relationship.RelationshipPackage;
import edu.toronto.cs.se.mmtf.mid.relationship.diagram.trait.RelationshipDiagramTrait;
import edu.toronto.cs.se.mmtf.mid.trait.MultiModelConstraintChecker;
import edu.toronto.cs.se.mmtf.mid.trait.MultiModelInstanceFactory;
import edu.toronto.cs.se.mmtf.mid.trait.MultiModelTypeFactory;

/**
 * The command to create a binary link.
 * 
 * @author Alessio Di Sandro
 * 
 */
public class BinaryLinkNewBinaryLinkCommand extends BinaryLinkCreateCommand {

	/**
	 * Constructor: initialises the superclass.
	 * 
	 * @param req
	 *            The request.
	 * @param source
	 *            The source model element reference.
	 * @param target
	 *            The target model element reference.
	 */
	public BinaryLinkNewBinaryLinkCommand(CreateRelationshipRequest request,
			EObject source, EObject target) {

		super(request, source, target);
	}

	/**
	 * Checks if a binary link can be created.
	 * 
	 * @return True if a binary link can be created, false otherwise.
	 */
	@Override
	public boolean canExecute() {

		return super.canExecute();
	}

	protected BinaryLink doExecuteInstancesLevel() throws Exception {

		ModelRel modelRel = getContainer();
		Link linkType = RelationshipDiagramTrait.selectLinkTypeToCreate(modelRel, getSource(), getTarget());
		BinaryLink newLink = (BinaryLink) MultiModelInstanceFactory.createLink(linkType, modelRel, RelationshipPackage.eINSTANCE.getBinaryLink());
		newLink.getElementRefs().add(getSource());
		newLink.getElementRefs().add(getTarget());

		return newLink;
	}

	protected BinaryLink doExecuteTypesLevel() throws MMTFException {

		ModelRel modelRelType = getContainer();
		String newLinkTypeName = MidDiagramTrait.getStringInput("Create new light link type", "Insert new link type name");
		BinaryLink newLinkType = (BinaryLink) MultiModelTypeFactory.createLightLinkType(
			modelRelType,
			getSource(),
			getTarget(),
			newLinkTypeName,
			RelationshipPackage.eINSTANCE.getBinaryLink()
		);
		MMTF.syncRepository((MultiModel) getContainer().eContainer());

		return newLinkType;
	}

	/**
	 * Creates a new binary link.
	 * 
	 * @param monitor
	 *            The progress monitor.
	 * @param info
	 *            Additional parameter, not used.
	 * @return The ok result.
	 * @throws ExecutionException
	 *             If this command can't be executed.
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

		if (!canExecute()) {
			throw new ExecutionException("Invalid arguments in create link command");
		}

		try {
			BinaryLink newElement = MultiModelConstraintChecker.isInstancesLevel(getContainer()) ?
				doExecuteInstancesLevel() :
				doExecuteTypesLevel();
			doConfigure(newElement, monitor, info);
			((CreateElementRequest) getRequest()).setNewElement(newElement);
	
			return CommandResult.newOKCommandResult(newElement);
		}
		catch (ExecutionException ee) {
			throw ee;
		}
		catch (Exception e) {
			MMTFException.print(MMTFException.Type.WARNING, "No binary link created", e);
			return CommandResult.newErrorCommandResult("No binary link created");
		}
	}

}
