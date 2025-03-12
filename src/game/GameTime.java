// Project: Comp296 - Trivia Time Project
// Filename: GameTime.java
// Creates the Trivia Time game itself

package game;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameTime extends BorderPane {

	private Button btnBack;
	private Button btnExit;
	
	private Label lblChooseTopic;
	
	private Button btnBingU;
	private Button btnSfgadv;
	private Button btnVeg;
	private Button btnPsych;
	private Button btnGeo;
	
	private RadioButton option1;
	private RadioButton option2;
	private RadioButton option3;
	private RadioButton option4;
	private RadioButton option5;
	
	private Button btnSubmit;
	
	private Label correct1;
	private Label correct2;
	private Label correct3;
	private Label correct4;
	private Label correct5;
	
	private Label youAnswered1;
	private Label youAnswered2;
	private Label youAnswered3;
	private Label youAnswered4;
	private Label youAnswered5;
	
	private Label correctAnswer1;
	private Label correctAnswer2;
	private Label correctAnswer3;
	private Label correctAnswer4;
	private Label correctAnswer5;
	
	private Label gameFinished;
	
	private HBox backAndExitButtonsBox;
	private VBox bodyBox;
	
	private String title;
	
	public GameTime() {
		
		// Initializations
		
		btnBack = new Button();
		btnExit = new Button();
		
		lblChooseTopic = new Label();
		
		btnBingU = new Button();
		btnSfgadv = new Button();
		btnVeg = new Button();
		btnPsych = new Button();
		btnGeo = new Button();
		
		option1 = new RadioButton();
		option2 = new RadioButton();
		option3 = new RadioButton();
		option4 = new RadioButton();
		option5 = new RadioButton();
		
		btnSubmit = new Button();
		
		correct1 = new Label();
		correct2 = new Label();
		correct3 = new Label();
		correct4 = new Label();
		correct5 = new Label();
		
		youAnswered1 = new Label();
		youAnswered2 = new Label();
		youAnswered3 = new Label();
		youAnswered4 = new Label();
		youAnswered5 = new Label();
		
		correctAnswer1 = new Label();
		correctAnswer2 = new Label();
		correctAnswer3 = new Label();
		correctAnswer4 = new Label();
		correctAnswer5 = new Label();
		
		gameFinished = new Label();
		
		backAndExitButtonsBox = new HBox();
		bodyBox = new VBox();
		
		this.title = "Trivia Time!";
		
		styleScreen();
		show();
		createGameListeners();
		
	}
	
	public void styleScreen() {
		
		backAndExitButtonsBox.setSpacing(20);
		backAndExitButtonsBox.setAlignment(Pos.CENTER);
		backAndExitButtonsBox.setPadding(new Insets(0, 0, 0, 0));
		
		bodyBox.setSpacing(20);
		bodyBox.setAlignment(Pos.CENTER);
		bodyBox.setPadding(new Insets(0, 0, 0, 0));
		
	}
	
	public void show() {
		
		Stage stage = new Stage();
		
		//The usual
		Scene scene = new Scene(this, 700, 350);
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
		
	}
	
	private void createGameListeners() {
		
	}
	
}
