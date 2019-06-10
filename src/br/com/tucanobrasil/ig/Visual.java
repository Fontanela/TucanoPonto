/*     */ package br.com.tucanobrasil.ig;
/*     */ 
/*     */ import br.com.tucanobrasil.docs.Documentos;
/*     */ import br.com.tucanobrasil.sis.lgraf.LData;
/*     */ import br.com.tucanobrasil.sis.rede.Rede;
/*     */ import br.com.tucanobrasil.sis.util.Ig;
/*     */ import br.com.tucanobrasil.sis.util.LEsc;
/*     */ import br.com.tucanobrasil.sis.util.Tempo;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ import javax.swing.table.JTableHeader;
/*     */ 
/*     */ public class Visual extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JScrollPane barPon;
/*     */   private JTable tabPon;
/*     */   private DefaultTableModel modTabPon;
/*     */   
/*     */   public Visual()
/*     */   {
/*  37 */     janela();
/*  38 */     criarTabelas();
/*  39 */     criarMenus();
/*  40 */     criarRotulos();
/*  41 */     adiComps();
/*     */     
/*  43 */     if (this.tabPon.getRowCount() > 0) {
/*  44 */       this.tabPon.setRowSelectionInterval(this.tabPon.getRowCount() - 1, this.tabPon.getRowCount() - 1);
/*     */     }
/*  46 */     Ig.scrollToVisible(this.tabPon, this.tabPon.getSelectedRow(), 0);
/*     */     
/*  48 */     JLabel label = new JLabel("<html><b>Data:");
/*  49 */     label.setFont(new Font("Arial", 0, 14));
/*  50 */     label.setBounds(10, 296, 44, 21);
/*  51 */     getContentPane().add(label);
/*     */     
/*  53 */     this.tex1 = new LData();
/*  54 */     this.tex1.valido = true;
/*  55 */     this.tex1.setText("05/09/2018");
/*  56 */     this.tex1.setFont(new Font("Arial", 0, 14));
/*  57 */     this.tex1.setBounds(56, 295, 152, 26);
/*  58 */     getContentPane().add(this.tex1);
/*     */     
/*  60 */     JLabel label_1 = new JLabel("<html><b>á:");
/*  61 */     label_1.setFont(new Font("Arial", 0, 14));
/*  62 */     label_1.setBounds(218, 296, 20, 21);
/*  63 */     getContentPane().add(label_1);
/*     */     
/*  65 */     this.tex2 = new LData();
/*  66 */     this.tex2.valido = true;
/*  67 */     this.tex2.setText("05/09/2018");
/*  68 */     this.tex2.setFont(new Font("Arial", 0, 14));
/*  69 */     this.tex2.setBounds(240, 296, 152, 26);
/*  70 */     getContentPane().add(this.tex2);
/*     */     
/*  72 */     Calendar cal = Calendar.getInstance();
/*  73 */     cal.set(cal.get(1), cal.get(2), cal.get(5));
/*  74 */     String este = Tempo.formatarCalendar(cal);
/*     */     
/*  76 */     this.tex1.setText(Tempo.amePBras(este));
/*     */     
/*  78 */     cal.add(5, -31);
/*  79 */     String proximo = Tempo.formatarCalendar(cal);
/*     */     
/*  81 */     this.tex2.setText(Tempo.amePBras(proximo));
/*     */     
/*  83 */     JButton button = new JButton("PESQUISAR");
/*  84 */     button.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/*  86 */         Visual.this.popTab();
/*     */       }
/*  88 */     });
/*  89 */     button.setFont(new Font("Arial", 0, 14));
/*  90 */     button.setBounds(402, 294, 180, 26);
/*  91 */     getContentPane().add(button);
/*     */     
/*  93 */     this.button_1 = new JButton("IMPRIMIR");
/*  94 */     this.button_1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  96 */         Rede.DEST = "servidor.php";
/*     */         
/*  98 */         Documentos.imprimirPonto(Rede.httpPost("CONID\007"), Visual.this.tex2.getText(), Visual.this.tex1.getText());
/*  99 */         Rede.DEST = "pon_servidor.php";
/*     */       }
/* 101 */     });
/* 102 */     this.button_1.setFont(new Font("Arial", 0, 14));
/* 103 */     this.button_1.setBounds(592, 295, 180, 26);
/* 104 */     getContentPane().add(this.button_1);
/*     */     
/* 106 */     popTab();
/*     */     
/* 108 */     setVisible(true);
/*     */   }
/*     */   
/*     */   private void janela() {
/* 112 */     setTitle("Tucano Ponto - Histórico Ponto Funcionário");
/* 113 */     setSize(800, 363);
/* 114 */     getContentPane().setLayout(null);
/* 115 */     setLocationRelativeTo(null);
/* 116 */     setResizable(false);
/* 117 */     setDefaultCloseOperation(3);
/* 118 */     setIconImage(new ImageIcon("32x32.png").getImage());
/* 119 */     getContentPane().setBackground(new Color(255, 255, 181));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 125 */   public static int firstTime = 0;
/* 126 */   public static boolean emEdicao = true;
/*     */   private LData tex2;
/*     */   
/*     */   private void criarMenus() {}
/*     */   
/* 131 */   private void criarTabelas() { this.modTabPon = new DefaultTableModel(new String[] { "Data", "<html><b><font size = 3>EM", "<html><b><font size = 3><font size = 3>SM", "<html><b><font size = 3>ET", "<html><b><font size = 3>ST", "Total", "Total Ajus.", "Dif" }, 0) {
/*     */       private static final long serialVersionUID = 1L;
/*     */       
/*     */       public boolean isCellEditable(int row, int column) {
/* 135 */         return false;
/*     */       }
/*     */       
/* 138 */     };
/* 139 */     this.tabPon = new JTable(this.modTabPon);
/* 140 */     this.tabPon.setRowHeight(0, 30);
/* 141 */     this.tabPon.getTableHeader().setReorderingAllowed(false);
/*     */     
/* 143 */     this.tabPon.addKeyListener(new KeyListener()
/*     */     {
/*     */       public void keyTyped(KeyEvent arg0) {}
/*     */       
/*     */ 
/*     */ 
/*     */       public void keyReleased(KeyEvent arg0) {}
/*     */       
/*     */ 
/*     */ 
/*     */       public void keyPressed(KeyEvent arg0)
/*     */       {
/* 155 */         System.exit(0);
/*     */       }
/*     */       
/* 158 */     });
/* 159 */     this.barPon = new JScrollPane(this.tabPon);
/* 160 */     this.barPon.setBounds(10, 11, 774, 273); }
/*     */   
/*     */   private LData tex1;
/*     */   private JButton button_1;
/*     */   private void criarRotulos() {}
/*     */   
/* 166 */   private void popTab() { String este = Tempo.brasPAme(this.tex1.getText());
/* 167 */     String proximo = Tempo.brasPAme(this.tex2.getText());
/*     */     
/* 169 */     System.out.println(este + " " + proximo);
/* 170 */     String[] dados = LEsc.spl(Rede.httpPost("RHP" + proximo + "\001" + este + "\001" + "LOG"), "\002");
/*     */     
/* 172 */     ArrayList<String> his = LEsc.strPArr(dados[1]);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 181 */     Ig.popTab(this.modTabPon, his, 8);
/*     */   }
/*     */   
/*     */   private void adiComps() {
/* 185 */     getContentPane().add(this.barPon);
/*     */   }
/*     */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\ig\Visual.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */