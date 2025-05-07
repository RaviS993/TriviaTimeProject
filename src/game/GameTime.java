// Project: Comp296 - Trivia Time Project
// Filename: GameTime.java
//
// Creates the Trivia Time game itself
package game;

import database.GameData;
import finished.GameFinishedScreen;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import leaderboard.LeaderboardScreen;

public class GameTime extends BorderPane {
	
	Stage stage = new Stage();

	private Button btnExit;
	private Button btnShowLeaderboard = new Button("Show Leaderboard");
	
	private Label lblWelcome;
	private Label lblChooseTopic;
	
	private Label lblEnterName;
	private TextField tfEnterName = new TextField();
	private String enteredName;
	
	private int numberOfCorrectAnswers = 0;
	private Label correctAnswerTally = new Label("Number of questions answered correctly: " + numberOfCorrectAnswers);
	
	private Button[] categories;
	private int[] categoryIDs;
	private boolean[] categoryUsed;
	
	private HBox exitButtonBox;
	private HBox correctAnswerTallyBox = new HBox(20);
	private HBox enterNameBox = new HBox(20);
	
	private VBox bodyBox;
	private VBox bottomBox;
	
	private String title;
	
	public GameTime() {
		
		// Initializations
		
		btnExit = new Button();
		
		lblWelcome = new Label("Welcome to Trivia Time!");
		lblChooseTopic = new Label("Choose a topic:");
		lblEnterName = new Label("Enter your name:");
		
		tfEnterName.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent enbl) {
				if (enbl.getCode().equals(KeyCode.ENTER)) {
					enteredName = tfEnterName.getText();
					for (int i = 0; i < categories.length; i++) {
						categories[i].setDisable(false);
					}
				}
			}
		});
		
		this.title = "Trivia Time!";
		
		createCategoryButtons();
		styleLandingScreen();
		show();
		createLandingListeners();
		
	}
	
	public void createCategoryButtons() {
		
		int rowCount;
		GameData dbObj = new GameData();
		ResultSet rs = dbObj.getCategory();
		try {
			rs.last();
			rowCount = rs.getRow();
			rs.beforeFirst();
			categories = new Button[rowCount];
			categoryIDs = new int[rowCount];
			categoryUsed = new boolean[rowCount];
            int loopCount = 0;
			while (rs.next()) {
				categories[loopCount] = new Button(rs.getString("CategoryName"));
				categoryIDs[loopCount] = rs.getInt("CategoryID");
				categories[loopCount].setDisable(true);
				loopCount++;
            }
			categories[0].setStyle("-fx-base: brown");
			categories[1].setStyle("-fx-base: yellow");
			categories[2].setStyle("-fx-base: green");
			categories[3].setStyle("-fx-base: blue");
			categories[4].setStyle("-fx-base: red");
			dbObj.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateCategoryUsed(int categoryIndex) {
		categoryUsed[categoryIndex] = true;
		categories[categoryIndex].setDisable(true);
	}
	
	public void styleLandingScreen() {
		
		// Correct Answer Tally Box
		
		correctAnswerTallyBox.getChildren().add(correctAnswerTally);
		this.setTop(correctAnswerTallyBox);
		
		// Enter Name Box
		
		enterNameBox.getChildren().addAll(lblEnterName, tfEnterName);
		
		// Exit Button Box
		
		exitButtonBox = new HBox(btnExit);
		exitButtonBox.setSpacing(20);
		exitButtonBox.setAlignment(Pos.CENTER);
		exitButtonBox.setPadding(new Insets(0, 0, 0, 0));
		
		btnExit.setText("Exit");
		
		this.setRight(exitButtonBox);
		
		// Category Boxes
		
		lblWelcome.setStyle("-fx-font: 24 arial");
		bodyBox = new VBox(lblWelcome, enterNameBox, lblChooseTopic);
		
		for (int i = 0; i < categories.length; i++) {
			bodyBox.getChildren().add(categories[i]);
		}
		
		// Body Box
		
		bodyBox.setSpacing(20);
		bodyBox.setAlignment(Pos.CENTER);
		bodyBox.setPadding(new Insets(0, 0, 0, 0));
		
		this.setCenter(bodyBox);
		
		// compilationBox = new VBox(exitButtonBox, bodyBox);
		
		// Bottom Box
		
		bottomBox = new VBox(btnShowLeaderboard);
		
		bottomBox.setSpacing(20);
		bottomBox.setAlignment(Pos.CENTER);
		bodyBox.setPadding(new Insets(0, 0, 0, 0));
		
		this.setBottom(bottomBox);
		
	}
	
	public void show() {
		
		//The usual
		Scene scene = new Scene(this, 700, 500);
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void createLandingListeners() {
		
		btnExit.setOnAction(e -> {
			Platform.exit();
		});
		
		for (int i = 0; i < categories.length; i++) {
			int index = i;
			categories[i].setOnAction(e -> {
				new TriviaQuestions(categoryIDs[index], this, index, enteredName);
			});
		}
		
		btnShowLeaderboard.setOnAction(e -> {
			LeaderboardScreen dbLbs = new LeaderboardScreen();
		});
		
	}

	public void setNumberOfCorrectAnswers(int numberOfCorrectAnswers) {
		this.numberOfCorrectAnswers += numberOfCorrectAnswers;
		correctAnswerTally.setText("Number of questions answered correctly: " + this.numberOfCorrectAnswers);
	}

	public void checkGameOver() {
		boolean gameOver = true;
		
		for (int i = 0; i < categoryUsed.length; i++) {
			if (categoryUsed[i] == false) {
				gameOver = false;
				break;
			}
		}
		
		if (gameOver) {
			new GameFinishedScreen();
			stage.close();
		}
	}
	
}