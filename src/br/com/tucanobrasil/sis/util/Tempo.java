 package br.com.tucanobrasil.sis.util;

 

 import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

 

 

 

 

 

 

 

 

 

 

 public class Tempo

 {

   public static String hora()

   {

    DateFormat dateFormat = new SimpleDateFormat("HHmm");

     Date date = new Date();

     return dateFormat.format(date);

   }



   

   public static boolean horaValida(String h) {

    if (h.equals("")) { return true;

     }

     String[] v = h.split(":");

     

     if (v.length < 2) {

      return false;

     }

     

     if ((LEsc.eSoNumero(v[0])) && (LEsc.eSoNumero(v[1]))) {

       return true;

     }

     return false;

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

   

   public static String shortDW(int year, int month, int day) {

     String dateString = String.format("%d-%d-%d", new Object[] { Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day) });

     Date date = null;

     try {

      date = new SimpleDateFormat("yyyy-M-d").parse(dateString);

     } catch (ParseException e) {

       e.printStackTrace();

     }

     

     SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE");

    return dateFormat.format(date).substring(0, 3);

   }

   

   public static String horaRH(String hora) {

     String[] i = hora.split(":", -1);

     

    if (i.length == 1) { return "";

     }

     return String.format("%02d", new Object[] { Integer.valueOf(Proc.SPI(i[0])) }) + ":" + String.format("%02d", new Object[] { Integer.valueOf(Proc.SPI(i[1])) });

   }

   

   public static String numeroRH(String num) {

     String[] i = num.split("\\.", -1);

     

    if (i.length == 1) return i[0];

    if (i[1].equals("0")) {

       return i[0];

     }

     return num;

   }

   

   public static String calcLisHoras(String[] lis) {

     String[] arrayOfString1 = lis;int j = lis.length; for (int i = 0; i < j; i++) { String hhmm = arrayOfString1[i];

      if ((hhmm != null) && (!hhmm.equals(null))) {

         try {         }

         catch (NumberFormatException localNumberFormatException) {}

       }}



				return null;     }

     

 

 

 	

		

    

   

   public static String difHoras(String time1, String time2) {

     SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

     Date date1 = null;Date date2 = null;

     try {

       date1 = format.parse(time1 + ":00");

      date2 = format.parse(time2 + ":00");

 

     }

     catch (ParseException e)

     {

     return "00:00";

     }

    long milliseconds = date2.getTime() - date1.getTime();

     

    int minutes = (int)(milliseconds / 60000L % 60L);

     int hours = (int)(milliseconds / 3600000L % 24L);

     

    String sin = "";

     if ((minutes < 0) || (hours < 0)) {

       sin = "-";

     }

     

    String res = hours + ":" + minutes;

     res = res.replace("-", "");

     return sin + res;

   }

   

   public static String[] splitData(String data) {

     return new String[] { data.substring(0, 10), data.substring(11) };

   }

   

   public static Calendar dateToCal(int y, int m, int d) {

     Calendar ccc = Calendar.getInstance();

     ccc.set(y, m, d);

     return ccc;

   }

   

   public static void somaDia(Calendar c, int v) {

     c.add(5, v);

   }

   

   public static String calToAme(Calendar c) {

     SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

    return format1.format(c.getTime());

   }

   

   public static String formatarCalendar(Calendar cal) {

    return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

   }

   

   public static String amePBras(String ame) {

    String[] infos = ame.split("-");

     

     return infos[2] + "/" + infos[1] + "/" + infos[0];

   }

   

   public static String brasPAme(String ame) {

    String[] infos = ame.split("/");

     

    if (infos.length < 3) { return "";

     }

    return infos[2] + "-" + infos[1] + "-" + infos[0];

   }

   

   public static String formatoBrasil() {

     DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

     Date date = new Date();

    return dateFormat.format(date);

   }

   

   public static String dataBrasil() {

     DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

     Date date = new Date();

    return dateFormat.format(date);

   }

   

   public static String data() {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    Date date = new Date();

     return dateFormat.format(date);

   }

   

   public static String shortbrdata() {

   DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

     Date date = new Date();

     return dateFormat.format(date);

   }

   

   public static String amepshortbr(String ame) {

   String[] infs = ame.split("-");

     

     String y = infs[0].substring(2, 4);

     

    return infs[2] + "/" + infs[1] + "/" + y;

   }

   

   public static String horario() {

     DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

     Date date = new Date();

    return dateFormat.format(date);

   }

   

   public static String horarr() {

	     DateFormat dateFormat = new SimpleDateFormat("HH:mm");

	     Date date = new Date();

	    return dateFormat.format(date);

	   }

   

 }





