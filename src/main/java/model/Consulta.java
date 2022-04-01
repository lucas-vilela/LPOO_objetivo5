package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dao.AnimalDAO;

public class Consulta {

	private Integer id_con;
	private Calendar dat_con;
	private String historico;
	private Tratamento tratamento;
	private Veterinario veterinario;
	private List<Exame> exames = new ArrayList<>();

	public Consulta(Integer id_con, Calendar dat_con, String historico, Tratamento tratamento, Veterinario veterinario,
			List<Exame> exames) {
		super();
		this.id_con = id_con;
		this.dat_con = dat_con;
		this.historico = historico;
		this.tratamento = tratamento;
		this.veterinario = veterinario;
		this.exames = exames;
	}

	public Consulta() {
		super();
	}

	public Integer getId_con() {
		return id_con;
	}

	public void setId_con(Integer id_con) {
		this.id_con = id_con;
	}

	public Calendar getDat_con() {
		return dat_con;
	}

	public void setDat_con(Calendar dat_con) {
		this.dat_con = dat_con;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public Tratamento getTratamento() {
		return tratamento;
	}

	public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	@Override
	public String toString() {
		return "\n    Id Consulta: " + id_con + 
			   "\n    Data da Consulta: " + calendarToString(dat_con) + 
			   "\n    Historico: " + historico + 
			   "\n    Id Tratamento: "+ tratamento.getId_trat() +
			   "\n    Pet: "+ AnimalDAO.selectAnimalById(tratamento.getAnimal().getId_animal()).getNome_animal() + 
			   "\n    Veterinario(a)=" + veterinario.getNom_vet() +
			   "\n    Exames=" + exames + "\n";// TENTAR FAZER UM FOR POR INDICES DA LISTA DE EXAMES COM O TOSTRING MODIFICADO
	}
	
	//método utilitário
	
			private static String calendarToString(Calendar date) {
				if(date != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
					return sdf.format(date.getTime());
				}
				return "00/00/0000"; 
			}

}
