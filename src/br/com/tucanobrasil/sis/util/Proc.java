 package br.com.tucanobrasil.sis.util;
 
 import java.util.ArrayList;
 
 
 
 
 
 
 
 
 public class Proc
 {
   public static String[] headpack(String s)
   {
    return new String[] { s.substring(0, 3), s.substring(3) };
   }
   
   public static int intbol(boolean b) {
     return b ? 1 : 0;
   }
   
   public static boolean strPBol(String c) {
     if (c.length() == 0) { return false;
     }
     if (c.equals("0")) return false;
     return true;
   }
   
   public static String arred(String d) {
     return String.format("%.2f", new Object[] { Double.valueOf(Double.parseDouble(d)) });
   }
   
   public static String arred(double n) {
     return String.format("%.2f", new Object[] { Double.valueOf(n) });
   }
   
   public static double rtd(double number) {
    number = Math.round(number * 100.0D);
    return number / 100.0D;
   }
   
   public static int SPI(String arg) {
     return Integer.valueOf(arg).intValue();
   }
   
   public static double SPD(String arg) {
     return Double.parseDouble(arg);
   }
   
   public static ArrayList<String> breakLis(String[] lis, int freq) {
     ArrayList<String> outs = new ArrayList<String>();
     
     int l = 0;
    while (l != lis.length) {
      String outAt = "";
     for (int i = 0; i < freq; i++) {
         outAt = outAt + lis[(l + i)] + "\001";
       }
       
      outs.add(outAt.substring(0, outAt.length() - 1));
       l += freq;
     }
     return outs;
   }
 }


