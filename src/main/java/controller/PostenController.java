package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
	renummer.setText("Rechnungsnummer: " + PrimaryController.Rin);
	
	System.out.println(PrimaryController.knr);
	
	getKunde();
	
	
	}
	
	
	@FXML
	private void getKunde() throws SQLException {
		
		 
		  
		  
		  	String url = "jdbc:mysql://localhost:3306/kfz_rechnung";
			String user = "root";
			String pass = "";
		
		
		
			Connection conn = DriverManager.getConnection(url, user, pass);
			
		
		
			
		String kdnr1 = "SELECT VName FROM kunde WHERE KDNr = ?;";
		
		String kdnr2 = "SELECT NName FROM kunde WHERE KDNr = ?;";
		
		PreparedStatement hh = conn.prepareStatement(kdnr1);
		
		PreparedStatement kk = conn.prepareStatement(kdnr2);
		
		
		
		hh.setString(1, PrimaryController.knr);
		
		kk.setString(1, PrimaryController.knr);
		
		ResultSet resultSet = hh.executeQuery();
		
		if(resultSet.next()) {
			System.out.println(resultSet.getString("VName"));
			kunde.setText( resultSet.getString("VName"));
			//Rin = resultat.getString("ReNr");
			//System.out.println(Rin);
			//Rechnung.setrechnungid(renumme);
		}
		
		ResultSet resultat = kk.executeQuery();
		
		
		
	}
	
	
	@FXML
	private void getAdresse() throws SQLException {
		
		 
		  
		  
		  	String url = "jdbc:mysql://localhost:3306/kfz_rechnung";
			String user = "root";
			String pass = "";
		
		
		
			Connection conn = DriverManager.getConnection(url, user, pass);
			
		
		
			
		String adnr1 = "SELECT VName FROM kunde WHERE KDNr = ?;";
		
		//String kdnr2 = "SELECT NName FROM kunde WHERE KDNr = ?;";
		
		PreparedStatement hh = conn.prepareStatement(adnr1);
		
		//PreparedStatement kk = conn.prepareStatement(kdnr2);
		
		
		
		hh.setString(1, PrimaryController.knr);
		
		//kk.setString(1, PrimaryController.knr);
		
		ResultSet resultSet = hh.executeQuery();
		
		if(resultSet.next()) {
			System.out.println(resultSet.getString("VName"));
			kunde.setText( resultSet.getString("VName"));
			//Rin = resultat.getString("ReNr");
			//System.out.println(Rin);
			//Rechnung.setrechnungid(renumme);
		}
		
		//ResultSet resultat = kk.executeQuery();
		
		
		
	}
	
	
	
	
	
	@FXML
	private Label datum;
	
	@FXML
	private Label kfz;
	
	@FXML
	private Label adresse;
	
	@FXML
	private Label kunde;
	
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
	  private DatePicker datePicker;
	  
	  
	  @FXML
	  private TextField artikelname;
	  
	  @FXML
	  private TextField einkaufspreis;
	  
	  @FXML
	  private Button submitArtikel;
	  
	
	@FXML
	private TableView<Posten> artikelTable = new TableView();
	
	@FXML
	private void submitArtikel(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		//PrimaryController.con();
		try {
			con();
			//DBUtil.dbConnect();
			//changeGreen();
			
			String url = "jdbc:mysql://localhost:3306/kfz_rechnung";
			String user = "root";
			String pass = "";
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			
			String art = artikelBox.getValue();
			
			String artid = "SELECT artikelNr FROM artikel WHERE artikelname = ?;";
			
			
			PreparedStatement jj  = conn.prepareStatement(artid);
			
			jj.setString(1, art);
			
			//int ll = jj.executeUpdate();
			
			ResultSet resultat = jj.executeQuery();
			
			//System.out.println(resultat.getInt());
			
			
			int artinr = 0;
			while (resultat.next()) {
				System.out.println(resultat.getInt("artikelNr"));
				artinr = resultat.getInt("artikelNr");
			}
			
			
			/*
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = new simpleDateFormat.format(new Date());
			*/
			
			//Column count doesn't match value count at row 1
			String oo = "INSERT INTO posten VALUES (null, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement ff = conn.prepareStatement(oo);
			
			ff.setInt(1, 1);
			ff.setInt(2, artinr);
			ff.setString(3, "2022-12-02");
			ff.setFloat(4, 30);
			ff.setInt(5, 1);
			ff.setInt(6, 2);
			
			ff.executeUpdate();
			
			
			
			
			String test = artikelBox.getValue();
			System.out.println(test);
			
			
			
			if (test.equals("Reifen")) {
				
				
				
				//ObservableList<Posten> psData = PostenDAO.searchPosten();
				//populatePosten(psData);
				System.out.println("ok");
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
			MainExtender.setRoot("primary2");
			  
			  
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
