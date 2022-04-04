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
	
	public static Animal selectAnimalById(Integer id_animal) {
		final String sql = "select * from animal where id_animal=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id_animal);
			ResultSet rs = pstmt.executeQuery();
			Animal animal = null;
			if (rs.next()) {
				animal = resultsetToAnimal(rs);
			}
			rs.close();
			return animal;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public static List<Animal> selectAnimaisCompleto() {
		final String sql = "select * from animal";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(sql);
			) {
			List<Animal> animais = new ArrayList<>();
			while (rs.next()) {
				animais.add(resultsetToAnimalCompleto(rs));
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
	
	
	public static boolean insertAnimal(Animal animal) {

		final String sql = "insert into animal (id_cli,id_esp,nome_animal,idade_animal,sexo_animal) values(?,?,?,?,?)";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, animal.getCliente().getId_cli());
			pstmt.setInt(2, animal.getEspecie().getId_esp());
			pstmt.setString(3, animal.getNome_animal());
			pstmt.setInt(4, animal.getIdade_animal());
			pstmt.setInt(5, animal.getSexo_animal());

			int count = pstmt.executeUpdate();

			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	public static String deleteAnimal(int id) {
		String status = "Falha";
		final String sql = "delete from animal where id_animal=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			
			String nome = AnimalDAO.selectAnimalById(id).getNome_animal();
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				status = "x_x  Você sacrificou o(a) "+ nome + " \npress F to respect...";
				return status; 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return status;
	}
	

	private static Animal resultsetToAnimal(ResultSet rs) throws SQLException {
		Animal a = new Animal();

		a.setId_animal(rs.getInt("id_animal"));
		a.setIdade_animal(rs.getInt("idade_animal"));
		a.setNome_animal(rs.getString("nome_animal"));
		a.setSexo_animal(rs.getInt("sexo_animal"));
		a.setEspecie(EspecieDAO.selectEspecieById(rs.getInt("id_esp")));
		//a.setCliente(ClienteDAO.selectClienteById(rs.getInt("id_cli"))); //essa chamada faz o programa entrar em looping infinito
		return a;
	}
	
	private static Animal resultsetToAnimalCompleto(ResultSet rs) throws SQLException {
		Animal a = new Animal();

		a.setId_animal(rs.getInt("id_animal"));
		a.setIdade_animal(rs.getInt("idade_animal"));
		a.setNome_animal(rs.getString("nome_animal"));
		a.setSexo_animal(rs.getInt("sexo_animal"));
		a.setEspecie(EspecieDAO.selectEspecieById(rs.getInt("id_esp")));
		a.setCliente(ClienteDAO.selectClienteById(rs.getInt("id_cli")));
		return a;
	}

//	public static void main(String[] args) {
//		System.out.println(selectAnimais());
//		System.out.println(selectAnimaisByEspecie(1));
//	}

}
