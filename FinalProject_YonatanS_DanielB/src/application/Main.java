/*
 * README
 * Yonatan Shaked 208406561
 * Daniel Berin 205738172
 * Final project for OOP class Afeka
*/
package application;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import view.View;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Model model = new Model();
		View view = new View(stage);
		Controller controller = new Controller(model, view);
		controller.start();
	}
}