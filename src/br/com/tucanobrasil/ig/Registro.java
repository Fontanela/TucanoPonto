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
import br.com.tucanobrasil.sis.util.LEsc;
import br.com.tucanobrasil.sis.util.Tempo;





 

 

 

 

	public class Registro extends JFrame{

	

		private static final long serialVersionUID = 1L;

		

		//Declara��o das Variaveis/Atributos

			private JLabel rotCod;

			private JTextField cbTipo;

			JButton button;

			String horaVERIFICA = null;

		   

		//M�todo que inicializa a janela	

			public Registro(){

			

				//Chama os m�todos

					janela();//Cria janela

					criarRotulos();//Cria r�tulos

					criarTextos();//Cria textos	     

					adiComps();//Adiciona os componentes no painel

					

					

				    

				//Atribui os valores de hora e minuto a variavel "hora"	

					int hora = Integer.valueOf(Tempo.hora()).intValue();

					

				//Verificação do tempo minimo de almoço	

					

					//Pegando a hora exata, com minutos

						String horar = Tempo.horarr();

										

					//Pegando os dados do histórico de ponto	

						ArrayList<String> infos = Rede.httpArr("DHP");

						

					//Atribuindo os dados da Saída manhã para a variável sm

						String sm = (String)infos.get(1);

															

					//Calculando a diferença entre o horário de sáida e o horário atual

						String almoco = Tempo.diffHours(sm, horar);

						

					//Retirando o separador de horas ":"	

						String amcoc = almoco.replace(":","");

						

					//Tranformando a string em valor integral	

						int armoco = LEsc.STI(amcoc);

						

	

				

				//Cria��o da variavel "set", sendo incializada com o valor nulo	

					String set = null;

					

				//Se a hora for maoir que 00:01 e menor que 11:00, mostra entrada manh�

					if (hora > 0001 && hora <= 1100 ) {

						set = "EM";

						horaVERIFICA = set;				       

					}

					

				//Se a hora for maoir que 11:01, e menor que 12:40, mostra sa�da manh�    

					if (hora >= 1101 && hora <= 1240 ) {

						set = "SM";

						horaVERIFICA = set;

					}

				     

				//Se a hora for maoir que 12:41, e menor que 15:00, mostra entrada tarde	

					if (hora >= 1214 && hora <= 1500) {

						set = "ET";

						horaVERIFICA = set;					                  

					}

				

				//Se a hora for maoir que 15:01, e menor que 23:59, mostra sa�da tarde	

					if (hora >= 1501 && hora <= 2359) {

						set = "ST";

						horaVERIFICA = set;					     

					}

					

				

				//O campo de texto assume o texto da hora	

					this.cbTipo.setText(set);

				

				//Registra ao apertar "ENTER"

					this.cbTipo.addKeyListener(new KeyListener(){

						public void keyTyped(KeyEvent arg0) {}    

						public void keyReleased(KeyEvent arg0){

							if (arg0.getKeyCode() == 10) {



								//Se for entrada manha pergunta se deseja mesmo registrar, se sim registra, se n�o, volta

									if(horaVERIFICA == "EM") {

										if(Ig.doubt("Voc� deseja registrar ENTRADA MANH�?")){ 

											Registro.this.rpt(); 

										}

										else { 

											return; 

										}

									}

								

								//Se for saida manha pergunta se deseja mesmo registrar, se sim registra, se n�o, volta	

									else if(horaVERIFICA == "SM"){

										if(Ig.doubt("Voc� deseja registrar SA�DA MANH�?")){ 

											Registro.this.rpt(); }

										else{ 

											return; 

										}

									}

									

								//Se for entrada tarde pergunta se deseja mesmo registrar, se sim registra, se n�o, volta

									else if(horaVERIFICA == "ET"){	

										

										//Se o tempo de almoço for superior a 30 minutos mas inferior a 50

											if(armoco > 30 && armoco < 50 ) {		

												if(Ig.doubt("O intervalo de almoço é de 1 hora \r\n Deseja continuar?")){ 

													if(Ig.doubt("Você deseja registrar ENTRADA TARDE?")){ 

														Registro.this.rpt(); }

													else { 

														return; 

													}

												}

												else { 

													return; 

												}

											}

										

										//Se o tempo minimo for menor que 30	

											else if(armoco < 30) {

												Ig.erro("    Não é possivel fazer seu registro \r\n *Tempo minimo de almoço: 30 minutos \r\n Favor pedir autorização ao RH");

											}

											

										//Se não cumprir com nenhum requisito	

											else {

												if(Ig.doubt("Você deseja registrar ENTRADA TARDE?")){ 

													Registro.this.rpt(); }

												else { 

													return; 

												}

											}

			

									}

									

								//Se for saida tarde pergunta se deseja mesmo registrar, se sim registra, se n�o, volta

									else if(horaVERIFICA == "ST"){	

										if(Ig.doubt("Voc� deseja regisstrar SA�DA TARDE?")){ 

											Registro.this.rpt(); }

										else { 

										return; 

										}

									}

							}

						}

						public void keyPressed(KeyEvent arg0) {}

					});

					

				//Bot�o "OK"

					this.button = new JButton("<html>OK &nbsp; ");

					this.button.setHorizontalTextPosition(2);

					this.button.setHorizontalAlignment(0);

					this.button.setBounds(184, 46, 115, 40);

					this.button.setIcon(Ig.icone("fim.png"));

					getContentPane().add(this.button);

				     

					this.button.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent arg0) {



							

							//Se for entrada manha pergunta se deseja mesmo registrar, se sim registra, se n�o, volta

								if(horaVERIFICA == "EM") {

									if(Ig.doubt("Voc� deseja registrar ENTRADA MANH�?")){ 

										Registro.this.rpt(); 

									}

									else { 

										return; 

									}

								}

							

							//Se for saida manha pergunta se deseja mesmo registrar, se sim registra, se n�o, volta	

								else if(horaVERIFICA == "SM"){

																	

									if(Ig.doubt("Você deseja registrar SAÍDA MANHÃ?")){ 

										Registro.this.rpt(); }

									else{ 

										return; 

									}

								}

								

							//Se for entrada tarde pergunta se deseja mesmo registrar, se sim registra, se n�o, volta

								else if(horaVERIFICA == "ET"){											

									

									//Se o tempo de almoço for superior a 30 minutos mas inferior a 50

										if(armoco > 30 && armoco < 50 ) {		

											if(Ig.doubt("O intervalo de almoço é de 1 hora \r\n Deseja continuar?")){ 

												if(Ig.doubt("Você deseja registrar ENTRADA TARDE?")){ 

													Registro.this.rpt(); }

												else { 

													return; 

												}

											}

											else { 

												return; 

											}

										}

									

									//Se o tempo minimo for menor que 30	

										else if(armoco < 30) {

											Ig.erro("    Não é possivel fazer seu registro \r\n *Tempo minimo de almoço: 30 minutos \r\n Favor pedir autorização ao RH");

										}

										

									//Se não cumprir com nenhum requisito	

										else {

											if(Ig.doubt("Você deseja registrar ENTRADA TARDE?")){ 

												Registro.this.rpt(); }

											else { 

												return; 

											}

										}

									

								}

								

							//Se for saida tarde pergunta se deseja mesmo registrar, se sim registra, se n�o, volta

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

		//Fim do met�do "registro"	

		   

		//M�todo que efetua o registro e faz as verifica��es neces�rias	

			private void rpt() {

			

			//Atribui os valores do campo para a variavel "cbTipo"

				this.cbTipo.setText(this.cbTipo.getText().toUpperCase());

				String tipo = this.cbTipo.getText();

			

				

				int val = -1;

				

				//Atribui um valor n�merico inteiro dependendo do registro

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

					 

					//Se o valor for -1, que � o valor incial da variavel, sobe o aviso

						if (val == -1) {

							Ig.advertencia("Valor desconhecido.");

						return;

						}

				    

				//String com o vlaor que consta no banco		

					String t = Rede.httpPost("RPT" + val);

				

				//Se t for diferente  e o registro for "Sa�da Tarde"

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

				     

				//Se o registro estiver vazio ele abre a visualiza��o do hist�rico ponto 

					if (t.equals("RES")) {

						dispose();

						new Visual();

					}

					else {

						Ig.advertencia("Voc� j� fez esse registro.");

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

		this.rotCod = new JLabel("Voc� deseja registrar:");

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





