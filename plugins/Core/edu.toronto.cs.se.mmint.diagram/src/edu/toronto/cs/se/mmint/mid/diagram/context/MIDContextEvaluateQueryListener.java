/**
 * Copyright (c) 2012-2019 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 *    Naama Ben-David - Implementation.
 */
package edu.toronto.cs.se.mmint.mid.diagram.context;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.swt.events.SelectionEvent;

import edu.toronto.cs.se.mmint.mid.MID;
import edu.toronto.cs.se.mmint.mid.Model;
import edu.toronto.cs.se.mmint.mid.diagram.library.MIDContextMenuListener;
import edu.toronto.cs.se.mmint.mid.diagram.library.MIDDiagramUtils;
import edu.toronto.cs.se.mmint.mid.reasoning.MIDConstraintChecker;
import edu.toronto.cs.se.mmint.mid.ui.MIDDialogCancellation;
import edu.toronto.cs.se.mmint.mid.ui.MIDDialogs;

public class MIDContextEvaluateQueryListener extends MIDContextMenuListener {

  private MID mid;
  private List<Model> models;

  public MIDContextEvaluateQueryListener(String menuLabel, MID mid, List<Model> models) {
    super(menuLabel);
    this.mid = mid;
    this.models = models;
  }

  @Override
  public void widgetSelected(SelectionEvent e) {
    var command = new MIDContextEvaluateQueryCommand(TransactionUtil.getEditingDomain(this.mid), this.menuLabel,
                                                     MIDDiagramUtils.getActiveInstanceMIDFiles());
    runListenerCommand(command);
  }

  protected class MIDContextEvaluateQueryCommand extends AbstractTransactionalCommand {

    public MIDContextEvaluateQueryCommand(TransactionalEditingDomain domain, String label, List<IFile> affectedFiles) {
      super(domain, label, affectedFiles);
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
      //TODO Need to insert nulls in a query with some in and some out args?
      //TODO Adapt ocl and check that it still works
      //TODO Add javadoc for IReasoningEngine.evaluateQuery
      //TODO Display results
      try {
        var queryFilePath = MIDDialogs.selectQueryFileToEvaluate();
        var queryName = MIDDialogs.getStringInput("Evaluate query", "Insert pattern name to run", null);
        var queryResult = MIDConstraintChecker.evaluateQuery(queryFilePath, queryName,
                                                             MIDContextEvaluateQueryListener.this.mid,
                                                             MIDContextEvaluateQueryListener.this.models);
        System.err.println(queryResult);
        return CommandResult.newOKCommandResult();
      }
      catch (MIDDialogCancellation e) {
        return CommandResult.newCancelledCommandResult();
      }
    }

  }

}
