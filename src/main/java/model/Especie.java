package model;

import java.util.ArrayList;
import java.util.List;

public class Especie {

	private Integer id_esp;
	private String nom_esp;
	private List<Animal> animais = new ArrayList<>();
	
	
	
	public Especie(Integer id_esp, String nom_esp, List<Animal> animais) {
		super();
		this.id_esp = id_esp;
		this.nom_esp = nom_esp;
		this.animais = animais;
	}

	public Especie() {
		super();
	}

	public Integer getId_esp() {
		return id_esp;
	}

	public void setId_esp(Integer id_esp) {
		this.id_esp = id_esp;
	}

	public String getNom_esp() {
		return nom_esp;
	}

	public void setNom_esp(String nom_esp) {
		this.nom_esp = nom_esp;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	@Override
	public String toString() {
		return "Especie [id_esp=" + id_esp + ", nom_esp=" + nom_esp + ", animais=" + animais + "]";
	}
	
	
	
}
