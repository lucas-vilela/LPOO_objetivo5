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
	
	public static boolean insertEspecie(Especie esp) {

		final String sql = "insert into especie (nom_esp) values(?)";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, esp.getNom_esp());

			int count = pstmt.executeUpdate();

			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	public static void deleteEspecie(int id) {
		final String sql = "delete from especie where id_esp=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			
			pstmt.executeQuery();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static Especie resultsetToEspecie(ResultSet rs) throws SQLException {
		Especie e = new Especie();

		e.setId_esp(rs.getInt("id_esp"));
		e.setNom_esp(rs.getString("nom_esp"));
		//e.setAnimais(AnimalDAO.selectAnimaisByEspecie(rs.getInt("id_esp"))); //Essa chamada faz o programa entrar em looping infitino.

		return e;
	}

//	public static void main(String[] args) {
//		System.out.println(selectEspecies());
//		System.out.println(selectEspecieById(1));
//	}

}
