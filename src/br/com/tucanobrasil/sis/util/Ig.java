package br.com.tucanobrasil.sis.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import br.com.tucanobrasil.M;
import br.com.tucanobrasil.sis.tech.DBCheck;
import br.com.tucanobrasil.sis.tech.DBDrop;
import br.com.tucanobrasil.sis.tech.DBPass;
import br.com.tucanobrasil.sis.tech.DBText;


public class Ig { //Ig = Interface graphic
	

	public static int[] lineArr(JTable t) {
		int[] l = new int[t.getRowCount()];
		for (int i = 0; i < t.getRowCount(); i++) {
			l[i] = i;
		}
		return l;
	}
	

	
	public static void selectAll(JTable t) {
		t.setRowSelectionInterval(0, t.getRowCount() - 1);
	}
	
	public static void unselect(JTable t) {
		t.setRowSelectionInterval(-1, -1);
	}
	
	public static int radioSelected(Container con) {
		Component[] c = con.getComponents();
		
		int co = 0;
		for (int i = 0; i < c.length; i++) {
			if(c[i] instanceof JRadioButton) {
				if(((JRadioButton) c[i]).isSelected()) {
					return co;
				}
				else {
					co++;
				}
			}
		}
		return -1;
	}
	
	public static void ocultCol(int ind, JTable t) {
		TableColumnModel tc = t.getColumnModel();
		tc.removeColumn(tc.getColumn(ind));
	}
	
	public static JRadioButton[] getAllRadios(Container con) {
		ArrayList<JRadioButton> db = new ArrayList<JRadioButton>();
		for (Component c : con.getComponents()) {
			if(c instanceof JRadioButton) {
				db.add((JRadioButton)c);
			}
		}
		return db.toArray(new JRadioButton[db.size()]);
	}
	
	public static JTextField[] getTexts(Container con, int s, int f) {
		ArrayList<JTextField> db = new ArrayList<JTextField>();
		
		Component[] c = con.getComponents();
		for (int i = s; i < f; i++) {
			Component at = c[i];
			if(at instanceof DBText || at instanceof DBPass || at instanceof JFormattedTextField) {
				db.add((JTextField)at);
			}
		}
		return db.toArray(new JTextField[db.size()]);
	}
	
	public static JTextField[] getAllTexts(Container con) {
		ArrayList<JTextField> db = new ArrayList<JTextField>();
		for (Component c : con.getComponents()) {
			if(c instanceof DBText || c instanceof DBPass || c instanceof JFormattedTextField) {
				db.add((JTextField)c);
			}
		}
		return db.toArray(new JTextField[db.size()]);
	}
	
	
	
	public static JTextField[] getAllJava(Container con) {
		ArrayList<JTextField> db = new ArrayList<JTextField>();
		for (Component c : con.getComponents()) {
			if( (c instanceof JTextField || c instanceof JFormattedTextField) && !(c instanceof DBText)) {
				db.add((JTextField)c);
			}
		}
		return db.toArray(new JTextField[db.size()]);
	}
	
	
	
	public static DBCheck[] getAllChecks(Container con) {
		ArrayList<DBCheck> db = new ArrayList<DBCheck>();
		for (Component c : con.getComponents()) {
			if(c instanceof DBCheck) {
				db.add((DBCheck)c);
			}
		}
		return db.toArray(new DBCheck[db.size()]);
	}
	
	public static JCheckBox[] getAllJChecks(Container con) {
		ArrayList<JCheckBox> db = new ArrayList<JCheckBox>();
		for (Component c : con.getComponents()) {
			if(c instanceof JCheckBox) {
				db.add((JCheckBox)c);
			}
		}
		return db.toArray(new JCheckBox[db.size()]);
	}
	
	public static DBDrop[] getAllDrops(Container con) {
		ArrayList<DBDrop> db = new ArrayList<DBDrop>();
		for (Component c : con.getComponents()) {
			if(c instanceof DBDrop) {
				db.add((DBDrop)c);
			}
		}
		return db.toArray(new DBDrop[db.size()]);
	}
		
	public static JDateChooser[] getAllDates(Container con) {
		ArrayList<JDateChooser> db = new ArrayList<JDateChooser>();
		for (Component c : con.getComponents()) {
			if(c instanceof JDateChooser) {
				db.add((JDateChooser)c);
			}
		}
		return db.toArray(new JDateChooser[db.size()]);
	}
	
	public static String serChecks(JCheckBox[] comps){
		String mod = "";
		for (int i = 0; i < comps.length; i++) {
			mod = mod + P.intBool(comps[i].isSelected()) + "";
		}
		return mod.substring(0, mod.length() - 1);
	}
	
	public static String serChecksLeng(JCheckBox[] comps, String regex){
		String mod = "";
		for (int i = 0; i < comps.length; i++) {
			mod = mod + P.intBool(comps[i].isSelected()) + regex;
		}
		return mod.substring(0, mod.length() - 1);
	}
	
	public static String serTexs(JTextField[] comps){
		String mod = "";
		for (int i = 0; i < comps.length; i++) {
			mod = mod + comps[i].getText() + "";
		}
		return mod.substring(0, mod.length() - 1);
	}
	

	public static String serTexsLeng(JTextField[] comps, int ini, int fin, String regex){
		String mod = "";
		for (int i = ini; i < fin; i++) {
			mod = mod + comps[i].getText() + regex;
		}
		return mod.substring(0, mod.length() - 1);
	}
	
	
	public static String serTexsCon(JTextField[] comps){
		String mod = "";
		for (int i = comps.length -1; i > 0; i--) {
			mod = mod + comps[i].getText() + "";
		}
		return mod.substring(0, mod.length() - 1);
	}
	
	public static String serDrops(DBDrop[] comps){
		String mod = "";
		for (int i = 0; i < comps.length; i++) {
			mod = mod + comps[i].getCurrValue() + "";
		}
		return mod.substring(0, mod.length() - 1);
	}
	
	
	public static String serDatas(JDateChooser[] comps){
		String mod = "";
		for (int i = 0; i < comps.length; i++) {
			mod = mod + Time.DTBR(comps[i].getDate() + "") + "";
		}
		return mod.substring(0, mod.length() - 1);
	}
	
	
	
	public static JLabel back(int x, int y){
		JLabel button = new JLabel(Ig.icon("back.png"));
		button.setHorizontalTextPosition(SwingConstants.RIGHT);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBounds(x, y, 150, 35);
		return button;
	}
	
	public static JLabel sback(int x, int y){ //short
		JLabel button = new JLabel(Ig.icon("shortback.png"));
		button.setHorizontalTextPosition(SwingConstants.RIGHT);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBounds(x, y, 35, 35);
		return button;
	}
	
	public static void mse(boolean b) { //selection mode
		if(b) { Ig.err("Você está no modo de seleção."); return; }
		Ig.err("Você nâo está no modo de seleção.");
	}
	
	public static void ps() { //PROCESSED SUCESSFULLY
		Ig.success("Processado com sucesso!");
	}
	
	public static void wr() { //WITHOUT REG
		Ig.alert("Selecione um registro para continuar.");
	}
	
	public static void na(){ //No AUTHORIZED
		Ig.err("Você não possuí nivel de acesso para essa função.");
	}
	
	public static void desTab(JTable table){ 
		table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
	    table.getActionMap().put("Enter", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent ae) {}
	    });
	}
	
	public static String serTab(DefaultTableModel tab){
		String str = "";
		
		for(int i = 0; i < tab.getRowCount(); i++){
			for (int j = 0; j < tab.getColumnCount(); j++) {
				if(tab.getValueAt(i, j) == null){
					str = str + "";
					continue;
				}
				str = str + tab.getValueAt(i, j) + "";
			}
		}
		if(str.equals("")) { return ""; }
		return str.substring(0, str.length() - 1);
	}
	
	
	public static String serTabData(DefaultTableModel tab){
		String str = "";
		
		for(int i = 0; i < tab.getRowCount(); i++){
			for (int j = 0; j < tab.getColumnCount(); j++) {
				if(tab.getValueAt(i, j) == null){
					str = str + "";
					continue;
				}
				str = str + Time.brazTAme(tab.getValueAt(i, j) + "") + "";
			}
		}
		if(str.equals("")) { return ""; }
		return str.substring(0, str.length() - 1);
	}
		
	public static String serTabSelected(JTable tab, int[] rows, int col){
		String str = "";
		
		for(int i = 0; i < rows.length; i++){
				if(Ig.gdt(rows[i], col, tab) == null){
					str = str + "";
					continue;
				}
				str += Ig.gdt(rows[i], col, tab)  + "";			
		}
		if(str.equals("")) { return ""; }
		return str.substring(0, str.length() - 1);
	}
	
	
	public static String serTab(DefaultTableModel tab, int[] cols){
		String str = "";
		
		for(int i = 0; i < tab.getRowCount(); i++){
			for (int j = 0; j < cols.length; j++) {
				if(i == tab.getRowCount() - 1 && j == cols.length - 1){
					str = str + tab.getValueAt(i, cols[j]);
					break;
				}
				str = str + tab.getValueAt(i, cols[j]) + "";
			}
		}
		return str;
	}
	
	public static void eraseTab(DefaultTableModel mod){
		mod.setRowCount(0);
	}
	
	public static void disPanel(Container pan){ //disable panel
		Component[] components = pan.getComponents();
		
	    for(int i = 0; i < components.length; i++) {
	    	if(components[i].getClass().getName().contains("JLabel")) { continue; }
	        components[i].setEnabled(false);
	    }
	}
	
	public static void disComps(JComponent[] comps){ //disable components
		for (int i = 0; i < comps.length; i++) {
			comps[i].setEnabled(false);
		}
	}
	
	public static String gdm(int r, int c, DefaultTableModel t) { //get data model
		if(t.getValueAt(r, c) == null){
			return "";
		}
		return t.getValueAt(r, c).toString();
	}
	
	public static String gdt(int r, int c, JTable t) { //get data table
		if(t.getModel().getValueAt(r, c) == null){
			return "";
		}
		return t.getModel().getValueAt(r, c).toString();
	}
	
	public static boolean gdtBo(int r, int c, JTable t) { //get data table
		if(t.getModel().getValueAt(r, c).equals("true")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static String gdt(int c, JTable t) { //get data table
		int r = t.getSelectedRow();
		
		if(r == -1) { return ""; } //added 2/05/2019
		
		if(t.getModel().getValueAt(r, c) == null){
			return "";
		}
		return t.getModel().getValueAt(r, c).toString();
	}
	
	public static void eraseTexts(JTextField[] texts){
		for (int i = 0; i < texts.length; i++) {
			texts[i].setText("");
		}
	}
	
	public static void eraseTexts(JTextField[] texts, int s, int f){
		for (int i = s; i < f; i++) {
			texts[i].setText("");
		}
	}
	
	public class MyColorCellRenderer extends DefaultTableCellRenderer {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int rowToColor = -1;

	    public MyColorCellRenderer() {
	    }

	    public void setRowToColor(int row) {
	        rowToColor = row;
	    }

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

	         if (rowToColor!=-1 && row==rowToColor)
	            setForeground(Color.RED);
	         return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);          
	    }
	}

	
	public static ArrayList<String> jTexsTArr(JTextField[] comps){ //jtexts to array
		String[] str = new String[comps.length];
		
		for (int i = 0; i < comps.length; i++) {
			str[i] = comps[i].getText();
		}
		return new ArrayList<String>(Arrays.asList(str));
	}
	
	public static String[] jTextTSArr(JTextField[] comps) {
		String[] str = new String[comps.length];
		
		for (int i = 0; i < comps.length; i++) {
			str[i] = comps[i].getText();
		}
		return str;
	}
	
	public static void scrollToVisible(JTable table, int rowIndex, int vColIndex)
	{
	    if (!(table.getParent() instanceof JViewport)) return;
	    JViewport viewport = (JViewport)table.getParent();
	    Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
	    Point pt = viewport.getViewPosition();
	    rect.setLocation(rect.x-pt.x, rect.y-pt.y);
	    viewport.scrollRectToVisible(rect);
	}
	
	public static ImageIcon icon(String dir){
		try {
			Image image = ImageIO.read(new File(dir));
			return new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String sTab(DefaultTableModel tab){ //fully serialize table
		String r = "";
		
		for(int i = 0; i < tab.getRowCount(); i++){
			for(int j = 0; j < tab.getColumnCount(); j++){
				if(i == tab.getRowCount() - 1 && j == tab.getColumnCount() - 1){
					r = r + tab.getValueAt(i, j);
					break;
				}
				r = r + tab.getValueAt(i, j) + "";
			}
		}
		
		return r;
	}
	
	public static void addR(DefaultTableModel tab, String[] dados){
		tab.addRow(dados);
	}

	public static void chaR(DefaultTableModel tab, String[] dados, int r){
		for (int i = 0; i < dados.length; i++) {
			tab.setValueAt(dados[i], r, i);
		}
	}
	
	public static void popTab(DefaultTableModel tab, ArrayList<String> infos, int freq, int[] freqs){
		tab.setRowCount(0);
		for(int i = 0; i < infos.size(); i = i + freq){
			String[] pAdi = new String[freq];
			for(int j = 0; j < freq; j++){
				for(int k = 0; k < freqs.length; k++){
					if(j == freqs[k]){
						pAdi[j] = infos.get(i + j);
					}
				}
			}
			tab.addRow(pAdi);
		}
	}
	
	public static void popTab(DefaultTableModel tab, String[] infos){
		tab.setRowCount(0);
		int freq = tab.getColumnCount();
		
		for(int i = 0; i < infos.length; i = i + freq){
			String[] pAdi = new String[freq];
			for(int j = 0; j < freq; j++){
				pAdi[j] = infos[i + j];
			}
			tab.addRow(pAdi);
		}
	}
	
	public static void popTab(DefaultTableModel tab, ArrayList<String> infos, int freq){
		tab.setRowCount(0);
		
		for(int i = 0; i < infos.size(); i = i + freq){
			String[] pAdi = new String[freq];
			for(int j = 0; j < freq; j++){
				pAdi[j] = infos.get(i + j);
			}
			tab.addRow(pAdi);
		}
	}
	
	public static void popTabDdsPd(DefaultTableModel tab, ArrayList<String> infos, int freq){
		tab.setRowCount(0);
		double vlrUnt = 0;
		int qtd = 0;
				
		for(int i = 0; i < infos.size(); i = i + freq){
			String[] pAdi = new String[freq];
			for(int j = 0; j < freq; j++){
				if(j == 1) {qtd = P.STI(infos.get(i + j));}
				if(j == 2) {vlrUnt = P.STD(infos.get(i + j));}
				if(j == 6) {pAdi[j] = P.rtd(vlrUnt*qtd) + "";}
				else {pAdi[j] = infos.get(i + j);}
			}
			tab.addRow(pAdi);
		}
	}
	
	public static void popTabCheck(DefaultTableModel tab, ArrayList<String> infos, int freq, int clol){
		tab.setRowCount(0);
		boolean chk = false;
		
		for(int i = 0; i < infos.size(); i = i + freq){
			Object[] pAdi = new Object[freq];
			for(int j = 0; j < freq; j++){
				if(j == clol) {
					if(P.STI(infos.get(i + j)) == 1) {chk=true;}
					else {chk=false;}
					
					pAdi[j] = chk;
				}
				else {pAdi[j] = infos.get(i + j);}
			}
			tab.addRow(pAdi);
		}
	}
	
	public static void popTabCons(DefaultTableModel tab, ArrayList<String> infos, int freq, int vlr, int saldop, double saldoant, int D, int r){
		tab.setRowCount(0);
		double saldo = saldoant;
		String dc = "";
		if(infos.size() > 0) {
			 dc = infos.get(D);
		}
		DecimalFormat numFormat = new DecimalFormat("#,###,###.00");
		String VLR = "", VLR2 = "";
		String DZ = "D", CZ = "C";

		if(infos.size() > 0) {
			for(int i = 0; i < infos.size(); i = i + freq){
				String[] pAdi = new String[freq];
				for(int j = 0; j < freq; j++){
					if(j+1 == D) {
						dc = infos.get(i + (j+1));
					}
					if(j == vlr) {		
							if(r == 1) {DZ = "d"; CZ = "c";}
							else {DZ = "D"; CZ = "C";}
							if(dc.equals("C")||dc.equals(CZ)) {
								saldo += P.STD(infos.get(i + j));
							}
							else if(dc.equals("D")||dc.equals(DZ)) {
								saldo -= P.STD(infos.get(i + j));
							}
							else {
								saldo += 0;
							}					
					}
					if(j == saldop) {
						VLR = numFormat.format(P.rtd(saldo));				
						VLR2 = VLR.replace(",", "s");
						String VLR3 = VLR2.replace(".",",");
						String VLR4 = VLR3.replace("s",".");
						pAdi[j] = VLR4;
					}
					else {
						pAdi[j] = infos.get(i + j);
					}
				}
				tab.addRow(pAdi);
			}
		}
	}
	
	public static void setRelative(JComponent a, JComponent b){
		b.setBounds(15, 15 + a.getY() + a.getHeight(), b.getPreferredSize().width, b.getPreferredSize().height);
	}
	
	public static void setTextRelative(JComponent a, JComponent b){
		b.setBounds(10 + a.getX() + a.getWidth(), a.getY() - 2, 100, 23);
	}
	
	public static boolean doubt(String mensagem){
		int dialogResult = JOptionPane.showConfirmDialog (null, mensagem, M.TIT + "S/N?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(dialogResult == JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	
	public static boolean vista(String mensagem){
		int dialogResult = JOptionPane.showConfirmDialog (null, mensagem, M.TIT + "S/N?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		UIManager.put(JOptionPane.YES_OPTION, "Parcelado");
		UIManager.put(JOptionPane.NO_OPTION, "À vista");
		
		
		if(dialogResult == JOptionPane.YES_OPTION){
			return true;
		}
		return false;
}
	
	public static void err(String mensagem){
		JOptionPane.showMessageDialog(null, mensagem, M.TIT + "ERRO!",
                JOptionPane.ERROR_MESSAGE);
		
	}
	
	public static void success(String mensagem){
		JOptionPane.showMessageDialog(null, mensagem, M.TIT + "OK.",
                JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void alert(String mensagem){
		JOptionPane.showMessageDialog(null, mensagem, M.TIT + "ADVERTÊNCIA!",
                JOptionPane.WARNING_MESSAGE);
	}
	
	public static int ok(String mensagem){
		return JOptionPane.showOptionDialog(null,
				mensagem, M.TIT + "Ok",
				JOptionPane.PLAIN_MESSAGE,
				JOptionPane.DEFAULT_OPTION,
				null,
				null,
				null);
	}




}
