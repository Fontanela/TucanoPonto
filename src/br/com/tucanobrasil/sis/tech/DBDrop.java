package br.com.tucanobrasil.sis.tech;

import java.util.ArrayList;
import javax.swing.JComboBox;

public class DBDrop extends JComboBox<Object> {
	

	private static final long serialVersionUID = 1L;

	//public String curr;
	public ArrayList<String> ids = new ArrayList<String>();
	public DBDrop() {}
	
	public String getCurrValue() {
		int s = getSelectedIndex();
		if(s == -1) { return "0"; }
		return ids.get(s);
	}
	
	public String getCurrText() {
		int s = getSelectedIndex();
		if(s == -1) { return ""; }
		return getItemAt(s).toString();
	}
	
	public void setIndexByID(String r) {
		for (int i = 0; i < ids.size(); i++) {
			if(ids.get(i).equals(r)) {
				setSelectedIndex(i); return;
			}
		}
	}
	
	public void setIndexByText(String r) {
		for (int i = 0; i < getItemCount(); i++) {
			if(getItemAt(i).equals(r)) {
				setSelectedIndex(i); return;
			}
		}
		setSelectedIndex(0);
	}
	
	/*public void setIndexByCurrValue() {
		for (int i = 0; i < ids.size(); i++) {
			if(ids.get(i).equals(curr)) {
				setSelectedIndex(i); return;
			}
		}
	}*/
	
	public void popCb(ArrayList<String> serial) {
		for (int i = 0; i < serial.size(); i += 2) {
			addRow(serial.get(i), serial.get(i + 1));
		}
	}
	
	public void addRow(String id, String val) {
		ids.add(id);
		addItem(val);
	}
	
	public void remRow(int r) {
		ids.remove(r);
		remove(r);
	}
	
	public void show() {
		String fArray = "";
		for (int i = 0; i < ids.size(); i++) {
			/*
			System.out.println("use C:\\!conv\\" + getItemAt(i));
			System.out.println("EXPORTE PARA C:\\!conv\\c" + getItemAt(i));
			*/
			//System.out.println("\"C:\\Program Files\\LibreOffice\\program\\scalc.exe\" --convert-to csv:\"Text - txt - csv (StarCalc)\":\"31,ANSI,1\" C:\\!CONV\\C"+ getItemAt(i) +".DBF");
			
			fArray += "C" + getItemAt(i) + ";";
		}
		System.out.println(fArray);
	}
	
}
