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
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
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
/*     */ public class Login extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JLabel rotData;
/*     */   private JLabel rotCod;
/*     */   private JLabel rotSen;
/*     */   private JTextField texCod;
/*     */   private JPasswordField texSen;
/*     */   private JButton botLog;
/*     */   JLabel rotNome;
/*     */   
/*     */   public Login()
/*     */   {
/*  45 */     janela();
/*  46 */     criarRotulos();
/*  47 */     criarTextos();
/*  48 */     criarBotoes();
/*     */     
/*  50 */     adiComps();
/*  51 */     proParal();
/*     */     
/*  53 */     this.rotNome = new JLabel("");
/*  54 */     this.rotNome.setFont(new Font("Arial", 0, 14));
/*  55 */     this.rotNome.setBounds(10, 200, 181, 14);
/*  56 */     getContentPane().add(this.rotNome);
/*     */     
/*  58 */     JLabel label = new JLabel("<html><u>Esqueceu sua senha?");
/*  59 */     label.setFont(new Font("Serif", 0, 16));
/*  60 */     label.setBounds(10, 242, 136, 24);
/*  61 */     label.setCursor(Cursor.getPredefinedCursor(12));
/*  62 */     label.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
/*     */       public void mousePressed(MouseEvent arg0) {}
/*     */       public void mouseExited(MouseEvent arg0) {}
/*  65 */       public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { Ig.sucesso("Por favor entre em contato com o RH.");
/*     */       }
/*  67 */     });
/*  68 */     getContentPane().add(label);
/*     */     
/*  70 */     JLabel label_1 = new JLabel("<html><u>Solicitar alteração RH.");
/*  71 */     label_1.setFont(new Font("Serif", 0, 16));
/*  72 */     label_1.setBounds(10, 291, 166, 18);
/*  73 */     label_1.setCursor(Cursor.getPredefinedCursor(12));
/*  74 */     label_1.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
/*     */       public void mousePressed(MouseEvent arg0) {}
/*     */       public void mouseExited(MouseEvent arg0) {}
/*  77 */       public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { Login.this.login();
/*     */         
/*  79 */         if (Login.this.ok) {
/*  80 */           Login.this.dispose();
/*  81 */           new Solicitacao();
/*     */         }
/*     */         else {
/*  84 */           Ig.advertencia("Nome ou senha incorretos! Tente novamente.");
/*     */         }
/*     */       }
/*  87 */     });
/*  88 */     getContentPane().add(label_1);
/*     */     
/*  90 */     JLabel label_2 = new JLabel("<html><u>Visualizar histórico ponto.");
/*  91 */     label_2.setFont(new Font("Serif", 0, 16));
/*  92 */     label_2.setBounds(10, 349, 181, 18);
/*  93 */     label_2.setCursor(Cursor.getPredefinedCursor(12));
/*  94 */     label_2.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
/*     */       public void mousePressed(MouseEvent arg0) {}
/*     */       public void mouseExited(MouseEvent arg0) {}
/*  97 */       public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { String res = Rede.httpPost("LOG" + Login.this.texCod.getText() + "\001" + new String(Login.this.texSen.getPassword()));
/*     */         
/*  99 */         if (res.substring(0, 3).equals("LOK")) {
/* 100 */           Login.this.dispose();
/* 101 */           new Visual();
/*     */         }
/*     */         else {
/* 104 */           Ig.advertencia("Nome ou senha incorretos! Tente novamente.");
/*     */         }
/*     */       }
/* 107 */     });
/* 108 */     getContentPane().add(label_2);
/*     */     
/*     */ 
/* 111 */     this.label_4 = new JLabel("<html><u>Vis. solicitações RH");
/* 112 */     this.label_4.setFont(new Font("Serif", 0, 16));
/* 113 */     this.label_4.setBounds(10, 320, 166, 18);
/* 114 */     this.label_4.setCursor(Cursor.getPredefinedCursor(12));
/* 115 */     getContentPane().add(this.label_4);
/* 116 */     this.label_4.addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
/*     */       public void mousePressed(MouseEvent arg0) {}
/*     */       public void mouseExited(MouseEvent arg0) {}
/* 119 */       public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { Login.this.login();
/*     */         
/* 121 */         if (!Login.this.ok) return;
/* 122 */         new Solicitacoes(br.com.tucanobrasil.M.ID);
/*     */         
/* 124 */         Login.this.dispose();
/*     */       }
/*     */       
/*     */ 
/* 128 */     });
/* 129 */     this.label_3 = new JLabel("Gestão de Recursos Empresariais (Registro Ponto):");
/* 130 */     this.label_3.setFont(new Font("Serif", 0, 20));
/* 131 */     this.label_3.setBounds(56, 63, 415, 24);
/* 132 */     getContentPane().add(this.label_3);
/*     */     
/* 134 */     setVisible(true);
/*     */   }
/*     */   
/*     */   private void janela() {
/* 138 */     setTitle("TucanoPonto - Registro Ponto");
/* 139 */     setSize(526, 442);
/* 140 */     getContentPane().setLayout(null);
/* 141 */     setLocationRelativeTo(null);
/* 142 */     setResizable(false);
/* 143 */     setDefaultCloseOperation(3);
/* 144 */     setIconImage(new ImageIcon("32x32.png").getImage());
/* 145 */     getContentPane().setBackground(new Color(255, 255, 181));
/*     */   }
/*     */   
/*     */   private void criarRotulos() {
/* 149 */     this.rotData = new JLabel("Data: " + Tempo.formatoBrasil());
/* 150 */     this.rotData.setBounds(319, 307, 201, 14);
/* 151 */     this.rotData.setFont(new Font("Arial", 0, 16));
/*     */     
/* 153 */     this.rotCod = new JLabel("Código:");
/* 154 */     this.rotCod.setBounds(228, 104, 58, 18);
/* 155 */     this.rotCod.setFont(new Font("Arial", 0, 17));
/*     */     
/* 157 */     this.rotSen = new JLabel("Senha:");
/* 158 */     this.rotSen.setBounds(228, 175, 58, 14);
/* 159 */     this.rotSen.setFont(new Font("Arial", 0, 17));
/*     */     
/* 161 */     JLabel ima = new JLabel(new ImageIcon("tucsma.png"));
/* 162 */     ima.setBounds(167, 11, 181, 41);
/* 163 */     ima.setBackground(Color.BLACK);
/* 164 */     ima.setOpaque(true);
/*     */     
/* 166 */     getContentPane().add(ima);
/*     */   }
/*     */   
/*     */   private void criarTextos() {
/* 170 */     this.texCod = new JTextField();
/* 171 */     this.texCod.setBounds(-3, 133, 523, 31);
/* 172 */     this.texCod.setHorizontalAlignment(0);
/* 173 */     this.texCod.setFont(new Font("Arial", 0, 16));
/* 174 */     this.texCod.addKeyListener(new KeyListener() {
/*     */       public void keyTyped(KeyEvent arg0) {}
/*     */       
/*     */       public void keyReleased(KeyEvent arg0) {}
/*     */       
/*     */       public void keyPressed(KeyEvent arg0) {
/* 180 */         if (arg0.getKeyCode() == 10) {
/* 181 */           Login.this.texSen.requestFocus();
/*     */         }
/*     */         
/*     */       }
/* 185 */     });
/* 186 */     this.texCod.getDocument().addDocumentListener(new DocumentListener()
/*     */     {
/*     */       public void changedUpdate(DocumentEvent arg0)
/*     */       {
/* 190 */         Login.this.regrasCod();
/*     */       }
/*     */       
/*     */       public void insertUpdate(DocumentEvent arg0)
/*     */       {
/* 195 */         Login.this.regrasCod();
/*     */       }
/*     */       
/*     */       public void removeUpdate(DocumentEvent arg0)
/*     */       {
/* 200 */         Login.this.regrasCod();
/*     */       }
/*     */       
/*     */ 
/* 204 */     });
/* 205 */     this.texCod.addFocusListener(new FocusListener() {
/*     */       public void focusLost(FocusEvent arg0) {
/* 207 */         String nome = Rede.httpPost("NPC" + Login.this.texCod.getText());
/*     */         
/* 209 */         if (nome.length() > 0) {
/* 210 */           Login.this.rotNome.setText(nome.split(" ", -1)[0]);
/*     */         }
/*     */         else {
/* 213 */           Login.this.rotNome.setText("");
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */       public void focusGained(FocusEvent arg0) {}
/* 219 */     });
/* 220 */     this.texSen = new JPasswordField();
/* 221 */     this.texSen.setBounds(-14, 200, 534, 31);
/* 222 */     this.texSen.setFont(new Font("Arial", 0, 16));
/* 223 */     this.texSen.setHorizontalAlignment(0);
/* 224 */     this.texSen.getDocument().addDocumentListener(new DocumentListener()
/*     */     {
/*     */       public void changedUpdate(DocumentEvent arg0)
/*     */       {
/* 228 */         Login.this.regrasSen();
/*     */       }
/*     */       
/*     */       public void insertUpdate(DocumentEvent arg0)
/*     */       {
/* 233 */         Login.this.regrasSen();
/*     */       }
/*     */       
/*     */       public void removeUpdate(DocumentEvent arg0)
/*     */       {
/* 238 */         Login.this.regrasSen();
/*     */       }
/*     */       
/*     */ 
/* 242 */     });
/* 243 */     this.texSen.addKeyListener(new KeyListener()
/*     */     {
/*     */       public void keyTyped(KeyEvent arg0) {}
/*     */       
/*     */ 
/*     */       public void keyReleased(KeyEvent arg0)
/*     */       {
/* 250 */         if (Login.this.ok) {
/* 251 */           Login.this.dispose();new Registro();
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       public void keyPressed(KeyEvent arg0) {}
/* 259 */     });
/* 260 */     this.texSen.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/* 262 */         Login.this.login();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/* 267 */   boolean ok = false;
/*     */   private JLabel label_3;
/*     */   private JLabel label_4;
/*     */   
/* 271 */   private void login() { String res = Rede.httpPost("LOG" + this.texCod.getText() + "\001" + new String(this.texSen.getPassword()));
/*     */     
/* 273 */     if (res.substring(0, 3).equals("LOK")) {
/* 274 */       br.com.tucanobrasil.M.ID = res.substring(3);
/* 275 */       this.ok = true;
/*     */     }
/*     */     else {
/* 278 */       Ig.advertencia("Nome ou senha incorretos.");
/*     */     }
/*     */   }
/*     */   
/*     */   private void criarBotoes() {
/* 283 */     this.botLog = new JButton("<html>&nbsp; Registrar Ponto &nbsp;");
/* 284 */     this.botLog.setBounds(252, 242, 258, 43);
/* 285 */     this.botLog.setIcon(Ig.icone("clock.png"));
/* 286 */     this.botLog.setHorizontalTextPosition(2);
/* 287 */     this.botLog.setHorizontalAlignment(0);
/*     */     
/* 289 */     this.botLog.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/* 291 */         Login.this.login();
/*     */         
/* 293 */         if (Login.this.ok) {
/* 294 */           Login.this.dispose();new Registro();
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   private void adiComps()
/*     */   {
/* 302 */     getContentPane().add(this.rotData);
/* 303 */     getContentPane().add(this.rotCod);
/* 304 */     getContentPane().add(this.rotSen);
/*     */     
/* 306 */     getContentPane().add(this.texCod);
/* 307 */     getContentPane().add(this.texSen);
/*     */     
/* 309 */     getContentPane().add(this.botLog);
/*     */   }
/*     */   
/*     */   private void proParal() {
/* 313 */     new Timer().schedule(new TimerTask() {
/*     */       public void run() {
/* 315 */         Login.this.rotData.setText("Data: " + Tempo.formatoBrasil());
/*     */       }
/* 317 */     }, 1000L, 1000L);
/*     */   }
/*     */   
/*     */   private void regrasCod() {
/* 321 */     SwingUtilities.invokeLater(new Runnable() {
/*     */       public void run() {
/* 323 */         if (!LEsc.eSoNumero(Login.this.texCod.getText())) {
/* 324 */           Login.this.texCod.setText("");
/* 325 */           return;
/*     */         }
/*     */         
/* 328 */         if (Login.this.texCod.getText().length() >= 2) {
/* 329 */           Login.this.texSen.requestFocus();
/* 330 */           return;
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   private void regrasSen() {
/* 337 */     SwingUtilities.invokeLater(new Runnable() {
/*     */       public void run() {
/* 339 */         String pass = new String(Login.this.texSen.getPassword());
/*     */         
/* 341 */         if (!LEsc.eSoNumero(pass)) {
/* 342 */           Login.this.texSen.setText("");
/* 343 */           return;
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\ig\Login.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */