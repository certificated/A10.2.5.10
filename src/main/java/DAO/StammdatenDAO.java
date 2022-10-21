package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import Class.Stammdaten;
import application.blueprint.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StammdatenDAO {

	public static ObservableList<Stammdaten> searchStammdaten () throws SQLException, ClassNotFoundException{
		String selectStmt = "SELECT * FROM stammdaten";
		try {
			ResultSet rssd = DBUtil.dbExcecuteQuery(selectStmt);
			
			//Wert änderung
			ObservableList<Stammdaten> sdList = getStammdatenList(rssd);
			
			
			return sdList;
		} catch(SQLException e) {
			
			System.out.println("SQL select operation konnte nicht vollendet werden: " + e);
			throw e;
		}
		
	}
	
	//instanzierung von Kunde überprüfung
		private static ObservableList<Stammdaten> getStammdatenList(ResultSet rs) throws SQLException, ClassNotFoundException {
			
			ObservableList<Stammdaten> sdList = FXCollections.observableArrayList();
			
			while (rs.next()) {
				Stammdaten sd = new Stammdaten();
				
				sd.setstammid(rs.getInt("Stamm_ID"));
				sd.setstammblz(rs.getInt("BLZ"));
				sd.setstammab(rs.getString("Abgesang"));
				sd.setstammzb(rs.getString("Zahlungsbedingung"));
				
				sdList.add(sd);
			}
			return sdList;
		}
}
