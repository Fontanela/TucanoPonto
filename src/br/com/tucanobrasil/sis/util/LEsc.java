 package br.com.tucanobrasil.sis.util;
 
 import java.util.ArrayList;
import javax.swing.JTextField;
 
 
 
 

 
 
 
 public class LEsc
 {
   public static String strLis(String[] s)
   {
     String mod = "";
     for (int i = 0; i < s.length; i++) {
      mod = mod + s[i] + "\001";
     }
   return mod.substring(0, mod.length() - 1);
   }
   
   public static boolean typeOfMtch(Object o, String s) {
     if (o == null) return false;
    if (typeOf(o).equals(s)) {
      return true;
     }
    return false;
   }
   
   public static String typeOf(Object o) {
    return o.getClass().getSimpleName();
   }
   
   public static String remUChr(String str) {
     if (str.length() == 0) return "";
     return str.substring(0, str.length() - 1);
   }
   
   public static String repChar(String ch, int x) {
     String r = "";
     for (int i = 0; i < x; i++) {
      r = r + ch;
     }
     return r;
   }
   
   public static ArrayList<String> arrPArr(String[] arr) {
     ArrayList<String> j = new ArrayList<String>();
     for (int i = 0; i < arr.length; i++) {
       j.add(arr[i]);
     }
     return j;
   }
   
   public static String[] cabCont(String str) {
     return new String[] { str.substring(0, 3), str.substring(3) };
   }
   
   public static int pInt(String str) {
     return Integer.parseInt(soNumeros(str));
   }
   
   public static String stndArr(ArrayList<String> arr, String sep) {
    String str = "";
     for (int i = 0; i < arr.size(); i++) {
      str = str + (String)arr.get(i) + sep;
     }
     
    if (!str.equals("")) {
      return str.substring(0, str.length() - 1);
     }
    return "";
   }
   
   public static ArrayList<String> remArr(String strArr, int freq, int ind) {
     ArrayList<String> arr = new ArrayList<String>();
     
     for (int i = 0; i < arr.size(); i += freq) {
      if (ind * freq == i) {
        for (int j = 0; j <= freq; j++) {
           if (j == freq - 1) {
             arr.remove(ind * freq);
            break;
           }
           arr.remove(ind * freq);
         }
       }
     }
    return arr;
   }
   
   public static String sTextos(JTextField[] sText) {
     String s = "";
  for (int i = 0; i < sText.length; i++) {
       String c = lastCharArr(1, sText.length, i, "\001");
       s = s + sText[i].getText() + c;
     }
     return s;
   }
   
   public static ArrayList<String> modeloVazio(int freq) {
     ArrayList<String> mod = new ArrayList<String>();
     
     for (int i = 0; i < freq; i++) {
       mod.add("");
     }
     return mod;
   }
   
   public static String lastCharArr(int freq, int tot, int ind, String ch) {
    if (ind == tot - freq) {
       return "";
     }
     return ch;
   }
   
   public static String soNumeros(String q) {
    return q.replaceAll("\\D+", "");
   }
   
   public static boolean eSoNumero(String q) {
    if (q.matches("[0-9]+")) {
       return true;
     }
    return false;
   }
   
   public static boolean isDouble(String q) {
     if (q.matches("^\\d+(\\.\\d+)*$")) {
     return true;
     }
     return false;
   }
   
   public static String arrPStr(ArrayList<String> arr) {
     String str = "";
   for (int i = 0; i < arr.size(); i++) {
      str = str + (String)arr.get(i);
     }
     return str;
   }
   
 
   public static String[] spl(String str, String padr) { return str.split(padr, -1); }
   
   public static ArrayList<String> strPArr(String str, boolean vaziaColuna) {
     String[] dados;
 
     if (vaziaColuna) {
       dados = str.split("\001", -1);
     }
     else
     {
      dados = str.split("\001");
     }
     
     if (dados.length == 1) {
       if (dados[0].equals("")) {
         return new ArrayList<String>();
       }
       ArrayList<String> r = new ArrayList<String>();
       r.add(dados[0]);
      return r;
     }
     
     ArrayList<String> r = new ArrayList<String>();
     for (int i = 0; i < dados.length; i++) {
     r.add(dados[i]);
     }
   return r;
   }
   
   public static ArrayList<String> strPArr(String str) {
     String[] dados = str.split("\001", -1);
     
    if (dados.length == 1) {
       if (dados[0].equals("")) {
         return new ArrayList<String>();
       }
       ArrayList<String> r = new ArrayList<String>();
       r.add(dados[0]);
      return r;
     }
     
     ArrayList<String> r = new ArrayList<String>();
     for (int i = 0; i < dados.length; i++) {
       r.add(dados[i]);
     }
     return r;
   }
 }
