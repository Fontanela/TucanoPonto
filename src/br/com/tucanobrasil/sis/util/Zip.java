 package br.com.tucanobrasil.sis.util;
 
 import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
 
 
 
 
 
 public class Zip
 {
   public static void unZipIt(String zipFile, String outputFolder)
   {
     byte[] buffer = new byte[1024];
     
 
     try
     {
       File folder = new File(outputFolder);
       if (!folder.exists()) {
        folder.mkdir();
       }
       
 
      ZipInputStream zis = 
        new ZipInputStream(new FileInputStream(zipFile));
       
       ZipEntry ze = zis.getNextEntry();
       
      while (ze != null)
       {
         String fileName = ze.getName();
         File newFile = new File(outputFolder + File.separator + fileName);
         
 
 
         new File(newFile.getParent()).mkdirs();
         
         FileOutputStream fos = new FileOutputStream(newFile);
        while ((zis.read(buffer)) > 0) { int len1 = 0;
          fos.write(buffer, 0, len1);
         }
         
         fos.close();
         ze = zis.getNextEntry();
       }
       
       zis.closeEntry();
       zis.close();
     }
     catch (IOException ex) {
      ex.printStackTrace();
     }
   }
 }

