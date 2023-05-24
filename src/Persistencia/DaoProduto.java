package Persistencia;

import Negocio.Departamento;
import Negocio.Fornecedor;
import Negocio.Funcionario;
import Negocio.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoProduto implements DaoBasico {

    public DaoProduto()  { //cCntructor
        String inst = "CREATE TABLE IF NOT EXISTS Consumidor"
                + ", Nome VARCHAR(45) String NOT NULL"
                //+ ", Bairro VARCHAR(25) NOT NULL"
                + ", Tipo VARCHAR(12) NOT NULL"
                + ", DataV VARCHAR(10) NOT NULL"
                + ", PrecCKwh FLOAT NOT NULL"
                + ", Atraso TINYINT(1) NOT NULL"
                + ", PRIMARY KEY (Codigo)"
                + ", CONSTRAINT Codigo FOREIGN KEY (Codigo) REFERENCES Bairro (Codigo)"
                + ", CONSTRAINT Codigo FOREIGN KEY (Codigo) REFERENCES Bairro (Codigo)"
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
        Produto cC = (Produto) o;
        String inst = "Insert Into Consumidor ";
        inst += "(Numero, Codigo, Nome, QuantKwh, Tipo, DataV, PrecoKwh, Atraso) ";
        inst += "values(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection con = DaoConexao.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setString(1, cC.getNome());
                pS.setString(2, cC.getDataV());
                pS.setString(3, cC.getTipo());
                pS.setDouble(4,cC.getValor());
            	pS.setInt(5, cC.getCodigo());
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
        Produto cC = (Produto) o;
        String inst = "Update Consumidor set Codigo= ?, Nome = ? ";
        inst += "QuantKwh = ?, Tipo = ?, DataV = ?, PrecoKwh = ?, Atraso = ? ";
        inst += "where Numero = ?";
        try {
        	Connection con = DaoConexao.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setString(1, cC.getNome());
                pS.setString(2, cC.getDataV());
                pS.setString(3, cC.getTipo());
                pS.setDouble(4,cC.getValor());
            	pS.setInt(5, cC.getCodigo());
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
        String inst = "Delete From Consumidor where Numero = ?";
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
        String inst = "Select * from Consumidor where Numero = ?";
        Produto cC = null; 
        ResultSet rS;
        
        try {
            Connection cCn = DaoConexao.getInstancia().getCon();
            try (PreparedStatement pS = cCn.prepareStatement(inst)) {
                pS.setInt(1, numero);              
                rS = pS.executeQuery();
                DaoConexao.getInstancia().setCon(cCn);
                if (rS.next()) {
                    cC = new Produto();
                    DaoFornecedor dB = new DaoFornecedor();
                    cC.setCodigo(rS.getInt("IdcodDepto"));
                    Object o = dB.busca(rS.getInt("IdcodDepto"), 0);
                    Fornecedor b = (Fornecedor) o;
                   // cC.setBairro(b); 
                    cC.setNome(rS.getString("Nome"));
                    cC.setDataV(rS.getString("DataV"));
                    cC.setTipo(rS.getString("Tipo"));
                    cC.setCodigo(rS.getInt("Tipo"));
                    cC.setValor(rS.getDouble("Double"));
                    
               }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
          }
        return (cC);
    }
    
    @Override
    public List<Object> carrega() {
        String inst = "Select * From Consumidor order by Nome";
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
                        o = busca(rS.getInt("Numero"), 0);
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
