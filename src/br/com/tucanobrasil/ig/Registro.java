/*     */ package br.com.tucanobrasil.ig;
/*     */ 
/*     */ import br.com.tucanobrasil.sis.rede.Rede;
/*     */ import br.com.tucanobrasil.sis.util.Ig;
/*     */ import br.com.tucanobrasil.sis.util.Metodos;
/*     */ import br.com.tucanobrasil.sis.util.Tempo;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Registro
/*     */   extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JLabel rotCod;
/*     */   private JTextField cbTipo;
/*     */   JButton button;
/*     */   
/*     */   public Registro()
/*     */   {
/*  34 */     janela();
/*  35 */     criarRotulos();
/*  36 */     criarTextos();
/*     */     
/*  38 */     adiComps();
/*     */     
/*  40 */     int hora = Integer.valueOf(Tempo.hora()).intValue();
/*     */     
/*  42 */     String set = null;
/*  43 */     if (hora < 12) {
/*  44 */       if (!Metodos.regEx(0)) {
/*  45 */         set = "EM";
/*     */       }
/*     */       else {
/*  48 */         set = "SM";
/*     */       }
/*     */     }
/*     */     
/*  52 */     if (hora == 12) {
/*  53 */       set = "ET";
/*  54 */       if (!Metodos.regEx(1)) {
/*  55 */         set = "SM";
/*     */       }
/*     */     }
/*     */     
/*  59 */     if (hora >= 13) {
/*  60 */       set = "ET";
/*     */       
/*  62 */       if (Metodos.regEx(2)) {
/*  63 */         set = "ST";
/*     */       }
/*     */     }
/*     */     
/*  67 */     this.cbTipo.setText(set);
/*     */     
/*  69 */     this.cbTipo.addKeyListener(new KeyListener()
/*     */     {
/*     */       public void keyTyped(KeyEvent arg0) {}
/*     */       
/*     */       public void keyReleased(KeyEvent arg0)
/*     */       {
/*  75 */         if (arg0.getKeyCode() == 10) {
/*  76 */           Registro.this.rpt();
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       public void keyPressed(KeyEvent arg0) {}
/*  84 */     });
/*  85 */     this.button = new JButton("<html>OK &nbsp; ");
/*  86 */     this.button.setHorizontalTextPosition(2);
/*  87 */     this.button.setHorizontalAlignment(0);
/*  88 */     this.button.setBounds(184, 46, 115, 40);
/*  89 */     this.button.setIcon(Ig.icone("fim.png"));
/*  90 */     getContentPane().add(this.button);
/*     */     
/*  92 */     this.button.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/*  94 */         Registro.this.rpt();
/*     */       }
/*     */       
/*  97 */     });
/*  98 */     setVisible(true);
/*  99 */     this.cbTipo.requestFocus();
/*     */   }
/*     */   
/*     */   private void rpt() {
/* 103 */     this.cbTipo.setText(this.cbTipo.getText().toUpperCase());
/* 104 */     String tipo = this.cbTipo.getText();
/*     */     
/* 106 */     int val = -1;
/* 107 */     if (tipo.equals("EM")) {
/* 108 */       val = 0;
/*     */     }
/* 110 */     if (tipo.equals("SM")) {
/* 111 */       val = 1;
/*     */     }
/* 113 */     if (tipo.equals("ET")) {
/* 114 */       val = 2;
/*     */     }
/* 116 */     if (tipo.equals("ST")) {
/* 117 */       val = 3;
/*     */     }
/*     */     
/* 120 */     if (val == -1) {
/* 121 */       Ig.advertencia("Valor desconhecido.");
/* 122 */       return;
/*     */     }
/*     */     
/* 125 */     String t = Rede.httpPost("RPT" + val);
/*     */     
/* 127 */     if ((!t.equals("JRE")) && 
/* 128 */       (this.cbTipo.getText().equals("ST"))) {
/* 129 */       ArrayList<String> infos = Rede.httpArr("DHP");
/*     */       
/* 131 */       boolean isemp = false;
/* 132 */       for (int i = 0; i < infos.size(); i++) {
/* 133 */         if (((String)infos.get(i)).equals("")) {
/* 134 */           isemp = true; break;
/*     */         }
/*     */       }
/*     */       
/* 138 */       if (!isemp) {
/* 139 */         String manha = Tempo.difHoras((String)infos.get(0), (String)infos.get(1));
/* 140 */         String tarde = Tempo.difHoras((String)infos.get(2), (String)infos.get(3));
/* 141 */         String total = Tempo.calcLisHoras(new String[] { manha, tarde });
/* 142 */         String diff = Tempo.difHoras("8:48", total);
/*     */         
/* 144 */         Rede.httpPost("FPT" + total + "\001" + diff).equals("FCS");
/*     */       }
/* 146 */       dispose();new Visual();
/* 147 */       return;
/*     */     }
/*     */     
/*     */ 
/* 151 */     if (t.equals("RES")) {
/* 152 */       dispose();new Visual();
/*     */     }
/*     */     else {
/* 155 */       Ig.advertencia("Você já fez esse registro.");
/*     */     }
/*     */   }
/*     */   
/*     */   private void janela() {
/* 160 */     setTitle("Tucano Ponto - Registro");
/* 161 */     setSize(331, 136);
/* 162 */     getContentPane().setLayout(null);
/* 163 */     setLocationRelativeTo(null);
/* 164 */     setResizable(false);
/* 165 */     setDefaultCloseOperation(3);
/* 166 */     setIconImage(new ImageIcon("32x32.png").getImage());
/* 167 */     getContentPane().setBackground(new Color(255, 255, 181));
/*     */   }
/*     */   
/*     */   private void criarRotulos() {
/* 171 */     this.rotCod = new JLabel("Você deseja registrar:");
/* 172 */     this.rotCod.setBounds(10, 14, 145, 18);
/* 173 */     this.rotCod.setFont(new Font("Arial", 0, 14));
/*     */   }
/*     */   
/*     */   private void criarTextos() {
/* 177 */     this.cbTipo = new JTextField();
/* 178 */     this.cbTipo.setBounds(160, 12, 139, 23);
/*     */   }
/*     */   
/*     */   private void adiComps() {
/* 182 */     getContentPane().add(this.rotCod);
/*     */     
/* 184 */     getContentPane().add(this.cbTipo);
/*     */   }
/*     */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\ig\Registro.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */