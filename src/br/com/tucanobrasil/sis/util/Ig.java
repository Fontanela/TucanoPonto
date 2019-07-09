 package br.com.tucanobrasil.sis.util;
 import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import br.com.tucanobrasil.M;
import br.com.tucanobrasil.sis.lgraf.LCheck;
import br.com.tucanobrasil.sis.tech.DBCheck;
import br.com.tucanobrasil.sis.tech.DBDrop;
import br.com.tucanobrasil.sis.tech.DBPass;
import br.com.tucanobrasil.sis.tech.DBText;

 

 
 
 public class Ig
 {
   public static int radioSelected(Container con)
   {
     Component[] c = con.getComponents();
     
     int co = 0;
     for (int i = 0; i < c.length; i++) {
       if ((c[i] instanceof JRadioButton)) {
         if (((JRadioButton)c[i]).isSelected()) {
           return co;
         }
         
         co++;
       }
     }
     
     return -1;
   }
   
   public static void ocultCol(int ind, JTable t) {
     TableColumnModel tc = t.getColumnModel();
    tc.removeColumn(tc.getColumn(ind));
   }
   
   public static JTextField[] getAllTexts(Container con) {
     ArrayList<JTextField> db = new ArrayList<JTextField>();
    Component[] arrayOfComponent; int j = (arrayOfComponent = con.getComponents()).length; for (int i = 0; i < j; i++) { Component c = arrayOfComponent[i];
      if (((c instanceof DBText)) || ((c instanceof DBPass))) {
        db.add((JTextField)c);
       }
     }
   return (JTextField[])db.toArray(new JTextField[db.size()]);
   }
   
   public static LCheck[] getAllLChecks(Container con)
   {
    ArrayList<LCheck> db = new ArrayList<LCheck>();
     Component[] arrayOfComponent; int j = (arrayOfComponent = con.getComponents()).length; for (int i = 0; i < j; i++) { Component c = arrayOfComponent[i];
      if ((c instanceof LCheck)) {
        db.add((LCheck)c);
       }
     }
     return (LCheck[])db.toArray(new LCheck[db.size()]);
   }
   
   public static DBCheck[] getAllChecks(Container con) {
     ArrayList<DBCheck> db = new ArrayList<DBCheck>();
     Component[] arrayOfComponent; int j = (arrayOfComponent = con.getComponents()).length; for (int i = 0; i < j; i++) { Component c = arrayOfComponent[i];
       if ((c instanceof DBCheck)) {
        db.add((DBCheck)c);
       }
     }
     return (DBCheck[])db.toArray(new DBCheck[db.size()]);
   }
   
   public static DBDrop[] getAllDrops(Container con) {
     ArrayList<DBDrop> db = new ArrayList<DBDrop>();
     Component[] arrayOfComponent; int j = (arrayOfComponent = con.getComponents()).length; for (int i = 0; i < j; i++) { Component c = arrayOfComponent[i];
       if ((c instanceof DBDrop)) {
        db.add((DBDrop)c);
       }
     }
     return (DBDrop[])db.toArray(new DBDrop[db.size()]);
   }
   
   public static String serChecks(JCheckBox[] comps) {
     String mod = "";
     for (int i = 0; i < comps.length; i++) {
     mod = mod + Proc.intbol(comps[i].isSelected()) + "\001";
     }
    return mod.substring(0, mod.length() - 1);
   }
   
   public static String serTexs(JTextField[] comps) {
     String mod = "";
     for (int i = 0; i < comps.length; i++) {
      mod = mod + comps[i].getText() + "\001";
     }
    return mod.substring(0, mod.length() - 1);
   }
   
   public static String serDrops(DBDrop[] comps) {
    String mod = "";
    for (int i = 0; i < comps.length; i++) {
      mod = mod + comps[i].getCurrValue() + "\001";
     }
     return mod.substring(0, mod.length() - 1);
   }
   
   public static String dataTable(int[] lines, int[] cols, JTable tab) {
    String r = "";
     for (int i = 0; i < lines.length; i++) {
       for (int j = 0; j < cols.length; j++) {
        r = r + gdt(lines[i], cols[j], tab) + "\001";
       }
     }
     
     return LEsc.remUChr(r);
   }
   
   public static JLabel voltar(int x, int y) {
     JLabel button = new JLabel(icone("back.png"));
     button.setHorizontalTextPosition(4);
     button.setHorizontalAlignment(0);
     button.setCursor(Cursor.getPredefinedCursor(12));
     button.setBounds(x, y, 150, 35);
     return button;
   }
   
   public static JLabel svoltar(int x, int y) {
    JLabel button = new JLabel(icone("shortback.png"));
    button.setHorizontalTextPosition(4);
     button.setHorizontalAlignment(0);
     button.setCursor(Cursor.getPredefinedCursor(12));
    button.setBounds(x, y, 35, 35);
     return button;
   }
   
   public static void mse(boolean b) {
     if (b) { erro("Você está no modo de seleção.");return; }
    erro("Você não está no modo de seleção.");
   }
   
   public static void pcs() {
     sucesso("Processado com sucesso!");
   }
   
   public static void sr() {
     advertencia("Selecione um registro para continuar.");
   }
   
   public static void na() {
    erro("Você não possuí nivel de acesso para essa função.");
   }
   
			public static boolean doubt(String mensagem){
			int dialogResult = JOptionPane.showConfirmDialog (null, mensagem, M.TIT + "S/N?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(dialogResult == JOptionPane.YES_OPTION){
				return true;
			}
			return false;
			}

   public static void desTab(JTable table) {
     table.getInputMap(1).put(KeyStroke.getKeyStroke(10, 0), "Enter");
     table.getActionMap().put("Enter", new AbstractAction() {
       private static final long serialVersionUID = 1L;
       
       public void actionPerformed(ActionEvent ae) {}
     });
   }
   
   public static String serTab(DefaultTableModel tab) { String str = "";
     
     for (int i = 0; i < tab.getRowCount(); i++) {
      for (int j = 0; j < tab.getColumnCount(); j++) {
       if (tab.getValueAt(i, j) == null) {
          str = str + "\001";
         }
         else
          str = str + tab.getValueAt(i, j) + "\001";
       }
     }
     return str.substring(0, str.length() - 1);
   }
   
   public static String serTab(DefaultTableModel tab, int[] cols) {
    String str = "";
     
    for (int i = 0; i < tab.getRowCount(); i++) {
      for (int j = 0; j < cols.length; j++) {
        if ((i == tab.getRowCount() - 1) && (j == cols.length - 1)) {
           str = str + tab.getValueAt(i, cols[j]);
          break;
         }
         str = str + tab.getValueAt(i, cols[j]) + "\001";
       }
     }
     return str;
   }
   
   public static void limpaTab(DefaultTableModel mod) {
    mod.setRowCount(0);
   }
   
   public static void disPanel(Container pan) {
     Component[] components = pan.getComponents();
     
     for (int i = 0; i < components.length; i++) {
       if (!components[i].getClass().getName().contains("JLabel"))
         components[i].setEnabled(false);
     }
   }
   
   public static void disComps(JComponent[] comps) {
    for (int i = 0; i < comps.length; i++) {
       comps[i].setEnabled(false);
     }
   }
   
   public static String gdm(int r, int c, DefaultTableModel t) {
     if (t.getValueAt(r, c) == null) {
       return "";
     }
     return t.getValueAt(r, c).toString();
   }
   
   public static String gdt(int r, int c, JTable t) {
     if (t.getModel().getValueAt(r, c) == null) {
       return "";
     }
   return t.getModel().getValueAt(r, c).toString();
   }
   
   public static void limpaTextos(JTextField[] campos) {
     for (int i = 0; i < campos.length; i++) {
		campos[i].setText("");
     }
   }
   
   public static ArrayList<String> jTexsPArr(JTextField[] comps) {
     String[] str = new String[comps.length];
     
     for (int i = 0; i < comps.length; i++) {
      str[i] = comps[i].getText();
     }
     return null;
   }
   
   public static void scrollToVisible(JTable table, int rowIndex, int vColIndex)
   {
     if (!(table.getParent() instanceof JViewport)) return;
     JViewport viewport = (JViewport)table.getParent();
    Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
    Point pt = viewport.getViewPosition();
     rect.setLocation(rect.x - pt.x, rect.y - pt.y);
     viewport.scrollRectToVisible(rect);
   }
   
   public static ImageIcon icone(String dir) {
     try {
     Image image = ImageIO.read(new File(dir));
      return new ImageIcon(image);
     } catch (IOException e) {
      e.printStackTrace();
     }
   return null;
   }
   
   public static String sTab(DefaultTableModel tab) {
     String r = "";
     
     for (int i = 0; i < tab.getRowCount(); i++) {
       for (int j = 0; j < tab.getColumnCount(); j++) {
        if ((i == tab.getRowCount() - 1) && (j == tab.getColumnCount() - 1)) {
           r = r + tab.getValueAt(i, j);
          break;
         }
        r = r + tab.getValueAt(i, j) + "\001";
       }
     }
     
    return r;
   }
   
   public static void adiL(DefaultTableModel tab, String[] dados) {
     tab.addRow(dados);
   }
   
   public static void altL(DefaultTableModel tab, String[] dados, int r) {
     for (int i = 0; i < dados.length; i++) {
       tab.setValueAt(dados[i], r, i);
     }
   }
   
   public static void popTab(DefaultTableModel tab, ArrayList<String> infos, int freq, int[] freqs) {
    tab.setRowCount(0);
     for (int i = 0; i < infos.size(); i += freq) {
       String[] pAdi = new String[freq];
      for (int j = 0; j < freq; j++) {
        for (int k = 0; k < freqs.length; k++) {
           if (j == freqs[k]) {
            pAdi[j] = ((String)infos.get(i + j));
           }
         }
       }
       tab.addRow(pAdi);
     }
   }
   
   public static void popTab(DefaultTableModel tab, String[] infos) {
    tab.setRowCount(0);
    int freq = tab.getColumnCount();
    for (int i = 0; i < infos.length; i += freq) {
       String[] pAdi = new String[freq];
      for (int j = 0; j < freq; j++) {
        pAdi[j] = infos[(i + j)];
       }
       tab.addRow(pAdi);
     }
   }
   
   public static void popTab(DefaultTableModel tab, ArrayList<String> infos, int freq) {
     tab.setRowCount(0);
     
     for (int i = 0; i < infos.size(); i += freq) {
      String[] pAdi = new String[freq];
       for (int j = 0; j < freq; j++) {
         pAdi[j] = ((String)infos.get(i + j));
       }
       tab.addRow(pAdi);
     }
   }
   
   public static void setRelativo(JComponent a, JComponent b) {
    b.setBounds(15, 15 + a.getY() + a.getHeight(), b.getPreferredSize().width, b.getPreferredSize().height);
   }
   
   public static void setTextoRel(JComponent a, JComponent b) {
    b.setBounds(10 + a.getX() + a.getWidth(), a.getY() - 2, 100, 23);
   }
   
   public static boolean duvida(String mensagem) {
    int dialogResult = JOptionPane.showConfirmDialog(null, mensagem, "TucERP - AVISO!", 0, 2);
    if (dialogResult == 0) {
       return true;
     }
     return false;
   }
   
   public static void erro(String mensagem) {
     JOptionPane.showMessageDialog(null, mensagem, "TucERP - ERRO!", 
       0);
   }
   
   public static void sucesso(String mensagem) {
     JOptionPane.showMessageDialog(null, mensagem, "TucERP - OK.", 
       1);
   }
   
   public static void advertencia(String mensagem) {
     JOptionPane.showMessageDialog(null, mensagem, "TucERP - ADVERTÊNCIA!", 
       2);
   }
   
   public static int ok(String mensagem) {
     return JOptionPane.showOptionDialog(null, 
      mensagem, "TucanoAlm - Ok", 
       -1, 
       -1, 
       null, 
       null, 
      null);
   }
 }
