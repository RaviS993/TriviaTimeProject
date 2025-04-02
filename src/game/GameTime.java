// Project: Comp296 - Trivia Time Project
// Filename: GameTime.java
// Creates the Trivia Time game itself
package game;

import database.GameData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameTime extends BorderPane {

	private Button btnBack;
	private Button btnExit;
	
	private Label lblWelcome;
	private Label lblChooseTopic;
	
	private Button[] categories;
	private int[] categoryIDs;
	
	/*
	private Button btnBingU;
	private Button btnSfGadv;
	private Button btnVeg;
	private Button btnPsych;
	private Button btnGeo;
	*/
	
	private HBox backButtonBox;
	private HBox exitButtonBox;
	private VBox bodyBox;
	
	private String title;
	
	public GameTime() {
		
		// Initializations
		
		btnExit = new Button();
		
		lblWelcome = new Label("Welcome to Trivia Time!");
		
		lblChooseTopic = new Label("Choose a topic:");
		
		/*
		btnBingU = new Button();
		btnSfGadv = new Button();
		btnVeg = new Button();
		btnPsych = new Button();
		btnGeo = new Button();
		*/
		
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
            int loopCount = 0;
			while (rs.next()) {
				categories[loopCount] = new Button(rs.getString("CategoryName"));
				categoryIDs[loopCount] = rs.getInt("CategoryID");
				loopCount++;
            }
			categories[0].setStyle("-fx-base: brown");
			categories[1].setStyle("-fx-base: yellow");
			categories[2].setStyle("-fx-base: green");
			categories[3].setStyle("-fx-base: blue");
			categories[4].setStyle("-fx-base: red");
			System.out.println("Loop Count = " + loopCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void styleLandingScreen() {
		
		// Exit Button Box
		
		exitButtonBox = new HBox(btnExit);
		exitButtonBox.setSpacing(20);
		exitButtonBox.setAlignment(Pos.CENTER);
		exitButtonBox.setPadding(new Insets(0, 0, 0, 0));
		
		btnExit.setText("Exit");
		
		this.setRight(exitButtonBox);
		
		// Category Boxes
		
		lblWelcome.setStyle("-fx-font: 24 arial");
		bodyBox = new VBox(lblWelcome, lblChooseTopic);
		
		for (int i = 0; i < categories.length; i++) {
			bodyBox.getChildren().add(categories[i]);
		}
		
		bodyBox.setSpacing(20);
		bodyBox.setAlignment(Pos.CENTER);
		bodyBox.setPadding(new Insets(0, 0, 0, 0));
		
		this.setCenter(bodyBox);
		
		// compilationBox = new VBox(exitButtonBox, bodyBox);
		
	}
	
	public void show() {
		
		Stage stage = new Stage();
		
		//The usual
		Scene scene = new Scene(this, 700, 350);
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
		
	}
	
	private void createLandingListeners() {
		
		btnExit.setOnAction(e -> {
			
			Platform.exit();
			
		});
		
		for (int i = 0; i < categories.length; i++) {
			int index = i;
			categories[i].setOnAction(e -> {
				new TriviaQuestions(categoryIDs[index]);
				
				backButtonBox = new HBox(btnExit);
				backButtonBox.setSpacing(20);
				backButtonBox.setAlignment(Pos.CENTER);
				backButtonBox.setPadding(new Insets(0, 0, 0, 0));
				
				btnBack.setText("Back");
				
				this.setLeft(backButtonBox);
			});
		}
		
	}	
}
