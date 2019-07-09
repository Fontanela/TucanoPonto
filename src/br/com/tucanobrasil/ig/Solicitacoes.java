 package br.com.tucanobrasil.ig;
 import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import br.com.tucanobrasil.sis.lgraf.LRealArea;
import br.com.tucanobrasil.sis.rede.Rede;
import br.com.tucanobrasil.sis.util.Arquivo;
import br.com.tucanobrasil.sis.util.Ig;
import br.com.tucanobrasil.sis.util.LEsc;
import br.com.tucanobrasil.sis.util.Tempo;
import br.com.tucanobrasil.sis.util.Util;
 
 
 public class Solicitacoes
   extends JFrame
 {
   private static final long serialVersionUID = 1L;
   private JMenuBar barMenu;
   private JMenu menOpt;
   private JScrollPane barPon;
   public JTable tabPon;
   private DefaultTableModel modTabPon;
   private JLabel rotSel;
   private ButtonGroup bg;
   private JRadioButton radCor;
   private JRadioButton radOut;
   private LRealArea texDesc;
   private LRealArea texOut;
   JLabel rotDesc;
   String idFunc = "";
   
  public Solicitacoes(String idFunc) { this.idFunc = idFunc;
     
     janela();
     criarTabelas();
     criarMenus();
     criarRotulos();
     adiComps();
     
     popTab();
     
 
     this.texDesc.setEditable(false);this.texOut.setEditable(false);
     this.texEM.setEditable(false);this.texSM.setEditable(false);
     this.texET.setEditable(false);this.texST.setEditable(false);
     this.radCor.setEnabled(false);this.radOut.setEnabled(false);
     
     proParal();
     setVisible(true);
   }
   
   private void janela() {
    setTitle("Tucano Ponto - Solicitar Correções do Ponto");
    setSize(929, 596);
   getContentPane().setLayout(null);
    setLocationRelativeTo(null);
    setResizable(false);
     setDefaultCloseOperation(3);
     setIconImage(new ImageIcon("32x32.png").getImage());
    getContentPane().setBackground(new Color(255, 255, 181));
   }
   
   private void criarMenus() {
    this.barMenu = new JMenuBar();
     this.barMenu.setBounds(0, 0, getWidth(), 25);
     
    this.menOpt = new JMenu("Opções");
    this.barMenu.add(this.menOpt);
     
    JMenuItem miImp = new JMenuItem("Imprimir");
     miImp.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
       String conteudo = LEsc.arrPStr(Arquivo.ler("./html_soli.html"));
         
        int l = Solicitacoes.this.tabPon.getSelectedRow();
         
        String dataSoli = Tempo.amePBras(Ig.gdt(l, 5, Solicitacoes.this.tabPon).substring(0, 10));
        String dataOcorr = Ig.gdt(l, 2, Solicitacoes.this.tabPon).substring(0, 10);
         
         conteudo = conteudo.replace("#N", Ig.gdt(l, 1, Solicitacoes.this.tabPon));
       conteudo = conteudo.replace("#1", dataSoli);
        conteudo = conteudo.replace("#2", dataOcorr);
         
        ArrayList<String> d = Rede.httpArr("PDS" + Ig.gdt(Solicitacoes.this.tabPon.getSelectedRow(), 0, Solicitacoes.this.tabPon));
         conteudo = conteudo.replace("#3", Solicitacoes.formataTextA4((String)d.get(0), 73));
         
         if (((String)d.get(3)).equals("0")) {
           conteudo = conteudo.replace("#a", "x");
           
          conteudo = conteudo.replace("#b", " ");
          conteudo = conteudo.replace("#c", " ");
          conteudo = conteudo.replace("#4", "OUTRA</b>: ");
           
           conteudo = conteudo.replace("#5", "");
          conteudo = conteudo.replace("#7", "");
           conteudo = conteudo.replace("#6", "");
         conteudo = conteudo.replace("#8", "");
         }
        else if (((String)d.get(3)).equals("1")) {
           String[] infs = ((String)d.get(1)).split(";", -1);
           
           conteudo = conteudo.replace("#b", "x");
           
           conteudo = conteudo.replace("#5", infs[0]);
           conteudo = conteudo.replace("#6", infs[1]);
         conteudo = conteudo.replace("#7", infs[2]);
         conteudo = conteudo.replace("#8", infs[3]);
           
           conteudo = conteudo.replace("#a", " ");
          conteudo = conteudo.replace("#c", " ");
           conteudo = conteudo.replace("#4", "OUTRA</b>: ");
         }
         else {
          conteudo = conteudo.replace("#c", "x");
           conteudo = conteudo.replaceAll("#4", "OUTRA</b>: " + Solicitacoes.formataTextA4((String)d.get(2), 90 - "OUTRA: ".length()));
           
        conteudo = conteudo.replace("#a", "");
          conteudo = conteudo.replace("#b", "");
           
           conteudo = conteudo.replace("#5", "");
           conteudo = conteudo.replace("#7", "");
           conteudo = conteudo.replace("#6", "");
           conteudo = conteudo.replace("#8", "");
         }
         
        Arquivo.standardFile("res_soli.html", conteudo);
         Util.openChrome("res_soli.html");
       }
    });
    this.menOpt.add(miImp);
   }
   
   public static String nextCol(String val, int space) {
    return val + LEsc.repChar(" ", space - val.length());
   }
   
   public static String formataTextA4(String any, int fLineLength) {
     int first = 0;
    int maxReached = 98;
    int reg = 0;
     
    String lineAt = "";
     for (int i = 0; i < any.length(); i++) {
      int usemax = maxReached;
      if (first == 0) { usemax = fLineLength;
       }
     lineAt = lineAt + any.charAt(i);
      reg++;
       
      if (reg >= usemax) {
         lineAt = lineAt + "\r\n";
        reg = 0;
         first = 1;
       }
     }
     return lineAt;
   }
   
   public static int firstTime = 0;
  public static boolean emEdicao = true;
   
  private void criarTabelas() { this.modTabPon = new DefaultTableModel(new String[] { "ID", "Funcionário", "Data Ocorr.", "Tipo", "IdFunc", "DataSoli" }, 0) {
       private static final long serialVersionUID = 1L;
       
       public boolean isCellEditable(int row, int column) {
         return false;
       }
       
     };
    this.tabPon = new JTable(this.modTabPon);
    this.tabPon.setRowHeight(0, 30);
     this.tabPon.getTableHeader().setReorderingAllowed(false);
     this.tabPon.setAutoResizeMode(0);
     
    this.tabPon.getColumnModel().removeColumn(this.tabPon.getColumnModel().getColumn(5));
     this.tabPon.getColumnModel().removeColumn(this.tabPon.getColumnModel().getColumn(4));
     this.tabPon.getColumnModel().removeColumn(this.tabPon.getColumnModel().getColumn(0));
     
 
 
     this.tabPon.getColumnModel().getColumn(0).setPreferredWidth(522);
     this.tabPon.getColumnModel().getColumn(1).setPreferredWidth(187);
    this.tabPon.getColumnModel().getColumn(2).setPreferredWidth(192);
     
 
 
 
 
    this.barPon = new JScrollPane(this.tabPon);
     this.barPon.setBounds(10, 74, 903, 273);
   }
   
   private JLabel label_3;
  private void criarRotulos() { this.rotSel = new JLabel("SOLICITAÇÕES CORREÇÕES PONTO:");
    this.rotSel.setFont(new Font("Serif", 0, 18));
    this.rotSel.setBounds(10, 35, 312, 24);
   }
   
   public JTextField texEM;
   public JTextField texSM;
   public JTextField texET;
   public JTextField texST;
   JScrollPane brDesc;
   JScrollPane brOut;
   void popTab() {
    ArrayList<String> dados = Rede.httpArr("PLC" + this.idFunc);
     
     for (int i = 0; i < dados.size(); i += 6) {
      if (((String)dados.get(i + 3)).equals("0")) {
         dados.set(i + 3, "Apenas justificativa.");
       }
       else if (((String)dados.get(i + 3)).equals("1")) {
       dados.set(i + 3, "Corrigir ponto.");
       }
       else {
         dados.set(i + 3, "Outro.");
       }
     }
     
    if (dados.size() > 0) {
       Ig.popTab(this.modTabPon, dados, 6);
       
       this.tabPon.setRowSelectionInterval(0, 0);
       return;
     }
     
     this.modTabPon.setRowCount(0);
   }
   
   private void adiComps()
   {
     this.bg = new ButtonGroup();
     
    getContentPane().add(this.barMenu);
     
     getContentPane().add(this.barPon);
     
     getContentPane().add(this.rotSel);
     
    this.label_3 = new JLabel("DETALHES DA SOLICITAÇÃO:");
    this.label_3.setFont(new Font("Serif", 0, 18));
     this.label_3.setBounds(10, 358, 261, 24);
     getContentPane().add(this.label_3);
     
    this.radCor = new JRadioButton("Corrigir ponto para:");
     this.radCor.setFont(new Font("Arial", 0, 14));
     this.radCor.setBounds(10, 460, 155, 18);
    getContentPane().add(this.radCor);
     
    this.radOut = new JRadioButton("Outra:");
     this.radOut.setSelected(true);
     this.radOut.setFont(new Font("Arial", 0, 14));
     this.radOut.setBounds(10, 492, 68, 18);
     getContentPane().add(this.radOut);
     
     this.radCor.setSelected(false);this.radOut.setSelected(false);
     
     this.bg.add(this.radCor);
     this.bg.add(this.radOut);
     
     JLabel label = new JLabel("EM:");
   label.setFont(new Font("Arial", 0, 14));
    label.setBounds(170, 460, 29, 18);
     getContentPane().add(label);
     
    this.texEM = new JTextField();
     this.texEM.setFont(new Font("Arial", 0, 14));
     this.texEM.setBounds(202, 458, 68, 23);
     getContentPane().add(this.texEM);
     
   JLabel label_1 = new JLabel("SM:");
    label_1.setFont(new Font("Arial", 0, 14));
    label_1.setBounds(283, 459, 29, 18);
     getContentPane().add(label_1);
     
    this.texSM = new JTextField();
    this.texSM.setFont(new Font("Arial", 0, 14));
    this.texSM.setBounds(315, 457, 68, 23);
     getContentPane().add(this.texSM);
     
     JLabel label_2 = new JLabel("ET:");
     label_2.setFont(new Font("Arial", 0, 14));
     label_2.setBounds(393, 457, 29, 18);
     getContentPane().add(label_2);
     
    this.texET = new JTextField();
     this.texET.setFont(new Font("Arial", 0, 14));
     this.texET.setBounds(425, 455, 68, 23);
    getContentPane().add(this.texET);
     
     JLabel label_4 = new JLabel("ST:");
     label_4.setFont(new Font("Arial", 0, 14));
     label_4.setBounds(503, 457, 29, 18);
     getContentPane().add(label_4);
     
     this.texST = new JTextField();
    this.texST.setFont(new Font("Arial", 0, 14));
     this.texST.setBounds(535, 455, 68, 23);
     getContentPane().add(this.texST);
     
     this.texDesc = new LRealArea();
     
     this.brDesc = new JScrollPane(this.texDesc);
     this.brDesc.setBounds(177, 393, 426, 51);
     getContentPane().add(this.brDesc);
     
     this.texOut = new LRealArea();
    this.brOut = new JScrollPane(this.texOut);
     this.brOut.setBounds(92, 492, 511, 51);
    getContentPane().add(this.brOut);
     
     this.rotDesc = new JLabel("Descrição da ocorrência:");
    this.rotDesc.setFont(new Font("Arial", 0, 14));
     this.rotDesc.setBounds(10, 398, 167, 18);
     getContentPane().add(this.rotDesc);
   }
   
 
 
   private void proParal()
   {
    new Timer().schedule(new TimerTask() {
       int u = -1;
       
      public void run() { if (this.u != Solicitacoes.this.tabPon.getSelectedRow()) {
          String val = Ig.gdt(Solicitacoes.this.tabPon.getSelectedRow(), 3, Solicitacoes.this.tabPon);
           
          ArrayList<String> d = Rede.httpArr("PDS" + Ig.gdt(Solicitacoes.this.tabPon.getSelectedRow(), 0, Solicitacoes.this.tabPon));
           
          Solicitacoes.this.texDesc.setText((String)d.get(0));
          Solicitacoes.this.texOut.setText("");
           Solicitacoes.this.texEM.setText("");Solicitacoes.this.texSM.setText("");
           Solicitacoes.this.texET.setText("");Solicitacoes.this.texST.setText("");
          Solicitacoes.this.bg.clearSelection();
           
         if (val.equals("Corrigir ponto.")) {
            Solicitacoes.this.radCor.setSelected(true);
             
            String[] horas = ((String)d.get(1)).split(";", -1);
             
            Solicitacoes.this.texEM.setText(horas[0]);
           Solicitacoes.this.texSM.setText(horas[1]);
             Solicitacoes.this.texET.setText(horas[2]);
             Solicitacoes.this.texST.setText(horas[3]);
             
             Solicitacoes.this.texOut.setText("");
           }
           else if (val.equals("Outro.")) {
            Solicitacoes.this.radOut.setSelected(true);
            Solicitacoes.this.texOut.setText((String)d.get(2));
           }
           
           this.u = Solicitacoes.this.tabPon.getSelectedRow();
         }
       }
     }, 33L, 33L);
   }
 }
