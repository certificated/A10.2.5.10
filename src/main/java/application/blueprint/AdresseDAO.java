package application.blueprint;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdresseDAO {

	public static ObservableList<Adresse> searchAdressen () throws SQLException, ClassNotFoundException{
		String selectStmt = "SELECT * FROM adresse";
		try {
			ResultSet adrkd = DBUtil.dbExcecuteQuery(selectStmt);
			
			//Wert änderung
			ObservableList<Adresse> adrList = getAdresseList(adrkd);
			
			
			return adrList;
		} catch(SQLException e) {
			
			System.out.println("SQL select operation konnte nicht vollendet werden: " + e);
			throw e;
		}
		
	}
	
	//instanzierung von Kunde überprüfung
		private static ObservableList<Adresse> getAdresseList(ResultSet rs) throws SQLException, ClassNotFoundException {
			
			ObservableList<Adresse> adrList = FXCollections.observableArrayList();
			
			while (rs.next()) {
				Adresse adr = new Adresse();
				
				adr.setadrid(rs.getInt("AdrNr"));
				adr.setadrort(rs.getString("Ort"));
				adr.setadrplz(rs.getInt("PLZ"));
				adr.setadrstrasse(rs.getString("Str"));
				adr.setadrhanr(rs.getInt("HsNr"));
				
				adrList.add(adr);
			}
			return adrList;
		}
		
		
		public static void insertAdresse (String ort, Integer plz, String strasse, Integer hr) throws SQLException, ClassNotFoundException{
			String updateStmt = "INSERT INTO adresse (AdrNr, Ort, PLZ, Str, HsNr) VALUES (null,'" + ort + "','" + plz +"','" + strasse +"','" + hr  +"');";
			try {
				DBUtil.dbExcequteUpdate(updateStmt);
			} catch (SQLException e) {
				System.out.print("Fehler während der DELETE Operation: " +e);
				throw e;
			}
}}
