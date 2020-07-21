 package br.com.tucanobrasil.sis.rede;
 
 import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
 import java.util.Scanner;
import br.com.tucanobrasil.M;
import br.com.tucanobrasil.sis.util.Ig;
import br.com.tucanobrasil.sis.util.LEsc;
 
 
 
 
 public class Rede
 {
   public static String PHPSESSID = null;
   public static String DEST = null;
   private static Scanner scanner;
   public static int PCKTLOG = 0;

   
   @SuppressWarnings("rawtypes")
public static void setPHPSESSID()
   {
     try
     {
      URL authUrl = new URL(M.LNK + "/server.php");
      HttpURLConnection authCon = (HttpURLConnection)authUrl.openConnection();
       try
       {
        authCon.connect();
       }
       catch (ConnectException e)
       {
         Ig.err("Não há conexão com o servidor");
        System.exit(0);
       }
      StringBuilder sb = new StringBuilder();
       
      @SuppressWarnings("unchecked")
List<String> cookies = (List)authCon.getHeaderFields().get("Set-Cookie");
       if (cookies != null) {
        for (String cookie : cookies)
         {
           if (sb.length() > 0) {
             sb.append("; ");
           }
           String value = cookie.split(";")[0];
          sb.append(value);
         }
       }
      PHPSESSID = sb.toString();
     }
     catch (MalformedURLException e)
     {
      e.printStackTrace();
     }
     catch (IOException e)
     {
      e.printStackTrace();
     }
   }
   
	public static String post(String header, String packet) { return httpPost(header + "" + packet); }

   
   public static String httpPost(String dados)
   {
   if(PCKTLOG == 1) {
		System.out.println("SENDED: --------");
		System.out.println();
		
		
		System.out.println(dados);
		System.out.println();
		System.out.println();
	}
	   
     dados = "pac=" + dados;
     try
     {
       byte[] bDados = dados.getBytes(StandardCharsets.UTF_8);
       
      URL url = new URL(M.LNK + "/" + DEST);
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
       
       conn.setRequestMethod("POST");
       conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      conn.setRequestProperty("Charset", "UTF-8");

       conn.setRequestProperty("Cookie", PHPSESSID);
       
       conn.setDoOutput(true);
       
      DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
       wr.write(bDados);
       
       InputStreamReader x = new InputStreamReader(conn.getInputStream(), "UTF-8");
       
      String res = "";
      for (int c = x.read(); c != -1; c = x.read()) {
         res = res + (char)c;
       }
      
      if(PCKTLOG == 1) {
			System.out.println("-------- RECEIVED:");
			System.out.println();
			System.out.println(res);
			System.out.println();
			System.out.println();
		}
      x.close();
       return res;
     }
     catch (UnsupportedEncodingException e)
     {
      e.printStackTrace();
     }
     catch (MalformedURLException e)
     {
       e.printStackTrace();
     }
     catch (IOException e)
     {
       e.printStackTrace();
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     return null;
   }
   
   public static String view(String site)
   {
     String content = null;
    URLConnection connection = null;
     try
     {
       connection = new URL(site).openConnection();
       scanner = new Scanner(connection.getInputStream());
       scanner.useDelimiter("\\Z");
      content = scanner.next();
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
    return content;
   }
   
   public static ArrayList<String> httpArr(String pac)
   {
     return LEsc.strPArr(httpPost(pac));
   }
 }
