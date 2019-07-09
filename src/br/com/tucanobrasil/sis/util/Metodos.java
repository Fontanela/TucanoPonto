 package br.com.tucanobrasil.sis.util;
 
 import java.util.ArrayList;
import br.com.tucanobrasil.sis.rede.Rede;
 
 

 public class Metodos
 {
   public static boolean regEx(int ind)
   {
    ArrayList<String> res = Rede.httpArr("CTP");
    System.out.println(res);
     if (res.size() == 0) {
       return false;
     }
    if (((String)res.get(ind)).equals("")) {
       return false;
     }
     return true;
   }
   
   public static boolean cheData(String data) {
     String v = data;
     
    if (v.length() != 10) { return false;
     }
   String d = v.substring(0, 2);
    String m = v.substring(3, 5);
     String a = v.substring(6, 8);
     
     if ((!LEsc.eSoNumero(d)) || (!LEsc.eSoNumero(m)) || (!LEsc.eSoNumero(a))) {
      return false;
     }
     return true;
   }
   
   public static String din(String soNum) {
    if (soNum.length() == 0) {
       return "0";
     }
     
     if (soNum.length() == 1) {
      soNum = "0.0" + soNum;
       
       return soNum;
     }
     
     if (soNum.length() == 2) {
     return "0." + soNum;
     }
     
     if (soNum.length() == 3) {
      String c = soNum.substring(1, 3);
       String r = soNum.substring(0, 1);
      soNum = r + "." + c;
       
       return soNum;
     }
     
     if (soNum.length() == 4) {
      String c = soNum.substring(2, 4);
      String r = soNum.substring(0, 2);
     soNum = r + "." + c;
       
       return soNum;
     }
     
    if (soNum.length() == 5) {
      String c = soNum.substring(3, 5);
       String r = soNum.substring(0, 3);
       soNum = r + "." + c;
       
       return soNum;
     }
     
     if (soNum.length() == 6) {
      String c = soNum.substring(4, 6);
     String r = soNum.substring(1, 4);
     String m = soNum.substring(0, 1);
       soNum = m + r + "." + c;
       
      return soNum;
     }
     
     if (soNum.length() == 7) {
      String c = soNum.substring(5, 7);
       String r = soNum.substring(2, 5);
      String m = soNum.substring(0, 2);
       soNum = m + r + "." + c;
      return soNum;
     }
     
     if (soNum.length() == 8) {
       String c = soNum.substring(6, 8);
       String r = soNum.substring(3, 6);
      String m = soNum.substring(0, 3);
      soNum = m + r + "." + c;
       
      return soNum;
     }
     
    if (soNum.length() == 9) {
       String c = soNum.substring(7, 9);
       String r = soNum.substring(4, 7);
       String m = soNum.substring(1, 4);
      String mm = soNum.substring(0, 1);
       soNum = mm + m + r + "." + c;
       
      return soNum;
     }
     
 
    return null;
   }
   
   public static String fDin(String soNum) {
     if (soNum.length() == 0) {
       soNum = "R$ ";
       return soNum;
     }
     
    if (soNum.length() == 1) {
      soNum = "R$ " + soNum;
       
       return soNum;
     }
     
     if (soNum.length() == 2) {
      soNum = "R$ " + soNum;
       
      return soNum;
     }
     
    if (soNum.length() == 3) {
       String c = soNum.substring(1, 3);
       String r = soNum.substring(0, 1);
      soNum = "R$ " + r + "," + c;
       
       return soNum;
     }
     
     if (soNum.length() == 4) {
       String c = soNum.substring(2, 4);
      String r = soNum.substring(0, 2);
       soNum = "R$ " + r + "," + c;
       
     return soNum;
     }
     
     if (soNum.length() == 5) {
       String c = soNum.substring(3, 5);
     String r = soNum.substring(0, 3);
       soNum = "R$ " + r + "," + c;
       
       return soNum;
     }
     
     if (soNum.length() == 6) {
       String c = soNum.substring(4, 6);
       String r = soNum.substring(1, 4);
       String m = soNum.substring(0, 1);
       soNum = "R$ " + m + "." + r + "," + c;
       
       return soNum;
     }
     
    if (soNum.length() == 7) {
       String c = soNum.substring(5, 7);
      String r = soNum.substring(2, 5);
       String m = soNum.substring(0, 2);
       soNum = "R$ " + m + "." + r + "," + c;
     }
     
     if (soNum.length() == 8) {
       String c = soNum.substring(6, 8);
       String r = soNum.substring(3, 6);
       String m = soNum.substring(0, 3);
       soNum = "R$ " + m + "." + r + "," + c;
       
       return soNum;
     }
     
    if (soNum.length() == 9) {
     String c = soNum.substring(7, 9);
       String r = soNum.substring(4, 7);
      String m = soNum.substring(1, 4);
       String mm = soNum.substring(0, 1);
       soNum = "R$ " + mm + "." + m + "." + r + "," + c;
       
       return soNum;
     }
     
 
     return null;
   }
 }


