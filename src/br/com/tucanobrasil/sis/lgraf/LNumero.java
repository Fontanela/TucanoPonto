/*    */ package br.com.tucanobrasil.sis.lgraf;
/*    */ 
/*    */ import br.com.tucanobrasil.sis.tech.DBText;
/*    */ import br.com.tucanobrasil.sis.util.Ig;
/*    */ import br.com.tucanobrasil.sis.util.LEsc;
/*    */ import javax.swing.SwingUtilities;
/*    */ import javax.swing.event.DocumentEvent;
/*    */ import javax.swing.event.DocumentListener;
/*    */ import javax.swing.text.Document;
/*    */ 
/*    */ public class LNumero extends DBText implements DocumentListener
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public LNumero()
/*    */   {
/* 17 */     getDocument().addDocumentListener(this);
/*    */   }
/*    */   
/*    */   public LNumero(String t) {
/* 21 */     super(t);
/* 22 */     getDocument().addDocumentListener(this);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void changedUpdate(DocumentEvent arg0) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public void insertUpdate(DocumentEvent arg0)
/*    */   {
/* 34 */     regrasCod();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void removeUpdate(DocumentEvent arg0) {}
/*    */   
/*    */ 
/*    */   private void regrasCod()
/*    */   {
/* 44 */     SwingUtilities.invokeLater(new Runnable() {
/*    */       public void run() {
/* 46 */         if (!LEsc.eSoNumero(LNumero.this.getText())) {
/* 47 */           LNumero.this.setText("");
/* 48 */           Ig.erro("Somente números nesse campo.");
/* 49 */           return;
/*    */         }
/*    */       }
/*    */     });
/*    */   }
/*    */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\sis\lgraf\LNumero.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */