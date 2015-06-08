/**
 * Copyright (c) 2012-2015 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 */
package edu.toronto.cs.se.modelepedia.powerwindow.operator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;

import edu.toronto.cs.se.mmint.MultiModelTypeRegistry;
import edu.toronto.cs.se.mmint.mid.GenericElement;
import edu.toronto.cs.se.mmint.mid.Model;
import edu.toronto.cs.se.mmint.mid.ModelOrigin;
import edu.toronto.cs.se.mmint.mid.MultiModel;
import edu.toronto.cs.se.mmint.mid.library.MultiModelUtils;
import edu.toronto.cs.se.mmint.mid.operator.impl.ConversionOperatorImpl;
import edu.toronto.cs.se.modelepedia.petrinet.PetriNet;
import edu.toronto.cs.se.modelepedia.petrinet.PetriNetFactory;
import edu.toronto.cs.se.modelepedia.petrinet.PetriNetPackage;
import edu.toronto.cs.se.modelepedia.petrinet.Place;
import edu.toronto.cs.se.modelepedia.powerwindow.Window;

public class PowerWindowToPetriNet extends ConversionOperatorImpl {

	// input-output
	private final static @NonNull String IN_MODEL = "window";
	private final static @NonNull String OUT_MODEL = "petrinet";
	// constants
	private static final int DELAY_BOUND = 50;
	private static final String FILE_SUFFIX = "_pw2pn";

	private Model newPetrinetModel;

	@Override
	public Map<String, Model> run(
			Map<String, Model> inputsByName, Map<String, GenericElement> genericsByName,
			Map<String, MultiModel> outputMIDsByName) throws Exception {

		// input
		Model windowModel = inputsByName.get(IN_MODEL);
		MultiModel instanceMID = outputMIDsByName.get(OUT_MODEL);

		// convert
		Window window = (Window) windowModel.getEMFInstanceRoot();
		PetriNet newPetrinet = PetriNetFactory.eINSTANCE.createPetriNet();
		if (window.getSensor() != null && window.getSensor().getDelay() < DELAY_BOUND) {
			Place newPlace = PetriNetFactory.eINSTANCE.createPlace();
			newPetrinet.getNodes().add(newPlace);
		}

		// output
		String newPetrinetModelUri = MultiModelUtils.replaceFileExtensionInUri(windowModel.getUri(), PetriNetPackage.eNAME);
		newPetrinetModelUri = MultiModelUtils.addFileNameSuffixInUri(newPetrinetModelUri, FILE_SUFFIX + (new Date()).getTime());
		MultiModelUtils.createModelFile(newPetrinet, newPetrinetModelUri, true);
		Model petrinetModelType = MultiModelTypeRegistry.getType(PetriNetPackage.eNS_URI);
		newPetrinetModel = petrinetModelType.createMAVOInstanceAndEditor(newPetrinetModelUri, ModelOrigin.CREATED, instanceMID);
		Map<String, Model> outputsByName = new HashMap<>();
		outputsByName.put(OUT_MODEL, newPetrinetModel);

		return outputsByName;
	}

	@Override
	public void cleanup() throws Exception {

		if (newPetrinetModel != null) {
			MultiModelUtils.deleteModelFile(newPetrinetModel);
			newPetrinetModel = null;
		}
	}

}
