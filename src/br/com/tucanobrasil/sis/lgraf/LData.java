/*    */ package br.com.tucanobrasil.sis.lgraf;
/*    */ 
/*    */ import br.com.tucanobrasil.sis.tech.DBText;
/*    */ import br.com.tucanobrasil.sis.util.Metodos;
/*    */ import br.com.tucanobrasil.sis.util.Tempo;
/*    */ import java.awt.Color;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ 
/*    */ public class LData
/*    */   extends DBText
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 14 */   int u = -1;
/*    */   
/*    */   public boolean valido;
/*    */   
/*    */ 
/*    */   public LData()
/*    */   {
/* 21 */     sched();
/*    */   }
/*    */   
/*    */   public LData(String tex) {
/* 25 */     super(tex);
/*    */     
/* 27 */     sched();
/*    */   }
/*    */   
/*    */   public String ame() {
/* 31 */     return Tempo.brasPAme(getText());
/*    */   }
/*    */   
/*    */   public boolean isValidd() {
/* 35 */     if (!Metodos.cheData(getText())) {
/* 36 */       return false;
/*    */     }
/* 38 */     return true;
/*    */   }
/*    */   
/*    */   private void sched() {
/* 42 */     new Timer().schedule(new TimerTask() {
/*    */       public void run() {
/* 44 */         if (!Metodos.cheData(LData.this.getText())) {
/* 45 */           LData.this.setBackground(new Color(255, 53, 53));
/* 46 */           LData.this.valido = false;
/*    */         }
/*    */         else {
/* 49 */           LData.this.setBackground(Color.WHITE);
/* 50 */           LData.this.valido = true;
/*    */         }
/*    */       }
/* 53 */     }, 33L, 33L);
/*    */   }
/*    */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\sis\lgraf\LData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */