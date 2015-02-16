/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;
import java.util.List;

public class RouteSensor extends TransformationDefinition {

	public RouteSensor(final BenchmarkResult bmr, final DatabaseDriver driver) {
		super(bmr, driver);
	}

	@Override
	protected void lhs() throws IOException {
		final List<? extends Object> routes = driver.collectVertices(ModelConstants.ROUTE);
		itemsToModify = Transformation.pickRandom(nElemToModify, routes);
	}

	@Override
	protected void rhs() throws IOException {
		for (final Object route : itemsToModify) {
			driver.deleteOneOutgoingEdge(route, ModelConstants.ROUTE_ROUTEDEFINITION);
		}
	}

}