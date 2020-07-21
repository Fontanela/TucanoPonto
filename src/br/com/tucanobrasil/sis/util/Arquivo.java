 package br.com.tucanobrasil.sis.util;
 
 import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
   
   public static void standardFile(String caminho, String cont){
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(caminho), "UTF-8"));
		    writer.write(cont);
		    writer.flush();
		    writer.close();
		} catch (IOException ex) {
		    // Report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
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


