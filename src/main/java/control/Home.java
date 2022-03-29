package control;

import java.util.Scanner;

public class Home {

	public static void main(String[] args) {
		 
		Scanner input = new Scanner(System.in);
		
		int opcao = 0;
		
		do {
			System.out.println("================== HOME =================");
			System.out.println(
					  "\n    1) Realizar Consulta.\n"
					+ "\n    2) Manter CLientes."
					+ "\n    3) Manter Pets."
					+ "\n    4) Manter Consultas."
					+ "\n    5) Manter Especies."
					+ "\n    6) Manter Exames."
					+ "\n    7) Manter Tratamentos."
					+ "\n    8) Manter Veterinarios."
					+ "\n\n    Pressione 0 para sair...\n"
					);
			System.out.println("=========================================");
			opcao = input.nextInt();
			switch(opcao) {
			
			case 1:
				ConsultarController.main(null);
				break;
			case 2:
				ClienteController.main(null);
				break;
			case 3:
				AnimalController.main(null);
				break;
			case 4:
				ConsultaController.main(null);
				break;
			case 5:
				EspecieController.main(null);
				break;
			case 6:
				ExameController.main(null);
				break;
			case 7:
				TratamentoController.main(null);
				break;
			case 8:
				VeterinarioController.main(null);
				break;
				default:
					if(opcao != 0) System.out.println("Opção inválida.");
			}
		}while(opcao != 0);
		System.out.println("\nMorreu o bolo °_° e seguinte... Caixão Rei x_x");

	}

}
