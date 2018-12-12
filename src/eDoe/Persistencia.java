package eDoe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;

import eDoe.controllers.CrudUsuario;
import eDoe.controllers.GestorItem;
import eDoe.models.Usuario;

public class Persistencia {

	/**
	 * 
	 * @param usuarios          o mapa de usuarios que foram cadastrado no sistema.
	 * @param doacoesRealizadas a lista de doações que foram realizadas pelo
	 *                          sistema.
	 * @param descritores       o mapa de descritores que foram cadastrado no
	 *                          sistema.
	 */
	public void salvar(Map<String, Usuario> usuarios, ArrayList<String> doacoesRealizadas,
			Map<String, Integer> descritores) {
		try {
			FileOutputStream fos = new FileOutputStream("dados.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(usuarios);
			oos.writeObject(doacoesRealizadas);
			oos.writeObject(descritores);

			oos.close();
		}

		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Carrega os dados de execucoes passadas para a atual execucao do programa.
	 * Cada controlador executa um setData a partir da leituras dos objetos
	 * armazenadas nas execucoes passadas.
	 * 
	 * @param uc controlador de usuarios.
	 * @param gi gestor de itens.
	 */
	@SuppressWarnings("unchecked")
	public void carregar(CrudUsuario uc, GestorItem gi) {
		ObjectInputStream ois = null;
		try {
			if (!new File("dados.txt").exists()) {
				FileOutputStream fos = new FileOutputStream("dados.txt");
				fos.close();
			}

			FileInputStream fis = new FileInputStream("dados.txt");

			if (fis.available() > 0) {
				ois = new ObjectInputStream(fis);
				uc.carregaUsuarios((Map<String, Usuario>) ois.readObject());
				uc.carregaDoacoes((ArrayList<String>) ois.readObject());
				gi.carregaDescritores((Map<String, Integer>) ois.readObject());
				ois.close();
			}

		} catch (IOException | ClassNotFoundException ioecnfe) {
			ioecnfe.printStackTrace();
		}

	}

}
