package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import Class.Kunde;
import Class.Rechnung;
import application.blueprint.DBUtil;
import controller.PrimaryController;

public class RechnungDAO {

	public static Rechnung searchRechnung (String reID) throws SQLException, ClassNotFoundException{
		String selectStmt = "SELECT * FROM kunde WHERE KDNr="+reID;
		
		try {
			ResultSet rsKd = DBUtil.dbExcecuteQuery(selectStmt);
			
			Rechnung rechnung = getRechnungFromResultSet(rsKd);
			
			return rechnung;
		}catch (SQLException e) {
			System.out.println("auf der Suche nach dem Kunden mit der ID" + reID + "ist ein Fehler aufgetreten: " + e);
			throw e;
		}
	}
		
	
	public static Rechnung getRechnungFromResultSet(ResultSet rs)throws SQLException {
			
			Rechnung re = null;
			
			if (rs.next()) {
				re = new Rechnung();
				re.setrechnungid(rs.getInt("ReNr"));
				re.setfiid(rs.getInt("FI_ID"));
				re.setkdid(rs.getInt("KDNr"));
				re.setadrid(rs.getInt("AdrNr"));
				re.setstmmid(rs.getInt("Stamm_ID"));
				
				
			}
			
			return re;
		}
	
	public static void getReNr (int kdid, int adrnr, int kfznr) throws SQLException, ClassNotFoundException
	{
		String renummer = "SELECT ReNr FROM rechnung WHERE KDNr = ?  AND AdrNr = ? AND FI_ID = ?;";
		PreparedStatement prepStmtRn = DBUtil.conn.prepareStatement(renummer);
		
		prepStmtRn.setInt(1, kdid);
		prepStmtRn.setInt(2, adrnr);
		prepStmtRn.setInt(3, kfznr);
		ResultSet resultat = prepStmtRn.executeQuery();
		
		if(resultat.next()) {
			System.out.println(resultat.getString("ReNr"));
			//Rechnungsnummer.setText("Rechnungsnummer: " + resultat.getString("ReNr")); //dieser Befehl soll im Controller bleiben
			PrimaryController.Rin = resultat.getString("ReNr");
			System.out.println(PrimaryController.Rin);
			
			
			String artid = "SELECT artID FROM artikel WHERE artikelname = ?;";
			
			PreparedStatement jj  = DBUtil.conn.prepareStatement(artid);
				
		}
		
	}		
		
	
	public static void reNr(String kdid, String adrid, String kfzid, LocalDate date) throws SQLException {
		  String tst = "SET FOREIGN_KEY_CHECKS=0;";
		  String prpstmt = "INSERT INTO rechnung (KDNr, AdrNr, FI_ID, Stamm_ID, Datum) VALUES (?, ?, ?, ?, ?);";
		  String uu = "SET FOREIGN_KEY_CHECKS=1;";
		  
		    String url = "jdbc:mysql://localhost:3306/kfz_rechnung";
			String user = "root";
			String pass = "";
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement aa = conn.prepareStatement(tst);
			PreparedStatement prp = conn.prepareStatement(prpstmt);
			PreparedStatement kk = conn.prepareStatement(uu);
			
			//die Variablen die für das prepared statement eingesetzt werden
			prp.setString(1, kdid);
			prp.setString(2, adrid);
			prp.setString(3, kfzid);
			prp.setString(4, "4");
			prp.setObject(5, date);
		  
			int test = aa.executeUpdate();
			int rows = prp.executeUpdate();
			int ll = kk.executeUpdate();
			
			System.out.println("teste " + test);
			System.out.println("betroffene Zeilen: "+ rows);
			System.out.println("ok " + ll);
	}
	
	public static void getrennr (String kdid, String adrid, String kfzid) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/kfz_rechnung";
		String user = "root";
		String pass = "";
		
		
		String renummer = "SELECT ReNr FROM rechnung WHERE KDNr = ?  AND AdrNr = ? AND FI_ID = ?;";
		
		Connection conn = DriverManager.getConnection(url, user, pass);
		PreparedStatement prepStmtRn = conn.prepareStatement(renummer);
		
		prepStmtRn.setString(1, kdid);
		prepStmtRn.setString(2, adrid);
		prepStmtRn.setString(3, kfzid);
		ResultSet resultat = prepStmtRn.executeQuery();
		
		
		if(resultat.next()) {
			System.out.println(resultat.getString("ReNr"));
			// unterer Befehl allein soll bleiben
			//Rechnungsnummer.setText("Rechnungsnummer: " + resultat.getString("ReNr"));
			PrimaryController.Rin = resultat.getString("ReNr");
			System.out.println(PrimaryController.Rin);
	}
		
		String artid = "SELECT artID FROM artikel WHERE artikelname = ?;";
		
		
		PreparedStatement jj  = conn.prepareStatement(artid);
		/*
		PreparedStatement jj = conn.prepareStatement(artid);
		jj.setString(1, "test");*/
	}
}
