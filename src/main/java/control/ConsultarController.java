package control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import dao.AnimalDAO;
import dao.ConsultaDAO;
import dao.TratamentoDAO;
import dao.VeterinarioDAO;
import model.Consulta;
import model.Exame;
import model.Tratamento;
import model.Veterinario;

public class ConsultarController {

	private static Consulta consulta = new Consulta();
	private static List<Exame> exames = new ArrayList();
	private static GregorianCalendar dataDaConsulta = new GregorianCalendar();
	private static boolean op_trat = false;

	public static void main(String[] args) throws ParseException {

		Scanner input = new Scanner(System.in);
		consulta.setExames(null);
		consulta.setTratamento(null);

		int opcao = 0;

		do {
			System.out.println("================ CONSULTA ===============");
			System.out.println(
					"\n    1) Inserir comentário." + 
					"\n    2) Novo tratamento." + 
					"\n    3) Seguir no tratamento." + 
					"\n    4) Solicitar exame." + 
					"\n    5) Veterinário." + 
					"\n\n  6) Registrar consulta."

				  + "\n\n    Pressione 0 para voltar ao menu anterior...\n");
			System.out.println("=========================================");
			opcao = input.nextInt();
			input.nextLine();
			switch (opcao) {

			case 1:
				insertComentario();
				break;

			case 2:
				insertTratamento(true);
				op_trat = true;
				break;
			case 3:
				insertTratamento(false);
				break;

			case 4:
				insertExame();
				break;

			case 5:
				insertVeterianario();
				break;
			case 6:
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
		int confirma = 0;
//		consulta.setDat_con(dataDaConsulta);

		do {
			System.out.println("\n*************** COMENTAR ****************\n");

			System.out.println("Digite o comentário:\n");
			comentario = input.nextLine();

			System.out.println("    Confirma? (0 não / 1 sim ) ");
			confirma = input.nextInt();
			input.nextLine();
			System.out.println("\n*****************************************\n");

		} while (confirma != 1);

		consulta.setHistorico(comentario);

		return comentario;
	}

	public static Tratamento insertTratamento(boolean op) throws ParseException {

		Scanner input = new Scanner(System.in);
		Calendar data_ini = Calendar.getInstance();
		Calendar data_fin = Calendar.getInstance();
		Integer id_animal,confirma;
		String data;
		Tratamento tratamento = new Tratamento();

		if (op_trat == true) {
			do {
				System.out.println("\n************** TRATAMENTO ***************\n");

				System.out.println("ID do pet:");
				id_animal = input.nextInt();
				input.nextLine();

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");

				System.out.println("Data inicial: DD/MM/YYYY");
				data = input.nextLine();
				data_fin.setTime(sdf.parse(data));

				System.out.println("Data final: DD/MM/YYYY");
				data = input.nextLine();
				data_ini.setTime(sdf.parse(data));

				tratamento.setAnimal(AnimalDAO.selectAnimalById(id_animal));
				tratamento.setData_ini(data_ini);
				tratamento.setData_fin(data_fin);
//		TratamentoDAO.insertTratamento(tratamento); //NESCESSÁRIO INSERIR O TRATAMENTO PRA PEGAR O ID PRA INSERIR NA CONSULTA. EXCLUIR SE NÃO FOR REGISTRADA A CONSULTA

				System.out.println("    Confirma? (0 não / 1 sim ) ");
				confirma = input.nextInt();
				input.nextLine();
				System.out.println("\n*****************************************\n");

			} while (confirma != 1);

			consulta.setTratamento(tratamento);
		} else if (op_trat == false) {
			int id_trat;
			do {
			System.out.println("\n************** TRATAMENTO ***************\n");

			System.out.println("ID do tratamento já existente:");
			id_trat = input.nextInt();

			System.out.println("    Confirma? (0 não / 1 sim ) ");
			confirma = input.nextInt();
			input.nextLine();
			System.out.println("\n*****************************************\n");
			
			}while(confirma != 1);
			consulta.setTratamento(TratamentoDAO.selectTratamentoById(id_trat));
		}

		return tratamento;
	}

	public static Exame insertExame() {
		Scanner input = new Scanner(System.in);
		String des_exame;
		int confirma;

		do {
		System.out.println("\n************ SOLICITAR EXAME ************\n");
		System.out.println("Descrição do exame:");
		des_exame = input.nextLine();

		System.out.println("    Confirma? (0 não / 1 sim ) ");
		confirma = input.nextInt();
		input.nextLine();
		System.out.println("\n*****************************************\n");
		
		}while(confirma != 1);
		
		Exame exame = new Exame();
		exame.setDes_exame(des_exame);
		exame.setConsulta(consulta);
		exames.add(exame);
		consulta.setExames(exames);
		return exame;
	}

	public static void insertVeterianario() {
		Scanner input = new Scanner(System.in);
		Integer id_veterinario,confirma;
		Veterinario veterinario;

		do {
		System.out.println("\n************** VETERINÁRIO **************\n");
		System.out.println("ID do veterinário:");
		id_veterinario = input.nextInt();
		
		System.out.println("    Confirma? (0 não / 1 sim ) ");
		confirma = input.nextInt();
		input.nextLine();
		System.out.println("\n*****************************************\n");
		
		}while(confirma != 1);
		
		
		veterinario = VeterinarioDAO.selectVeterinarioById(id_veterinario);
		consulta.setVeterinario(veterinario);
	}

	public static void registraConsulta() {

		consulta.setDat_con(dataDaConsulta);
		ConsultaDAO.insertConsulta(consulta, op_trat);

	}

}
