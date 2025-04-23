// Project: Comp296 - Trivia Time Project
// Filename: TriviaQuestions.java
// Creates the Trivia Time leaderboard screen
package leaderboard;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LeaderboardScreen extends BorderPane {
	
	private Stage stage = new Stage();
	
	private VBox nameBox = new VBox(20);
	private VBox categoryIDBox = new VBox(20);
	private VBox answeredCorrectlyBox = new VBox(20);

	public LeaderboardScreen() {
		createLeaderboard();
		show();
	}
	
	private void createLeaderboard() {
		
	}
	
	public void show() {
		
		//The usual
		Scene scene = new Scene(this, 700, 350);
		stage.setTitle("title");
		stage.setScene(scene);
		stage.show();
		
	}
	
}