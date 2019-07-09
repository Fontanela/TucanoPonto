package br.com.tucanobrasil.sis.util;


import java.awt.Cursor;
import javax.swing.JFrame;

public class CursorHandler {
	
	/*
	 * Author: Gabriel Fontanela
	 * In development for Alarmes Tucano softwares
	 * Since 01 of March of 2018,
	 * Last update 02/07/2019,
	 * Prohibited total or partial reproduction of the content, enterprise use only.
	 */
	
	public static void start(final JFrame jal) {
		new Thread(new Runnable() {
			public void run() {
				jal.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			}
		}).run();
	}
	
	public static void stop(final JFrame jal) {
		new Thread(new Runnable() {
			public void run() {
				jal.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}).run();
	}
	
}
