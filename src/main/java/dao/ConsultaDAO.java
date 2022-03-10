package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Consulta;



public class ConsultaDAO extends BaseDAO {
	
	public static List<Consulta> selectConsulta() {
		final String sql = "select * from consulta";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(sql);) {
			List<Consulta> consultas = new ArrayList<>();
			while (rs.next()) {
				consultas.add(resultsetToConsulta(rs));
			}
			return consultas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Consulta selectConsultaById(Integer id) {
		final String sql = "select * from consulta where id_con=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Consulta consulta = null;
			if (rs.next()) {
				consulta = resultsetToConsulta(rs);
			}
			rs.close();
			return consulta;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	private static Consulta resultsetToConsulta(ResultSet rs) throws SQLException {
		Consulta c = new Consulta();

		c.setId_con(rs.getInt("id_con"));
		c.setDat_con(rs.getString("dat_con"));
		c.setHistorico(rs.getString("historico"));

//      a.setCliente(ClienteDAO.selectClienteById(rs.getInt("id_cli")));
		return c;
	}

	public static void main(String[] args) {
		System.out.println(selectConsulta());
		System.out.println(selectConsultaById(3));
	}
	
}
