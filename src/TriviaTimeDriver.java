// Project: Comp296 - Trivia Time Project
// Filename: TriviaTimeDriver.java
// Creates a driver class for the Trivia Time project
//
// import database.GameData;
import game.GameTime;
import javafx.application.Application;
import javafx.stage.Stage;
public class TriviaTimeDriver extends Application {
	
	public void start(Stage myStage) {
		new GameTime();
	}

	public static void main(String[] args) {
		
		/*
		GameData dbObj = new GameData();
		
		dbObj.getCategory();
		dbObj.getAnswers();
		dbObj.getLeaderboard();
		dbObj.close();
		*/
		
		launch(args);
	}

}