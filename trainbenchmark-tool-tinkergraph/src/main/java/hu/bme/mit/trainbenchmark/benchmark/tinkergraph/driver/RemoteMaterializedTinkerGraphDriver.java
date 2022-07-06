package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver;

import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.ser.Serializers;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.groovy.jsr223.dsl.credential.__;
import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.IO;
import org.apache.tinkerpop.gremlin.tinkergraph.process.traversal.materialized.AbstractMaterializedView;
import org.apache.tinkerpop.gremlin.tinkergraph.process.traversal.materialized.MaterializedTraversalSource;
import org.apache.tinkerpop.gremlin.tinkergraph.process.traversal.materialized.MaterializedTraversalStore;
import org.apache.tinkerpop.gremlin.tinkergraph.process.traversal.materialized.MaterializedView;

import java.io.IOException;

public class RemoteMaterializedTinkerGraphDriver extends GraphDriver {

	private final Cluster cluster;

	protected RemoteMaterializedTinkerGraphDriver() throws IOException {
		cluster = Cluster.build()
			.addContactPoint("localhost")
			.port(8182)
			.serializer(Serializers.GRAPHBINARY_V1D0.simpleInstance())
			.create();
		try {
			g = AnonymousTraversalSource.traversal(MaterializedTraversalSource.class).withRemote(DriverRemoteConnection.using(cluster, "g"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		initializeViews();
	}

	private void initializeViews() {
		AbstractMaterializedView<?, ?> switchMonitored = new MaterializedView<>("SwitchMonitored", g.V().hasLabel("Switch").not(__.outE("monitoredBy")).as("sw").asAdmin());
		MaterializedTraversalStore.getInstance().registerView(switchMonitored);
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
