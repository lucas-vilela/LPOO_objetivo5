package model;

public class Exame {

	private Integer id_exame;
	private String des_exame;
	
	
	
	public Exame(Integer id_exame, String des_exame) {
		super();
		this.id_exame = id_exame;
		this.des_exame = des_exame;
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

	@Override
	public String toString() {
		return "Exame [id_exame=" + id_exame + ", des_exame=" + des_exame + "]";
	}

	
	
	
}
