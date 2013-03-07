/*
 * 
 */
package edu.toronto.cs.se.modelepedia.classdiagram.diagram.part;

import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import edu.toronto.cs.se.modelepedia.classdiagram.diagram.providers.ClassDiagramElementTypes;

/**
 * @generated
 */
public class ClassDiagramPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createObjects1Group());
		paletteRoot.add(createConnections2Group());
	}

	/**
	 * Creates "Objects" palette tool group
	 * @generated
	 */
	private PaletteContainer createObjects1Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Objects1Group_title);
		paletteContainer.setId("createObjects1Group"); //$NON-NLS-1$
		paletteContainer.add(createAttribute1CreationTool());
		paletteContainer.add(createClass2CreationTool());
		paletteContainer.add(createOperation3CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Connections" palette tool group
	 * @generated
	 */
	private PaletteContainer createConnections2Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Connections2Group_title);
		paletteContainer.setId("createConnections2Group"); //$NON-NLS-1$
		paletteContainer.add(createDependency1CreationTool());
		paletteContainer.add(createNestedIn2CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAttribute1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Attribute1CreationTool_title,
				Messages.Attribute1CreationTool_desc,
				Collections
						.singletonList(ClassDiagramElementTypes.Attribute_3001));
		entry.setId("createAttribute1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ClassDiagramElementTypes
				.getImageDescriptor(ClassDiagramElementTypes.Attribute_3001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createClass2CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Class2CreationTool_title,
				Messages.Class2CreationTool_desc,
				Collections.singletonList(ClassDiagramElementTypes.Class_2001));
		entry.setId("createClass2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ClassDiagramElementTypes
				.getImageDescriptor(ClassDiagramElementTypes.Class_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOperation3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Operation3CreationTool_title,
				Messages.Operation3CreationTool_desc,
				Collections
						.singletonList(ClassDiagramElementTypes.Operation_3002));
		entry.setId("createOperation3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ClassDiagramElementTypes
				.getImageDescriptor(ClassDiagramElementTypes.Operation_3002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createDependency1CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Dependency1CreationTool_title,
				Messages.Dependency1CreationTool_desc,
				Collections
						.singletonList(ClassDiagramElementTypes.Dependency_4001));
		entry.setId("createDependency1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ClassDiagramElementTypes
				.getImageDescriptor(ClassDiagramElementTypes.Dependency_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNestedIn2CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.NestedIn2CreationTool_title,
				Messages.NestedIn2CreationTool_desc,
				Collections
						.singletonList(ClassDiagramElementTypes.ClassNestedIn_4002));
		entry.setId("createNestedIn2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ClassDiagramElementTypes
				.getImageDescriptor(ClassDiagramElementTypes.ClassNestedIn_4002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List<IElementType> elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List<IElementType> relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}