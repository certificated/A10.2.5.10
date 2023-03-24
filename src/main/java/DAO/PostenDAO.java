package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import Class.Posten;
import application.blueprint.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PostenDAO {

	public static Posten searchPosten (String artID) throws SQLException, ClassNotFoundException{
		String selectStmt = "SELECT * FROM artikel WHERE artikelNr = " + artID;//+artID;
		
		try {
			ResultSet rsKd = DBUtil.dbExcecuteQuery(selectStmt);
			
			Posten posten = getPostenFromResultSet(rsKd);
			
			return posten;
		}catch (SQLException e) {
			System.out.println("auf der Suche nach dem Kunden mit der ID" + artID + "ist ein Fehler aufgetreten: " + e);
			throw e;
		}
		
		
	}

	public static Posten getPostenFromResultSet(ResultSet rs)throws SQLException {
		
		Posten ps = null;
		
		if (rs.next()) {
			ps = new Posten();
			ps.setartid(rs.getInt("artikelNr"));
			ps.setartname(rs.getString("artikelname"));
			ps.setartbeschreibung(rs.getString("artikelEKpreis"));
			//ps.setartab(rs.getDate("datum"));
			ps.setartpreis(rs.getInt("anzahl"));
			
			
		}
		
		return ps;
	}
	//List die den Posten mit einzelnen Items befüllt
	public static ObservableList<Posten> searchPosten () throws SQLException, ClassNotFoundException{
		String selectStmt = "SELECT * FROM artikel";
		try {
			ResultSet rskd = DBUtil.dbExcecuteQuery(selectStmt);
			
			//Wert änderung
			ObservableList<Posten> psList = getPostenList(rskd);
			
			
			return psList;
		} catch(SQLException e) {
			
			System.out.println("SQL select operation konnte nicht vollendet werden: " + e);
			throw e;
		}
		
	}
	
	//instanzierung von Kunde überprüfung
	private static ObservableList<Posten> getPostenList(ResultSet rs) throws SQLException, ClassNotFoundException {
		
		ObservableList<Posten> psList = FXCollections.observableArrayList(); 
		
		while (rs.next()) {
			Posten ps = new Posten();
			//kd.setkundeid(rs.getInt("KDNr"));
			//kd.setnachname(rs.getString("NName"));
			//kd.setvorname(rs.getString(""));
			ps.setartid(rs.getInt("artikelNr"));
			ps.setartname(rs.getString("artikelName"));
			ps.setartbeschreibung(rs.getString("artikelEKpreis"));
			ps.setartbeschreibung(rs.getString("artikelEKpreis"));
			//ps.setartab(rs.getDate("datum"));
			ps.setartpreis(rs.getInt("anzahl"));
			//
			//kd.setemail(rs.getString("Email"));
			//kd.settel(rs.getString("Tel"));
			
			psList.add(ps);//
			
		}
		return psList;
}}
