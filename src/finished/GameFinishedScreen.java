// Project: Comp296 - Trivia Time Project
// Filename: GameFinishedScreen.java
//
// Creates the Trivia Time game-finished screen
package finished;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import leaderboard.LeaderboardScreen;

public class GameFinishedScreen extends BorderPane {
	
	private Stage stage = new Stage();
	
	private Button btnExit = new Button("Exit");
	private Button btnShowLeaderboard = new Button("Show Leaderboard");
	
	private Label lblGameFinished = new Label("Game Finished!");
	
	public GameFinishedScreen() {
		styleGameFinishedScreen();
		createGameFinishedListeners();
		show();
	}
	
	private void styleGameFinishedScreen() {
		
		// Game Finished Label
		
		lblGameFinished.setStyle("-fx-font: 36 arial");
		lblGameFinished.setAlignment(Pos.CENTER);
		lblGameFinished.setPadding(new Insets(0, 0, 0, 0));
		
		this.setCenter(lblGameFinished);
		
		// Exit Button
		
		btnExit.setAlignment(Pos.CENTER);
		btnExit.setPadding(new Insets(0, 0, 0, 0));
		
		this.setRight(btnExit);
		
		// Show Leaderboard Button
		
		btnShowLeaderboard.setAlignment(Pos.CENTER);
		btnShowLeaderboard.setPadding(new Insets(0, 0, 0, 0));
		
		this.setBottom(btnShowLeaderboard);		
		
	}
	
	private void createGameFinishedListeners() {
		
		btnExit.setOnAction(e -> {
			Platform.exit();
		});
		
		btnShowLeaderboard.setOnAction(e -> {
			LeaderboardScreen dbLbs = new LeaderboardScreen();
		});
		
	}
	
	public void show() {
		
		//The usual
		Scene scene = new Scene(this, 700, 500);
		stage.setTitle("Game Finished Screen");
		stage.setScene(scene);
		stage.show();
		
	}

}
