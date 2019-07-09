 package br.com.tucanobrasil.sis.lgraf;
 
 import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import br.com.tucanobrasil.sis.tech.DBText;
import br.com.tucanobrasil.sis.util.Ig;
import br.com.tucanobrasil.sis.util.LEsc;
 
 public class LIden extends DBText implements DocumentListener
 {
   private static final long serialVersionUID = 1L;
   
   public LIden()
   {
     getDocument().addDocumentListener(this);
   }
   
   public LIden(String t) {
     super(t);
    getDocument().addDocumentListener(this);
   }
   
 
   public void changedUpdate(DocumentEvent arg0)
   {
    regrasCod();
   }
   
 
   public void insertUpdate(DocumentEvent arg0)
   {
     regrasCod();
   }
   
 
 
   public void removeUpdate(DocumentEvent arg0) {}
   
 
   private void regrasCod()
   {
     SwingUtilities.invokeLater(new Runnable() {
       public void run() {
         if (!LEsc.eSoNumero(LIden.this.getText())) {
          LIden.this.setText("");
           Ig.erro("Somente números nesse campo.");
           return;
         }
         
         if (LIden.this.getText().length() > 14) {
          LIden.this.setText(LIden.this.getText().substring(0, 13));
         }
       }
     });
   }
 }

