package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.AnimalDAO;
import dao.ClienteDAO;
import dao.ConsultaDAO;
import dao.ExameDAO;
import dao.TratamentoDAO;
import dao.VeterinarioDAO;
import model.Cliente;
import model.Consulta;
import model.Exame;
import model.Tratamento;
import model.Veterinario;

public class ConsultarController {

private static Consulta consulta = new Consulta();
private static List<Exame>  exames = new ArrayList();
	
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		ConsultaDAO.insertConsultaVazia();
		consulta.setId_con(ConsultaDAO.selectConsultaByLastID().getId_con()); // PRECISEI SETAR O ID AQUI PARA USAR LÁ EM EXAMES
		
		int opcao = 0;

		do {
			System.out.println("================ CONSULTA ===============");
			System.out.println("\n    1) Inserir comentário." + //juntar essa 3 primeiras coisas
							   "\n    2) Prescrever tratamento." + 
							   "\n    3) Solicitar exame." +
							   "\n    4) Veterinário." +
							   "\n    5) Registrar consulta."

					+ "\n\n    Pressione 0 para voltar ao menu anterior...\n");
			System.out.println("=========================================");
			opcao = input.nextInt();
			input.nextLine();
			switch (opcao) {

			case 1:
				insertComentario();
				break;

			case 2:
				insertTratamento();
				break;

			case 3:
				insertExame();
				break;

			case 4:
				insertVeterianario();
				break;
			case 5:
				registraConsulta();
				break;
			default:
				if (opcao != 0)
					System.out.println("Opção inválida.");
			}
		} while (opcao != 0);

	}

	public static String insertComentario() {

		Scanner input = new Scanner(System.in);
		String comentario;

		System.out.println("\n*************** COMENTAR ****************\n");

		System.out.println("Digite o comentário:\n");
		comentario = input.nextLine();

		consulta.setHistorico(comentario);// montar a consulta assim
		
		System.out.println("\n*****************************************\n");
		
		return comentario;
	}

	public static Tratamento insertTratamento() {

		Scanner input = new Scanner(System.in);
		String data_ini, data_fin;
		Integer id_animal;

		System.out.println("\n************** TRATAMENTO ***************\n");

		System.out.println("ID do pet:");
		id_animal = input.nextInt();
		input.nextLine();
		System.out.println("Data inicial:");
		data_ini = input.nextLine();
		System.out.println("Data final:");
		data_fin = input.nextLine();

		Tratamento tratamento = new Tratamento();
		tratamento.setAnimal(AnimalDAO.selectAnimalById(id_animal));
		tratamento.setData_ini(data_ini);
		tratamento.setData_fin(data_fin);
		TratamentoDAO.insertTratamento(tratamento); //NESCESSÁRIO INSERIR O TRATAMENTO PRA PEGAR O ID PRA INSERIR NA CONSULTA. EXCLUIR SE NÃO FOR REGISTRADA A CONSULTA
		
		consulta.setTratamento(TratamentoDAO.selectTratamentoByLastID());

		System.out.println("\n*****************************************\n");
		
		return tratamento;
	}

	public static Exame insertExame() {
		Scanner input = new Scanner(System.in);
		String des_exame;

		System.out.println("\n************ SOLICITAR EXAME ************\n");
		System.out.println("Descrição do exame:");
		des_exame = input.nextLine();

		Exame exame = new Exame();
		exame.setDes_exame(des_exame);
		exame.setConsulta(consulta);
		ExameDAO.insertExame(exame);
		exames.add(ExameDAO.selectExameByLastID());
		consulta.setExames(exames);
		
		
		System.out.println("\n*****************************************\n");

		return exame;
	}

	public static void insertVeterianario() {
		Scanner input = new Scanner(System.in);
		Integer veterinario;

		System.out.println("\n************** VETERINÁRIO **************\n");
		System.out.println("ID do veterinário:");
		veterinario = input.nextInt();
		
		consulta.setVeterinario(VeterinarioDAO.selectVeterinarioById(veterinario));;
		
		
		System.out.println("\n*****************************************\n");
	}
	
	public static void registraConsulta() {

		consulta.setDat_con("00/00/00"); 
		ConsultaDAO.updateConsultaCompleto(consulta); // NO FIM SÓ FAZER UM UPDATE
	}

}
