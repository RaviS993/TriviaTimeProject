// Project: Comp296 - Trivia Time Project
// Filename: TriviaTimeDriver.java
//
// Creates a driver class for the Trivia Time project
import game.GameTime;
import javafx.application.Application;
import javafx.stage.Stage;
public class TriviaTimeDriver extends Application {
	
	public void start(Stage myStage) {
		new GameTime();
	}

	public static void main(String[] args) {
		launch(args);
	}

}