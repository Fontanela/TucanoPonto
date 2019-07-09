package br.com.tucanobrasil.ig;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import br.com.tucanobrasil.sis.rede.Rede;
import br.com.tucanobrasil.sis.util.Ig;
import br.com.tucanobrasil.sis.util.LEsc;
import br.com.tucanobrasil.sis.util.Tempo;

//Inicio da classe "Login"
	public class Login extends JFrame{
	
		private static final long serialVersionUID = 1L;
		
		//Declara��o das Variaveis/Atributos
			private JLabel rotData;
			private JLabel rotCod;
			private JLabel rotSen;
			private JTextField texCod;
			private JPasswordField texSen;
			private JButton botLog;
			JLabel rotNome;
		
		//M�todo que inicializa a janela	
			public Login(){
			
				//Chama os m�todos
					janela();//Cria janela
					criarRotulos();//Cria r�tulos
					criarTextos();//Cria textos
					criarBotoes();//Cria bot�es					    
					adiComps();//Adiciona componentes
					proParal();//M�todo que aualiza a hora
					
					
				//R�tulos
					
					//R�tulo do nome 
						this.rotNome = new JLabel("");
						this.rotNome.setFont(new Font("Arial", 0, 14));
						this.rotNome.setBounds(10, 200, 181, 14);
						getContentPane().add(this.rotNome);
						
					//R�tulo do cabe�alho	
						this.label_3 = new JLabel("Gest�o de Recursos Empresariais (Registro Ponto):");
						this.label_3.setFont(new Font("Serif", 0, 20));
						this.label_3.setBounds(56, 63, 415, 24);
						getContentPane().add(this.label_3);	
						
				//R�tulos/Bot�es
					
					//R�tulo/Bot�o "Esqueceu sua senha?"
						JLabel label = new JLabel("<html><u>Esqueceu sua senha?");
						label.setFont(new Font("Serif", 0, 16));
						label.setBounds(10, 242, 136, 24);
						label.setCursor(Cursor.getPredefinedCursor(12));
						label.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
						public void mousePressed(MouseEvent arg0) {}
						public void mouseExited(MouseEvent arg0) {}
						public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { 
							Ig.sucesso("Por favor entre em contato com o RH.");}	});
						getContentPane().add(label);
							    
					//R�tulo/Bot�o "Solicitar altera��o RH"
						JLabel label_1 = new JLabel("<html><u>Solicitar altera��o RH.");
						label_1.setFont(new Font("Serif", 0, 16));
						label_1.setBounds(10, 291, 166, 18);
						label_1.setCursor(Cursor.getPredefinedCursor(12));
						label_1.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
						public void mousePressed(MouseEvent arg0) {}
						public void mouseExited(MouseEvent arg0) {}
						public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { Login.this.login();
						
						//Se o login estive certo, ao clicar no bot�o abre a janela "Solicita��o"	
							if (Login.this.ok) {
								Login.this.dispose();//Fecha a janela
								new Solicitacao();
							}
							
						//Se n�o, sobe o aivso
							else {
								Ig.advertencia("Nome ou senha incorretos! Tente novamente.");
							}
						}});
						getContentPane().add(label_1);
							    
					//R�tulo/Bot�o "Vizualizar his�rico ponto"	
						JLabel label_2 = new JLabel("<html><u>Visualizar historico ponto.");
						label_2.setFont(new Font("Serif", 0, 16));
						label_2.setBounds(10, 349, 181, 18);
						label_2.setCursor(Cursor.getPredefinedCursor(12));
						label_2.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
						public void mousePressed(MouseEvent arg0) {}
						public void mouseExited(MouseEvent arg0) {}
						public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { String res = Rede.httpPost("LOG" + Login.this.texCod.getText() + "\001" + new String(Login.this.texSen.getPassword()));
							  
						//Se o login estiver certo, abre o hist�rico
							if (res.substring(0, 3).equals("LOK")) {
								Login.this.dispose();
								new Visual();
							}
							
						//Se n�o, sobe o aviso
							else {
								Ig.advertencia("Nome ou senha incorretos! Tente novamente.");
							}
						}});
						getContentPane().add(label_2);
							    
					//R�tulo/Bot�o "Vis. solicita��es RH"		
						this.label_4 = new JLabel("<html><u>Vis. solicita��es RH");
						this.label_4.setFont(new Font("Serif", 0, 16));
						this.label_4.setBounds(10, 320, 166, 18);
						this.label_4.setCursor(Cursor.getPredefinedCursor(12));
						getContentPane().add(this.label_4);
						this.label_4.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
						public void mousePressed(MouseEvent arg0) {}
						public void mouseExited(MouseEvent arg0) {}
						public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { Login.this.login();
							     
						//Se o login n�o estiver certo, abre a janela de Solicita��es
							if (!Login.this.ok) return;
								new Solicitacoes(br.com.tucanobrasil.M.ID);						        
								Login.this.dispose();
							}
								      
						});
						    
					//Deixa visivel	
						setVisible(true);
			}
		//Fim do m�todo "login"	
			
			  
		//M�todo que cria a janela
			private void janela() {
			
				//Propriedades da janela
					setTitle("TucanoPonto - Registro Ponto");
					setSize(526, 442);
					getContentPane().setLayout(null);
					setLocationRelativeTo(null);
					setResizable(false);
					setDefaultCloseOperation(3);
					setIconImage(new ImageIcon("32x32.png").getImage());
					getContentPane().setBackground(new Color(255, 255, 181));
			}
		//Fim do m�todo "janela"
			  
		//M�todo que cria os r�tulos	
			private void criarRotulos() {
			
				//R�tulo "Data"
					this.rotData = new JLabel("Data: " + Tempo.formatoBrasil());
					this.rotData.setBounds(319, 307, 201, 14);
					this.rotData.setFont(new Font("Arial", 0, 16));
						    
				//R�tulo "C�digo"	
					this.rotCod = new JLabel("Codigo:");
					this.rotCod.setBounds(228, 104, 58, 18);
					this.rotCod.setFont(new Font("Arial", 0, 17));
						    
				//R�tulo "Senha"	
					this.rotSen = new JLabel("Senha:");
					this.rotSen.setBounds(228, 175, 58, 14);
					this.rotSen.setFont(new Font("Arial", 0, 17));
						    
				//Logo tucano
					JLabel ima = new JLabel(new ImageIcon("tucsma.png"));
					ima.setBounds(167, 11, 181, 41);
					ima.setBackground(Color.BLACK);
					ima.setOpaque(true);				    
					getContentPane().add(ima);
			}
		//Fim do m�todo "criarRotulos"	  
		
		//M�todo que cria os cmapos de texto
			private void criarTextos() {
			
			//Campo de texto "C�digo"
				this.texCod = new JTextField();
				this.texCod.setBounds(-3, 133, 523, 31);
				this.texCod.setHorizontalAlignment(0);
				this.texCod.setFont(new Font("Arial", 0, 16));
				this.texCod.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent arg0) {}					      
				public void keyReleased(KeyEvent arg0) {}					      
				public void keyPressed(KeyEvent arg0) {
					//Regras do c�digo
						if (arg0.getKeyCode() == 10) {
							Login.this.texSen.requestFocus();
						}}});
						this.texCod.getDocument().addDocumentListener(new DocumentListener()
						{
						public void changedUpdate(DocumentEvent arg0){
							Login.this.regrasCod();
						}						      
						public void insertUpdate(DocumentEvent arg0){
							Login.this.regrasCod();
						}						      
						public void removeUpdate(DocumentEvent arg0){
							Login.this.regrasCod();
						}				
						});
						this.texCod.addFocusListener(new FocusListener() {
						public void focusLost(FocusEvent arg0) {
							String nome = Rede.httpPost("NPC" + Login.this.texCod.getText());
								        
							if (nome.length() > 0) {
								Login.this.rotNome.setText(nome.split(" ", -1)[0]);
							}
							else {
								Login.this.rotNome.setText("");
							}
						}
						public void focusGained(FocusEvent arg0) {}
							});
					
			//Campo de texto "Senha"
				this.texSen = new JPasswordField();
				this.texSen.setBounds(-14, 200, 534, 31);
				this.texSen.setFont(new Font("Arial", 0, 16));
				this.texSen.setHorizontalAlignment(0);
				//Regras da Senha
					this.texSen.getDocument().addDocumentListener(new DocumentListener()		{
					public void changedUpdate(DocumentEvent arg0){
					
						Login.this.regrasSen();
					}				      
					public void insertUpdate(DocumentEvent arg0){
						Login.this.regrasSen();
					}	
					public void removeUpdate(DocumentEvent arg0){
						Login.this.regrasSen();
					}});
					this.texSen.addKeyListener(new KeyListener(){
						public void keyTyped(KeyEvent arg0) {}
						public void keyReleased(KeyEvent arg0){
							if (Login.this.ok) {
								Login.this.dispose();new Registro();
								}
							}		
						public void keyPressed(KeyEvent arg0) {}
					});
					this.texSen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Login.this.login();
						}
					});
		}
		//Fim do m�todo que cria campos de texto
		
		//Declara��o das Variaveis/Atributos	
		boolean ok = false;
		private JLabel label_3;
		private JLabel label_4;
			  
		//M�todo que veifica e faz o login do usu�rio
			private void login() { String res = Rede.httpPost("LOG" + this.texCod.getText() + "\001" + new String(this.texSen.getPassword()));
		
				//Se o login bater com as informa��es da base de dados
					if (res.substring(0, 3).equals("LOK")) {
						br.com.tucanobrasil.M.ID = res.substring(3);
						this.ok = true;
					}
					else {
						Ig.advertencia("Nome ou senha incorretos.");
					}
			}
		//Fim do m�todo "login"	
		
		//M�todo que cria os bot�es	
		private void criarBotoes() {
		
			//Bot�o "Registrar Ponto"
				this.botLog = new JButton("<html>&nbsp; Registrar Ponto &nbsp;");
				this.botLog.setBounds(252, 242, 258, 43);
				this.botLog.setIcon(Ig.icone("clock.png"));
				this.botLog.setHorizontalTextPosition(2);
				this.botLog.setHorizontalAlignment(0);
					 
				//Quando pressionado verifica o login, fecha a janela e abre a ja nela "Registro'
					this.botLog.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							Login.this.login();
							
							if (Login.this.ok) {
								Login.this.dispose();new Registro();
							}
					}});
		}
		//Fim do m�todo "criarBotoes"

		//M�todo que adiciona os componentes ao painel
		private void adiComps(){
		
			//R�tulos
			
				//Adcionando o r�tulo "Data" ao painel
					getContentPane().add(this.rotData);
				
				//Adcionando o r�tulo "C�digo" ao painel	
					getContentPane().add(this.rotCod);
					
				//Adcionando o r�tulo "Senha" ao painel	
					getContentPane().add(this.rotSen);
					    
			//Campos de texto
					
				//Adcionando o campo de texto "C�digo" ao painel
					getContentPane().add(this.texCod);
					
				//Adcionando o campo de texto "Senha" ao painel	
					getContentPane().add(this.texSen);
					    
			//Adicionando o bot�o "Registrar Ponto" ao painel
				getContentPane().add(this.botLog);
		}
		//Fim do m�todo "adiComps"
			  
		//M�todo que que marca o ponto baseado na hora atual
			private void proParal() {
				new Timer().schedule(new TimerTask() {
					public void run() {
						Login.this.rotData.setText("Data: " + Tempo.formatoBrasil());
					}
				}, 1000L, 1000L);
			}
			
		//M�todo que define as regras para o campo de texto "C�digo"	  
			private void regrasCod() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						
						//S� aceita n�mero
							if (!LEsc.eSoNumero(Login.this.texCod.getText())) {
								Login.this.texCod.setText("");
								return;
							}
							        
						//Se o valor digitado no campo for maior ou igual a 2 ele pula para campo de senha
							if (Login.this.texCod.getText().length() >= 2) {
								Login.this.texSen.requestFocus();
								return;
							}
					}
				});
			}
		//Fim do m�todo "regrasCod"  
			
		//M�todo de regras de senha
			private void regrasSen() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						String pass = new String(Login.this.texSen.getPassword());
						       
						//S� aceita n�mero
							if (!LEsc.eSoNumero(pass)) {
								Login.this.texSen.setText("");
								return;
							}
					}
				});
			}
		//Fim do m�todo "regrasSen"
	}
