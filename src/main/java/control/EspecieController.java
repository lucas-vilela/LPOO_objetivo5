package control;

import java.util.Scanner;
import dao.EspecieDAO;
import model.Especie;

public class EspecieController {

public static void main(String[] args) {

		
		Scanner input = new Scanner(System.in);
		
		int opcao = 0;
		
		do {
			System.out.println("============= MENU ESP�CIES =============");
			System.out.println(
					  "\n    1) Listar Esp�cies." +
					  "\n    2) Inserir Esp�cie."

				    + "\n\n    Pressione 0 para voltar ao menu...\n"
					);
			System.out.println("=========================================");
			opcao = input.nextInt();
			switch(opcao) {
			
			case 1:
				selectEspecies();
				break;
			case 2:
				insertEspecie();
				break;

				default:
					if(opcao != 0) System.out.println("Op��o inv�lida.");
			}
		}while(opcao != 0);
		
	}
	
	private static void selectEspecies() {
		System.out.println("\n**************** ESP�CIES ***************\n"+ EspecieDAO.selectEspecies());
		System.out.println("\n*****************************************\n");
	}
	
	private static void insertEspecie() {
		String nome;
		Scanner input = new Scanner(System.in);
		System.out.println("\n************ INSERIR ESP�CIE ************\n");
		
				
				System.out.println("    Nome da esp�cie: ");
				nome = input.nextLine();
				

				Especie newespecie = new Especie();
				newespecie.setNom_esp(nome);
				EspecieDAO.insertEspecie(newespecie);
				
				
				
		System.out.println("\n*****************************************\n");
	}

}
