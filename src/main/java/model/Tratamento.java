package model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Tratamento {
	private Integer id_trat;
	private Calendar data_ini;
	private Calendar data_fin;
	private Animal animal;
	private List<Consulta> consultas = new ArrayList<>();
	
	public Tratamento(Integer id_trat, Calendar data_ini, Calendar data_fin, Animal animal, List<Consulta> consultas) {
		super();
		this.id_trat = id_trat;
		this.data_ini = data_ini;
		this.data_fin = data_fin;
		this.animal = animal;
		this.consultas = consultas;
	}
	
	public Tratamento(Calendar data_ini, Calendar data_fin, Animal animal, List<Consulta> consultas) {
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

	public Calendar getData_ini() {
		return data_ini;
	}

	public void setData_ini(Calendar data_ini) {
		this.data_ini = data_ini;
	}

	public Calendar getData_fin() {
		return data_ini;
	}

	public void setData_fin(Calendar data_fin) {
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
			   "\n    Data de início: " + calendarToString(data_ini) + 
			   "\n    Data de término: " + calendarToString(data_fin) + 
			   "\n    Pet: " + animal.getNome_animal() + 
			   "\n    Consultas:\n" + consultas + "\n";
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
