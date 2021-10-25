package view;

import javafx.stage.Stage;
import model.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

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
	private Button leaderboardButton, newGameButton;
	private ToggleGroup tg;
	private Pane drawPane;
	private MatrixView theMatrixView;
	private VBox vbLeft;
	private Text scoreText, nameText;

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

		newGameButton = new Button("New Game");
		newGameButton.setPadding(new Insets(5, 20, 5, 20));
		newGameButton.setTranslateX(10);

		vbLeft = new VBox();
		vbLeft.getChildren().addAll(newGameButton, rbLvl1, rbLvl2, rbLvl3, leaderboardButton);
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
		HBox topRightBox = new HBox();
		topRightBox.setPadding(new Insets(0, 45, 0, 0));
		topRightBox.setSpacing(30);
		topRightBox.getChildren().addAll(nameText, scoreText);
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
		primaryStage.setTitle("Final Project : Yonatan Shaked & Daniel Berin");
		primaryStage.getIcons().add(new Image(getClass().getResource("icon.png").toExternalForm()));
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}

	public void setNameText(String s) {
		nameText.setText(s);
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
		TextInputDialog tid = new TextInputDialog("Player Name");
		tid.getDialogPane().setMinSize(400, 250);
		tid.setTitle("New game dialog");
		tid.setHeaderText("Please enter your name:");
		tid.showAndWait();
		Player p = new Player(tid.getEditor().getText());
		return p;
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

	public void showLeaderBoard(ArrayList<Player> leaderboard) {
		BorderPane bpLb = new BorderPane();
		bpLb.setBackground(new Background(new BackgroundFill(Color.rgb(70, 70, 70), new CornerRadii(0), Insets.EMPTY)));

		Text title = new Text("LEADERBOARD");
		title.setFont(Font.font("ariel", 24));
		title.setFill(Color.WHITE);
		title.setUnderline(true);
		HBox topBox = new HBox();
		topBox.setPadding(new Insets(15, 0, 15, 0));
		topBox.getChildren().add(title);
		topBox.setAlignment(Pos.CENTER);

		VBox left = new VBox();
		Text playerT = new Text(" Name   :   Score ");
		playerT.setFont(Font.font("ariel", 20));
		playerT.setFill(Color.WHITE);
		playerT.setUnderline(true);
		left.getChildren().add(playerT);
		int indx = 1;
		for (Player p : leaderboard) {
			Text player = new Text(indx + ":  " + p.getName() + "   |   " + p.getScore());
			indx++;
			player.setFont(Font.font("ariel", 18));
			player.setFill(Color.WHITE);
			left.getChildren().add(player);
		}
		left.setAlignment(Pos.CENTER_LEFT);

		bpLb.setTop(topBox);
		bpLb.setLeft(left);
		left.setTranslateX(150);
		Stage stage = new Stage();
		stage.setTitle("Leaderboard");
		stage.setResizable(false);
		stage.setScene(new Scene(bpLb, 450, 450));
		stage.show();
	}

	public void addLeaderboardListener(EventHandler<MouseEvent> click) {
		leaderboardButton.addEventHandler(MouseEvent.MOUSE_CLICKED, click);
	}

	public void addNewGameListener(EventHandler<MouseEvent> click) {
		newGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, click);
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