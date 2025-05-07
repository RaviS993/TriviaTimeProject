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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import leaderboard.LeaderboardScreen;

public class GameFinishedScreen extends BorderPane {
	
	private Stage stage = new Stage();
	
	private Button btnExit = new Button("Exit");
	private Button btnShowLeaderboard = new Button("Show Leaderboard");
	
	private Label lblGameFinished = new Label("Game Finished!");
	
	private HBox exitButtonBox = new HBox(20);
	private HBox showLeaderboardButtonBox = new HBox(20);
	private HBox gameFinishedBox = new HBox(20);
	
	public GameFinishedScreen() {
		styleGameFinishedScreen();
		createGameFinishedListeners();
		show();
	}
	
	private void styleGameFinishedScreen() {
		
		// Game Finished Label
		
		gameFinishedBox.getChildren().add(lblGameFinished);
		
		lblGameFinished.setStyle("-fx-font: 36 arial");
		
		gameFinishedBox.setAlignment(Pos.CENTER);
		gameFinishedBox.setPadding(new Insets(0, 0, 0, 0));
		
		this.setCenter(gameFinishedBox);
		
		// Exit Button
		
		exitButtonBox.getChildren().add(btnExit);
		
		exitButtonBox.setAlignment(Pos.CENTER);
		exitButtonBox.setPadding(new Insets(0, 0, 0, 0));
		
		this.setRight(exitButtonBox);
		
		// Show Leaderboard Button
		
		showLeaderboardButtonBox.getChildren().add(btnShowLeaderboard);
		
		showLeaderboardButtonBox.setAlignment(Pos.CENTER);
		showLeaderboardButtonBox.setPadding(new Insets(0, 0, 0, 0));
		
		this.setBottom(showLeaderboardButtonBox);		
		
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
