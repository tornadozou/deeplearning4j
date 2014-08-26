package org.deeplearning4j.example.mnist;

import java.io.File;

import org.deeplearning4j.datasets.iterator.DataSetIterator;
import org.deeplearning4j.datasets.iterator.impl.RawMnistDataSetIterator;
import org.deeplearning4j.dbn.DBN;
import org.deeplearning4j.linalg.dataset.DataSet;
import org.deeplearning4j.util.SerializationUtils;

public class RawDBNFinetuneExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		DataSetIterator iter = new RawMnistDataSetIterator(80,60000);
		DBN d = SerializationUtils.readObject(new File(args[0]));
		while(iter.hasNext()) {
			DataSet d2 = iter.next();
			d.setInput(d2.getFeatureMatrix());
			d.finetune(d2.getLabels(), 0.0001f, 1000);
		}
		
		SerializationUtils.saveObject(d, new File(args[1]));
		
		
	}

}
