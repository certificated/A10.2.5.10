package application.blueprint;

import java.awt.event.ActionEvent;   
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.sql.*;


public class DBUtil {
	
	//Model für DB Verbindung
	
	public static Connection con = null;
	PreparedStatement preparedStatement = null;
	
	/*public static void changeScene(ActionEvent event, String fxmlFile, String title, String ort) {
		Parent root = null;
		
		try {
			FXMLLoader loader = new FXMLLoader(DBUtil.class.getResource(fxmlFile));
			root = loader.load();
			SecondaryController secondaryController = loader.getController();
			secondaryController.saveort(ort);
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root));
		stage.show();
		
	}*/
	
	//jdbc muss eingebunden sein
	public static void dbConnect(/*ActionEvent event*/) {
		//Connection connection = null;
		//PreparedStatement preparedStatement = null;
		
		
		String url = "jdbc:mysql://localhost:3306/kfz_rechnung";
		String user = "root";
		String pass = "";

		try {
		    con = DriverManager.getConnection(url, user, pass);
		    //System.out.println("Verbindung erfolgreich hergestellt");
		    
		    
		    
		} catch (SQLException e) {
		    System.out.println(e.getMessage());
			
		}
		
		}
		
	public static void dbDisconnect() {
		
		try {
			if(con != null && !con.isClosed()) {
				con.close();
				//System.out.println("Die Verbindung wurde erfolgreich unterbrochen.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//abspeichern des Datensatzes und Ausgabe 
	//Abändern des CachedRowSet in die neuere Variante
	public static ResultSet dbExcecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException{
		
		
		
		Statement stmt = null;
		ResultSet resultSet = null;
		RowSetFactory rsf = RowSetProvider.newFactory();
		CachedRowSet crs = null;
		String test;
		
	try {
		
		System.out.println("Select statement: " + queryStmt +"\n");
		stmt = con.createStatement();
		
		resultSet = stmt.executeQuery(queryStmt);
		
		//crs = new CachedRowSetImpl();
		crs = rsf.createCachedRowSet();
		crs.populate(resultSet);
	
	}
		
	 catch(Exception e) {
		
		e.printStackTrace();
	}
	finally {
		if (resultSet != null) {
		    resultSet.close();
        }
        if (stmt != null) {
            
            stmt.close();
        }
        
    }
	
			return crs;
			
		}
		
	public static void dbExcequteUpdate(String sqlStmt) throws SQLException {
		
		Statement stmt = null;
		try {
			dbConnect();
			stmt = con.createStatement();
			stmt.executeUpdate(sqlStmt);
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			if(stmt != null) {
				stmt.close();
				
			}
			dbDisconnect();
		}
	}
	
	
	
	
}
	
	
	
	
	


