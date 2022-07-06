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
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.TinkerGraphTransformation;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.Collection;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;

public class TinkerGraphTransformationRepairPosLength<TTinkerGraphDriver extends GraphDriver>
		extends TinkerGraphTransformation<TinkerGraphPosLengthMatch, TTinkerGraphDriver> {

	public TinkerGraphTransformationRepairPosLength(final TTinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<TinkerGraphPosLengthMatch> matches) {
		for (final TinkerGraphPosLengthMatch match : matches) {
			driver.traversal()
				.V(match.getSegment())
				.property(LENGTH, -match.getLength() + 1)
				.iterate();
		}
	}

}
