 package br.com.tucanobrasil.sis.lgraf;
 
 import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextArea;

 
 public class LRealArea extends JTextArea implements FocusListener
 {
   private static final long serialVersionUID = 1L;
   
   public LRealArea()
   {
     getDocument().putProperty("filterNewlines", Boolean.TRUE);
     setLineWrap(true);
     addFocusListener(this);
   }
   
   public LRealArea(String arg0) {
     super(arg0);
    getDocument().putProperty("filterNewlines", Boolean.TRUE);
     setLineWrap(true);
     addFocusListener(this);
   }
   
   public void focusGained(FocusEvent arg0) {}
   
   public void focusLost(FocusEvent arg0)
   {
     try
     {
      if (getText().length() > 0) {
         setText(getText().substring(0, getText().length()));
       }
     }
     catch (NullPointerException e) {
      e.printStackTrace();
     }
     catch (Exception e) {
      e.printStackTrace();
       System.out.println("EXCEPTION E");
     }
   }
 }
