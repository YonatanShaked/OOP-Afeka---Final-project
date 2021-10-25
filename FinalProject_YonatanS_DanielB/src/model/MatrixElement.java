package model;

public class MatrixElement {

	public enum eElementColor {
		RED, BLUE, LIMEGREEN, YELLOW, BROWN, FUCHSIA, DARKSLATEBLUE, ORANGE
	};

	private boolean pressState;
	private eElementColor elementColor;
	private int x, y;

	public MatrixElement(boolean press, eElementColor color, int x, int y) {
		this.pressState = press;
		this.elementColor = color;
		this.y = y;
		this.x = x;
	}

	public boolean getPressState() {
		return pressState;
	}

	public void setPressState(boolean b) {
		this.pressState = b;
	}

	public eElementColor getColor() {
		return this.elementColor;
	}

	public void setColor(eElementColor color) {
		this.elementColor = color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
