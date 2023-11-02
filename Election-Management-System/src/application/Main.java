package application;

import application.mvc.controller.ElectionController;
import application.mvc.model.ElectionRound;
import application.mvc.model.ModelInterface;
import application.mvc.view.ViewModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) {
		ViewModel theModel = new ViewModel(primaryStage);
		ModelInterface modelInterface = new ElectionRound();
		ElectionController controller = new ElectionController(modelInterface, theModel);
	}
}
