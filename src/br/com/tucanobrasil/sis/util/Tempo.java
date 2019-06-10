/*     */ package br.com.tucanobrasil.sis.util;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Tempo
/*     */ {
/*     */   public static String hora()
/*     */   {
/*  22 */     DateFormat dateFormat = new SimpleDateFormat("HH");
/*  23 */     Date date = new Date();
/*  24 */     return dateFormat.format(date);
/*     */   }
/*     */   
/*     */   public static boolean horaValida(String h) {
/*  28 */     if (h.equals("")) { return true;
/*     */     }
/*  30 */     String[] v = h.split(":");
/*     */     
/*  32 */     if (v.length < 2) {
/*  33 */       return false;
/*     */     }
/*     */     
/*  36 */     if ((LEsc.eSoNumero(v[0])) && (LEsc.eSoNumero(v[1]))) {
/*  37 */       return true;
/*     */     }
/*  39 */     return false;
/*     */   }
/*     */   
/*     */   public static String shortDW(int year, int month, int day) {
/*  43 */     String dateString = String.format("%d-%d-%d", new Object[] { Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day) });
/*  44 */     Date date = null;
/*     */     try {
/*  46 */       date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
/*     */     } catch (ParseException e) {
/*  48 */       e.printStackTrace();
/*     */     }
/*     */     
/*  51 */     SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE");
/*  52 */     return dateFormat.format(date).substring(0, 3);
/*     */   }
/*     */   
/*     */   public static String horaRH(String hora) {
/*  56 */     String[] i = hora.split(":", -1);
/*     */     
/*  58 */     if (i.length == 1) { return "";
/*     */     }
/*  60 */     return String.format("%02d", new Object[] { Integer.valueOf(Proc.SPI(i[0])) }) + ":" + String.format("%02d", new Object[] { Integer.valueOf(Proc.SPI(i[1])) });
/*     */   }
/*     */   
/*     */   public static String numeroRH(String num) {
/*  64 */     String[] i = num.split("\\.", -1);
/*     */     
/*  66 */     if (i.length == 1) return i[0];
/*  67 */     if (i[1].equals("0")) {
/*  68 */       return i[0];
/*     */     }
/*  70 */     return num;
/*     */   }
/*     */   
/*     */   public static String calcLisHoras(String[] lis) {
/*  75 */     String[] arrayOfString1 = lis;int j = lis.length; for (int i = 0; i < j; i++) { String hhmm = arrayOfString1[i];
/*  76 */       if ((hhmm != null) && (!hhmm.equals(null))) {
/*     */         try {         }
/*     */         catch (NumberFormatException localNumberFormatException) {}
/*     */       }}
/*     */
return null;     }
/*     */     
/*     */ 
/*     */ 
/*     */ 	
		
/*     */     
/*  92 */   
/*     */   
/*     */   public static String difHoras(String time1, String time2) {
/*  96 */     SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
/*  97 */     Date date1 = null;Date date2 = null;
/*     */     try {
/*  99 */       date1 = format.parse(time1 + ":00");
/* 100 */       date2 = format.parse(time2 + ":00");
/*     */ 
/*     */     }
/*     */     catch (ParseException e)
/*     */     {
/* 105 */       return "00:00";
/*     */     }
/* 107 */     long milliseconds = date2.getTime() - date1.getTime();
/*     */     
/* 109 */     int minutes = (int)(milliseconds / 60000L % 60L);
/* 110 */     int hours = (int)(milliseconds / 3600000L % 24L);
/*     */     
/* 112 */     String sin = "";
/* 113 */     if ((minutes < 0) || (hours < 0)) {
/* 114 */       sin = "-";
/*     */     }
/*     */     
/* 117 */     String res = hours + ":" + minutes;
/* 118 */     res = res.replace("-", "");
/* 119 */     return sin + res;
/*     */   }
/*     */   
/*     */   public static String[] splitData(String data) {
/* 123 */     return new String[] { data.substring(0, 10), data.substring(11) };
/*     */   }
/*     */   
/*     */   public static Calendar dateToCal(int y, int m, int d) {
/* 127 */     Calendar ccc = Calendar.getInstance();
/* 128 */     ccc.set(y, m, d);
/* 129 */     return ccc;
/*     */   }
/*     */   
/*     */   public static void somaDia(Calendar c, int v) {
/* 133 */     c.add(5, v);
/*     */   }
/*     */   
/*     */   public static String calToAme(Calendar c) {
/* 137 */     SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
/* 138 */     return format1.format(c.getTime());
/*     */   }
/*     */   
/*     */   public static String formatarCalendar(Calendar cal) {
/* 142 */     return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
/*     */   }
/*     */   
/*     */   public static String amePBras(String ame) {
/* 146 */     String[] infos = ame.split("-");
/*     */     
/* 148 */     return infos[2] + "/" + infos[1] + "/" + infos[0];
/*     */   }
/*     */   
/*     */   public static String brasPAme(String ame) {
/* 152 */     String[] infos = ame.split("/");
/*     */     
/* 154 */     if (infos.length < 3) { return "";
/*     */     }
/* 156 */     return infos[2] + "-" + infos[1] + "-" + infos[0];
/*     */   }
/*     */   
/*     */   public static String formatoBrasil() {
/* 160 */     DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
/* 161 */     Date date = new Date();
/* 162 */     return dateFormat.format(date);
/*     */   }
/*     */   
/*     */   public static String dataBrasil() {
/* 166 */     DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
/* 167 */     Date date = new Date();
/* 168 */     return dateFormat.format(date);
/*     */   }
/*     */   
/*     */   public static String data() {
/* 172 */     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
/* 173 */     Date date = new Date();
/* 174 */     return dateFormat.format(date);
/*     */   }
/*     */   
/*     */   public static String shortbrdata() {
/* 178 */     DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
/* 179 */     Date date = new Date();
/* 180 */     return dateFormat.format(date);
/*     */   }
/*     */   
/*     */   public static String amepshortbr(String ame) {
/* 184 */     String[] infs = ame.split("-");
/*     */     
/* 186 */     String y = infs[0].substring(2, 4);
/*     */     
/* 188 */     return infs[2] + "/" + infs[1] + "/" + y;
/*     */   }
/*     */   
/*     */   public static String horario() {
/* 192 */     DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
/* 193 */     Date date = new Date();
/* 194 */     return dateFormat.format(date);
/*     */   }
/*     */ }


