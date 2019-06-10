/*    */ package br.com.tucanobrasil.sis.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Proc
/*    */ {
/*    */   public static String[] headpack(String s)
/*    */   {
/* 16 */     return new String[] { s.substring(0, 3), s.substring(3) };
/*    */   }
/*    */   
/*    */   public static int intbol(boolean b) {
/* 20 */     return b ? 1 : 0;
/*    */   }
/*    */   
/*    */   public static boolean strPBol(String c) {
/* 24 */     if (c.length() == 0) { return false;
/*    */     }
/* 26 */     if (c.equals("0")) return false;
/* 27 */     return true;
/*    */   }
/*    */   
/*    */   public static String arred(String d) {
/* 31 */     return String.format("%.2f", new Object[] { Double.valueOf(Double.parseDouble(d)) });
/*    */   }
/*    */   
/*    */   public static String arred(double n) {
/* 35 */     return String.format("%.2f", new Object[] { Double.valueOf(n) });
/*    */   }
/*    */   
/*    */   public static double rtd(double number) {
/* 39 */     number = Math.round(number * 100.0D);
/* 40 */     return number / 100.0D;
/*    */   }
/*    */   
/*    */   public static int SPI(String arg) {
/* 44 */     return Integer.valueOf(arg).intValue();
/*    */   }
/*    */   
/*    */   public static double SPD(String arg) {
/* 48 */     return Double.parseDouble(arg);
/*    */   }
/*    */   
/*    */   public static ArrayList<String> breakLis(String[] lis, int freq) {
/* 52 */     ArrayList<String> outs = new ArrayList<String>();
/*    */     
/* 54 */     int l = 0;
/* 55 */     while (l != lis.length) {
/* 56 */       String outAt = "";
/* 57 */       for (int i = 0; i < freq; i++) {
/* 58 */         outAt = outAt + lis[(l + i)] + "\001";
/*    */       }
/*    */       
/* 61 */       outs.add(outAt.substring(0, outAt.length() - 1));
/* 62 */       l += freq;
/*    */     }
/* 64 */     return outs;
/*    */   }
/*    */ }


