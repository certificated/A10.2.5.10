package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.blueprint.DBUtil;

public class AufschlagDAO {

	public static void createArtikel( ) throws SQLException {
		String Stmt = "INSERT INTO artikel VALUES (?,?,?,?,?);";
		
		Connection conn = DBUtil.conn;
		PreparedStatement prpstmt = conn.prepareStatement(Stmt);
	}
}
