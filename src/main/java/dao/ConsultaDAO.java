package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Consulta;




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
		
		final String sql_insert_tratamento = "insert into tratamento (id_animal,dat_ini,dat_fin) values(?,?,?)";
		final String sql_insert_exame = "insert into exame (id_con,des_exame) values(?,?)";
		final String sql_insert_consulta_vazia = "insert into consulta (dat_con,historico) values('00-00-00','')";
		final String sql_update_consulta = "update consulta set id_trat = ? ,id_vet = ? ,dat_con = ?, historico = ? where id_con = ?";
		
		try
        (
        		Connection conn = getConnection();
        		PreparedStatement pstmt_tratamento = conn.prepareStatement(sql_insert_tratamento, Statement.RETURN_GENERATED_KEYS);
        		PreparedStatement pstmt_exame = conn.prepareStatement(sql_insert_exame);
				PreparedStatement pstmt_consulta_vazia = conn.prepareStatement(sql_insert_consulta_vazia, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement pstmt_update_consulta = conn.prepareStatement(sql_update_consulta);
		)
        {
            /*
                Inicia a transação, desligando o autocommit.
             */
			conn.setAutoCommit(false);
			
			int count = pstmt_consulta_vazia.executeUpdate();
			long id_con = 0L; 
			long id_trat = 0L;
			
			if (count > 0) { // se inseriu a consulta vazia, pega o id dela
			            	ResultSet rs = pstmt_consulta_vazia.getGeneratedKeys();
			                if (rs.next()) {
			                    id_con = rs.getLong(1);
			                }
			                rs.close(); 
			
			                pstmt_tratamento.setInt(1, consulta.getTratamento().getAnimal().getId_animal());
			                pstmt_tratamento.setDate(2, new Date(consulta.getTratamento().getData_ini().getTimeInMillis()));
			                pstmt_tratamento.setDate(3, new Date(consulta.getTratamento().getData_fin().getTimeInMillis()));
			
			                count = pstmt_tratamento.executeUpdate();
			                
			                if (count > 0) { // se inseriu a tratamento, pega o id dele
						                	ResultSet rs2 = pstmt_tratamento.getGeneratedKeys();
							                if (rs2.next()) {
							                    id_trat = rs2.getLong(1);
							                }
							                rs2.close();
						                
						                
							                pstmt_exame.setInt(1, (int)id_con);
							    			pstmt_exame.setString(2, consulta.getExames().get(0).getDes_exame());
							    			
							    			pstmt_exame.executeUpdate();
							    			
							    			pstmt_update_consulta.setInt(1, (int)id_trat);
							    			pstmt_update_consulta.setInt(2, consulta.getVeterinario().getId_vet());
							    			pstmt_update_consulta.setDate(3, new Date(consulta.getDat_con().getTimeInMillis()));
							    			pstmt_update_consulta.setString(4, consulta.getHistorico());
							    			pstmt_update_consulta.setInt(5, (int)id_con);
							    			
							    			pstmt_update_consulta.executeUpdate();
						    			
						                	}
							}
            conn.commit();
            conn.setAutoCommit(true);
                /*
                    Fim da transação ao comitar. Religa o autocomit, assim outros comportamentos da classe
                    ficam liberados para realizar as operações com o autocommit.
                 */
                return count > 0;
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }

//		final String sql = "insert into consulta (id_trat,id_vet,dat_con,historico) values(?,?,?,?)";
//
//		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
//			pstmt.setInt(1, consulta.getTratamento().getId_trat());
//			pstmt.setInt(2, consulta.getVeterinario().getId_vet());
//			pstmt.setDate(3, new Date(consulta.getDat_con().getTimeInMillis()));
//			pstmt.setString(4, consulta.getHistorico());
//			
//
//			int count = pstmt.executeUpdate();
//
//			return count > 0;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}

//	}
	
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
			pstmt.setDate(3, new Date(consulta.getDat_con().getTimeInMillis()));
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
		c.setDat_con(dateToCalendar(rs.getDate("dat_con")));
		c.setHistorico(rs.getString("historico"));
		c.setExames(ExameDAO.selectExamesByConsulta(rs.getInt("id_con")));

		return c;
	}
	

	
	

	public static void main(String[] args) {
		System.out.println(selectConsulta());
//		System.out.println(selectConsultaById(3));
//		insertConsultaVazia();
//		System.out.println(selectConsultaByLastID());	
		}
	
	
	private static Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
}
