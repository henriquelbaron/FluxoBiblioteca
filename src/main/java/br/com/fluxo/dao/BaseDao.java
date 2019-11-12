package br.com.fluxo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.fluxo.domain.Fluxo;

public class BaseDao {
	private Connection con;
	private PreparedStatement statement;
	private ResultSet rs;
	private Fluxo fluxo;

	public BaseDao() {
	}

	public boolean inserir(Object objeto) throws Exception {
		fluxo = (Fluxo) objeto;
		String sql = "Insert Into fluxo (timeStamp, pessoasDentro) values(?,?)";
		try {
			con = SessionFactory.getConnection();
			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1, new Date(fluxo.getTimeStamp().getTime()));
			statement.setInt(2, fluxo.getPessoasDentro());
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			System.out.println("Erro ao inserir fluxo " + e.getMessage());
			e.printStackTrace();
		} finally {
			SessionFactory.closeConnection(con, statement, rs);
		}
		return false;
	}
}
