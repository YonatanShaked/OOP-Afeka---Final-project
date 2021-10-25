package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import controller.CtrlFileHandler;
import model.MatrixElement.eElementColor;

public class Matrix {
	private Player player;
	private ArrayList<Player> leaderboard;
	private int level;
	private ArrayList<ArrayList<MatrixElement>> theMatrix;

	public Matrix(int level, Player player, ArrayList<Player> leaderboard) {
		this.player = player;
		this.leaderboard = leaderboard;
		this.level = level;
		theMatrix = new ArrayList<ArrayList<MatrixElement>>();
		Random rnd = new Random();
		
		switch (this.level) {
		case 1:
			for (int row = 0; row < 7; row++) {
				ArrayList<MatrixElement> rowlist = new ArrayList<MatrixElement>();
				for (int col = 0; col < 7; col++) {
					MatrixElement el = new MatrixElement(false, eElementColor.values()[rnd.nextInt(4)], row, col);
					rowlist.add(el);
				}
				theMatrix.add(rowlist);
			}
			break;

		case 2:
			for (int row = 0; row < 10; row++) {
				ArrayList<MatrixElement> rowlist = new ArrayList<MatrixElement>();
				for (int col = 0; col < 10; col++) {
					MatrixElement el = new MatrixElement(false, eElementColor.values()[rnd.nextInt(6)], row, col);
					rowlist.add(el);
				}
				theMatrix.add(rowlist);
			}
			break;

		case 3:
			for (int row = 0; row < 15; row++) {
				ArrayList<MatrixElement> rowlist = new ArrayList<MatrixElement>();
				for (int col = 0; col < 15; col++) {
					MatrixElement el = new MatrixElement(false, eElementColor.values()[rnd.nextInt(8)], row, col);
					rowlist.add(el);
				}
				theMatrix.add(rowlist);
			}
			break;
		}
	}

	public int getScore() {
		return player.getScore();
	}

	public int getLevel() {
		return level;
	}

	public ArrayList<ArrayList<MatrixElement>> getTheMatrix() {
		return theMatrix;
	}

	public boolean getElementStatus(int row, int col) {
		return theMatrix.get(row).get(col).getPressState();
	}

	public eElementColor getElementColor(int row, int col) {
		return theMatrix.get(row).get(col).getColor();
	}

	public void changeElementStatus(int row, int col) {
		theMatrix.get(row).get(col).setPressState(!theMatrix.get(row).get(col).getPressState());
		check4Selected();
	}

	private void check4Selected() {
		int slctd = 0;
		ArrayList<MatrixElement> pressedElements = new ArrayList<MatrixElement>();
		for (int i = 0; i < theMatrix.size(); i++) {
			for (int j = 0; j < theMatrix.get(i).size(); j++) {
				if (theMatrix.get(i).get(j).getPressState()) {
					pressedElements.add(theMatrix.get(i).get(j));
					slctd++;
				}
			}
		}

		if (slctd == 4) {
			if (isColor(pressedElements) && isRect(pressedElements)) {
				progressGame(pressedElements);
			}
			resetMatrix();
		}
	}

	private boolean isColor(ArrayList<MatrixElement> pressedElements) {
		if (pressedElements.get(0).getColor() == pressedElements.get(1).getColor()
				&& pressedElements.get(1).getColor() == pressedElements.get(2).getColor()
				&& pressedElements.get(2).getColor() == pressedElements.get(3).getColor()) {
			return true;
		}
		return false;
	}

	private boolean isRect(ArrayList<MatrixElement> pressedElements) {
		if (pressedElements.get(0).getX() == pressedElements.get(1).getX()
				&& pressedElements.get(2).getX() == pressedElements.get(3).getX()
				&& pressedElements.get(0).getY() == pressedElements.get(2).getY()
				&& pressedElements.get(1).getY() == pressedElements.get(3).getY()) {
			return true;
		}
		return false;
	}

	private void progressGame(ArrayList<MatrixElement> pressedElements) { // drisa
		Random rnd = new Random();
		for (int i = pressedElements.get(0).getY(); i <= pressedElements.get(1).getY(); i++) {
			for (int j = pressedElements.get(1).getX(); j <= pressedElements.get(2).getX(); j++) {
				switch (this.level) {
				case 1:
					theMatrix.get(j).get(i).setColor(eElementColor.values()[rnd.nextInt(4)]);
					player.setScore(player.getScore() + (10 * 3));
					break;

				case 2:
					theMatrix.get(j).get(i).setColor(eElementColor.values()[rnd.nextInt(6)]);
					player.setScore(player.getScore() + (10 * 2));
					break;

				case 3:
					theMatrix.get(j).get(i).setColor(eElementColor.values()[rnd.nextInt(8)]);
					player.setScore(player.getScore() + (10 * 1));
					break;
				}
			}
		}
		saveToFile();
	}

	private void saveToFile() {
		boolean canAdd = true;
		for (Player p : leaderboard) {
			if (p.getName().equals(player.getName())) {
				canAdd = false;
				if (player.getScore() > p.getScore())
					p.setScore(player.getScore());
			}
		}

		if (canAdd)
			leaderboard.add(player);

		Collections.sort(leaderboard, Collections.reverseOrder());

		if (leaderboard.size() > 10)
			leaderboard.subList(10, leaderboard.size()).clear();
		
		CtrlFileHandler.saveFile(leaderboard);
	}

	private void resetMatrix() {
		for (int i = 0; i < theMatrix.size(); i++) {
			for (int j = 0; j < theMatrix.get(i).size(); j++) {
				theMatrix.get(i).get(j).setPressState(false);
			}
		}
	}
}
