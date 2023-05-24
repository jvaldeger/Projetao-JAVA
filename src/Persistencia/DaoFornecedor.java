


	package Persistencia;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

	import Negocio.Fornecedor;

	public class DaoFornecedor implements DaoBasico {

	    public DaoFornecedor()  { //contructor
	        String inst = "CREATE TABLE IF NOT EXISTS Fornecedor"
	                + " (codFornecedor INT NOT NULL"
	                + ", nome VARCHAR(45) NOT NULL"
	                + ", Cpf VARCHAR(45) NOT NULL"
	                + ", Cidade VARCHAR(45) NOT NULL"
	                + ", Estado VARCHAR(45) NOT NULL"
	                + ", PRIMARY KEY (codFornecedor)"
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
	        Fornecedor b = (Fornecedor) o;
	        String inst = "Insert Into Fornecedor (codFornecedor, nome,Cpf,Cidade,Estado) values(?, ?)";
	        try {
	        	try (PreparedStatement pS
	                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
	                    pS.setInt(1, b.getCodFornecedor());
	                    pS.setString(2, b.getNome());
	                    pS.setString(3, b.getCpf());
	                    pS.setString(4, b.getCidade());
	                    pS.setString(5, b.getEstado());
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
	        Fornecedor b = (Fornecedor) o;
	        String inst = "Update Fornecedor set Nome = ? where codFornecedor = ?";
	        try {
	        	  try (PreparedStatement pS
	                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
	        		  pS.setInt(1, b.getCodFornecedor());
	                    pS.setString(2, b.getNome());
	                    pS.setString(3, b.getCpf());
	                    pS.setString(4, b.getCidade());
	                    pS.setString(5, b.getEstado());
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
	        Fornecedor b = (Fornecedor) o;
	        String inst = "Delete From Fornecedor where codFornecedor = ?";
	        try {
	        	  try (PreparedStatement pS
	                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
	                    pS.setInt(1, b.getCodFornecedor());
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
	    public Object busca(int CodFornecedor, int nada) {
	        String inst = "Select * from Fornecedor where codFornecedor = ?";
	        Fornecedor b = null; 
	        ResultSet rS;
	        
	        try {
	        	  try (PreparedStatement pS
	                     = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
	                     pS.setInt(1,CodFornecedor );
	                     rS = pS.executeQuery();
	                     DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
	                     if (rS.next()) {
	                         b = new Fornecedor();
	                         b.setCodFornecedor(rS.getInt("CodFornecedor"));
	                         b.setNome(rS.getString("Nome"));
	                         b.setCpf(rS.getString("Cpf"));
	                         b.setCidade(rS.getString("Cidade"));
	                         b.setEstado(rS.getString("Estado"));
	                     }
	              }
	        } catch (SQLException e) {
	              throw new RuntimeException(e.getMessage());
	          }
	        return (b);
	    }
	    
	    @Override
	    public List<Object> carrega() {
	        String inst = "Select * From Fornecedor order by codFornecedor,nome,Cpf,Cidade,Estado";
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
	                        o = busca(rS.getInt("CodFornecedor"), 0);
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



