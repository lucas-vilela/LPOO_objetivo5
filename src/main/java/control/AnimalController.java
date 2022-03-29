package control;

import java.util.Scanner;

import dao.AnimalDAO;
import dao.ClienteDAO;
import dao.EspecieDAO;
import model.Animal;
import model.Cliente;


public class AnimalController {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		int opcao = 0;
		
		do {
			System.out.println("================ MENU PETS ==============");
			System.out.println(
					  "\n    1) Listar PETS."+
					  "\n    2) Inserir PET." +
				      "\n    3) Excluir PET."

				    + "\n\n    Pressione 0 para voltar ao menu...\n"
					);
			System.out.println("=========================================");
			opcao = input.nextInt();
			switch(opcao) {
			
			case 1:
				selectAnimais();
				break;
			case 2:
				insertAnimal();
				break;
			case 3:
				deleteAnimal();
				break;	

				default:
					if(opcao != 0) System.out.println("Op��o inv�lida.");
			}
		}while(opcao != 0);
		
	}
	
	private static void selectAnimais() {
		System.out.println("\n****************** PETS *****************\n"+ AnimalDAO.selectAnimaisCompleto());
		System.out.println("\n*****************************************\n");
	}

	private static void insertAnimal() {

		Scanner input = new Scanner(System.in);
		String nome;
		Integer id_cli,id_esp,idade,sexo;

		System.out.println("\n*************** INSERIR PET *************\n");
		System.out.println("    ID do dono: ");
		id_cli = input.nextInt();
		input.nextLine();
		System.out.println("    ID da esp�cie: ");
		id_esp = input.nextInt();
		input.nextLine();
		System.out.println("    Nome: ");
		nome = input.nextLine();
		System.out.println("    Idade: ");
		idade = input.nextInt();
		input.nextLine();
		System.out.println("    Sexo: ");
		sexo = input.nextInt();
		input.nextLine();

		Animal newanimal = new Animal(nome,idade,sexo,EspecieDAO.selectEspecieById(id_esp),ClienteDAO.selectClienteById(id_cli));
		AnimalDAO.insertAnimal(newanimal);

		System.out.println("\n*****************************************\n");
	}
	
	private static void deleteAnimal() {
		
		Scanner input = new Scanner(System.in);
		Integer id;
		
		
		System.out.println("\n************** EXCLUIR PET **************\n");
		
		System.out.println("    Digite o id do Pet que ser� excluido: ");
		id = input.nextInt();
		
		AnimalDAO.deleteAnimal(id);
		
		System.out.println("\n*****************************************\n");
	}
	
}


