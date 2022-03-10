package model;

import java.util.ArrayList;
import java.util.List;

public class Consulta {

	private Integer id_con;
	private String dat_con;
	private String historico;
	private Tratamento tratamento;
	private Veterinario veterinario;
	private List<Exame> exames = new ArrayList<>();

	public Consulta(Integer id_con, String dat_con, String historico, Tratamento tratamento, Veterinario veterinario,
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

	public String getDat_con() {
		return dat_con;
	}

	public void setDat_con(String dat_con) {
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
		return "Consulta [id_con=" + id_con + ", dat_con=" + dat_con + ", historico=" + historico + ", tratamento="
				+ tratamento + ", veterinario=" + veterinario + ", exames=" + exames + "]";
	}

}
