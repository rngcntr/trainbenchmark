package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphEngine;

public class TinkerGraphDriverFactory extends DriverFactory<GraphDriver> {

	private final TinkerGraphEngine engine;

	public TinkerGraphDriverFactory(TinkerGraphEngine engine) {
		super();
		this.engine = engine;
	}
	@Override
	public GraphDriver createInstance() throws Exception {
		switch (engine) {
			case LOCAL:
				return new TinkerGraphDriver();
			case REMOTE:
				return new RemoteTinkerGraphDriver();
			case REMOTE_MATERIALIZED:
				return new RemoteMaterializedTinkerGraphDriver();
			default:
				throw new UnsupportedOperationException("No driver found for engine: " + engine);
		}
	}
}
