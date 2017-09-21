/**
 *
 */
package com.vis.test;

import com.vis.constants.GridConstants;
import com.vis.models.OutputData;

/**
 * @author Vis
 *
 */
public class TestUtil {

	public static boolean isValidAssignmentOverall(OutputData outputData) {
		// boolean valid = false;
		int grid[][] = outputData.getGrid();
		if (!outputData.isSuccess()) {
			return false;
		} else {
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid.length; j++) {
					if (GridConstants.LIZARD.getNumber() == grid[i][j] && isAttacking(grid, i, j)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private static boolean isAttacking(int[][] grid, int i, int j) {
		// check all directions
		// go Down
		for (int k = i + 1; k < grid.length; k++) {
			if (grid[k][j] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[k][j] == GridConstants.LIZARD.getNumber() && (k != i)) {
				return true;
			}
		}

		// go Up
		for (int k = i - 1; k > -1; k--) {
			if (grid[k][j] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[k][j] == GridConstants.LIZARD.getNumber() && (k != i)) {
				return true;
			}
		}
		// go right
		for (int k = j; k < grid.length; k++) {
			if (grid[i][k] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[i][k] == GridConstants.LIZARD.getNumber() && k != j) {
				return true;
			}
		}

		// go left
		for (int k = j - 1; k > -1; k--) {
			if (grid[i][k] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[i][k] == GridConstants.LIZARD.getNumber() && k != j) {
				return true;
			}
		}

		// go diagonallyUpBackward
		for (int k = i - 1, l = j - 1; k > -1 && l > -1; k--, l--) {
			if (grid[k][l] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[k][l] == GridConstants.LIZARD.getNumber() && (k != i || l != j)) {
				return true;
			}
		}

		// go diagonallyDownBackward
		for (int k = i + 1, l = j - 1; k < grid.length && l > -1; k++, l--) {
			if (grid[k][l] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[k][l] == GridConstants.LIZARD.getNumber() && (k != i || l != j)) {
				return true;
			}
		}

		// go diagonallyUpForward
		for (int k = i - 1, l = j + 1; k > -1 && l < grid.length; k--, l++) {
			if (grid[k][l] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[k][l] == GridConstants.LIZARD.getNumber() && (k != i || l != j)) {
				return true;
			}
		}

		// go diagonallyDownForward
		for (int k = i + 1, l = j + 1; k < grid.length && l < grid.length; k++, l++) {
			if (grid[k][l] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[k][l] == GridConstants.LIZARD.getNumber() && (k != i || l != j)) {
				return true;
			}
		}
		return false;
	}
}
