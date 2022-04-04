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

	
	public static boolean insertConsulta(Consulta consulta,boolean op_trat) {
		
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
			
			                
			                if(op_trat == false) { // se vai seguir num tratamento que já existe pega o id que vem do controller
			                	id_trat = consulta.getTratamento().getId_trat();
			                }else {
			                
					                pstmt_tratamento.setInt(1, consulta.getTratamento().getAnimal().getId_animal());
					                pstmt_tratamento.setDate(2, new Date(consulta.getTratamento().getData_ini().getTimeInMillis()));
					                pstmt_tratamento.setDate(3, new Date(consulta.getTratamento().getData_fin().getTimeInMillis()));
					
					                pstmt_tratamento.executeUpdate();
					                // se não vai seguir no mesmo, pega o id do novo tratamento inserido
				                	ResultSet rs2 = pstmt_tratamento.getGeneratedKeys();
					                if (rs2.next()) {
					                    id_trat = rs2.getLong(1);
					                }
					                rs2.close();
					                }   
			                
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
	
	
	public static String deleteConsulta(int id) {
		String status = "Falha ao deletar consulta.";
	
		final String sql_delete_tratamento = "delete from tratamento where id_trat=?";
		final String sql_delete_exame = "delete from exame where id_con=?";
		final String sql_delete_consulta = "delete from consulta where id_con=?";
		
		try
        (
        		Connection conn = getConnection();
        		PreparedStatement pstmt_tratamento = conn.prepareStatement(sql_delete_tratamento);
        		PreparedStatement pstmt_exame = conn.prepareStatement(sql_delete_exame);
				PreparedStatement pstmt_consulta = conn.prepareStatement(sql_delete_consulta);
		)
        {
//            /*
//                Inicia a transação, desligando o autocommit.
//             */
//			conn.setAutoCommit(false);
//			int id_trat = selectConsultaById(id).getTratamento().getId_trat();
//			
//			
//			pstmt_exame.setInt(1, selectConsultaById(id).getExames().get(0).getId_exame());
//			System.out.println(selectConsultaById(id).getExames().get(0).getId_exame());
//			pstmt_exame.executeUpdate();
//			
//			pstmt_consulta.setInt(1, id);
//			System.out.println(id);
//			pstmt_consulta.executeUpdate();
//			
//			
//			pstmt_tratamento.setInt(1, id_trat);
//			System.out.println(id_trat);
//			pstmt_tratamento.executeUpdate();
			
			
			pstmt_consulta.setInt(1, id);
			pstmt_consulta.executeUpdate();
			
			
			
			
			
			
			
			status = "Consulta de id: " + id + "excluída.";
			
			
			
			conn.commit();
            conn.setAutoCommit(true);
                /*
                    Fim da transação ao comitar. Religa o autocomit, assim outros comportamentos da classe
                    ficam liberados para realizar as operações com o autocommit.
                 */
                return status;
            }catch (SQLException e){
                e.printStackTrace();
                return status;
            }
        }
				
		
		
		
		
		
//		final String sql = "delete from consulta where id_con=?";
//		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
//			pstmt.setInt(1, id);
//			
//				TratamentoDAO.deleteTratamento(selectConsultaById(id).getTratamento().getId_trat());
//				ExameDAO.deleteExameByIdCon(id);
//				int count = pstmt.executeUpdate();
//
//				if (count > 0) {
//					status = "Foi excluída a consulta de id: " + id;
//					return status;
//				}
//			
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return status;
//
//	}
	
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
