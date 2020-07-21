package br.com.tucanobrasil.sis.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Time {
	
	public static boolean validHour(String h) {
		if(h.equals("")) { return true; }
		
		String[] v = h.split(":");
		
		if(v.length < 2) {
			return false;
		}
		
		if(W.isNumber(v[0]) && W.isNumber(v[1])) {
			return true;
		}
		return false;
	}
	
	public static String shortDW(int year, int month, int day) { //short day of week
		String dateString = String.format("%d-%d-%d", year, month, day);
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE");
		return dateFormat.format(date).substring(0, 3);
	}
	
	public static String small() { //short day of week
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yy");
		Date date = new Date();
		return dateFormat.format(date);
	}
		

	public static int DWI(String day) { //Dia da semana para index
		String pais = System.getProperty("user.country"); 
		
		String[] dias = {"seg", "ter", "qua", "qui", "sex", "sáb", "dom"};
		String[] UsDias = {"mon", "tue", "wed", "thu", "fri", "sat", "sun"};
		
		if(!pais.equals("BR")) {			
			return Arrays.asList(UsDias).indexOf(day.toLowerCase());				
		}

		return Arrays.asList(dias).indexOf(day.toLowerCase());		
	}
	
	public static String hourRH(String hora) {
		String[] i = hora.split(":", -1);
		
		if(i.length == 1) { return ""; }
		
		return String.format("%02d", P.STI(i[0])) + ":" + String.format("%02d", P.STI(i[1]));
	}
	
	public static String numberRH(String num) {
		String[] i = num.split("\\.", -1);
		
		if(i.length == 1) { return i[0]; }
		if(i[1].equals("0")) {
			return i[0];
		}
		return num;
	}
	
	public static String calcHoursList(String[] lis){
		int sum = 0;
		for(String hhmm : lis){
			if(hhmm != null && !hhmm.equals(null)){
				 String[] split = hhmm.split(":");
				 try {
					 int mins = Integer.valueOf(split[ 0 ]) * 60 + Integer.valueOf( split[ 1 ] );
					 sum += mins;
				 }
				 catch(NumberFormatException e) {
					 //System.err.println(hhmm.charAt(1));
				 }
				// sum += mins;
			}
		}
		
		String min = "" + ( sum % 60); min = min.replace("-", "");
		String formattedWorkingTime = (int)Math.floor(sum/60) + ":" + min;
		
		return formattedWorkingTime;
	}
	
	public static String calcToShow(String[] lis){
		int sum = 0;
		for(String hhmm : lis){
			if(hhmm != null && !hhmm.equals(null)){
				 String[] split = hhmm.split(":");
				 try {
					 int mins = Integer.valueOf(split[ 0 ]) * 60 + Integer.valueOf( split[ 1 ] );
					 sum += mins;
				 }
				 catch(NumberFormatException e) {
					 //System.err.println(hhmm.charAt(1));
				 }
				// sum += mins;
			}
		}
		
		String formattedWorkingTime = (int)Math.floor(sum/60) + ":" + (sum % 60);
		String ret = "" + ( sum % 60); ret = ret.replace("-", "");
		if(formattedWorkingTime.contains("-")) {
			return (int)Math.floor(sum/60) + ":" + ret;
			//return "-" + ret;
		}

		return formattedWorkingTime;
	}
	
	public static String diffHours(String time1, String time2){
		SimpleDateFormat formats = new SimpleDateFormat("HH:mm");
		Date date1 = null, date2 = null;
		try {
			date1 = formats.parse(time1);
			date2 = formats.parse(time2);
		}
		catch (ParseException e) {
			return "00:00";
		}
		double milliseconds = date2.getTime() - date1.getTime(); 
		
		int minutes = (int) ((milliseconds / (1000*60)) % 60);
		int hours   = (int) ((milliseconds / (1000*60*60)) % 24);
		
		String sin = ""; //sinal
		if(minutes < 0 || hours < 0){
			sin = "-";
		}
		String zrm = "", zrh = "";
		
		if(minutes < 10 && minutes > -10){zrm = "0";}
		if(hours < 10 && hours > -10){zrh = "0";}		
		
		String res = zrh+hours + ":"+ zrm + minutes;
		res = res.replace("-", "");
		return sin + res;
		
	}
	
	public static String diffHoursALM(String time1, String time2){
		SimpleDateFormat formats = new SimpleDateFormat("HH:mm");
		Date date1 = null, date2 = null;
		try {
			date1 = formats.parse(time1);
			date2 = formats.parse(time2);
		}
		catch (ParseException e) {
			return "00:00";
		}
		double milliseconds = date2.getTime() - date1.getTime(); 
		
		int minutes = (int) ((milliseconds / (1000*60)) % 60);
		int hours   = (int) ((milliseconds / (1000*60*60)) % 24);
		
		String sin = ""; //sinal
		if(minutes < 0 || hours < 0){
			sin = "-";
		}
		
		if(minutes < 10 && minutes > -10){
			String res = hours + ":0" + minutes;;
			res = res.replace("-", "");
			return sin + res;
		}
		
		else {
		String res = hours + ":" + minutes;
		res = res.replace("-", "");
		return sin + res;
		}
	}
	
	public static String[] splitData(String data){
		return new String[]{data.substring(0, 10), data.substring(11)};
	}
	
	public static Calendar dateToCal(int y, int m, int d) {
		Calendar ccc = Calendar.getInstance();
		ccc.set(y, m, d);
		return ccc;
	}
	
	public static void sumDay(Calendar c, int v) {
		c.add(Calendar.DATE, v);
	}
	
	public static String calToAme(Calendar c) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(c.getTime());
	}
	
	public static String calToBra(Calendar c) {
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		return format1.format(c.getTime());
	}
	
	public static String formatCalendar(Calendar cal){
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}
	
	public static String dtNfTbr(String ame){
		String[] dds = ame.split("T");
		String[] infos = dds[0].split("-");
		
		return infos[2] + "/" + infos[1] + "/" + infos[0];
	}
	
	public static String ameTBraz(String ame){
		String[] infos = ame.split("-");
		
		return infos[2] + "/" + infos[1] + "/" + infos[0];
	}
	
	public static String brazTAme(String ame){ //Brasileiro p/ Americano
		String[] infos = ame.split("/");
		
		if(infos.length < 3) { return ""; }
		
		return infos[2] + "-" + infos[1] + "-" + infos[0];
	}
	
	public static String brazilFormat(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String brazilDate(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String ameDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	
	
	public static String date(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String shortbrdata() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String ametshortbr(String ame) {
		String[] infs = ame.split("-");
		
		String y = infs[0].substring(2, 4);
		
		return infs[2] +"/"+ infs[1] +"/"+ y;
	}
	
	public static String hour(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String DTBR(String data) {//Passa os valores do data chooser para data normal br
		String[] Data = data.split(" ", -1);
		String DataFor = "";
		
		//Leitura do mês selecionado para mês numérico	
			if(Data[1].equals("Jan"))DataFor = "01";if(Data[1].equals("Feb"))DataFor = "02";if(Data[1].equals("Mar"))DataFor = "03";if(Data[1].equals("Apr"))DataFor = "04";	if(Data[1].equals("May"))DataFor = "05";if(Data[1].equals("Jun"))DataFor = "06";
			if(Data[1].equals("Jul"))DataFor = "07";if(Data[1].equals("Aug"))DataFor = "08";if(Data[1].equals("Sep"))DataFor = "09";if(Data[1].equals("Oct"))DataFor = "10";if(Data[1].equals("Nov"))DataFor = "11";if(Data[1].equals("Dec"))DataFor = "12";

			String databr = Data[2] + "/" + DataFor + "/" + Data[5];
							
		return databr; 
	}
	
	public static String dtcSoMesAno(String data) {//Passa os valores do data chooser para data normal br
		String[] Data = data.split(" ", -1);
		String DataFor = "";
		
		//Leitura do mês selecionado para mês numérico	
			if(Data[1].equals("Jan"))DataFor = "01";if(Data[1].equals("Feb"))DataFor = "02";if(Data[1].equals("Mar"))DataFor = "03";if(Data[1].equals("Apr"))DataFor = "04";	if(Data[1].equals("May"))DataFor = "05";if(Data[1].equals("Jun"))DataFor = "06";
			if(Data[1].equals("Jul"))DataFor = "07";if(Data[1].equals("Aug"))DataFor = "08";if(Data[1].equals("Sep"))DataFor = "09";if(Data[1].equals("Oct"))DataFor = "10";if(Data[1].equals("Nov"))DataFor = "11";if(Data[1].equals("Dec"))DataFor = "12";

			String[] Ano = Data[5].split("", -1);
			String AnoFor = Ano[2] + Ano[3];
			
			String databr = AnoFor + "/" + DataFor;
							
		return databr; 
	}
	

}
