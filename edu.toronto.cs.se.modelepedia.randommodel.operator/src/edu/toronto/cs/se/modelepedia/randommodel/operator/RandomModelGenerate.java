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
package edu.toronto.cs.se.modelepedia.randommodel.operator;

import java.net.URL;
import java.util.Date;
import java.util.Properties;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;

import edu.toronto.cs.se.mmtf.mid.Model;
import edu.toronto.cs.se.mmtf.mid.ModelOrigin;
import edu.toronto.cs.se.mmtf.mid.MultiModel;
import edu.toronto.cs.se.mmtf.mid.editor.Editor;
import edu.toronto.cs.se.mmtf.mid.operator.impl.OperatorExecutableImpl;
import edu.toronto.cs.se.mmtf.mid.trait.MultiModelInstanceFactory;
import edu.toronto.cs.se.mmtf.mid.trait.MultiModelOperatorUtils;
import edu.toronto.cs.se.modelepedia.randommodel.RandomModel;
import edu.toronto.cs.se.modelepedia.randommodel.RandomModelPackage;

public class RandomModelGenerate extends OperatorExecutableImpl {

	private static final String RANDOM_SUFFIX = "_random_";
	private static final String PYTHON_SCRIPT = "script/graph_gen.py";
	/** % of annotated elements in the random model. */
	private static final String PROPERTY_IN_ANNOTATIONS = "annotations";
	/** % of may elements among the annotated elements. */
	private static final String PROPERTY_IN_MAY = "may";
	/** % of set elements among the annotated elements. */
	private static final String PROPERTY_IN_SET = "set";
	/** % of var elements among the annotated elements. */
	private static final String PROPERTY_IN_VAR = "var";
	/** The initial seed for the pseudorandom generator. */
	private static final String PROPERTY_IN_SEED = "seed";
	/** The default initial seed for the pseudorandom generator. */
	private static final String PROPERTY_IN_SEED_DEFAULT = null;
	/** The file name for loading/saving the random generator internal state. */
	private static final String PROPERTY_IN_STATE = "state";
	/** The default file name for loading/saving the random generator internal state. */
	private static final String PROPERTY_IN_STATE_DEFAULT = null;

	private double annotations;
	private double may;
	private double set;
	private double var;
	private String seed;
	private String state;

	private void readProperties(Properties properties) throws Exception {

		annotations = MultiModelOperatorUtils.getDoubleProperty(properties, PROPERTY_IN_ANNOTATIONS);
		may = MultiModelOperatorUtils.getDoubleProperty(properties, PROPERTY_IN_MAY);
		set = MultiModelOperatorUtils.getDoubleProperty(properties, PROPERTY_IN_SET);
		var = MultiModelOperatorUtils.getDoubleProperty(properties, PROPERTY_IN_VAR);
		seed = MultiModelOperatorUtils.getOptionalStringProperty(properties, PROPERTY_IN_SEED, PROPERTY_IN_SEED_DEFAULT);
		state = MultiModelOperatorUtils.getOptionalStringProperty(properties, PROPERTY_IN_STATE, PROPERTY_IN_STATE_DEFAULT);
	}

	@Override
	public EList<Model> execute(EList<Model> actualParameters) throws Exception {

		// create random instance
		Model model = actualParameters.get(0);
		Properties inputProperties = MultiModelOperatorUtils.getPropertiesFile(
			this,
			model,
			null,
			MultiModelOperatorUtils.INPUT_PROPERTIES_SUFFIX
		);
		readProperties(inputProperties);
		String baseUri = model.getUri().substring(0, model.getUri().lastIndexOf(IPath.SEPARATOR)+1);
		String subdir = MultiModelOperatorUtils.getCreateSubdir(model, inputProperties);
		if (subdir != null) {
			baseUri += subdir + IPath.SEPARATOR;
		}
		String modelType = ((RandomModel) model.getRoot()).getName();
		String randomUri =
			baseUri +
			modelType +
			RANDOM_SUFFIX +
			(new Date()).getTime() +
			"." + RandomModelPackage.eNAME;
		URL url = RandomModelOperatorActivator.getDefault().getBundle().getEntry("/");
		String pythonPath = FileLocator.toFileURL(url).getPath() + PYTHON_SCRIPT;
		String workspaceUri = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		String[] cmd = new String[] {
			"python",
			pythonPath,
			"-input",
			workspaceUri + model.getUri(),
			"-output",
			workspaceUri + randomUri,
			"-instname",
			randomUri,
			"-annotated",
			String.valueOf(annotations),
			"-may",
			String.valueOf(may),
			"-set",
			String.valueOf(set),
			"-var",
			String.valueOf(var)
		};
		if (seed != null) {
			String[] cmd2 = new String[cmd.length+2];
			System.arraycopy(cmd, 0, cmd2, 0, cmd.length);
			System.arraycopy(new String[] {"-seed", seed}, 0, cmd2, cmd.length, 2);
			cmd = cmd2;
		}
		if (state != null) {
			String[] cmd2 = new String[cmd.length+2];
			System.arraycopy(cmd, 0, cmd2, 0, cmd.length);
			System.arraycopy(new String[] {"-state", state}, 0, cmd2, cmd.length, 2);
			cmd = cmd2;
		}
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec(cmd);
		rt.exec(cmd);
		p.waitFor();

		// create model
		EList<Model> result = new BasicEList<Model>();
		boolean updateMid = MultiModelOperatorUtils.isUpdatingMid(inputProperties);
		URI modelUri = URI.createPlatformResourceURI(randomUri, true);
		MultiModel owner;
		Model newElement;
		if (updateMid) {
			owner = (MultiModel) model.eContainer();
			MultiModelInstanceFactory.assertModelUnique(owner, modelUri);
		}
		else {
			owner = null;
		}
		newElement = MultiModelInstanceFactory.createModel(null, ModelOrigin.CREATED, owner, modelUri);
		if (updateMid) {
			Editor editor = MultiModelInstanceFactory.createEditor(newElement);
			if (editor != null) {
				MultiModelInstanceFactory.addModelEditor(editor, owner);
			}
		}
		result.add(newElement);

		return result;
	}

}