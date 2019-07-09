 package br.com.tucanobrasil.sis.lgraf;
 
 import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Timer;
import java.util.TimerTask;
import br.com.tucanobrasil.sis.tech.DBText;
import br.com.tucanobrasil.sis.util.LEsc;
 
 public class LDin
   extends DBText implements FocusListener
 {
   private static final long serialVersionUID = 1L;
   
   public LDin()
   {
    addFocusListener(this);
     
   timer();
   }
   
   public LDin(String t) {
     super(t);
     
     timer();
   }
   
 
 
   public void focusGained(FocusEvent arg0) {}
   
 
 
   public void focusLost(FocusEvent arg0)
   {
    String val = getText();
     
     if (!LEsc.isDouble(val)) { setText("");return;
     }
     int iVir = val.indexOf(".");
     if (iVir == -1) {
       setText(val + ".00");
 
     }
     else if (val.charAt(val.length() - 1) == ".".charAt(0)) {
       setText("");
     }
   }
   
 
   public boolean valido = false;
   
   private void timer() { new Timer().schedule(new TimerTask() {
       public void run() {
         String val = LDin.this.getText();
         
        if (val.length() == 0) { LDin.this.valido = false;LDin.this.setBackground(new Color(255, 53, 53));return; }
         if (!LEsc.isDouble(val)) { LDin.this.valido = false;LDin.this.setBackground(new Color(255, 53, 53));return; }
        if (val.charAt(val.length() - 1) == ".".charAt(0)) { LDin.this.valido = false;LDin.this.setBackground(new Color(255, 53, 53));return;
         }
         LDin.this.valido = true;LDin.this.setBackground(Color.WHITE);
       }
     }, 1L, 33L);
   }
 }

