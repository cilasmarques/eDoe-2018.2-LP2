package eDoe.models;

/**
 * Interface dos usuarios do sistema eDoe
 * 
 * @author Cilas Medeiros, Brenno Harten, Raiff Maia
 */
public interface Usuario_eDoe {

	public void setNome(String nome);

	public void setEmail(String email);

	public void setCelular(String celular);

	public String getStatus();

	public String toString();

}
