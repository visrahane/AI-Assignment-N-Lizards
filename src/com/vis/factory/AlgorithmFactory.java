/**
 * 
 */
package com.vis.factory;



import com.vis.constants.AlgorithmConstants;
import com.vis.exception.InvalidInputException;
import com.vis.service.AlgorithmService;
import com.vis.service.BFSImpl;
import com.vis.service.DFSImpl;
import com.vis.service.SAImpl;

/**
 * @author vis
 *
 */
public class AlgorithmFactory {

	public static AlgorithmService getAlgorithm(String algoName) {
		AlgorithmService algorithmService = null;
		switch(algoName) {
		case AlgorithmConstants.BFS:
			algorithmService = new BFSImpl();
			break;
		case AlgorithmConstants.DFS:
			algorithmService = new DFSImpl();
			break;
		case AlgorithmConstants.SA:
			algorithmService = new SAImpl();
			break;
		default:
			try {
				throw new InvalidInputException();
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
		}

		return algorithmService;
	}

}
