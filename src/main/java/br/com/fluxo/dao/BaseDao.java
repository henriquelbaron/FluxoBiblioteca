package br.com.fluxo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fluxo.domain.Fluxo;
import br.com.fluxo.domain.FluxoDTO;
import br.com.fluxo.util.DateUtils;

public class BaseDao {
	private Connection con;
	private PreparedStatement statement;
	private ResultSet rs;
	private Fluxo fluxo;

	public BaseDao() {
	}

	public boolean inserir(Object objeto) throws Exception {
		fluxo = (Fluxo) objeto;
		String sql = "Insert Into fluxo (tempo, direcao) values(?,?)";
		try {
			con = SessionFactory.getConnection();
			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setTimestamp(1, new java.sql.Timestamp(fluxo.getTimeStamp().getTime()));
			statement.setBoolean(2, fluxo.isDirecao());
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

	public List<FluxoDTO> getFluxo(Date data, Date data2) {
		String sql = "select count(id) as quantidade, direcao  from fluxo where tempo between (?) and (?) group by direcao";
		try {
			con = SessionFactory.getConnection();
			statement = con.prepareStatement(sql);
			statement.setTimestamp(1, new java.sql.Timestamp(data.getTime()));
			statement.setTimestamp(2, new java.sql.Timestamp(data2.getTime()));
			rs = statement.executeQuery();
			List<FluxoDTO> fluxos = new ArrayList<>();
			while (rs.next()) {
				fluxos.add(new FluxoDTO(rs.getBoolean("direcao"), rs.getLong("quantidade"), data));
			}
			return fluxos;
		} catch (Exception e) {
			System.out.println("Erro ao inserir fluxo " + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			SessionFactory.closeConnection(con, statement, rs);
		}
	}

	public static void main(String[] args) {
		BaseDao dao = new BaseDao();
		Date agora = DateUtils.stringToDate("18/11/2019", "00:00");
		Date amanha = DateUtils.addDiaData(agora, 2);
		List<FluxoDTO> fluxos = dao.getFluxo(agora, amanha);

		for (FluxoDTO fluxoDTO : fluxos) {
			System.out.println(fluxoDTO.getQuantidade() + " " + fluxoDTO.isDirecao());
		}
	}

}
