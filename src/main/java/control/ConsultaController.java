package control;

import java.util.Scanner;

import dao.AnimalDAO;
import dao.ConsultaDAO;

public class ConsultaController {
	
	

		public static void main(String[] args) {
			
			Scanner input = new Scanner(System.in);
			
			int opcao = 0;
			
			do {
				System.out.println("============= MENU CONSULTAS ============");
				System.out.println(
						  "\n    1) Listar CONSULTAS."

					    + "\n\n    Pressione 0 para voltar ao menu...\n"
						);
				System.out.println("=========================================");
				opcao = input.nextInt();
				switch(opcao) {
				
				case 1:
					selectAnimais();
					break;

					default:
						if(opcao != 0) System.out.println("Opção inválida.");
				}
			}while(opcao != 0);
			
		}
		
		private static void selectAnimais() {
			System.out.println("\n************** CONSULTAS ****************\n"+ ConsultaDAO.selectConsulta());
			System.out.println("\n*****************************************\n");
		}

		
}
