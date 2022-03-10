package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Tratamento;



public class TratamentoDAO extends BaseDAO {
	
	public static List<Tratamento> selectTratamentos() {
		final String sql = "select * from tratamento";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(sql);) {
			List<Tratamento> tratamentos = new ArrayList<>();
			while (rs.next()) {
				tratamentos.add(resultsetToTratamento(rs));
			}
			return tratamentos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Tratamento selectTratamentoById(Integer id) {
		final String sql = "select * from tratamento where id_trat=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Tratamento tratamento = null;
			if (rs.next()) {
				tratamento = resultsetToTratamento(rs);
			}
			rs.close();
			return tratamento;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	private static Tratamento resultsetToTratamento(ResultSet rs) throws SQLException {
		Tratamento t = new Tratamento();

		t.setId_trat(rs.getInt("id_tart"));
		t.setData_ini(rs.getString("dat_ini"));
		t.setData_fin(rs.getString("dat_fin"));
//      a.setCliente(ClienteDAO.selectClienteById(rs.getInt("id_cli")));
		return t;
	}

	public static void main(String[] args) {
		System.out.println(selectTratamentos());
		System.out.println(selectTratamentoById(1));
	}
	
}
