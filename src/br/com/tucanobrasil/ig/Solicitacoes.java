package br.com.tucanobrasil.ig;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import br.com.tucanobrasil.sis.lgraf.LRealArea;
import br.com.tucanobrasil.sis.rede.Rede;
import br.com.tucanobrasil.sis.util.Arquivo;
import br.com.tucanobrasil.sis.util.Ig;
import br.com.tucanobrasil.sis.util.LEsc;
import br.com.tucanobrasil.sis.util.Tempo;
import br.com.tucanobrasil.sis.util.Util;
import javax.swing.JButton;

//Inicio da classe
public class Solicitacoes extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	//Declaração de variáveis
		private JMenuBar barMenu;
		private JMenu menOpt;
		private JScrollPane barPon;
		public JTable tabPon;
		private DefaultTableModel modTabPon;
		private JLabel rotSel;
		private ButtonGroup bg;
		private JRadioButton radCor;
		private JRadioButton radOut;
		private LRealArea texDesc;
		private JLabel lblDetalhesDaSolicitao;	   
		public JTextField texEM;
		public JTextField texSM;
		public JTextField texET;
		public JTextField texST;
		JScrollPane brDesc;
		JScrollPane brOut;
		private LRealArea texOut;
		JLabel rotDesc;
		String idFunc = "";
	   
	//Método de inicialização	
		public Solicitacoes(String idFunc) { 
			
			//Atribuindo o id do funcionário a váriavel
				this.idFunc = idFunc;
		     
			//Chama os métodos
				janela();
				criarTabelas();
				criarMenus();
				popTab();
				proParal();
			    		
			//Não pemite que os campos sejam editados
				this.texDesc.setEditable(false);this.texOut.setEditable(false);
				this.texEM.setEditable(false);this.texSM.setEditable(false);
				this.texET.setEditable(false);this.texST.setEditable(false);
				this.radCor.setEnabled(false);this.radOut.setEnabled(false);
				
			//Deixa os componentes visiveis	
				setVisible(true);
		}
	//Fim do método "Solicitações"	
	   
	//Método que cria a janela	
		private void janela() {
			
			//Propriedades da janela
				setTitle("Tucano Ponto - Solicitar Correções do Ponto");
				setSize(929, 628);
				getContentPane().setLayout(null);
				setLocationRelativeTo(null);
				setResizable(false);
				setDefaultCloseOperation(3);
				setIconImage(new ImageIcon("32x32.png").getImage());
				getContentPane().setBackground(new Color(255, 255, 181));
				
			//Botão "Voltar"	
				btnVoltar = new JButton("Voltar");
				btnVoltar.setBounds(10, 566, 117, 25);
				getContentPane().add(btnVoltar);
				btnVoltar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent arg0) { dispose(); new Login();}});
			
			//Rótulos
				
				//Rótulo "SOLICITAÇÕES CORREÇÕES PONTO"	
					this.rotSel = new JLabel("SOLICITAÇÕES CORREÇÕES PONTO:");
					this.rotSel.setFont(new Font("Serif", 0, 18));
					this.rotSel.setBounds(10, 35, 397, 24);
					getContentPane().add(this.rotSel);
			
				//Rótulo "DETALHES DA SOLICITAÇÃO"	     
					this.lblDetalhesDaSolicitao = new JLabel("DETALHES DA SOLICITAÇÃO:");
					this.lblDetalhesDaSolicitao.setFont(new Font("Serif", 0, 18));
					this.lblDetalhesDaSolicitao.setBounds(10, 358, 297, 24);
					getContentPane().add(this.lblDetalhesDaSolicitao);
					
				//Rótulo "Descrição da ocorrência:"	
					this.rotDesc = new JLabel("Descrição da ocorrência:");
					this.rotDesc.setFont(new Font("Arial", 0, 14));
					this.rotDesc.setBounds(10, 398, 189, 18);
					getContentPane().add(this.rotDesc);
					
				//Rótulo "EM"	
					JLabel label = new JLabel("EM:");
					label.setFont(new Font("Arial", 0, 14));
					label.setBounds(195, 460, 29, 18);
					getContentPane().add(label);
					
				//Rótulo "SM"		
					JLabel label_1 = new JLabel("SM:");
					label_1.setFont(new Font("Arial", 0, 14));
					label_1.setBounds(308, 464, 29, 18);
					getContentPane().add(label_1);				
				     
				//Rótulo "ET"		
					JLabel label_2 = new JLabel("ET:");
					label_2.setFont(new Font("Arial", 0, 14));
					label_2.setBounds(418, 462, 29, 18);
					getContentPane().add(label_2);
					
				//Rótulo "ST"	
					JLabel label_4 = new JLabel("ST:");
					label_4.setFont(new Font("Arial", 0, 14));
					label_4.setBounds(528, 462, 29, 18);
					getContentPane().add(label_4);
			
			//Botões de rádio
					
				//Botão "Corrigir ponto para:"	
					this.radCor = new JRadioButton("Corrigir ponto para:");
					radCor.setBackground(new Color(255, 255, 181));
					this.radCor.setSelected(false);
					this.radCor.setFont(new Font("Arial", 0, 14));
					this.radCor.setBounds(10, 460, 171, 18);
					getContentPane().add(this.radCor);
					     
				//Botão "Outra."	
					this.radOut = new JRadioButton("Outra:");
					radOut.setBackground(new Color(255, 255, 181));
					this.radOut.setSelected(false);
					this.radOut.setFont(new Font("Arial", 0, 14));
					this.radOut.setBounds(10, 492, 68, 18);
					getContentPane().add(this.radOut);
					     
				//Criação do gupo de botõe	
					this.bg = new ButtonGroup();
					this.bg.add(this.radCor);
					this.bg.add(this.radOut);
			
			    
			//Campo de texto
				
				//Campo de texto "EM"
					this.texEM = new JTextField();
					this.texEM.setFont(new Font("Arial", 0, 14));
					this.texEM.setBounds(227, 458, 68, 23);
					getContentPane().add(this.texEM);
			    
				//Campo de texto "SM"	
					this.texSM = new JTextField();
					this.texSM.setFont(new Font("Arial", 0, 14));
					this.texSM.setBounds(340, 458, 68, 23);
					getContentPane().add(this.texSM);
			
				//Campo de texto "ET"
					this.texET = new JTextField();
					this.texET.setFont(new Font("Arial", 0, 14));
					this.texET.setBounds(450, 458, 68, 23);
					getContentPane().add(this.texET);
				     
				//Campo de texto "ST"
					this.texST = new JTextField();
					this.texST.setFont(new Font("Arial", 0, 14));
					this.texST.setBounds(560, 458, 68, 23);
					getContentPane().add(this.texST);
			     
				//Campo de texto "Descrição"	
					this.texDesc = new LRealArea();		     
					this.brDesc = new JScrollPane(this.texDesc);
					this.brDesc.setBounds(202, 394, 426, 51);
					getContentPane().add(this.brDesc);
			     
				//Cmapo de texto "Outros"	
					this.texOut = new LRealArea();
					this.brOut = new JScrollPane(this.texOut);
					this.brOut.setBounds(117, 493, 511, 51);
					getContentPane().add(this.brOut);
		}
	//Fim do método "janela"	
	   
	//Método que cria a barra de menu	
		private void criarMenus() {
			
			//Crição da barra 
				this.barMenu = new JMenuBar();
				this.barMenu.setBounds(0, 0, getWidth(), 25);
				getContentPane().add(this.barMenu);
			    
				//Criação do menu "Opções"
					this.menOpt = new JMenu("Opções");
					this.barMenu.add(this.menOpt);
				   
					//Item imprimir dentro do menu	
						JMenuItem miImp = new JMenuItem("Imprimir");
						miImp.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							         				    
								//Declaração de variáveis internas	
									int l = tabPon.getSelectedRow();
									String dataSoli = Tempo.amePBras(Ig.gdt(l, 5, Solicitacoes.this.tabPon).substring(0, 10));
									String dataOcorr = Ig.gdt(l, 2, Solicitacoes.this.tabPon).substring(0, 10);
									ArrayList<String> d = Rede.httpArr("PDS" + Ig.gdt(Solicitacoes.this.tabPon.getSelectedRow(), 0, Solicitacoes.this.tabPon));
									String conteudo = LEsc.arrPStr(Arquivo.ler("./html_soli.html"));
								         
								//Trocando as variaveis do arquivo HTML pelo conteudo
									conteudo = conteudo.replace("#N", Ig.gdt(l, 1, tabPon));
									conteudo = conteudo.replace("#1", dataSoli);
									conteudo = conteudo.replace("#2", dataOcorr);
									conteudo = conteudo.replace("#3", Solicitacoes.formataTextA4((String)d.get(0), 73));
								         
								//Verificações baseadas nos dados	
									if (((String)d.get(3)).equals("0")) {
										conteudo = conteudo.replace("#a", "x");
										           
										conteudo = conteudo.replace("#b", " ");
										conteudo = conteudo.replace("#c", " ");
										conteudo = conteudo.replace("#4", "OUTRA</b>: ");
										           
										conteudo = conteudo.replace("#5", "");
										conteudo = conteudo.replace("#7", "");
										conteudo = conteudo.replace("#6", "");
										conteudo = conteudo.replace("#8", "");
									}
									
									else if (((String)d.get(3)).equals("1")) {
										String[] infs = ((String)d.get(1)).split(";", -1);
										           
										conteudo = conteudo.replace("#b", "x");
										           
										conteudo = conteudo.replace("#5", infs[0]);
										conteudo = conteudo.replace("#6", infs[1]);
										conteudo = conteudo.replace("#7", infs[2]);
										conteudo = conteudo.replace("#8", infs[3]);
										           
										conteudo = conteudo.replace("#a", " ");
										conteudo = conteudo.replace("#c", " ");
										conteudo = conteudo.replace("#4", "OUTRA</b>: ");
									}
									else {
										conteudo = conteudo.replace("#c", "x");
										conteudo = conteudo.replaceAll("#4", "OUTRA</b>: " + Solicitacoes.formataTextA4((String)d.get(2), 90 - "OUTRA: ".length()));
										           
										conteudo = conteudo.replace("#a", "");
										conteudo = conteudo.replace("#b", "");
										           
										conteudo = conteudo.replace("#5", "");
										conteudo = conteudo.replace("#7", "");
										conteudo = conteudo.replace("#6", "");
										conteudo = conteudo.replace("#8", "");
									}
																	
								//Escreve no arquivo e imprime	
									Arquivo.standardFile("res_soli.html", conteudo);
									Util.openChrome("res_soli.html");
							}
						});
			this.menOpt.add(miImp);
		}
	
	//Método que faz colunas	
		public static String nextCol(String val, int space) {
			return val + LEsc.repChar(" ", space - val.length());
		}
	//Fim do método "nextCol"	
	   
	//Método que formata o texto em A4
		public static String formataTextA4(String any, int fLineLength) {
			int first = 0;
			int maxReached = 98;
			int reg = 0;
			     
			String lineAt = "";
			for (int i = 0; i < any.length(); i++) {
				int usemax = maxReached;
				
				if (first == 0) { usemax = fLineLength;	}
				lineAt = lineAt + any.charAt(i);
				reg++;
				
				if (reg >= usemax) {
					lineAt = lineAt + "\r\n";
					reg = 0;
					first = 1;
				}
			}
			return lineAt;
		}
	//Fim do método "formataTextA4"	
	   
	//Variaveis estáticas	
		public static int firstTime = 0;
		public static boolean emEdicao = true;
		private JButton btnVoltar;
	 
	//Método que cria a tabela	
		private void criarTabelas() { 
			
		//Criação do modelo da tabela	
			this.modTabPon = new DefaultTableModel(new String[] { "ID", "Funcionário", "Data Ocorr.", "Tipo", "IdFunc", "DataSoli" }, 0) {
				private static final long serialVersionUID = 1L;
		      	
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
		
		//Criação da tabela	
			this.tabPon = new JTable(this.modTabPon);
			this.tabPon.setRowHeight(0, 30);
			this.tabPon.getTableHeader().setReorderingAllowed(false);
			this.tabPon.setAutoResizeMode(0);
		
			//Propriedades da tabela	
				this.tabPon.getColumnModel().removeColumn(this.tabPon.getColumnModel().getColumn(5));
				this.tabPon.getColumnModel().removeColumn(this.tabPon.getColumnModel().getColumn(4));
				this.tabPon.getColumnModel().removeColumn(this.tabPon.getColumnModel().getColumn(0));	     	 	 
				this.tabPon.getColumnModel().getColumn(0).setPreferredWidth(522);
				this.tabPon.getColumnModel().getColumn(1).setPreferredWidth(187);
				this.tabPon.getColumnModel().getColumn(2).setPreferredWidth(192);
		
			//Barra de rolagem	
				this.barPon = new JScrollPane(this.tabPon);
				this.barPon.setBounds(10, 74, 903, 273);
				getContentPane().add(this.barPon);
		}
	//Fim do método "criarTabelas"
		
	//Método que popula a tabela	
		void popTab() {
				
			ArrayList<String> dados = Rede.httpArr("PLC" + idFunc);
			     
			for (int i = 0; i < dados.size(); i += 6) {
				if (((String)dados.get(i + 3)).equals("0")) {
					dados.set(i + 3, "Apenas justificativa.");
				}
				else if (((String)dados.get(i + 3)).equals("1")) {
					dados.set(i + 3, "Corrigir ponto.");
				}
				else {
					dados.set(i + 3, "Outro.");
				}
			}
			     
			if (dados.size() > 0) {
				Ig.popTab(this.modTabPon, dados, 6);
				       
				this.tabPon.setRowSelectionInterval(0, 0);
				return;
			}
			     
			this.modTabPon.setRowCount(0);
		}
	//Fim do método "popTab"	
		
	//Método que atualiza os dados em tempo real
		private void proParal(){
			new Timer().schedule(new TimerTask() {
				int u = -1;
				
				public void run() { 
					if (this.u != Solicitacoes.this.tabPon.getSelectedRow()) {
						String val = Ig.gdt(Solicitacoes.this.tabPon.getSelectedRow(), 3, Solicitacoes.this.tabPon);
						
						ArrayList<String> d = Rede.httpArr("PDS" + Ig.gdt(Solicitacoes.this.tabPon.getSelectedRow(), 0, Solicitacoes.this.tabPon));
						
						Solicitacoes.this.texDesc.setText((String)d.get(0));
						Solicitacoes.this.texOut.setText("");
						Solicitacoes.this.texEM.setText("");Solicitacoes.this.texSM.setText("");
						Solicitacoes.this.texET.setText("");Solicitacoes.this.texST.setText("");
						Solicitacoes.this.bg.clearSelection();
						           
						//Verifica as opções da ocorrência
						
							//Se for "Corrigir ponto"
								if (val.equals("Corrigir ponto.")) {
									Solicitacoes.this.radCor.setSelected(true);
									
									String[] horas = ((String)d.get(1)).split(";", -1);
									
									Solicitacoes.this.texEM.setText(horas[0]);
									Solicitacoes.this.texSM.setText(horas[1]);
									Solicitacoes.this.texET.setText(horas[2]);
									Solicitacoes.this.texST.setText(horas[3]);             
									Solicitacoes.this.texOut.setText("");
								}
							//Se for "Outro"	
								else if (val.equals("Outro.")) {
									Solicitacoes.this.radOut.setSelected(true);
									Solicitacoes.this.texOut.setText((String)d.get(2));
								}
						
						this.u = Solicitacoes.this.tabPon.getSelectedRow();
					}
				}
			}, 33L, 33L);
		}
	//Fim do método "proParal"	
}
//Fim da classe
