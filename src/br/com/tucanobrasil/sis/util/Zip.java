/*    */ package br.com.tucanobrasil.sis.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.zip.ZipEntry;
/*    */ import java.util.zip.ZipInputStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Zip
/*    */ {
/*    */   public static void unZipIt(String zipFile, String outputFolder)
/*    */   {
/* 22 */     byte[] buffer = new byte[1024];
/*    */     
/*    */ 
/*    */     try
/*    */     {
/* 27 */       File folder = new File(outputFolder);
/* 28 */       if (!folder.exists()) {
/* 29 */         folder.mkdir();
/*    */       }
/*    */       
/*    */ 
/* 33 */       ZipInputStream zis = 
/* 34 */         new ZipInputStream(new FileInputStream(zipFile));
/*    */       
/* 36 */       ZipEntry ze = zis.getNextEntry();
/*    */       
/* 38 */       while (ze != null)
/*    */       {
/* 40 */         String fileName = ze.getName();
/* 41 */         File newFile = new File(outputFolder + File.separator + fileName);
/*    */         
/*    */ 
/*    */ 
/* 45 */         new File(newFile.getParent()).mkdirs();
/*    */         
/* 47 */         FileOutputStream fos = new FileOutputStream(newFile);
/* 50 */         while ((zis.read(buffer)) > 0) { int len1 = 0;
/* 51 */           fos.write(buffer, 0, len1);
/*    */         }
/*    */         
/* 54 */         fos.close();
/* 55 */         ze = zis.getNextEntry();
/*    */       }
/*    */       
/* 58 */       zis.closeEntry();
/* 59 */       zis.close();
/*    */     }
/*    */     catch (IOException ex) {
/* 62 */       ex.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

