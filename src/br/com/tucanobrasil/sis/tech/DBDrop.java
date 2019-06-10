/*    */ package br.com.tucanobrasil.sis.tech;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.JComboBox;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DBDrop
/*    */   extends JComboBox<Object>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public String curr;
/* 20 */   public ArrayList<String> ids = new ArrayList();
/*    */   
/*    */   public String getCurrValue()
/*    */   {
/* 24 */     int s = getSelectedIndex();
/* 25 */     if (s == -1) return "0";
/* 26 */     return (String)this.ids.get(s);
/*    */   }
/*    */   
/*    */   public void setIndexByRegister(String r) {
/* 30 */     for (int i = 0; i < this.ids.size(); i++) {
/* 31 */       if (((String)this.ids.get(i)).equals(r)) {
/* 32 */         setSelectedIndex(i);return;
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void setIndexByCurrValue() {
/* 38 */     for (int i = 0; i < this.ids.size(); i++) {
/* 39 */       if (((String)this.ids.get(i)).equals(this.curr)) {
/* 40 */         setSelectedIndex(i);return;
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void addRow(String id, String val) {
/* 46 */     this.ids.add(id);
/* 47 */     addItem(val);
/*    */   }
/*    */   
/*    */   public void remRow(int r) {
/* 51 */     this.ids.remove(r);
/* 52 */     remove(r);
/*    */   }
/*    */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\sis\tech\DBDrop.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */