package control;

import java.util.Scanner;


import dao.TratamentoDAO;

public class TratamentoController {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int opcao = 0;

		do {
			System.out.println("============ MENU TRATAMENTOS ===========");
			System.out.println("\n    1) Listar TRATAMENTOS."

					+ "\n\n    Pressione 0 para voltar ao menu...\n");
			System.out.println("=========================================");
			opcao = input.nextInt();
			switch (opcao) {

			case 1:
				selectTratamentos();
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

}
