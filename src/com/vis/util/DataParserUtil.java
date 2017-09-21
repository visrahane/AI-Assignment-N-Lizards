/**
 * 
 */
package com.vis.util;

import java.util.List;

import com.vis.models.InputData;

/**
 * @author Vis
 *
 */
public class DataParserUtil {

	public static void main(String[] args) {

	}

	public static InputData parseInputData(List<String> inputDataList) {
		InputData inputData = new InputData();
		inputData.setAlgoName(inputDataList.get(0));
		inputData.setGridLength(Integer.parseInt(inputDataList.get(1)));
		inputData.setNoOfLizards(Integer.parseInt(inputDataList.get(2)));
		int[][] inputMatrix = populateInputMatrix(inputDataList, inputData.getGridLength());
		inputData.setGrid(inputMatrix);
		return inputData;

	}

	private static int[][] populateInputMatrix(List<String> inputDataList, int matrixLength) {
		int[][] inputMatrix = new int[matrixLength][matrixLength];
		for (int k = 3, i = 0; k < matrixLength + 3; k++, i++) {
			String line = inputDataList.get(k);
			for (int j = 0; j < matrixLength; j++) {
				inputMatrix[i][j] = line.charAt(j) - 48;
			}
		}
		return inputMatrix;
	}
}
