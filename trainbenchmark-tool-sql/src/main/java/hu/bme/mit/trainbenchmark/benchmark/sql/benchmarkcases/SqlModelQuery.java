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
package hu.bme.mit.trainbenchmark.benchmark.sql.benchmarkcases;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SqlDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class SqlModelQuery<TSQLDriver extends SqlDriver> extends ModelQuery<SqlMatch, TSQLDriver> {

	protected final String queryDefinition; 
	protected PreparedStatement statement;
	
	public SqlModelQuery(final TSQLDriver driver, final BenchmarkConfigCore benchmarkConfig, final RailwayQuery query) throws IOException, SQLException {
		super(query, driver);

		final String queryPath = benchmarkConfig.getWorkspaceDir() + driver.getResourceDirectory() + "queries/" + query + ".sql";
		queryDefinition = FileUtils.readFileToString(new File(queryPath));
	}

	@Override
	public Collection<SqlMatch> evaluate() throws SQLException {
		if (statement == null) {
			statement = driver.getConnection().prepareStatement(queryDefinition);
		}
		return driver.runStatement(query, statement);
	}

}
