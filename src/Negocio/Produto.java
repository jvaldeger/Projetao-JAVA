package Negocio;

import Util.Diversos;

public class Produto {
	private String nome;
	private String dataV;
	private double valor;
	private String tipo;
	private int Codigo;
	
	public int getCodigo() {
		return Codigo;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataV() {
		return dataV;
	}

	public void setDataV(String dataV) {
		this.dataV = dataV;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	// public String relatorio(){
	// String resp = "";

	// resp = nome + " com o n�mero " + numero + '\n';
	// resp += "Localizado no bairro " + bairro.getDescricao();
	// resp += " com o c�digo " + bairro.getCodigo() + '\n';
	// resp += "Sendo do tipo ";
	// resp += (tipo == 'C' ? "Comercial" : tipo == 'I' ? "Industrial" :
	// "Residencial");
	// resp += " com a quantidade consumida em kWh(s) de " + quantKwh +'\n';
	// resp += "O pre�o do consumo � de " +
	// Diversos.doisDigitos(1).format(precoKwh) +'\n';
	// resp += "Sendo o pre�o a pagar de " +
	// Diversos.doisDigitos(1).format(precoReal()) +'\n';
	// resp +=(atraso ? "Estando em atraso\n" : "N�o estando em atraso\n");
	// resp +=
	// "\n_____________________________________________________________\n\n";

	// return resp;
}

// }

