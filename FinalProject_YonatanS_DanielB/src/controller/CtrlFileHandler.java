package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import model.Player;

public class CtrlFileHandler {

	public static void saveFile(ArrayList<Player> leaderboard) {
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream("Leaderboard.txt"));
			
			for (Player p : leaderboard)
				pw.println(p.getName() + " : " + p.getScore());
			
			pw.close();
		} catch (IOException e) {
			System.out.println("Cant save");
			e.printStackTrace();
		}
	}

	public static ArrayList<Player> loadFile() {
		ArrayList<Player> leaderboard = new ArrayList<>();
		try {
			File myObj = new File("Leaderboard.txt");
			Scanner myReader = new Scanner(myObj);
			
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] arrOfStr = data.split(" : ", 2);
				Player p = new Player(arrOfStr[0]);
				p.setScore(Integer.parseInt(arrOfStr[1]));
				leaderboard.add(p);
			}
			
			myReader.close();
			return leaderboard;
		} catch (FileNotFoundException e) {
			return leaderboard;
		}
	}
}