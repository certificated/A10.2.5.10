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

public class KFZSetupController {

	
	@FXML
	private Button kdInsert;
	@FXML
    private TextField kfzid;
    @FXML
    private TextArea resultArea;
    @FXML
    private TextField marke;
    @FXML
    private TextField modell;
    @FXML
    private TextField hu;
    @FXML
    private TextField kennz;
    @FXML
    private TextField kmstand;
    @FXML
    private TextField erstzul;
    
    
    
    @FXML
	private TableView<Kunde> kfzTable = new TableView<>();
   
	
	@FXML
	private TableColumn<Kunde , Integer> kfzID = new TableColumn<>();
	
	@FXML
	private TableColumn<Kunde, String> kfzMarke = new TableColumn<>();
	
	@FXML
	private TableColumn<Kunde, String> kfzModell = new TableColumn<>();
	
	@FXML 
	private TableColumn<Kunde, String> kfzHu = new TableColumn<>();
	
	@FXML
	private TableColumn<Kunde, String> kfzKennz = new TableColumn<>();
    
	@FXML
	private TableColumn<Kunde, String> kfzKmstand = new TableColumn<>();
	@FXML
	private TableColumn<Kunde, String> kfzErstzul = new TableColumn<>();
	
	
	
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
	private void KFZEintragen (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		if (kfzid != null && marke != null && modell != null && hu != null && kennz != null && erstzul != null) {
		
		int id = Integer.parseInt(kfzid.getText());
		int km = Integer.parseInt(kmstand.getText());
		
		try {
		KFZDAO.insertKFZ(id, marke.getText(), modell.getText(), hu.getText(), kennz.getText(), km, erstzul.getText());
		resultArea.setText("Kunde eingetragen");
		} catch (SQLException e) {
			resultArea.setText("problem");
		}
		}else {System.out.println("alle Felder müssen ausgefüllt werden!");}
	} 
	
	@FXML
	private void switchToOverview () throws SQLException, ClassNotFoundException, IOException {
		MainExtender.setRoot("primary2");
	}
}
