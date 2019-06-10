/*     */ package br.com.tucanobrasil.docs;
/*     */ 
/*     */ import br.com.tucanobrasil.O;
/*     */ import br.com.tucanobrasil.sis.rede.Rede;
/*     */ import br.com.tucanobrasil.sis.util.Arquivo;
/*     */ import br.com.tucanobrasil.sis.util.LEsc;
/*     */ import br.com.tucanobrasil.sis.util.Proc;
/*     */ import br.com.tucanobrasil.sis.util.Tempo;
/*     */ import br.com.tucanobrasil.sis.util.Util;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ 
/*     */ public class Documentos
/*     */ {
/*     */   public static void imprimirPonto(String idUsu, String d1, String d2)
/*     */   {
/*  17 */     d1 = Tempo.brasPAme(d1);
/*  18 */     d2 = Tempo.brasPAme(d2);
/*     */     
/*  20 */     boolean usaVt = Proc.strPBol(Rede.httpPost("UVT" + idUsu));
/*  21 */     String nome = Rede.httpPost("NDF" + idUsu);
/*     */     
/*  23 */     double va = 0.0D;double vr = 0.0D;double vt = 0.0D;
/*     */     
/*  25 */     String[] di = d1.split("-", -1);
/*  26 */     String strDf = d2;
/*     */     
/*  28 */     Calendar ccc = Tempo.dateToCal(Proc.SPI(di[0]), Proc.SPI(di[1]) - 1, Proc.SPI(di[2]));
/*     */     
/*  30 */     String r = "";
/*  31 */     String lisHE = "";
/*  32 */     String lisNE = "";
/*  33 */     while (!Tempo.calToAme(ccc).equals(strDf)) {
/*  34 */       String dataAt = Tempo.calToAme(ccc);
/*  35 */       String brasil = Tempo.amePBras(dataAt);
/*     */       
/*  37 */       String[] nums = dataAt.split("-", -1);
/*  38 */       String dw = Tempo.shortDW(Proc.SPI(nums[0]), Proc.SPI(nums[1]), Proc.SPI(nums[2])).toUpperCase();
/*     */       
/*  40 */       ArrayList<String> dados = Rede.httpArr("PUXRELD\007" + idUsu + "\001" + dataAt);
/*  41 */       if (dados.size() == 0) {
/*  42 */         if ((!dw.equals("SÁB")) && (!dw.equals("DOM"))) {
/*  43 */           r = r + brasil + "   " + dw + O.nextCol("", 86) + "00:00 \r\n";
/*     */         }
/*     */         else
/*     */         {
/*  47 */           r = r + brasil + "   " + dw + "\r\n";
/*     */         }
/*     */         
/*  50 */         Tempo.somaDia(ccc, 1);
/*     */ 
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/*     */ 
/*  58 */         String em = (String)dados.get(0);
/*  59 */         String sm = (String)dados.get(2);
/*  60 */         String et = (String)dados.get(4);
/*  61 */         String st = (String)dados.get(6);
/*     */         
/*  63 */         String ema = (String)dados.get(1);
/*  64 */         String sma = (String)dados.get(3);
/*  65 */         String eta = (String)dados.get(5);
/*  66 */         String sta = (String)dados.get(7);
/*     */         
/*  68 */         String totaj = (String)dados.get(8);
/*     */         
/*  70 */         String manha = Tempo.difHoras(em, sm);
/*  71 */         String tarde = Tempo.difHoras(et, st);
/*     */         
/*  73 */         String tot = Tempo.horaRH(Tempo.calcLisHoras(new String[] { manha, tarde }));
/*     */         
/*  75 */         if (totaj.equals("")) {
/*  76 */           if ((!ema.equals("")) || (!sma.equals("")) || (!eta.equals("")) || (!sta.equals(""))) {
/*  77 */             String uem = em;String usm = sm;String uet = et;String ust = st;
/*  78 */             if (!ema.equals("")) uem = ema;
/*  79 */             if (!sma.equals("")) usm = sma;
/*  80 */             if (!eta.equals("")) uet = eta;
/*  81 */             if (!sta.equals("")) { ust = sta;
/*     */             }
/*  83 */             manha = Tempo.difHoras(uem, usm);
/*  84 */             tarde = Tempo.difHoras(uet, ust);
/*     */             
/*  86 */             totaj = Tempo.horaRH(Tempo.calcLisHoras(new String[] { manha, tarde }));
/*     */           }
/*     */           else {
/*  89 */             totaj = tot;
/*     */           }
/*     */         }
/*     */         else {
/*  93 */           ema = "";
/*  94 */           sma = "";
/*  95 */           eta = "";
/*  96 */           sta = "";
/*     */         }
/*     */         
/*  99 */         String dif = Tempo.difHoras("8:48", totaj);
/*     */         
/* 101 */         if (dif.contains("-")) {
/* 102 */           dif = dif.replaceAll("-", "");
/* 103 */           lisNE = lisNE + dif + "\001";
/*     */           
/* 105 */           dif = "-" + Tempo.horaRH(dif);
/*     */         }
/*     */         else {
/* 108 */           lisHE = lisHE + dif + "\001";
/*     */           
/* 110 */           dif = "+" + Tempo.horaRH(dif);
/*     */         }
/*     */         
/* 113 */         int horas = Proc.SPI(totaj.split(":")[0]);
/*     */         
/* 115 */         if ((horas >= 6) && (horas < 8)) {
/* 116 */           va += 0.5D;
/* 117 */           vr += 1.0D;
/* 118 */           if (usaVt) vt += 1.0D;
/*     */         }
/* 120 */         else if (horas >= 8) {
/* 121 */           va += 1.0D;
/* 122 */           vr += 1.0D;
/* 123 */           if (usaVt) vt += 1.0D;
/*     */         }
/*     */         else {
/* 126 */           va += 0.0D;
/* 127 */           vr += 0.0D;
/* 128 */           if (usaVt) { vt += 1.0D;
/*     */           }
/*     */         }
/* 131 */         if (!((String)dados.get(10)).equals("")) va += Proc.SPD((String)dados.get(10));
/* 132 */         if (!((String)dados.get(11)).equals("")) vr += Proc.SPD((String)dados.get(11));
/* 133 */         if (!((String)dados.get(12)).equals("")) { vt += Proc.SPD((String)dados.get(12));
/*     */         }
/* 135 */         r = r + O.nextCol(new StringBuilder(String.valueOf(brasil)).append("   ").append(dw).toString(), 18) + O.nextCol(em, 10) + O.nextCol(ema, 9) + O.nextCol(sm, 7);
/* 136 */         r = r + O.nextCol(sma, 9) + O.nextCol(et, 7) + O.nextCol(eta, 9) + O.nextCol(st, 7);
/* 137 */         r = r + O.nextCol(sta, 9) + O.nextCol(tot, 8) + O.nextCol(totaj, 9) + O.nextCol(dif, 8) + (String)dados.get(9) + "\r\n";
/*     */         
/* 139 */         Tempo.somaDia(ccc, 1);
/*     */       }
/*     */     }
/* 142 */     lisHE = LEsc.remUChr(lisHE);
/* 143 */     lisNE = LEsc.remUChr(lisNE);
/*     */     
/* 145 */     String totHE = Tempo.calcLisHoras(lisHE.split("\001", -1));
/* 146 */     String totNE = Tempo.calcLisHoras(lisNE.split("\001", -1));
/* 147 */     String[] vals = totNE.split(":");
/* 148 */     totNE = "-" + vals[0] + ":" + "-" + vals[1];
/*     */     
/* 150 */     String theDiff = Tempo.calcLisHoras(new String[] { totHE, totNE });
/*     */     
/* 152 */     String conteudo = LEsc.arrPStr(Arquivo.ler("./mod_pon.txt"));
/*     */     
/* 154 */     conteudo = conteudo.replace("#X", nome);
/* 155 */     conteudo = conteudo.replace("#Y", Tempo.dataBrasil());
/* 156 */     conteudo = conteudo.replace("#CONTEUDOS", r);
/* 157 */     conteudo = conteudo.replace("#TOT", theDiff);
/* 158 */     conteudo = conteudo.replace("#VA", "VA: " + va + " VR: " + vr);
/* 159 */     conteudo = conteudo.replace("#VT", "VT: " + vt);
/*     */     
/* 161 */     Arquivo.standardFile("./relatorios/" + nome + ".txt", conteudo);
/* 162 */     Util.openFolder("./relatorios");
/*     */   }
/*     */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\docs\Documentos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */