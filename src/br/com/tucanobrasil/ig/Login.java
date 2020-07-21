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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.com.tucanobrasil.M;
import br.com.tucanobrasil.sis.rede.Rede;
import br.com.tucanobrasil.sis.util.Ig;
import br.com.tucanobrasil.sis.util.LEsc;
import br.com.tucanobrasil.sis.util.RoundJTextField;
import br.com.tucanobrasil.sis.util.RoundPassJTextField;
import br.com.tucanobrasil.sis.util.Tempo;
import javax.swing.SwingConstants;

//Inicio da classe "Login"

	public class Login extends JFrame{

		private static final long serialVersionUID = 1L;

		//Declaração das Variaveis/Atributos
			private JLabel rotData, rotCod, rotSen, lblSubTit, lblVisSolicitaesRh, rotData_1;
			private JTextField texCod;
			private JPasswordField texSen;
			boolean ok = false;

		//Método que inicializa a janela	
			public Login(){

				//Chama os métodos
					janela();//Cria janela
					proParal();//Método que aualiza a hora

					setVisible(true);
			}

		//Fim do método "login"	

		//Método que cria a janela
			private void janela() {			

				//Propriedades da janela
					setTitle("Tucano - Ponto");
					setSize(242, 442);
					getContentPane().setLayout(null);
					setLocationRelativeTo(null);
					setResizable(false);
					setDefaultCloseOperation(3);
					setIconImage(new ImageIcon("./Icons/32x32.png").getImage());
					getContentPane().setBackground(new Color(0, 51, 90));
						
					//Rótulo do cabeçalho	
						lblSubTit = new JLabel("Registro Ponto");
						lblSubTit.setForeground(Color.WHITE);
						lblSubTit.setFont(new Font("Roboto", Font.BOLD, 23));
						lblSubTit.setBounds(10, 12, 502, 28);
						getContentPane().add(lblSubTit);	

					//Rótulo/Botão "Solicitar alteração RH"
						JLabel lblSolicitarAlteraoRh = new JLabel("Solicitar alteração RH");
						lblSolicitarAlteraoRh.setForeground(Color.WHITE);
						lblSolicitarAlteraoRh.setFont(new Font("Roboto", Font.BOLD, 17));
						lblSolicitarAlteraoRh.setBounds(12, 329, 215, 18);
						lblSolicitarAlteraoRh.setCursor(Cursor.getPredefinedCursor(12));
						lblSolicitarAlteraoRh.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
						public void mousePressed(MouseEvent arg0) {}
						public void mouseExited(MouseEvent arg0) {}
						public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { login();
						if (ok) {
							dispose();//Fecha a janela
							new Solicitacao();
						}else {
								Ig.alert("Nome ou senha incorretos! Tente novamente.");
							}
						}});
						getContentPane().add(lblSolicitarAlteraoRh);
							
					//Rótulo/Botão "Vizualizar histórico ponto"	
						JLabel lblVisualizarHistoricoPonto = new JLabel("Visualizar historico ponto");
						lblVisualizarHistoricoPonto.setForeground(Color.WHITE);
						lblVisualizarHistoricoPonto.setFont(new Font("Roboto", Font.BOLD, 17));
						lblVisualizarHistoricoPonto.setBounds(12, 387, 246, 18);
						lblVisualizarHistoricoPonto.setCursor(Cursor.getPredefinedCursor(12));
						lblVisualizarHistoricoPonto.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
						public void mousePressed(MouseEvent arg0) {}
						public void mouseExited(MouseEvent arg0) {}
						public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { String res = Rede.httpPost("LOG" + texCod.getText() + "\001" + new String(texSen.getPassword()));
							if (res.substring(0, 3).equals("LOK")) {
								dispose();
								new Visual(res.substring(3));
							}else {
								Ig.alert("Nome ou senha incorretos! Tente novamente.");
							}
						}});
						getContentPane().add(lblVisualizarHistoricoPonto);

					//Rótulo/Botão "Vis. solicitações RH"		
						lblVisSolicitaesRh = new JLabel("Vis. solicitações RH");
						lblVisSolicitaesRh.setForeground(Color.WHITE);
						lblVisSolicitaesRh.setFont(new Font("Roboto", Font.BOLD, 17));
						lblVisSolicitaesRh.setBounds(12, 358, 215, 18);
						lblVisSolicitaesRh.setCursor(Cursor.getPredefinedCursor(12));
						getContentPane().add(lblVisSolicitaesRh);
						lblVisSolicitaesRh.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
						public void mousePressed(MouseEvent arg0) {}
						public void mouseExited(MouseEvent arg0) {}
						public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { login();
							if (!ok) return;
								new Solicitacoes(br.com.tucanobrasil.M.ID);						        
								dispose();
							}
						});
						
					//Rótulo "Data"
						rotData = new JLabel(Tempo.extensoData());
						rotData.setForeground(Color.WHITE);
						rotData.setBounds(10, 52, 217, 21);
						rotData.setFont(new Font("Roboto", Font.BOLD, 17));
						getContentPane().add(rotData);

					//Rótulo "Código"	
						rotCod = new JLabel("Código");
						rotCod.setHorizontalAlignment(SwingConstants.CENTER);
						rotCod.setForeground(Color.WHITE);
						rotCod.setBounds(12, 110, 217, 18);
						rotCod.setFont(new Font("Roboto", Font.BOLD, 17));
						getContentPane().add(rotCod);

					//Rótulo "Senha"	
						rotSen = new JLabel("Senha");
						rotSen.setHorizontalAlignment(SwingConstants.CENTER);
						rotSen.setForeground(Color.WHITE);
						rotSen.setBounds(12, 187, 217, 14);
						rotSen.setFont(new Font("Roboto", Font.BOLD, 17));
						getContentPane().add(rotSen);

					//Campo de texto "Código"
						texCod = new RoundJTextField(1);
						texCod.setBounds(10, 140, 222, 31);
						texCod.setHorizontalAlignment(0);
						texCod.setFont(new Font("Roboto", Font.BOLD, 20));
						texCod.setForeground(Color.BLACK);
						getContentPane().add(texCod);
						texCod.addKeyListener(new KeyListener() {
						public void keyTyped(KeyEvent arg0) {}					      
						public void keyReleased(KeyEvent arg0) {}					      
						public void keyPressed(KeyEvent arg0) {
								if (arg0.getKeyCode() == 10) {
									texSen.requestFocus();
								}
							}
						});
						texCod.getDocument().addDocumentListener(new DocumentListener(){
							public void changedUpdate(DocumentEvent arg0){regrasCod();}						      
							public void insertUpdate(DocumentEvent arg0){regrasCod();}						      
							public void removeUpdate(DocumentEvent arg0){regrasCod();}				
						});

					

					//Campo de texto "Senha"
						texSen = new RoundPassJTextField(1);
						texSen.setBounds(10, 211, 222, 31);
						texSen.setFont(new Font("Roboto", Font.BOLD, 20));
						texSen.setForeground(Color.BLACK);
						getContentPane().add(texSen);
						texSen.setHorizontalAlignment(0);
						texSen.addKeyListener(new KeyListener(){
							public void keyTyped(KeyEvent arg0) {}
							public void keyReleased(KeyEvent arg0){
								if (ok) {
									dispose();new Registro();
								}
							}		
							public void keyPressed(KeyEvent arg0) {}
						});
						texSen.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							login();
						}
						});
						texSen.getDocument().addDocumentListener(new DocumentListener()		{
							public void changedUpdate(DocumentEvent arg0){regrasSen();}				      
							public void insertUpdate(DocumentEvent arg0){regrasSen();}	
							public void removeUpdate(DocumentEvent arg0){regrasSen();}});
						
						JLabel lblNewLabel = new JLabel("");
						lblNewLabel.setIcon(new ImageIcon("/home/fontanela/eclipse-workspace/TucanoERP/Icons/3005767-48.png"));
						lblNewLabel.setBounds(97, 254, 48, 48);
						getContentPane().add(lblNewLabel);
						
						rotData_1 = new JLabel(Tempo.horario());
						rotData_1.setForeground(Color.WHITE);
						rotData_1.setFont(new Font("Roboto", Font.BOLD, 17));
						rotData_1.setBounds(10, 77, 217, 21);
						getContentPane().add(rotData_1);				
			}

		private void login() { 
			String res = Rede.httpPost("LOG" + texCod.getText() + "\001" + new String(texSen.getPassword()));
			if (res.substring(0, 3).equals("LOK")) {
				M.ID = res.substring(3);
				ok = true;
			}else {
				Ig.alert("Nome ou senha incorretos.");
			}
		}

		//Método que que marca o ponto baseado na hora atual
			private void proParal() {

				new Timer().schedule(new TimerTask() {

					public void run() {
						rotData.setText(Tempo.extensoData());
						rotData_1.setText(Tempo.horario());
					}

				}, 1000L, 1000L);

			}

		//Método que define as regras para o campo de texto "Código"	  
			private void regrasCod() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {

						if (!LEsc.eSoNumero(texCod.getText())) {
							texCod.setText("");
							return;
						}

						if (texCod.getText().length() >= 2) {
							texSen.requestFocus();
							return;
						}
					}
				});
			}
		//Fim do método "regrasCod"  
			
		//Método de regras de senha
			private void regrasSen() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						
						String pass = new String(texSen.getPassword());						    

						if (!LEsc.eSoNumero(pass)) {
							texSen.setText("");
							return;
						}
					}

				});

			}
	}