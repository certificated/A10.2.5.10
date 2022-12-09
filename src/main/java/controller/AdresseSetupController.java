package controller;

import java.io.IOException;
import java.sql.SQLException;

import Class.Kunde;
import DAO.AdresseDAO;
import application.blueprint.MainExtender;
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
	    public TextField ort;
	    @FXML
	    public TextField plz;
	    @FXML
	    public TextField strasse;
	    @FXML
	    public TextField hr;
	   
	    
	    
	    
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
		
		
		/**
		 * Wenn keine Werte Fehlen werden die Eingetragenen Werte in die Datenbank übernommen
		 * @param actionEvent
		 * @throws SQLException
		 * @throws ClassNotFoundException
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
		
		/**
		 * um auf die vorherige View zurückzukommen
		 * @throws SQLException
		 * @throws ClassNotFoundException
		 * @throws IOException
		 */
		@FXML
		private void switchToOverview () throws SQLException, ClassNotFoundException, IOException {
			MainExtender.setRoot("primary2");
		}
}
