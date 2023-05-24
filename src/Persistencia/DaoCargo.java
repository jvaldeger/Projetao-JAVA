package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Negocio.Cargo;
import Negocio.Departamento;

public class DaoCargo implements DaoBasico {

    public DaoCargo()  { //contructor
        String inst = "CREATE TABLE IF NOT EXISTS Cargo"
                + " (CodCargo INT NOT NULL"
                + ", Cargo VARCHAR(45) NOT NULL"
                + ", PRIMARY KEY (codCargo)"
                + " (SALARIO DOUBLE NOT NULL"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
  
        try {
        	try (PreparedStatement pS
                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
                pS.execute();
                 
             }
        	DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
          }
    }

    @Override
    public boolean incluir(Object o) {
        boolean result = true;
        Cargo b = (Cargo) o;
        String inst = "Insert Into Cargo (CodCargo, Cargahoraria,Cargo,Salario) values(?, ?)";
        try {
        	try (PreparedStatement pS
                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
                    pS.setInt(1, b.getCodCargo());
                    pS.setString(2, b.getCargo());
                    pS.setDouble(2, b.getSalario());
                    pS.setString(2, b.getCargahoraria());
                    pS.execute();
              }
          DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
          } catch (SQLException e) {
               result = false;
               throw new RuntimeException(e.getMessage());             
            }
        return (result);
    }

    @Override
    public boolean alterar(Object o) {
        boolean result = true;
        Cargo b = (Cargo) o;
        String inst = "Update CARGO set Cargo ? where  =  CodCargo?";
        try {
        	  try (PreparedStatement pS
                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
        		  pS.setInt(1, b.getCodCargo());
                  pS.setString(2, b.getCargo());
                  pS.setDouble(2, b.getSalario());
                  pS.setString(2, b.getCargahoraria());
                  pS.execute();
               }    
        	  DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
        } catch (SQLException e) {
                    result = false;
                   throw new RuntimeException(e.getMessage());
          }
       return (result);
    }

    @Override
    public boolean excluir(Object o) {
        boolean result = true;
        Cargo b = (Cargo) o;
        String inst = "Delete From Cargo where CodCargo = ?";
        try {
        	  try (PreparedStatement pS
                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
                    pS.setInt(1, b.getCodCargo());
                   pS.execute();
            }
          DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
        } catch (SQLException e) {
              result = false;
              throw new RuntimeException(e.getMessage());
          }
        return (result);
    }

    @Override
    public Object busca(int codigo, int nada) {
        String inst = "Select * from Cargo where CodCargo = ?";
        Cargo b = null; 
        ResultSet rS;
        
        try {
        	  try (PreparedStatement pS
                     = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
                     pS.setInt(1, codigo);
                     rS = pS.executeQuery();
                     DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                     if (rS.next()) {
                         b = new Cargo();
                         b.setCodCargo(rS.getInt("codCargo"));
                         b.setCargo(rS.getString("Cargo"));
                         b.setSalario(rS.getDouble("Salario"));
                         b.setCargahoraria(rS.getString("Cargahoraria"));
                     }
              }
        } catch (SQLException e) {
              throw new RuntimeException(e.getMessage());
          }
        return (b);
    }
    
    @Override
    public List<Object> carrega() {
        String inst = "Select * From Cargo order by Cargohoraria,Salario,CodCargo";
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;

        try {
            try (PreparedStatement pS
                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
                    rS = pS.executeQuery(inst);
                    DaoConexao.getInstancia().setCon
                                                             (DaoConexao.getInstancia().getCon());
                    if (rS != null) 
                        while (rS.next()) {
                        o = busca(rS.getInt("CodCargo"), 0);
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
