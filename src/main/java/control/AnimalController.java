package control;

import java.util.Scanner;

import dao.AnimalDAO;


public class AnimalController {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		int opcao = 0;
		
		do {
			System.out.println("================ MENU PETS ==============");
			System.out.println(
					  "\n    1) Listar PETS."

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
		System.out.println("\n****************** PETS *****************\n"+ AnimalDAO.selectAnimais());
		System.out.println("\n*****************************************\n");
	}

	}


