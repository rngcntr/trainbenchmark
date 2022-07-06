package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver;

import org.apache.tinkerpop.gremlin.process.traversal.IO;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import java.io.IOException;

public class TinkerGraphDriver extends GraphDriver {

	protected TinkerGraphDriver() throws IOException {
		graph = TinkerGraph.open();
		g = graph.traversal();
	}

	@Override
	public void read(String modelPath) {
		System.out.println();
		g.io(modelPath).with(IO.reader, IO.graphml).read().iterate();
	}
}
