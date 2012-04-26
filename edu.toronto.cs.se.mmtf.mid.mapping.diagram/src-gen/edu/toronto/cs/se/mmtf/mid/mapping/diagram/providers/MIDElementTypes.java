/*
 * Copyright (C) 2012 Marsha Chechik, Alessio Di Sandro, Rick Salay
 * 
 * This file is part of MMTF ver. 0.9.0.
 * 
 * MMTF is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * MMTF is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MMTF.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.toronto.cs.se.mmtf.mid.mapping.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

import edu.toronto.cs.se.mmtf.mid.mapping.MappingPackage;
import edu.toronto.cs.se.mmtf.mid.mapping.diagram.edit.parts.BinaryMappingLinkEditPart;
import edu.toronto.cs.se.mmtf.mid.mapping.diagram.edit.parts.MappingLinkEditPart;
import edu.toronto.cs.se.mmtf.mid.mapping.diagram.edit.parts.MappingLinkElementsEditPart;
import edu.toronto.cs.se.mmtf.mid.mapping.diagram.edit.parts.MappingReferenceEditPart;
import edu.toronto.cs.se.mmtf.mid.mapping.diagram.edit.parts.ModelContainerEditPart;
import edu.toronto.cs.se.mmtf.mid.mapping.diagram.edit.parts.ModelElementReferenceEditPart;
import edu.toronto.cs.se.mmtf.mid.mapping.diagram.part.MIDDiagramEditorPlugin;

/**
 * @generated
 */
public class MIDElementTypes {

	/**
	 * @generated
	 */
	private MIDElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType MappingReference_1000 = getElementType("edu.toronto.cs.se.mmtf.mid.mapping.diagram.MappingReference_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ModelContainer_2005 = getElementType("edu.toronto.cs.se.mmtf.mid.mapping.diagram.ModelContainer_2005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType MappingLink_2006 = getElementType("edu.toronto.cs.se.mmtf.mid.mapping.diagram.MappingLink_2006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ModelElementReference_3003 = getElementType("edu.toronto.cs.se.mmtf.mid.mapping.diagram.ModelElementReference_3003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType MappingLinkElements_4004 = getElementType("edu.toronto.cs.se.mmtf.mid.mapping.diagram.MappingLinkElements_4004"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType BinaryMappingLink_4005 = getElementType("edu.toronto.cs.se.mmtf.mid.mapping.diagram.BinaryMappingLink_4005"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return MIDDiagramEditorPlugin.getInstance()
						.getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(MappingReference_1000,
					MappingPackage.eINSTANCE.getMappingReference());

			elements.put(ModelContainer_2005,
					MappingPackage.eINSTANCE.getModelContainer());

			elements.put(MappingLink_2006,
					MappingPackage.eINSTANCE.getMappingLink());

			elements.put(ModelElementReference_3003,
					MappingPackage.eINSTANCE.getModelElementReference());

			elements.put(MappingLinkElements_4004,
					MappingPackage.eINSTANCE.getMappingLink_Elements());

			elements.put(BinaryMappingLink_4005,
					MappingPackage.eINSTANCE.getBinaryMappingLink());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(MappingReference_1000);
			KNOWN_ELEMENT_TYPES.add(ModelContainer_2005);
			KNOWN_ELEMENT_TYPES.add(MappingLink_2006);
			KNOWN_ELEMENT_TYPES.add(ModelElementReference_3003);
			KNOWN_ELEMENT_TYPES.add(MappingLinkElements_4004);
			KNOWN_ELEMENT_TYPES.add(BinaryMappingLink_4005);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case MappingReferenceEditPart.VISUAL_ID:
			return MappingReference_1000;
		case ModelContainerEditPart.VISUAL_ID:
			return ModelContainer_2005;
		case MappingLinkEditPart.VISUAL_ID:
			return MappingLink_2006;
		case ModelElementReferenceEditPart.VISUAL_ID:
			return ModelElementReference_3003;
		case MappingLinkElementsEditPart.VISUAL_ID:
			return MappingLinkElements_4004;
		case BinaryMappingLinkEditPart.VISUAL_ID:
			return BinaryMappingLink_4005;
		}
		return null;
	}

}
