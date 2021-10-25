package view;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Matrix;

public class MatrixView {
	private Matrix theMatrix;
	private int level;
	private ArrayList<ArrayList<Rectangle>> rctMatrix;
	private final int rctSize = 35;

	public MatrixView(Matrix m, int level) {
		this.theMatrix = m;
		this.level = level;
		rctMatrix = new ArrayList<ArrayList<Rectangle>>();
	}

	public void show(Pane drawPane) {
		drawPane.getChildren().clear();

		switch (level) {
		case 1:
			for (int row = 0; row < 7; row++) {
				ArrayList<Rectangle> rowlist = new ArrayList<Rectangle>();
				for (int col = 0; col < 7; col++) {
					Rectangle rct = new Rectangle(row * (rctSize + 2), col * (rctSize + 2), rctSize, rctSize);
					rct.setFill(Color.valueOf(theMatrix.getTheMatrix().get(row).get(col).getColor().toString()));
					rct.setStroke(Color.BLACK);
					rct.setStrokeWidth(3);
					rowlist.add(rct);
					drawPane.getChildren().add(rct);
				}
				rctMatrix.add(rowlist);
			}
			break;

		case 2:
			for (int row = 0; row < 10; row++) {
				ArrayList<Rectangle> rowlist = new ArrayList<Rectangle>();
				for (int col = 0; col < 10; col++) {
					Rectangle rct = new Rectangle(row * (rctSize + 2), col * (rctSize + 2), rctSize, rctSize);
					rct.setFill(Color.valueOf(theMatrix.getTheMatrix().get(row).get(col).getColor().toString()));
					rct.setStroke(Color.BLACK);
					rct.setStrokeWidth(3);
					rowlist.add(rct);
					drawPane.getChildren().add(rct);
				}
				rctMatrix.add(rowlist);
			}
			break;

		case 3:
			for (int row = 0; row < 15; row++) {
				ArrayList<Rectangle> rowlist = new ArrayList<Rectangle>();
				for (int col = 0; col < 15; col++) {
					Rectangle rct = new Rectangle(row * (rctSize + 2), col * (rctSize + 2), rctSize, rctSize);
					rct.setFill(Color.valueOf(theMatrix.getTheMatrix().get(row).get(col).getColor().toString()));
					rct.setStroke(Color.BLACK);
					rct.setStrokeWidth(3);
					rowlist.add(rct);
					drawPane.getChildren().add(rct);
				}
				rctMatrix.add(rowlist);
			}
			break;
		}
	}

	public int getElementCol(Object src) { // finds the col of the rct
		for (int i = 0; i < rctMatrix.size(); i++) {
			if (rctMatrix.get(i).contains(src))
				return rctMatrix.get(i).indexOf(src);
		}
		return 0;
	}

	public int getElementRow(Object src) { // finds the row of the rct
		for (int i = 0; i < rctMatrix.size(); i++) {
			if (rctMatrix.get(i).contains(src))
				return i;
		}
		return 0;
	}

	public void updateMatrixParameters(Text scoreText) {
		for (int i = 0; i < theMatrix.getTheMatrix().size(); i++) {
			for (int j = 0; j < theMatrix.getTheMatrix().get(i).size(); j++) {
				rctMatrix.get(i).get(j).setFill(Color.valueOf(theMatrix.getElementColor(i, j).toString()));
				if (theMatrix.getElementStatus(i, j))
					rctMatrix.get(i).get(j).setStroke(Color.WHITE);
				else
					rctMatrix.get(i).get(j).setStroke(Color.BLACK);
			}
		}
		scoreText.setText("Score: " + theMatrix.getScore());
	}

	public void addClickEventToMatrix(EventHandler<MouseEvent> click) {
		for (int i = 0; i < theMatrix.getTheMatrix().size(); i++)
			for (int j = 0; j < theMatrix.getTheMatrix().get(i).size(); j++)
				rctMatrix.get(i).get(j).addEventHandler(MouseEvent.MOUSE_CLICKED, click);
	}
}