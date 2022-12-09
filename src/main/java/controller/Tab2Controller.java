package controller;

import java.io.IOException;

import controller.PrimaryController;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class Tab2Controller {

	@FXML
	private Button nRechnungSelect;
	
	@FXML
	private Button nRechnungInsert;
	
	/**
	 * erstellt ein neues Fenster welches das vorherige Fenster während der Laufzeit deaktiviert
	 * @throws IOException
	 */
	@FXML
	private void rechnungSelect() throws IOException {
		//MainExtender.setRoot("Artikel");
		 Stage test = new Stage();
		 //Scene scene1 = new Scene();
		 test.initModality(Modality.APPLICATION_MODAL);
		 //scene1.setRoot(MainExtender.loadFXML("Artikel.fxml"));
		 //test.setScene();
		 test.showAndWait();
	}
	/**
	 * wechselt zur Artikel-View
	 * @throws IOException
	 */
	 @FXML
	  public void Rechnung() throws IOException {
	    	MainExtender.setRoot("Artikel");
	    	//PostenController.rechnummer();
	    }
	 
	
	
	/*@FXML
	  TabPane tabPane = new TabPane();
	  
	  Tab tab1 = new Tab();
	  
	  Tab tab2 = new Tab();
	  
	  Tab tab3 = new Tab();
	  */
	 /**
	  * wechselt zur vorherigen View 
	  * @throws IOException
	  */
	 @FXML
	   public void backTo2() throws IOException {
		  
			//PrimaryController.tabPane.getSelectionModel().select(PrimaryController.tab3);
		//PrimaryController.tab2();
		  
		  
	  }
	
	
}
