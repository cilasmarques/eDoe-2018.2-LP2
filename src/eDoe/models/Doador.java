package eDoe.models;

public class Doador extends Usuario{

	private String status;

	public Doador(String documento, String nome, String email, String celular, String classe, String status) {
		super(documento, nome, email, celular, classe);
		this.status = status;
	}

	public String toString() {
		return this.nome + "/" + this.documento + ", " + this.email + ", " + this.celular + ", status: " + this.status;
	}

	public String getStatus() {
		return this.status;
	}
	
}
