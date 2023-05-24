package Negocio;

import Util.Diversos;

public class Cargo {
	private int codCargo;
	private String cargo;
	private double salario;
	private String cargahoraria;

	public int getCodCargo() {
		return codCargo;
	}

	public void setCodCargo(int codCargo) {
		this.codCargo = codCargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getCargahoraria() {
		return cargahoraria;
	}

	public void setCargahoraria(String cargahoraria) {
		this.cargahoraria = cargahoraria;
	}

	// public String relatorio(){
	// String resp = "";

	// resp = descricao + " com o código " + codigo + '\n';
	// resp +=
	// "\n_____________________________________________________________\n\n";

	// return resp;
	// }
}
