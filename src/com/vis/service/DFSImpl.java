/**
 *
 */
package com.vis.service;

import java.util.List;
import java.util.Stack;

import com.vis.models.InputData;
import com.vis.models.OutputData;
import com.vis.util.AlgorithmUtil;

/**
 * @author vis
 *
 */
public class DFSImpl implements AlgorithmService {

	@Override
	public OutputData runAlgorithm(InputData inputData) {
		OutputData outputData = new OutputData();
		if (!AlgorithmUtil.isSolutionFeasible(inputData)) {
			return outputData;
		}
		Stack<InputData> stack = new Stack<>();
		stack.add(inputData);
		while (!stack.isEmpty()) {
			InputData transInputData = stack.pop();
			if (transInputData.getNoOfLizards() == 0) {
				outputData.setGrid(transInputData.getGrid());
				outputData.setSuccess(true);
				break;
			}
			// put children in the Q
			List<InputData> childrenInputDataNodes = AlgorithmUtil.placeLizard(transInputData);
			for (int i = childrenInputDataNodes.size() - 1; i > -1; i--) {
				stack.push(childrenInputDataNodes.get(i));
			}
		}

		return outputData;
	}

}
