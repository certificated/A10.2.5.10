package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import controller.PrimaryController;
import Class.Posten;
import DAO.PostenDAO;
import application.blueprint.DBUtil;
import application.blueprint.MainExtender;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import controller.PrimaryController;

public class PostenController {

	@FXML
	private ChoiceBox<String> artikelBox;
	
	/* ursprüngliche version die Funktioneiert
	artikelBox.getItems().add("Reifen");
	artikelBox.getItems().add("Winterreifen");
	artikelBox.getItems().add("Rückspiegel");
	*/
	
	@FXML
	private void initialize() throws Exception {
	artikelBox.getItems().add("Reifen");
	artikelBox.getItems().add("Winterreifen");
	artikelBox.getItems().add("Rückspiegel");
	}
	
	@FXML
	private TableColumn<Posten , Integer> artid = new TableColumn<>();
	
	@FXML
	private TableColumn<Posten, String> artname = new TableColumn<>();
	
	@FXML
	private TableColumn<Posten, String> artbeschreibung = new TableColumn<>();
	
	@FXML 
	private TableColumn<Posten, Date> artab = new TableColumn<>();
	
	@FXML
	private TableColumn<Posten, Integer> artpreis = new TableColumn<>();
	
	@FXML
	public void newDate(String date) throws ClassNotFoundException, SQLException {
		String stmt = "SELECT * FROM posten ORDER BY datum DESC;";
		
		date = datum.getText();
		System.out.println(date);
		
		
		//if (isExpire(date) == true) {
			
		//}
		
		DBUtil.dbExcecuteQuery(stmt);
	}
	
	@FXML
	public TextField datum = new TextField();
	
	@FXML
	private TableView<Posten> artikelTable = new TableView();
	
	@FXML
	private void submitArtikel(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		//PrimaryController.con();
		try {
			con();
			//DBUtil.dbConnect();
			//changeGreen();
			
			
			
			String test = artikelBox.getValue();
			System.out.println(test);
			
			if (test.equals("Reifen")) {
				ObservableList<Posten> psData = PostenDAO.searchPosten();
				populatePosten(psData);
			}
			else {
				System.out.println("Sie haben keinen Artikel Ausgewählt!");
			} 
		}
		catch(SQLException e) {
			throw e;
		}
	}
		
		private void populatePosten (ObservableList<Posten> psData) throws ClassNotFoundException {
			artikelTable.setItems(psData);
			
		}
		
		@FXML  void con() {
			DBUtil.dbConnect();
			
		}
		
		@FXML
		private Button back;
		
		@FXML
		private void back() throws IOException {
			
			//PrimaryController.getTab();
			
			//MainExtender.setRoot("");
		}
}
