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

package hu.bme.mit.trainbenchmark.config;

public abstract class AbstractConfig {

	protected int xms;
	protected int xmx;

	protected AbstractConfig() {
	}
	
	public AbstractConfig(int xms, int xmx) {
		this.xms = xms;
		this.xmx = xmx;
	}

	public String getWorkspacePath() {
		return "../";
	}

	public int getXms() {
		return xms;
	}
	
	public int getXmx() {
		return xmx;
	}

}
