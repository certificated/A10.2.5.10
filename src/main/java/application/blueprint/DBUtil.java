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

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class DBUtil {
	
	
	//Model für DB Verbindung
	
	public static Connection conn = null;
	PreparedStatement preparedStatement = null;
	
	public static Session session = null;
	
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
	public static void dbConnect(/*ActionEvent event*/) throws SQLException {
		//Connection connection = null;
		//PreparedStatement preparedStatement = null;
		
		// SSL Tunnel settings
				String host = "bbwausbildung-digital.de"; 
				String user = "dbuser"; 
				String password = "Modesto#13"; 
				int lport = 1235; 
				int rport = 3306; 
				String rhost = "127.0.0.1"; 
		
				
				// MySQL Connection settings
				String dbuserName = "cajkakfz"; 
				String dbpassword = "Asdf456"; 
				String dbname = "kfz_werkstatt"; 
				String url = "jdbc:mysql://localhost:" + lport + "/" + dbname ; 
				String driverName = "com.mysql.jdbc.Driver"; 
				
				//Connection conn = null;
				Session session = null;
				try {
					
					java.util.Properties config = new java.util.Properties();
					config.put("StrictHostKeyChecking", "no"); 
					
					
					// JSch - settings
					JSch jsch = new JSch();
					session = jsch.getSession(user, host, 22);
					session.setPassword(password);
					session.setConfig(config);
					
					// JSch - connection
					session.connect();
					System.out.println("-- SSH connection successful");
					int assinged_port = session.setPortForwardingL(lport, rhost, rport);
					System.out.println("-- localhost:" + assinged_port + " tunneled to " + host + ":" + rport);

					// mysql database connectivity
					Class.forName(driverName);
					System.out.println("-- MySQL connecting to " + url);
					conn = DriverManager.getConnection(url, dbuserName, dbpassword);

					System.out.println("-- Database connection established");
					
				} catch (Exception e) {
					e.printStackTrace(); // print any errors
				} 
				/*finally {
					if (conn != null && !conn.isClosed()) { 
						System.out.println("-- Closing Database Connection");
						conn.close(); // Close: MySQL
					}
					if (session != null && session.isConnected()) {
						System.out.println("-- Closing SSH Connection");
						session.disconnect(); // Close: SSH
					}
				}*/
			}
	//muss unbedingt in einer eigenen Funktion stehen damit nicht nach der Verbindung diese direkt wieder geschlossen wird
	public static void dbDisconnect() throws SQLException {
		System.out.println("wie");
		//if (session != null && session.isConnected()) {
			System.out.println("-- Closing SSH Connection");
			session.disconnect(); // Close: SSH
		
		
		if (conn != null && !conn.isClosed()) { 
			System.out.println("-- Closing Database Connection");
			conn.close(); // Close: MySQL
		}
		else {
			System.out.println("wo");
		}
		System.out.println("ist");
		if (session != null && session.isConnected()) {
			System.out.println("-- Closing SSH Connection");
			session.disconnect(); // Close: SSH
		}
	}
	
		
		
		
		
		//81.169.247.150   ssh -L 3307:127.0.0.1:3306 -N $user@81.169.247.150
		// versuch Putty zu verwenden um den Localhost Port weiterhin zu verwenden und so die andere DB zu erreichen
		  //String url = "jdbc:mysql://81.169.247.150/CajkaChristian_ex_1";
		  //String user = "CajkaChr";
		  //String pass = "Qwert123";
		 
		
		
		// probably das hier überarbeiten falls ein anderer Port verwendet werden muss
		/*String url = "jdbc:mysql://localhost:3306/kfz_rechnung";
		String user = "root";
		String pass = "";
*/
	/*	try {
		    con = DriverManager.getConnection(url, user, pass);
		    //System.out.println("Verbindung erfolgreich hergestellt");
		    
		    
		    
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		//}
	
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
	*/
	
	//abspeichern des Datensatzes und Ausgabe 
	//Abändern des CachedRowSet in die neuere Variante
	
	public static ResultSet dbExcecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException{
		
		dbConnect();
		//
		Statement stmt = null;
		ResultSet resultSet = null;
		RowSetFactory rsf = RowSetProvider.newFactory();
		CachedRowSet crs = null;
		String test;
		
	try {
		
		System.out.println("Select statement: " + queryStmt +"\n");
		stmt = conn.createStatement();
		
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
			stmt = conn.createStatement();
			stmt.executeUpdate(sqlStmt);
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			if(stmt != null) {
				stmt.close();
				
			}
		//	dbDisconnect();
		}
	}
	
	
	
	
}
	
	
	
	
	


