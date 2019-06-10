/*     */ package br.com.tucanobrasil.ig;
/*     */ 
/*     */ import br.com.tucanobrasil.sis.lgraf.LRealArea;
/*     */ //import java.awt.Container;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class Solicitacao extends javax.swing.JFrame implements java.awt.KeyEventDispatcher
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JLabel rotAgr;
/*     */   private JLabel rotDesc;
/*     */   private JLabel rotSen;
/*     */   private br.com.tucanobrasil.sis.lgraf.LData texData;
/*     */   private LRealArea texDesc;
/*     */   private LRealArea texOut;
/*     */   private javax.swing.JButton botLog;
/*     */   private JLabel label;
/*     */   private JLabel rotSol;
/*     */   private JRadioButton radOp1;
/*     */   private JRadioButton radOp2;
/*     */   private JRadioButton radOp3;
/*     */   private JLabel label_5;
/*     */   private JTextField texEM;
/*     */   private JTextField texSM;
/*     */   private JTextField texET;
/*     */   private JTextField texST;
/*     */   private javax.swing.ButtonGroup bg;
/*     */   javax.swing.JScrollPane barOut;
/*     */   
/*     */   public void envSol()
/*     */   {
/*  34 */     if (!this.texData.valido) {
/*  35 */       br.com.tucanobrasil.sis.util.Ig.advertencia("Insira a data em um formato correto.");
/*  36 */       return;
/*     */     }
/*     */     
/*  39 */     int tipo = 0;
/*  40 */     String corrigir = "";
/*  41 */     String outra = "";
/*  42 */     if (this.st[1]) {
/*  43 */       tipo = 1;
/*  44 */       corrigir = this.texEM.getText() + ";" + this.texSM.getText() + ";" + this.texET.getText() + ";" + this.texST.getText();
/*     */     }
/*  46 */     else if (this.st[2]) {
/*  47 */       tipo = 2;
/*  48 */       outra = this.texOut.getText();
/*     */     }
/*     */     
/*  51 */     String pac = "SCP" + this.texData.getText() + "\001" + this.texDesc.getText() + "\001" + tipo + "\001" + corrigir + "\001" + outra;
/*  52 */     if (br.com.tucanobrasil.sis.rede.Rede.httpPost(pac).equals("PCS")) {
/*  53 */       dispose();
/*  54 */       br.com.tucanobrasil.sis.util.Ig.sucesso("Solicitação enviada com sucesso! Aguarde a correção pelo RH.");
/*     */       
/*  56 */       new Solicitacoes(br.com.tucanobrasil.M.ID);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Solicitacao()
/*     */   {
/*  73 */     janela();
/*  74 */     criarRotulos();
/*  75 */     criarTextos();
/*  76 */     criarBotoes();
/*  77 */     geral();
/*     */     
/*  79 */     adiComps();
/*  80 */     proParal();
/*     */     
/*  82 */     br.com.tucanobrasil.M.manager.addKeyEventDispatcher(this);
/*     */     
/*  84 */     setVisible(true);
/*     */   }
/*     */   
/*     */   private void janela() {
/*  88 */     setTitle("Tucano Ponto - Registro");
/*  89 */     setSize(624, 448);
/*  90 */     getContentPane().setLayout(null);
/*  91 */     setLocationRelativeTo(null);
/*  92 */     setResizable(false);
/*  93 */     setDefaultCloseOperation(3);
/*  94 */     setIconImage(new javax.swing.ImageIcon("32x32.png").getImage());
/*  95 */     getContentPane().setBackground(new java.awt.Color(255, 255, 181));
/*     */   }
/*     */   
/*     */   private void criarRotulos() {
/*  99 */     this.rotAgr = new JLabel("Data: " + br.com.tucanobrasil.sis.util.Tempo.formatoBrasil());
/* 100 */     this.rotAgr.setBounds(433, 386, 170, 14);
/* 101 */     this.rotAgr.setFont(new java.awt.Font("Arial", 0, 14));
/*     */     
/* 103 */     this.rotDesc = new JLabel("Data ocorrido:");
/* 104 */     this.rotDesc.setBounds(10, 101, 99, 18);
/* 105 */     this.rotDesc.setFont(new java.awt.Font("Arial", 0, 14));
/*     */     
/* 107 */     this.rotSen = new JLabel("Descrição da ocorrência:");
/* 108 */     this.rotSen.setBounds(10, 131, 167, 18);
/* 109 */     this.rotSen.setFont(new java.awt.Font("Arial", 0, 14));
/*     */   }
/*     */   
/*     */   private void criarTextos() {
/* 113 */     this.texData = new br.com.tucanobrasil.sis.lgraf.LData();
/* 114 */     this.texData.setBounds(110, 98, 493, 23);
/* 115 */     this.texData.setFont(new java.awt.Font("Arial", 0, 14));
/* 116 */     this.texData.addKeyListener(new java.awt.event.KeyListener() { public void keyTyped(java.awt.event.KeyEvent arg0) {}
/*     */       
/*     */       public void keyReleased(java.awt.event.KeyEvent arg0) {}
/* 119 */       public void keyPressed(java.awt.event.KeyEvent arg0) { if (arg0.getKeyCode() == 10) {
/* 120 */           Solicitacao.this.texDesc.requestFocus();
/* 121 */           return;
/*     */         }
/*     */         
/*     */       }
/* 125 */     });
/* 126 */     this.texDesc = new LRealArea();
/* 127 */     this.texDesc.setFont(new java.awt.Font("Arial", 0, 14));
/* 128 */     this.texDesc.addKeyListener(new java.awt.event.KeyListener() { public void keyTyped(java.awt.event.KeyEvent arg0) {}
/*     */       
/*     */       public void keyReleased(java.awt.event.KeyEvent arg0) {}
/* 131 */       public void keyPressed(java.awt.event.KeyEvent arg0) { if (arg0.getKeyCode() == 10) {}
/*     */ 
/*     */       }
/*     */       
/*     */ 
/* 136 */     });
/* 137 */     javax.swing.JScrollPane barDes = new javax.swing.JScrollPane(this.texDesc);
/* 138 */     barDes.setBounds(177, 126, 426, 51);
/*     */     
/* 140 */     getContentPane().add(barDes);
/*     */     
/* 142 */     this.texEM = new JTextField();
/* 143 */     this.texEM.setFont(new java.awt.Font("Arial", 0, 14));
/* 144 */     this.texEM.setBounds(202, 236, 68, 23);
/* 145 */     getContentPane().add(this.texEM);
/*     */     
/* 147 */     this.texSM = new JTextField();
/* 148 */     this.texSM.setFont(new java.awt.Font("Arial", 0, 14));
/* 149 */     this.texSM.setBounds(315, 235, 68, 23);
/* 150 */     getContentPane().add(this.texSM);
/*     */     
/* 152 */     this.texET = new JTextField();
/* 153 */     this.texET.setFont(new java.awt.Font("Arial", 0, 14));
/* 154 */     this.texET.setBounds(425, 233, 68, 23);
/* 155 */     getContentPane().add(this.texET);
/*     */     
/* 157 */     this.texST = new JTextField();
/* 158 */     this.texST.setFont(new java.awt.Font("Arial", 0, 14));
/* 159 */     this.texST.setBounds(535, 233, 68, 23);
/* 160 */     getContentPane().add(this.texST);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void criarBotoes()
/*     */   {
/* 177 */     this.botLog = new javax.swing.JButton("Solicitar Correção");
/* 178 */     this.botLog.setBounds(408, 332, 195, 43);
/*     */     
/* 180 */     this.botLog.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(java.awt.event.ActionEvent arg0) {
/* 182 */         Solicitacao.this.envSol();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   private void geral()
/*     */   {
/* 189 */     JLabel label_1 = new JLabel("Ocorrências / Solicitação Ponto:");
/* 190 */     label_1.setFont(new java.awt.Font("Serif", 1, 18));
/* 191 */     label_1.setBounds(180, 63, 250, 24);
/* 192 */     getContentPane().add(label_1);
/*     */     
/* 194 */     this.rotSol = new JLabel("Solicitação:");
/* 195 */     this.rotSol.setFont(new java.awt.Font("Arial", 0, 14));
/* 196 */     this.rotSol.setBounds(10, 186, 83, 18);
/* 197 */     getContentPane().add(this.rotSol);
/*     */     
/* 199 */     this.radOp1 = new JRadioButton("Apenas justificativa. Não desejo solicitar nada.");
/* 200 */     this.radOp1.setSelected(true);
/* 201 */     this.radOp1.setFont(new java.awt.Font("Arial", 0, 14));
/* 202 */     this.radOp1.setBounds(10, 210, 317, 18);
/* 203 */     getContentPane().add(this.radOp1);
/*     */     
/* 205 */     this.radOp2 = new JRadioButton("Corrigir ponto para:");
/* 206 */     this.radOp2.setSelected(true);
/* 207 */     this.radOp2.setFont(new java.awt.Font("Arial", 0, 14));
/* 208 */     this.radOp2.setBounds(10, 238, 155, 18);
/* 209 */     getContentPane().add(this.radOp2);
/*     */     
/* 211 */     this.radOp3 = new JRadioButton("Outra:");
/* 212 */     this.radOp3.setSelected(true);
/* 213 */     this.radOp3.setFont(new java.awt.Font("Arial", 0, 14));
/* 214 */     this.radOp3.setBounds(10, 270, 68, 18);
/* 215 */     getContentPane().add(this.radOp3);
/*     */     
/* 217 */     this.bg = new javax.swing.ButtonGroup();
/* 218 */     this.bg.add(this.radOp1);
/* 219 */     this.bg.add(this.radOp2);
/* 220 */     this.bg.add(this.radOp3);
/*     */     
/* 222 */     JLabel label_3 = new JLabel("EM:");
/* 223 */     label_3.setFont(new java.awt.Font("Arial", 0, 14));
/* 224 */     label_3.setBounds(170, 238, 29, 18);
/* 225 */     getContentPane().add(label_3);
/*     */     
/* 227 */     JLabel label_4 = new JLabel("SM:");
/* 228 */     label_4.setFont(new java.awt.Font("Arial", 0, 14));
/* 229 */     label_4.setBounds(283, 237, 29, 18);
/* 230 */     getContentPane().add(label_4);
/*     */     
/*     */ 
/* 233 */     this.label_5 = new JLabel("ET:");
/* 234 */     this.label_5.setFont(new java.awt.Font("Arial", 0, 14));
/* 235 */     this.label_5.setBounds(393, 235, 29, 18);
/* 236 */     getContentPane().add(this.label_5);
/*     */     
/* 238 */     this.texOut = new LRealArea();
/* 239 */     this.texOut.setFont(new java.awt.Font("Arial", 0, 14));
/* 240 */     this.texOut.setLayout(null);
/*     */     
/* 242 */     this.barOut = new javax.swing.JScrollPane(this.texOut);
/* 243 */     this.barOut.setBounds(92, 270, 511, 51);
/*     */     
/* 245 */     getContentPane().add(this.barOut);
/*     */     
/* 247 */     JLabel label_2 = new JLabel("ST:");
/* 248 */     label_2.setFont(new java.awt.Font("Arial", 0, 14));
/* 249 */     label_2.setBounds(503, 235, 29, 18);
/* 250 */     getContentPane().add(label_2);
/*     */   }
/*     */   
/*     */   private void adiComps() {
/* 254 */     getContentPane().add(this.rotAgr);
/* 255 */     getContentPane().add(this.rotDesc);
/* 256 */     getContentPane().add(this.rotSen);
/*     */     
/* 258 */     getContentPane().add(this.texData);
/*     */     
/* 260 */     getContentPane().add(this.botLog);
/*     */     
/* 262 */     this.label = new JLabel(new javax.swing.ImageIcon("tucsma.png"));
/* 263 */     this.label.setOpaque(true);
/* 264 */     this.label.setBackground(java.awt.Color.BLACK);
/* 265 */     this.label.setBounds(219, 11, 181, 41);
/* 266 */     getContentPane().add(this.label);
/*     */   }
/*     */   
/* 269 */   boolean[] st = new boolean[3];
/*     */   
/* 271 */   private void proParal() { new java.util.Timer().schedule(new java.util.TimerTask() {
/*     */       public void run() {
/* 273 */         Solicitacao.this.rotAgr.setText("Data: " + br.com.tucanobrasil.sis.util.Tempo.formatoBrasil());
/*     */         
/* 275 */         if ((Solicitacao.this.st[0] != Solicitacao.this.radOp1.isSelected()) || (Solicitacao.this.st[1] != Solicitacao.this.radOp2.isSelected()) || (Solicitacao.this.st[2] != Solicitacao.this.radOp3.isSelected())) {
/* 276 */           Solicitacao.this.st[0] = Solicitacao.this.radOp1.isSelected();
/* 277 */           Solicitacao.this.st[1] = Solicitacao.this.radOp2.isSelected();
/* 278 */           Solicitacao.this.st[2] = Solicitacao.this.radOp3.isSelected();
/*     */           
/* 280 */           if (Solicitacao.this.st[0]) {
/* 281 */             Solicitacao.this.texEM.setEnabled(false);Solicitacao.this.texSM.setEnabled(false);
/* 282 */             Solicitacao.this.texET.setEnabled(false);Solicitacao.this.texST.setEnabled(false);
/*     */             
/* 284 */             Solicitacao.this.texOut.setEditable(false);Solicitacao.this.texOut.setEnabled(false);Solicitacao.this.texOut.setBackground(new java.awt.Color(240, 240, 240));
/*     */           }
/* 286 */           else if (Solicitacao.this.st[1]) {
/* 287 */             Solicitacao.this.texEM.setEnabled(true);Solicitacao.this.texSM.setEnabled(true);
/* 288 */             Solicitacao.this.texET.setEnabled(true);Solicitacao.this.texST.setEnabled(true);
/*     */             
/* 290 */             Solicitacao.this.texOut.setEditable(false);Solicitacao.this.texOut.setEnabled(false);Solicitacao.this.texOut.setBackground(new java.awt.Color(240, 240, 240));
/*     */           }
/*     */           else {
/* 293 */             Solicitacao.this.texEM.setEnabled(false);Solicitacao.this.texSM.setEnabled(false);
/* 294 */             Solicitacao.this.texET.setEnabled(false);Solicitacao.this.texST.setEnabled(false);
/*     */             
/* 296 */             Solicitacao.this.texOut.setEditable(true);Solicitacao.this.texOut.setEnabled(true);Solicitacao.this.texOut.setBackground(java.awt.Color.WHITE);
/*     */           }
/*     */         }
/*     */       }
/* 300 */     }, 33L, 33L);
/*     */   }
/*     */   
/*     */   public boolean dispatchKeyEvent(java.awt.event.KeyEvent e)
/*     */   {
/* 305 */     if ((e.getID() == 402) && 
/* 306 */       (e.getKeyCode() == 27)) {
/* 307 */       br.com.tucanobrasil.M.manager.removeKeyEventDispatcher(this);
/* 308 */       dispose();
/* 309 */       new Visual();
/*     */     }
/*     */     
/* 312 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\ig\Solicitacao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */