 package br.com.tucanobrasil.sis.util;
 
 import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
 
 public class Arquivo
 {
   public static void dirIfNot(String dir)
   {
     File directory = new File(dir);
     if (!directory.exists()) {
      directory.mkdir();
     }
   }
   
   public static void gerRel(String nome, String[] infos) {
     ArrayList<String> arq = ler("modelo.html");
    String con = LEsc.arrPStr(arq);
     con = con.replace("#func", nome);
     
     String add = "";
     String[] lisH = new String[infos.length / 8];
   for (int i = 0; i < infos.length; i += 8)
     {
       add = add + "<tr><td>" + infos[i] + "</td><td>" + infos[(i + 1)] + "</td><td>" + infos[(i + 2)] + "</td><td>" + infos[(i + 3)] + "</td><td>" + infos[(i + 4)] + "</td><td>" + infos[(i + 5)] + "</td><td>" + infos[(i + 6)] + "</td><td>" + infos[(i + 7)] + "</td></tr>";
       
 
     if (!infos[(i + 5)].equals("")) {
         lisH[(i / 8)] = infos[(i + 5)];
       }
     }
     String tot = Tempo.calcLisHoras(lisH);
     
    con = con.replace("#infos", add);
    con = con.replace("#total", tot);
     
    sobEsc("relatorios/" + nome, null);
     
    standardFile("relatorios/" + nome + "/2018-03-01.html", con);
   }
   

   public static void standardFile(String caminho, String cont)
   {     
   }
   
   public static void sobEsc(String caminho, String cont)
   {
     File fold = new File(caminho);
     fold.delete();
    File fnew = new File(caminho);
     
     if (cont == null) {
       fnew.mkdirs();
       return;
     }
     try {
       FileWriter f2 = new FileWriter(fnew, false);
       f2.write(cont);
      f2.close();
     } catch (IOException e) {
      e.printStackTrace();
     }
   }
   
   public static ArrayList<String> ler(String arq) {
     ArrayList<String> res = new ArrayList<String>();
     try {
      BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(arq), "UTF-8"));
       BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(arq), "UTF-8"));
       
       int lines = 0;
       while (reader.readLine() != null) lines++;
       reader.close();
       
       int c = 0;
      while (c != lines) {
         if (c == lines - 1) {
          res.add(in.readLine());
         }
         else {
          res.add(in.readLine() + "\r\n");
         }
         c++;
       }
       in.close();
       return res;
     }
     catch (IOException e) {
       e.printStackTrace();
     }
    return null;
   }
 }


