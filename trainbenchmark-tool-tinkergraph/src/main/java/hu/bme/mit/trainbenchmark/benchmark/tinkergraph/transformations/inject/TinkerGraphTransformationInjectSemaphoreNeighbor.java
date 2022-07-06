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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.inject;

import java.util.Collection;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.GraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.TinkerGraphTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class TinkerGraphTransformationInjectSemaphoreNeighbor<TTinkerGraphDriver extends GraphDriver>
		extends TinkerGraphTransformation<TinkerGraphSemaphoreNeighborInjectMatch, TTinkerGraphDriver> {

	public TinkerGraphTransformationInjectSemaphoreNeighbor(final TTinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<TinkerGraphSemaphoreNeighborInjectMatch> matches) {
		for (final TinkerGraphSemaphoreNeighborInjectMatch match : matches) {
			driver.traversal()
				.V(match.getRoute())
				.outE(ModelConstants.ENTRY)
				.where(__.inV().is(match.getSemaphore()))
				.drop().iterate();
		}
	}

}
