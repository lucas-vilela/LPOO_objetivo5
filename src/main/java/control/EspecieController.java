package control;

import java.util.Scanner;
import dao.EspecieDAO;
import model.Especie;

public class EspecieController {

public static void main(String[] args) {

		
		Scanner input = new Scanner(System.in);
		
		int opcao = 0;
		
		do {
			System.out.println("============= MENU ESPÉCIES =============");
			System.out.println(
					  "\n    1) Listar Espécies." +
					  "\n    2) Inserir Espécie."

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
					if(opcao != 0) System.out.println("Opção inválida.");
			}
		}while(opcao != 0);
		
	}
	
	private static void selectEspecies() {
		System.out.println("\n**************** ESPÉCIES ***************\n"+ EspecieDAO.selectEspecies());
		System.out.println("\n*****************************************\n");
	}
	
	private static void insertEspecie() {
		String nome;
		Scanner input = new Scanner(System.in);
		System.out.println("\n************ INSERIR ESPÉCIE ************\n");
		
				
				System.out.println("    Nome da espécie: ");
				nome = input.nextLine();
				

				Especie newespecie = new Especie();
				newespecie.setNom_esp(nome);
				EspecieDAO.insertEspecie(newespecie);
				
				
				
		System.out.println("\n*****************************************\n");
	}

}
