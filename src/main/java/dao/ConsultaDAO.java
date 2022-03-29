package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Consulta;
import model.Tratamento;



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

	public static Consulta selectConsultaByLastID() {
		final String sql = "select * from consulta where id_con=(select max(id_con) from consulta)";
		try (Connection conn = getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(sql);) {
			
			Consulta consulta = null;
			if (rs.next()) {
				
				consulta = resultsetToConsultaSoID(rs);
			}
			rs.close();
			
			return consulta;
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
	
	public static List<Consulta> selectConsultasByTratamento(Integer id) {
		final String sql = "select * from consulta where id_trat=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			List<Consulta> consultas = new ArrayList<>();
			while (rs.next()) {
				consultas.add(resultsetToConsulta(rs));
			}
			rs.close();
			return consultas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	
	public static boolean insertConsulta(Consulta consulta) {

		final String sql = "insert into consulta (id_trat,id_vet,dat_con,historico) values(?,?,?,?)";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, consulta.getTratamento().getId_trat());
			pstmt.setInt(2, consulta.getVeterinario().getId_vet());
			pstmt.setString(3, consulta.getDat_con());
			pstmt.setString(4, consulta.getHistorico());
			

			int count = pstmt.executeUpdate();

			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public static boolean insertConsultaVazia() {

		final String sql = "insert into consulta (dat_con,historico) values('00-00-00','')";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			

			int count = pstmt.executeUpdate();

			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public static boolean updateConsulta(Consulta consulta) {

		final String sql = "update consulta set id_trat = ?";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, consulta.getTratamento().getId_trat());
			
			int count = pstmt.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public static boolean updateConsultaCompleto(Consulta consulta) {

		final String sql = "update consulta set id_trat = ? ,id_vet = ? ,dat_con = ?, historico = ? where id_con = ?";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, consulta.getTratamento().getId_trat());
			pstmt.setInt(2, consulta.getVeterinario().getId_vet());
			pstmt.setString(3, consulta.getDat_con());
			pstmt.setString(4, consulta.getHistorico());
			pstmt.setInt(5, consulta.getId_con());
			
			
			int count = pstmt.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	public static void deleteConsulta(int id) {
		final String sql = "delete from consulta where id_con=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			
			pstmt.executeQuery();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	private static Consulta resultsetToConsultaSoID(ResultSet rs) throws SQLException {
		Consulta c = new Consulta();

		
		c.setId_con(rs.getInt("id_con"));
		return c;
	}
	
	
	private static Consulta resultsetToConsulta(ResultSet rs) throws SQLException {
		Consulta c = new Consulta();

		
		c.setId_con(rs.getInt("id_con"));
		c.setTratamento(TratamentoDAO.selectTratamentoSconsultaById(rs.getInt("id_trat")));
		c.setVeterinario(VeterinarioDAO.selectVeterinarioById(rs.getInt("id_vet")));
		c.setDat_con(rs.getString("dat_con"));
		c.setHistorico(rs.getString("historico"));
		c.setExames(ExameDAO.selectExamesByConsulta(rs.getInt("id_con")));

		return c;
	}
	

	
	

	public static void main(String[] args) {
//		System.out.println(selectConsulta());
//		System.out.println(selectConsultaById(3));
//		insertConsultaVazia();
		System.out.println(selectConsultaByLastID());	
		}
	
}
