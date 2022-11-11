package controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import Class.Adresse;
import Class.KFZ;
import Class.Kunde;
import Class.Posten;
import Class.Stammdaten;
import DAO.AdresseDAO;
import DAO.KFZDAO;
import DAO.KundeDAO;
import DAO.PostenDAO;
import DAO.StammdatenDAO;
import application.blueprint.DBUtil;
import application.blueprint.MainExtender;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PrimaryController {

	
	@FXML
	private TextField Filter;
	@FXML
	private TextField Filter1;
	@FXML
	private TextField Filter2;
	@FXML
	private TextField Filter3;
	
	@FXML
	TextFieldTableCell<Kunde, String> cell = new TextFieldTableCell<>();
	
    //kunden Table
    @FXML
	private TableView<Kunde> kundenTable= new TableView<>();
   
	@FXML
	private TableColumn<Kunde , Integer> kundeID = new TableColumn<>();
	
	@FXML
	private TableColumn<Kunde, String> kundeNachname = new TableColumn<>();
	
	@FXML
	private TableColumn<Kunde, String> kundeVorname = new TableColumn<>();
	
	@FXML 
	private TableColumn<Kunde, String> kundeEmail = new TableColumn<>();
	
	@FXML
	private TableColumn<Kunde, String> kundetel = new TableColumn<>();
	
	@FXML
	private Button Rechnung = new Button();
	
	
	
	@FXML
	Button zeigeAlleKd;
	
	@FXML
	private void searchKunden (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		con();
		try {
			//con();
			//DBUtil.dbConnect();
			changeGreen();
			ObservableList<Kunde> kdData = KundeDAO.searchKunden();
		
		populateKunden(kdData); 
		
		} catch (SQLException e){
			System.out.println("Error bei DB");
			changeRed();
			throw e;
		}
	}
	
	@FXML
	DatePicker d = new DatePicker();
	
	@FXML
	   private void UpdateKunden() {
		   
		
		
		kundeVorname.setCellFactory(TextFieldTableCell.forTableColumn());	
		kundeVorname.setOnEditCommit(
			    new EventHandler<CellEditEvent<Kunde, String>>() {
			        @Override 
			        public void handle(CellEditEvent<Kunde, String> t) {
			            ((Kunde) t.getTableView().getItems().get(
			                t.getTablePosition().getRow())
			                ).setvorname(t.getNewValue());
			        }
			    }
			);
		
		
		int rowNum = kundenTable.getSelectionModel().getSelectedIndex();
			//System.out.println(rowNum);
		   
			int CellRow = kundeID.getCellData(rowNum);
			
			System.out.println(CellRow);
			
			
			
			//kundeVorname.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setvorname(e.getNewValue()));
			
			//kundeVorname.getCellData(rowNum);
			
			//System.out.println(kundeVorname.getCellData(rowNum));
			System.out.println(kundeVorname.getCellData(rowNum));
			
	   }
	
	
	
	@FXML
	private void searchKunde (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		try {
		
		Kunde kd = KundeDAO.searchKunde(kundeID.getText());
		
		populateKunde(kd);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void UpdateKunde() throws SQLException {
		
		//Connection con = null;
		
		String url = "jdbc:mysql://localhost:3306/kfz_rechnung";
		String user = "root";
		String pass = "";

		
		
		int rowNum = kundenTable.getSelectionModel().getSelectedIndex();
		
		int kdnr = rowNum++;
		
		String vorname = kundeVorname.getCellData(kdnr);
		String nachname = kundeNachname.getCellData(kdnr);
		String email = kundeEmail.getCellData(kdnr);
		String tel = kundetel.getCellData(kdnr);
		
		
		int id = kundeID.getCellData(kdnr);
		
		
		/*
		 * String stmt = "UPDATE kunde SET VName = '" +vorname+ 
				"' , NName = '" +nachname+ 
				"' , email = '" +email+ 
				"' , Tel = '"+tel+ 
				"' WHERE KDNr = " + id;
		*/
		
		final String prpstmt = "UPDATE kunde SET VName = ? , NName = ?  , email = ?  , Tel = ? WHERE KDNr = ?";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement prp = conn.prepareStatement(prpstmt);
			prp.setString(1, vorname);
			prp.setString(2, nachname);
			prp.setString(3, email);
			prp.setString(4, tel);
			prp.setInt(5, id);
			int rows = prp.executeUpdate();
			System.out.println("betroffene Zeilen: "+ rows);
		} catch (Exception e) {
			System.out.println("test");
		}
		//DBUtil.dbExcequteUpdate(stmt);
		
	}
	
	//TODO TAB PANE schneeeeeeeeelllllllllllll
	
	  @FXML
	  TabPane tabPane = new TabPane();
	  @FXML
	  Tab tab1 = new Tab();
	  @FXML
	  Tab tab2 = new Tab();
	  @FXML
	  Tab tab3 = new Tab();
	  @FXML
	  Tab tab4 = new Tab();
	  
	  @FXML
	   public void getTab() {
		  //tabPane.getSelectionModel().select(tab3);
		  int Tab = tabPane.getSelectionModel().getSelectedIndex() + 1;
		  //System.out.println(tabPane.getSelectionModel().getSelectedIndex());
		  //System.out.println(Tab);
		  //SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		  tabPane.getSelectionModel().select(tab2); 
		  
		  
	  }
	  @FXML
	  public void tab2() throws IOException {
		  //SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		  MainExtender.setRoot("primary2");
		 try {
			tabPane.getSelectionModel().select(tab3);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		  
	  }
	  
	  //@FXML
	  //public Button Rechnung = new Button();
	  
	  @FXML
	  public void Rechnung() throws IOException {
	    	MainExtender.setRoot("Artikel");
	    }
	  
	  /*@FXML
	  private void getTab() {
	  tabPane.getTabs().addAll(new Tab("Tab1"), new Tab("tab2"), new Tab("Tab3"));
	  
	  tabPane.getSelectionModel().select("tab2");
	  }*/
	
	
	// Choice BOX für die Artikelauswahl
	@FXML
	private ChoiceBox<String> artikelBox;
	
	@FXML
	private TableView<Posten> artikelTable = new TableView();
	
	@FXML
	private void submitArtikel(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		con();
		try {
			//con();
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
	
	// datumsabfrage
	@FXML
	public boolean isExpire(String date){
	    if(date.isEmpty() || date.trim().equals("")){
	        return false;
	    }else{
	            SimpleDateFormat sdf =  new SimpleDateFormat("MMM-dd-yyyy hh:mm:ss a"); // today
	               Date d=null;
	               Date d1=null;
	            String today = getToday("MMM-dd-yyyy hh:mm:ss a");
	            try {
	                // System.out.println("expdate>> "+date);
	                // System.out.println("today>> "+today+"\n\n");
	                d = sdf.parse(date);
	                d1 = sdf.parse(today);
	                System.out.println("alles erfolgreich gelaufen");
	                if(d1.compareTo(d) <0){ // not expired
	                    return false;
	                }else if(d.compareTo(d1)==0){ // both date are same
	                            if(d.getTime() < d1.getTime()){ // not expired
	                                return false;
	                            }else if(d.getTime() == d1.getTime()){ //expired
	                                return true;
	                            }else{ //expired
	                                return true;
	                                
	                            }
	                }else{//expired
	                	System.out.println("alles erfolgreich gelaufen");
	                	return true;
	                    
	                }
	                
	            } catch (ParseException e) {
	                e.printStackTrace();                    
	                return false;
	            }
	    }
	}


	  public static String getToday(String format){
	     Date date = new Date();
	     return new SimpleDateFormat(format).format(date);
	 }
	  
	  //kundenTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
	  //@Override
	  
	  
	  
	  // TODO aktuellen ausgewählten Wert in der Tabelle in ein Textfeld eintragen für später
	  
	  /*private void getSelected(ObservableValue observableValue, Object oldValue, Object newValue) {
		  if (kundenTable.getSelectionModel().getSelectedItem() != null) {
			  TableViewSelectionModel selectionModel = kundenTable.getSelectionModel();
			  ObservableList selectedCells = selectionModel.getSelectedCells();
			  TablePosition tablePosition = (TablePosition) selectedCells.get(0);
			  Object val = tablePosition.getTableColumn().getCellData(newValue);
			  System.out.println("Selected Value" + val);
		  }
	  }*/
	  
	  @FXML
	  private DatePicker datePicker;
	  
	  @FXML
	  private TextArea DataField;
	  
	  @FXML
	  public void getKunde(ActionEvent event) {
		  //DataField.setText(kundenTable.getSelectionModel().toString());
		  Kunde person = kundenTable.getSelectionModel().getSelectedItem();
		  if (person.equals(null)) {
			  DataField.setText("keinen Kunden ausgewählt");
		  } else {
			  int nr = person.getKundeid();
			  String vname = person.getvorname();
			  String nname = person.getnachname();
			  String email = person.getemail();
			  String tel = person.gettel();
			  
			  
			  
			  
			  String kd = "Nummer: " +nr+ ", Vorname: " +vname+ ", Nachname: " +nname+ ", email: " +email+ ", tel: " +tel;
			  
			  DataField.setText(kd);
					  
		  }
	  }
	  
	  @FXML
	  public void getAdresse(ActionEvent event) {
		  
		  Adresse adresse = adrTable.getSelectionModel().getSelectedItem();
		  if (adresse.equals(null)) {
			  DataField.setText("keine Adresse ausgewählt!");
		  }
		  else {
			  int nr = adresse.getadrid();
			  String ort = adresse.getadrort();
			  int ha = adresse.getadrhanr();
			  String strasse = adresse.getadrstrasse();
			  int plz = adresse.getadrplz();
			   String adr = "Nummer: " +nr+ ", Ort: " +ort+ ", Haus NR: " +ha+ ", Straße: " +strasse+ ", PLZ: " +plz;
			  DataField.setText(adr);
			  
		  }
	  }
	  
	  @FXML
	  public void getKFZ(ActionEvent event) {
		  //DataField.setText(kfzTable.ger);
		  KFZ kfz = kfzTable.getSelectionModel().getSelectedItem();
		  if (kfz.equals(null)) {
			  DataField.setText("kein Fahrzeug ausgewählz");
		  } else {
			  int nr = kfz.getkfzid();
			  String t = kfz.getkfzmarke();
			  String l = kfz.getkfzmodell();
			  String o = kfz.getkfzhu();
			  String i = kfz.getkfzhu();
			  String p = kfz.getkfzerstzul();
			  int km = kfz.getkfzkmstand();
			 // String kfz = "Nummer" +nr+ ", Ort: " +ort+ ", Haus NR: " +ha+ ", Straße: " +strasse+ ", PLZ: " +plz;
		  }
	  }
	  
	  @FXML
	  public void getDate(ActionEvent event) {
	  LocalDate myDate = datePicker.getValue();
	  DataField.setText(myDate.toString());
	  
	  }
	  
	 
	
	@FXML
	private void initialize() throws Exception {
		
		 
		
		try {
			//NIEMALS DOPPELTE FX:ID HABEN
			
		
		//String s = kundeVorname.getCellData(1);
		//System.out.println(s);
		//String Stmt = "UPDATE kunde SET VName = " +t+ "";
			//kfz onaction noch leer
			//kunde
		
			
			//TODO ausgewählten Kunden aus der aktuellen TableView in ein Textfeld eintragen
		ObservableList<Kunde> kdlist;
		kdlist = kundenTable.getSelectionModel().getSelectedItems();
		//TODO selbes wie in Zeile 435 	
		System.out.println(kdlist.size());
		
		
			// die Auswahl an Posten
		artikelBox.getItems().add("Reifen");
		artikelBox.getItems().add("Winterreifen");
		artikelBox.getItems().add("Rückspiegel");
		artikelBox.getItems().add("");
		
		
		//artikelTable wird mit Daten befüllt
 		artid.setCellValueFactory(cellData -> cellData.getValue().artidproperty().asObject());
		artab.setCellValueFactory(new PropertyValueFactory <Posten, Date>("artab"));
		artname.setCellValueFactory(cellData -> cellData.getValue().artnameproperty());
		artpreis.setCellValueFactory(cellData -> cellData.getValue().artpreisproperty().asObject());
		
		// kundenTable mit Werten befüllen
		kundeID.setCellValueFactory(cellData -> cellData.getValue().kundeidproperty().asObject());
		
		kundeVorname.setCellValueFactory(cellData -> cellData.getValue().vornameproperty());
		kundeVorname.setCellFactory(TextFieldTableCell.forTableColumn());
		kundeVorname.setOnEditCommit(cellData-> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setvorname(cellData.getNewValue());/*int rowNum = kundenTable.getSelectionModel().getSelectedIndex(); String t = kundeVorname.getCellData(rowNum);System.out.println(t);*/try {UpdateKunde();} catch (SQLException e) {e.printStackTrace();} }); //Datensatz überschreiben
		kundeNachname.setCellValueFactory(cellData -> cellData.getValue().nachnameproperty());
		kundeNachname.setOnEditCommit(cellData-> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setnachname(cellData.getNewValue()); int rowNum = kundenTable.getSelectionModel().getSelectedIndex(); String t = kundeVorname.getCellData(rowNum);System.out.println(t);try {UpdateKunde();} catch (SQLException e) {e.printStackTrace();} });
		kundeNachname.setCellFactory(TextFieldTableCell.forTableColumn());
		kundeEmail.setCellValueFactory(cellData -> cellData.getValue().emailproperty());
		kundeEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		kundeEmail.setOnEditCommit(cellData-> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setemail(cellData.getNewValue()); int rowNum = kundenTable.getSelectionModel().getSelectedIndex(); String t = kundeVorname.getCellData(rowNum);System.out.println(t);try {UpdateKunde();} catch (SQLException e) {e.printStackTrace();} });
		kundetel.setCellValueFactory(cellData -> cellData.getValue().telproperty());
		kundetel.setCellFactory(TextFieldTableCell.forTableColumn());
		kundetel.setOnEditCommit(cellData-> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).settel(cellData.getNewValue()); int rowNum = kundenTable.getSelectionModel().getSelectedIndex(); String t = kundeVorname.getCellData(rowNum);System.out.println(t);try {UpdateKunde();} catch (SQLException e) {e.printStackTrace();} });
		
		//adressenTable wird mit Daten befüllt
		adrNr.setCellValueFactory(cellData -> cellData.getValue().adridproperty().asObject());
		adrNr.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		adrOrt.setCellValueFactory(test -> test.getValue().adrortproperty());
		adrOrt.setCellFactory(TextFieldTableCell.forTableColumn());
		adrOrt.setOnEditCommit(cellData -> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setadrort(cellData.getNewValue()); try {UpdateAdresse();} catch (SQLException e) {e.printStackTrace();} });
		adrPlz.setCellValueFactory(cellData -> cellData.getValue().adrplzproperty().asObject());
		adrPlz.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		adrPlz.setOnEditCommit(cellData -> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setadrplz(cellData.getNewValue()); try {UpdateAdresse();} catch (SQLException e) {e.printStackTrace();} });
		adrStrasse.setCellValueFactory(cellData -> cellData.getValue().adrstrasseproperty());
		adrStrasse.setCellFactory(TextFieldTableCell.forTableColumn());
		adrStrasse.setOnEditCommit(cellData -> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setadrstrasse(cellData.getNewValue()); try {UpdateAdresse();} catch (SQLException e) {e.printStackTrace();} });
		adrHnr.setCellValueFactory(cellData -> cellData.getValue().adrhanrproperty().asObject());
		adrHnr.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		adrHnr.setOnEditCommit(cellData -> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setadrhanr(cellData.getNewValue()); try {UpdateAdresse();} catch (SQLException e) {e.printStackTrace();} });
		
		//kfzTable wird mit Daten befüllt
		kfzNr.setCellValueFactory(cellData -> cellData.getValue().kfzidproperty().asObject());
		kfzNr.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		kfzMarke.setCellValueFactory(test -> test.getValue().kfzmarkeproperty());
		kfzMarke.setCellFactory(TextFieldTableCell.forTableColumn());
		kfzMarke.setOnEditCommit(cellData -> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setkfzmarke(cellData.getNewValue()); try {UpdateKFZ();} catch (SQLException e) {e.printStackTrace();} });
		kfzModell.setCellValueFactory(cellData -> cellData.getValue().kfzmodellproperty());
		kfzModell.setCellFactory(TextFieldTableCell.forTableColumn());
		kfzModell.setOnEditCommit(cellData -> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setkfzmodell(cellData.getNewValue()); try {UpdateKFZ();} catch (SQLException e) {e.printStackTrace();} });
		kfzHubraum.setCellValueFactory(cellData -> cellData.getValue().kfzhuproperty());
		kfzHubraum.setCellFactory(TextFieldTableCell.forTableColumn());
		kfzHubraum.setOnEditCommit(cellData -> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setkfzhu(cellData.getNewValue()); try {UpdateKFZ();} catch (SQLException e) {e.printStackTrace();} });
		kfzKennZ.setCellValueFactory(cellData -> cellData.getValue().kfzkennzproperty());
		kfzKennZ.setCellFactory(TextFieldTableCell.forTableColumn());
		kfzKennZ.setOnEditCommit(cellData -> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setkfzkennz(cellData.getNewValue()); try {UpdateKFZ();} catch (SQLException e) {e.printStackTrace();} });
		kfzKmStand.setCellValueFactory(cellData -> cellData.getValue().kfzkmstandproperty().asObject());
		kfzKmStand.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		kfzKmStand.setOnEditCommit(cellData -> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setkfzkmstand(cellData.getNewValue()); try {UpdateKFZ();} catch (SQLException e) {e.printStackTrace();} });
		kfzErstZul.setCellValueFactory(cellData -> cellData.getValue().kfzerstzulproperty());
		kfzErstZul.setCellFactory(TextFieldTableCell.forTableColumn());
		kfzErstZul.setOnEditCommit(cellData -> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setkfzerstzul(cellData.getNewValue()); try {UpdateKFZ();} catch (SQLException e) {e.printStackTrace();} });
		
		//stammdatenTable wird mit Daten befüllt
		stammId.setCellValueFactory(cellData -> cellData.getValue().setammidproperty().asObject());
		stammId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		stammBlz.setCellValueFactory(test -> test.getValue().setammblzproperty().asObject());
		stammBlz.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		stammBlz.setOnEditCommit(cellData -> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setstammblz(cellData.getNewValue()); try {UpdateStamm();} catch (SQLException e) {e.printStackTrace();} });
		stammAb.setCellValueFactory(cellData -> cellData.getValue().setammabproperty());
		stammAb.setCellFactory(TextFieldTableCell.forTableColumn());
		stammAb.setOnEditCommit(cellData -> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setstammab(cellData.getNewValue()); try {UpdateStamm();} catch (SQLException e) {e.printStackTrace();} });
		stammZb.setCellValueFactory(cellData -> cellData.getValue().stammzbproperty());
		stammZb.setCellFactory(TextFieldTableCell.forTableColumn());
		stammZb.setOnEditCommit(cellData -> {cellData.getTableView().getItems().get(cellData.getTablePosition().getRow()).setstammzb(cellData.getNewValue()); try {UpdateStamm();} catch (SQLException e) {e.printStackTrace();} });
		
		
		//Kunde populateKunden
		con();
		changeGreen();
		ObservableList<Kunde> kdData = KundeDAO.searchKunden();
	
	populateKunden(kdData); 
	
	
	//Kunde Suchfilter
	FilteredList<Kunde> filteredData = new FilteredList<>(kdData, b->true);
	
	Filter.textProperty().addListener((observable, oldValue, newValue) ->{
		filteredData.setPredicate(Kunde ->{
		if (newValue == null|| newValue.isEmpty()) {
			return true;
		}
		
		String lowerCaseFilter = newValue.toLowerCase();
		if (Kunde.getvorname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
			return true;
		} else if (Kunde.getnachname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
			return true;
		}else if (Kunde.getemail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
			return true;
		}else if (Kunde.gettel().toLowerCase().indexOf(lowerCaseFilter) != -1) {
			return true;
		}
		else if (String.valueOf(Kunde.getKundeid()).indexOf(lowerCaseFilter) != -1) {
			return true;
		}
		
		
		else {
			return false;
		}
			
		
		});
		});
	
	SortedList<Kunde> sortedData = new SortedList<>(filteredData);
	
	sortedData.comparatorProperty().bind(kundenTable.comparatorProperty());
	kundenTable.setItems(sortedData);
		
		
		} catch (Exception e) {
			System.out.println("Fehler: " + e);
			throw e;
		}
		
		//adressen populate Adressen
		con();
		changeGreen();
		ObservableList<Adresse> adrData = AdresseDAO.searchAdressen();
	
		populateAdressen(adrData);
		
		//Adressen Suchfilter
		FilteredList<Adresse> filteredData = new FilteredList<>(adrData, b->true);
		
		Filter1.textProperty().addListener((observable, oldValue, newValue) ->{
			filteredData.setPredicate(Adresse ->{
			if (newValue == null|| newValue.isEmpty()) {
				return true;
			}
			
			String lowerCaseFilter = newValue.toLowerCase();
			if (String.valueOf(Adresse.getadrid()).indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if (Adresse.getadrort().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			}else if (String.valueOf(Adresse.getadrplz()).indexOf(lowerCaseFilter) != -1) {
				return true;
			}else if (Adresse.getadrstrasse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			
			}else if (String.valueOf(Adresse.getadrhanr()).indexOf(lowerCaseFilter) != -1) {
				return true;
			}
			
			
			else {
				return false;
			}
				
			
			});
			});
		
		SortedList<Adresse> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(adrTable.comparatorProperty());
		adrTable.setItems(sortedData);
			
			
			
		//KFZ populateKFZ
		con();
		changeGreen();
		ObservableList<KFZ> kfzData = KFZDAO.searchKFZs();
	
		populateKFZs(kfzData);
		
		
		//KFZ Suchfilter
		FilteredList<KFZ> filteredDatakfz = new FilteredList<>(kfzData, b->true);
		
		Filter1.textProperty().addListener((observable, oldValue, newValue) ->{
			filteredDatakfz.setPredicate(KFZ ->{
			if (newValue == null|| newValue.isEmpty()) {
				return true;
			}
			
			String lowerCaseFilter = newValue.toLowerCase();
			if (String.valueOf(KFZ.getkfzid()).indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if (KFZ.getkfzmarke().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if (KFZ.getkfzmodell().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if (KFZ.getkfzhu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if (KFZ.getkfzkennz().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			}
			else if (String.valueOf(KFZ.getkfzkmstand()).indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if (KFZ.getkfzerstzul().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			}else {
				return false;
			}
				
			
			});
			});
		
		SortedList<KFZ> sortedDatakfz = new SortedList<>(filteredDatakfz);
		
		sortedDatakfz.comparatorProperty().bind(kfzTable.comparatorProperty());
		kfzTable.setItems(sortedDatakfz);
			
		
		
	}
	
	//KundeListe wird als Daten von Zellen bereitgestellt
	private void  populateKunde (Kunde kd) throws ClassNotFoundException {
		ObservableList<Kunde> kdData = FXCollections.observableArrayList();
		kdData.add(kd);
		kundenTable.setItems(kdData);
	
	}
	
	//idk
	private void populateKunden (ObservableList<Kunde> kdData) throws ClassNotFoundException {
		kundenTable.setItems(kdData);
		
	}
	
	
	
	
	//
	//Adressen Controller Overview
	//
	
	//AdressenTable
	@FXML
	private TableView<Adresse> adrTable = new TableView<>();
	
	
	@FXML
	private TableColumn<Adresse , Integer> adrNr = new TableColumn<>();
	
	@FXML
	private TableColumn<Adresse, String> adrOrt = new TableColumn<>();
	
	@FXML
	private TableColumn<Adresse, Integer> adrPlz = new TableColumn<>();
	
	@FXML
	private TableColumn<Adresse, String> adrStrasse = new TableColumn<>();
	
	@FXML
	private TableColumn<Adresse, Integer> adrHnr = new TableColumn<>();
	
	@FXML
	private void searchAdressen (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		
		try {
			con();
			//DBUtil.dbConnect();
			changeGreen();
			ObservableList<Adresse> adrData = AdresseDAO.searchAdressen();  
		
			populateAdressen(adrData);
		
		} catch (SQLException e){
			System.out.println("Error bei DB");
			changeRed();
			throw e;
		}
	}
	
	private void populateAdressen (ObservableList<Adresse> adrData) throws ClassNotFoundException {
		adrTable.setItems(adrData);
		
	}
	
	@FXML
	private void UpdateAdresse() throws SQLException{
		int rowNum = adrTable.getSelectionModel().getSelectedIndex();
		
		int kdnr = rowNum++;
		
		String ort = adrOrt.getCellData(kdnr);
		int plz = adrPlz.getCellData(kdnr);
		String strasse = adrStrasse.getCellData(kdnr);
		int hn = adrHnr.getCellData(kdnr);
		
		
		
		int adrnr = adrNr.getCellData(kdnr);
		
		
		
		/*String stmt = "UPDATE adresse SET Ort = '" +ort+ 
				"' , PLZ = '" +plz+ 
				"' , Str = '" +strasse+ 
				"' , HsNr = '"+hn+ 
				"' WHERE AdrNr = " + test;
		DBUtil.dbExcequteUpdate(stmt);*/
		
		String url = "jdbc:mysql://localhost:3306/kfz_rechnung";
		String user = "root";
		String pass = "";
		
		final String prpstmt = "UPDATE adresse SET Ort = ? , PLZ = ?  , Str = ?  , HsNr = ? WHERE AdrNr = ?";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement prp = conn.prepareStatement(prpstmt);
			prp.setString(1, ort);
			prp.setInt(2, plz);
			prp.setString(3, strasse);
			prp.setInt(4, hn);
			prp.setInt(5, adrnr);
			int rows = prp.executeUpdate();
			System.out.println("betroffene Zeilen: "+rows);
		} catch (Exception e) {
			System.out.println("test");
		}
	}
	//
	//kfz controlleroverview
	//
	
	
	//kfz table
	@FXML
	private TableView<KFZ> kfzTable = new TableView<>();
	
	@FXML
	private TableColumn<KFZ , Integer> kfzNr = new TableColumn<>();
	
	@FXML
	private TableColumn<KFZ, String> kfzMarke = new TableColumn<>();
	
	@FXML
	private TableColumn<KFZ, String> kfzModell = new TableColumn<>();
	
	@FXML
	private TableColumn<KFZ, String> kfzHubraum = new TableColumn<>();
	
	@FXML
	private TableColumn<KFZ, String> kfzKennZ = new TableColumn<>();
	
	@FXML
	private TableColumn<KFZ, Integer> kfzKmStand = new TableColumn<>();
	
	@FXML
	private TableColumn<KFZ, String> kfzErstZul = new TableColumn<>();
	
	
	//Funktionen für kfz
	@FXML
	private void searchKFZs (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		try {
			DBUtil.dbConnect();
			changeGreen();
			ObservableList<KFZ> kfzData = KFZDAO.searchKFZs();
		
			populateKFZs(kfzData);
		
		} catch (SQLException e){
			System.out.println("Error bei DB");
			changeRed();
			throw e;
		}
	}
	
	private void populateKFZs (ObservableList<KFZ> kfzData) throws ClassNotFoundException {
		kfzTable.setItems(kfzData);
		
	}
	
	@FXML
	private void UpdateKFZ () throws SQLException{
		int rowNum = kfzTable.getSelectionModel().getSelectedIndex();
		
		int kdnr = rowNum++;
		
		String marke = kfzMarke.getCellData(kdnr);
		String modell = kfzModell.getCellData(kdnr);
		String hu = kfzHubraum.getCellData(kdnr);
		String kennz = kfzKennZ.getCellData(kdnr);
		int kmstand = kfzKmStand.getCellData(kdnr);
		String erstzul = kfzErstZul.getCellData(kdnr);
		
		int kfznr = kfzNr.getCellData(kdnr);
		
		/*String stmt = "UPDATE kfz SET  Marke = '" +marke+ 
				"' , Modell = '" +modell+ 
				"' , HU = '" +hu+ 
				"' , KennZ = '"+kennz+ 
				"' , kmStand = '"+kmstand+
				"' , ErstZul = '"+erstzul+
				"' WHERE FI_ID = " + test;
		DBUtil.dbExcequteUpdate(stmt);*/
		
		
		String url = "jdbc:mysql://localhost:3306/kfz_rechnung";
		String user = "root";
		String pass = "";
		
		final String prpstmt = "UPDATE kfz SET Marke = ? , Modell = ?  , HU = ?  , KennZ = ?, kmStand = ?, ErstZul = ? WHERE FI_ID = ?";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement prp = conn.prepareStatement(prpstmt);
			prp.setString(1, marke);
			prp.setString(2, modell);
			prp.setString(3, hu);
			prp.setString(4, kennz);
			prp.setInt(5, kmstand);
			prp.setString(6, erstzul);
			prp.setInt(7, kfznr);
			int rows = prp.executeUpdate();
			System.out.println("betroffene Zeilen: "+rows);
		} catch (Exception e) {
			System.out.println("test");
		}
	}
	
	
	
	//Stammdaten Controller Overview
	@FXML
	private TableView<Stammdaten> stammTable = new TableView<>();
	
	@FXML
	private TableColumn<Stammdaten , Integer> stammId = new TableColumn<>();
	
	@FXML
	private TableColumn<Stammdaten, Integer> stammBlz = new TableColumn<>();
	
	@FXML
	private TableColumn<Stammdaten, String> stammAb = new TableColumn<>();
	
	@FXML
	private TableColumn<Stammdaten, String> stammZb = new TableColumn<>();
	
	
	//Funktionen für Stammdaten
	//TODO
	// Stammdaten werden keine neue Insert View bekommen weil das Anlegen von neuen Stammdaten keinen Sinn macht 
		@FXML
		private void searchStammdaten (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
			try {
				DBUtil.dbConnect();
				changeGreen();
				ObservableList<Stammdaten> stammData = StammdatenDAO.searchStammdaten();   //searchStammdaten();
			
				populateStammdaten(stammData);
			
			} catch (SQLException e){
				System.out.println("Error bei DB");
				changeRed();
				throw e;
			}
		}
		
		private void populateStammdaten (ObservableList<Stammdaten> stammData) throws ClassNotFoundException {
			stammTable.setItems(stammData);	
		}
		
		@FXML
		private void UpdateStamm () throws SQLException {
			int rowNum = stammTable.getSelectionModel().getSelectedIndex();
			
			int kdnr = rowNum++;
			
			int blz = stammBlz.getCellData(kdnr);
			String ab = stammAb.getCellData(kdnr);
			String zb = stammZb.getCellData(kdnr);
			
			int test = stammId.getCellData(kdnr);
			
			
			
			/*String stmt = "UPDATE stammdaten SET  BLZ = '" +blz+ 
					"' , Abgesang = '" +ab+ 
					"' , Zahlungsbedingung = '" +zb+ 
					"' WHERE Stamm_ID = " + test;
			DBUtil.dbExcequteUpdate(stmt);*/
			
			
			String url = "jdbc:mysql://localhost:3306/kfz_rechnung";
			String user = "root";
			String pass = "";
			
			final String prpstmt = "UPDATE stammdaten SET BLZ = ? , Abgesang = ?  , Zahlungsbedingung = ? WHERE Stamm_ID = ?";
			try {
				Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement prp = conn.prepareStatement(prpstmt);
				prp.setInt(1, blz);
				prp.setString(2, ab);
				prp.setString(3, zb);
				prp.setInt(4, test);
				int rows = prp.executeUpdate();
				System.out.println("betroffene Zeilen: "+rows);
			} catch (Exception e) {
				System.out.println("test");
			}
		}
	
	
	
	//DB View stuff
	@FXML
	private TextArea dbArea;
	
	public void setDbTextToArea() {
		
	}
	
	
	@FXML
	private Button posten;
	
	
	
	
	@FXML
	void changePosten(ActionEvent event) throws IOException{
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("primary3.fxml"));
			//Pane root = FXMLLoader.load(getClass().getResource("primary3.fxml"));
			Parent root1;
			
			root1 = (Parent) fxmlLoader.load();
			//Scene root1;
			Stage stage = new Stage();
			stage.setTitle("test");
			//root1.setRoot("primary3.fxml");
			stage.setScene(new Scene(root1));
			stage.show();
			
			//MainExtender.setRoot("primary3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private Circle status;
	
	@FXML  void con() {
		DBUtil.dbConnect();
		changeGreen();
		dbArea.setText("Datenbankverbindung wurde erfolgreich hergestellt");
	}
	
	@FXML
	private void dc() {
		DBUtil.dbDisconnect();
		changeRed();
		dbArea.setText("Datenbankverbindung wurde erfolgreich unterbrochen");
	}
	
    public void changeGreen() {
    	//status = new Circle();
    	status.setFill(Paint.valueOf("#7FFF00"));
    }
    
    public void changeRed() {
    	status.setFill(Paint.valueOf("#FF0000"));
    }
	
    
    
	
    
    /*
	private void zeigeAlleKd (Kunde kd) throws ClassNotFoundException {
    	ObservableList<Kunde> kdData = FXCollections.observableArrayList();
    	kdData.add(kd);
    	kundenTable.setItems(kdData);
    }
	*/
	
	
	
	
    
   
    
    @FXML
	private void manageBenutzeKdBtn () throws IOException {
    	
    	
    }
    
    @FXML
	private void kundeAnlegen () throws IOException {
    	MainExtender.setRoot("KundeSetup");
    	
    }
    
    
    
    @FXML
	private void fahrzeugAnlegen () throws IOException {
    	MainExtender.setRoot("KFZSetup");
    	
    }
    
    @FXML
	private void adresseAnlegen () throws IOException {
    	MainExtender.setRoot("AdresseSetup");
    	
    }
    
    @FXML
	private void rechnungAnlegen () throws IOException {
    	
    	
    }
    
    @FXML
	private void stammdatenAnlegen() throws IOException {
    	
    	
    }
    
    
}
