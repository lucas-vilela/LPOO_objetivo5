package model;

import java.util.ArrayList;
import java.util.List;

public class Animal {

	private Integer id_animal;
	private String nome_animal;
	private Integer idade_animal;
	private Integer sexo_animal;
	private Especie especie;
	private Cliente cliente;
	private List<Tratamento> tratamentos = new ArrayList<>();
	
	
	public Animal(Integer id_animal, String nome_animal, Integer idade_animal, Integer sexo_animal, Especie especie,
			Cliente cliente, List<Tratamento> tratamentos) {
		super();
		this.id_animal = id_animal;
		this.nome_animal = nome_animal;
		this.idade_animal = idade_animal;
		this.sexo_animal = sexo_animal;
		this.especie = especie;
		this.cliente = cliente;
		this.tratamentos = tratamentos;
	}

	public Animal() {
		super();
	}

	public Integer getId_animal() {
		return id_animal;
	}

	public void setId_animal(Integer id_animal) {
		this.id_animal = id_animal;
	}

	public String getNome_animal() {
		return nome_animal;
	}

	public void setNome_animal(String nome_animal) {
		this.nome_animal = nome_animal;
	}

	public Integer getIdade_animal() {
		return idade_animal;
	}

	public void setIdade_animal(Integer idade_animal) {
		this.idade_animal = idade_animal;
	}

	public Integer getSexo_animal() {
		return sexo_animal;
	}

	public void setSexo_animal(Integer sexo_animal) {
		this.sexo_animal = sexo_animal;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Tratamento> getTratamentos() {
		return tratamentos;
	}

	public void setTratamentos(List<Tratamento> tratamentos) {
		this.tratamentos = tratamentos;
	}

	@Override
	public String toString() {
		return"\n\n    ID do pet: " + id_animal + 
			    "\n    Nome: " + nome_animal + 
			    "\n    Idade: " + idade_animal + 
			    "\n    Sexo (0-F�mea / 1-Macho) : " + sexo_animal + 
			    "\n    Especie: " + especie.getNom_esp() + 
			    "\n    Dono(a): "  + (cliente != null ? cliente.getNom_cli() : "") +
			    "\n    Tratamentos: " + tratamentos;
	}
	
	
	

	
}
