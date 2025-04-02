// Project: Comp296 - Trivia Time Project
// Filename: TriviaQuestions.java
// Creates the Trivia Time questions
package game;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.GameData;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TriviaQuestions extends GameTime {
	
	private String[] questions;
	private int[] questionIDs;
	private Label questionName;
	
	public TriviaQuestions(int category) {
		createCategoryQuestions(category);
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
				questions[loopCount] = new String(rs.getString("CategoryName"));
				questionIDs[loopCount] = rs.getInt("CategoryID");
				loopCount++;
            }
			System.out.println("Loop Count = " + loopCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void show() {
		
		Stage stage = new Stage();
		
		//The usual
		Scene scene = new Scene(this, 700, 350);
		stage.setTitle("title");
		stage.setScene(scene);
		stage.show();
		
	}
	
}
