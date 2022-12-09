package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		PreparedStatement prepStmtRn = DBUtil.con.prepareStatement(renummer);
		
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
			
			PreparedStatement jj  = DBUtil.con.prepareStatement(artid);
				
		}
		
	}		
		
	
}
