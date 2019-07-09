 package br.com.tucanobrasil.sis.lgraf;
 
 import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import br.com.tucanobrasil.sis.tech.DBText;
import br.com.tucanobrasil.sis.util.Metodos;
import br.com.tucanobrasil.sis.util.Tempo;
 
 public class LData
   extends DBText
 {
   private static final long serialVersionUID = 1L;
   int u = -1;
   
   public boolean valido;
   
 
   public LData()
   {
     sched();
   }
   
   public LData(String tex) {
    super(tex);
     
     sched();
   }
   
   public String ame() {
     return Tempo.brasPAme(getText());
   }
   
   public boolean isValidd() {
     if (!Metodos.cheData(getText())) {
      return false;
     }
     return true;
   }
   
   private void sched() {
     new Timer().schedule(new TimerTask() {
       public void run() {
         if (!Metodos.cheData(LData.this.getText())) {
          LData.this.setBackground(new Color(255, 53, 53));
          LData.this.valido = false;
         }
         else {
           LData.this.setBackground(Color.WHITE);
         LData.this.valido = true;
         }
       }
     }, 33L, 33L);
   }
 }


