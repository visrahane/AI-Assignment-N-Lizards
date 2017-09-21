/**
 *
 */
package com.vis.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.vis.constants.GridConstants;
import com.vis.models.InputData;
import com.vis.models.OutputData;
import com.vis.starter.Starter;

/**
 * @author Vis
 *
 */
public class SAImplTest {

	@Test
	public void testSAFor4x4_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("SA", 4, 4);
		OutputData outputData = Starter.runAlgorithm(inputData);
		System.out.println(Arrays.deepToString(outputData.getGrid()));
		assertEquals(true, outputData.isSuccess());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testSAFor3x3_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("SA", 3, 2);
		OutputData outputData = Starter.runAlgorithm(inputData);
		System.out.println(Arrays.deepToString(outputData.getGrid()));
		assertEquals(true, outputData.isSuccess());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testSAFor2x2_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("SA", 2, 1);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
		System.out.println(Arrays.deepToString(outputData.getGrid()));
	}

	@Test
	public void testSAFor5x5_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("SA", 5, 5);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
		System.out.println(Arrays.deepToString(outputData.getGrid()));
	}

	@Test
	public void testSAFor6x6_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("SA", 6, 6);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
		System.out.println(Arrays.deepToString(outputData.getGrid()));
	}

	@Test
	public void testSAFor10x10_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("SA", 11, 11);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
		System.out.println(Arrays.deepToString(outputData.getGrid()));
	}

	// failure testCases
	@Test
	public void testSAFor3x3_shouldReturnFailure_whenNoSolution() {
		InputData inputData = new InputData("SA", 3, 3);
		int grid[][] = inputData.getGrid();
		for (int i = 0; i < inputData.getGrid().length; i++) {
			for (int j = 0; j < inputData.getGrid().length; j++) {
				grid[i][j] = GridConstants.TREE.getNumber();
			}
		}
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(false, outputData.isSuccess());
	}

	// with trees
	@Test
	public void testSAFor3x3WithTree_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("SA", 3, 3);
		int[][] grid = new int[3][3];
		grid[1][0] = GridConstants.TREE.getNumber();
		inputData.setGrid(grid);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
		System.out.println(Arrays.deepToString(outputData.getGrid()));
	}

	@Test
	public void testSAFor8x8WithTree_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("SA", 8, 8);
		int[][] grid = new int[8][8];
		grid[3][4] = grid[5][5] = GridConstants.TREE.getNumber();
		inputData.setGrid(grid);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
		System.out.println(Arrays.deepToString(outputData.getGrid()));
	}

	@Test
	public void testSAFor8x9WithTree_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("SA", 8, 9);
		int[][] grid = new int[8][8];
		grid[3][4] = grid[5][5] = GridConstants.TREE.getNumber();
		inputData.setGrid(grid);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
		System.out.println(Arrays.deepToString(outputData.getGrid()));
	}

	@Test
	public void testSAFor3x4WithTree_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("SA", 3, 4);
		int[][] grid = new int[3][3];
		grid[1][0] = grid[0][1] = grid[1][1] = grid[1][2] = grid[2][1] = GridConstants.TREE.getNumber();
		inputData.setGrid(grid);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
		System.out.println(Arrays.deepToString(outputData.getGrid()));
	}

}
