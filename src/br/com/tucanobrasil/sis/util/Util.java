/*    */ package br.com.tucanobrasil.sis.util;
/*    */ 
/*    */ import br.com.tucanobrasil.M;
/*    */ import java.awt.Desktop;
/*    */ import java.awt.MouseInfo;
/*    */ import java.awt.Point;
/*    */// import java.awt.PointerInfo;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Util
/*    */ {
/*    */   public static ArrayList<String> addRangeToArr(ArrayList<String> arr, ArrayList<String> arr2, int c, int f)
/*    */   {
/* 23 */     for (int i = c; i < f; i++) {
/* 24 */       arr2.add((String)arr.get(i));
/*    */     }
/* 26 */     return arr2;
/*    */   }
/*    */   
/*    */   public static int[] mousePos() {
/* 30 */     Point p = MouseInfo.getPointerInfo().getLocation();
/* 31 */     return new int[] { p.x, p.y };
/*    */   }
/*    */   
/*    */   public static String getProp(String obj, String prop) {
/* 35 */     int ind = obj.indexOf(prop);
/*    */     
/* 37 */     obj = obj.substring(ind + prop.length() + 1);
/*    */     
/* 39 */     ind = obj.indexOf(",");
/* 40 */     return obj.substring(0, ind);
/*    */   }
/*    */   
/*    */   public static void openOffice(String arq) {
/* 44 */     String wordPadExecutable = "C:\\Program Files\\LibreOffice\\program\\soffice.exe";
/*    */     
/* 46 */     ProcessBuilder pb = new ProcessBuilder(new String[] { wordPadExecutable, M.SYSDIR + "\\" + arq });
/*    */     try
/*    */     {
/* 49 */       pb.start();
/*    */     } catch (IOException e) {
/* 51 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   public static void openFolder(String dir) {
/*    */     try {
/* 57 */       Desktop.getDesktop().open(new File(dir));
/*    */     } catch (IOException e) {
/* 59 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   public static void openChrome(String file) {
/*    */     try {
/* 65 */       Runtime.getRuntime().exec(new String[] { "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe", M.SYSDIR + "\\" + file });
/*    */     } catch (IOException e) {
/* 67 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

