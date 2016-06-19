package hu.bme.mit.trainbenchmark.benchmark.sqlite.transformations.inject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformation.SQLiteTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class SQLiteTransformationInject extends SQLiteTransformation<Long> {

	public SQLiteTransformationInject(final SQLiteDriver driver, final BenchmarkConfigCore benchmarkConfig, final RailwayQuery query)
			throws IOException {
		super(driver, benchmarkConfig, query);
	}

	@Override
	public void activate(final Collection<Long> elements) throws SQLException {
		if (preparedUpdateStatement == null) {
			preparedUpdateStatement = driver.getConnection().prepareStatement(updateQuery);
		}

		for (final Long element : elements) {
			preparedUpdateStatement.setLong(1, element);
			preparedUpdateStatement.executeUpdate();
		}
	}

	
}
