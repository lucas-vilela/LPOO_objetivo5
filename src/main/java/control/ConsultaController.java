package control;

import java.util.Scanner;

import dao.AnimalDAO;
import dao.ConsultaDAO;
import dao.VeterinarioDAO;

public class ConsultaController {
	
	

		public static void main(String[] args) {
			
			Scanner input = new Scanner(System.in);
			
			int opcao = 0;
			
			do {
				System.out.println("============= MENU CONSULTAS ============");
				System.out.println(
						  "\n    1) Listar CONSULTAS." +
						  "\n    1) Excluir CONSULTA."

					    + "\n\n    Pressione 0 para voltar ao menu...\n"
						);
				System.out.println("=========================================");
				opcao = input.nextInt();
				switch(opcao) {
				
				case 1:
					selectConsultas();
					break;
				case 2:
					deleteConsulta();
					break;

					default:
						if(opcao != 0) System.out.println("Opção inválida.");
				}
			}while(opcao != 0);
			
		}
		
		private static void selectConsultas() {
			System.out.println("\n************** CONSULTAS ****************\n"+ ConsultaDAO.selectConsulta());
			System.out.println("\n*****************************************\n");
		}
		
		private static void deleteConsulta() {
			
			Scanner input = new Scanner(System.in);
			Integer id,confirma;
			
			do {
			System.out.println("\n*********** EXCLUIR CONSULTA ************\n");
			
			System.out.println("    Digite o id do Consulta que será excluida:\n\nObs: serão excluídos os exames relacionados a essa consulta ");
			id = input.nextInt();
			
			 
			
			System.out.println("    Confirma? (0 não / 1 sim ) ");
			confirma = input.nextInt();
			input.nextLine();
			System.out.println("\n*****************************************\n");
			
			}while(confirma != 1);
			
			System.out.println(ConsultaDAO.deleteConsulta(id));
		}

		
}
