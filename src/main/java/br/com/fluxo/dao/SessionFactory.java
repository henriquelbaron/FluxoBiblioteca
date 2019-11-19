package br.com.fluxo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionFactory {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost/arduino", "root", "");
	}

	public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			conn.close();
			ps.close();
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao fechar conexao " + ex.getMessage());
		}
	}
}