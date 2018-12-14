package eDoe.models;

/**
 * Classe que contem os metodos e atributos usuario doador
 * 
 * @author Cilas Medeiros, Brenno Harten, Raiff Maia
 */
public class Doador extends Usuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status;

	/**
	 * Constutor do doador
	 * 
	 * @param documento Documento de identificação do doador
	 * @param nome      Nome do doador
	 * @param email     Email do doador
	 * @param celular   Celular do doador
	 * @param classe    Classe do doador
	 * @param status    Status do doador (é esperado que seja "doador")
	 */
	public Doador(String documento, String nome, String email, String celular, String classe, String status) {
		super(documento, nome, email, celular, classe);
		if (!status.equals("doador"))
			throw new IllegalArgumentException("Usuário nao eh um doador");
		this.status = status;
	}

	/**
	 * Método que retorna o status do doador
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
		return this.nome + "/" + this.documento + ", " + this.email + ", " + this.celular + ", status: " + this.status;
	}

}
