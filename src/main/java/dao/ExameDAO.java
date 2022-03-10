package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Exame;



public class ExameDAO extends BaseDAO {
	
	public static List<Exame> selectExames() {
		final String sql = "select * from exame";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(sql);) {
			List<Exame> exames = new ArrayList<>();
			while (rs.next()) {
				exames.add(resultsetToExame(rs));
			}
			return exames;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Exame selectExameById(Integer id) {
		final String sql = "select * from exame where id_exame=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Exame exame = null;
			if (rs.next()) {
				exame = resultsetToExame(rs);
			}
			rs.close();
			return exame;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	private static Exame resultsetToExame(ResultSet rs) throws SQLException {
		Exame e = new Exame();

		e.setId_exame(rs.getInt("id_exame"));
		e.setDes_exame(rs.getString("des_exame"));
//      a.setCliente(ClienteDAO.selectClienteById(rs.getInt("id_cli")));
		return e;
	}

	public static void main(String[] args) {
		System.out.println(selectExames());
		System.out.println(selectExameById(1));
	}
	
}
