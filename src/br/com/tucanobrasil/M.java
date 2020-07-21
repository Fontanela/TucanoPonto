package br.com.tucanobrasil;
//Bibliotecas
import java.awt.Color;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import br.com.tucanobrasil.ig.Atualizacao;
import br.com.tucanobrasil.ig.Login;
import br.com.tucanobrasil.sis.rede.Rede;
import br.com.tucanobrasil.sis.util.Arquivo;
import br.com.tucanobrasil.sis.util.Ig;

//Criação da classe "M"

	public class M{

		//Declaração das Variaveis/Atributos
		   public static String LNK = "";
		   public static KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		   public static KeyboardFocusManager MANAGERII = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		   public static String SYSDIR = System.getProperty("user.dir");  
		   public static final String TIT = "TucPonto - ";
		   public static int VERSAO = 333;
		   public static String STRVER = "3.0.5";
		   public static String CNAME = "tucpon.jar";
		   public static final String NOMEDOWN = "tucpon.zip";
		   public static String ID = "";

		//Método que inicia o sistema

			   public static void main(String[] args) {

				 //Gerenciador java

					try {

						

						//Verificação do sistema operacional que está em uso

							String os = System.getProperty("os.name");

						

						/*Se o sistema operacional for linux ele usar o look and feel GTK, padrão do linux	

						  Se não, usa o look and fell padrão da máquina windows em que estiver*/ 

							/*if(os.equals("Linux")) {

								UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");

							}

							else {*/

								UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

							//}

			

					} catch (ClassNotFoundException e) {

						e.printStackTrace();

					} catch (InstantiationException e) {

						e.printStackTrace();

					} catch (IllegalAccessException e) {

						e.printStackTrace();

					} catch (UnsupportedLookAndFeelException e) {

						e.printStackTrace();

					}

				    

				     UIDefaults defaults = UIManager.getLookAndFeelDefaults();

				    if (defaults.get("Table.alternateRowColor") == null) {

				       defaults.put("Table.alternateRowColor", new Color(206, 206, 206));

				    }

				    

				    UIManager.put("Button.font", new Font("Arial", 1, 14));

				    UIManager.put("MenuBar.font", new Font("Arial", 0, 14));

				    UIManager.put("MenuItem.font", new Font("Arial", 0, 14));

				    UIManager.put("Menu.font", new Font("Arial", 0, 14));

				    UIManager.put("PopupMenu.font", new Font("Arial", 0, 14));

				    UIManager.put("OptionPane.font", new Font("Arial", 0, 14));

				    UIManager.put("Panel.font", new Font("Arial", 0, 14));

				    UIManager.put("Viewport.font", new Font("Arial", 0, 14));

				    UIManager.put("TabbedPane.font", new Font("Arial", 0, 14));

				    UIManager.put("Table.font", new Font("Arial", 0, 17));

				    UIManager.put("TableHeader.font", new Font("Arial", 0, 14));

				    UIManager.put("TextField.font", new Font("Arial", 0, 14));

				    UIManager.put("ComboBox.font", new Font("Arial", 1, 14));

				    UIManager.put("PasswordField.font", new Font("Arial", 0, 14));

				    UIManager.put("TextArea.font", new Font("Arial", 0, 14));

				    UIManager.put("TextPane.font", new Font("Arial", 0, 14));

			    

				//Conexão com o servidor
				    LNK = (String)Arquivo.ler("cfg.ini").get(0);			    

				    //Checa a versão do software
					    Rede.setPHPSESSID();
					    Rede.DEST = "cvs_server.php";
					    String versao = Rede.httpPost("CVI" + VERSAO + ";1");
					     if (versao.substring(0, 3).equals("VOB")) {

					      if (Ig.doubt("O sistema precisa ser atualizado, deseja prosseguir agora?")) {
					         String tamanho = versao.substring(3);
					         new Atualizacao();
					        
					         downAtua(LNK + "/releases/" + "tucpon.zip", "tucpon.zip", Double.parseDouble(tamanho));
					         return;
					      }

					      return;
					    }

					     Rede.DEST = "server.php";
					     
					   //Criação do método de gerenciamento de teclas
							M.MANAGERII.addKeyEventDispatcher(new KeyEventDispatcher() {
								public boolean dispatchKeyEvent(KeyEvent e) { 
									if (e.getID() == KeyEvent.KEY_RELEASED) {					
										if(e.getKeyCode() == KeyEvent.VK_F11){
											if(Rede.PCKTLOG == 0) {
												Rede.PCKTLOG = 1;
												System.err.println("PACKETLOGGER ACTIVATED");
											}
											else {
												Rede.PCKTLOG = 0;
												System.err.println("PACKETLOGGER DEACTIVATED");
											}
										}
									}
									return false;
								}
							});
					     
			//Inicia a tela de login
			    new Login();

			  }

			   

		  //Método que atualiza o sistema

			public static void downAtua(String site, String file, double bytes){

			    try {

			       URL url = new URL(site);

			       InputStream in = url.openStream();

			      

			       FileOutputStream fos = new FileOutputStream(new File(file));

			      

			       int length = -1;

			       byte[] buffer = new byte['�'];

			      

				   int size = 0;

				   while ((length = in.read(buffer)) > -1) {

					   	fos.write(buffer, 0, length);

					    size += length;

					        

					    double f = size / bytes * 100.0D;

					        

					    Atualizacao.percent = (int)f;

				   }

				   

				   fos.close();

				   in.close();

			    } 

			    catch (IOException e) {

			    	e.printStackTrace();

			    }

			  }

			//Fim do método "downAtua"

	}

//Fim da classe

