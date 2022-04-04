package control;

import java.util.Scanner;

import dao.AnimalDAO;
import dao.ClienteDAO;
import dao.EspecieDAO;
import dao.VeterinarioDAO;
import model.Animal;
import model.Veterinario;

public class VeterinarioController {

		public static void main(String[] args) {

			
			Scanner input = new Scanner(System.in);
			
			int opcao = 0;
			
			do {
				System.out.println("============= MENU VETERINÁRIOS =============");
				System.out.println(
						  "\n    1) Listar Veterinários." +
						  "\n    2) Inserir Veterinário." +
						  "\n    3) Excluir Veterinário." 

					    + "\n\n    Pressione 0 para voltar ao menu...\n"
						);
				System.out.println("=============================================");
				opcao = input.nextInt();
				switch(opcao) {
				
				case 1:
					selectVeterinarios();
					break;
				case 2:
					insertVeterinario();
					break;
				case 3:
					deleteVeterinario();
					break;

					default:
						if(opcao != 0) System.out.println("Opção inválida.");
				}
			}while(opcao != 0);
			
		}
		
		private static void selectVeterinarios() {
			System.out.println("\n**************** VETERINÁRIOS ***************\n"+ VeterinarioDAO.selectVeterinarios());
			System.out.println("\n*********************************************\n");
		}
		
		private static void insertVeterinario() {

			Scanner input = new Scanner(System.in);
			String nome,endereco,telefone;
			int confirma;
			
			do {
			System.out.println("\n********** INSERIR VETERINÁRIO *********\n");
			
			System.out.println("    Nome: ");
			nome = input.nextLine();
			System.out.println("    Endereço: ");
			endereco = input.nextLine();
			System.out.println("    Telefone: (DDD) 9XXXX-XXXX");
			telefone = input.nextLine();

			System.out.println("    Confirma? (0 não / 1 sim ) ");
			confirma = input.nextInt();
			input.nextLine();
			System.out.println("\n*****************************************\n");
			
			}while(confirma != 1);
			
			Veterinario newvet = new Veterinario();
			newvet.setNom_vet(nome);
			newvet.setEnd_vet(endereco);
			newvet.setTel_vet(telefone);
			VeterinarioDAO.insertVeterinario(newvet);
		}
		
		private static void deleteVeterinario() {
			
			Scanner input = new Scanner(System.in);
			Integer id,confirma;
			
			do {
			System.out.println("\n********** EXCLUIR VETERINÁRIO **********\n");
			
			System.out.println("    Digite o id do Veterinário que será excluido: ");
			id = input.nextInt();
			
			
			
			System.out.println("    Confirma? (0 não / 1 sim ) ");
			confirma = input.nextInt();
			input.nextLine();
			System.out.println("\n*****************************************\n");
			
			}while(confirma != 1);
			System.out.println(VeterinarioDAO.deleteVeterinario(id)); 
		}

	

}
