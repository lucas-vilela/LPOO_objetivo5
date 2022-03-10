package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Especie;

public class EspecieDAO extends BaseDAO {

	public static List<Especie> selectEspecies() {
		final String sql = "select * from especie";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(sql);) {
			List<Especie> especies = new ArrayList<>();
			while (rs.next()) {
				especies.add(resultsetToEspecie(rs));
			}
			return especies;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Especie selectEspecieById(Integer id) {
		final String sql = "select * from especie where id_esp=?";
		try (
				Connection conn = getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Especie especie = null;
			if (rs.next()) {
				especie = resultsetToEspecie(rs);
			}
			rs.close();
			return especie;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	private static Especie resultsetToEspecie(ResultSet rs) throws SQLException {
		Especie e = new Especie();

		e.setId_esp(rs.getInt("id_esp"));
		e.setNom_esp(rs.getString("nom_esp"));
		e.setAnimais(AnimalDAO.selectAnimaisByEspecie(rs.getInt("id_esp")));

		return e;
	}

//	public static void main(String[] args) {
//		System.out.println(selectEspecies());
//		System.out.println(selectEspecieById(1));
//	}

}
