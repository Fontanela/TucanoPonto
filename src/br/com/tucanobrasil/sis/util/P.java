package br.com.tucanobrasil.sis.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class P { // Processment class
	

	public static String boolTStr(boolean b) {
		if(b) { return "1"; }
		return "0";
	}
	
	public static String[] headpack(String s) {
		return new String[] {s.substring(0, 3), s.substring(3)};
	}
	
	public static int intBool(boolean b) {
		return b ? 1 : 0;
	}

	public static boolean strTBool(String c) {
		if(c.equals("1")) { return true; }
		return false;
	}
	
	public static String round(String d) {
		return String.format("%.2f", Double.parseDouble(d));
	}
	public static Date STDT(String d) throws ParseException {
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(d);
		return date1;  
	}
	
	public static String capitalize(String str) {
	    if(str == null || str.isEmpty()) {
	        return str;
	    }

	    return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}
	
	public static String capitalizeAll(String str) {
	    if(str == null || str.isEmpty()) {
	        return str;
	    }

	    String[] strA = str.split(" ");
	    String strFin = "";
	    
	    for(int i = 0; i < strA.length; i++) {
	    	strFin += P.capitalize(strA[i]) + " ";
	    }
	    return strFin;
	}
	
	public static String round(double n) {
		return String.format("%.2f", n);
	}
	
	public static String bmf(double n) { //money format
		NumberFormat brs = NumberFormat.getCurrencyInstance();
		return brs.format(n).replace("R", "").replace("$", "");
	}
	
	
	public static String mf(double n) { //money format
		return new DecimalFormat("0.00").format(n).replace(",", ".");
	}
	
	public static String mf(String n) { //money format
		double b = P.STD(n);
		return new DecimalFormat("0.00").format(b).replace(",", ".");
	}
	
	public static double rtd(double number){ //round 2 decimal
		number = Math.round(number * 100);
		return number / 100;
	}
	
	public static String rtd(String arg){ //round 2 decimal
		double number = STD(arg);
		number = Math.round(number * 100);
		return "" + number / 100;
	}
	
	public static int STI(String arg){ //STRING TO INTEGER
		if(arg.length() == 0) { return 0; }
		return Integer.valueOf(arg);
	}
	
	public static String ITS(String arg){ //STRING TO INTEGER
	if(arg.length() == 0) { return ""; }
	return String.valueOf(arg);
	}
	
	public static double STD(String arg){ //STRING TO DOUBLE
		if(arg.length() == 0) { return 0; }
		return Double.parseDouble(arg.replace(",", "."));
	}
	
	@SuppressWarnings("deprecation")
	public static String SDTI(String arg) { //STRING DOUBLE TO INT
		if(arg.length() == 0) { return "0"; } return "" + new Double(arg.replace(",", ".")).intValue();
	}
	
	@SuppressWarnings("deprecation")
	public static double SDTD(String arg) { //STRING DOUBLE TO D
		if(arg.length() == 0) { return 0D; } return new Double(arg.replace(",", ".")).doubleValue();
	}
	
	public static ArrayList<String> breakLis(String lis[], int freq) {
		ArrayList<String> outs = new ArrayList<String>();
		
		int l = 0;
		while(l != lis.length) {
			String outAt = "";
			for (int i = 0; i < freq; i++) {
				outAt = outAt + lis[l + i] + "";
			}

			outs.add(outAt.substring(0, outAt.length() - 1));
			l = l + freq;
		}
		return outs;
	}

}
