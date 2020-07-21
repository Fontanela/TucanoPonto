package br.com.tucanobrasil.sis.util;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextField;
import br.com.tucanobrasil.sys.O;

public class Obj { 	//ONE DIMENSIONAL, BIDIMENSIONAL AND THREE OR MORE USE AL OF OBJECT (ex: ''ArrayList<ArrayList<ArrayList<Obj>>> a = new ArrayList<ArrayList<ArrayList<Obj>>>();'')

	public String[] cols;
	public ArrayList<String> o;
	
	public Obj(String[] cols) {
		this.cols = cols;
		o = new ArrayList<String>();
	}
	
	public Obj(Obj c) { //clonagem
		this.cols = c.cols.clone();
		o = new ArrayList<String>(c.o);
	}
	
	public Obj(String[] cols, String[] data) {
		if(data.length < cols.length) {
			System.out.println("EMPTY OBJECT: " + cols.length + "/" + data.length);
			
			this.cols = cols;
			o = new ArrayList<String>();
		}
		else {
			this.cols = cols;
			o = new ArrayList<String>();
			pop(data);
		}
	}
	
	public int size() {
		return o.size() / cols.length;
	}
	
	public void pop(String[] data) {
		if(data.length < cols.length) { return; }
		
		for (int i = 0; i < data.length; i++) {
			o.add(data[i]);
		}
	}
	
	public void removeLine(int i) {
		for (int j = 0; j < cols.length; j++) {
			o.remove(i * cols.length);
		}
	}
	
	public void addLine(Object[] ob) { //AVANCADO
		if(ob.length != cols.length) { Ig.err("Erro inesperado."); System.exit(0); return; }
		
		for (int i = 0; i < ob.length; i++) {
			if(ob[i] instanceof JTextField) {
				JTextField t = (JTextField)ob[i];
				
				o.add(t.getText());
			}
			else {
				o.add(ob[i].toString());
			}
		}
	}
	
	public void chaLine(int r, Object[] ob) {
		if(ob.length != cols.length) { Ig.err("Erro inesperado."); System.exit(0); return; }
		
		for (int i = 0; i < ob.length; i++) {
			if(ob[i] instanceof JTextField) {
				JTextField t = (JTextField)ob[i];
				
				o.set((r * cols.length) + i, t.getText());
			}
			else {
				o.set((r * cols.length) + i, ob[i].toString());
			}
		}
	}
	
	public void addLine(String[] l) { //SIMPLES
		if(l.length != cols.length) { return; }
		
		for (int i = 0; i < l.length; i++) {
			o.add(l[i]);
		}
	}
	
	public void addLine(String s) {
		String[] l = s.split("", -1);
		if(l.length != cols.length) { return; }
		
		for (int i = 0; i < l.length; i++) {
			o.add(l[i]);
		}
	}
	
	public String get(String col) {
		return o.get(getCellIndex(0, col));
	}
	
	public String get(int index, String col) {
		return o.get(getCellIndex(index, col));
	}
	
	public void set(int index, String col, String val) {
		o.set(getCellIndex(index, col), val);
	}
	
	public void set(String col, String val) {
		o.set(getCellIndex(0, col), val);
	}
	
	private int getCellIndex(int index, String col) {
		return index * cols.length + colIndexByName(col);
	}
	
	public int colIndexByName(String col) {
		for (int i = 0; i < cols.length; i++) {
			if(cols[i].equals(col)) { return i; }
		}
		return -1;
	}
	
	public String serialize() {
		String[] fields = cols;
		String r = "";
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < fields.length; j++) {
				r += get(i, fields[j]) + "";
			}
		}
		return W.remLChr(r);
	}
	
	public String serialize(String[] fields) {
		String r = "";
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < fields.length; j++) {
				r += get(i, fields[j]) + "";
			}
		}
		return W.remLChr(r);
	}
	
	public String serialize(int[] lines) {
		String r = "";
		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < cols.length; j++) {
				r += get(lines[i], cols[j]) + "";
			}
		}
		return W.remLChr(r);
	}
	
	public String serialize(String[] fields, int[] lines) {
		String r = "";
		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < fields.length; j++) {
				r += get(lines[i], fields[j]) + "";
			}
		}
		return W.remLChr(r);
	}
	
	public String[] getLine(int l) {
		return serialize(new int[] {l}).split(O.SOH, -1);
	}
	
	public String headers() {
		String r = "";
		for (int i = 0; i < cols.length; i++) {
			r += cols[i] + "";
		}
		return W.remLChr(r);
	}
	
	public String overNetwork(){
		return headers() +""+ serialize(cols);
	}
	
	/*
	 * PROGRAMMER HELP METHODS
	 */
	
	public boolean isMultiline() {
		if(size() > 1) { return true; }
		return false;
	}
	
	public void showCols() {
		System.out.println(">>>>>" + Arrays.toString(cols));
		System.out.println(">>>>>" + o);
	}

}
