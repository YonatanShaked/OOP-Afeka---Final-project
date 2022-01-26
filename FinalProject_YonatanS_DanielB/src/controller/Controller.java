package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Toggle;
import javafx.scene.input.MouseEvent;
import model.Model;
import model.Player;
import view.View;

public class Controller {

	private View view;
	private Model model;

	public Controller(Model m, View v) {
		model = m;
		view = v;
	}

	public void start() {
		//MySqlController.test();
		EventHandler<MouseEvent> clickOnMatrix = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				int elementRow = view.getElementRow(event.getSource());
				int elementCol = view.getElementCol(event.getSource());
				model.changeElementStatus(elementRow, elementCol);
				view.updateMatrixParameter();
			}
		};

		EventHandler<MouseEvent> clickOnLeaderboard = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				view.showLeaderBoard(model.getLeaderboard(), model.getLeaderboardT(), model.getMvp());
			}
		};

		EventHandler<MouseEvent> clickOnNewGame = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				initNewGame(clickOnMatrix);
			}
		};
		
		EventHandler<MouseEvent> clickOnSwitchTeam = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				model.getPlayer().setTeam(view.setTeamInputDialog());
				MySqlController.setPlayerToTeam(model.getPlayer(), model.getPlayer().getTeam());
				model.setLeaderBoard(controller.MySqlController.getTopPlayers());
				model.setLeaderBoardT(controller.MySqlController.getTopTeams());
				model.setMvp(controller.MySqlController.findMvp());
				view.setTeamNameText(model.getPlayer().getTeam().getName());
			}
		};

		ChangeListener<Toggle> toggleChangeListener = new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
				String kindOfLevel = view.getKindOfLevel();
				model.update(kindOfLevel);
				view.update(model.getMatrix());
				view.addClickEventToMatrix(clickOnMatrix);
				String s = model.getPlayer().getName();
				model.setPlayer(new Player(s));
				view.setScoreText(model.getPlayer().getScore());
			}
		};

		view.update(model.getMatrix());
		view.addClickEventToMatrix(clickOnMatrix);
		view.addLeaderboardListener(clickOnLeaderboard);
		view.addNewGameListener(clickOnNewGame);
		view.addSwitchTeamListener(clickOnSwitchTeam);
		view.setNameText(model.getPlayer().getName());
		view.addToggleChangeListener(toggleChangeListener);
		initNewGame(clickOnMatrix);
	}

	private void initNewGame(EventHandler<MouseEvent> clickOnMatrix) {
		Player p = view.newGameInputDialog();
		model.setPlayer(p);
		view.setTeamNameText(p.getTeam().getName());
		view.setNameText(p.getFname() + " " + p.getLname() + " " + p.getMname());
		view.setScoreText(p.getScore());
		String kindOfLevel = view.getKindOfLevel();
		model.setLeaderBoard(controller.MySqlController.getTopPlayers());
		model.setLeaderBoardT(controller.MySqlController.getTopTeams());
		model.setMvp(controller.MySqlController.findMvp());
		model.update(kindOfLevel);
		view.update(model.getMatrix());
		view.addClickEventToMatrix(clickOnMatrix);
	}
}