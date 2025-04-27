// Project: Comp296 - Trivia Time Project
// Filename: LeaderboardScreen.java
//
// Creates the Trivia Time leaderboard screen
package leaderboard;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.GameData;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LeaderboardScreen extends BorderPane {
	
	private Stage stage = new Stage();
	
	private Button btnExit = new Button("Exit");
	
	private Label lblLeaderboardID = new Label("Leaderboard ID");
	private Label lblPlayerName = new Label("Player Name");
	private Label lblCategoryID = new Label("Category ID");
	private Label lblScore = new Label("Number of Correct Answers");
	
	private int[] leaderboardIDs;
	private String[] playerNames;
	private int[] categoryIDs;
	private int[] answeredCorrectlys;
	
	private String[] leaderboardIDStrings;
	private String[] categoryIDStrings;
	private String[] answeredCorrectlyStrings;
	
	private Label[] lblLeaderboardIDStats;
	private Label[] lblPlayerNameStats;
	private Label[] lblCategoryIDStats;
	private Label[] lblScoreStats;
	
	private VBox leaderboardIDBox = new VBox();
	private VBox playerNameBox = new VBox();
	private VBox categoryIDBox = new VBox();
	private VBox answeredCorrectlyBox = new VBox();
	
	private HBox compilationBox = new HBox();
	private HBox exitButtonBox = new HBox();

	public LeaderboardScreen() {
		createLeaderboard();
		styleLeaderboardScreen();
		createLeaderboardListeners();
		show();
	}
	
	private void createLeaderboard() {
		
		leaderboardIDBox.getChildren().add(lblLeaderboardID);
		playerNameBox.getChildren().add(lblPlayerName);
		categoryIDBox.getChildren().add(lblCategoryID);
		answeredCorrectlyBox.getChildren().add(lblScore);
		
		int rowCount;
		GameData dbObj = new GameData();
		ResultSet rs = dbObj.getLeaderboard();
		try {
			rs.last();
			rowCount = rs.getRow();
			System.out.println("The current row count is: " + rowCount);
			rs.beforeFirst();
			
			leaderboardIDs = new int[rowCount];
			playerNames = new String[rowCount];
			categoryIDs = new int[rowCount];
			answeredCorrectlys = new int[rowCount];
			
			leaderboardIDStrings = new String[rowCount];
			categoryIDStrings = new String[rowCount];
			answeredCorrectlyStrings = new String[rowCount];
			
			lblLeaderboardIDStats = new Label[rowCount];
			lblPlayerNameStats = new Label[rowCount];
			lblCategoryIDStats = new Label[rowCount];
			lblScoreStats = new Label[rowCount];
			
            int loopCount = 0;
			while (rs.next()) {
				leaderboardIDs[loopCount] = rs.getInt("LeaderboardID");
				playerNames[loopCount] = rs.getString("Name");
				categoryIDStrings[loopCount] = rs.getString("CategoryName");
				answeredCorrectlys[loopCount] = rs.getInt("Score");
				
				leaderboardIDStrings[loopCount] = Integer.toString(leaderboardIDs[loopCount]);
				answeredCorrectlyStrings[loopCount] = Integer.toString(answeredCorrectlys[loopCount]);
				
				lblLeaderboardIDStats[loopCount] = new Label(leaderboardIDStrings[loopCount]);
				lblPlayerNameStats[loopCount] = new Label(playerNames[loopCount]);
				lblCategoryIDStats[loopCount] = new Label(categoryIDStrings[loopCount]);
				lblScoreStats[loopCount] = new Label(answeredCorrectlyStrings[loopCount]);
				
				leaderboardIDBox.getChildren().add(lblLeaderboardIDStats[loopCount]);
				playerNameBox.getChildren().add(lblPlayerNameStats[loopCount]);
				categoryIDBox.getChildren().add(lblCategoryIDStats[loopCount]);
				answeredCorrectlyBox.getChildren().add(lblScoreStats[loopCount]);
				
				loopCount++;
            }
			dbObj.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void styleLeaderboardScreen() {
		
		// LeaderboardID Box
		
		leaderboardIDBox.setSpacing(20);
		leaderboardIDBox.setAlignment(Pos.CENTER);
		leaderboardIDBox.setPadding(new Insets(0, 0, 0, 0));
		
		this.setTop(leaderboardIDBox);
		
		// Player Name Box
		
		playerNameBox.setSpacing(20);
		playerNameBox.setAlignment(Pos.CENTER);
		playerNameBox.setPadding(new Insets(0, 0, 0, 0));
		
		this.setTop(playerNameBox);
		
		// CategoryID Box
		
		categoryIDBox.setSpacing(20);
		categoryIDBox.setAlignment(Pos.CENTER);
		categoryIDBox.setPadding(new Insets(0, 0, 0, 0));
		
		this.setTop(categoryIDBox);
		
		// Score Box
		
		answeredCorrectlyBox.setSpacing(20);
		answeredCorrectlyBox.setAlignment(Pos.CENTER);
		answeredCorrectlyBox.setPadding(new Insets(0, 0, 0, 0));
		
		this.setTop(answeredCorrectlyBox);
		
		// Compilation Box
		
		compilationBox.getChildren().addAll(leaderboardIDBox, playerNameBox, categoryIDBox, answeredCorrectlyBox);
		
		compilationBox.setSpacing(50);
		compilationBox.setAlignment(Pos.CENTER);
		compilationBox.setPadding(new Insets(0, 0, 0, 0));
		
		ScrollPane leaderboardSP = new ScrollPane(compilationBox);
		leaderboardSP.setMinWidth(600);
		
		this.setLeft(leaderboardSP);
		
		// Exit Button Box
		
		exitButtonBox.getChildren().addAll(btnExit);
		
		exitButtonBox.setSpacing(20);
		exitButtonBox.setAlignment(Pos.CENTER);
		exitButtonBox.setPadding(new Insets(0, 0, 0, 0));
		
		this.setRight(exitButtonBox);
		
	}
	
	private void createLeaderboardListeners() {
		
		btnExit.setOnAction(e -> {
			stage.close();
		});
		
	}
	
	public void show() {
		
		//The usual
		Scene scene = new Scene(this, 700, 500);
		stage.setTitle("Leaderboard Screen");
		stage.setScene(scene);
		stage.show();
		
	}
	
}