 package br.com.tucanobrasil.sis.util;
 
 import java.awt.Desktop;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import br.com.tucanobrasil.M;
 
 
 
 
 
 
 
 
 public class Util
 {
   public static ArrayList<String> addRangeToArr(ArrayList<String> arr, ArrayList<String> arr2, int c, int f)
   {
     for (int i = c; i < f; i++) {
       arr2.add((String)arr.get(i));
     }
     return arr2;
   }
   
   public static int[] mousePos() {
     Point p = MouseInfo.getPointerInfo().getLocation();
    return new int[] { p.x, p.y };
   }
   
   public static String getProp(String obj, String prop) {
    int ind = obj.indexOf(prop);
     
     obj = obj.substring(ind + prop.length() + 1);
     
     ind = obj.indexOf(",");
     return obj.substring(0, ind);
   }
   
   public static void openOffice(String arq) {
     String wordPadExecutable = "C:\\Program Files\\LibreOffice\\program\\soffice.exe";
     
     ProcessBuilder pb = new ProcessBuilder(new String[] { wordPadExecutable, M.SYSDIR + "\\" + arq });
     try
     {
       pb.start();
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
   
   public static void openFolder(String dir) {
     try {
       Desktop.getDesktop().open(new File(dir));
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
   
   public static void openChrome(String file) {
     try {
       Runtime.getRuntime().exec(new String[] { "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe", M.SYSDIR + "\\" + file });
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 }

