/*     */ package br.com.tucanobrasil.sis.util;
/*     */ 
/*     */ import br.com.tucanobrasil.sis.lgraf.LCheck;
/*     */ import br.com.tucanobrasil.sis.tech.DBCheck;
/*     */ import br.com.tucanobrasil.sis.tech.DBDrop;
/*     */ import br.com.tucanobrasil.sis.tech.DBPass;
/*     */ import br.com.tucanobrasil.sis.tech.DBText;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ //import java.awt.Dimension;
/*     */ import java.awt.Image;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ //import java.util.Arrays;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.AbstractAction;
/*     */ //import javax.swing.ActionMap;
/*     */ import javax.swing.ImageIcon;
/*     */// import javax.swing.InputMap;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ //import javax.swing.table.TableModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Ig
/*     */ {
/*     */   public static int radioSelected(Container con)
/*     */   {
/*  49 */     Component[] c = con.getComponents();
/*     */     
/*  51 */     int co = 0;
/*  52 */     for (int i = 0; i < c.length; i++) {
/*  53 */       if ((c[i] instanceof JRadioButton)) {
/*  54 */         if (((JRadioButton)c[i]).isSelected()) {
/*  55 */           return co;
/*     */         }
/*     */         
/*  58 */         co++;
/*     */       }
/*     */     }
/*     */     
/*  62 */     return -1;
/*     */   }
/*     */   
/*     */   public static void ocultCol(int ind, JTable t) {
/*  66 */     TableColumnModel tc = t.getColumnModel();
/*  67 */     tc.removeColumn(tc.getColumn(ind));
/*     */   }
/*     */   
/*     */   public static JTextField[] getAllTexts(Container con) {
/*  71 */     ArrayList<JTextField> db = new ArrayList<JTextField>();
/*  72 */     Component[] arrayOfComponent; int j = (arrayOfComponent = con.getComponents()).length; for (int i = 0; i < j; i++) { Component c = arrayOfComponent[i];
/*  73 */       if (((c instanceof DBText)) || ((c instanceof DBPass))) {
/*  74 */         db.add((JTextField)c);
/*     */       }
/*     */     }
/*  77 */     return (JTextField[])db.toArray(new JTextField[db.size()]);
/*     */   }
/*     */   
/*     */   public static LCheck[] getAllLChecks(Container con)
/*     */   {
/*  82 */     ArrayList<LCheck> db = new ArrayList<LCheck>();
/*  83 */     Component[] arrayOfComponent; int j = (arrayOfComponent = con.getComponents()).length; for (int i = 0; i < j; i++) { Component c = arrayOfComponent[i];
/*  84 */       if ((c instanceof LCheck)) {
/*  85 */         db.add((LCheck)c);
/*     */       }
/*     */     }
/*  88 */     return (LCheck[])db.toArray(new LCheck[db.size()]);
/*     */   }
/*     */   
/*     */   public static DBCheck[] getAllChecks(Container con) {
/*  92 */     ArrayList<DBCheck> db = new ArrayList<DBCheck>();
/*  93 */     Component[] arrayOfComponent; int j = (arrayOfComponent = con.getComponents()).length; for (int i = 0; i < j; i++) { Component c = arrayOfComponent[i];
/*  94 */       if ((c instanceof DBCheck)) {
/*  95 */         db.add((DBCheck)c);
/*     */       }
/*     */     }
/*  98 */     return (DBCheck[])db.toArray(new DBCheck[db.size()]);
/*     */   }
/*     */   
/*     */   public static DBDrop[] getAllDrops(Container con) {
/* 102 */     ArrayList<DBDrop> db = new ArrayList<DBDrop>();
/* 103 */     Component[] arrayOfComponent; int j = (arrayOfComponent = con.getComponents()).length; for (int i = 0; i < j; i++) { Component c = arrayOfComponent[i];
/* 104 */       if ((c instanceof DBDrop)) {
/* 105 */         db.add((DBDrop)c);
/*     */       }
/*     */     }
/* 108 */     return (DBDrop[])db.toArray(new DBDrop[db.size()]);
/*     */   }
/*     */   
/*     */   public static String serChecks(JCheckBox[] comps) {
/* 112 */     String mod = "";
/* 113 */     for (int i = 0; i < comps.length; i++) {
/* 114 */       mod = mod + Proc.intbol(comps[i].isSelected()) + "\001";
/*     */     }
/* 116 */     return mod.substring(0, mod.length() - 1);
/*     */   }
/*     */   
/*     */   public static String serTexs(JTextField[] comps) {
/* 120 */     String mod = "";
/* 121 */     for (int i = 0; i < comps.length; i++) {
/* 122 */       mod = mod + comps[i].getText() + "\001";
/*     */     }
/* 124 */     return mod.substring(0, mod.length() - 1);
/*     */   }
/*     */   
/*     */   public static String serDrops(DBDrop[] comps) {
/* 128 */     String mod = "";
/* 129 */     for (int i = 0; i < comps.length; i++) {
/* 130 */       mod = mod + comps[i].getCurrValue() + "\001";
/*     */     }
/* 132 */     return mod.substring(0, mod.length() - 1);
/*     */   }
/*     */   
/*     */   public static String dataTable(int[] lines, int[] cols, JTable tab) {
/* 136 */     String r = "";
/* 137 */     for (int i = 0; i < lines.length; i++) {
/* 138 */       for (int j = 0; j < cols.length; j++) {
/* 139 */         r = r + gdt(lines[i], cols[j], tab) + "\001";
/*     */       }
/*     */     }
/*     */     
/* 143 */     return LEsc.remUChr(r);
/*     */   }
/*     */   
/*     */   public static JLabel voltar(int x, int y) {
/* 147 */     JLabel button = new JLabel(icone("back.png"));
/* 148 */     button.setHorizontalTextPosition(4);
/* 149 */     button.setHorizontalAlignment(0);
/* 150 */     button.setCursor(Cursor.getPredefinedCursor(12));
/* 151 */     button.setBounds(x, y, 150, 35);
/* 152 */     return button;
/*     */   }
/*     */   
/*     */   public static JLabel svoltar(int x, int y) {
/* 156 */     JLabel button = new JLabel(icone("shortback.png"));
/* 157 */     button.setHorizontalTextPosition(4);
/* 158 */     button.setHorizontalAlignment(0);
/* 159 */     button.setCursor(Cursor.getPredefinedCursor(12));
/* 160 */     button.setBounds(x, y, 35, 35);
/* 161 */     return button;
/*     */   }
/*     */   
/*     */   public static void mse(boolean b) {
/* 165 */     if (b) { erro("Você está no modo de seleção.");return; }
/* 166 */     erro("Você não está no modo de seleção.");
/*     */   }
/*     */   
/*     */   public static void pcs() {
/* 170 */     sucesso("Processado com sucesso!");
/*     */   }
/*     */   
/*     */   public static void sr() {
/* 174 */     advertencia("Selecione um registro para continuar.");
/*     */   }
/*     */   
/*     */   public static void na() {
/* 178 */     erro("Você não possuí nivel de acesso para essa função.");
/*     */   }
/*     */   
/*     */   public static void desTab(JTable table) {
/* 182 */     table.getInputMap(1).put(KeyStroke.getKeyStroke(10, 0), "Enter");
/* 183 */     table.getActionMap().put("Enter", new AbstractAction() {
/*     */       private static final long serialVersionUID = 1L;
/*     */       
/*     */       public void actionPerformed(ActionEvent ae) {}
/*     */     });
/*     */   }
/*     */   
/* 190 */   public static String serTab(DefaultTableModel tab) { String str = "";
/*     */     
/* 192 */     for (int i = 0; i < tab.getRowCount(); i++) {
/* 193 */       for (int j = 0; j < tab.getColumnCount(); j++) {
/* 194 */         if (tab.getValueAt(i, j) == null) {
/* 195 */           str = str + "\001";
/*     */         }
/*     */         else
/* 198 */           str = str + tab.getValueAt(i, j) + "\001";
/*     */       }
/*     */     }
/* 201 */     return str.substring(0, str.length() - 1);
/*     */   }
/*     */   
/*     */   public static String serTab(DefaultTableModel tab, int[] cols) {
/* 205 */     String str = "";
/*     */     
/* 207 */     for (int i = 0; i < tab.getRowCount(); i++) {
/* 208 */       for (int j = 0; j < cols.length; j++) {
/* 209 */         if ((i == tab.getRowCount() - 1) && (j == cols.length - 1)) {
/* 210 */           str = str + tab.getValueAt(i, cols[j]);
/* 211 */           break;
/*     */         }
/* 213 */         str = str + tab.getValueAt(i, cols[j]) + "\001";
/*     */       }
/*     */     }
/* 216 */     return str;
/*     */   }
/*     */   
/*     */   public static void limpaTab(DefaultTableModel mod) {
/* 220 */     mod.setRowCount(0);
/*     */   }
/*     */   
/*     */   public static void disPanel(Container pan) {
/* 224 */     Component[] components = pan.getComponents();
/*     */     
/* 226 */     for (int i = 0; i < components.length; i++) {
/* 227 */       if (!components[i].getClass().getName().contains("JLabel"))
/* 228 */         components[i].setEnabled(false);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void disComps(JComponent[] comps) {
/* 233 */     for (int i = 0; i < comps.length; i++) {
/* 234 */       comps[i].setEnabled(false);
/*     */     }
/*     */   }
/*     */   
/*     */   public static String gdm(int r, int c, DefaultTableModel t) {
/* 239 */     if (t.getValueAt(r, c) == null) {
/* 240 */       return "";
/*     */     }
/* 242 */     return t.getValueAt(r, c).toString();
/*     */   }
/*     */   
/*     */   public static String gdt(int r, int c, JTable t) {
/* 246 */     if (t.getModel().getValueAt(r, c) == null) {
/* 247 */       return "";
/*     */     }
/* 249 */     return t.getModel().getValueAt(r, c).toString();
/*     */   }
/*     */   
/*     */   public static void limpaTextos(JTextField[] campos) {
/* 253 */     for (int i = 0; i < campos.length; i++) {
/* 254 */       campos[i].setText("");
/*     */     }
/*     */   }
/*     */   
/*     */   public static ArrayList<String> jTexsPArr(JTextField[] comps) {
/* 259 */     String[] str = new String[comps.length];
/*     */     
/* 261 */     for (int i = 0; i < comps.length; i++) {
/* 262 */       str[i] = comps[i].getText();
/*     */     }
/* 264 */     return null;
/*     */   }
/*     */   
/*     */   public static void scrollToVisible(JTable table, int rowIndex, int vColIndex)
/*     */   {
/* 269 */     if (!(table.getParent() instanceof JViewport)) return;
/* 270 */     JViewport viewport = (JViewport)table.getParent();
/* 271 */     Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
/* 272 */     Point pt = viewport.getViewPosition();
/* 273 */     rect.setLocation(rect.x - pt.x, rect.y - pt.y);
/* 274 */     viewport.scrollRectToVisible(rect);
/*     */   }
/*     */   
/*     */   public static ImageIcon icone(String dir) {
/*     */     try {
/* 279 */       Image image = ImageIO.read(new File(dir));
/* 280 */       return new ImageIcon(image);
/*     */     } catch (IOException e) {
/* 282 */       e.printStackTrace();
/*     */     }
/* 284 */     return null;
/*     */   }
/*     */   
/*     */   public static String sTab(DefaultTableModel tab) {
/* 288 */     String r = "";
/*     */     
/* 290 */     for (int i = 0; i < tab.getRowCount(); i++) {
/* 291 */       for (int j = 0; j < tab.getColumnCount(); j++) {
/* 292 */         if ((i == tab.getRowCount() - 1) && (j == tab.getColumnCount() - 1)) {
/* 293 */           r = r + tab.getValueAt(i, j);
/* 294 */           break;
/*     */         }
/* 296 */         r = r + tab.getValueAt(i, j) + "\001";
/*     */       }
/*     */     }
/*     */     
/* 300 */     return r;
/*     */   }
/*     */   
/*     */   public static void adiL(DefaultTableModel tab, String[] dados) {
/* 304 */     tab.addRow(dados);
/*     */   }
/*     */   
/*     */   public static void altL(DefaultTableModel tab, String[] dados, int r) {
/* 308 */     for (int i = 0; i < dados.length; i++) {
/* 309 */       tab.setValueAt(dados[i], r, i);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void popTab(DefaultTableModel tab, ArrayList<String> infos, int freq, int[] freqs) {
/* 314 */     tab.setRowCount(0);
/* 315 */     for (int i = 0; i < infos.size(); i += freq) {
/* 316 */       String[] pAdi = new String[freq];
/* 317 */       for (int j = 0; j < freq; j++) {
/* 318 */         for (int k = 0; k < freqs.length; k++) {
/* 319 */           if (j == freqs[k]) {
/* 320 */             pAdi[j] = ((String)infos.get(i + j));
/*     */           }
/*     */         }
/*     */       }
/* 324 */       tab.addRow(pAdi);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void popTab(DefaultTableModel tab, String[] infos) {
/* 329 */     tab.setRowCount(0);
/* 330 */     int freq = tab.getColumnCount();
/*     */     
/* 332 */     for (int i = 0; i < infos.length; i += freq) {
/* 333 */       String[] pAdi = new String[freq];
/* 334 */       for (int j = 0; j < freq; j++) {
/* 335 */         pAdi[j] = infos[(i + j)];
/*     */       }
/* 337 */       tab.addRow(pAdi);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void popTab(DefaultTableModel tab, ArrayList<String> infos, int freq) {
/* 342 */     tab.setRowCount(0);
/*     */     
/* 344 */     for (int i = 0; i < infos.size(); i += freq) {
/* 345 */       String[] pAdi = new String[freq];
/* 346 */       for (int j = 0; j < freq; j++) {
/* 347 */         pAdi[j] = ((String)infos.get(i + j));
/*     */       }
/* 349 */       tab.addRow(pAdi);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setRelativo(JComponent a, JComponent b) {
/* 354 */     b.setBounds(15, 15 + a.getY() + a.getHeight(), b.getPreferredSize().width, b.getPreferredSize().height);
/*     */   }
/*     */   
/*     */   public static void setTextoRel(JComponent a, JComponent b) {
/* 358 */     b.setBounds(10 + a.getX() + a.getWidth(), a.getY() - 2, 100, 23);
/*     */   }
/*     */   
/*     */   public static boolean duvida(String mensagem) {
/* 362 */     int dialogResult = JOptionPane.showConfirmDialog(null, mensagem, "TucERP - AVISO!", 0, 2);
/* 363 */     if (dialogResult == 0) {
/* 364 */       return true;
/*     */     }
/* 366 */     return false;
/*     */   }
/*     */   
/*     */   public static void erro(String mensagem) {
/* 370 */     JOptionPane.showMessageDialog(null, mensagem, "TucERP - ERRO!", 
/* 371 */       0);
/*     */   }
/*     */   
/*     */   public static void sucesso(String mensagem) {
/* 375 */     JOptionPane.showMessageDialog(null, mensagem, "TucERP - OK.", 
/* 376 */       1);
/*     */   }
/*     */   
/*     */   public static void advertencia(String mensagem) {
/* 380 */     JOptionPane.showMessageDialog(null, mensagem, "TucERP - ADVERTÊNCIA!", 
/* 381 */       2);
/*     */   }
/*     */   
/*     */   public static int ok(String mensagem) {
/* 385 */     return JOptionPane.showOptionDialog(null, 
/* 386 */       mensagem, "TucanoAlm - Ok", 
/* 387 */       -1, 
/* 388 */       -1, 
/* 389 */       null, 
/* 390 */       null, 
/* 391 */       null);
/*     */   }
/*     */ }
