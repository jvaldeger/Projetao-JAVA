
package Persistencia;

import Negocio.Departamento;
import Negocio.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoFuncionario implements DaoBasico {

    public DaoFuncionario()  { //cCntructor
        String inst = "CREATE TABLE IF NOT EXISTS Funcionario"
                + ", Nome VARCHAR(45) String NOT NULL"
                + ", telefone VARCHAR(25) NOT NULL"
                + ", cpf VARCHAR(12) NOT NULL"
                + ", bairro VARCHAR (20) NOT NULL"
                + ", DataV VARCHAR(10) NOT NULL"
                + " (codCargo INT NOT NULL"
                + " (codDepartamento INT NOT NULL"
                + ", PRIMARY KEY (Matricula)"
                + ", CONSTRAINT CodigoCAR FOREIGN KEY (codCargo) REFERENCES Cargo(codCargo)"
                + ", CONSTRAINT CodigoDEP FOREIGN KEY (codDepartamento) REFERENCES Departamento (codDepartamento)"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
  
        try {
            Connection con =  DaoConexao.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.execute();
            }
            DaoConexao.getInstancia().setCon(con);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
          }
    }

    @Override
    public boolean incluir(Object o) {
        boolean result = true;
        Funcionario cC = (Funcionario) o;
        String inst = "Insert Into Funcionario";
        inst +=  "(Nome, telefone, cpf, bairro,DataV, codCargo, codDepartamento) ";
        inst += "values(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection con = DaoConexao.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
            	pS.setInt(1, cC.getMatricula());
                pS.setString(2, cC.getNome());
                pS.setString(3, cC.getBairro());
                pS.setString(4, cC.getCpf());	
                pS.setString(5, cC.getTelefone());
                pS.setString(6, cC.getDataV());
                pS.execute();
            }
            DaoConexao.getInstancia().setCon(con);
        } catch (SQLException e) {
               result = false;
               throw new RuntimeException(e.getMessage());             
          }
        return (result);
    }

    @Override
    public boolean alterar(Object o) {
        boolean result = true;
        Funcionario cC = (Funcionario) o;
        String inst = "Update Funcionario set Codigo= ?, Nome = ? ";
        inst += "telefone = ?, cpf = ?, DataV = ?, codCargo = ?, codDepartamento = ? ";
        inst += "where Matricula = ?";
        try {
        	Connection con = DaoConexao.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
            	pS.setInt(1,cC.getDepartamento().getIdcodDepto());
            	pS.setInt(1, cC.getMatricula());
                pS.setString(2, cC.getNome());
                pS.setString(3, cC.getBairro());
                pS.setString(4, cC.getCpf());	
                pS.setString(5, cC.getTelefone());
                pS.setString(6, cC.getDataV());
                pS.execute();
            }
            
            DaoConexao.getInstancia().setCon(con);
        } catch (SQLException e) {
                    result = false;
            throw new RuntimeException(e.getMessage());
        }
        return (result);
    }

    @Override
    public boolean excluir(Object o) {
        boolean result = true;
        Funcionario cC = (Funcionario) o;
        String inst = "Delete From Funcionario where Matricula = ?";
        try {
            Connection cCn = DaoConexao.getInstancia().getCon();
            try (PreparedStatement pS = cCn.prepareStatement(inst)) {
                pS.setInt(1, cC.getMatricula());
                pS.execute();
            }
            DaoConexao.getInstancia().setCon(cCn);
        } catch (SQLException e) {
            result = false;
            throw new RuntimeException(e.getMessage());
          }
        return (result);
    }

    @Override
    public Object busca(int numero, int nada) {
        String inst = "Select * from Funcionario where Matricula = ?";
        Funcionario cC = null; 
        ResultSet rS;
        
        try {
            Connection cCn = DaoConexao.getInstancia().getCon();
            try (PreparedStatement pS = cCn.prepareStatement(inst)) {
                pS.setInt(1, numero);
                rS = pS.executeQuery();
                DaoConexao.getInstancia().setCon(cCn);
                if (rS.next()) {
                    cC = new Funcionario();
                    DaoDepartamento dB = new DaoDepartamento();
                    cC.setMatricula(rS.getInt("Matricula"));
                    Object o = dB.busca(rS.getInt("Codigo"), 0);
                    Departamento b = (Departamento) o;
                   // cC.setBairro(b); 
                    cC.setNome(rS.getString("Nome"));
                    cC.setBairro(rS.getString("Bairro"));
                    cC.setCpf(rS.getString("CPF"));
                    cC.setTelefone(rS.getString("Telefone"));
                    cC.setDataV(rS.getString("DataV"));
                    
               }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
          }
        return (cC);
    }
    
    @Override
    public List<Object> carrega() {
        String inst = "Select * From Funcionario order by Nome";
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;

        try {
            try (PreparedStatement pS
                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
                rS = pS.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if (rS != null) 
                    while (rS.next()) {
                        o = busca(rS.getInt("Matricula"), 0);
                        lista.add(o);
                    }               
                pS.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return (lista);
    }
}
