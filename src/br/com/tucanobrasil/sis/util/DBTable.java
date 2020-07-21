package br.com.tucanobrasil.sis.util;

import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class DBTable extends JTable {
	

	/*
	 * 1. colunas visiveis no começo
	 * 2. invisiveis no final
	 */

	private static final long serialVersionUID = 1L;
	
	ActionEvent act;

	public String[] cdgCols;
	public DBTable() {}
	
	DefaultTableModel modTab;
	public DBTable(DefaultTableModel modTabPon0, String[] cdgCols) {
		super(modTabPon0);
		this.modTab = modTabPon0;
		this.cdgCols = cdgCols;
		
		if(cdgCols != null) {
			if(this.cdgCols.length != modTabPon0.getColumnCount()) { O.error(); }
		}
	}
	
	ListSelectionListener al;
	public void addListener(ListSelectionListener al) {
		this.al = al;
	}
	
	public void showColumns() {
		for(int i = 0 ; i < getColumnCount(); i++) {
			TableColumn tc = getColumn(getColumnName(i));
	
			System.out.println("tabPon.getColumnModel().getColumn("+ i +").setPreferredWidth("+ tc.getWidth() +");");
		}
	}
	
	public String dataByCDG(String cdg) {
		return Ig.gdt(indexByCDG(cdg), this);
	}
	
	public int indexByCDG(String cdg) {
		for (int i = 0; i < cdgCols.length; i++) {
			if(cdgCols[i].equals(cdg)) { return i; }
		}
		return -1; //404 NOT FOUND KKKJ
	}
	
	public void asObject(Obj info) {
		for (int i = 0; i < info.size(); i++) {
			modTab.addRow(info.getLine(i));
		}
	}
	
}
