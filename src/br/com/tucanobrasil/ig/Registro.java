package br.com.tucanobrasil.ig;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import br.com.tucanobrasil.sis.rede.Rede;
import br.com.tucanobrasil.sis.util.Ig;
import br.com.tucanobrasil.sis.util.Tempo;
 
 
 
 
	public class Registro extends JFrame{
	
		private static final long serialVersionUID = 1L;
		
		//Declaração das Variaveis/Atributos
			private JLabel rotCod;
			private JTextField cbTipo;
			JButton button;
			String horaVERIFICA = null;
		   
		//Método que inicializa a janela	
			public Registro(){
			
				//Chama os métodos
					janela();//Cria janela
					criarRotulos();//Cria rótulos
					criarTextos();//Cria textos	     
					adiComps();//Adiciona os componentes no painel
				    
				//Atribui os valores de hora e minuto a variavel "hora"	
					int hora = Integer.valueOf(Tempo.hora()).intValue();
				
				//Criação da variavel "set", sendo incializada com o valor nulo	
					String set = null;
					
				//Se a hora for maoir que 00:01 e menor que 11:00, mostra entrada manhã
					if (hora > 0001 && hora <= 1100 ) {
						set = "EM";
						horaVERIFICA = "EM";				       
					}
					
				//Se a hora for maoir que 11:01, e menor que 12:40, mostra saída manhã    
					if (hora >= 1101 && hora <= 1240 ) {
						set = "SM";
						horaVERIFICA = "SM";
					}
				     
				//Se a hora for maoir que 12:41, e menor que 15:00, mostra entrada tarde	
					if (hora >= 1241 && hora <= 1500) {
						set = "ET";
						horaVERIFICA = "ET";					                  
					}
				
				//Se a hora for maoir que 15:01, e menor que 23:59, mostra saída tarde	
					if (hora >= 1501 && hora <= 2359) {
						set = "ST";
						horaVERIFICA = "ST";					     
					}
					
				
				//O campo de texto assume o texto da hora	
					this.cbTipo.setText(set);
				
				//Registra ao apertar "ENTER"
					this.cbTipo.addKeyListener(new KeyListener(){
						public void keyTyped(KeyEvent arg0) {}    
						public void keyReleased(KeyEvent arg0){
							if (arg0.getKeyCode() == 10) {

								//Se for entrada manha pergunta se deseja mesmo registrar, se sim registra, se não, volta
									if(horaVERIFICA == "EM") {
										if(Ig.doubt("Você deseja registrar ENTRADA MANHÃ?")){ 
											Registro.this.rpt(); 
										}
										else { 
											return; 
										}
									}
								
								//Se for saida manha pergunta se deseja mesmo registrar, se sim registra, se não, volta	
									else if(horaVERIFICA == "SM"){
										if(Ig.doubt("Você deseja registrar SAÍDA MANHÃ?")){ 
											Registro.this.rpt(); }
										else{ 
											return; 
										}
									}
									
								//Se for entrada tarde pergunta se deseja mesmo registrar, se sim registra, se não, volta
									else if(horaVERIFICA == "ET"){		
										if(Ig.doubt("Você deseja registrar ENTRADA TARDE?")){ 
											Registro.this.rpt(); }
										else { 
											return; 
										}
									}
									
								//Se for saida tarde pergunta se deseja mesmo registrar, se sim registra, se não, volta
									else if(horaVERIFICA == "ST"){	
										if(Ig.doubt("Você deseja regisstrar SAÍDA TARDE?")){ 
											Registro.this.rpt(); }
										else { 
										return; 
										}
									}
							}
						}
						public void keyPressed(KeyEvent arg0) {}
					});
					
				//Botão "OK"
					this.button = new JButton("<html>OK &nbsp; ");
					this.button.setHorizontalTextPosition(2);
					this.button.setHorizontalAlignment(0);
					this.button.setBounds(184, 46, 115, 40);
					this.button.setIcon(Ig.icone("fim.png"));
					getContentPane().add(this.button);
				     
					this.button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
					
							//Se for entrada manha pergunta se deseja mesmo registrar, se sim registra, se não, volta
								if(horaVERIFICA == "EM") {
									if(Ig.doubt("Você deseja registrar ENTRADA MANHÃ?")){ 
										Registro.this.rpt(); 
									}
									else { 
										return; 
									}
								}
							
							//Se for saida manha pergunta se deseja mesmo registrar, se sim registra, se não, volta	
								else if(horaVERIFICA == "SM"){
									if(Ig.doubt("Você deseja registrar SAÍDA MANHÃ?")){ 
										Registro.this.rpt(); }
									else{ 
										return; 
									}
								}
								
							//Se for entrada tarde pergunta se deseja mesmo registrar, se sim registra, se não, volta
								else if(horaVERIFICA == "ET"){		
									if(Ig.doubt("Você deseja registrar ENTRADA TARDE?")){ 
										Registro.this.rpt(); }
									else { 
										return; 
									}
								}
								
							//Se for saida tarde pergunta se deseja mesmo registrar, se sim registra, se não, volta
								else if(horaVERIFICA == "ST"){	
									if(Ig.doubt("Você deseja regisstrar SAÍDA TARDE?")){ 
										Registro.this.rpt(); }
									else { 
									return; 
									}
								}

						}					       
					});
					
				//Torna visivel	
					setVisible(true);
					
				//Cursor vai pro campo de texto	
					this.cbTipo.requestFocus();
			}
		//Fim do metódo "registro"	
		   
		//Método que efetua o registro e faz as verificações necesárias	
			private void rpt() {
			
			//Atribui os valores do campo para a variavel "cbTipo"
				this.cbTipo.setText(this.cbTipo.getText().toUpperCase());
				String tipo = this.cbTipo.getText();
			
				
				int val = -1;
				
				//Atribui um valor númerico inteiro dependendo do registro
					if (tipo.equals("EM")) {
						val = 0;
					}
					if (tipo.equals("SM")) {
						val = 1;
					}
					if (tipo.equals("ET")) {
						val = 2;
					}
					if (tipo.equals("ST")) {
						val = 3;
					}
					 
					//Se o valor for -1, que é o valor incial da variavel, sobe o aviso
						if (val == -1) {
							Ig.advertencia("Valor desconhecido.");
						return;
						}
				    
				//String com o vlaor que consta no banco		
					String t = Rede.httpPost("RPT" + val);
				
				//Se t for diferente  e o registro for "Saída Tarde"
					if ((!t.equals("JRE")) && (this.cbTipo.getText().equals("ST"))) {
						ArrayList<String> infos = Rede.httpArr("DHP");
				       
						boolean isemp = false;
						
						for (int i = 0; i < infos.size(); i++) {
							if (((String)infos.get(i)).equals("")) {
								isemp = true; break;
							}
						}
				       
						if (!isemp) {
							String manha = Tempo.difHoras((String)infos.get(0), (String)infos.get(1));
							String tarde = Tempo.difHoras((String)infos.get(2), (String)infos.get(3));
							String total = Tempo.calcLisHoras(new String[] { manha, tarde });
							String diff = Tempo.difHoras("8:48", total);
							         
							Rede.httpPost("FPT" + total + "\001" + diff).equals("FCS");
						}
						dispose();new Visual();
						return;
				}
				     
				//Se o registro estiver vazio ele abre a visualização do histórico ponto 
					if (t.equals("RES")) {
						dispose();
						new Visual();
					}
					else {
						Ig.advertencia("Você já fez esse registro.");
					}
			}
		   
		private void janela() {
		setTitle("Tucano Ponto - Registro");
		setSize(331, 136);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(3);
		setIconImage(new ImageIcon("32x32.png").getImage());
		getContentPane().setBackground(new Color(255, 255, 181));
		}
		   
		private void criarRotulos() {
		this.rotCod = new JLabel("Você deseja registrar:");
		this.rotCod.setBounds(10, 14, 145, 18);
		this.rotCod.setFont(new Font("Arial", 0, 14));
		}
		   
		private void criarTextos() {
		this.cbTipo = new JTextField();
		this.cbTipo.setBounds(160, 12, 139, 23);
		}
		   
		private void adiComps() {
		getContentPane().add(this.rotCod);
		     
		getContentPane().add(this.cbTipo);
		}
		}


