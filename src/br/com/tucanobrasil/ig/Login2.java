/*     */ package br.com.tucanobrasil.ig;
/*     */ 
/*     */ import br.com.tucanobrasil.sis.rede.Rede;
/*     */ import br.com.tucanobrasil.sis.util.Ig;
/*     */ import br.com.tucanobrasil.sis.util.LEsc;
/*     */ import br.com.tucanobrasil.sis.util.Tempo;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.event.DocumentEvent;
/*     */ import javax.swing.event.DocumentListener;
/*     */ import javax.swing.text.Document;
/*     */ 
/*     */ public class Login2 extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JLabel rotData;
/*     */   private JLabel rotCod;
/*     */   private JLabel rotSen;
/*     */   private JTextField texCod;
/*     */   private JPasswordField texSen;
/*     */   private JButton botLog;
/*     */   private JLabel label;
/*     */   private JLabel label_2;
/*     */   
/*     */   public Login2()
/*     */   {
/*  44 */     janela();
/*  45 */     criarRotulos();
/*  46 */     criarTextos();
/*  47 */     criarBotoes();
/*     */     
/*  49 */     adiComps();
/*  50 */     proParal();
/*     */     
/*     */ 
/*     */ 
/*  54 */     JLabel label_1 = new JLabel("Registro Ponto (" + br.com.tucanobrasil.M.STRVER + "):");
/*  55 */     label_1.setFont(new Font("Serif", 1, 21));
/*  56 */     label_1.setBounds(201, 22, 203, 24);
/*  57 */     getContentPane().add(label_1);
/*     */     
/*  59 */     this.label_2 = new JLabel("<html><u>Esqueceu sua senha?");
/*  60 */     this.label_2.setFont(new Font("Serif", 0, 16));
/*  61 */     this.label_2.setBounds(10, 146, 136, 24);
/*  62 */     this.label_2.setCursor(Cursor.getPredefinedCursor(12));
/*  63 */     this.label_2.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
/*     */       public void mousePressed(MouseEvent arg0) {}
/*     */       public void mouseExited(MouseEvent arg0) {}
/*  66 */       public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { Ig.sucesso("Por favor entre em contato com o RH.");
/*     */       }
/*  68 */     });
/*  69 */     getContentPane().add(this.label_2);
/*     */     
/*  71 */     JLabel label_3 = new JLabel("<html><u>Solicitar alteração RH.");
/*  72 */     label_3.setFont(new Font("Serif", 0, 16));
/*  73 */     label_3.setBounds(10, 179, 166, 18);
/*  74 */     label_3.setCursor(Cursor.getPredefinedCursor(12));
/*  75 */     label_3.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
/*     */       public void mousePressed(MouseEvent arg0) {}
/*     */       public void mouseExited(MouseEvent arg0) {}
/*  78 */       public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { String res = Rede.httpPost("LOG" + Login2.this.texCod.getText() + "\001" + new String(Login2.this.texSen.getPassword()));
/*     */         
/*  80 */         if (res.substring(0, 3).equals("LOK")) {
/*  81 */           Login2.this.dispose();
/*  82 */           new Solicitacao();
/*     */         }
/*     */         else {
/*  85 */           Ig.advertencia("Nome ou senha incorretos! Tente novamente." + res);
/*     */         }
/*     */         
/*     */       }
/*  89 */     });
/*  90 */     getContentPane().add(label_3);
/*     */     
/*  92 */     this.label_4 = new JLabel("<html><u>Visualizar histórico ponto.");
/*  93 */     this.label_4.setFont(new Font("Serif", 0, 16));
/*  94 */     this.label_4.setBounds(10, 208, 181, 18);
/*  95 */     this.label_4.setCursor(Cursor.getPredefinedCursor(12));
/*  96 */     this.label_4.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
/*     */       public void mousePressed(MouseEvent arg0) {}
/*     */       public void mouseExited(MouseEvent arg0) {}
/*  99 */       public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { String res = Rede.httpPost("LOG" + Login2.this.texCod.getText() + "\001" + new String(Login2.this.texSen.getPassword()));
/*     */         
/* 101 */         if (res.substring(0, 3).equals("LOK")) {
/* 102 */           Login2.this.dispose();
/* 103 */           new Visual();
/*     */         }
/*     */         else {
/* 106 */           Ig.advertencia("Nome ou senha incorretos! Tente novamente." + res);
/*     */         }
/*     */         
/*     */       }
/* 110 */     });
/* 111 */     getContentPane().add(this.label_4);
/*     */     
/* 113 */     setVisible(true);
/*     */   }
/*     */   
/*     */   private void janela() {
/* 117 */     setTitle("Tucano Ponto - Registro");
/* 118 */     setSize(420, 265);
/* 119 */     getContentPane().setLayout(null);
/* 120 */     setLocationRelativeTo(null);
/* 121 */     setResizable(false);
/* 122 */     setDefaultCloseOperation(3);
/* 123 */     setIconImage(new ImageIcon("icosma.png").getImage());
/* 124 */     getContentPane().setBackground(new Color(255, 255, 181));
/*     */   }
/*     */   
/*     */   private void criarRotulos() {
/* 128 */     this.rotData = new JLabel("Data: " + Tempo.formatoBrasil());
/* 129 */     this.rotData.setBounds(10, 63, 357, 14);
/* 130 */     this.rotData.setFont(new Font("Arial", 0, 14));
/*     */     
/* 132 */     this.rotCod = new JLabel("CÃ³digo:");
/* 133 */     this.rotCod.setBounds(10, 91, 49, 18);
/* 134 */     this.rotCod.setFont(new Font("Arial", 0, 14));
/*     */     
/* 136 */     this.rotSen = new JLabel("Senha:");
/* 137 */     this.rotSen.setBounds(10, 121, 49, 14);
/* 138 */     this.rotSen.setFont(new Font("Arial", 0, 14));
/*     */   }
/*     */   
/*     */   private void criarTextos() {
/* 142 */     this.texCod = new JTextField();
/* 143 */     this.texCod.setBounds(69, 88, 335, 23);
/* 144 */     this.texCod.setFont(new Font("Arial", 0, 14));
/* 145 */     this.texCod.getDocument().addDocumentListener(new DocumentListener()
/*     */     {
/*     */       public void changedUpdate(DocumentEvent arg0) {
/* 148 */         Login2.this.regrasCod();
/*     */       }
/*     */       
/*     */       public void insertUpdate(DocumentEvent arg0)
/*     */       {
/* 153 */         Login2.this.regrasCod();
/*     */       }
/*     */       
/*     */       public void removeUpdate(DocumentEvent arg0)
/*     */       {
/* 158 */         Login2.this.regrasCod();
/*     */       }
/*     */       
/* 161 */     });
/* 162 */     this.texCod.addKeyListener(new KeyListener()
/*     */     {
/*     */       public void keyTyped(KeyEvent arg0) {}
/*     */       
/*     */ 
/*     */       public void keyReleased(KeyEvent arg0) {}
/*     */       
/*     */       public void keyPressed(KeyEvent arg0)
/*     */       {
/* 171 */         arg0.getKeyCode();
/*     */ 
/*     */       }
/*     */       
/*     */ 
/* 176 */     });
/* 177 */     this.texSen = new JPasswordField();
/* 178 */     this.texSen.setBounds(69, 120, 335, 23);
/* 179 */     this.texSen.setFont(new Font("Arial", 0, 14));
/*     */     
/* 181 */     this.texSen.addKeyListener(new KeyListener()
/*     */     {
/*     */       public void keyTyped(KeyEvent arg0) {}
/*     */       
/*     */ 
/*     */       public void keyReleased(KeyEvent arg0)
/*     */       {
/* 188 */         if (Login2.this.ok) {
/* 189 */           Login2.this.dispose();new Registro();
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       public void keyPressed(KeyEvent arg0) {}
/* 197 */     });
/* 198 */     this.texSen.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/* 200 */         Login2.this.login();
/*     */       }
/*     */       
/* 203 */     });
/* 204 */     this.texSen.getDocument().addDocumentListener(new DocumentListener()
/*     */     {
/*     */       public void changedUpdate(DocumentEvent arg0) {
/* 207 */         Login2.this.regrasSen();
/*     */       }
/*     */       
/*     */       public void insertUpdate(DocumentEvent arg0)
/*     */       {
/* 212 */         Login2.this.regrasSen();
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 217 */       public void removeUpdate(DocumentEvent arg0) { Login2.this.regrasSen(); }
/*     */     });
/*     */   }
/*     */   
/* 221 */   boolean ok = false;
/*     */   private JLabel label_4;
/*     */   
/*     */   private void login() {
/* 225 */     String res = Rede.httpPost("LOG" + this.texCod.getText() + "\001" + new String(this.texSen.getPassword()));
/*     */     
/* 227 */     if (res.substring(0, 3).equals("LOK")) {
/* 228 */       this.ok = true;
/*     */     }
/*     */     else {
/* 231 */       Ig.advertencia("Nome ou senha incorretos." + res);
/*     */     }
/*     */   }
/*     */   
/*     */   private void criarBotoes() {
/* 236 */     this.botLog = new JButton("Registrar Ponto");
/* 237 */     this.botLog.setBounds(186, 154, 218, 43);
/* 238 */     this.botLog.setIcon(Ig.icone("clock.png"));
/* 239 */     this.botLog.setHorizontalTextPosition(2);
/* 240 */     this.botLog.setHorizontalAlignment(0);
/*     */     
/* 242 */     this.botLog.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/* 244 */         Login2.this.login();
/*     */         
/* 246 */         if (Login2.this.ok) {
/* 247 */           Login2.this.dispose();new Registro();
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   private void adiComps()
/*     */   {
/* 255 */     getContentPane().add(this.rotData);
/* 256 */     getContentPane().add(this.rotCod);
/* 257 */     getContentPane().add(this.rotSen);
/*     */     
/* 259 */     getContentPane().add(this.texCod);
/* 260 */     getContentPane().add(this.texSen);
/*     */     
/* 262 */     getContentPane().add(this.botLog);
/*     */     
/* 264 */     this.label = new JLabel(new ImageIcon("tucsma.png"));
/* 265 */     this.label.setOpaque(true);
/* 266 */     this.label.setBackground(Color.BLACK);
/* 267 */     this.label.setBounds(10, 11, 181, 41);
/* 268 */     getContentPane().add(this.label);
/*     */   }
/*     */   
/*     */   private void proParal() {
/* 272 */     new Timer().schedule(new TimerTask() {
/*     */       public void run() {
/* 274 */         Login2.this.rotData.setText("Data: " + Tempo.formatoBrasil());
/*     */       }
/* 276 */     }, 1000L, 1000L);
/*     */   }
/*     */   
/*     */   private void regrasCod() {
/* 280 */     SwingUtilities.invokeLater(new Runnable() {
/*     */       public void run() {
/* 282 */         if (!LEsc.eSoNumero(Login2.this.texCod.getText())) {
/* 283 */           Login2.this.texCod.setText("");
/* 284 */           return;
/*     */         }
/*     */         
/* 287 */         if (Login2.this.texCod.getText().length() >= 2) {
/* 288 */           Login2.this.texSen.requestFocus();
/* 289 */           return;
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   private void regrasSen() {
/* 296 */     SwingUtilities.invokeLater(new Runnable() {
/*     */       public void run() {
/* 298 */         String pass = new String(Login2.this.texSen.getPassword());
/*     */         
/* 300 */         if (!LEsc.eSoNumero(pass)) {
/* 301 */           Login2.this.texSen.setText("");
/* 302 */           return;
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\ig\Login2.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */