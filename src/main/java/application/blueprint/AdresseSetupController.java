package application.blueprint;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class AdresseSetupController {
			
		@FXML
		private Button kdInsert;
		@FXML
	    private TextField adrid;
	    @FXML
	    private TextArea resultArea;
	    @FXML
	    private TextField ort;
	    @FXML
	    private TextField plz;
	    @FXML
	    private TextField strasse;
	    @FXML
	    private TextField hr;
	   
	    
	    
	    
	    @FXML
		private TableView<Kunde> adresseTable = new TableView<>();
	   
		
		@FXML
		private TableColumn<Kunde , Integer> adresseID = new TableColumn<>();
		
		@FXML
		private TableColumn<Kunde, String> adresseOrt = new TableColumn<>();
		
		@FXML
		private TableColumn<Kunde, String> adressePlz = new TableColumn<>();
		
		@FXML 
		private TableColumn<Kunde, String> adresseStrasse = new TableColumn<>();
		
		@FXML
		private TableColumn<Kunde, String> adresseHr = new TableColumn<>();
	    
		
		
		
		
		/*public static void nurZahlen() {
		kmstand.textProperty().addListener(new ChangeListener<String>() {
		    
			@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            System.out.println("test");
		        	kmstand.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		}); }
		*/
		
		
		
		@FXML
		private void AdresseEintragen (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
			if (ort != null && plz != null && strasse != null && hr != null) {
			
			//int id = Integer.parseInt(adrid.getText());
			int plzz = Integer.parseInt(plz.getText());
			int har = Integer.parseInt(hr.getText());
			try {
			AdresseDAO.insertAdresse(ort.getText(), plzz, strasse.getText(), har);
			resultArea.setText("Kunde eingetragen");
			} catch (SQLException e) {
				resultArea.setText("problem");
			}
			} else {System.out.println("alle Felder müssen ausgefüllt werden!");}
		} 
		
		@FXML
		private void switchToOverview () throws SQLException, ClassNotFoundException, IOException {
			MainExtender.setRoot("primary");
		}
}
