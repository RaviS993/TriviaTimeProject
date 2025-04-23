// Project: Comp296 - Trivia Time Project
// Filename: GameData.java
// Creates a database for the Trivia Time project
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class GameData {
	
	private Connection connection;
	private Statement statement;
	
	public GameData() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//Establish a connection
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/triviatime", "root", "Tscrkwht828_?!");
			System.out.println("Database connected");
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		//Create a statement object
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			System.out.println("Statement object created");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ResultSet getCategory() {
		
		ResultSet rs = null;
		System.out.println("");
		
		try {
			rs = statement.executeQuery("SELECT * FROM category");
            /*
			ResultSetMetaData mData = rs.getMetaData();
            int numOfCatColumns = mData.getColumnCount();
            for (int i = 1; i <= numOfCatColumns; i++) {
                System.out.printf("%-20s", mData.getColumnName(i));
            }
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= numOfCatColumns; i++) {
                    System.out.printf("%-20s", rs.getString(i));
                }
                System.out.println();
            }
            */
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("");
		return rs;
		
	}
	
	public ResultSet getQuestions(int category) {
		
		ResultSet rs = null;
		System.out.println("");
		
		try {
			rs = statement.executeQuery("SELECT * FROM questions WHERE category_CategoryID = " + category);
            ResultSetMetaData mData = rs.getMetaData();
            int numOfQuesColumns = mData.getColumnCount();
            for (int i = 1; i <= numOfQuesColumns; i++) {
                System.out.printf("%-20s", mData.getColumnName(i));
            }
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= numOfQuesColumns; i++) {
                    System.out.printf("%-20s", rs.getString(i));
                }
                System.out.println();
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("");
		return rs;
		
	}
	
	public ResultSet getAnswers(int question) {
		
		ResultSet rs = null;
		System.out.println("");
		
		try {
			rs = statement.executeQuery("SELECT * FROM answers WHERE questions_QuestionID = " + question);
            ResultSetMetaData mData = rs.getMetaData();
            int numOfAnsColumns = mData.getColumnCount();
            for (int i = 1; i <= numOfAnsColumns; i++) {
                System.out.printf("%-20s", mData.getColumnName(i));
            }
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= numOfAnsColumns; i++) {
                    System.out.printf("%-20s", rs.getString(i));
                }
                System.out.println();
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("");
		return rs;
		
	}
	
	public void saveLeaderboard(String name, int categoryID, int answeredCorrectly) {
		
		String insertStatement = "INSERT INTO leaderboard (name, categoryID, score) VALUES ('" + name + "', " + categoryID + ", '" + answeredCorrectly + "')";

		try {
			statement.executeUpdate(insertStatement);
			System.out.println("Leaderboard data inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(insertStatement);
		
	}
	
	public ResultSet getLeaderboard() {
		
		ResultSet rs = null;
		System.out.println("");
		
		try {
			rs = statement.executeQuery("SELECT * FROM leaderboard");
            ResultSetMetaData mData = rs.getMetaData();
            int numOfLeadColumns = mData.getColumnCount();
            for (int i = 1; i <= numOfLeadColumns; i++) {
                System.out.printf("%-20s", mData.getColumnName(i));
            }
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= numOfLeadColumns; i++) {
                    System.out.printf("%-20s", rs.getString(i));
                }
                System.out.println();
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("");
		return rs;
		
	}
	
	public void close() {
		
		try {
			connection.close();
			System.out.println("Database closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
