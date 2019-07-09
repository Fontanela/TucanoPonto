 package br.com.tucanobrasil.sis.lgraf;
 
 import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import br.com.tucanobrasil.sis.util.Ig;
 
 
 public class LCheck
   extends JLabel
 {
   private static final long serialVersionUID = 1L;
   public boolean checked = false;
   
   public LCheck() { setIcon(Ig.icone("checkoff.png"));
     setBackground(Color.BLACK);
     setOpaque(true);
     setCursor(Cursor.getPredefinedCursor(12));
     addMouseListener(new MouseListener() { public void mouseReleased(MouseEvent arg0) {}
       public void mousePressed(MouseEvent arg0) {}
      public void mouseExited(MouseEvent arg0) {} public void mouseEntered(MouseEvent arg0) {} public void mouseClicked(MouseEvent arg0) { if (!LCheck.this.checked) {
           LCheck.this.set(true);
         }
         else
           LCheck.this.set(false);
       }
     });
   }
   
   public int intbol() {
     return this.checked ? 1 : 0;
   }
   
   public void set(boolean v) {
    if (v) {
       setIcon(Ig.icone("checkon.png"));
      this.checked = true;
     }
     else {
       setIcon(Ig.icone("checkoff.png"));
       this.checked = false;
     }
   }
 }


