/**
 *
 */
package com.vis.models;

import java.util.Arrays;

/**
 * @author vis
 *
 */
public class OutputData {

	private int grid[][];

	private boolean success;

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int outputMatrix[][]) {
		grid = outputMatrix;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OutputData [grid=").append(Arrays.toString(grid)).append(", success=").append(success)
		.append("]");
		return builder.toString();
	}


}
