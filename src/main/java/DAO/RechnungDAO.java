package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import Class.Kunde;
import Class.Rechnung;
import application.blueprint.DBUtil;

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
		
		
	
}
