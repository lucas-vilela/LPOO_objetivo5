package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private Integer id_cli;
	private String nom_cli;
	private String end_cli;
	private String tel_cli;
	private String cep_cli;
	private String email_cli;
	private List<Animal> animais = new ArrayList<>();
	
	
	
	public Cliente(Integer id_cli, String nom_cli, String end_cli, String tel_cli, String cep_cli, String email_cli,
			List<Animal> animais) {
		super();
		this.id_cli = id_cli;
		this.nom_cli = nom_cli;
		this.end_cli = end_cli;
		this.tel_cli = tel_cli;
		this.cep_cli = cep_cli;
		this.email_cli = email_cli;
		this.animais = animais;
	}

	public Cliente() {
		super();
	}

	public Integer getId_cli() {
		return id_cli;
	}

	public void setId_cli(Integer id_cli) {
		this.id_cli = id_cli;
	}

	public String getNom_cli() {
		return nom_cli;
	}

	public void setNom_cli(String nom_cli) {
		this.nom_cli = nom_cli;
	}

	public String getEnd_cli() {
		return end_cli;
	}

	public void setEnd_cli(String end_cli) {
		this.end_cli = end_cli;
	}

	public String getTel_cli() {
		return tel_cli;
	}

	public void setTel_cli(String tel_cli) {
		this.tel_cli = tel_cli;
	}

	public String getCep_cli() {
		return cep_cli;
	}

	public void setCep_cli(String cep_cli) {
		this.cep_cli = cep_cli;
	}

	public String getEmail_cli() {
		return email_cli;
	}

	public void setEmail_cli(String email_cli) {
		this.email_cli = email_cli;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	@Override
	public String toString() {
		return "Cliente [id_cli=" + id_cli + ", nom_cli=" + nom_cli + ", end_cli=" + end_cli + ", tel_cli=" + tel_cli
				+ ", cep_cli=" + cep_cli + ", email_cli=" + email_cli + ", animais=" + animais + "]";
	}
	
	
	
	
	
	
	
	
	
}
