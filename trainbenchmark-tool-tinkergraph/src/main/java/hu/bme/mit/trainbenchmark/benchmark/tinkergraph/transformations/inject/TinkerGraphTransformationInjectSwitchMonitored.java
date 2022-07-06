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
import java.util.stream.Collectors;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.GraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.TinkerGraphTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class TinkerGraphTransformationInjectSwitchMonitored<TTinkerGraphDriver extends GraphDriver>
		extends TinkerGraphTransformation<TinkerGraphSwitchMonitoredInjectMatch, TTinkerGraphDriver> {

	public TinkerGraphTransformationInjectSwitchMonitored(final TTinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<TinkerGraphSwitchMonitoredInjectMatch> matches) {
		driver.traversal()
			.V(matches.stream().map(m -> m.getSw()).collect(Collectors.toList()))
			.outE(ModelConstants.MONITORED_BY)
			.drop().iterate();
	}

}
