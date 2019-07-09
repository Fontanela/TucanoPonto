package br.com.tucanobrasil.ig;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

//Inicio da classe
	public class Login2 extends JFrame{
	
		private static final long serialVersionUID = 1L;
		
		//Declara��o das Variaveis/Atributos
			private JLabel rotData;
			private JLabel rotCod;
			private JLabel rotSen;
			private JLabel label_2;
			private JTextField texCod;
			private JPasswordField texSen;
			private JButton botLog;
			JLabel rotNome;

		
		//M�todo que inicializa a janela	
			public Login2(){
			
				//Chama os m�todos
					janela();//Cria janela
					criarRotulos();//Cria r�tulos
					criarTextos();//Cria textos
					criarBotoes();//Cria bot�es					    
					adiComps();//Adiciona componentes
					proParal();//M�todo que aualiza a hora
					
					
				//R�tulos
			    
					//R�tulo "Registro Ponto..."
						JLabel label_1 = new JLabel("Registro Ponto (" + br.com.tucanobrasil.M.STRVER + "):");
						label_1.setFont(new Font("Serif", 1, 21));
						label_1.setBounds(201, 22, 203, 24);
						getContentPane().add(label_1);
					    
					//R�tulo "Esqueceu sua senha?"	
						this.label_2 = new JLabel("<html><u>Esqueceu sua senha?");
						this.label_2.setFont(new Font("Serif", 0, 16));
						this.label_2.setBounds(10, 146, 136, 24);
						this.label_2.setCursor(Cursor.getPredefinedCursor(12));
						this.label_2.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
						public void mousePressed(MouseEvent arg0) {}
						public void mouseExited(MouseEvent arg0) {}
						public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { Ig.sucesso("Por favor entre em contato com o RH.");
						}
						});
						getContentPane().add(this.label_2);
					
				//R�tulo/Bot�es		
						
					//R�tulo/Bot�o "Solicitar altera�a� RH"	
						JLabel label_3 = new JLabel("<html><u>Solicitar altera��o RH.");
						label_3.setFont(new Font("Serif", 0, 16));
						label_3.setBounds(10, 179, 166, 18);
						label_3.setCursor(Cursor.getPredefinedCursor(12));
						label_3.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
						public void mousePressed(MouseEvent arg0) {}
						public void mouseExited(MouseEvent arg0) {}
						public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { String res = Rede.httpPost("LOG" + Login2.this.texCod.getText() + "\001" + new String(Login2.this.texSen.getPassword()));        
							if (res.substring(0, 3).equals("LOK")) {
								Login2.this.dispose();
								new Solicitacao();
							}
							else {
								Ig.advertencia("Nome ou senha incorretos! Tente novamente." + res);
							}
							        
						}});
						getContentPane().add(label_3);
					
					//R�tulo/Bot�o "   
						this.label_4 = new JLabel("<html><u>Visualizar historico do ponto.");
						this.label_4.setFont(new Font("Serif", 0, 16));
						this.label_4.setBounds(10, 208, 181, 18);
						this.label_4.setCursor(Cursor.getPredefinedCursor(12));
						this.label_4.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
						public void mousePressed(MouseEvent arg0) {}
						public void mouseExited(MouseEvent arg0) {}
						public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { String res = Rede.httpPost("LOG" + Login2.this.texCod.getText() + "\001" + new String(Login2.this.texSen.getPassword()));        
							if (res.substring(0, 3).equals("LOK")) {
								Login2.this.dispose();
								new Visual();
							}
							else {
								Ig.advertencia("Nome ou senha incorretos! Tente novamente." + res);
							}
							        
						}});
						getContentPane().add(this.label_4);
				
				//Deixa visivel
					setVisible(true);
		}
		//Fim do m�todo "login2"
		
		//M�todo que cria a janela	
			private void janela() {
				
				//Propriedades da janela	
					setTitle("Tucano Ponto - Registro");
					setSize(420, 265);
					getContentPane().setLayout(null);
					setLocationRelativeTo(null);
					setResizable(false);
					setDefaultCloseOperation(3);
					setIconImage(new ImageIcon("icosma.png").getImage());
					getContentPane().setBackground(new Color(255, 255, 181));
			}
		//Fim do m�todo "janela"
			  
		//M�todo que cria os r�tulos
			private void criarRotulos() {
			
				//R�tulo "Data"
					this.rotData = new JLabel("Data: " + Tempo.formatoBrasil());
					this.rotData.setBounds(10, 63, 357, 14);
					this.rotData.setFont(new Font("Arial", 0, 14));
					    
				//R�tulo "C�digo"	
					this.rotCod = new JLabel("C�digo:");
					this.rotCod.setBounds(10, 91, 49, 18);
					this.rotCod.setFont(new Font("Arial", 0, 14));
					   
				//R�tulo "Senha"	
					this.rotSen = new JLabel("Senha:");
					this.rotSen.setBounds(10, 121, 49, 14);
					this.rotSen.setFont(new Font("Arial", 0, 14));
			}
		//Fim do m�todo "criarR�tulos"
		  
		//M�todo que cria as caixas de texto	
			private void criarTextos() {
				
				//Campo de texto "C�digo"
					this.texCod = new JTextField();
					this.texCod.setBounds(69, 88, 335, 23);
					this.texCod.setFont(new Font("Arial", 0, 14));
					this.texCod.getDocument().addDocumentListener(new DocumentListener(){
						public void changedUpdate(DocumentEvent arg0) {
							Login2.this.regrasCod();}
						public void insertUpdate(DocumentEvent arg0){
							Login2.this.regrasCod();}
						public void removeUpdate(DocumentEvent arg0){
							Login2.this.regrasCod();}});
					this.texCod.addKeyListener(new KeyListener(){
						public void keyTyped(KeyEvent arg0) {}
						public void keyReleased(KeyEvent arg0) {}
						public void keyPressed(KeyEvent arg0){
						arg0.getKeyCode();}});
					
				//Campo de texto "Senha"
					this.texSen = new JPasswordField();
					this.texSen.setBounds(69, 120, 335, 23);
					this.texSen.setFont(new Font("Arial", 0, 14));    
					this.texSen.addKeyListener(new KeyListener(){
					public void keyTyped(KeyEvent arg0) {}
					public void keyReleased(KeyEvent arg0){
						if (Login2.this.ok) {
							Login2.this.dispose();new Registro();
						}
					}
					public void keyPressed(KeyEvent arg0) {}});
					this.texSen.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							Login2.this.login();
						}});
					this.texSen.getDocument().addDocumentListener(new DocumentListener(){
						public void changedUpdate(DocumentEvent arg0) {
						Login2.this.regrasSen();
					}
					      
					public void insertUpdate(DocumentEvent arg0){
						Login2.this.regrasSen();}
					public void removeUpdate(DocumentEvent arg0) { 
						Login2.this.regrasSen();}});
			}
		//Fim do m�todo "criarTextos"
		
		//Declara��o das Variaveis/Atributos	
			boolean ok = false;
			private JLabel label_4;
	
			private JLabel label;
		
		//M�todo de que verifica e faz o login
			private void login() {
				String res = Rede.httpPost("LOG" + this.texCod.getText() + "\001" + new String(this.texSen.getPassword()));
		    
					if (res.substring(0, 3).equals("LOK")) {
						this.ok = true;
					}
					else {
						Ig.advertencia("Nome ou senha incorretos." + res);
					}
		}
		//Fim do m�todo "Login"  
		
		//M�todo que cria os bot�es
			private void criarBotoes() {
				this.botLog = new JButton("Registrar Ponto");
				this.botLog.setBounds(186, 154, 218, 43);
				this.botLog.setIcon(Ig.icone("clock.png"));
				this.botLog.setHorizontalTextPosition(2);
				this.botLog.setHorizontalAlignment(0);
				    
				this.botLog.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Login2.this.login();
						        
						if (Login2.this.ok) {
							Login2.this.dispose();new Registro();
						}
					}
				});
			}
		//Fim do m�todo "criarBot�es"
		
		//M�todo que adiciona os componentes no painel
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
	
				//Logo tucano	
					this.label = new JLabel(new ImageIcon("tucsma.png"));
					this.label.setOpaque(true);
					this.label.setBackground(Color.BLACK);
					this.label.setBounds(10, 11, 181, 41);
					getContentPane().add(this.label);
			}
		//Fim do m�todo "adiComps"
			
		//M�todo de tempo
			private void proParal() {
				new Timer().schedule(new TimerTask() {
					public void run() {
						Login2.this.rotData.setText("Data: " + Tempo.formatoBrasil());
					}
				}, 1000L, 1000L);
			}
		//Fim do m�todo "proParal"
		  
		//M�todo de regras para o campo de c�digo
			private void regrasCod() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						if (!LEsc.eSoNumero(Login2.this.texCod.getText())) {
							Login2.this.texCod.setText("");
							return;
						}
						        
						if (Login2.this.texCod.getText().length() >= 2) {
							Login2.this.texSen.requestFocus();
							return;
						}
					}
				});
			}
		
		//M�todo para as regras do campo de texto de senha	
			private void regrasSen() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						String pass = new String(Login2.this.texSen.getPassword());
						        
						if (!LEsc.eSoNumero(pass)) {
							Login2.this.texSen.setText("");
							return;
						}
					}
				});
			}
	}
//Fim da classe