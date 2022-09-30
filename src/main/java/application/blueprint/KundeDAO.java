package application.blueprint;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import application.blueprint.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KundeDAO {

	
	//String empId = kdID
	public static Kunde searchKunde (String kdID) throws SQLException, ClassNotFoundException{
		String selectStmt = "SELECT * FROM kunde WHERE KDNr="+kdID;
		
		try {
			ResultSet rsKd = DBUtil.dbExcecuteQuery(selectStmt);
			
			Kunde kunde = getKundeFromResultSet(rsKd);
			
			return kunde;
		}catch (SQLException e) {
			System.out.println("auf der Suche nach dem Kunden mit der ID" + kdID + "ist ein Fehler aufgetreten: " + e);
			throw e;
		}
		
		
	}

	public static Kunde getKundeFromResultSet(ResultSet rs)throws SQLException {
		
		Kunde kd = null;
		
		if (rs.next()) {
			kd = new Kunde();
			kd.setkundeid(rs.getInt("KDNr"));
			kd.setvorname(rs.getString("VName"));
			kd.setnachname(rs.getString("NName"));
			kd.setemail(rs.getString("Email"));
			kd.settel(rs.getString("Tel"));
			
			
		}
		
		return kd;
	}
	
	public static ObservableList<Kunde> searchKunden () throws SQLException, ClassNotFoundException{
		String selectStmt = "SELECT * FROM kunde";
		try {
			ResultSet rskd = DBUtil.dbExcecuteQuery(selectStmt);
			
			//Wert änderung
			ObservableList<Kunde> kdList = getKundeList(rskd);
			
			
			return kdList;
		} catch(SQLException e) {
			
			System.out.println("SQL select operation konnte nicht vollendet werden: " + e);
			throw e;
		}
		
	}
	
	//instanzierung von Kunde überprüfung
	private static ObservableList<Kunde> getKundeList(ResultSet rs) throws SQLException, ClassNotFoundException {
		
		ObservableList<Kunde> kdList = FXCollections.observableArrayList();
		
		while (rs.next()) {
			Kunde kd = new Kunde();
			//kd.setkundeid(rs.getInt("KDNr"));
			//kd.setnachname(rs.getString("NName"));
			//kd.setvorname(rs.getString("Tel"));
			kd.setkundeid(rs.getInt("KDNr"));
			kd.setnachname(rs.getString("NName"));
			kd.setvorname(rs.getString("VName"));
			kd.setemail(rs.getString("email"));
			kd.settel(rs.getString("Tel"));
			
			//kd.setemail(rs.getString("Email"));
			//kd.settel(rs.getString("Tel"));
			
			kdList.add(kd);
		}
		return kdList;
	}
	
	/*
	public static void updateKdEmail (String kdID, String kdEmail) throws SQLException, ClassNotFoundException {
		
		String updateStmt = 
				"BEGIN\n" +
						" Update kunde\n" +
						"  SET EMAIL = '" + kdEmail + "'\n" +
						"  WHERE KDNr = " + kdID + ";\n" +
						"END;";
	
	try {
		DBUtil.dbExcequteUpdate(updateStmt);
	} catch (SQLException e) {
		System.out.print("Fehler während des Updates der Datenbank: " + e);
		throw e;
	}*/
	
	/*public static void deleteKdWithID (String kdID) throws SQLException, ClassNotFoundException {
		String updateStmt = 
				"BEGIN\n" +
						" DELETE FROM"
	}*/
//}

	
	//unfertig später bearbeiten
	public static void insertKd (String vorName, String nachName, String email, String tel) throws SQLException, ClassNotFoundException{
		String updateStmt = "INSERT INTO kunde (KDNr, VName, NName, email, Tel) VALUES (null , '" + vorName + "','" + nachName + "','" + email +"','" + tel +"');";
		try {
			DBUtil.dbExcequteUpdate(updateStmt);
		} catch (SQLException e) {
			System.out.print("Fehler während der DELETE Operation: " +e);
			throw e;
		}
		
		
									
	}
	
	
	
}
