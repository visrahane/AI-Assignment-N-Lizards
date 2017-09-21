/**
 *
 */
package com.vis.starter;

import java.util.ArrayList;
import java.util.List;

import com.vis.constants.GridConstants;
import com.vis.constants.IOConstants;
import com.vis.factory.AlgorithmFactory;
import com.vis.models.InputData;
import com.vis.models.OutputData;
import com.vis.reader.InputReader;
import com.vis.reader.OutputWriter;
import com.vis.service.AlgorithmService;
import com.vis.util.DataParserUtil;

/**
 * @author Vis
 *
 */
public class Starter {

	public static void main(String[] args) {
		long curretTime = System.currentTimeMillis();
		InputData inputData = readInput();
		OutputData outputData = runAlgorithm(inputData);
		displayOutput(outputData);
		System.out.println(System.currentTimeMillis() - curretTime);
		// Arrays.deepToString(outputData.getGrid()));
	}

	private static void displayOutput(OutputData outputData) {
		List<String> outputDataList = generateOutput(outputData);
		OutputWriter outputWriter = new OutputWriter("output.txt");
		outputWriter.writeFile(outputDataList);
	}

	public static OutputData runAlgorithm(InputData inputData) {
		AlgorithmService algorithmService = AlgorithmFactory.getAlgorithm(inputData.getAlgoName());
		OutputData outputData = algorithmService.runAlgorithm(inputData);
		return outputData;
	}

	private static List<String> generateOutput(OutputData outputData) {
		List<String> outputDataList = new ArrayList<>();
		if (outputData.isSuccess()) {
			outputDataList.add(IOConstants.SUCCESS);
		}else {
			outputDataList.add(IOConstants.FAIL);
			return outputDataList;
		}
		int[][] outputGrid=outputData.getGrid();
		StringBuffer strBuffer =new StringBuffer();
		for (int[] element : outputGrid) {
			for(int j=0;j<outputGrid.length;j++) {
				if (GridConstants.DANGER.getNumber() == element[j]) {
					strBuffer.append(GridConstants.BLANK.getNumber());
				} else {
					strBuffer.append(element[j]);
				}
			}
			outputDataList.add(strBuffer.toString());
			strBuffer.delete(0, outputGrid.length);
		}
		return outputDataList;
	}

	private static InputData readInput() {
		InputReader inputReader = new InputReader("input.txt");
		List<String> inputDataList = inputReader.readFile();
		InputData inputData = DataParserUtil.parseInputData(inputDataList);
		return inputData;
	}

}
