package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;

public class TinkerGraphDriverFactory extends DriverFactory<GraphDriver> {

	@Override
	public GraphDriver createInstance() throws Exception {
		return new RemoteTinkerGraphDriver();
	}

}
