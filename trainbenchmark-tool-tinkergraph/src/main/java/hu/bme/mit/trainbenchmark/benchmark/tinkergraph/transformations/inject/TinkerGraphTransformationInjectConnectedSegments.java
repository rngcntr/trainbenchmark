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

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.GraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.TinkerGraphTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.Collection;

public class TinkerGraphTransformationInjectConnectedSegments<TTinkerGraphDriver extends GraphDriver>
		extends TinkerGraphTransformation<TinkerGraphConnectedSegmentsInjectMatch, TTinkerGraphDriver> {

	public TinkerGraphTransformationInjectConnectedSegments(final TTinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<TinkerGraphConnectedSegmentsInjectMatch> matches) throws Exception {
		for (final TinkerGraphConnectedSegmentsInjectMatch match : matches) {
			// create (segment2) node
			Vertex segment2 = driver.traversal().addV(ModelConstants.SEGMENT)
				.property(ModelConstants.ID, driver.generateNewVertexId())
				.property(ModelConstants.LENGTH, TrainBenchmarkConstants.DEFAULT_SEGMENT_LENGTH)
				.next();

			// (segment2)-[:monitoredBy]->(sensor)
			driver.traversal().addE(ModelConstants.MONITORED_BY).from(segment2).to(match.getSensor()).iterate();

			// (segment1)-[:connectsTo]->(segment2)
			driver.traversal().addE(ModelConstants.CONNECTS_TO).from(match.getSegment1()).to(segment2).iterate();
			// (segment2)-[:connectsTo]->(segment3)
			driver.traversal().addE(ModelConstants.CONNECTS_TO).from(segment2).to(match.getSegment3()).iterate();

			// remove (segment1)-[:connectsTo]->(segment3)
			driver.traversal().V(match.getSegment1())
				.outE(ModelConstants.CONNECTS_TO)
				.where(__.inV().is(match.getSegment3()))
				.drop().iterate();
		}
	}

}
