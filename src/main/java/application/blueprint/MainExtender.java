package application.blueprint;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;


/**
 * JavaFX App
 */
public class MainExtender extends Application {

    private static Scene scene;
    
    public static Stage window;

    @Override
    public void start (Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary2"));
        scene.setRoot(loadFXML("primary2"));
        stage.setScene(scene);
        stage.show();
        window = stage;
        
       stage.setOnCloseRequest(e -> {
        	//e.consume();
			try {
				closeProgramm();
				//System.out.println("teeeeeest");
				//Platform.exit();
				//System.exit(0);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}});

        
        
    }

    public static void setRoot(String fxml) throws IOException {
    	scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
        
    }

    @FXML
	 public static void closeProgramm() throws SQLException {
	 
		 DBUtil.dbDisconnect();
	 
	 }
    
    



}