/*     */ package br.com.tucanobrasil.sis.rede;
/*     */ 
/*     */ import br.com.tucanobrasil.M;
/*     */ import br.com.tucanobrasil.sis.util.Ig;
/*     */ import br.com.tucanobrasil.sis.util.LEsc;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.ConnectException;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ //import java.util.Map;
/*     */ import java.util.Scanner;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Rede
/*     */ {
/*  26 */   public static String PHPSESSID = null;
/*  27 */   public static String DEST = null;
/*     */   private static Scanner scanner;
/*     */   
/*     */   @SuppressWarnings("rawtypes")
public static void setPHPSESSID()
/*     */   {
/*     */     try
/*     */     {
/*  34 */       URL authUrl = new URL(M.LNK + "/pon_servidor.php");
/*  35 */       HttpURLConnection authCon = (HttpURLConnection)authUrl.openConnection();
/*     */       try
/*     */       {
/*  38 */         authCon.connect();
/*     */       }
/*     */       catch (ConnectException e)
/*     */       {
/*  42 */         Ig.erro("Não há...");
/*  43 */         System.exit(0);
/*     */       }
/*  45 */       StringBuilder sb = new StringBuilder();
/*     */       
/*  47 */       @SuppressWarnings("unchecked")
List<String> cookies = (List)authCon.getHeaderFields().get("Set-Cookie");
/*  48 */       if (cookies != null) {
/*  49 */         for (String cookie : cookies)
/*     */         {
/*  51 */           if (sb.length() > 0) {
/*  52 */             sb.append("; ");
/*     */           }
/*  54 */           String value = cookie.split(";")[0];
/*  55 */           sb.append(value);
/*     */         }
/*     */       }
/*  58 */       PHPSESSID = sb.toString();
/*     */     }
/*     */     catch (MalformedURLException e)
/*     */     {
/*  62 */       e.printStackTrace();
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*  66 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static String httpPost(String dados)
/*     */   {
/*  72 */     dados = "pac=" + dados;
/*     */     try
/*     */     {
/*  75 */       byte[] bDados = dados.getBytes(StandardCharsets.UTF_8);
/*     */       
/*  78 */       URL url = new URL(M.LNK + "/" + DEST);
/*  79 */       HttpURLConnection conn = (HttpURLConnection)url.openConnection();
/*     */       
/*  81 */       conn.setRequestMethod("POST");
/*  82 */       conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
/*  83 */       conn.setRequestProperty("Charset", "UTF-8");
/*  84
/*  85 */       conn.setRequestProperty("Cookie", PHPSESSID);
/*     */       
/*  87 */       conn.setDoOutput(true);
/*     */       
/*  89 */       DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
/*  90 */       wr.write(bDados);
/*     */       
/*  92 */       InputStreamReader x = new InputStreamReader(conn.getInputStream(), "UTF-8");
/*     */       
/*  94 */       String res = "";
/*  95 */       for (int c = x.read(); c != -1; c = x.read()) {
/*  96 */         res = res + (char)c;
/*     */       }
/*  98 */       x.close();
/*  99 */       return res;
/*     */     }
/*     */     catch (UnsupportedEncodingException e)
/*     */     {
/* 103 */       e.printStackTrace();
/*     */     }
/*     */     catch (MalformedURLException e)
/*     */     {
/* 107 */       e.printStackTrace();
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 111 */       e.printStackTrace();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 115 */       e.printStackTrace();
/*     */     }
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   public static String view(String site)
/*     */   {
/* 122 */     String content = null;
/* 123 */     URLConnection connection = null;
/*     */     try
/*     */     {
/* 126 */       connection = new URL(site).openConnection();
/* 127 */       scanner = new Scanner(connection.getInputStream());
/* 128 */       scanner.useDelimiter("\\Z");
/* 129 */       content = scanner.next();
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 133 */       ex.printStackTrace();
/*     */     }
/* 135 */     return content;
/*     */   }
/*     */   
/*     */   public static ArrayList<String> httpArr(String pac)
/*     */   {
/* 140 */     return LEsc.strPArr(httpPost(pac));
/*     */   }
/*     */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\sis\rede\Rede.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */