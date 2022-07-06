package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config;

public enum TinkerGraphEngine {
	LOCAL("Local"),
	REMOTE("Remote"),
	REMOTE_MATERIALIZED("RemoteMaterialized");

	private String name;

	TinkerGraphEngine(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
