package com.vis.test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import com.vis.constants.GridConstants;
import com.vis.models.InputData;
import com.vis.models.OutputData;
import com.vis.starter.Starter;

public class DFSImplTest {

	@Test
	public void testDFSFor4x4_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("DFS", 4, 4);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		int[][] expected = { { 13, 13, 1, 13 }, { 1, 13, 13, 13 }, { 13, 13, 13, 1 }, { 13, 1, 13, 13 } };
		Assert.assertArrayEquals(expected, outputData.getGrid());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testDFSFor3x3_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("DFS", 3, 2);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		int[][] expected = { { 1, 13, 13 }, { 13, 13, 13 }, { 13, 1, 13 } };
		Assert.assertArrayEquals(expected, outputData.getGrid());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testDFSFor2x2_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("DFS", 2, 1);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		int[][] expected = { { 1, 13 }, { 13, 13 } };
		Assert.assertArrayEquals(expected, outputData.getGrid());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testDFSFor5x5_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("DFS", 5, 5);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		int[][] expected = { { 1, 13, 13, 13, 13 }, { 13, 13, 13, 1, 13 }, { 13, 1, 13, 13, 13 }, { 13, 13, 13, 13, 1 },
				{ 13, 13, 1, 13, 13 } };
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
		Assert.assertArrayEquals(expected, outputData.getGrid());
	}

	@Test
	public void testDFSFor6x6_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("DFS", 6, 6);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testDFSFor22x22_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("DFS", 22, 22);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	// failure testCases
	@Test
	public void testDFSFor100x100_shouldReturnFailure_whenNoSolution() {
		InputData inputData = new InputData("DFS", 100, 10001);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(false, outputData.isSuccess());

	}

	// with trees
	@Test
	public void testDFSFor3x3WithTree_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("DFS", 3, 3);
		int[][] grid = new int[3][3];
		grid[1][0] = GridConstants.TREE.getNumber();
		inputData.setGrid(grid);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		int[][] expected = { { 1, 13, 13 }, { 2, 13, 1 }, { 1, 13, 13 } };
		Assert.assertArrayEquals(expected, outputData.getGrid());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testDFSFor8x8WithTree_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("DFS", 8, 8);
		int[][] grid = new int[8][8];
		grid[3][4] = grid[5][5] = GridConstants.TREE.getNumber();
		inputData.setGrid(grid);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		int[][] expected = { { 1, 13, 13, 13, 13, 13, 13, 13 }, { 13, 13, 13, 13, 1, 13, 13, 13 },
				{ 13, 1, 13, 13, 13, 13, 13, 13 }, { 13, 13, 13, 13, 2, 1, 13, 13 }, { 13, 13, 1, 13, 13, 13, 13, 13 },
				{ 13, 13, 13, 13, 13, 2, 1, 13 }, { 13, 13, 13, 1, 13, 13, 13, 13 },
				{ 13, 13, 13, 13, 13, 13, 13, 1 } };
		Assert.assertArrayEquals(expected, outputData.getGrid());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testDFSFor8x9WithTree_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("DFS", 8, 9);
		int[][] grid = new int[8][8];
		grid[3][4] = grid[5][5] = GridConstants.TREE.getNumber();
		inputData.setGrid(grid);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		int[][] expected = { { 1, 13, 13, 13, 13, 13, 13, 13 }, { 13, 13, 13, 13, 1, 13, 13, 13 },
				{ 13, 13, 13, 13, 13, 13, 1, 13 }, { 13, 1, 13, 13, 2, 13, 13, 13 }, { 13, 13, 13, 13, 13, 1, 13, 13 },
				{ 13, 13, 1, 13, 13, 2, 13, 1 }, { 13, 13, 13, 13, 13, 1, 13, 13 }, { 13, 13, 13, 1, 13, 13, 13, 13 } };
		Assert.assertArrayEquals(expected, outputData.getGrid());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testDFSFor3x4WithTree_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("DFS", 3, 4);
		int[][] grid = new int[3][3];
		grid[1][0] = grid[0][1] = grid[1][1] = grid[1][2] = grid[2][1] = GridConstants.TREE.getNumber();
		inputData.setGrid(grid);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		int[][] expected = { { 1, 2, 1 }, { 2, 2, 2 }, { 1, 2, 1 } };
		Assert.assertArrayEquals(expected, outputData.getGrid());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

}
