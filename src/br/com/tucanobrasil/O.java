package br.com.tucanobrasil;

//Biblioteca
import br.com.tucanobrasil.sis.util.LEsc;

//Cria��o da classe 
	public class O{
		public static void p(String s) {
		System.out.println(s);
	}
	 
	public static String nextCol(String val, int space) {
		return val + LEsc.repChar(" ", space - val.length());
	}
}
