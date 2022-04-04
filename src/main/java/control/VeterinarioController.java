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
				System.out.println("============= MENU VETERIN�RIOS =============");
				System.out.println(
						  "\n    1) Listar Veterin�rios." +
						  "\n    2) Inserir Veterin�rio." +
						  "\n    3) Excluir Veterin�rio." 

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
						if(opcao != 0) System.out.println("Op��o inv�lida.");
				}
			}while(opcao != 0);
			
		}
		
		private static void selectVeterinarios() {
			System.out.println("\n**************** VETERIN�RIOS ***************\n"+ VeterinarioDAO.selectVeterinarios());
			System.out.println("\n*********************************************\n");
		}
		
		private static void insertVeterinario() {

			Scanner input = new Scanner(System.in);
			String nome,endereco,telefone;
			int confirma;
			
			do {
			System.out.println("\n********** INSERIR VETERIN�RIO *********\n");
			
			System.out.println("    Nome: ");
			nome = input.nextLine();
			System.out.println("    Endere�o: ");
			endereco = input.nextLine();
			System.out.println("    Telefone: (DDD) 9XXXX-XXXX");
			telefone = input.nextLine();

			System.out.println("    Confirma? (0 n�o / 1 sim ) ");
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
			System.out.println("\n********** EXCLUIR VETERIN�RIO **********\n");
			
			System.out.println("    Digite o id do Veterin�rio que ser� excluido: ");
			id = input.nextInt();
			
			
			
			System.out.println("    Confirma? (0 n�o / 1 sim ) ");
			confirma = input.nextInt();
			input.nextLine();
			System.out.println("\n*****************************************\n");
			
			}while(confirma != 1);
			System.out.println(VeterinarioDAO.deleteVeterinario(id)); 
		}

	

}
