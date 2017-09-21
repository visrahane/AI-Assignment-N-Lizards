/**
 *
 */
package com.vis.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.vis.models.InputData;
import com.vis.models.OutputData;
import com.vis.util.AlgorithmUtil;

/**
 * @author vis
 *
 */
public class BFSImpl implements AlgorithmService {

	@Override
	public OutputData runAlgorithm(InputData inputData) {
		OutputData outputData = new OutputData();
		if (!AlgorithmUtil.isSolutionFeasible(inputData)) {
			return outputData;
		}
		Queue<InputData> queue = new LinkedList<>();
		queue.add(inputData);
		while (!queue.isEmpty()) {
			InputData transInputData = queue.poll();
			if (transInputData.getNoOfLizards() == 0) {
				outputData.setGrid(transInputData.getGrid());
				outputData.setSuccess(true);
				break;
			}
			// put children in the Q
			List<InputData> childrenInputDataNodes = AlgorithmUtil.placeLizard(transInputData);
			for (InputData element : childrenInputDataNodes) {
				queue.add(element);
			}

		}

		return outputData;
	}


}
