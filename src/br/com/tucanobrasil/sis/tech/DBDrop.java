 package br.com.tucanobrasil.sis.tech;
 
 import java.util.ArrayList;
import javax.swing.JComboBox;
 

 
 
 public class DBDrop
   extends JComboBox<Object>
 {
   private static final long serialVersionUID = 1L;
   public String curr;
  public ArrayList<String> ids = new ArrayList<String>();
   
   public String getCurrValue()
   {
     int s = getSelectedIndex();
     if (s == -1) return "0";
     return (String)this.ids.get(s);
   }
   
   public void setIndexByRegister(String r) {
     for (int i = 0; i < this.ids.size(); i++) {
      if (((String)this.ids.get(i)).equals(r)) {
         setSelectedIndex(i);return;
       }
     }
   }
   
   public void setIndexByCurrValue() {
     for (int i = 0; i < this.ids.size(); i++) {
       if (((String)this.ids.get(i)).equals(this.curr)) {
         setSelectedIndex(i);return;
       }
     }
   }
   
   public void addRow(String id, String val) {
     this.ids.add(id);
    addItem(val);
   }
   
   public void remRow(int r) {
     this.ids.remove(r);
    remove(r);
   }
 }

