/**
 * Copyright (c) 2012-2019 Marsha Chechik, Alessio Di Sandro, Rick Salay.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Alessio Di Sandro - Implementation.
 */
package edu.toronto.cs.se.mmint.operator.experiment;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Properties;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.jdt.annotation.NonNull;

import edu.toronto.cs.se.mmint.MMINTException;

public class SampleRunner implements Runnable {

  private final static @NonNull String SAMPLE_SUBDIR = "sample";

  private Experiment exp;
  private int expIndex;
  private int sampleIndex;
  private IPath path;

  public SampleRunner(@NonNull ExperimentRunner experimentRunner, int sampleIndex) {
    this.exp = experimentRunner.exp;
    this.expIndex = experimentRunner.expIndex;
    this.sampleIndex = sampleIndex;
    this.path = experimentRunner.path.append(SAMPLE_SUBDIR + this.sampleIndex);
  }

  @Override
  public void run() {
    try {
      // create sample folder
      var folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(this.path);
      if (!folder.exists(null)) {
        folder.create(true, true, null);
      }
      // run samples workflow
      System.err.println(MessageFormat.format("Running experiment {0} out of {1}, sample {2}", this.expIndex,
                                              this.exp.numExperiments-1, this.sampleIndex));
      var inputs = this.exp.input.samplesWorkflow.checkAllowedInputs(ECollections.asEList(this.exp.input.models));
      var samples = this.exp.input.samplesWorkflow.startInstance(inputs, new Properties(), ECollections.emptyEList(),
                                                                 new HashMap<>(), null);
    }
    catch (Exception e) {
      MMINTException.print(IStatus.WARNING, MessageFormat.format("Experiment {0} out of {1}, sample {2} failed",
                                                                 this.expIndex, this.exp.numExperiments-1,
                                                                 this.sampleIndex), e);
    }
  }

}
