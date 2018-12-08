package eDoe.models;

public class Receptor extends Usuario {

	private String status;

	public Receptor(String documento, String nome, String email, String celular, String classe, String status) {
		super(documento, nome, email, celular, classe);
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public String toString() {
		return this.nome + "/" + this.documento + ", " + this.email + ", " + this.celular + ", status: "
				+ this.status.toLowerCase();
	}

}
