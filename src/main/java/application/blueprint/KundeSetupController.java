package application.blueprint;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import application.blueprint.Kunde;
import application.blueprint.KundeDAO;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.function.Predicate;











public class KundeSetupController {
	
	/*@FXML
	public void switchToPrimary() throws IOException{
		MainExtender.setRoot("primary");
		
		
	}
	@FXML
	public void  switchToOverview() throws IOException{
		MainExtender.setRoot("primary");

	}
	*/
	@FXML
	private Button kdInsert = new Button("Submit");
	@FXML
    private TextField kdIdText;
    @FXML
    private TextArea resultArea;
    @FXML
    private TextField kdEmail = new ValidatingTextField(input-> input.contains("a"));;
    @FXML
    private TextField kdNachname;
    @FXML
    private TextField kdVorname;
    @FXML
    private TextField kdTelefon;
	
	@FXML
	private TableView KundenTable;
	
	@FXML
	private TableColumn<Kunde , Integer> kundeID;
	
	@FXML
	private TableColumn<Kunde, String> kundeNachname;
	
	@FXML
	private TableColumn<Kunde, String> kundeVorname;
	
	@FXML
	private TableColumn<Kunde, String> kundeEmail;
	
	@FXML
	private TableColumn<Kunde, String> kundetel;
	
	//wir wollen noch nicht searchEmployees
	
	
	
	private void initialize() {
		kundeID.setCellValueFactory(cellData -> cellData.getValue().kundeidproperty().asObject());
		kundeNachname.setCellValueFactory(cellData -> cellData.getValue().nachnameproperty());
		kundeVorname.setCellValueFactory(cellData -> cellData.getValue().vornameproperty());
		kundeEmail.setCellValueFactory(cellData -> cellData.getValue().emailproperty());
		kundetel.setCellValueFactory(cellData -> cellData.getValue().telproperty());
	}
	
	@FXML
	private void  populateKunde (Kunde kd) throws ClassNotFoundException {
		ObservableList<Kunde> kdData = FXCollections.observableArrayList();
		kdData.add(kd);
		KundenTable.setItems(kdData);
	}
	
	@FXML
	private void setKdInfoToTextArea (Kunde kd) {
		resultArea.setText("Vorname: " + kd.getvorname() + "\n" + "Nachname" +kd.getnachname());
	}
	
	@FXML
	private void populateAndShowKunde(Kunde kd) throws ClassNotFoundException{
		if (kd != null) {
			
			populateKunde(kd);
			setKdInfoToTextArea(kd);
		} else {
			resultArea.setText("Dieser Kunde existiert nicht");
		}
	}
	
	@FXML
	private void populateKunden (ObservableList<Kunde> kdData) throws ClassNotFoundException {
		KundenTable.setItems(kdData);
	}
	
	@FXML
	private void kundeEintragen (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		KundeDAO.insertKd(kdNachname.getText(), kdVorname.getText(), kdEmail.getText(), kdTelefon.getText());
		resultArea.setText("Kunde eingetragen");
		}
	

	@FXML
	private void switchToOverview () throws SQLException, ClassNotFoundException, IOException {
		MainExtender.setRoot("primary");
	}
	
	
	
	
	
	private static class ValidatingTextField extends TextField {
		private final Predicate<String> validation;
		private BooleanProperty isValidProperty = new SimpleBooleanProperty();
		
		ValidatingTextField(Predicate<String> validation) {
			this.validation = validation;
			
			textProperty().addListener((o,oldValue, newText) -> {
				isValidProperty.set(validation.test(newText));
			});
			
			isValidProperty.set(validation.test(""));
		}
	}
	
	
	
}
