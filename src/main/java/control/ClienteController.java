package control;

import java.util.Scanner;

import dao.ClienteDAO;

public class ClienteController {

	public static void main(String[] args) {

		
		Scanner input = new Scanner(System.in);
		
		int opcao = 0;
		
		do {
			System.out.println("============= MENU CLIENTES =============");
			System.out.println(
					  "\n    1) Listar Clientes."

				    + "\n\n    Pressione 0 para voltar ao menu...\n"
					);
			System.out.println("=========================================");
			opcao = input.nextInt();
			switch(opcao) {
			
			case 1:
				selectClientes();
				break;

				default:
					if(opcao != 0) System.out.println("Opção inválida.");
			}
		}while(opcao != 0);
		
	}
	
	private static void selectClientes() {
		System.out.println("\n**************** CLIENTES ***************\n"+ ClienteDAO.selectClientes());
		System.out.println("\n*****************************************\n");
	}

}
