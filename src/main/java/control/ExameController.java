package control;

import java.util.Scanner;

import dao.EspecieDAO;
import dao.ExameDAO;
import dao.VeterinarioDAO;

public class ExameController {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		int opcao = 0;
		
		do {
			System.out.println("============== MENU EXAMES ==============");
			System.out.println(
					  "\n    1) Listar EXAMES." +
					  "\n    2) Excluir EXAME."

				    + "\n\n    Pressione 0 para voltar ao menu...\n"
					);
			System.out.println("=========================================");
			opcao = input.nextInt();
			switch(opcao) {
			
			case 1:
				selectExames();
				break;
			case 2:
				deleteExame();
				break;

				default:
					if(opcao != 0) System.out.println("Opção inválida.");
			}
		}while(opcao != 0);
		
	}
	
	private static void selectExames() {
		System.out.println("\n***************** EXAMES ****************\n"+ ExameDAO.selectExames());
		System.out.println("\n*****************************************\n");
	}
	
	private static void deleteExame() {
		
		Scanner input = new Scanner(System.in);
		Integer id,confirma;
		
		
		
		System.out.println("\n************* EXCLUIR EXAME *************\n");
		
		do {
		System.out.println("    Digite o id do Exame que será excluido: ");
		id = input.nextInt();

		System.out.println("    Confirma? (0 não / 1 sim ) ");
		confirma = input.nextInt();
		input.nextLine();
		}while(confirma != 1);
		
		
		ExameDAO.deleteExame(id); 
		
		System.out.println("\n*****************************************\n");
	}

	

}
