package model;

import java.util.ArrayList;
import java.util.List;

public class Tratamento {
	private Integer id_trat;
	private String data_ini;
	private String data_fin;
	private Animal animal;
	private List<Consulta> consultas = new ArrayList<>();
	
	public Tratamento(Integer id_trat, String data_ini, String data_fin, Animal animal, List<Consulta> consultas) {
		super();
		this.id_trat = id_trat;
		this.data_ini = data_ini;
		this.data_fin = data_fin;
		this.animal = animal;
		this.consultas = consultas;
	}
	
	public Tratamento(String data_ini, String data_fin, Animal animal, List<Consulta> consultas) {
		super();
		
		this.data_ini = data_ini;
		this.data_fin = data_fin;
		this.animal = animal;
		this.consultas = consultas;
	}

	public Tratamento() {
		super();
	}

	public Integer getId_trat() {
		return id_trat;
	}

	public void setId_trat(Integer id_trat) {
		this.id_trat = id_trat;
	}

	public String getData_ini() {
		return data_ini;
	}

	public void setData_ini(String data_ini) {
		this.data_ini = data_ini;
	}

	public String getData_fin() {
		return data_fin;
	}

	public void setData_fin(String data_fin) {
		this.data_fin = data_fin;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	@Override
	public String toString() {
		return "\n    Id Tratamento: " + id_trat + 
			   "\n    Data de início: " + data_ini + 
			   "\n    Data de término: " + data_fin + 
			   "\n    Pet: " + animal.getNome_animal() + 
			   "\n    Consultas:\n" + consultas + "\n";
	}
	
	
		
	
}
