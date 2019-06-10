/*     */ package br.com.tucanobrasil.ig;
/*     */ 
/*     */ import br.com.tucanobrasil.sis.lgraf.LRealArea;
/*     */ import br.com.tucanobrasil.sis.rede.Rede;
/*     */ import br.com.tucanobrasil.sis.util.Arquivo;
/*     */ import br.com.tucanobrasil.sis.util.Ig;
/*     */ import br.com.tucanobrasil.sis.util.LEsc;
/*     */ import br.com.tucanobrasil.sis.util.Tempo;
/*     */ import br.com.tucanobrasil.sis.util.Util;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ 
/*     */ 
/*     */ public class Solicitacoes
/*     */   extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JMenuBar barMenu;
/*     */   private JMenu menOpt;
/*     */   private JScrollPane barPon;
/*     */   public JTable tabPon;
/*     */   private DefaultTableModel modTabPon;
/*     */   private JLabel rotSel;
/*     */   private ButtonGroup bg;
/*     */   private JRadioButton radCor;
/*     */   private JRadioButton radOut;
/*     */   private LRealArea texDesc;
/*     */   private LRealArea texOut;
/*     */   JLabel rotDesc;
/*  51 */   String idFunc = "";
/*     */   
/*  53 */   public Solicitacoes(String idFunc) { this.idFunc = idFunc;
/*     */     
/*  55 */     janela();
/*  56 */     criarTabelas();
/*  57 */     criarMenus();
/*  58 */     criarRotulos();
/*  59 */     adiComps();
/*     */     
/*  61 */     popTab();
/*     */     
/*     */ 
/*  64 */     this.texDesc.setEditable(false);this.texOut.setEditable(false);
/*  65 */     this.texEM.setEditable(false);this.texSM.setEditable(false);
/*  66 */     this.texET.setEditable(false);this.texST.setEditable(false);
/*  67 */     this.radCor.setEnabled(false);this.radOut.setEnabled(false);
/*     */     
/*  69 */     proParal();
/*  70 */     setVisible(true);
/*     */   }
/*     */   
/*     */   private void janela() {
/*  74 */     setTitle("Tucano Ponto - Solicitações Correção");
/*  75 */     setSize(929, 596);
/*  76 */     getContentPane().setLayout(null);
/*  77 */     setLocationRelativeTo(null);
/*  78 */     setResizable(false);
/*  79 */     setDefaultCloseOperation(3);
/*  80 */     setIconImage(new ImageIcon("32x32.png").getImage());
/*  81 */     getContentPane().setBackground(new Color(255, 255, 181));
/*     */   }
/*     */   
/*     */   private void criarMenus() {
/*  85 */     this.barMenu = new JMenuBar();
/*  86 */     this.barMenu.setBounds(0, 0, getWidth(), 25);
/*     */     
/*  88 */     this.menOpt = new JMenu("Opções");
/*  89 */     this.barMenu.add(this.menOpt);
/*     */     
/*  91 */     JMenuItem miImp = new JMenuItem("Imprimir");
/*  92 */     miImp.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  94 */         String conteudo = LEsc.arrPStr(Arquivo.ler("./html_soli.html"));
/*     */         
/*  96 */         int l = Solicitacoes.this.tabPon.getSelectedRow();
/*     */         
/*  98 */         String dataSoli = Tempo.amePBras(Ig.gdt(l, 5, Solicitacoes.this.tabPon).substring(0, 10));
/*  99 */         String dataOcorr = Ig.gdt(l, 2, Solicitacoes.this.tabPon).substring(0, 10);
/*     */         
/* 101 */         conteudo = conteudo.replace("#N", Ig.gdt(l, 1, Solicitacoes.this.tabPon));
/* 102 */         conteudo = conteudo.replace("#1", dataSoli);
/* 103 */         conteudo = conteudo.replace("#2", dataOcorr);
/*     */         
/* 105 */         ArrayList<String> d = Rede.httpArr("PDS" + Ig.gdt(Solicitacoes.this.tabPon.getSelectedRow(), 0, Solicitacoes.this.tabPon));
/* 106 */         conteudo = conteudo.replace("#3", Solicitacoes.formataTextA4((String)d.get(0), 73));
/*     */         
/* 108 */         if (((String)d.get(3)).equals("0")) {
/* 109 */           conteudo = conteudo.replace("#a", "x");
/*     */           
/* 111 */           conteudo = conteudo.replace("#b", " ");
/* 112 */           conteudo = conteudo.replace("#c", " ");
/* 113 */           conteudo = conteudo.replace("#4", "OUTRA</b>: ");
/*     */           
/* 115 */           conteudo = conteudo.replace("#5", "");
/* 116 */           conteudo = conteudo.replace("#7", "");
/* 117 */           conteudo = conteudo.replace("#6", "");
/* 118 */           conteudo = conteudo.replace("#8", "");
/*     */         }
/* 120 */         else if (((String)d.get(3)).equals("1")) {
/* 121 */           String[] infs = ((String)d.get(1)).split(";", -1);
/*     */           
/* 123 */           conteudo = conteudo.replace("#b", "x");
/*     */           
/* 125 */           conteudo = conteudo.replace("#5", infs[0]);
/* 126 */           conteudo = conteudo.replace("#6", infs[1]);
/* 127 */           conteudo = conteudo.replace("#7", infs[2]);
/* 128 */           conteudo = conteudo.replace("#8", infs[3]);
/*     */           
/* 130 */           conteudo = conteudo.replace("#a", " ");
/* 131 */           conteudo = conteudo.replace("#c", " ");
/* 132 */           conteudo = conteudo.replace("#4", "OUTRA</b>: ");
/*     */         }
/*     */         else {
/* 135 */           conteudo = conteudo.replace("#c", "x");
/* 136 */           conteudo = conteudo.replaceAll("#4", "OUTRA</b>: " + Solicitacoes.formataTextA4((String)d.get(2), 90 - "OUTRA: ".length()));
/*     */           
/* 138 */           conteudo = conteudo.replace("#a", "");
/* 139 */           conteudo = conteudo.replace("#b", "");
/*     */           
/* 141 */           conteudo = conteudo.replace("#5", "");
/* 142 */           conteudo = conteudo.replace("#7", "");
/* 143 */           conteudo = conteudo.replace("#6", "");
/* 144 */           conteudo = conteudo.replace("#8", "");
/*     */         }
/*     */         
/* 147 */         Arquivo.standardFile("res_soli.html", conteudo);
/* 148 */         Util.openChrome("res_soli.html");
/*     */       }
/* 150 */     });
/* 151 */     this.menOpt.add(miImp);
/*     */   }
/*     */   
/*     */   public static String nextCol(String val, int space) {
/* 155 */     return val + LEsc.repChar(" ", space - val.length());
/*     */   }
/*     */   
/*     */   public static String formataTextA4(String any, int fLineLength) {
/* 159 */     int first = 0;
/* 160 */     int maxReached = 98;
/* 161 */     int reg = 0;
/*     */     
/* 163 */     String lineAt = "";
/* 164 */     for (int i = 0; i < any.length(); i++) {
/* 165 */       int usemax = maxReached;
/* 166 */       if (first == 0) { usemax = fLineLength;
/*     */       }
/* 168 */       lineAt = lineAt + any.charAt(i);
/* 169 */       reg++;
/*     */       
/* 171 */       if (reg >= usemax) {
/* 172 */         lineAt = lineAt + "\r\n";
/* 173 */         reg = 0;
/* 174 */         first = 1;
/*     */       }
/*     */     }
/* 177 */     return lineAt;
/*     */   }
/*     */   
/* 180 */   public static int firstTime = 0;
/* 181 */   public static boolean emEdicao = true;
/*     */   
/* 183 */   private void criarTabelas() { this.modTabPon = new DefaultTableModel(new String[] { "ID", "Funcionário", "Data Ocorr.", "Tipo", "IdFunc", "DataSoli" }, 0) {
/*     */       private static final long serialVersionUID = 1L;
/*     */       
/*     */       public boolean isCellEditable(int row, int column) {
/* 187 */         return false;
/*     */       }
/*     */       
/* 190 */     };
/* 191 */     this.tabPon = new JTable(this.modTabPon);
/* 192 */     this.tabPon.setRowHeight(0, 30);
/* 193 */     this.tabPon.getTableHeader().setReorderingAllowed(false);
/* 194 */     this.tabPon.setAutoResizeMode(0);
/*     */     
/* 196 */     this.tabPon.getColumnModel().removeColumn(this.tabPon.getColumnModel().getColumn(5));
/* 197 */     this.tabPon.getColumnModel().removeColumn(this.tabPon.getColumnModel().getColumn(4));
/* 198 */     this.tabPon.getColumnModel().removeColumn(this.tabPon.getColumnModel().getColumn(0));
/*     */     
/*     */ 
/*     */ 
/* 202 */     this.tabPon.getColumnModel().getColumn(0).setPreferredWidth(522);
/* 203 */     this.tabPon.getColumnModel().getColumn(1).setPreferredWidth(187);
/* 204 */     this.tabPon.getColumnModel().getColumn(2).setPreferredWidth(192);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 210 */     this.barPon = new JScrollPane(this.tabPon);
/* 211 */     this.barPon.setBounds(10, 74, 903, 273);
/*     */   }
/*     */   
/*     */   private JLabel label_3;
/* 215 */   private void criarRotulos() { this.rotSel = new JLabel("SOLICITAÇÕES CORREÇÃO PONTO:");
/* 216 */     this.rotSel.setFont(new Font("Serif", 0, 18));
/* 217 */     this.rotSel.setBounds(10, 35, 312, 24);
/*     */   }
/*     */   
/*     */   public JTextField texEM;
/*     */   public JTextField texSM;
/*     */   public JTextField texET;
/*     */   public JTextField texST;
/*     */   JScrollPane brDesc;
/*     */   JScrollPane brOut;
/*     */   void popTab() {
/* 227 */     ArrayList<String> dados = Rede.httpArr("PLC" + this.idFunc);
/*     */     
/* 229 */     for (int i = 0; i < dados.size(); i += 6) {
/* 230 */       if (((String)dados.get(i + 3)).equals("0")) {
/* 231 */         dados.set(i + 3, "Apenas justificativa.");
/*     */       }
/* 233 */       else if (((String)dados.get(i + 3)).equals("1")) {
/* 234 */         dados.set(i + 3, "Corrigir ponto.");
/*     */       }
/*     */       else {
/* 237 */         dados.set(i + 3, "Outro.");
/*     */       }
/*     */     }
/*     */     
/* 241 */     if (dados.size() > 0) {
/* 242 */       Ig.popTab(this.modTabPon, dados, 6);
/*     */       
/* 244 */       this.tabPon.setRowSelectionInterval(0, 0);
/* 245 */       return;
/*     */     }
/*     */     
/* 248 */     this.modTabPon.setRowCount(0);
/*     */   }
/*     */   
/*     */   private void adiComps()
/*     */   {
/* 253 */     this.bg = new ButtonGroup();
/*     */     
/* 255 */     getContentPane().add(this.barMenu);
/*     */     
/* 257 */     getContentPane().add(this.barPon);
/*     */     
/* 259 */     getContentPane().add(this.rotSel);
/*     */     
/* 261 */     this.label_3 = new JLabel("DETALHES DA SOLICITAÇÃO:");
/* 262 */     this.label_3.setFont(new Font("Serif", 0, 18));
/* 263 */     this.label_3.setBounds(10, 358, 261, 24);
/* 264 */     getContentPane().add(this.label_3);
/*     */     
/* 266 */     this.radCor = new JRadioButton("Corrigir ponto para:");
/* 267 */     this.radCor.setFont(new Font("Arial", 0, 14));
/* 268 */     this.radCor.setBounds(10, 460, 155, 18);
/* 269 */     getContentPane().add(this.radCor);
/*     */     
/* 271 */     this.radOut = new JRadioButton("Outra:");
/* 272 */     this.radOut.setSelected(true);
/* 273 */     this.radOut.setFont(new Font("Arial", 0, 14));
/* 274 */     this.radOut.setBounds(10, 492, 68, 18);
/* 275 */     getContentPane().add(this.radOut);
/*     */     
/* 277 */     this.radCor.setSelected(false);this.radOut.setSelected(false);
/*     */     
/* 279 */     this.bg.add(this.radCor);
/* 280 */     this.bg.add(this.radOut);
/*     */     
/* 282 */     JLabel label = new JLabel("EM:");
/* 283 */     label.setFont(new Font("Arial", 0, 14));
/* 284 */     label.setBounds(170, 460, 29, 18);
/* 285 */     getContentPane().add(label);
/*     */     
/* 287 */     this.texEM = new JTextField();
/* 288 */     this.texEM.setFont(new Font("Arial", 0, 14));
/* 289 */     this.texEM.setBounds(202, 458, 68, 23);
/* 290 */     getContentPane().add(this.texEM);
/*     */     
/* 292 */     JLabel label_1 = new JLabel("SM:");
/* 293 */     label_1.setFont(new Font("Arial", 0, 14));
/* 294 */     label_1.setBounds(283, 459, 29, 18);
/* 295 */     getContentPane().add(label_1);
/*     */     
/* 297 */     this.texSM = new JTextField();
/* 298 */     this.texSM.setFont(new Font("Arial", 0, 14));
/* 299 */     this.texSM.setBounds(315, 457, 68, 23);
/* 300 */     getContentPane().add(this.texSM);
/*     */     
/* 302 */     JLabel label_2 = new JLabel("ET:");
/* 303 */     label_2.setFont(new Font("Arial", 0, 14));
/* 304 */     label_2.setBounds(393, 457, 29, 18);
/* 305 */     getContentPane().add(label_2);
/*     */     
/* 307 */     this.texET = new JTextField();
/* 308 */     this.texET.setFont(new Font("Arial", 0, 14));
/* 309 */     this.texET.setBounds(425, 455, 68, 23);
/* 310 */     getContentPane().add(this.texET);
/*     */     
/* 312 */     JLabel label_4 = new JLabel("ST:");
/* 313 */     label_4.setFont(new Font("Arial", 0, 14));
/* 314 */     label_4.setBounds(503, 457, 29, 18);
/* 315 */     getContentPane().add(label_4);
/*     */     
/* 317 */     this.texST = new JTextField();
/* 318 */     this.texST.setFont(new Font("Arial", 0, 14));
/* 319 */     this.texST.setBounds(535, 455, 68, 23);
/* 320 */     getContentPane().add(this.texST);
/*     */     
/* 322 */     this.texDesc = new LRealArea();
/*     */     
/* 324 */     this.brDesc = new JScrollPane(this.texDesc);
/* 325 */     this.brDesc.setBounds(177, 393, 426, 51);
/* 326 */     getContentPane().add(this.brDesc);
/*     */     
/* 328 */     this.texOut = new LRealArea();
/* 329 */     this.brOut = new JScrollPane(this.texOut);
/* 330 */     this.brOut.setBounds(92, 492, 511, 51);
/* 331 */     getContentPane().add(this.brOut);
/*     */     
/* 333 */     this.rotDesc = new JLabel("Descrição da ocorrência:");
/* 334 */     this.rotDesc.setFont(new Font("Arial", 0, 14));
/* 335 */     this.rotDesc.setBounds(10, 398, 167, 18);
/* 336 */     getContentPane().add(this.rotDesc);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void proParal()
/*     */   {
/* 343 */     new Timer().schedule(new TimerTask() {
/* 344 */       int u = -1;
/*     */       
/* 346 */       public void run() { if (this.u != Solicitacoes.this.tabPon.getSelectedRow()) {
/* 347 */           String val = Ig.gdt(Solicitacoes.this.tabPon.getSelectedRow(), 3, Solicitacoes.this.tabPon);
/*     */           
/* 349 */           ArrayList<String> d = Rede.httpArr("PDS" + Ig.gdt(Solicitacoes.this.tabPon.getSelectedRow(), 0, Solicitacoes.this.tabPon));
/*     */           
/* 351 */           Solicitacoes.this.texDesc.setText((String)d.get(0));
/* 352 */           Solicitacoes.this.texOut.setText("");
/* 353 */           Solicitacoes.this.texEM.setText("");Solicitacoes.this.texSM.setText("");
/* 354 */           Solicitacoes.this.texET.setText("");Solicitacoes.this.texST.setText("");
/* 355 */           Solicitacoes.this.bg.clearSelection();
/*     */           
/* 357 */           if (val.equals("Corrigir ponto.")) {
/* 358 */             Solicitacoes.this.radCor.setSelected(true);
/*     */             
/* 360 */             String[] horas = ((String)d.get(1)).split(";", -1);
/*     */             
/* 362 */             Solicitacoes.this.texEM.setText(horas[0]);
/* 363 */             Solicitacoes.this.texSM.setText(horas[1]);
/* 364 */             Solicitacoes.this.texET.setText(horas[2]);
/* 365 */             Solicitacoes.this.texST.setText(horas[3]);
/*     */             
/* 367 */             Solicitacoes.this.texOut.setText("");
/*     */           }
/* 369 */           else if (val.equals("Outro.")) {
/* 370 */             Solicitacoes.this.radOut.setSelected(true);
/* 371 */             Solicitacoes.this.texOut.setText((String)d.get(2));
/*     */           }
/*     */           
/* 374 */           this.u = Solicitacoes.this.tabPon.getSelectedRow();
/*     */         }
/*     */       }
/* 377 */     }, 33L, 33L);
/*     */   }
/*     */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\ig\Solicitacoes.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */