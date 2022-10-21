package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import application.blueprint.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class rechnungKundeDAO {
    public static void kundeEingabe (String vornametextfield, String nachnametextfield, String telefontextfield) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO kunde (kundennummer, vorname, nachname, telefonnummer) VALUES (null, '"+vornametextfield+"', '"+nachnametextfield+"', '"+telefontextfield+"');";

        try {
            DBUtil.dbExcequteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Fehler beim hinzufügen eines neuen Kunden: " + e);
            throw e;
        }
    }

    public static void adresseEingabe (String Strasse, int Hausnummer, String Ort, int Plz) throws SQLException, ClassNotFoundException {
        String updateStmt = "BEGIN\n" + "INSERT INTO adresse\n" + "(adresseLNR, strasse, hausnummer, ort, plz)\n" + "VALUES\n" + "(null , '"+Strasse+"', '"+Hausnummer+"', '"+Ort+"', '"+Plz+"');\n";

        try {
            DBUtil.dbExcequteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Fehler beim hinzufügen einer neuen Adresse: " + e);
            throw e;
        }
    }

    public static void connectToDatabase() {

    }
}