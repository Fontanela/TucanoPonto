/*    */ package br.com.tucanobrasil;
/*    */ 
/*    */ import br.com.tucanobrasil.sis.util.LEsc;
/*    */ 
/*    */ public class O
/*    */ {
/*    */   public static void p(String s) {
/*  8 */     System.out.println(s);
/*    */   }
/*    */   
/*    */   public static String nextCol(String val, int space) {
/* 12 */     return val + LEsc.repChar(" ", space - val.length());
/*    */   }
/*    */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\O.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */