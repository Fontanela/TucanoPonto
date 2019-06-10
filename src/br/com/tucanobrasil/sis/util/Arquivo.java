/*     */ package br.com.tucanobrasil.sis.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Arquivo
/*     */ {
/*     */   public static void dirIfNot(String dir)
/*     */   {
/*  26 */     File directory = new File(dir);
/*  27 */     if (!directory.exists()) {
/*  28 */       directory.mkdir();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void gerRel(String nome, String[] infos) {
/*  33 */     ArrayList<String> arq = ler("modelo.html");
/*  34 */     String con = LEsc.arrPStr(arq);
/*  35 */     con = con.replace("#func", nome);
/*     */     
/*  37 */     String add = "";
/*  38 */     String[] lisH = new String[infos.length / 8];
/*  39 */     for (int i = 0; i < infos.length; i += 8)
/*     */     {
/*  41 */       add = add + "<tr><td>" + infos[i] + "</td><td>" + infos[(i + 1)] + "</td><td>" + infos[(i + 2)] + "</td><td>" + infos[(i + 3)] + "</td><td>" + infos[(i + 4)] + "</td><td>" + infos[(i + 5)] + "</td><td>" + infos[(i + 6)] + "</td><td>" + infos[(i + 7)] + "</td></tr>";
/*     */       
/*     */ 
/*  44 */       if (!infos[(i + 5)].equals("")) {
/*  45 */         lisH[(i / 8)] = infos[(i + 5)];
/*     */       }
/*     */     }
/*  48 */     String tot = Tempo.calcLisHoras(lisH);
/*     */     
/*  50 */     con = con.replace("#infos", add);
/*  51 */     con = con.replace("#total", tot);
/*     */     
/*  53 */     sobEsc("relatorios/" + nome, null);
/*     */     
/*  55 */     standardFile("relatorios/" + nome + "/2018-03-01.html", con);
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public static void standardFile(String caminho, String cont)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aconst_null
/*     */     //   1: astore_2
/*     */     //   2: new 118	java/io/BufferedWriter
/*     */     //   5: dup
/*     */     //   6: new 120	java/io/OutputStreamWriter
/*     */     //   9: dup
/*     */     //   10: new 122	java/io/FileOutputStream
/*     */     //   13: dup
/*     */     //   14: aload_0
/*     */     //   15: invokespecial 124	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
/*     */     //   18: ldc 125
/*     */     //   20: invokespecial 127	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
/*     */     //   23: invokespecial 130	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
/*     */     //   26: astore_2
/*     */     //   27: aload_2
/*     */     //   28: aload_1
/*     */     //   29: invokevirtual 133	java/io/Writer:write	(Ljava/lang/String;)V
/*     */     //   32: aload_2
/*     */     //   33: invokevirtual 138	java/io/Writer:flush	()V
/*     */     //   36: aload_2
/*     */     //   37: invokevirtual 141	java/io/Writer:close	()V
/*     */     //   40: goto +30 -> 70
/*     */     //   43: astore_3
/*     */     //   44: aload_2
/*     */     //   45: invokevirtual 141	java/io/Writer:close	()V
/*     */     //   48: goto +31 -> 79
/*     */     //   51: astore 5
/*     */     //   53: goto +26 -> 79
/*     */     //   56: astore 4
/*     */     //   58: aload_2
/*     */     //   59: invokevirtual 141	java/io/Writer:close	()V
/*     */     //   62: goto +5 -> 67
/*     */     //   65: astore 5
/*     */     //   67: aload 4
/*     */     //   69: athrow
/*     */     //   70: aload_2
/*     */     //   71: invokevirtual 141	java/io/Writer:close	()V
/*     */     //   74: goto +5 -> 79
/*     */     //   77: astore 5
/*     */     //   79: return
/*     */     // Line number table:
/*     */     //   Java source line #59	-> byte code offset #0
/*     */     //   Java source line #62	-> byte code offset #2
/*     */     //   Java source line #63	-> byte code offset #10
/*     */     //   Java source line #62	-> byte code offset #23
/*     */     //   Java source line #64	-> byte code offset #27
/*     */     //   Java source line #65	-> byte code offset #32
/*     */     //   Java source line #66	-> byte code offset #36
/*     */     //   Java source line #67	-> byte code offset #40
/*     */     //   Java source line #70	-> byte code offset #44
/*     */     //   Java source line #69	-> byte code offset #56
/*     */     //   Java source line #70	-> byte code offset #58
/*     */     //   Java source line #71	-> byte code offset #67
/*     */     //   Java source line #70	-> byte code offset #70
/*     */     //   Java source line #72	-> byte code offset #79
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	80	0	caminho	String
/*     */     //   0	80	1	cont	String
/*     */     //   1	70	2	writer	java.io.Writer
/*     */     //   43	1	3	localIOException	IOException
/*     */     //   56	12	4	localObject	Object
/*     */     //   51	1	5	localException	Exception
/*     */     //   65	1	5	localException1	Exception
/*     */     //   77	1	5	localException2	Exception
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   2	40	43	java/io/IOException
/*     */     //   44	48	51	java/lang/Exception
/*     */     //   2	44	56	finally
/*     */     //   58	62	65	java/lang/Exception
/*     */     //   70	74	77	java/lang/Exception
/*     */   }
/*     */   
/*     */   public static void sobEsc(String caminho, String cont)
/*     */   {
/*  75 */     File fold = new File(caminho);
/*  76 */     fold.delete();
/*  77 */     File fnew = new File(caminho);
/*     */     
/*  79 */     if (cont == null) {
/*  80 */       fnew.mkdirs();
/*  81 */       return;
/*     */     }
/*     */     try {
/*  84 */       FileWriter f2 = new FileWriter(fnew, false);
/*  85 */       f2.write(cont);
/*  86 */       f2.close();
/*     */     } catch (IOException e) {
/*  88 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static ArrayList<String> ler(String arq) {
/*  93 */     ArrayList<String> res = new ArrayList<String>();
/*     */     try {
/*  95 */       BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(arq), "UTF-8"));
/*  96 */       BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(arq), "UTF-8"));
/*     */       
/*  98 */       int lines = 0;
/*  99 */       while (reader.readLine() != null) lines++;
/* 100 */       reader.close();
/*     */       
/* 102 */       int c = 0;
/* 103 */       while (c != lines) {
/* 104 */         if (c == lines - 1) {
/* 105 */           res.add(in.readLine());
/*     */         }
/*     */         else {
/* 108 */           res.add(in.readLine() + "\r\n");
/*     */         }
/* 110 */         c++;
/*     */       }
/* 112 */       in.close();
/* 113 */       return res;
/*     */     }
/*     */     catch (IOException e) {
/* 116 */       e.printStackTrace();
/*     */     }
/* 118 */     return null;
/*     */   }
/*     */ }


