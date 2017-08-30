/**
 * 
 */
package com.vis.models;

/**
 * @author Vis
 *
 */
public class InputData {

	private String algoName;

	private int noOfLizards;

	private int gridLength;

	private int[][] inputMatrix;

	public String getAlgoName() {
		return algoName;
	}

	public void setAlgoName(String algoName) {
		this.algoName = algoName;
	}

	public int getNoOfLizards() {
		return noOfLizards;
	}

	public void setNoOfLizards(int noOfLizards) {
		this.noOfLizards = noOfLizards;
	}

	public int getGridLength() {
		return gridLength;
	}

	public void setGridLength(int gridLength) {
		this.gridLength = gridLength;
	}

	public int[][] getInputMatrix() {
		return inputMatrix;
	}

	public void setInputMatrix(int[][] inputMatrix) {
		this.inputMatrix = inputMatrix;
	}
}
