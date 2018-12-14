package eDoe.models;

/**
 * Classe que contem os metodos e atributos usuario receptor
 * 
 * @author Cilas Medeiros, Brenno Harten, Raiff Maia
 */
public class Receptor extends Usuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String status;

	/**
	 * Constutor do receptor
	 * 
	 * @param documento Documento de identificação do receptor
	 * @param nome      Nome do receptor
	 * @param email     Email do receptor
	 * @param celular   Celular do receptor
	 * @param classe    Classe do receptor
	 * @param status    Status do receptor (é esperado que seja "Receptor")
	 */
	public Receptor(String documento, String nome, String email, String celular, String classe, String status) {
		super(documento, nome, email, celular, classe);
		if (!status.equals("Receptor"))
			throw new IllegalArgumentException("Usuário nao eh um receptor.");
		this.status = status;
	}

	/**
	 * Método que retorna o estatus do doador
	 * 
	 * @return String com status do doador
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * toString, formado pelo: nome, documento de identificação, email, celular e
	 * status
	 */
	public String toString() {
		return this.nome + "/" + this.documento + ", " + this.email + ", " + this.celular + ", status: "
				+ this.status.toLowerCase();
	}

}
