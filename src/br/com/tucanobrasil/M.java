/*     */ package br.com.tucanobrasil;
/*     */ 
/*     */ import br.com.tucanobrasil.ig.Atualizacao;
/*     */ import br.com.tucanobrasil.ig.Login;
/*     */ import br.com.tucanobrasil.sis.rede.Rede;
/*     */ import br.com.tucanobrasil.sis.util.Arquivo;
/*     */ import br.com.tucanobrasil.sis.util.Ig;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.KeyboardFocusManager;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import javax.swing.UIDefaults;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ public class M
/*     */ {
/*  24 */   public static String LNK = "";
/*  25 */   public static KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
/*     */   
/*  27 */   public static String SYSDIR = System.getProperty("user.dir");
/*     */   
/*     */ 
/*  30 */   public static int VERSAO = 333;
/*  31 */   public static String STRVER = "3.0.5";
/*  32 */   public static String CNAME = "tucpon.jar";
/*     */   
/*     */   public static final String NOMEDOWN = "tucpon.zip";
/*  35 */   public static String ID = "";
/*     */   
/*     */   public static void main(String[] args) {
/*  38 */     try { UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
/*     */     } catch (ClassNotFoundException e) {
/*  40 */       e.printStackTrace();
/*     */     } catch (InstantiationException e) {
/*  42 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/*  44 */       e.printStackTrace();
/*     */     } catch (UnsupportedLookAndFeelException e) {
/*  46 */       e.printStackTrace();
/*     */     }
/*     */     
/*  49 */     UIDefaults defaults = UIManager.getLookAndFeelDefaults();
/*  50 */     if (defaults.get("Table.alternateRowColor") == null) {
/*  51 */       defaults.put("Table.alternateRowColor", new Color(206, 206, 206));
/*     */     }
/*     */     
/*  54 */     UIManager.put("Button.font", new Font("Arial", 1, 14));
/*  55 */     UIManager.put("MenuBar.font", new Font("Arial", 0, 14));
/*  56 */     UIManager.put("MenuItem.font", new Font("Arial", 0, 14));
/*  57 */     UIManager.put("Menu.font", new Font("Arial", 0, 14));
/*  58 */     UIManager.put("PopupMenu.font", new Font("Arial", 0, 14));
/*  59 */     UIManager.put("OptionPane.font", new Font("Arial", 0, 14));
/*  60 */     UIManager.put("Panel.font", new Font("Arial", 0, 14));
/*  61 */     UIManager.put("Viewport.font", new Font("Arial", 0, 14));
/*  62 */     UIManager.put("TabbedPane.font", new Font("Arial", 0, 14));
/*  63 */     UIManager.put("Table.font", new Font("Arial", 0, 17));
/*  64 */     UIManager.put("TableHeader.font", new Font("Arial", 0, 14));
/*  65 */     UIManager.put("TextField.font", new Font("Arial", 0, 14));
/*  66 */     UIManager.put("ComboBox.font", new Font("Arial", 1, 14));
/*  67 */     UIManager.put("PasswordField.font", new Font("Arial", 0, 14));
/*  68 */     UIManager.put("TextArea.font", new Font("Arial", 0, 14));
/*  69 */     UIManager.put("TextPane.font", new Font("Arial", 0, 14));
/*     */     
/*  71 */     LNK = (String)Arquivo.ler("cfg.ini").get(0);
/*     */     
/*  73 */     Rede.setPHPSESSID();
/*  74 */     Rede.DEST = "cvs_server.php";
/*     */     
/*  76 */     String versao = Rede.httpPost("CVI" + VERSAO + ";1");
/*  77 */     System.out.println("CVI" + VERSAO + ";1");
/*  78 */     if (versao.substring(0, 3).equals("VOB")) {
/*  79 */       if (Ig.duvida("O sistema precisa ser atualizado, deseja prosseguir agora?")) {
/*  80 */         String tamanho = versao.substring(3);
/*  81 */         new Atualizacao();
/*     */         
/*  83 */         downAtua(LNK + "/releases/" + "tucpon.zip", "tucpon.zip", Double.parseDouble(tamanho));
/*  84 */         return;
/*     */       }
/*     */       
/*  87 */       return;
/*     */     }
/*     */     
/*     */ 
/*  91 */     Rede.DEST = "pon_servidor.php";
/*  92 */     String hAt = Rede.httpPost("PHA");
/*     */     try {
/*  94 */       Runtime.getRuntime().exec("cmd /c time " + hAt);
/*     */     } catch (IOException e) {
/*  96 */       e.printStackTrace();
/*     */     }
/*     */     
/*     */ 
/* 100 */     new Login();
/*     */   }
/*     */   
/*     */   public static void downAtua(String site, String file, double bytes)
/*     */   {
/*     */     try {
/* 106 */       URL url = new URL(site);
/* 107 */       InputStream in = url.openStream();
/*     */       
/* 109 */       FileOutputStream fos = new FileOutputStream(new File(file));
/*     */       
/* 111 */       int length = -1;
/* 112 */       byte[] buffer = new byte['È'];
/*     */       
/* 114 */       int size = 0;
/* 115 */       while ((length = in.read(buffer)) > -1) {
/* 116 */         fos.write(buffer, 0, length);
/* 117 */         size += length;
/*     */         
/* 119 */         double f = size / bytes * 100.0D;
/*     */         
/* 121 */         Atualizacao.percent = (int)f;
/*     */       }
/* 123 */       fos.close();
/* 124 */       in.close();
/*     */     } catch (IOException e) {
/* 126 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\M.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */