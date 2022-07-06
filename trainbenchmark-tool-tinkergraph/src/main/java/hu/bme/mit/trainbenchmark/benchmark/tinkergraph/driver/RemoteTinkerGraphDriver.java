package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver;

import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.ser.Serializers;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.IO;

import java.io.IOException;

public class RemoteTinkerGraphDriver extends GraphDriver {

	private Cluster cluster;

	protected RemoteTinkerGraphDriver() throws IOException {
		cluster = Cluster.build()
			.addContactPoint("localhost")
			.port(8182)
			.serializer(Serializers.GRAPHBINARY_V1D0.simpleInstance())
			.create();
		try {
			g = AnonymousTraversalSource.traversal().withRemote(DriverRemoteConnection.using(cluster, "g"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		g.V().drop().iterate();
		cluster.close();
	}

	@Override
	public void read(String modelPath) {
		System.out.println();
		g.io(modelPath.replaceFirst("..", "")).with(IO.reader, IO.graphml).read().iterate();
	}
}
