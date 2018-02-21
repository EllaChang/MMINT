/**
 * Copyright (c) 2012-2017 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay, Nick Fung.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 *    Nick Fung - Implementation.
 */
package edu.toronto.cs.se.modelepedia.safetycase.operator;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import edu.toronto.cs.se.mmint.operator.slice.Slice;
import edu.toronto.cs.se.modelepedia.safetycase.ASIL;
import edu.toronto.cs.se.modelepedia.safetycase.Context;
import edu.toronto.cs.se.modelepedia.safetycase.Goal;
import edu.toronto.cs.se.modelepedia.safetycase.InContextOf;
import edu.toronto.cs.se.modelepedia.safetycase.SafetyCase;
import edu.toronto.cs.se.modelepedia.safetycase.Solution;
import edu.toronto.cs.se.modelepedia.safetycase.Strategy;
import edu.toronto.cs.se.modelepedia.safetycase.SupportedBy;

public class GSNSliceRevise extends Slice {
	
	@Override
	// Get all model elements in a safety case that needs to be re-checked 
	// given the input set of elements that needs to be revised.
	public Set<EObject> getAllImpactedElements(Set<EObject> criterion) {
		Set<EObject> impacted = new HashSet<>(criterion);
		
		// Iterate through the input set of revised elements to identify 
		// all model elements that require re-checking.
		for (EObject elem : criterion) {
			impacted.addAll(getDirectlyImpactedElements(elem, criterion));
		}
		
		return impacted;
	}
	
	// Get impacted model elements directly reachable from the input element.
	@Override
	public Set<EObject> getDirectlyImpactedElements(EObject elem, Set<EObject> alreadyImpacted) {
		Set<EObject> impacted = new HashSet<>();
		
		// If input is a safety case, then the following are also impacted:
		// 1) Its goals, strategies, solutions, ASILs and contexts.
		if (elem instanceof SafetyCase) {
			SafetyCase sc = (SafetyCase) elem;
			impacted.addAll(sc.getGoals());
			impacted.addAll(sc.getStrategies());
			impacted.addAll(sc.getSolutions());
			impacted.addAll(sc.getContexts());
			impacted.addAll(sc.getASILLevels());
			
		// If input is a goal, then the following are also impacted:
		// 1) All SupportedBy relations connected to it.
		} else if (elem instanceof Goal) {
			Goal g = (Goal) elem;
			impacted.addAll(g.getSupportedBy());
			impacted.addAll(g.getSupports());
		
		// If input is a strategy, then the following are also impacted:
		// 1) All SupportedBy relations connected to it.
		} else if (elem instanceof Strategy) {
			Strategy s = (Strategy) elem;
			impacted.addAll(s.getSupportedBy());
			impacted.addAll(s.getSupports());
			
		// If input is a solution, then the following are also impacted:
		// 1) All SupportedBy relations connected to it.
		} else if (elem instanceof Solution) {
			Solution s = (Solution) elem;
			impacted.addAll(s.getSupports());
			
		// If input is a context, then the following are also impacted:
		// 1) All InContextOf relations connected to it. 
		} else if (elem instanceof Context) {
			Context c = (Context) elem;
			impacted.addAll(c.getContextOf());

		// If input is an ASIL, then the following are also impacted:
		// 1) All InContextOf relations connected to it. 
		} else if (elem instanceof ASIL) {
			ASIL a = (ASIL) elem;
			impacted.addAll(a.getContextOf());
			
		// If input is a SupportedBy relation, then its source and 
		// target model element is potentially impacted.
		} else if (elem instanceof SupportedBy) {
			SupportedBy rel = (SupportedBy) elem;
			impacted.add(rel.getConclusion());
			impacted.add(rel.getPremise());
			
		// If input is a InContextOf relation, then its source and
		// target model element is potentially impacted.
		} else if (elem instanceof InContextOf) {
			InContextOf rel = (InContextOf) elem;
			impacted.add(rel.getContext());
			impacted.add(rel.getContextOf());
		}
		
		// TO-DO: Check for cases where null may be added to the impacted set.
	
		return impacted;
	}
}