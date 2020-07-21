package br.com.tucanobrasil.ig;


//Bibliotecas
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.com.tucanobrasil.M;
import br.com.tucanobrasil.sis.rede.Rede;
import br.com.tucanobrasil.sis.tech.DBDrop;
import br.com.tucanobrasil.sis.util.Ig;
import br.com.tucanobrasil.sis.util.LEsc;
import br.com.tucanobrasil.sis.util.O;
import br.com.tucanobrasil.sis.util.P;
import br.com.tucanobrasil.sis.util.Tempo;

//Inicio da classe
	public class Registro extends JFrame{
	
		private static final long serialVersionUID = 1L;

		//Declaraçãoo das Variaveis/Atributos
			private JLabel rotCod;
			String sm = "", almoco = "", amcoc = "";
			String[] tps = {"Entrada Manhã", "Saída Manhã", "Entrada Tarde", "Saída Tarde"};
			DBDrop dropTipoReg;
			int armoco;

		//Método que inicializa a janela	
			public Registro(){
		
				//Chama os métodos
					janela();//Cria janela
					
				//Torna visivel	
					setVisible(true);
			}

		//Fim do método "registro"							  

		//Método que cria a janela	
			private void janela() {

				//Propriedades da janela
					setTitle("Tucano Ponto - Registro");
					setSize(331, 171);
					getContentPane().setLayout(null);
					setLocationRelativeTo(null);
					setResizable(false);
					setIconImage(new ImageIcon("32x32.png").getImage());
					getContentPane().setBackground(new Color(0, 51, 90));
					
				//Rótulo "Você deseja registrar:"
					rotCod = new JLabel("Você deseja registrar");
					rotCod.setHorizontalAlignment(SwingConstants.CENTER);
					rotCod.setForeground(Color.WHITE);
					rotCod.setFont(new Font("Roboto", Font.BOLD, 20));
					rotCod.setBounds(49, 22, 230, 24);	
					getContentPane().add(rotCod);

				//Menu cascata do tipo de registro	 
					dropTipoReg = new DBDrop();
					dropTipoReg.setBounds(49, 60, 230, 25);
					getContentPane().add(dropTipoReg);
					dropTipoReg.setForeground(Color.BLACK);
					dropTipoReg.setFont(new Font("Roboto", Font.BOLD, 17));
					((JLabel) dropTipoReg.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
					for(int i = 0; i < tps.length; i++) {
						dropTipoReg.addRow(i+"", tps[i]);
					}
					dropTipoReg.addKeyListener(new KeyListener() {
						public void keyTyped(KeyEvent arg0) {}					      
						public void keyReleased(KeyEvent arg0) {}					      
						public void keyPressed(KeyEvent e) {
							if(e.getKeyCode() == KeyEvent.VK_ENTER){
								finalizaq();
							}			
						}});
					
					String[] infos = Rede.post("DHP", "").split(O.SOH);

					int hora = Integer.valueOf(Tempo.hora()).intValue();
					
					if(hora > 1200 && infos.length == 0) {
						dropTipoReg.setSelectedIndex(2);
					}else {
						dropTipoReg.setSelectedIndex(infos.length);	
					}
					
			}
		//Fim do método "janela"	
		
		//Método que finaliza o registo e faz as peruntas	
			private void finalizaq() {
				String horar = Tempo.horarr();
				ArrayList<String> infos = Rede.httpArr("DHP");

				if(!infos.contains("NFR")) {
					sm = (String)infos.get(1);	
				}

				almoco = Tempo.diffHours(sm, horar);
				amcoc = almoco.replace(":","");
				armoco = LEsc.STI(amcoc);
				
				int tp = P.STI(dropTipoReg.getCurrValue());				
				
				if(armoco > 30 && armoco < 60 ) {		
					if(Ig.doubt("O intervalo de almoço é de 1 hora \r\n Deseja continuar?")){ 						
					}else { 
						return; 
					}
				}
				
				if(Ig.doubt("Você deseja registrar " + tps[tp] + "?")){ 
					rpt(); 
				}
			}
		//Fim do método "finalizaq"	

		//Método que efetua o registro e faz as verificações necessárias	
			private void rpt() {

				int val = P.STI(dropTipoReg.getCurrValue());

				//String com o valor que consta no banco		
					String t = Rede.httpPost("RPT" + val);

				//Se t for diferente  e o registro for "Saída Tarde"
					if ((!t.equals("JRE")) && val == 3) {
						ArrayList<String> infos = Rede.httpArr("DHP");

						boolean isemp = false;

						for (int i = 0; i < infos.size(); i++) {
							if (((String)infos.get(i)).equals("")) {
								isemp = true; break;
							}
						}

						if (!isemp) {
							String manha = Tempo.diffHours((String)infos.get(0), (String)infos.get(1));
							String tarde = Tempo.diffHours((String)infos.get(2), (String)infos.get(3));	
							String total = Tempo.calcHoursList(new String[] { manha, tarde });
							String diff = Tempo.diffHours("08:48", total);

							Rede.httpPost("FPT" + total + "" + diff);
						}
						dispose();new Visual(M.ID);
						return;
				}

				System.out.println(t);	
					
					if (t.equals("RES")) {
						dispose();
						new Visual(M.ID);
					}else {
						Ig.alert("Você já fez esse registo");
					}
			}
		//Fim do método "rpt"			
	}
//Fim da classe	