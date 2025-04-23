// Project: Comp296 - Trivia Time Project
// Filename: TriviaQuestions.java
// Creates the Trivia Time questions
package game;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.GameData;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TriviaQuestions extends BorderPane {
	
	private GameTime landingScreen;
	private Stage stage = new Stage();
	
	// private Button btnBack = new Button();
	private Button btnExit = new Button("Exit");
	private Button btnSubmit = new Button("Submit");
	private Button btnContinue = new Button("Continue");
	
	private int questionNumber = 0;
	private ToggleGroup optionsGroup = new ToggleGroup();
	private int answeredCorrectly = 0;
	private int categoryIndex;
	private int category;
	private boolean isCorrect = false;
	private String enteredName;
	
	private Label questionName = new Label();
	private Label correctness = new Label();
	private Label correctAnswerTally = new Label("Number of questions answered correctly: " + answeredCorrectly);
	
	private String[] questions;
	private int[] questionIDs;
	private RadioButton[] optionButtons = new RadioButton[5];
	private String[] answers;
	private boolean[] corrects;
	
	// private HBox backButtonBox = new HBox(btnBack);
	private HBox exitButtonBox = new HBox(btnExit);
	private HBox questionNameBox = new HBox(questionName);
	private HBox bottomBox = new HBox(20);
	private HBox correctAnswerTallyBox = new HBox(20);
	
	private VBox optionButtonsBox = new VBox();
	private VBox centerBox = new VBox(20);
	
	public TriviaQuestions(int category, GameTime landingScreen, int categoryIndex, String enteredName) {
		this.landingScreen = landingScreen;
		this.categoryIndex = categoryIndex;
		this.enteredName = enteredName;
		this.category = category;
		createCategoryQuestions(category);
		createOptions();
		questionName.setText(questions[questionNumber]);
		bottomBox.getChildren().addAll(btnSubmit, correctness);
		centerBox.getChildren().addAll(questionNameBox, optionButtonsBox, bottomBox, btnContinue);
		correctAnswerTallyBox.getChildren().addAll(correctAnswerTally);
		this.setTop(correctAnswerTally);
		this.setCenter(centerBox);
		this.setRight(exitButtonBox);
		btnContinue.setVisible(false);
		getAnswers();
		showAnswers();
		createQuizListeners(category);
		show();
	}
	
	public void createCategoryQuestions(int category) {
		
		int rowCount;
		GameData dbObj = new GameData();
		ResultSet rs = dbObj.getQuestions(category);
		try {
			rs.last();
			rowCount = rs.getRow();
			rs.beforeFirst();
			questions = new String[rowCount];
			questionIDs = new int[rowCount];
            int loopCount = 0;
			while (rs.next()) {
				questions[loopCount] = new String(rs.getString("Questions"));
				questionIDs[loopCount] = rs.getInt("QuestionID");
				loopCount++;
            }
			System.out.println("Loop Count = " + loopCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void createOptions() {
		
		btnSubmit.setDisable(true);
		
		for (int i = 0; i < optionButtons.length; i++) {
			optionButtons[i] = new RadioButton("answer " + (i + 1));
			optionButtonsBox.getChildren().add(optionButtons[i]);
			optionButtons[i].setToggleGroup(optionsGroup);
			
			final int loop = i;
			optionButtons[i].setOnAction(e -> {
				btnSubmit.setDisable(false);
				isCorrect = corrects[loop];
				System.out.println("Option Button Number = " + loop);
			});
		}
		
	}
	
	public void getAnswers() {
		
		System.out.println("Q Number = " + questionNumber);
		int rowCount;
		GameData dbObj = new GameData();
		ResultSet rs = dbObj.getAnswers(questionIDs[questionNumber]);
		try {
			rs.last();
			rowCount = rs.getRow();
			rs.beforeFirst();
			answers = new String[rowCount];
			corrects = new boolean[rowCount];
            int loopCount = 0;
			while (rs.next()) {
				answers[loopCount] = new String(rs.getString("Answer"));
				corrects[loopCount] = rs.getBoolean("correct");
				loopCount++;
            }
			System.out.println("Loop Count = " + loopCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void showAnswers() {
		
		for (int i = 0; i < optionButtons.length; i++) {
			optionButtons[i].setText(answers[i]);
		}
		
	}
	
	private void createQuizListeners(int category) {
		
		btnExit.setOnAction(e -> {
			Platform.exit();
		});
		
		btnSubmit.setOnAction(e -> {
			for (int i = 0; i < optionButtons.length; i++) {
				optionButtons[i].setDisable(true);
			}
			if (isCorrect) {
				correctness.setText("Correct!");
				answeredCorrectly++;
				correctAnswerTally.setText("Number of questions answered correctly: " + answeredCorrectly);
			} else {
				correctness.setText("Incorrect!");
			}
			for (int i = 0; i < optionButtons.length; i++) {
				if (corrects[i] == true) {
					optionButtons[i].setTextFill(Color.color(0, 1, 0));
				}
				else {
					optionButtons[i].setTextFill(Color.color(1, 0, 0));
				}
			}
			btnContinue.setVisible(true);
			btnSubmit.setDisable(true);
		});
		
		btnContinue.setOnAction(e -> {
			questionNumber++;
			btnContinue.setVisible(false);
			correctness.setText("");
			for (int i = 0; i < optionButtons.length; i++) {
				optionButtons[i].setDisable(false);
				optionButtons[i].setTextFill(Color.color(0, 0, 0));
			}
			if (questionNumber < questions.length) {
				questionName.setText(questions[questionNumber]);
				getAnswers();
				showAnswers();
			} else {
				System.out.println("The count in continue is " + answeredCorrectly);
				landingScreen.updateCategoryUsed(categoryIndex);
				landingScreen.setNumberOfCorrectAnswers(answeredCorrectly);
				GameData dbGdt = new GameData();
				dbGdt.saveLeaderboard(enteredName, category, answeredCorrectly);
				dbGdt.close();
				stage.close();
			}
		});
		
	}
	
	public void show() {
		
		//The usual
		Scene scene = new Scene(this, 700, 350);
		stage.setTitle("title");
		stage.setScene(scene);
		stage.show();
		
	}
	
}
