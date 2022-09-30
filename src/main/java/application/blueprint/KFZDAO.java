package application.blueprint;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KFZDAO {

	public static ObservableList<KFZ> searchKFZs() throws SQLException, ClassNotFoundException{
		String selectStmt = "SELECT * FROM kfz";
		try {
			ResultSet kfzkd = DBUtil.dbExcecuteQuery(selectStmt);
			
			//Wert änderung
			ObservableList<KFZ> kfzList = getkfzList(kfzkd);
			
			
			return kfzList;
		} catch(SQLException e) {
			
			System.out.println("SQL select operation konnte nicht vollendet werden: " + e);
			throw e;
		}
		
	}
	
	
		private static ObservableList<KFZ> getkfzList(ResultSet rs) throws SQLException, ClassNotFoundException {
			
			ObservableList<KFZ> kfzList = FXCollections.observableArrayList();
			
			while (rs.next()) {
				KFZ kfz = new KFZ();
				
				kfz.setkfzid(rs.getInt("FI_ID"));
				kfz.setkfzmarke(rs.getString("Marke"));
				kfz.setkfzmodell(rs.getString("Modell"));
				kfz.setkfzhu(rs.getString("HU"));
				kfz.setkfzkennz(rs.getString("KennZ"));
				kfz.setkfzkmstand(rs.getInt("kmStand"));
				kfz.setkfzerstzul(rs.getString("ErstZul"));
				
				kfzList.add(kfz);
			}
			return kfzList;
		}
		
		public static void insertKFZ (Integer KFZnr, String marke, String modell, String hu, String kennz, Integer kmstand, String erstzul) throws SQLException, ClassNotFoundException{
			String updateStmt = "INSERT INTO kfz (FI_ID, Marke, Modell, HU, KennZ, kmStand, ErstZul) VALUES ('" + KFZnr + "','" + marke + "','" + modell +"','" + hu +"','" + kennz +"','" + kmstand +"','"+ erstzul +"');";
			try {
				DBUtil.dbExcequteUpdate(updateStmt);
			} catch (SQLException e) {
				System.out.print("Fehler während der DELETE Operation: " +e);
				throw e;
			}
			
			
										
		}
}


