/**
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
package edu.toronto.cs.se.mmtf.mid.mapping.provider;

import edu.toronto.cs.se.mmtf.mid.mapping.util.MappingAdapterFactory;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MappingItemProviderAdapterFactory extends MappingAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmtf.mid.mapping.MappingReference} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MappingReferenceItemProvider mappingReferenceItemProvider;

	/**
	 * This creates an adapter for a {@link edu.toronto.cs.se.mmtf.mid.mapping.MappingReference}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createMappingReferenceAdapter() {
		if (mappingReferenceItemProvider == null) {
			mappingReferenceItemProvider = new MappingReferenceItemProvider(this);
		}

		return mappingReferenceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmtf.mid.mapping.BinaryMappingReference} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BinaryMappingReferenceItemProvider binaryMappingReferenceItemProvider;

	/**
	 * This creates an adapter for a {@link edu.toronto.cs.se.mmtf.mid.mapping.BinaryMappingReference}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBinaryMappingReferenceAdapter() {
		if (binaryMappingReferenceItemProvider == null) {
			binaryMappingReferenceItemProvider = new BinaryMappingReferenceItemProvider(this);
		}

		return binaryMappingReferenceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmtf.mid.mapping.ModelContainer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelContainerItemProvider modelContainerItemProvider;

	/**
	 * This creates an adapter for a {@link edu.toronto.cs.se.mmtf.mid.mapping.ModelContainer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createModelContainerAdapter() {
		if (modelContainerItemProvider == null) {
			modelContainerItemProvider = new ModelContainerItemProvider(this);
		}

		return modelContainerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmtf.mid.mapping.ModelElementReference} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelElementReferenceItemProvider modelElementReferenceItemProvider;

	/**
	 * This creates an adapter for a {@link edu.toronto.cs.se.mmtf.mid.mapping.ModelElementReference}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createModelElementReferenceAdapter() {
		if (modelElementReferenceItemProvider == null) {
			modelElementReferenceItemProvider = new ModelElementReferenceItemProvider(this);
		}

		return modelElementReferenceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmtf.mid.mapping.MappingLink} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MappingLinkItemProvider mappingLinkItemProvider;

	/**
	 * This creates an adapter for a {@link edu.toronto.cs.se.mmtf.mid.mapping.MappingLink}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createMappingLinkAdapter() {
		if (mappingLinkItemProvider == null) {
			mappingLinkItemProvider = new MappingLinkItemProvider(this);
		}

		return mappingLinkItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmtf.mid.mapping.BinaryMappingLink} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BinaryMappingLinkItemProvider binaryMappingLinkItemProvider;

	/**
	 * This creates an adapter for a {@link edu.toronto.cs.se.mmtf.mid.mapping.BinaryMappingLink}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBinaryMappingLinkAdapter() {
		if (binaryMappingLinkItemProvider == null) {
			binaryMappingLinkItemProvider = new BinaryMappingLinkItemProvider(this);
		}

		return binaryMappingLinkItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmtf.mid.mapping.HomomorphicMappingReference} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HomomorphicMappingReferenceItemProvider homomorphicMappingReferenceItemProvider;

	/**
	 * This creates an adapter for a {@link edu.toronto.cs.se.mmtf.mid.mapping.HomomorphicMappingReference}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createHomomorphicMappingReferenceAdapter() {
		if (homomorphicMappingReferenceItemProvider == null) {
			homomorphicMappingReferenceItemProvider = new HomomorphicMappingReferenceItemProvider(this);
		}

		return homomorphicMappingReferenceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmtf.mid.mapping.HomomorphicMappingLink} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HomomorphicMappingLinkItemProvider homomorphicMappingLinkItemProvider;

	/**
	 * This creates an adapter for a {@link edu.toronto.cs.se.mmtf.mid.mapping.HomomorphicMappingLink}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createHomomorphicMappingLinkAdapter() {
		if (homomorphicMappingLinkItemProvider == null) {
			homomorphicMappingLinkItemProvider = new HomomorphicMappingLinkItemProvider(this);
		}

		return homomorphicMappingLinkItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (mappingReferenceItemProvider != null) mappingReferenceItemProvider.dispose();
		if (binaryMappingReferenceItemProvider != null) binaryMappingReferenceItemProvider.dispose();
		if (modelContainerItemProvider != null) modelContainerItemProvider.dispose();
		if (modelElementReferenceItemProvider != null) modelElementReferenceItemProvider.dispose();
		if (mappingLinkItemProvider != null) mappingLinkItemProvider.dispose();
		if (binaryMappingLinkItemProvider != null) binaryMappingLinkItemProvider.dispose();
		if (homomorphicMappingReferenceItemProvider != null) homomorphicMappingReferenceItemProvider.dispose();
		if (homomorphicMappingLinkItemProvider != null) homomorphicMappingLinkItemProvider.dispose();
	}

}
