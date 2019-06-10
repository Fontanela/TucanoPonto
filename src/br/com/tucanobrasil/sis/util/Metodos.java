/*     */ package br.com.tucanobrasil.sis.util;
/*     */ 
/*     */ import br.com.tucanobrasil.sis.rede.Rede;
/*     */ //import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Metodos
/*     */ {
/*     */   public static boolean regEx(int ind)
/*     */   {
/*  19 */     ArrayList<String> res = Rede.httpArr("CTP");
/*  20 */     System.out.println(res);
/*  21 */     if (res.size() == 0) {
/*  22 */       return false;
/*     */     }
/*  24 */     if (((String)res.get(ind)).equals("")) {
/*  25 */       return false;
/*     */     }
/*  27 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean cheData(String data) {
/*  31 */     String v = data;
/*     */     
/*  33 */     if (v.length() != 10) { return false;
/*     */     }
/*  35 */     String d = v.substring(0, 2);
/*  36 */     String m = v.substring(3, 5);
/*  37 */     String a = v.substring(6, 8);
/*     */     
/*  39 */     if ((!LEsc.eSoNumero(d)) || (!LEsc.eSoNumero(m)) || (!LEsc.eSoNumero(a))) {
/*  40 */       return false;
/*     */     }
/*  42 */     return true;
/*     */   }
/*     */   
/*     */   public static String din(String soNum) {
/*  46 */     if (soNum.length() == 0) {
/*  47 */       return "0";
/*     */     }
/*     */     
/*  50 */     if (soNum.length() == 1) {
/*  51 */       soNum = "0.0" + soNum;
/*     */       
/*  53 */       return soNum;
/*     */     }
/*     */     
/*  56 */     if (soNum.length() == 2) {
/*  57 */       return "0." + soNum;
/*     */     }
/*     */     
/*  60 */     if (soNum.length() == 3) {
/*  61 */       String c = soNum.substring(1, 3);
/*  62 */       String r = soNum.substring(0, 1);
/*  63 */       soNum = r + "." + c;
/*     */       
/*  65 */       return soNum;
/*     */     }
/*     */     
/*  68 */     if (soNum.length() == 4) {
/*  69 */       String c = soNum.substring(2, 4);
/*  70 */       String r = soNum.substring(0, 2);
/*  71 */       soNum = r + "." + c;
/*     */       
/*  73 */       return soNum;
/*     */     }
/*     */     
/*  76 */     if (soNum.length() == 5) {
/*  77 */       String c = soNum.substring(3, 5);
/*  78 */       String r = soNum.substring(0, 3);
/*  79 */       soNum = r + "." + c;
/*     */       
/*  81 */       return soNum;
/*     */     }
/*     */     
/*  84 */     if (soNum.length() == 6) {
/*  85 */       String c = soNum.substring(4, 6);
/*  86 */       String r = soNum.substring(1, 4);
/*  87 */       String m = soNum.substring(0, 1);
/*  88 */       soNum = m + r + "." + c;
/*     */       
/*  90 */       return soNum;
/*     */     }
/*     */     
/*  93 */     if (soNum.length() == 7) {
/*  94 */       String c = soNum.substring(5, 7);
/*  95 */       String r = soNum.substring(2, 5);
/*  96 */       String m = soNum.substring(0, 2);
/*  97 */       soNum = m + r + "." + c;
/*  98 */       return soNum;
/*     */     }
/*     */     
/* 101 */     if (soNum.length() == 8) {
/* 102 */       String c = soNum.substring(6, 8);
/* 103 */       String r = soNum.substring(3, 6);
/* 104 */       String m = soNum.substring(0, 3);
/* 105 */       soNum = m + r + "." + c;
/*     */       
/* 107 */       return soNum;
/*     */     }
/*     */     
/* 110 */     if (soNum.length() == 9) {
/* 111 */       String c = soNum.substring(7, 9);
/* 112 */       String r = soNum.substring(4, 7);
/* 113 */       String m = soNum.substring(1, 4);
/* 114 */       String mm = soNum.substring(0, 1);
/* 115 */       soNum = mm + m + r + "." + c;
/*     */       
/* 117 */       return soNum;
/*     */     }
/*     */     
/*     */ 
/* 121 */     return null;
/*     */   }
/*     */   
/*     */   public static String fDin(String soNum) {
/* 125 */     if (soNum.length() == 0) {
/* 126 */       soNum = "R$ ";
/* 127 */       return soNum;
/*     */     }
/*     */     
/* 130 */     if (soNum.length() == 1) {
/* 131 */       soNum = "R$ " + soNum;
/*     */       
/* 133 */       return soNum;
/*     */     }
/*     */     
/* 136 */     if (soNum.length() == 2) {
/* 137 */       soNum = "R$ " + soNum;
/*     */       
/* 139 */       return soNum;
/*     */     }
/*     */     
/* 142 */     if (soNum.length() == 3) {
/* 143 */       String c = soNum.substring(1, 3);
/* 144 */       String r = soNum.substring(0, 1);
/* 145 */       soNum = "R$ " + r + "," + c;
/*     */       
/* 147 */       return soNum;
/*     */     }
/*     */     
/* 150 */     if (soNum.length() == 4) {
/* 151 */       String c = soNum.substring(2, 4);
/* 152 */       String r = soNum.substring(0, 2);
/* 153 */       soNum = "R$ " + r + "," + c;
/*     */       
/* 155 */       return soNum;
/*     */     }
/*     */     
/* 158 */     if (soNum.length() == 5) {
/* 159 */       String c = soNum.substring(3, 5);
/* 160 */       String r = soNum.substring(0, 3);
/* 161 */       soNum = "R$ " + r + "," + c;
/*     */       
/* 163 */       return soNum;
/*     */     }
/*     */     
/* 166 */     if (soNum.length() == 6) {
/* 167 */       String c = soNum.substring(4, 6);
/* 168 */       String r = soNum.substring(1, 4);
/* 169 */       String m = soNum.substring(0, 1);
/* 170 */       soNum = "R$ " + m + "." + r + "," + c;
/*     */       
/* 172 */       return soNum;
/*     */     }
/*     */     
/* 175 */     if (soNum.length() == 7) {
/* 176 */       String c = soNum.substring(5, 7);
/* 177 */       String r = soNum.substring(2, 5);
/* 178 */       String m = soNum.substring(0, 2);
/* 179 */       soNum = "R$ " + m + "." + r + "," + c;
/*     */     }
/*     */     
/* 182 */     if (soNum.length() == 8) {
/* 183 */       String c = soNum.substring(6, 8);
/* 184 */       String r = soNum.substring(3, 6);
/* 185 */       String m = soNum.substring(0, 3);
/* 186 */       soNum = "R$ " + m + "." + r + "," + c;
/*     */       
/* 188 */       return soNum;
/*     */     }
/*     */     
/* 191 */     if (soNum.length() == 9) {
/* 192 */       String c = soNum.substring(7, 9);
/* 193 */       String r = soNum.substring(4, 7);
/* 194 */       String m = soNum.substring(1, 4);
/* 195 */       String mm = soNum.substring(0, 1);
/* 196 */       soNum = "R$ " + mm + "." + m + "." + r + "," + c;
/*     */       
/* 198 */       return soNum;
/*     */     }
/*     */     
/*     */ 
/* 202 */     return null;
/*     */   }
/*     */ }


