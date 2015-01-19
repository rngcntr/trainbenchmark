/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.sesame;

import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.sesame.config.SesameBenchmarkConfig;

import org.apache.commons.cli.ParseException;

public class SesameBenchmarkLogic extends GenericBenchmarkLogic {

	SesameBenchmarkConfig sbc;

	public SesameBenchmarkLogic(final String[] args) throws ParseException {
		super(args);
		bc = sbc = new SesameBenchmarkConfig(args);
	}

	@Override
	protected String getPackageName() {
		return "sesame";
	}

}
