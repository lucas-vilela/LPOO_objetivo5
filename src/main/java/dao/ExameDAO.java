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
				exames.add(resultsetToExameCompleto(rs));
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
	
	public static Exame selectExameByLastID() {
		final String sql = "select * from exame where id_exame=(select max(id_exame) from exame);";
		try (Connection conn = getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(sql);) {
			
			Exame exame = null;
			if (rs.next()) {
				exame = resultsetToExameCompleto(rs);
			}
			rs.close();
			return exame;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static Exame selectExameByIdCompleto(Integer id) {
		final String sql = "select * from exame where id_exame=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Exame exame = null;
			if (rs.next()) {
				exame = resultsetToExameCompleto(rs);
			}
			rs.close();
			return exame;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	
	public static List<Exame> selectExamesByConsulta(Integer id_con) {
		final String sql = "select * from exame where id_con=?";
		try (
				Connection conn = getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);) 
		{
			pstmt.setInt(1, id_con);
			ResultSet rs = pstmt.executeQuery();
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
	
	public static List<Exame> selectExamesByConsultaCompleto(Integer id_con) {
		final String sql = "select * from exame where id_con=?";
		try (
				Connection conn = getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);) 
		{
			pstmt.setInt(1, id_con);
			ResultSet rs = pstmt.executeQuery();
			List<Exame> exames = new ArrayList<>();
			while (rs.next()) {
				exames.add(resultsetToExameCompleto(rs));
			}
			return exames;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public static boolean insertExame(Exame exame) {

		final String sql = "insert into exame (id_con,des_exame) values(?,?)";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, exame.getConsulta().getId_con());
			pstmt.setString(2, exame.getDes_exame());

			int count = pstmt.executeUpdate();

			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public static boolean updateExame(Exame exame) {

		final String sql = "update exame set id_con = ?";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, exame.getConsulta().getId_con());
			
			int count = pstmt.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	public static void deleteExame(int id) {
		final String sql = "delete from exame where id_exame=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			
			pstmt.executeQuery();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void deleteExameByIdCon(int id) {
		final String sql = "delete from exame where id_con=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			
			pstmt.executeQuery();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

	private static Exame resultsetToExame(ResultSet rs) throws SQLException {
		Exame e = new Exame();

		e.setId_exame(rs.getInt("id_exame"));
		e.setDes_exame(rs.getString("des_exame"));
//		e.setConsulta(ConsultaDAO.selectConsultaById(rs.getInt("id_con")));
		return e;
	}
	
	private static Exame resultsetToExameCompleto(ResultSet rs) throws SQLException {
		Exame e = new Exame();

		e.setId_exame(rs.getInt("id_exame"));
		e.setDes_exame(rs.getString("des_exame"));
		e.setConsulta(ConsultaDAO.selectConsultaById(rs.getInt("id_con")));
		return e;
	}

	public static void main(String[] args) {
		System.out.println(selectExames());
		System.out.println(selectExameById(1));
	}
	
}
