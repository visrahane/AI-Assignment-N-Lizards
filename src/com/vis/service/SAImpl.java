/**
 *
 */
package com.vis.service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.vis.constants.GridConstants;
import com.vis.models.InputData;
import com.vis.models.OutputData;
import com.vis.util.AlgorithmUtil;

/**
 * @author vis
 *
 */
public class SAImpl implements AlgorithmService {

	private static final long MAX_TIME = 1000 * 290; // 4:50s
	private static final String SPLITTOR = ":";

	@Override
	public OutputData runAlgorithm(InputData inputData) {
		OutputData outputNode = new OutputData();
		if (!AlgorithmUtil.isSolutionFeasible(inputData)) {
			return outputNode;
		}
		InputData currentNode = makeInitialNode(inputData);
		if (currentNode.getConflicts() == 0) {
			setSuccessOutput(outputNode, currentNode);
			return outputNode;
		}
		InputData nextNode;
		float temperature;

		long startTime = System.currentTimeMillis();

		for (int t = 1; timeElasped(startTime); t++) {
			temperature = schedule(t);
			if (temperature == 0) {
				break;
			}
			nextNode = getRandomSuccessor(currentNode);
			// no of conflicts
			int deltaE = nextNode.getConflicts() - currentNode.getConflicts();
			if (nextNode.getConflicts() == 0) {
				setSuccessOutput(outputNode, nextNode);
				break;
			} else if (deltaE < 0) {
				currentNode = nextNode;
			} else {
				if (accept(Math.exp(deltaE / temperature))) {
					currentNode = nextNode;
				}
			}
		}
		return outputNode;
	}

	private void setSuccessOutput(OutputData outputNode, InputData currentNode) {
		outputNode.setSuccess(true);
		outputNode.setGrid(currentNode.getGrid());
	}

	private InputData getRandomSuccessor(InputData currentNode) {
		InputData nextNode = initNode(currentNode);
		Random random = new Random();
		Set<String> randomNos = new HashSet<>();
		for (int i, j;;) {
			i = random.nextInt(nextNode.getGrid().length);
			j = random.nextInt(nextNode.getGrid().length);
			if (randomNos.add(i + "" + j) && AlgorithmUtil.isSlotAvailable(nextNode.getGrid(), j, i)) {
				moveLizard(nextNode, i, j);
				break;
			}
		}
		countConflicts(nextNode);
		return nextNode;
	}

	private void moveLizard(InputData nextNode, int i, int j) {
		int[][] grid = nextNode.getGrid();
		for (int k = 0; k < grid.length; k++) {
			if (grid[i][k] == GridConstants.LIZARD.getNumber()) {
				grid[i][k] = GridConstants.BLANK.getNumber();
				grid[i][j] = GridConstants.LIZARD.getNumber();
				nextNode.getLocationOfLizards().remove(i + SPLITTOR + k);
				nextNode.putIntoLocationSet(i + SPLITTOR + j);
				break;
			}
		}
	}

	private float schedule(int t) {
		return (float) (1 / Math.log(t));
		// return -1 * (t - 15000000);
	}

	private boolean accept(double p) {
		Random random = new Random();
		int r = random.nextInt(1);
		if (p > r) {
			return true;
		}
		return false;
	}

	private boolean timeElasped(long startTime) {
		return System.currentTimeMillis() - startTime < MAX_TIME;
	}

	private InputData makeInitialNode(InputData inputData) {
		InputData currentNode = initNode(inputData);

		for (int i = 0; i < currentNode.getGrid().length; i++) {

			for (int j = 0; j < currentNode.getGrid().length; j++) {
				if (AlgorithmUtil.isSlotAvailable(currentNode.getGrid(), j, i)) {
					AlgorithmUtil.putLizardInEmptySlot(currentNode.getGrid(), j, i);
					currentNode.setNoOfLizards(currentNode.getNoOfLizards() - 1);
					currentNode.putIntoLocationSet(i + ":" + j);
					break;
				}
			}
			if (currentNode.getNoOfLizards() == 0) {
				break;
			}
		}
		if (currentNode.getNoOfLizards() != 0) {
			addRemainingLizards(currentNode);
		}
		countConflicts(currentNode);
		return currentNode;
	}

	private void addRemainingLizards(InputData currentNode) {
		int [][]grid=currentNode.getGrid();
		for (int i=0;i<grid.length;i++) {
			boolean placeLizard = false;
			for (int j = 0; j < grid.length; j++) {
				if (currentNode.getNoOfLizards() == 0) {
					return;
				}
				if(placeLizard && AlgorithmUtil.isSlotAvailable(grid, j, i)){
					grid[i][j] = GridConstants.LIZARD.getNumber();
					currentNode.setNoOfLizards(currentNode.getNoOfLizards() - 1);
					placeLizard = false;
				}
				if (GridConstants.TREE.getNumber() == grid[i][j]) {
					placeLizard=true;
				}
			}
		}
	}

	private void countConflicts(InputData currentNode) {
		int conflicts = 0;
		for (String element : currentNode.getLocationOfLizards()) {
			conflicts += countConflictForLizard(element, currentNode);
		}
		currentNode.setConflicts(conflicts);
	}

	private int countConflictForLizard(String element, InputData currentNode) {
		int conflicts = 0;
		String []words=element.split(SPLITTOR);
		int i=Integer.parseInt(words[0]);
		int j=Integer.parseInt(words[1]);
		int grid[][]=currentNode.getGrid();

		// go Down
		for (int k = i + 1; k < grid.length; k++) {
			if (grid[k][j] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[k][j] == GridConstants.LIZARD.getNumber() && (k != i)) {
				conflicts++;
			}
		}

		// go Up
		for (int k = i - 1; k > -1; k--) {
			if (grid[k][j] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[k][j] == GridConstants.LIZARD.getNumber() && (k != i)) {
				conflicts++;
			}
		}
		// go right
		for (int k = j; k < grid.length; k++) {
			if (grid[i][k] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[i][k] == GridConstants.LIZARD.getNumber() && k != j) {
				conflicts++;
			}
		}

		// go left
		for (int k = j - 1; k > -1; k--) {
			if (grid[i][k] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[i][k] == GridConstants.LIZARD.getNumber() && k != j) {
				conflicts++;
			}
		}

		// go diagonallyUpBackward
		for (int k = i - 1, l = j - 1; k > -1 && l > -1; k--, l--) {
			if (grid[k][l] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[k][l] == GridConstants.LIZARD.getNumber() && (k != i || l != j)) {
				conflicts++;
			}
		}

		// go diagonallyDownBackward
		for (int k = i + 1, l = j - 1; k < grid.length && l > -1; k++, l--) {
			if (grid[k][l] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[k][l] == GridConstants.LIZARD.getNumber() && (k != i || l != j)) {
				conflicts++;
			}
		}

		// go diagonallyUpForward
		for (int k = i - 1, l = j + 1; k > -1 && l < grid.length; k--, l++) {
			if (grid[k][l] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[k][l] == GridConstants.LIZARD.getNumber() && (k != i || l != j)) {
				conflicts++;
			}
		}

		// go diagonallyDownForward
		for (int k = i + 1, l = j + 1; k < grid.length && l < grid.length; k++, l++) {
			if (grid[k][l] == GridConstants.TREE.getNumber()) {
				break;
			}
			if (grid[k][l] == GridConstants.LIZARD.getNumber() && (k != i || l != j)) {
				conflicts++;
			}
		}

		return conflicts;
	}

	private InputData initNode(InputData inputData) {
		InputData node = new InputData();
		node.setConflicts(inputData.getConflicts());
		node.setLocationOfLizards(inputData.getLocationOfLizards());
		node.setNoOfLizards(inputData.getNoOfLizards());
		int[][] grid = new int[inputData.getGrid().length][inputData.getGrid().length];
		AlgorithmUtil.copyIntoTempGrid(grid, inputData.getGrid());
		node.setGrid(grid);
		return node;
	}

}
