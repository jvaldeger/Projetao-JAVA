package Negocio;

import Util.Diversos;

public class Departamento {
	private int IdcodDepto;
	private String descricao;

	public int getIdcodDepto() {
		return IdcodDepto;
	}

	public void setIdcodDepto(int idcodDepto) {
		IdcodDepto = idcodDepto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	// public String relatorio(){
	// String resp = "";

	// resp = descricao + " com o código " + codigo + '\n';
	// resp +=
	// "\n_____________________________________________________________\n\n";

	// return resp;
	// }
}
