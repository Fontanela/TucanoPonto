/*     */ package br.com.tucanobrasil.sis.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */// import java.util.Arrays;
/*     */ import javax.swing.JTextField;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LEsc
/*     */ {
/*     */   public static String strLis(String[] s)
/*     */   {
/*  28 */     String mod = "";
/*  29 */     for (int i = 0; i < s.length; i++) {
/*  30 */       mod = mod + s[i] + "\001";
/*     */     }
/*  32 */     return mod.substring(0, mod.length() - 1);
/*     */   }
/*     */   
/*     */   public static boolean typeOfMtch(Object o, String s) {
/*  36 */     if (o == null) return false;
/*  37 */     if (typeOf(o).equals(s)) {
/*  38 */       return true;
/*     */     }
/*  40 */     return false;
/*     */   }
/*     */   
/*     */   public static String typeOf(Object o) {
/*  44 */     return o.getClass().getSimpleName();
/*     */   }
/*     */   
/*     */   public static String remUChr(String str) {
/*  48 */     if (str.length() == 0) return "";
/*  49 */     return str.substring(0, str.length() - 1);
/*     */   }
/*     */   
/*     */   public static String repChar(String ch, int x) {
/*  53 */     String r = "";
/*  54 */     for (int i = 0; i < x; i++) {
/*  55 */       r = r + ch;
/*     */     }
/*  57 */     return r;
/*     */   }
/*     */   
/*     */   public static ArrayList<String> arrPArr(String[] arr) {
/*  61 */     ArrayList<String> j = new ArrayList<String>();
/*  62 */     for (int i = 0; i < arr.length; i++) {
/*  63 */       j.add(arr[i]);
/*     */     }
/*  65 */     return j;
/*     */   }
/*     */   
/*     */   public static String[] cabCont(String str) {
/*  69 */     return new String[] { str.substring(0, 3), str.substring(3) };
/*     */   }
/*     */   
/*     */   public static int pInt(String str) {
/*  73 */     return Integer.parseInt(soNumeros(str));
/*     */   }
/*     */   
/*     */   public static String stndArr(ArrayList<String> arr, String sep) {
/*  77 */     String str = "";
/*  78 */     for (int i = 0; i < arr.size(); i++) {
/*  79 */       str = str + (String)arr.get(i) + sep;
/*     */     }
/*     */     
/*  82 */     if (!str.equals("")) {
/*  83 */       return str.substring(0, str.length() - 1);
/*     */     }
/*  85 */     return "";
/*     */   }
/*     */   
/*     */   public static ArrayList<String> remArr(String strArr, int freq, int ind) {
/*  89 */     ArrayList<String> arr = new ArrayList<String>();
/*     */     
/*  91 */     for (int i = 0; i < arr.size(); i += freq) {
/*  92 */       if (ind * freq == i) {
/*  93 */         for (int j = 0; j <= freq; j++) {
/*  94 */           if (j == freq - 1) {
/*  95 */             arr.remove(ind * freq);
/*  96 */             break;
/*     */           }
/*  98 */           arr.remove(ind * freq);
/*     */         }
/*     */       }
/*     */     }
/* 102 */     return arr;
/*     */   }
/*     */   
/*     */   public static String sTextos(JTextField[] sText) {
/* 106 */     String s = "";
/* 107 */     for (int i = 0; i < sText.length; i++) {
/* 108 */       String c = lastCharArr(1, sText.length, i, "\001");
/* 109 */       s = s + sText[i].getText() + c;
/*     */     }
/* 111 */     return s;
/*     */   }
/*     */   
/*     */   public static ArrayList<String> modeloVazio(int freq) {
/* 115 */     ArrayList<String> mod = new ArrayList<String>();
/*     */     
/* 117 */     for (int i = 0; i < freq; i++) {
/* 118 */       mod.add("");
/*     */     }
/* 120 */     return mod;
/*     */   }
/*     */   
/*     */   public static String lastCharArr(int freq, int tot, int ind, String ch) {
/* 124 */     if (ind == tot - freq) {
/* 125 */       return "";
/*     */     }
/* 127 */     return ch;
/*     */   }
/*     */   
/*     */   public static String soNumeros(String q) {
/* 131 */     return q.replaceAll("\\D+", "");
/*     */   }
/*     */   
/*     */   public static boolean eSoNumero(String q) {
/* 135 */     if (q.matches("[0-9]+")) {
/* 136 */       return true;
/*     */     }
/* 138 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isDouble(String q) {
/* 142 */     if (q.matches("^\\d+(\\.\\d+)*$")) {
/* 143 */       return true;
/*     */     }
/* 145 */     return false;
/*     */   }
/*     */   
/*     */   public static String arrPStr(ArrayList<String> arr) {
/* 149 */     String str = "";
/* 150 */     for (int i = 0; i < arr.size(); i++) {
/* 151 */       str = str + (String)arr.get(i);
/*     */     }
/* 153 */     return str;
/*     */   }
/*     */   
/*     */ 
/* 157 */   public static String[] spl(String str, String padr) { return str.split(padr, -1); }
/*     */   
/*     */   public static ArrayList<String> strPArr(String str, boolean vaziaColuna) {
/*     */     String[] dados;
/*   
/* 162 */     if (vaziaColuna) {
/* 163 */       dados = str.split("\001", -1);
/*     */     }
/*     */     else
/*     */     {
/* 167 */       dados = str.split("\001");
/*     */     }
/*     */     
/* 170 */     if (dados.length == 1) {
/* 171 */       if (dados[0].equals("")) {
/* 172 */         return new ArrayList<String>();
/*     */       }
/* 174 */       ArrayList<String> r = new ArrayList<String>();
/* 175 */       r.add(dados[0]);
/* 176 */       return r;
/*     */     }
/*     */     
/* 179 */     ArrayList<String> r = new ArrayList<String>();
/* 180 */     for (int i = 0; i < dados.length; i++) {
/* 181 */       r.add(dados[i]);
/*     */     }
/* 183 */     return r;
/*     */   }
/*     */   
/*     */   public static ArrayList<String> strPArr(String str) {
/* 187 */     String[] dados = str.split("\001", -1);
/*     */     
/* 189 */     if (dados.length == 1) {
/* 190 */       if (dados[0].equals("")) {
/* 191 */         return new ArrayList<String>();
/*     */       }
/* 193 */       ArrayList<String> r = new ArrayList<String>();
/* 194 */       r.add(dados[0]);
/* 195 */       return r;
/*     */     }
/*     */     
/* 198 */     ArrayList<String> r = new ArrayList<String>();
/* 199 */     for (int i = 0; i < dados.length; i++) {
/* 200 */       r.add(dados[i]);
/*     */     }
/* 202 */     return r;
/*     */   }
/*     */ }
