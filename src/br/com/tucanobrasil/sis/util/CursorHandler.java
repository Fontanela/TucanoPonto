package br.com.tucanobrasil.sis.util;


import java.awt.Cursor;

import javax.swing.JFrame;

public class CursorHandler {
	
	/*
	 * Author: Leonardo F. Couceiro - leoprog[at]outlook.com - https://padronizacao.com.br
	 * In development for Alarmes Tucano softwares
	 * Since 01 of March of 2018,
	 * Last update 29/04/2019,
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

	/*static Timer CURSOR;
		private static TimerTask TASK;
	private static WrapFrame CURR; //CURR = CURRENT
		
	public static void instance() {
		TASK = new TimerTask() {
			@Override
			public void run() {
				CURR.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			}
		};
		CURSOR = new Timer();
		CURSOR.scheduleAtFixedRate(TASK, delay, period);
	}
		
	public static void start(WrapFrame jal) {
		CURR = jal;
	}*/
	
}
