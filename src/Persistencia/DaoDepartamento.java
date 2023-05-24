package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Negocio.Departamento;

public class DaoDepartamento implements DaoBasico {

    public DaoDepartamento()  { //contructor
        String inst = "CREATE TABLE IF NOT EXISTS Departamento"
                + " (IdcodDepto INT NOT NULL"
                + ", descricao VARCHAR(45) NOT NULL"
                + ", PRIMARY KEY (IdcodDepto)"
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
        Departamento b = (Departamento) o;
        String inst = "Insert Into Departamento (IdcodDepto, descricao) values(?, ?)";
        try {
        	try (PreparedStatement pS
                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
                    pS.setInt(1, b.getIdcodDepto());
                    pS.setString(2, b.getDescricao());
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
        Departamento b = (Departamento) o;
        String inst = "Update Departamento set descricao= ? where IdcodDepto = ?";
        try {
        	  try (PreparedStatement pS
                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
            	    pS.setString(1, b.getDescricao());
                   pS.setInt(2, b.getIdcodDepto());
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
        Departamento b = (Departamento) o;
        String inst = "Delete From Departamento where IdcodDepto = ?";
        try {
        	  try (PreparedStatement pS
                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
                    pS.setInt(1, b.getIdcodDepto());
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
        String inst = "Select * from Departamento where IdcodDepto = ?";
        Departamento b = null; 
        ResultSet rS;
        
        try {
        	  try (PreparedStatement pS
                     = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
                     pS.setInt(1, codigo);
                     rS = pS.executeQuery();
                     DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                     if (rS.next()) {
                         b = new Departamento();
                         b.setIdcodDepto(rS.getInt("IdcodDepto"));
                         b.setDescricao(rS.getString("Descricao"));
                     }
              }
        } catch (SQLException e) {
              throw new RuntimeException(e.getMessage());
          }
        return (b);
    }
    
    @Override
    public List<Object> carrega() {
        String inst = "Select * From Departamento order by Descricao,IdcodDepto";
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
                        o = busca(rS.getInt("IdcodDepto"), 0);
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
