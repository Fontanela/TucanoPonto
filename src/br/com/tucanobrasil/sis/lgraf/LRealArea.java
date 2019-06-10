/*    */ package br.com.tucanobrasil.sis.lgraf;
/*    */ 
/*    */ import java.awt.event.FocusEvent;
/*    */ import java.awt.event.FocusListener;
/*    */ import javax.swing.JTextArea;
/*    */ import javax.swing.text.Document;
/*    */ 
/*    */ public class LRealArea extends JTextArea implements FocusListener
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public LRealArea()
/*    */   {
/* 14 */     getDocument().putProperty("filterNewlines", Boolean.TRUE);
/* 15 */     setLineWrap(true);
/* 16 */     addFocusListener(this);
/*    */   }
/*    */   
/*    */   public LRealArea(String arg0) {
/* 20 */     super(arg0);
/* 21 */     getDocument().putProperty("filterNewlines", Boolean.TRUE);
/* 22 */     setLineWrap(true);
/* 23 */     addFocusListener(this);
/*    */   }
/*    */   
/*    */   public void focusGained(FocusEvent arg0) {}
/*    */   
/*    */   public void focusLost(FocusEvent arg0)
/*    */   {
/*    */     try
/*    */     {
/* 32 */       if (getText().length() > 0) {
/* 33 */         setText(getText().substring(0, getText().length()));
/*    */       }
/*    */     }
/*    */     catch (NullPointerException e) {
/* 37 */       e.printStackTrace();
/*    */     }
/*    */     catch (Exception e) {
/* 40 */       e.printStackTrace();
/* 41 */       System.out.println("EXCEPTION E");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\sis\lgraf\LRealArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */