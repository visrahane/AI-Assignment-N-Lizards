/**
 * 
 */
package com.vis.starter;

import java.util.List;

import com.vis.factory.AlgorithmFactory;
import com.vis.models.InputData;
import com.vis.models.OutputData;
import com.vis.reader.InputReader;
import com.vis.service.AlgorithmService;
import com.vis.util.DataParserUtil;

/**
 * @author Vis
 *
 */
public class Starter {

	public static void main(String[] args) {
		InputReader inputReader = new InputReader("input.txt");
		List<String> inputDataList = inputReader.readFile();
		InputData inputData = DataParserUtil.parseInputData(inputDataList);
		AlgorithmService algorithmService = AlgorithmFactory.getAlgorithm(inputData.getAlgoName());
		OutputData outputData = algorithmService.runAlgorithm(inputData);
	}

}
