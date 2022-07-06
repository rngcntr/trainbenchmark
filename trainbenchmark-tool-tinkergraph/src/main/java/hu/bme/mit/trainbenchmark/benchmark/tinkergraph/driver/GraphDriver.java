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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.apache.tinkerpop.gremlin.groovy.jsr223.GremlinGroovyScriptEngine;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import javax.script.Bindings;
import javax.script.ScriptException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class GraphDriver extends Driver {

	protected GraphTraversalSource g;
	protected Graph graph;

	protected GraphDriver() throws IOException {
		super();
	}

	public GraphTraversalSource traversal() {
		return g;
	}

	public Graph getGraph() {
		return graph;
	}

	@Override
	public String getPostfix() {
		return "-tinkerpop.graphml";
	}

	public Collection<TinkerGraphMatch> runQuery(final RailwayQuery query, final String queryDefinition) throws ScriptException {
		//System.out.printf("Running Query: %s\n", queryDefinition);
		final GremlinGroovyScriptEngine engine = new GremlinGroovyScriptEngine();
		final Bindings bindings = engine.createBindings();
		bindings.put("g", g);

		final GraphTraversal traversal = (GraphTraversal) engine.eval(queryDefinition, bindings);
		final List<TinkerGraphMatch> results = new ArrayList<>();
		while (traversal.hasNext()) {
			// The traversal returns either a single Object (e.g. a Vertex) or a Map<String, Object>
			// if there are multiple values in a row
			//
			// For details, see http://tinkerpop.apache.org/docs/3.3.0/reference/
			//
			// "Select objects out of a Map<String,Object> flow (i.e. a sub-map). [...]
			// When there is only one label selected, then a single object is returned."
			Object row = traversal.next();
			results.add(TinkerGraphMatch.createMatchFromGremlinResult(query, row));
		}
		return results;
	}

	@Override
	public Number generateNewVertexId() {
		return 0;
	}

}
