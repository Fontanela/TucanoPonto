/*    */ package br.com.tucanobrasil.sis.lgraf;
/*    */ 
/*    */ import br.com.tucanobrasil.sis.util.Ig;
/*    */ import java.awt.Color;
/*    */ import java.awt.Cursor;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import javax.swing.JLabel;
/*    */ 
/*    */ 
/*    */ public class LCheck
/*    */   extends JLabel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 15 */   public boolean checked = false;
/*    */   
/* 17 */   public LCheck() { setIcon(Ig.icone("checkoff.png"));
/* 18 */     setBackground(Color.BLACK);
/* 19 */     setOpaque(true);
/* 20 */     setCursor(Cursor.getPredefinedCursor(12));
/* 21 */     addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
/*    */       public void mousePressed(MouseEvent arg0) {}
/* 23 */       public void mouseExited(MouseEvent arg0) {} public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { if (!LCheck.this.checked) {
/* 24 */           LCheck.this.set(true);
/*    */         }
/*    */         else
/* 27 */           LCheck.this.set(false);
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */   public int intbol() {
/* 33 */     return this.checked ? 1 : 0;
/*    */   }
/*    */   
/*    */   public void set(boolean v) {
/* 37 */     if (v) {
/* 38 */       setIcon(Ig.icone("checkon.png"));
/* 39 */       this.checked = true;
/*    */     }
/*    */     else {
/* 42 */       setIcon(Ig.icone("checkoff.png"));
/* 43 */       this.checked = false;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\sis\lgraf\LCheck.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */