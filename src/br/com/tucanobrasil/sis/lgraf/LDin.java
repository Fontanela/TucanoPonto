/*    */ package br.com.tucanobrasil.sis.lgraf;
/*    */ 
/*    */ import br.com.tucanobrasil.sis.tech.DBText;
/*    */ import br.com.tucanobrasil.sis.util.LEsc;
/*    */ import java.awt.Color;
/*    */ import java.awt.event.FocusEvent;
/*    */ import java.awt.event.FocusListener;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ 
/*    */ public class LDin
/*    */   extends DBText implements FocusListener
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public LDin()
/*    */   {
/* 18 */     addFocusListener(this);
/*    */     
/* 20 */     timer();
/*    */   }
/*    */   
/*    */   public LDin(String t) {
/* 24 */     super(t);
/*    */     
/* 26 */     timer();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void focusGained(FocusEvent arg0) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public void focusLost(FocusEvent arg0)
/*    */   {
/* 37 */     String val = getText();
/*    */     
/* 39 */     if (!LEsc.isDouble(val)) { setText("");return;
/*    */     }
/* 41 */     int iVir = val.indexOf(".");
/* 42 */     if (iVir == -1) {
/* 43 */       setText(val + ".00");
/*    */ 
/*    */     }
/* 46 */     else if (val.charAt(val.length() - 1) == ".".charAt(0)) {
/* 47 */       setText("");
/*    */     }
/*    */   }
/*    */   
/*    */ 
/* 52 */   public boolean valido = false;
/*    */   
/* 54 */   private void timer() { new Timer().schedule(new TimerTask() {
/*    */       public void run() {
/* 56 */         String val = LDin.this.getText();
/*    */         
/* 58 */         if (val.length() == 0) { LDin.this.valido = false;LDin.this.setBackground(new Color(255, 53, 53));return; }
/* 59 */         if (!LEsc.isDouble(val)) { LDin.this.valido = false;LDin.this.setBackground(new Color(255, 53, 53));return; }
/* 60 */         if (val.charAt(val.length() - 1) == ".".charAt(0)) { LDin.this.valido = false;LDin.this.setBackground(new Color(255, 53, 53));return;
/*    */         }
/* 62 */         LDin.this.valido = true;LDin.this.setBackground(Color.WHITE);
/*    */       }
/* 64 */     }, 1L, 33L);
/*    */   }
/*    */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\sis\lgraf\LDin.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */