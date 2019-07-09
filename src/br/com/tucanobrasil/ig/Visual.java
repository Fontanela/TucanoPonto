 package br.com.tucanobrasil.ig;
 import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import br.com.tucanobrasil.docs.Documentos;
import br.com.tucanobrasil.sis.lgraf.LData;
import br.com.tucanobrasil.sis.rede.Rede;
import br.com.tucanobrasil.sis.util.Ig;
import br.com.tucanobrasil.sis.util.LEsc;
import br.com.tucanobrasil.sis.util.Tempo;
 
 public class Visual extends JFrame
 {
   private static final long serialVersionUID = 1L;
   private JScrollPane barPon;
   private JTable tabPon;
   private DefaultTableModel modTabPon;
   
   public Visual()
   {
    janela();
     criarTabelas();
    criarMenus();
     criarRotulos();
     adiComps();
     
    if (this.tabPon.getRowCount() > 0) {
      this.tabPon.setRowSelectionInterval(this.tabPon.getRowCount() - 1, this.tabPon.getRowCount() - 1);
     }
    Ig.scrollToVisible(this.tabPon, this.tabPon.getSelectedRow(), 0);
     
    JLabel label = new JLabel("<html><b>Data:");
     label.setFont(new Font("Arial", 0, 14));
    label.setBounds(10, 296, 44, 21);
     getContentPane().add(label);
     
    this.tex1 = new LData();
    this.tex1.valido = true;
     this.tex1.setText("05/09/2018");
   this.tex1.setFont(new Font("Arial", 0, 14));
   this.tex1.setBounds(56, 295, 152, 26);
    getContentPane().add(this.tex1);
     
     JLabel label_1 = new JLabel("<html><b>Ã¡:");
     label_1.setFont(new Font("Arial", 0, 14));
     label_1.setBounds(218, 296, 20, 21);
     getContentPane().add(label_1);
     
    this.tex2 = new LData();
     this.tex2.valido = true;
     this.tex2.setText("05/09/2018");
   this.tex2.setFont(new Font("Arial", 0, 14));
     this.tex2.setBounds(240, 296, 152, 26);
     getContentPane().add(this.tex2);
     
    Calendar cal = Calendar.getInstance();
    cal.set(cal.get(1), cal.get(2), cal.get(5));
    String este = Tempo.formatarCalendar(cal);
     
    this.tex1.setText(Tempo.amePBras(este));
     
     cal.add(5, -31);
     String proximo = Tempo.formatarCalendar(cal);
     
    this.tex2.setText(Tempo.amePBras(proximo));
     
    JButton button = new JButton("PESQUISAR");
    button.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent arg0) {
         Visual.this.popTab();
       }
    });
     button.setFont(new Font("Arial", 0, 14));
    button.setBounds(402, 294, 180, 26);
     getContentPane().add(button);
     
    this.button_1 = new JButton("IMPRIMIR");
    this.button_1.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
         Rede.DEST = "servidor.php";
         
         Documentos.imprimirPonto(Rede.httpPost("CONID\007"), Visual.this.tex2.getText(), Visual.this.tex1.getText());
         Rede.DEST = "pon_servidor.php";
       }
    });
     this.button_1.setFont(new Font("Arial", 0, 14));
     this.button_1.setBounds(592, 295, 180, 26);
     getContentPane().add(this.button_1);
     
     popTab();
     
    setVisible(true);
   }
   
   private void janela() {
    setTitle("Tucano Ponto - Histórico Ponto do Funcionário");
    setSize(800, 363);
   getContentPane().setLayout(null);
     setLocationRelativeTo(null);
   setResizable(false);
    setDefaultCloseOperation(3);
     setIconImage(new ImageIcon("32x32.png").getImage());
     getContentPane().setBackground(new Color(255, 255, 181));
   }
   
 
 
 
   public static int firstTime = 0;
  public static boolean emEdicao = true;
   private LData tex2;
   
   private void criarMenus() {}
   
   private void criarTabelas() { this.modTabPon = new DefaultTableModel(new String[] { "Data", "<html><b><font size = 3>EM", "<html><b><font size = 3><font size = 3>SM", "<html><b><font size = 3>ET", "<html><b><font size = 3>ST", "Total", "Total Ajus.", "Dif" }, 0) {
       private static final long serialVersionUID = 1L;
       
       public boolean isCellEditable(int row, int column) {
        return false;
       }
       
    };
    this.tabPon = new JTable(this.modTabPon);
    this.tabPon.setRowHeight(0, 30);
    this.tabPon.getTableHeader().setReorderingAllowed(false);
     
     this.tabPon.addKeyListener(new KeyListener()
     {
       public void keyTyped(KeyEvent arg0) {}
       
 
 
       public void keyReleased(KeyEvent arg0) {}
       
 
 
       public void keyPressed(KeyEvent arg0)
       {
        System.exit(0);
       }
       
     });
    this.barPon = new JScrollPane(this.tabPon);
    this.barPon.setBounds(10, 11, 774, 273); }
   
   private LData tex1;
   private JButton button_1;
   private void criarRotulos() {}
   
  private void popTab() { String este = Tempo.brasPAme(this.tex1.getText());
    String proximo = Tempo.brasPAme(this.tex2.getText());
     
    System.out.println(este + " " + proximo);
    String[] dados = LEsc.spl(Rede.httpPost("RHP" + proximo + "\001" + este + "\001" + "LOG"), "\002");
    ArrayList<String> his = LEsc.strPArr(dados[1]);
     
 
 
 
 
 
 
 
    Ig.popTab(this.modTabPon, his, 8);
   }
   
   private void adiComps() {
    getContentPane().add(this.barPon);
   }
 }

