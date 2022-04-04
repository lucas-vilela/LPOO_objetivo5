package control;

import java.util.Scanner;

import dao.ExameDAO;
import dao.TratamentoDAO;

public class TratamentoController {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int opcao = 0;

		do {
			System.out.println("============ MENU TRATAMENTOS ===========");
			System.out.println("\n    1) Listar TRATAMENTOS." +
							   "\n    2) Exluir TRATAMENTO."

					+ "\n\n    Pressione 0 para voltar ao menu...\n");
			System.out.println("=========================================");
			opcao = input.nextInt();
			switch (opcao) {

			case 1:
				selectTratamentos();
				break;
			case 2:
				deleteTratamento();
				break;

			default:
				if (opcao != 0)
					System.out.println("Opção inválida.");
			}
		} while (opcao != 0);

	}

	private static void selectTratamentos() {
		System.out.println("\n************** TRATAMENTOS **************\n" + TratamentoDAO.selectTratamentos());
		System.out.println("\n*****************************************\n");
	}
	
private static void deleteTratamento() {
		
		Scanner input = new Scanner(System.in);
		Integer id,confirma;
		
		do {
		System.out.println("\n*********** EXCLUIR TRATAMENTO **********\n");
		
		System.out.println("    Digite o id do Tratamento que será excluido: ");
		id = input.nextInt();
		
		
		
		System.out.println("    Confirma? (0 não / 1 sim ) ");
		confirma = input.nextInt();
		input.nextLine();
		System.out.println("\n*****************************************\n");
		
		}while(confirma != 1);
		TratamentoDAO.deleteTratamento(id); 
	}

}
