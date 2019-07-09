package br.com.tucanobrasil.docs;

//Bibliotecas
import java.util.ArrayList;
import java.util.Calendar;
import br.com.tucanobrasil.O;
import br.com.tucanobrasil.sis.rede.Rede;
import br.com.tucanobrasil.sis.util.Arquivo;
import br.com.tucanobrasil.sis.util.LEsc;
import br.com.tucanobrasil.sis.util.Proc;
import br.com.tucanobrasil.sis.util.Tempo;
import br.com.tucanobrasil.sis.util.Util;

//Criação da classe
	public class Documentos{
	
		//Método que imprime o ponto
			public static void imprimirPonto(String idUsu, String d1, String d2){
			
			//Declaração das Variaveis/Atributos
				d1 = Tempo.brasPAme(d1);
				d2 = Tempo.brasPAme(d2);
				
				//Chamando o método que verifica se o funcionário usa VT
					boolean usaVt = Proc.strPBol(Rede.httpPost("UVT" + idUsu));
					String nome = Rede.httpPost("NDF" + idUsu);
				
				double va = 0.0D;double vr = 0.0D;double vt = 0.0D;
				
				//Divisão dos caracteres
					String[] di = d1.split("-", -1);
					String strDf = d2;
				
				Calendar ccc = Tempo.dateToCal(Proc.SPI(di[0]), Proc.SPI(di[1]) - 1, Proc.SPI(di[2]));
				
				//Declaração das Variaveis/Atributos
					String r = "";
					String lisHE = "";
					String lisNE = "";
				
				//Enquanto o calulo foi igual a diferença ele roda esse laço	
					while (!Tempo.calToAme(ccc).equals(strDf)) {
					
					
						String dataAt = Tempo.calToAme(ccc);
						String brasil = Tempo.amePBras(dataAt);
						
						String[] nums = dataAt.split("-", -1);
						String dw = Tempo.shortDW(Proc.SPI(nums[0]), Proc.SPI(nums[1]), Proc.SPI(nums[2])).toUpperCase();
				
						//Puxando os dados
							ArrayList<String> dados = Rede.httpArr("PUXRELD\007" + idUsu + "\001" + dataAt);
							
							//Se não tiver dados
								if (dados.size() == 0) {
								
									//Se não for Sábado ou Domingo ele mostra a hora "00:00"
										if ((!dw.equals("SÁB")) && (!dw.equals("DOM"))) {
										r = r + brasil + " " + dw + O.nextCol("", 86) + "00:00 \r\n";
										}
									
									//Se não, fica vazio	
										else{
											r = r + brasil + " " + dw + "\r\n";
										}
									
									Tempo.somaDia(ccc, 1);	
								
								}
								
								else{
								
									//Atribuindo os dados do banco de dados as suas respectivas variaveis
										String em = (String)dados.get(0);
										String sm = (String)dados.get(2);
										String et = (String)dados.get(4);
										String st = (String)dados.get(6);
										
										String ema = (String)dados.get(1);
										String sma = (String)dados.get(3);
										String eta = (String)dados.get(5);
										String sta = (String)dados.get(7);
										
										String totaj = (String)dados.get(8);
										
										String manha = Tempo.difHoras(em, sm);
										String tarde = Tempo.difHoras(et, st);
									
										
										String tot = Tempo.horaRH(Tempo.calcLisHoras(new String[] { manha, tarde }));
									
									//Se o total ajustado estiver vazio	
										if (totaj.equals("")) {
										
											//Se qualquer um dos campos "ajuste" não estiver vazio
												if ((!ema.equals("")) || (!sma.equals("")) || (!eta.equals("")) || (!sta.equals(""))) {
													String uem = em;String usm = sm;String uet = et;String ust = st;
													
													//Pega os valores dos campos "não ajustados"
														if (!ema.equals("")) uem = ema;
														if (!sma.equals("")) usm = sma;
														if (!eta.equals("")) uet = eta;
														if (!sta.equals("")) ust = sta;
											
											//Seprando as entradas e saídas por manha e tarde				
												manha = Tempo.difHoras(uem, usm);
												tarde = Tempo.difHoras(uet, ust);
											
											//Calcula o total ajustado, subtraindo o total da manhã pelo total da tarde
												totaj = Tempo.horaRH(Tempo.calcLisHoras(new String[] { manha, tarde }));
												}
												
											//Se os valores forem vazios	
												else {
												
													//Total ajustado vai ser igual a total
														totaj = tot;
												}
										}
										
									//Se o total ajustado não estiver vazio	
										else {
										
											//Campos de registro ficam vazios
												ema = "";
												sma = "";
												eta = "";
												sta = "";
										}
										
									//Diferença de horas  ****VERIFICAR****	
										String dif = Tempo.difHoras("8:48", totaj);
									
									//Se a diferença de horas for negativa	
										if (dif.contains("-")) {
											dif = dif.replaceAll("-", "");
											
											lisNE = lisNE + dif + "\001";
											
											//Faz uma string negativa
												dif = "-" + Tempo.horaRH(dif);
										}
										
									//Se não, deixa o sinal posivo na frente da hora
										else {
											lisHE = lisHE + dif + "\001";
											
											dif = "+" + Tempo.horaRH(dif);
										}
										
									//Chamando a variavel "Horas"
										int horas = Proc.SPI(totaj.split(":")[0]);
										
									//Se horas trabalhadas for maior ou igual a 6 e menor que 8	
										if ((horas >= 6) && (horas < 8)) {
											va += 0.5D;//VA fica com a metade
											vr += 1.0D;//VR inteiro
												
											//Se método "usaVt" for verdadeiro 			
												if (usaVt) vt += 1.0D;
										}
									
									//Se as horas trabalhadas forem maiores ou iguais a 8	
										else if (horas >= 8) {
											va += 1.0D;//Valor de VA fica inteiro
											vr += 1.0D;//Valor de VR fica inteiro
											
											//Se método "usaVt" for verdadeiro 	
												if (usaVt) vt += 1.0D;
										}
										
									//Se não zera os valores de VA e VR	
										else {
											va += 0.0D;
											vr += 0.0D;
											
											//Se método "usaVt" for verdadeiro 	
												if (usaVt) vt += 1.0D;
										}
										
									//Se os valores que estão no banco de dados de VA, VR e VT não forem vazios os campos pegam esse valor
										if (!((String)dados.get(10)).equals("")) va += Proc.SPD((String)dados.get(10));
										if (!((String)dados.get(11)).equals("")) vr += Proc.SPD((String)dados.get(11));
										if (!((String)dados.get(12)).equals("")) vt += Proc.SPD((String)dados.get(12));
										
									//Atribuindo os vlaores as respectivas colunas
										r = r + O.nextCol(new StringBuilder(String.valueOf(brasil)).append(" ").append(dw).toString(), 18) + O.nextCol(em, 10) + O.nextCol(ema, 9) + O.nextCol(sm, 7);
										r = r + O.nextCol(sma, 9) + O.nextCol(et, 7) + O.nextCol(eta, 9) + O.nextCol(st, 7);
										r = r + O.nextCol(sta, 9) + O.nextCol(tot, 8) + O.nextCol(totaj, 9) + O.nextCol(dif, 8) + (String)dados.get(9) + "\r\n";
										
										Tempo.somaDia(ccc, 1);
								}
					}
				//Fim do laço
					
					
				//Remove os carecteres desnecessários das variáveis	
					lisHE = LEsc.remUChr(lisHE);
					lisNE = LEsc.remUChr(lisNE);
				
				//Imprimindo o relatório	
					String totHE = Tempo.calcLisHoras(lisHE.split("\001", -1));
					String totNE = Tempo.calcLisHoras(lisNE.split("\001", -1));
					String[] vals = totNE.split(":");
					totNE = "-" + vals[0] + ":" + "-" + vals[1];
					
					String theDiff = Tempo.calcLisHoras(new String[] { totHE, totNE });
					
					//Pega o modelo de relatório
						String conteudo = LEsc.arrPStr(Arquivo.ler("./mod_pon.txt"));
					
					//Atribui as informações as seus respectivos lugares
						conteudo = conteudo.replace("#X", nome);
						conteudo = conteudo.replace("#Y", Tempo.dataBrasil());
						conteudo = conteudo.replace("#CONTEUDOS", r);
						conteudo = conteudo.replace("#TOT", theDiff);
						conteudo = conteudo.replace("#VA", "VA: " + va + " VR: " + vr);
						conteudo = conteudo.replace("#VT", "VT: " + vt);
					
					//Abre o txt
						Arquivo.standardFile("./relatorios/" + nome + ".txt", conteudo);
						Util.openFolder("./relatorios");
			}
	}
//Fim da classe