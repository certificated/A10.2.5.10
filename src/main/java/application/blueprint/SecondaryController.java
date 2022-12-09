package application.blueprint;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class SecondaryController {

	/*
	
	
	Button IDeinlesen;
	
	//fx:id="secondaryButton"  onAction="#switchToPrimary"
	
	//Hauptfenster öfffnen = MainExtender.setRoot("primary");
	
    @FXML
    private void switchToPrimary() throws IOException {
    	MainExtender.setRoot("primary");
    	
    	System.out.println("Sie sind zurück im Hauptfenster!");
    }
    
    
    
    
    /*
    
    @FXML
    private TextField txtID;
    
    public void IDeinlesen(String Ort)  {
    	String sql = "insert into adresse(Ort) values ('"+Ort+"');";
    	
    	DBUtil.dbConnect(sql);
    }
    
    
    public void saveort(String ort) {
    	System.out.println("test");
    	
    }
    
    //test ob DB angesprochen werden kann
    public void retrieve() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kfz_rechnung", "root", "");
			
			System.out.println("es funktioniert");
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
    }*/
    
}
