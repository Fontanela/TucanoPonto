 package br.com.tucanobrasil.sis.util;
 
 import java.util.ArrayList;
 
 
 
 
 
 
 
 public class StrLis
 {
   private String lis;
   private int freq;
   
   public StrLis(String lis, int freq)
   {
     this.lis = lis;
    this.freq = freq;
   }
   
   public void print() {
     System.out.println(">> " + this.lis);
   }
   
   public ArrayList<String> pArr() {
     return new ArrayList<String>();
   }
   
   public int tamanho() {
     ArrayList<String> arr = new ArrayList<String>();
     return arr.size();
   }
   
   public void adi(String str) {
    this.lis += str;
     this.lis = this.lis.substring(0, this.lis.length());
   }
   
   public void alterar(String val, int ind) {
    ArrayList<String> arr = new ArrayList<String>();
     
     if (ind == 0) {
      arr.set(ind, val + "\001");
     }
     else if (ind == tamanho() - 1) {
       arr.set(ind, "\001" + val);
     }
     else {
      arr.set(ind, "\001" + val + "\001");
     }
     
    this.lis = LEsc.arrPStr(arr);
   }
   
   public String ler(int ind) {
    ArrayList<String> arr = new ArrayList<String>();
     
    return (String)arr.get(ind);
   }
   
   public void rem(int ind) {
     ArrayList<String> arr = new ArrayList<String>();
     
     for (int i = 0; i < arr.size(); i += this.freq) {
       if (ind * this.freq == i) {
         for (int j = 0; j <= this.freq; j++) {
           if (j == this.freq - 1) {
             arr.remove(ind * this.freq);
             break;
           }
          arr.remove(ind * this.freq);
         }
       }
     }
     this.lis = LEsc.stndArr(arr, "\001");
   }
 }

