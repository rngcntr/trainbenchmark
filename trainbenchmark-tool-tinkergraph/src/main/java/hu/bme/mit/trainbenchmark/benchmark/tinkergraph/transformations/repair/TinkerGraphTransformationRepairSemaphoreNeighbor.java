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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.GraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.TinkerGraphTransformation;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.Collection;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;

public class TinkerGraphTransformationRepairSemaphoreNeighbor<TTinkerGraphDriver extends GraphDriver>
		extends TinkerGraphTransformation<TinkerGraphSemaphoreNeighborMatch, TTinkerGraphDriver> {

	public TinkerGraphTransformationRepairSemaphoreNeighbor(final TTinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<TinkerGraphSemaphoreNeighborMatch> matches) {
		for (final TinkerGraphSemaphoreNeighborMatch match : matches) {
			driver.traversal().V(match.getRoute2())
				.not(__.outE(ENTRY))
				.addE(ENTRY).to(match.getSemaphore())
				.iterate();
		}
	}

}
