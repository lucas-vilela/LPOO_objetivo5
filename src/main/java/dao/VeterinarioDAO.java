package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Veterinario;

public class VeterinarioDAO extends BaseDAO {

	public static List<Veterinario> selectVeterinarios() {
		final String sql = "select * from veterinario";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(sql);) {
			List<Veterinario> veterinarios = new ArrayList<>();
			while (rs.next()) {
				veterinarios.add(resultsetToVeterinario(rs));
			}
			return veterinarios;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Veterinario selectVeterinarioById(Integer id) {
		final String sql = "select * from veterinario where id_vet=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Veterinario veterinario = null;
			if (rs.next()) {
				veterinario = resultsetToVeterinario(rs);
			}
			rs.close();
			return veterinario;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	private static Veterinario resultsetToVeterinario(ResultSet rs) throws SQLException {
		Veterinario v = new Veterinario();

		v.setId_vet(rs.getInt("id_vet"));
		v.setNom_vet(rs.getNString("nom_vet"));
		v.setEnd_vet(rs.getString("end_vet"));
		v.setTel_vet(rs.getString("tel_vet"));

		return v;
	}

	public static void main(String[] args) {
		System.out.println(selectVeterinarios());
		System.out.println(selectVeterinarioById(2));
	}

}
