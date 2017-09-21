package com.vis.test;


import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import com.vis.constants.GridConstants;
import com.vis.models.InputData;
import com.vis.models.OutputData;
import com.vis.starter.Starter;

public class BFSImplTest {

	@Test
	public void testBFSFor4x4_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("BFS", 4, 4);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		int [][]expected= {{13, 13, 1, 13}, {1, 13, 13, 13},{ 13, 13, 13, 1},{ 13, 1, 13, 13}};
		Assert.assertArrayEquals(expected, outputData.getGrid());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testBFSFor3x3_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("BFS", 3, 2);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		int[][] expected = { { 1, 13, 13 }, { 13, 13, 13 }, { 13, 1, 13 } };
		Assert.assertArrayEquals(expected, outputData.getGrid());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testBFSFor2x2_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("BFS", 2, 1);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		int[][] expected = { { 1, 13 }, { 13, 13 } };
		Assert.assertArrayEquals(expected, outputData.getGrid());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testBFSFor5x5_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("BFS", 5, 5);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		int[][] expected = { { 1, 13, 13, 13, 13 }, { 13, 13, 13, 1, 13 }, { 13, 1, 13, 13, 13 }, { 13, 13, 13, 13, 1 },
				{ 13, 13, 1, 13, 13 } };
		Assert.assertArrayEquals(expected, outputData.getGrid());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testBFSFor6x6_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("BFS", 6, 6);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testBFSFor10x10_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("BFS", 10, 10);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	// failure testCases
	@Test
	public void testBFSFor3x3_shouldReturnFailure_whenNoSolution() {
		InputData inputData = new InputData("BFS", 3, 3);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(false, outputData.isSuccess());
	}

	// with trees
	@Test
	public void testBFSFor3x3WithTree_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("BFS", 3, 3);
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
	public void testBFSFor8x8WithTree_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("BFS", 8, 8);
		int[][] grid = new int[8][8];
		grid[3][4] = grid[5][5] = GridConstants.TREE.getNumber();
		inputData.setGrid(grid);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		int[][] expected = { { 1, 13,  13, 13, 13, 13, 13, 13 },
				{ 13, 13, 13, 13, 1,  13, 13, 13 },
				{ 13, 1,  13, 13, 13, 13, 13, 13 },
				{ 13, 13, 13, 13, 2,  1,  13, 13 },
				{ 13, 13, 1,  13, 13, 13, 13, 13 },
				{ 13, 13, 13, 13, 13, 2,   1, 13 },
				{ 13, 13, 13, 1,  13, 13, 13, 13 },
				{ 13, 13, 13, 13, 13, 13, 13, 1 } };
		Assert.assertArrayEquals(expected, outputData.getGrid());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testBFSFor8x9WithTree_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("BFS", 8, 9);
		int[][] grid = new int[8][8];
		grid[3][4] = grid[5][5] = GridConstants.TREE.getNumber();
		inputData.setGrid(grid);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		int[][] expected = { {1,  13, 13, 13, 13, 13, 13, 13},
				{13, 13, 13, 13, 1,  13, 13, 13},
				{13, 13, 13, 13, 13, 13, 1,  13},
				{13, 1,  13, 13, 2,  13, 13, 13},
				{13, 13, 13, 13, 13, 1,  13, 13},
				{13, 13, 1,  13, 13, 2,  13, 1},
				{13, 13, 13, 13, 13, 1,  13, 13},
				{13, 13, 13, 1,  13, 13, 13, 13} };
		Assert.assertArrayEquals(expected, outputData.getGrid());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}

	@Test
	public void testBFSFor3x4WithTree_shouldReturnSolution_whenSolutionExists() {
		InputData inputData = new InputData("BFS", 3, 4);
		int[][] grid = new int[3][3];
		grid[1][0] =grid[0][1] =grid[1][1] =grid[1][2]=grid[2][1] = GridConstants.TREE.getNumber();
		inputData.setGrid(grid);
		OutputData outputData = Starter.runAlgorithm(inputData);
		assertEquals(true, outputData.isSuccess());
		int[][] expected = { { 1, 2, 1 }, { 2, 2, 2 }, { 1, 2, 1 } };
		Assert.assertArrayEquals(expected, outputData.getGrid());
		assertEquals(true, TestUtil.isValidAssignmentOverall(outputData));
	}


}
