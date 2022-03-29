package control;

import java.util.Scanner;

import dao.EspecieDAO;
import dao.ExameDAO;

public class ExameController {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		int opcao = 0;
		
		do {
			System.out.println("============== MENU EXAMES ==============");
			System.out.println(
					  "\n    1) Listar EXAMES."

				    + "\n\n    Pressione 0 para voltar ao menu...\n"
					);
			System.out.println("=========================================");
			opcao = input.nextInt();
			switch(opcao) {
			
			case 1:
				selectEspecies();
				break;

				default:
					if(opcao != 0) System.out.println("Opção inválida.");
			}
		}while(opcao != 0);
		
	}
	
	private static void selectEspecies() {
		System.out.println("\n***************** EXAMES ****************\n"+ ExameDAO.selectExames());
		System.out.println("\n*****************************************\n");
	}

	

}
