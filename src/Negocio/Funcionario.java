package Negocio;

import Util.Diversos;

public class Funcionario {
	private String nome;
	private String telefone;
	private Departamento departamento;
	private Cargo cargo;
	private String cpf;
	private String bairro;
	private String dataV;
	private int codCargo;
	private int codDepartamento;
	private int matricula;
	
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getDataV() {
		return dataV;
	}

	public void setDataV(String dataV) {
		this.dataV = dataV;
	}

	public int getCodCargo() {
		return codCargo;
	}

	public void setCodCargo(int codCargo) {
		this.codCargo = codCargo;
	}

	public int getCodDepartamento() {
		return codDepartamento;
	}

	public void setCodDepartamento(int codDepartamento) {
		this.codDepartamento = codDepartamento;
	}

	 public void relatorio(){
	 //String resp = "";

	// resp = nome + " com o matricula" + matricula + '\n';
 //resp += "Localizado no bairro " + bairro.getDepartamento();
	 //resp += " com o código " + bairro.getCodigo() + '\n';
	 //resp += "Sendo do tipo ";
	 //resp += (tipo == 'C' ? "Comercial" : tipo == 'I' ? "Industrial" :
	 //"Residencial");
	 //resp += " com a quantidade consumida em kWh(s) de " + quantKwh +'\n';
	 //resp += "O preço do consumo é de " +
	 //Diversos.doisDigitos(1).format(precoKwh) +'\n';
	 //resp += "Sendo o preço a pagar de " +
	 //Diversos.doisDigitos(1).format(precoReal()) +'\n';
	 //resp +=(atraso ? "Estando em atraso\n" : "Não estando em atraso\n");
	 //resp +=
	 //"\n_____________________________________________________________\n\n";

	 //return resp;
}
}
// }

