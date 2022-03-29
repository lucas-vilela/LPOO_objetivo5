package control;

import java.util.Scanner;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteController {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int opcao = 0;

		do {
			System.out.println("============= MENU CLIENTES =============");
			System.out.println("\n    1) Listar Clientes." + 
							   "\n    2) Inserir Cliente." +
							   "\n    3) Excluir Cliente." 

					+ "\n\n    Pressione 0 para voltar ao menu...\n");
			System.out.println("=========================================");
			opcao = input.nextInt();
			switch (opcao) {

			case 1:
				selectClientes();
				break;
			case 2:
				insertCliente();
				break;
			case 3:
				deleteCliente();
				break;	

			default:
				if (opcao != 0)
					System.out.println("Opção inválida.");
			}
		} while (opcao != 0);

	}

	private static void selectClientes() {
		System.out.println("\n**************** CLIENTES ***************\n" + ClienteDAO.selectClientes());
		System.out.println("\n*****************************************\n");
	}

	private static void insertCliente() {

		Scanner input = new Scanner(System.in);
		String nome, endereco, telefone, cep, email;

		System.out.println("\n************* INSERIR CLIENTE ***********\n");
		System.out.println("    Nome: ");
		nome = input.nextLine();
		System.out.println("    Endereço: ");
		endereco = input.nextLine();
		System.out.println("    Telefone: ");
		telefone = input.nextLine();
		System.out.println("    CEP: ");
		cep = input.nextLine();
		System.out.println("    E-mail: ");
		email = input.nextLine();

		Cliente newcliente = new Cliente(nome, endereco, telefone, cep, email);
		ClienteDAO.insertCliente(newcliente);

		System.out.println("\n*****************************************\n");
	}
	
	private static void deleteCliente() {
		
		Scanner input = new Scanner(System.in);
		Integer id;
		
		
		System.out.println("\n************ EXCLUIR CLIENTE ************\n");
		
		System.out.println("    Digite o id do Cliente que será excluido: ");
		id = input.nextInt();
		
		ClienteDAO.deleteCliente(id);
		
		System.out.println("\n*****************************************\n");
	}

}
