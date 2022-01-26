package view;

import javafx.stage.Stage;
import model.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import controller.MySqlController;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;

public class View {
	private Stage primaryStage;
	private Scene mainScene;
	private BorderPane bp;
	private RadioButton rbLvl1;
	private RadioButton rbLvl2;
	private RadioButton rbLvl3;
	private Button leaderboardButton, newGameButton, switchTeamButton;
	private ToggleGroup tg;
	private Pane drawPane;
	private MatrixView theMatrixView;
	private VBox vbLeft;
	private Text scoreText, nameText, teamNameText;

	public View(Stage stage) {
		primaryStage = stage;
		tg = new ToggleGroup();

		rbLvl1 = new RadioButton("Level 1");
		rbLvl1.setSelected(true);
		rbLvl1.setToggleGroup(tg);
		rbLvl1.setPadding(new Insets(5, 20, 5, 20));
		rbLvl1.setTextFill(Color.WHITE);

		rbLvl2 = new RadioButton("Level 2");
		rbLvl2.setToggleGroup(tg);
		rbLvl2.setPadding(new Insets(5, 20, 5, 20));
		rbLvl2.setTextFill(Color.WHITE);

		rbLvl3 = new RadioButton("Level 3");
		rbLvl3.setToggleGroup(tg);
		rbLvl3.setPadding(new Insets(5, 20, 5, 20));
		rbLvl3.setTextFill(Color.WHITE);

		leaderboardButton = new Button("LeaderBoard");
		leaderboardButton.setPadding(new Insets(5, 20, 5, 20));
		leaderboardButton.setTranslateX(10);

		newGameButton = new Button("Switch Player");
		newGameButton.setPadding(new Insets(5, 20, 5, 20));
		newGameButton.setTranslateY(-10);
		newGameButton.setTranslateX(10);

		switchTeamButton = new Button("Switch Team");
		switchTeamButton.setPadding(new Insets(5, 20, 5, 20));
		switchTeamButton.setTranslateX(10);

		vbLeft = new VBox();
		vbLeft.getChildren().addAll(newGameButton, switchTeamButton, rbLvl1, rbLvl2, rbLvl3,
				leaderboardButton);
		vbLeft.setAlignment(Pos.CENTER_LEFT);

		Text title = new Text("COLORS GAME");
		title.setUnderline(true);
		title.setFont(Font.font("ariel", 24));
		title.setFill(Color.WHITE);
		HBox topBox = new HBox();
		topBox.setPadding(new Insets(15, 0, 15, 0));
		topBox.getChildren().addAll(title);
		topBox.setAlignment(Pos.CENTER);

		scoreText = new Text("Score: 0");
		scoreText.setFont(Font.font("ariel", 16));
		scoreText.setFill(Color.WHITE);
		nameText = new Text("");
		nameText.setFont(Font.font("ariel", 16));
		nameText.setFill(Color.WHITE);
		teamNameText = new Text("");
		teamNameText.setFont(Font.font("ariel", 16));
		teamNameText.setFill(Color.WHITE);
		HBox topRightBox = new HBox();
		topRightBox.setPadding(new Insets(0, 45, 0, 0));
		topRightBox.setSpacing(30);
		topRightBox.getChildren().addAll(teamNameText ,nameText, scoreText);
		topRightBox.setAlignment(Pos.TOP_RIGHT);

		drawPane = new Pane();
		bp = new BorderPane();
		bp.setLeft(vbLeft);
		bp.setTop(topBox);
		bp.setRight(topRightBox);
		Group appContent = new Group();
		appContent.getChildren().add(drawPane);
		appContent.setTranslateX(30);
		bp.setCenter(appContent);
		bp.setBackground(new Background(new BackgroundFill(Color.rgb(70, 70, 70), new CornerRadii(0), Insets.EMPTY)));

		mainScene = new Scene(bp, 950, 700);
		primaryStage.setTitle("Final Project DataBases : Yonatan & Guy");
		primaryStage.getIcons().add(new Image(getClass().getResource("icon.png").toExternalForm()));
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}

	public void setNameText(String s) {
		nameText.setText(s);
	}
	
	public void setTeamNameText(String s) {
		teamNameText.setText(s);
	}

	public void setScoreText(int x) {
		scoreText.setText("Score: " + x);
	}

	public String getKindOfLevel() {
		if (rbLvl1.isSelected())
			return rbLvl1.getText();

		else if (rbLvl2.isSelected())
			return rbLvl2.getText();

		else
			return rbLvl3.getText();
	}

	public Player newGameInputDialog() {
		Player p;
		TextInputDialog inputDialog = new TextInputDialog("Player Name");
		inputDialog.getDialogPane().setMinSize(400, 250);
		inputDialog.setTitle("Set player dialog");
		inputDialog.setHeaderText("Please enter your name (new or existing):");
		inputDialog.showAndWait();
		p = MySqlController.findPlayer(inputDialog.getEditor().getText());

		if (p == null) {
			String fname = "", lname = "", mname = "";
			String[] splited = inputDialog.getEditor().getText().split("\\s+");
			for(int i = 0; i < splited.length; i++) {
				if (i == 0)
					fname = splited[i];
				if (i == 1)
					lname = splited[i];
				if (i == 2)
					mname = splited[i];
			}
			int pid = MySqlController.getHighestPid() + 1;
			Team t = MySqlController.findTeam(1); // no team
			p = new Player(pid, fname, lname, mname, 0, t);
			MySqlController.addPlayer(p);
		}
		return p;
	}

	public Team setTeamInputDialog() { // goes to player
		Team t;
		TextInputDialog inputDialog = new TextInputDialog("Team Name");
		inputDialog.getDialogPane().setMinSize(400, 250);
		inputDialog.setTitle("Set team dialog");
		inputDialog.setHeaderText("Please enter your team name (new or existing):");
		inputDialog.showAndWait();
		t = MySqlController.findTeam(inputDialog.getEditor().getText());
		
		if (t == null) {
			int tid = MySqlController.getHighestTid() + 1;
			t = new Team(tid, inputDialog.getEditor().getText(), 0);
			MySqlController.addTeam(t);
		}
		
		return t;
	}

	public void update(Matrix theMatrix) {
		if (theMatrix.getLevel() == 1)
			theMatrixView = new MatrixView(theMatrix, 1);

		else if (theMatrix.getLevel() == 2)
			theMatrixView = new MatrixView(theMatrix, 2);

		else
			theMatrixView = new MatrixView(theMatrix, 3);

		theMatrixView.show(drawPane);
	}

	public void showLeaderBoard(ArrayList<Player> leaderboard, ArrayList<Team> leaderboardT, Player mvp) {
		Calendar cal = Calendar.getInstance();
		BorderPane bpLb = new BorderPane();
		bpLb.setBackground(new Background(new BackgroundFill(Color.rgb(70, 70, 70), new CornerRadii(0), Insets.EMPTY)));

		Text title = new Text("LEADERBOARD\n" + new SimpleDateFormat("MMMMMM").format(cal.getTime()) + "'s League");
		title.setFont(Font.font("ariel", 24));
		title.setFill(Color.WHITE);
		title.setUnderline(true);
		title.setTextAlignment(TextAlignment.CENTER);
		HBox topBox = new HBox();
		topBox.setPadding(new Insets(15, 0, 15, 0));
		topBox.getChildren().addAll(title);
		topBox.setAlignment(Pos.CENTER);

		VBox left = new VBox();
		Text playerH = new Text("SINGLE LEAGUE\n\n");
		playerH.setFont(Font.font("ariel", 24));
		playerH.setFill(Color.WHITE);
		playerH.setUnderline(true);
		playerH.setTextAlignment(TextAlignment.CENTER);
		left.getChildren().add(playerH);
		Text playerT = new Text(" Name   :   Score");
		playerT.setFont(Font.font("ariel", 20));
		playerT.setFill(Color.WHITE);
		playerT.setUnderline(true);
		playerT.setTextAlignment(TextAlignment.CENTER);
		left.getChildren().add(playerT);
		int indx = 1;
		for (Player p : leaderboard) {
			String name = p.getFname() + " " + p.getLname() + " " + p.getMname();
			Text player = new Text(indx + ":  " + name + "   |   " + p.getScore());
			indx++;
			player.setFont(Font.font("ariel", 18));
			player.setFill(Color.WHITE);
			player.setTextAlignment(TextAlignment.CENTER);
			left.getChildren().add(player);
		}
		left.setAlignment(Pos.CENTER_LEFT);

		VBox right = new VBox();
		Text teamH = new Text("TEAM LEAGUE\n");
		teamH.setFont(Font.font("ariel", 24));
		teamH.setFill(Color.WHITE);
		teamH.setUnderline(true);
		teamH.setTextAlignment(TextAlignment.CENTER);
		right.getChildren().add(teamH);
		String mvpName = mvp.getFname() + " " + mvp.getLname() + " " + mvp.getMname();
		Text teamMvp = new Text("MVP : " + mvpName + "\n");
		teamMvp.setFont(Font.font("ariel", 16));
		teamMvp.setFill(Color.WHITE);
		teamMvp.setTextAlignment(TextAlignment.CENTER);
		right.getChildren().add(teamMvp);
		Text teamT = new Text(" Name   :   Score ");
		teamT.setFont(Font.font("ariel", 20));
		teamT.setFill(Color.WHITE);
		teamT.setUnderline(true);
		teamT.setTextAlignment(TextAlignment.CENTER);
		right.getChildren().add(teamT);
		indx = 1;
		for (Team t : leaderboardT) { // temp
			Text team = new Text(indx + ":  " + t.getName() + "   |   " + t.getScore());
			indx++;
			team.setFont(Font.font("ariel", 18));
			team.setFill(Color.WHITE);
			team.setTextAlignment(TextAlignment.CENTER);
			right.getChildren().add(team);
		}
		right.setAlignment(Pos.CENTER_RIGHT);

		bpLb.setTop(topBox);
		bpLb.setLeft(left);
		bpLb.setRight(right);
		left.setTranslateX(50);
		right.setTranslateX(-50);
		Stage stage = new Stage();
		stage.setTitle("Leaderboard " + new SimpleDateFormat("MMMMMM").format(cal.getTime()));
		stage.setResizable(false);
		stage.setScene(new Scene(bpLb, 800, 550));
		stage.show();
	}

	public void addLeaderboardListener(EventHandler<MouseEvent> click) {
		leaderboardButton.addEventHandler(MouseEvent.MOUSE_CLICKED, click);
	}

	public void addNewGameListener(EventHandler<MouseEvent> click) {
		newGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, click);
	}

	public void addSwitchTeamListener(EventHandler<MouseEvent> click) {
		switchTeamButton.addEventHandler(MouseEvent.MOUSE_CLICKED, click);
	}

	public void addToggleChangeListener(ChangeListener<Toggle> cl) {
		tg.selectedToggleProperty().addListener(cl);
	}

	public int getElementCol(Object src) {
		return theMatrixView.getElementCol(src);
	}

	public int getElementRow(Object src) {
		return theMatrixView.getElementRow(src);
	}

	public void updateMatrixParameter() {
		theMatrixView.updateMatrixParameters(scoreText);
	}

	public void addClickEventToMatrix(EventHandler<MouseEvent> click) {
		theMatrixView.addClickEventToMatrix(click);
	}
}