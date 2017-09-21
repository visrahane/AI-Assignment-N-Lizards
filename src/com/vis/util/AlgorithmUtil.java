/**
 *
 */
package com.vis.util;

import java.util.ArrayList;
import java.util.List;

import com.vis.constants.GridConstants;
import com.vis.models.InputData;

/**
 * @author Vis
 *
 */
public class AlgorithmUtil {

	public static List<InputData> placeLizard(InputData transInputData) {
		List<InputData> childrenInputDataList = new ArrayList<>();
		boolean lizardPlaced = false;
		for (int i = 0; i < transInputData.getGridLength(); i++)// place lizard on any available row
		{
			int[][] grid = new int[transInputData.getGridLength()][transInputData.getGridLength()];
			copyIntoTempGrid(grid, transInputData.getGrid());

			if (isSlotAvailable(grid, transInputData.getColFillIndex(), i)) {
				// put lizard and modify grid as per lizards reach and then add to list
				putLizardInEmptySlot(grid, transInputData.getColFillIndex(), i);
				boolean stayOnSameCol = placeDangerInGrid(grid, transInputData, i);
				lizardPlaced = true;
				childrenInputDataList.add(getChildNode(transInputData, grid, stayOnSameCol, lizardPlaced));

			}
		}
		if (!lizardPlaced) {
			InputData childNode = getChildNode(transInputData, transInputData.getGrid(), false, false);
			if (allColumnChecked(childNode)) {
				childrenInputDataList.add(childNode);
			}
		}
		return childrenInputDataList;
	}

	public static boolean allColumnChecked(InputData childNode) {
		return childNode.getColFillIndex() < childNode.getGridLength();
	}

	public static void putLizardInEmptySlot(int[][] grid, int col, int row) {
		grid[row][col] = GridConstants.LIZARD.getNumber();
	}

	public static InputData getChildNode(InputData transInputData, int[][] grid, boolean stayOnSameCol,
			boolean placeLizard) {
		InputData childNode = new InputData();
		if (stayOnSameCol) {
			childNode.setColFillIndex(transInputData.getColFillIndex());
		} else {
			childNode.setColFillIndex(transInputData.getColFillIndex() + 1);
		}
		childNode.setGridLength(transInputData.getGridLength());
		if (placeLizard) {
			childNode.setNoOfLizards(transInputData.getNoOfLizards() - 1);
		} else {
			childNode.setNoOfLizards(transInputData.getNoOfLizards());
		}
		childNode.setGrid(grid.clone());

		return childNode;
	}

	public static void copyIntoTempGrid(int[][] tempGrid, int[][] grid) {
		for (int i = 0; i < tempGrid.length; i++) {
			for (int j = 0; j < tempGrid.length; j++) {
				tempGrid[i][j] = grid[i][j];
			}
		}

	}

	public static boolean placeDangerInGrid(int[][] grid, InputData transInputData, int index) {
		boolean decrement = false;

		// column up
		for (int i = index - 1; i > -1; i--) {
			if (grid[i][transInputData.getColFillIndex()] == GridConstants.BLANK.getNumber()) {
				grid[i][transInputData.getColFillIndex()] = GridConstants.DANGER.getNumber();
			} else if (grid[i][transInputData.getColFillIndex()] == GridConstants.TREE.getNumber()) {
				// do not goto next col or u miss the blanks on the same col
				decrement = true;
				break;
			}
		}

		// column down
		for (int i = index + 1; i < grid.length; i++) {
			if (grid[i][transInputData.getColFillIndex()] == GridConstants.BLANK.getNumber()) {
				grid[i][transInputData.getColFillIndex()] = GridConstants.DANGER.getNumber();
			} else if (grid[i][transInputData.getColFillIndex()] == GridConstants.TREE.getNumber()) {
				// do not goto next col or u miss the blanks on the same col
				decrement = true;
				break;
			}
		}
		// half row
		for (int j = transInputData.getColFillIndex() + 1; j < grid.length; j++) {
			if (grid[index][j] == GridConstants.BLANK.getNumber()) {
				grid[index][j] = GridConstants.DANGER.getNumber();
			} else if (grid[index][j] == GridConstants.TREE.getNumber()) {
				break;
			}
		}

		// diagonally up
		for (int i = index - 1, j = transInputData.getColFillIndex() + 1; i > -1 && j < grid.length; i--, j++) {
			if (grid[i][j] == GridConstants.BLANK.getNumber()) {
				grid[i][j] = GridConstants.DANGER.getNumber();
			} else if (grid[i][j] == GridConstants.TREE.getNumber()) {
				break;
			}

		}
		// diagonally down
		for (int i = index + 1, j = transInputData.getColFillIndex() + 1; i < grid.length
				&& j < grid.length; i++, j++) {
			if (grid[i][j] == GridConstants.BLANK.getNumber()) {
				grid[i][j] = GridConstants.DANGER.getNumber();
			} else if (grid[i][j] == GridConstants.TREE.getNumber()) {
				break;
			}
		}

		return decrement;

	}

	public static boolean isSlotAvailable(int[][] grid, int colFillIndex, int i) {
		if (colFillIndex < grid.length && grid[i][colFillIndex] == GridConstants.BLANK.getNumber()) {
			return true;
		}
		return false;
	}

	public static boolean isSolutionFeasible(InputData inputData) {
		int emptyPlaces = 0;
		int grid[][] = inputData.getGrid();
		for (int[] element : grid) {
			for (int j = 0; j < grid.length; j++) {
				if(element[j]==GridConstants.BLANK.getNumber()) {
					emptyPlaces++;
				}

			}
		}
		return emptyPlaces >= inputData.getNoOfLizards();
	}

}
