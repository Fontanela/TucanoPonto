/*    */ package br.com.tucanobrasil.sis.util;
/*    */ 
/*    */ //import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ //import java.util.Arrays;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StrLis
/*    */ {
/*    */   private String lis;
/*    */   private int freq;
/*    */   
/*    */   public StrLis(String lis, int freq)
/*    */   {
/* 20 */     this.lis = lis;
/* 21 */     this.freq = freq;
/*    */   }
/*    */   
/*    */   public void print() {
/* 25 */     System.out.println(">> " + this.lis);
/*    */   }
/*    */   
/*    */   public ArrayList<String> pArr() {
/* 29 */     return new ArrayList<String>();
/*    */   }
/*    */   
/*    */   public int tamanho() {
/* 33 */     ArrayList<String> arr = new ArrayList<String>();
/* 34 */     return arr.size();
/*    */   }
/*    */   
/*    */   public void adi(String str) {
/* 38 */     this.lis += str;
/* 39 */     this.lis = this.lis.substring(0, this.lis.length());
/*    */   }
/*    */   
/*    */   public void alterar(String val, int ind) {
/* 43 */     ArrayList<String> arr = new ArrayList<String>();
/*    */     
/* 45 */     if (ind == 0) {
/* 46 */       arr.set(ind, val + "\001");
/*    */     }
/* 48 */     else if (ind == tamanho() - 1) {
/* 49 */       arr.set(ind, "\001" + val);
/*    */     }
/*    */     else {
/* 52 */       arr.set(ind, "\001" + val + "\001");
/*    */     }
/*    */     
/* 55 */     this.lis = LEsc.arrPStr(arr);
/*    */   }
/*    */   
/*    */   public String ler(int ind) {
/* 59 */     ArrayList<String> arr = new ArrayList<String>();
/*    */     
/* 61 */     return (String)arr.get(ind);
/*    */   }
/*    */   
/*    */   public void rem(int ind) {
/* 65 */     ArrayList<String> arr = new ArrayList<String>();
/*    */     
/* 67 */     for (int i = 0; i < arr.size(); i += this.freq) {
/* 68 */       if (ind * this.freq == i) {
/* 69 */         for (int j = 0; j <= this.freq; j++) {
/* 70 */           if (j == this.freq - 1) {
/* 71 */             arr.remove(ind * this.freq);
/* 72 */             break;
/*    */           }
/* 74 */           arr.remove(ind * this.freq);
/*    */         }
/*    */       }
/*    */     }
/* 78 */     this.lis = LEsc.stndArr(arr, "\001");
/*    */   }
/*    */ }

