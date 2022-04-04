package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Tratamento;




public class TratamentoDAO extends BaseDAO {
	
	public static List<Tratamento> selectTratamentos() {
		final String sql = "select * from tratamento";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(sql);) {
			List<Tratamento> tratamentos = new ArrayList<>();
			while (rs.next()) {
				tratamentos.add(resultsetToTratamento(rs));
			}
			return tratamentos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static List<Tratamento> selectTratamentosSconsulta() {
		final String sql = "select * from tratamento";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(sql);) {
			List<Tratamento> tratamentos = new ArrayList<>();
			while (rs.next()) {
				tratamentos.add(resultsetToTratamentoSconsulta(rs));
			}
			return tratamentos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Tratamento selectTratamentoById(Integer id) {
		final String sql = "select * from tratamento where id_trat=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Tratamento tratamento = null;
			if (rs.next()) {
				tratamento = resultsetToTratamento(rs);
			}
			rs.close();
			return tratamento;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static Tratamento selectTratamentoByLastID() {
		final String sql = "select * from tratamento where id_trat=(select max(id_trat) from tratamento);";
		try (Connection conn = getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(sql);) {
			
			Tratamento tratamento = null;
			if (rs.next()) {
				tratamento = resultsetToTratamento(rs);
			}
			rs.close();
			return tratamento;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	

	
	public static Tratamento selectTratamentoSconsultaById(Integer id) {
		final String sql = "select * from tratamento where id_trat=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Tratamento tratamento = null;
			if (rs.next()) {
				tratamento = resultsetToTratamentoSconsulta(rs);
			}
			rs.close();
			return tratamento;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static List<Tratamento> selectTratamentosByIdAnimal(int id) {
		final String sql = "select * from tratamento where id_animal=?";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery(); 
			
			List<Tratamento> tratamentos = new ArrayList<>();
			while (rs.next()) {
				tratamentos.add(resultsetToTratamento(rs));
			}
			return tratamentos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	
	
	
	public static boolean insertTratamento(Tratamento trat) {

		final String sql = "insert into tratamento (id_animal,dat_ini,dat_fin) values(?,?,?)";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, trat.getAnimal().getId_animal());
			pstmt.setDate(2, new Date(trat.getData_ini().getTimeInMillis()));
			pstmt.setDate(3, new Date(trat.getData_fin().getTimeInMillis()));
			

			int count = pstmt.executeUpdate();

			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	public static void deleteTratamento(int id) {
		final String sql = "delete from veterinario where id_trat=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			
			pstmt.executeQuery();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

	private static Tratamento resultsetToTratamentoSconsulta(ResultSet rs) throws SQLException {
		Tratamento t = new Tratamento();

		t.setId_trat(rs.getInt("id_trat"));
		t.setData_ini(dateToCalendar(rs.getDate("dat_ini")));
		t.setData_fin(dateToCalendar(rs.getDate("dat_fin")));
//		t.setConsultas(ConsultaDAO.selectConsultasByTratamento(rs.getInt("id_trat")));
		t.setAnimal(AnimalDAO.selectAnimalById(rs.getInt("id_animal")));

		return t;
	}
	
	private static Tratamento resultsetToTratamento(ResultSet rs) throws SQLException {
		Tratamento t = new Tratamento();

		t.setId_trat(rs.getInt("id_trat"));
		t.setData_ini(dateToCalendar(rs.getDate("dat_ini")));
		t.setData_fin(dateToCalendar(rs.getDate("dat_fin")));
		t.setConsultas(ConsultaDAO.selectConsultasByTratamento(rs.getInt("id_trat")));
		t.setAnimal(AnimalDAO.selectAnimalById(rs.getInt("id_animal")));

		return t;
	}

	public static void main(String[] args) {
//		System.out.println(selectTratamentos());
//		System.out.println(selectTratamentoById(1));
		System.out.println(selectTratamentoByLastID());
	}
	
	private static Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	
	
}
