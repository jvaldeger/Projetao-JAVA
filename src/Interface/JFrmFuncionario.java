package Interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import Controle.ControleBasico;
import Controle.ControleGeral;
import Negocio.Cargo;
import Negocio.Departamento;
import Negocio.Funcionario;
import Util.Diversos;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import java.awt.ComponentOrientation;

import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.ImageIcon;

public class JFrmFuncionario extends JFrame {

	private JPanel jContentPane;
	private JTextField jTxtNome;
	private JTextField jTxtMatr;
	private JTextField jTxtcpf;
	private JTextField jTxtbairro;
	private JTextField jTxtdataV;
	private JTextField jTxtcodCargo;
	private JTextField jTxtcodDepartamento;
	private JTextField jTxtMatricula;
	private JComboBox<String> jCmbCargo;
	private JFormattedTextField jFtdTxtDataV;
    private JButton jBtnIncluir;
    private JButton jBtnAlterar;
    private JButton jBtnExcluir;
    private final ButtonGroup jButtonGroup = new ButtonGroup();
    private JTextArea jTxtARelatorio;
    private JButton jBtnRelatorio;
    
    private final String titulo;
    private ControleBasico cB, cC;  
    private List<Object> listaB, listaC;
    private JLabel lblTelefone;
    private JTextField jtxtTel;
    private JTextField jtxtCpf;
    private JTextField jtxtBairro;
    private JLabel lblCoddepartamento;
    private JTextField jtxtDepartamento;
    private JLabel jblCodCargo;
    private JLabel JlblCarg;
   
    

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	 	EventQueue.invokeLater(new Runnable() {
	  		public void run() {
				try {
					JFrmFuncionario frame = new JFrmFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
			      }
	      }
    	});
	}

	/**
	 * Create the frame.
	 */
	public JFrmFuncionario() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				carregaLista();
		        limpar();
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
				jTxtNome.requestFocusInWindow();
			}
		});
		titulo = "Cadastrar consumidores";
		this.cB = new ControleGeral(2);
		this.cC = new ControleGeral(1);
		setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		setTitle("Cadastrar consumidores");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 768, 466);
		jContentPane = new JPanel();
		jContentPane.setForeground(new Color(30, 144, 255));
		jContentPane.setBackground(Color.CYAN);
		jContentPane.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setContentPane(jContentPane);
		jContentPane.setLayout(null);
		
		JPanel jPnlBotoes = new JPanel();
		jPnlBotoes.setBackground(Color.CYAN);
		jPnlBotoes.setBounds(56, 240, 696, 65);
		jPnlBotoes.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jPnlBotoes);
		jPnlBotoes.setLayout(null);
		
		jBtnIncluir = new JButton("Incluir");
		jBtnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarDados('I');			
			}
		});
		jBtnIncluir.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnIncluir.setBounds(12, 23, 84, 25);
		jPnlBotoes.add(jBtnIncluir);
		
		JButton jBtnLimpar = new JButton("Limpar");
		jBtnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		jBtnLimpar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnLimpar.setBounds(415, 23, 84, 25);
		jPnlBotoes.add(jBtnLimpar);
		
		JButton jBtnRetornar = new JButton("Retornar");
		jBtnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja retornar", titulo))
					dispose();
			}
		});
		jBtnRetornar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnRetornar.setBounds(509, 23, 106, 25);
		jPnlBotoes.add(jBtnRetornar);
		
		jBtnAlterar = new JButton("Alterar");
		jBtnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrarDados('A');
			}
		});
		jBtnAlterar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnAlterar.setBounds(106, 23, 84, 25);
		jPnlBotoes.add(jBtnAlterar);
		
		jBtnExcluir = new JButton("Excluir");
		jBtnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Diversos.confirmar("Deseja excluir", titulo))
					cadastrarDados('E');
			}
		});
		jBtnExcluir.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnExcluir.setBounds(200, 23, 84, 25);
		jPnlBotoes.add(jBtnExcluir);
		
		jBtnRelatorio = new JButton("Relat\u00F3rio");
		jBtnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  relatorioGeral();	
			}
		});
		jBtnRelatorio.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnRelatorio.setBounds(294, 23, 111, 25);
		jPnlBotoes.add(jBtnRelatorio);
		
		JButton jBtnBairro = new JButton("");
		jBtnBairro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrmDepartamento frame = new JFrmDepartamento();
				frame.setVisible(true);
			}
		});
		jBtnBairro.setIcon(new ImageIcon("C:\\Users\\aluno.info\\workspace\\ConsumidorBairro\\Imagem\\search.png"));
		jBtnBairro.setToolTipText("Bairro");
		jBtnBairro.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnBairro.setBounds(624, 23, 57, 25);
		jPnlBotoes.add(jBtnBairro);
		
		JLabel jLblMatricula = new JLabel("Matricula");
		jLblMatricula.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblMatricula.setHorizontalAlignment(SwingConstants.CENTER);
		jLblMatricula.setBounds(0, 5, 94, 15);
		jLblMatricula.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.add(jLblMatricula);
		
		jTxtMatr = new JTextField();
		jTxtMatr.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				  pesquisa();
			}
			
		});
		jTxtMatr.setBounds(89, 0, 81, 25);
		jTxtMatr.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtMatr.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtMatr.setHorizontalAlignment(SwingConstants.CENTER);
		jContentPane.add(jTxtMatr);
		jTxtMatr.setColumns(10);
		
		JLabel jLblNome = new JLabel("Nome");
		jLblNome.setBounds(10, 36, 62, 15);
		jLblNome.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblNome.setHorizontalAlignment(SwingConstants.CENTER);
		jContentPane.add(jLblNome);
		
		jTxtNome = new JTextField();
		jTxtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTxtNome.setText(jTxtNome.getText().toUpperCase());
			}
		});
		jTxtNome.setBounds(89, 31, 215, 25);
		jTxtNome.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtNome.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtNome.setColumns(10);
		jTxtNome.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jTxtNome);
		
		JScrollPane jScrlPRelatorio = new JScrollPane();
		jScrlPRelatorio.setBounds(23, 321, 696, 90);
		jContentPane.add(jScrlPRelatorio);
		
		jTxtARelatorio = new JTextArea();
		jTxtARelatorio.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtARelatorio.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jScrlPRelatorio.setViewportView(jTxtARelatorio);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblCargo.setBounds(388, 23, 57, 25);
		jContentPane.add(lblCargo);
		
		jCmbCargo = new JComboBox<String>();
		jCmbCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionaChave();
			}
		});
		jCmbCargo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jCmbCargo.setBorder(new LineBorder(Color.BLACK, 2, true));
		jCmbCargo.setBackground(Color.CYAN);
		jCmbCargo.setBounds(444, 23, 103, 25);
		jContentPane.add(jCmbCargo);
		
		jFtdTxtDataV = new JFormattedTextField();
		jFtdTxtDataV.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jFtdTxtDataV.setHorizontalAlignment(SwingConstants.CENTER);
		jFtdTxtDataV.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jFtdTxtDataV.setBounds(661, 23, 91, 25);
		jContentPane.add(jFtdTxtDataV);
		jFtdTxtDataV.setFormatterFactory(Diversos.FormatoMascara(titulo, 0));
		
		JLabel jLblDataV = new JLabel("Data de Validade");
		jLblDataV.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblDataV.setHorizontalAlignment(SwingConstants.CENTER);
		jLblDataV.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblDataV.setBounds(589, 0, 163, 25);
		jContentPane.add(jLblDataV);
		
		 lblTelefone = new JLabel("Telefone");
		 lblTelefone.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 lblTelefone.setBackground(Color.WHITE);
		 lblTelefone.setBounds(8, 72, 64, 15);
		 jContentPane.add(lblTelefone);
		 
		 jtxtTel = new JTextField();
		 jtxtTel.setHorizontalAlignment(SwingConstants.CENTER);
		 jtxtTel.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 jtxtTel.setColumns(10);
		 jtxtTel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		 jtxtTel.setBounds(90, 67, 217, 25);
		 jContentPane.add(jtxtTel);
		 
		 JLabel lblCPF = new JLabel("CPF");
		 lblCPF.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 lblCPF.setBackground(Color.WHITE);
		 lblCPF.setBounds(30, 105, 64, 20);
		 jContentPane.add(lblCPF);
		 
		 jtxtCpf = new JTextField();
		 jtxtCpf.setHorizontalAlignment(SwingConstants.CENTER);
		 jtxtCpf.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 jtxtCpf.setColumns(10);
		 jtxtCpf.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		 jtxtCpf.setBounds(89, 103, 218, 25);
		 jContentPane.add(jtxtCpf);
		 
		 JLabel lblBairro = new JLabel("Bairro");
		 lblBairro.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 lblBairro.setBackground(Color.WHITE);
		 lblBairro.setBounds(8, 144, 64, 15);
		 jContentPane.add(lblBairro);
		 
		 jtxtBairro = new JTextField();
		 jtxtBairro.setHorizontalAlignment(SwingConstants.CENTER);
		 jtxtBairro.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 jtxtBairro.setColumns(10);
		 jtxtBairro.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		 jtxtBairro.setBounds(89, 139, 218, 25);
		 jContentPane.add(jtxtBairro);
		 
		 lblCoddepartamento = new JLabel("codDepartamento");
		 lblCoddepartamento.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 lblCoddepartamento.setBackground(Color.WHITE);
		 lblCoddepartamento.setBounds(8, 209, 148, 15);
		 jContentPane.add(lblCoddepartamento);
		 
		 jtxtDepartamento = new JTextField();
		 jtxtDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		 jtxtDepartamento.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 jtxtDepartamento.setColumns(10);
		 jtxtDepartamento.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		 jtxtDepartamento.setBounds(146, 204, 81, 25);
		 jContentPane.add(jtxtDepartamento);
		 
		 jblCodCargo = new JLabel("codCargo");
		 jblCodCargo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 jblCodCargo.setBackground(Color.WHITE);
		 jblCodCargo.setBounds(388, 59, 86, 15);
		 jContentPane.add(jblCodCargo);
		 
		 JlblCarg = new JLabel("");
		 JlblCarg.setHorizontalAlignment(SwingConstants.CENTER);
		 JlblCarg.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 JlblCarg.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		 JlblCarg.setBounds(473, 59, 33, 26);
		 jContentPane.add(JlblCarg);
		 setResizable(false);
		 setLocationRelativeTo(null); //centraliza o formulário
		 try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             UIManager.put("OptionPane.messageFont", 
                     new FontUIResource(new Font("ARIAL BLACK", Font.BOLD + Font.ITALIC, 13)));
             UIManager.put("OptionPane.messageForeground", Color.BLUE);
         } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFrmFuncionario.class.getName()).log(Level.SEVERE, null, ex);
          } 

	}
	
	private void limpar() {
		 JTextField txt[] = {jTxtMatr, jTxtNome,};
	     for (JTextField t : txt)
	            t.setText("");
	     jCmbCargo.setSelectedIndex(-1);
	     jButtonGroup.clearSelection();
	     jFtdTxtDataV.setText("");
	     jTxtARelatorio.setText("");
	     jTxtMatr.setEditable(true);
	     JButton jBtn[] = {jBtnAlterar, jBtnExcluir, jBtnIncluir};
	        for (JButton btn : jBtn) 
	            btn.setEnabled(false);    
	     jTxtNome.requestFocusInWindow();
	}
	
	private void relatorioGeral() {
        listaC = cC.lista();
        String resp = "";
        for (Object o : listaC) {
            Funcionario c = (Funcionario) o;
            resp += c.relatorio();
        }
        jTxtARelatorio.setText(!resp.isEmpty() ? resp : "Inexstência de dados");
    }
	
	private void carregaLista() {
			 int posi = jCmbCargo.getSelectedIndex(); //guarda a posição corrente
		       listaB = cB.lista();
		       jCmbCargo.removeAllItems();
		       for (Object o : listaB) {
		            Departamento b = (Departamento) o;
		            jCmbCargo.addItem(b.getDescricao());
		        }
		         if (posi > -1) 
		              jCmbCargo.setSelectedIndex(posi);
		         else {
		               jCmbCargo.setSelectedIndex(-1);
		         }
	  }     
	
	private void carregaObjetos(Funcionario c) {
		jTxtMatr.setText(String.valueOf(c.getMatricula()));
        jTxtNome.setText(c.getNome());
        jCmbCargo.setSelectedItem(c.getCargo().getCargo());
        jtxtTel.setText(c.getTelefone());
        jTxtcpf.setText(c.getCpf());
        jTxtbairro.setText(c.getBairro());
        
      /*  switch(c.getTipo()) {
           case 'C' : jRdbComercial.setSelected(true);
                      break;
           case 'I' : jRdbIndustrial.setSelected(true);
                      break;
           case 'R' : jRdbResidencial.setSelected(true);
        }*/
        jFtdTxtDataV.setText(c.getDataV());
       
	}
	
	 private void cadastrarDados(char opcao) {
         String resp = "";
        if(jTxtMatr.getText().isEmpty() || jTxtNome.getText().isEmpty() || jCmbCargo.getSelectedIndex() < 0 ||
        jtxtTel.getText().isEmpty() ||  jTxtcpf.getText().isEmpty() || 
        jTxtbairro.getText().isEmpty()|| jFtdTxtDataV.getText().isEmpty()|| 
        jtxtDepartamento.getText().isEmpty()|| JlblCarg.getText().isEmpty())
              resp = "Favor digitar os dados";
         else {
                  Funcionario c = new Funcionario();
                  c.setMatricula(Integer.parseInt(jTxtMatr.getText()));
                  c.setNome(jTxtNome.getText());
                  //Object o = cB.getBusca(Integer.parseInt(jLblCodigo.getText()), 0);
                   Object o=cB.getBusca(Integer.parseInt(jblCodCargo.getText()), 0);
                  Cargo b = (Cargo) o;
                  c.setCargo(b);
                  Departamento d = (Departamento) o;
                  c.setDepartamento(d);
                  c.setBairro(jTxtbairro.getText());
                  c.setTelefone(jtxtTel.getText());
                  c.setDataV(jFtdTxtDataV.getText());
                  c.setCpf(jTxtcpf.getText());
                  c.setCodCargo(Integer.parseInt(JlblCarg.getText()));
                  c.setCodDepartamento(Integer.parseInt(jtxtDepartamento.getText()));
                   if (!cC.setManipular(c, opcao)) {
                       resp = "Problemas ao " + (opcao == 'A' ? "atualizar" : opcao == 'E' ?  
                   	   "remover" : " inserir") + " os dados do(a) consumidor(a) " + c.getNome();
                       jBtnAlterar.setEnabled(false);
                       jBtnExcluir.setEnabled(false);
                      jBtnIncluir.setEnabled(false);
                   }   
                   else {
            	           //jLblPrecoF.setText("Preco a pagar de "+
                          //Diversos.doisDigitos(1).format(c.precoReal())); 
                          resp = "O(A) consumidor(a) " +  c.getNome();
                         switch(opcao) {
                              case 'A' :  resp += "\nFoi atualizado(a) ";
                                               break;
                              case 'E' :  resp  +=  "\nFoi removido(a) ";
                                              limpar();
                                              jBtnAlterar.setEnabled(false);
                                              jBtnExcluir.setEnabled(false);
                                              break;
                              case 'I' :  resp +=  "\nFoi inserido(a) ";
                                              jBtnAlterar.setEnabled(true);
                                             jBtnExcluir.setEnabled(true);
                                            jBtnIncluir.setEnabled(false);                  
                            }
                          resp += " com sucesso";                
                    }                  
              }
            Diversos.mostrarDados(resp, titulo, (resp.charAt(0) != 'F' && 
            		resp.charAt(0) != 'P'));
    }


	private void pesquisa() {
		int numero;
        Funcionario c;
        if (!Diversos.testaNum(jTxtMatr.getText(), titulo))
            jTxtMatr.setText(""); // converter texto para numero
        else if (!Diversos.intervalo(Integer.parseInt(jTxtMatr.getText()), 0 ,0 , titulo))
                 jTxtMatr.setText(""); //testar se é maior que zero
             else {
                   numero = Integer.parseInt(jTxtMatr.getText());
                   Object o = cC.getBusca(numero,0);
                   if (o == null) {
                      jBtnIncluir.setEnabled(true);
                      Diversos.mostrarDados("Consumidor(a)  " + numero + " inexistente", titulo, true);
                   }    
                   else {
                        c = (Funcionario) o;
                        carregaObjetos(c); 
                        jBtnAlterar.setEnabled(true);
                        jBtnExcluir.setEnabled(true);
                  } 
                  jTxtMatr.setEditable(false);
            }
	}
	
	private void selecionaChave() {
		int ind = jCmbCargo.getSelectedIndex();
        if (ind > -1) {
           Object o = listaB.get(ind);
           Departamento b = (Departamento) o;
           jtxtDepartamento.setText(String.valueOf(b.getIdcodDepto()));
           Cargo c = (Cargo) o;
           JlblCarg.setText(String.valueOf(b.getIdcodDepto()));
        }
	}
}
