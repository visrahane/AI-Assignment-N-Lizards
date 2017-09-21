/**
 * 
 */
package com.vis.constants;

/**
 * @author vis
 *
 */
public enum GridConstants {
	LIZARD(1), BLANK(0), TREE(2), DANGER(13);

	private int number;

	private GridConstants(int number) {
		this.setNumber(number);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
