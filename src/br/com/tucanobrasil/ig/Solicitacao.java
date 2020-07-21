package br.com.tucanobrasil.ig;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.com.tucanobrasil.M;
import br.com.tucanobrasil.sis.lgraf.LRealArea;
import br.com.tucanobrasil.sis.rede.Rede;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

//Inicio da classe 
public class Solicitacao extends javax.swing.JFrame implements java.awt.KeyEventDispatcher{
	
	private static final long serialVersionUID = 1L;
	
	//Declaração das variáveis
		private br.com.tucanobrasil.sis.lgraf.LData texData;
		boolean[] st = new boolean[3];
		private JLabel rotAgr;
		private JLabel rotDesc;
		private JLabel rotSen;
		private JLabel label;
		private JLabel rotSol;
		private JTextField texEM;
		private JTextField texSM;
		private JTextField texET;
		private JTextField texST;
		private JRadioButton radOp1;
		private JRadioButton radOp2;
		private JRadioButton radOp3;
		private LRealArea texDesc;
		private LRealArea texOut;
		private javax.swing.JButton btnLog;
		private JLabel lblEt;
		private javax.swing.ButtonGroup bg;
		javax.swing.JScrollPane barOut;
		
	//Método de inicialização	
		public Solicitacao(){
		
			//Chama os métodos principais
				janela();
				proParal();
				br.com.tucanobrasil.M.manager.addKeyEventDispatcher(this);
		
			//Deixa os componentes visiveis	
				setVisible(true);
		}

	//Método que evia as solicitações	
		public void envSol(){
			
			//Declarção de variáveis interas
				int tipo = 0;
				String corrigir = "";
				String outra = "";
			
			//Verifica se a data digitada é válida
				if (!this.texData.valido) {
					br.com.tucanobrasil.sis.util.Ig.advertencia("Insira a data em um formato correto.");
					return;
				}
			
			//Verifica o tipo de solicitação	
				if (this.st[1]) {
					tipo = 1;
					corrigir = this.texEM.getText() + ";" + this.texSM.getText() + ";" + this.texET.getText() + ";" + this.texST.getText();
				}
				else if (this.st[2]) {
					tipo = 2;
					outra = this.texOut.getText();
				}
			
			//Envia a solicitação pro banco de dados	
				String pac = "SCP" + this.texData.getText() + "" + this.texDesc.getText() + "" + tipo + "" + corrigir + "" + outra;
				
				//Se o servidor ecoar "PCS"
					if (Rede.httpPost(pac).equals("PCS")) {
						dispose();
						
						br.com.tucanobrasil.sis.util.Ig.sucesso("Solicitação enviada com sucesso! Aguarde a correção pelo RH.");
						 
						new Solicitacoes(br.com.tucanobrasil.M.ID);
					}
		}


//Método que cria a janela
	private void janela() {
		
		//Propriedades da janela
			setTitle("Tucano Ponto - Registro");
			setSize(624, 448);
			getContentPane().setLayout(null);
			setLocationRelativeTo(null);
			setResizable(false);
			setDefaultCloseOperation(3);
			setIconImage(new javax.swing.ImageIcon("32x32.png").getImage());
			getContentPane().setBackground(new java.awt.Color(255, 255, 181));
			
		//Rótulos
			
			//Logo
				this.label = new JLabel(new javax.swing.ImageIcon("tucsma.png"));
				this.label.setOpaque(true);
				this.label.setBackground(java.awt.Color.BLACK);
				this.label.setBounds(219, 11, 181, 41);
				getContentPane().add(this.label);
			
			//Rótulo do subtítulo	
				JLabel lblOcorrnciasSolicitao = new JLabel("Ocorrências / Solicitação Ponto:");
				lblOcorrnciasSolicitao.setFont(new java.awt.Font("Serif", 1, 18));
				lblOcorrnciasSolicitao.setBounds(143, 63, 333, 24);
				getContentPane().add(lblOcorrnciasSolicitao);
				
			//Rótulo "Data ocorrido"	
				this.rotDesc = new JLabel("Data ocorrido:");
				this.rotDesc.setBounds(10, 101, 99, 18);
				this.rotDesc.setFont(new java.awt.Font("Arial", 0, 14));
				getContentPane().add(this.rotDesc);
			
			//Descrição da ocorrência	
				this.rotSen = new JLabel("Descrição da ocorrência:");
				this.rotSen.setBounds(10, 131, 181, 18);
				this.rotSen.setFont(new java.awt.Font("Arial", 0, 14));
				getContentPane().add(this.rotSen);

			//Rótulo "Solicitação"	
				this.rotSol = new JLabel("Solicitação:");
				this.rotSol.setFont(new java.awt.Font("Arial", 0, 14));
				this.rotSol.setBounds(10, 186, 167, 18);
				getContentPane().add(this.rotSol);
				
			//Rótulo "EM"	
				JLabel lblEm = new JLabel("EM:");
				lblEm.setFont(new java.awt.Font("Arial", 0, 14));
				lblEm.setBounds(170, 238, 29, 18);
				getContentPane().add(lblEm);
				 
			//Rótulo "SM"
				JLabel lblSm = new JLabel("SM:");
				lblSm.setFont(new java.awt.Font("Arial", 0, 14));
				lblSm.setBounds(283, 237, 29, 18);
				getContentPane().add(lblSm);
				 
			//Rótulo "ET"
				this.lblEt = new JLabel("ET:");
				this.lblEt.setFont(new java.awt.Font("Arial", 0, 14));
				this.lblEt.setBounds(393, 235, 29, 18);
				getContentPane().add(this.lblEt);

			//Rótulo "ST"
				JLabel lblSt = new JLabel("ST:");
				lblSt.setFont(new java.awt.Font("Arial", 0, 14));
				lblSt.setBounds(503, 235, 29, 18);
				getContentPane().add(lblSt);
				
			//Rótulo "Data"
				this.rotAgr = new JLabel("Data: " + br.com.tucanobrasil.sis.util.Tempo.formatoBrasil());
				this.rotAgr.setBounds(408, 382, 195, 18);
				this.rotAgr.setFont(new java.awt.Font("Arial", 0, 14));
				getContentPane().add(this.rotAgr);

		//Botões de rádio
				
			//Botão "Apenas Justificativa..."	
				this.radOp1 = new JRadioButton("Apenas justificativa. Não desejo solicitar nada.");
				radOp1.setBackground(new Color(255, 255, 181));
				this.radOp1.setSelected(true);
				this.radOp1.setFont(new java.awt.Font("Arial", 0, 14));
				this.radOp1.setBounds(10, 210, 373, 24);
				getContentPane().add(this.radOp1);

			//Botão "Corrigir para:"	
				this.radOp2 = new JRadioButton("Corrigir para:");
				radOp2.setBackground(new Color(255, 255, 181));
				this.radOp2.setSelected(true);
				this.radOp2.setFont(new java.awt.Font("Arial", 0, 14));
				this.radOp2.setBounds(10, 238, 155, 21);
				getContentPane().add(this.radOp2);

			//Botão "Outra:"	
				this.radOp3 = new JRadioButton("Outra:");
				radOp3.setBackground(new Color(255, 255, 181));
				this.radOp3.setSelected(true);
				this.radOp3.setFont(new java.awt.Font("Arial", 0, 14));
				this.radOp3.setBounds(10, 270, 68, 31);
				getContentPane().add(this.radOp3);

				//Adicionando os botões ao grupo de botões	
					this.bg = new javax.swing.ButtonGroup();
					this.bg.add(this.radOp1);
					this.bg.add(this.radOp2);
					this.bg.add(this.radOp3);
				
			//Campos de texto
				
				//Campo de texto "Data"
					this.texData = new br.com.tucanobrasil.sis.lgraf.LData();
					this.texData.setBounds(110, 98, 493, 23);
					this.texData.setFont(new java.awt.Font("Arial", 0, 14));
					getContentPane().add(this.texData);
					this.texData.addKeyListener(new java.awt.event.KeyListener() { public void keyTyped(java.awt.event.KeyEvent arg0) {}
					public void keyReleased(java.awt.event.KeyEvent arg0) {}
					public void keyPressed(java.awt.event.KeyEvent arg0) { 
						if (arg0.getKeyCode() == 10) {
							Solicitacao.this.texDesc.requestFocus();
							return;
						}
					}});
				
				//Campo de texto "Descrição"	
					this.texDesc = new LRealArea();
					this.texDesc.setFont(new java.awt.Font("Arial", 0, 14));
					this.texDesc.addKeyListener(new java.awt.event.KeyListener() { public void keyTyped(java.awt.event.KeyEvent arg0) {}
					public void keyReleased(java.awt.event.KeyEvent arg0) {}
					public void keyPressed(java.awt.event.KeyEvent arg0) { if (arg0.getKeyCode() == 10) {}}});
					
					//Barra de rolagem para o campo de texto
						javax.swing.JScrollPane barDes = new javax.swing.JScrollPane(this.texDesc);
						barDes.setBounds(189, 126, 414, 51);
						getContentPane().add(barDes);
						
				//Campo de texto "Outra"
					this.texOut = new LRealArea();
					this.texOut.setFont(new java.awt.Font("Arial", 0, 14));
					this.texOut.addKeyListener(new java.awt.event.KeyListener() { public void keyTyped(java.awt.event.KeyEvent arg0) {}
					public void keyReleased(java.awt.event.KeyEvent arg0) {}
					public void keyPressed(java.awt.event.KeyEvent arg0) { if (arg0.getKeyCode() == 10) {}}});					
					
					//Barra de rolagem para o campo de texto
						this.barOut = new javax.swing.JScrollPane(this.texOut);
						this.barOut.setBounds(92, 270, 511, 51); 
						getContentPane().add(this.barOut);
					
				//Campo de texto "EM"		
					this.texEM = new JTextField();
					this.texEM.setFont(new java.awt.Font("Arial", 0, 14));
					this.texEM.setBounds(202, 236, 68, 23);
					getContentPane().add(this.texEM);
				
				//Campo de texto "SM"
					this.texSM = new JTextField();
					this.texSM.setFont(new java.awt.Font("Arial", 0, 14));
					this.texSM.setBounds(315, 235, 68, 23);
					getContentPane().add(this.texSM);
				 
				//Campo de texto "ET"	
					this.texET = new JTextField();
					this.texET.setFont(new java.awt.Font("Arial", 0, 14));
					this.texET.setBounds(425, 233, 68, 23);
					getContentPane().add(this.texET);
				 
				//Campo de texto "ST"	
					this.texST = new JTextField();
					this.texST.setFont(new java.awt.Font("Arial", 0, 14));
					this.texST.setBounds(535, 233, 68, 23);
					getContentPane().add(this.texST);
			
			//Botões		
					
				//Botão "Solicitar correção"	
					this.btnLog = new javax.swing.JButton("Solicitar Correção");
					this.btnLog.setBounds(408, 332, 195, 43);
					getContentPane().add(this.btnLog);
					this.btnLog.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent arg0) {Solicitacao.this.envSol();}});
					
				//Botão "Voltar"	
					JButton btnVoltar = new JButton("Voltar");
					btnVoltar.setBounds(12, 332, 195, 43);
					getContentPane().add(btnVoltar);
					btnVoltar.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent arg0) { dispose(); new Login();}});
	}
//Fim do método "janela"	

//Método que atualiza a hora e demais campos em tempo real
	private void proParal() { 
		new java.util.Timer().schedule(new java.util.TimerTask() {
			public void run() {
				
				//Atualizando a hora
					Solicitacao.this.rotAgr.setText("Data: " + br.com.tucanobrasil.sis.util.Tempo.formatoBrasil());
				
				//Verifica que botão de rádio está selecionado e deixa os campos de acordo	
					if ((Solicitacao.this.st[0] != Solicitacao.this.radOp1.isSelected()) || (Solicitacao.this.st[1] != Solicitacao.this.radOp2.isSelected()) || (Solicitacao.this.st[2] != Solicitacao.this.radOp3.isSelected())) {
						Solicitacao.this.st[0] = Solicitacao.this.radOp1.isSelected();
						Solicitacao.this.st[1] = Solicitacao.this.radOp2.isSelected();
						Solicitacao.this.st[2] = Solicitacao.this.radOp3.isSelected();
						
						if (Solicitacao.this.st[0]) {
							Solicitacao.this.texEM.setEnabled(false);Solicitacao.this.texSM.setEnabled(false);
							Solicitacao.this.texET.setEnabled(false);Solicitacao.this.texST.setEnabled(false);
							Solicitacao.this.texOut.setEditable(false);Solicitacao.this.texOut.setEnabled(false);Solicitacao.this.texOut.setBackground(new java.awt.Color(240, 240, 240));
						}
						else if (Solicitacao.this.st[1]) {
							Solicitacao.this.texEM.setEnabled(true);Solicitacao.this.texSM.setEnabled(true);
							Solicitacao.this.texET.setEnabled(true);Solicitacao.this.texST.setEnabled(true);
							Solicitacao.this.texOut.setEditable(false);Solicitacao.this.texOut.setEnabled(false);Solicitacao.this.texOut.setBackground(new java.awt.Color(240, 240, 240));
						}
						else {
							Solicitacao.this.texEM.setEnabled(false);Solicitacao.this.texSM.setEnabled(false);
							Solicitacao.this.texET.setEnabled(false);Solicitacao.this.texST.setEnabled(false);
							Solicitacao.this.texOut.setEditable(true);Solicitacao.this.texOut.setEnabled(true);texOut.setBackground(new java.awt.Color(255, 255, 255));
						}
					}
			}
		}, 33L, 33L);
	}
//Fim do método "proParal"	
	
//Método de teclado	
	public boolean dispatchKeyEvent(java.awt.event.KeyEvent e){
		if (e.getID() == KeyEvent.KEY_RELEASED) {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				M.manager.removeKeyEventDispatcher(this);
				dispose();			
				new Login();
			}		
		}
	return false;
	}
}
//Fim da classe