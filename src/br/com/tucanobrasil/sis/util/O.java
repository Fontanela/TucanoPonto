package br.com.tucanobrasil.sis.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextField;
import br.com.tucanobrasil.sys.util.Ig;
import br.com.tucanobrasil.sys.util.Ig2;
import br.com.tucanobrasil.sys.util.P;
import br.com.tucanobrasil.sys.util.W;

public class O { // Output and funcional class
	

	public static boolean isOK(String status) {
		if(P.strTBool(status)) { return true; }
		if(status.toUpperCase().equals("OK")) { return true; }
		return false;
	}
	
	public static String SOH = "";
	public static String STX = "";
	public static String BEL = "";
	
	public static String[] arrCol(String s) {
		return s.split(";", -1);
	}
	
	public static void execWait(String file) {
		Process p;
		try {
            p = Runtime.getRuntime().exec(file);
            p.waitFor();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void execOpenWait(String file) {
		Process p;
		try {
            p = Runtime.getRuntime().exec("cmd /c start " + file);
            p.waitFor();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String remLB(String with) { //remove line break
		return with.replace("\n", "").replace("\r", "");
	}
	
	public static String aprint(ArrayList<String> arr) {
		return Arrays.toString(arr.toArray());
	}
	
	public static String aprint(String[] arr) {
		return Arrays.toString(arr);
	}
	
	public static void error() {
		Ig.err("System error - contact support."); System.exit(0);
	}
	
	public static String[] sd(String s) { //na base de SOH, geralmente eh standard
		return s.split("", -1);
	}
	
	public static String[] sd(String d, String s) { //split delim
		return s.split(d, -1);
	}
	
	public static String[] cc(String cc) { //comma cols
		return cc.split(";", -1);
	}
	
	public static double percent(double x, double y) {
		 double a = x / 100;
		 return a * y;
	}
	
	public static boolean has(ArrayList<String> a) {
		if(a.size() > 0) { return true; } return false;
	}
	
	public static boolean has(String[] a) {
		if(a.length > 0) { return true; } return false;
	}
	
	public static <T> T co(Object o, Class<T> clazz) { //convertInstanceOfObject
	    try {
	        return clazz.cast(o);
	    } catch(ClassCastException e) {
	        return null;
	    }
	}
	
	public static void p(String s) {
		System.out.println(s);
	}

	public static String nc(String val, int space){ //next-col
		return val + W.repChar(" ", space - val.length());
	}
	
	public static String nextCol(String val, int space, String c){
		return val + W.repChar(c, space - val.length());
	}
	
	public static void nextOnEnter(JTextField[] texs, boolean unit) {
		for (int i = 0; i < texs.length; i++) {
			if(unit) {
				texs[i].setText("" + i);
			}
			if(i != texs.length - 1) {
				final int at = i;

				texs[i].addKeyListener(new KeyListener() {
					public void keyTyped(KeyEvent arg0) {}
					public void keyReleased(KeyEvent arg0) {}
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_ENTER) {
							texs[at + 1].requestFocus();
						}
					}
				});
			}
		}
	}
	
	public static void nextOnEnterExc(JTextField[] texs, boolean unit, String[] exc) {//Vai para o próximo com excessões
		for (int i = 0; i < texs.length; i++) {
			if(unit) {
				texs[i].setText("" + i);
			}			
						
			if(i != texs.length - 1 && Arrays.asList(exc).indexOf(i+"") == -1) {
				final int at = i;

				texs[i].addKeyListener(new KeyListener() {
					public void keyTyped(KeyEvent arg0) {}
					public void keyReleased(KeyEvent arg0) {}
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_ENTER) {
							texs[at + 1].requestFocus();
						}
					}
				});
			}
		}
	}
	
	
	public static void nextAndSelect(JTextField[] texs) {
		for (int i = 0; i < texs.length; i++) {
			if(i != texs.length - 1) {
				final int at = i;
	
				texs[i].addKeyListener(new KeyListener() {
					public void keyTyped(KeyEvent e) {}
					public void keyReleased(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_ENTER) {
							if(!texs[at + 1].isEnabled()) { return; }
							texs[at + 1].requestFocus();
							Ig2.selectAll(texs[at + 1]);
						}
					}
					public void keyPressed(KeyEvent e) {

					}
				});
			}
		}
	}
	
}
