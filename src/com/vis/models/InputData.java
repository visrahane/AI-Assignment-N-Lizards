/**
 *
 */
package com.vis.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vis
 *
 */
public class InputData {

	private String algoName;

	private int noOfLizards;

	private int gridLength;

	private int[][] grid;

	private int colFillIndex;

	private Set<String> locationOfLizards;

	private int conflicts;

	public InputData() {
		locationOfLizards = new HashSet<>();
	}

	public InputData(String algoName, int gridLength, int noOfLizards) {
		grid = new int[gridLength][gridLength];
		locationOfLizards = new HashSet<>();
		this.setAlgoName(algoName);
		this.setGridLength(gridLength);
		this.setNoOfLizards(noOfLizards);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InputData [algoName=").append(algoName).append(", noOfLizards=").append(noOfLizards)
		.append(", gridLength=").append(gridLength).append(", grid=").append(Arrays.toString(grid))
		.append(", colFillIndex=").append(colFillIndex).append(", locationOfLizards=").append(locationOfLizards)
		.append(", conflicts=").append(conflicts).append("]");
		return builder.toString();
	}

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

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] inputMatrix) {
		grid = inputMatrix;
	}

	public int getColFillIndex() {
		return colFillIndex;
	}

	public void setColFillIndex(int colFillIndex) {
		this.colFillIndex = colFillIndex;
	}

	public Set<String> getLocationOfLizards() {
		return locationOfLizards;
	}

	public void setLocationOfLizards(Set<String> locationOfLizards) {
		this.locationOfLizards = locationOfLizards;
	}

	public boolean putIntoLocationSet(String location) {
		return locationOfLizards.add(location);
	}

	public int getConflicts() {
		return conflicts;
	}

	public void setConflicts(int conflicts) {
		this.conflicts = conflicts;
	}

}
