package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.SwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.Random;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import eu.mondo.driver.graph.util.RDFUtil;

public class SwitchSensorXForm extends SwitchSensor implements TransformationBenchmarkCase {

	long newSensorId = 1000000000;

	@Override
	public void modify() throws IOException {
		final int nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// start the modification
		final long start = System.nanoTime();

		final Random random = bmr.getRandom();
		final int size = invalids.size();

		// edit
		final long startEdit = System.nanoTime();

		final Multimap<String, String> edges = ArrayListMultimap.create();
		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final Long switchId = invalids.get(rndTarget);

			// create a new sensor connected to the switch
			// long sensor = client.insertVertex(SENSOR);
			// driver.insertEdge(aSwitch, sensor, TRACKELEMENT_SENSOR);
			newSensorId++;
			String switchURI = RDFUtil.toURI(RDFConstants.BASE_PREFIX, switchId);
			String newSensorURI = RDFUtil.toURI(RDFConstants.BASE_PREFIX, newSensorId);
			edges.put(switchURI, newSensorURI);
		}

		driver.insertEdgesWithVertex(edges, RDFConstants.BASE_PREFIX + ModelConstants.TRACKELEMENT_SENSOR, RDFConstants.BASE_PREFIX + ModelConstants.SENSOR);

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}
}
