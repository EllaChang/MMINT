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
package edu.toronto.cs.se.mmint.z3.reasoning;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import edu.toronto.cs.se.mmint.MultiModelTypeRegistry;
import edu.toronto.cs.se.mmint.mavo.library.MAVOUtils;
import edu.toronto.cs.se.mmint.mid.MidLevel;
import edu.toronto.cs.se.mmint.mid.Model;
import edu.toronto.cs.se.mmint.mid.constraint.MultiModelConstraintChecker.MAVOTruthValue;
import edu.toronto.cs.se.mmint.mid.operator.Operator;
import edu.toronto.cs.se.mmint.reasoning.ReasoningEngine;
import edu.toronto.cs.se.mmint.z3.Z3SMTIncrementalSolver;
import edu.toronto.cs.se.mmint.z3.Z3SMTIncrementalSolver.Z3IncrementalBehavior;
import edu.toronto.cs.se.mmint.z3.Z3SMTUtils;
import edu.toronto.cs.se.mmint.z3.Z3SMTUtils.Z3BoolResult;
import edu.toronto.cs.se.mmint.z3.Z3SMTUtils.Z3ModelResult;
import edu.toronto.cs.se.mmint.z3.mavo.EcoreMAVOToSMTLIB;

public class Z3SMTReasoningEngine implements ReasoningEngine {

	private final static String ECOREMAVOTOSMTLIB_OPERATOR_URI = "http://se.cs.toronto.edu/modelepedia/Operator_EcoreMAVOToSMTLIB";
	private static final String CONSTRAINT_LANGUAGE = "SMTLIB";

	@Override
	public MAVOTruthValue checkConstraint(Model model, String constraint, MidLevel constraintLevel) {

		if (!MAVOUtils.isMAVOModel(model)) {
			return MAVOTruthValue.FALSE;
		}

		EcoreMAVOToSMTLIB ecore2smt = (EcoreMAVOToSMTLIB) MultiModelTypeRegistry.<Operator>getType(ECOREMAVOTOSMTLIB_OPERATOR_URI);
		EList<Model> actualParameters = new BasicEList<Model>();
		actualParameters.add(model);
		try {
			ecore2smt.execute(actualParameters);
		}
		catch (Exception e) {
			return MAVOTruthValue.FALSE;
		}
		ecore2smt.cleanup();

		// tri-state logic
		Z3SMTIncrementalSolver z3IncSolver = new Z3SMTIncrementalSolver();
		z3IncSolver.firstCheckSatAndGetModel(ecore2smt.getListener().getSMTEncoding());
		Z3ModelResult z3ModelResult = z3IncSolver.checkSatAndGetModel(Z3SMTUtils.assertion(constraint), Z3IncrementalBehavior.POP);
		boolean propertyTruthValue = z3ModelResult.getZ3BoolResult() == Z3BoolResult.SAT;
		z3ModelResult = z3IncSolver.checkSatAndGetModel(Z3SMTUtils.assertion(Z3SMTUtils.not(constraint)), Z3IncrementalBehavior.POP);
		boolean notPropertyTruthValue = z3ModelResult.getZ3BoolResult() == Z3BoolResult.SAT;

		return MAVOTruthValue.toMAVOTruthValue(propertyTruthValue, notPropertyTruthValue);
	}

	@Override
	public boolean checkConstraintConsistency(Model modelType, String constraint) {

		return true;
	}

	@Override
	public Set<String> getEngineLanguages() {

		Set<String> languages = new HashSet<String>();
		languages.add(CONSTRAINT_LANGUAGE);

		return languages;
	}

}