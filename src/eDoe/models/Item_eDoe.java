package eDoe.models;

import java.util.ArrayList;

public interface Item_eDoe {

	public boolean ehNecessario();

	public int getId();

	public int getQuantidade();

	public int getPontuacao();

	public void setPontuacao(int pontuacao);

	public String getDescricao();

	public ArrayList<String> getTags();

}
