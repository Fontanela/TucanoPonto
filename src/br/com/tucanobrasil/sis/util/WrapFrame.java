package br.com.tucanobrasil.sis.util;

import java.awt.Component;
import java.awt.KeyEventDispatcher;

import javax.swing.JFrame;
import javax.swing.table.TableCellRenderer;

import br.com.tucanobrasil.Main;

public abstract class WrapFrame extends JFrame implements KeyEventDispatcher {

	private static final long serialVersionUID = 1L;
	
	public WrapFrame wNext = null;
	public WrapFrame wOrigem = null;
	public JFrame jOrigem;
	public void setOrigem(Object o) {
		if(o instanceof WrapFrame) {
			wOrigem = (WrapFrame)o;
			return;
		}
		jOrigem = (JFrame)o;
	}
	
	public void setNext(Object o) {
		if(o instanceof WrapFrame) {
			wNext = (WrapFrame)o;
			return;
		}
	}
	
	public WrapFrame getOrigemW() {
		return wOrigem;
	}
	
	public JFrame getOrigemJ() {
		return jOrigem;
	}
	
	public WrapFrame getNext() {
		return wNext;
	}
	
	public void setOn() {
		Main.MANAGER.addKeyEventDispatcher(this);
		setVisible(true);
	}
	
	public void setOff() {
		Main.MANAGER.removeKeyEventDispatcher(this);
		setVisible(false);
	}
	
	public boolean actNext() {
		if(wNext != null) { wNext.setOn(); return true; }
		return false;
	}
	
	public WrapFrame sourceByIndex(Class<?> c, int index) { //o tipo do retorno pode ser object tb
		WrapFrame curr = this;
		int co = 0;
		while(co != index) {
			curr = curr.wOrigem;
			co++;
		}
		return curr;
	}
	
	public WrapFrame sourceByType(Class<?> c) { //o tipo do retorno pode ser object tb
		WrapFrame curr = this;
		curr.getClass().getSimpleName();
		while(true) {
			if(curr.getClass().getSimpleName().equals(c.getSimpleName())) {
				return curr;
			}
			curr = curr.wOrigem;
			
			if(curr == null) { return null; }
		}
	}
	
	public WrapFrame sourceByTitle(String title) {
		WrapFrame curr = this;
		while(true) {
			String tit = curr.getTitle();
			
			if(tit.equals(Main.TIT + title)) {
				return curr;
			}
			curr = curr.wOrigem;
		}
	}
	
	public WrapFrame own() { return this; }

	public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
