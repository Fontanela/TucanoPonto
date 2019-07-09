package br.com.tucanobrasil.ig;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import br.com.tucanobrasil.M;
import br.com.tucanobrasil.sis.util.Zip;

//Classe de atualização
	public class Atualizacao extends JFrame{
	
		private static final long serialVersionUID = 1L;
		
		//Declaração das Variaveis/Atributos
			public JProgressBar pb;
			public JLabel rot;
			public static int percent;
		
		//Método que inicializa a janela	
			public Atualizacao(){
				janela();
				 
				//Rótulo da porcentagem da atualização
					this.rot = new JLabel("<html><b>0%");
					this.rot.setFont(new Font("Arial", 0, 14));
					this.rot.setBounds(251, 10, 170, 21);
					getContentPane().add(this.rot);
					    
				//Barra de progresso
					this.pb = new JProgressBar();
					this.pb.setBounds(10, 10, 511, 26);		    
					getContentPane().add(this.pb);
					    
				//Cria uma variavel que atualiza com o tempo
				Timer t = new Timer();
				t.schedule(new TimerTask() {
					public void run() {
					
						//Atuliza o valor da porcentagem
							Atualizacao.this.pb.setValue(Atualizacao.percent);
							Atualizacao.this.rot.setText("<html><b>" + Atualizacao.percent + "%");
						
						//Se o valor da porcentagem for igual a 100
							if (Atualizacao.percent == 100) {
							
								//Ele atualiza
									Zip.unZipIt("tucpon.zip", M.SYSDIR);
									Atualizacao.this.dispose();
									
									try{
										Runtime.getRuntime().exec("cmd /c java -jar " + M.CNAME);
									} 
									catch (IOException e) {
										e.printStackTrace();
									}
									
									System.exit(0);
							}
					}
				}, 33L, 33L);
				
				//Deixa o painel visivel
					setVisible(true);
			}
			  
		//Método que cria a janela
			private void janela() {
				setTitle("Tucano Ponto - Baixando atualização");
				setSize(540, 78);
				getContentPane().setLayout(null);
				setLocationRelativeTo(null);
				setResizable(false);
				setIconImage(new ImageIcon("32x32.png").getImage());
				getContentPane().setBackground(new Color(255, 255, 181));
				}
		}
//Fim da classe