package control;

import java.util.Scanner;

import dao.VeterinarioDAO;

public class VeterinarioController {

		public static void main(String[] args) {

			
			Scanner input = new Scanner(System.in);
			
			int opcao = 0;
			
			do {
				System.out.println("============= MENU VETERIN�RIOS =============");
				System.out.println(
						  "\n    1) Listar Veterin�rios."

					    + "\n\n    Pressione 0 para voltar ao menu...\n"
						);
				System.out.println("=============================================");
				opcao = input.nextInt();
				switch(opcao) {
				
				case 1:
					selectVeterinarios();
					break;

					default:
						if(opcao != 0) System.out.println("Op��o inv�lida.");
				}
			}while(opcao != 0);
			
		}
		
		private static void selectVeterinarios() {
			System.out.println("\n**************** VETERIN�RIOS ***************\n"+ VeterinarioDAO.selectVeterinarios());
			System.out.println("\n*********************************************\n");
		}

	

}
