package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDAO extends BaseDAO {

	public static List<Cliente> selectClientes() {
		final String sql = "select * from cliente";
		try (	Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(sql);
			) {
			List<Cliente> clientes = new ArrayList<>();
			while (rs.next()) {
				clientes.add(resultsetToCliente(rs));
			}
			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Cliente selectClienteById(Integer id) {
		final String sql = "select * from cliente where id_cli=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Cliente cliente = null;
			if (rs.next()) {
				cliente = resultsetToCliente(rs);
			}
			rs.close();
			return cliente;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static boolean insertCliente(Cliente cliente) {

		final String sql = "insert into cliente (nom_cli,end_cli,tel_cli,cep_cli,email_cli) values(?,?,?,?,?)";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, cliente.getNom_cli());
			pstmt.setString(2, cliente.getEnd_cli());
			pstmt.setString(3, cliente.getTel_cli());
			pstmt.setString(4, cliente.getCep_cli());
			pstmt.setString(5, cliente.getEmail_cli());

			int count = pstmt.executeUpdate();

			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	private static Cliente resultsetToCliente(ResultSet rs) throws SQLException {
		Cliente c = new Cliente();

		c.setId_cli(rs.getInt("id_cli"));
		c.setNom_cli(rs.getNString("nom_cli"));
		c.setEnd_cli(rs.getNString("end_cli"));
		c.setTel_cli(rs.getNString("tel_cli"));
		c.setCep_cli(rs.getString("cep_cli"));
		c.setEmail_cli(rs.getString("email_cli"));
		c.setAnimais(AnimalDAO.selectAnimaisByCliente(rs.getInt("id_cli")));

		return c;
	}

	public static void main(String[] args) {
		System.out.println(selectClientes());
//		System.out.println(selectClienteById(3));

//		Cliente cliente = new Cliente(4,"Laura","Barroso","(53) 98112-1232","96043-239","laura@gmail.com");
//		System.out.println(insertCliente(cliente));

	}

}
