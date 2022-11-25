package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import controller.PrimaryController;
import Class.Posten;
import Class.Rechnung;
import DAO.PostenDAO;
import application.blueprint.DBUtil;
import application.blueprint.MainExtender;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import controller.PrimaryController;

public class PostenController{

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
	renummer.setText("Rechnungsnummer: " +PrimaryController.renumme);
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
		
		//lol das hat den typen absolut zerstört
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
		   public void backTo2() throws IOException {
			  
				//PrimaryController.tabPane.getSelectionModel().select(PrimaryController.tab3);
			//PrimaryController.tab2();
			MainExtender.setRoot(null);
			  
			  
		  }

		@FXML
		public Button rnummer = new Button();
		
		@FXML
		public void rechnummer() throws IOException{
		//renummer.setText(Integer.toString(selectedRechnung.getrechnungsid()));
		//renummer.setText("test");
			
		};
		
		
		
		private Rechnung selectedRechnung;
		
		@FXML
		private Label renummer = new Label();
		
		/*@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
			/*String url = "jdbc:mysql://localhost:3306/kfz_rechnung";
			String user = "root";
			String pass = "";
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			String renummer = "SELECT ReNr FROM rechnung WHERE KDNr = ?  AND AdrNr = ? AND FI_ID = ?;";
			PreparedStatement hh = conn.prepareStatement(renummer);
			
			//setzen der neuen Werte in der zweiten Prepared Statement abfrage
			hh.setString(1, kdnr[0]);
			hh.setString(2, adrnr[0]);
			hh.setString(3, kfznr[0]);
			ResultSet resultat = hh.executeQuery();
			
			
			if(resultat.next()) {
				System.out.println(resultat.getString("ReNr"));
				Rechnungsnummer.setText("Rechnungsnummer: " +resultat.getString("ReNr"));
			}
			*/
			/*Platform.runLater(() -> {
				renummer.setText("test");
			});
			renummer.setText(PrimaryController.renumme);
			System.out.println(PrimaryController.renumme);
			
			
		} 
*/
}
