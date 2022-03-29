package model;



public class Exame {

	private Integer id_exame;
	private String des_exame;
	private Consulta consulta;
	
	
	public Exame(Integer id_exame, String des_exame, Consulta consulta) {
		super();
		this.id_exame = id_exame;
		this.des_exame = des_exame;
		this.consulta = consulta;
	}

	public Exame() {
		super();
	}

	public Integer getId_exame() {
		return id_exame;
	}

	public void setId_exame(Integer id_exame) {
		this.id_exame = id_exame;
	}

	public String getDes_exame() {
		return des_exame;
	}

	public void setDes_exame(String des_exame) {
		this.des_exame = des_exame;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	
	public String toStringSconsulta() {//TENTAR USAR ESSE TOSTRING EM UMA LISTA DENTRO DO TOSTRING DE CONSULTA
		return "\n    Id Exame: " + id_exame + 
			     "    Descrição: " + des_exame;
	}
	
	public String toString() {
		return "\n    Id Exame: " + id_exame + 
			     "    Descrição: " + des_exame ;
			  // "\n    Id Consulta: " + consulta.getId_con() + "\n"; MANTENDO ESSE ID GERA PROBLEMA NA LISTAGEM DE CONSULTAS
	}
	
	
	
	
	
	
	
	

	
	
	
}
