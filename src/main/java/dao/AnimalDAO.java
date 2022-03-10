package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Animal;


public class AnimalDAO extends BaseDAO {

	public static List<Animal> selectAnimais() {
		final String sql = "select * from animal";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(sql);
			) {
			List<Animal> animais = new ArrayList<>();
			while (rs.next()) {
				animais.add(resultsetToAnimal(rs));
			}
			return animais;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static List<Animal> selectAnimaisByEspecie(Integer esp) {
		final String sql = "select * from animal where id_esp=?";
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);	
			) {
			pstmt.setInt(1, esp);
			ResultSet rs = pstmt.executeQuery();
			List<Animal> animais = new ArrayList<>();
			while (rs.next()) {
				animais.add(resultsetToAnimal(rs));
			}
			return animais;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static List<Animal> selectAnimaisByCliente(Integer id_cli) {
		final String sql = "select * from animal where id_cli=?";
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);	
			) {
			pstmt.setInt(1, id_cli);
			ResultSet rs = pstmt.executeQuery();
			List<Animal> animais = new ArrayList<>();
			while (rs.next()) {
				animais.add(resultsetToAnimal(rs));
			}
			return animais;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	

	private static Animal resultsetToAnimal(ResultSet rs) throws SQLException {
		Animal a = new Animal();

		a.setId_animal(rs.getInt("id_animal"));
		a.setIdade_animal(rs.getInt("idade_animal"));
		a.setNome_animal(rs.getString("nome_animal"));
		a.setSexo_animal(rs.getInt("sexo_animal"));
		//a.setEspecie(EspecieDAO.selectEspecieById(rs.getInt("id_esp")));
		//a.setCliente(ClienteDAO.selectClienteById(rs.getInt("id_cli")));
		return a;
	}

//	public static void main(String[] args) {
//		System.out.println(selectAnimais());
//		System.out.println(selectAnimaisByEspecie(1));
//	}

}
