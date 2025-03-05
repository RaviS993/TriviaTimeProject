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
	
	private Button btnSubmit;
	
	private Label correct1;
	private Label correct2;
	private Label correct3;
	private Label correct4;
	
	private Label youAnswered1;
	private Label youAnswered2;
	private Label youAnswered3;
	private Label youAnswered4;
	
	private Label correctAnswer1;
	private Label correctAnswer2;
	private Label correctAnswer3;
	private Label correctAnswer4;
	
	private Label gameFinished;
	
	private HBox backAndExitButtonsBox;
	private VBox bodyBox;
	
	public GameTime() {
		
		btnBack = new Button();
		btnExit = new Button();
		
		lblChooseTopic = new Label();
		
		btnBingU = new Button();
		btnSfgadv = new Button();
		btnVeg = new Button();
		btnPsych = new Button();
		btnGeo = new Button();
		
	}
	
}
