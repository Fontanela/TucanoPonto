package br.com.tucanobrasil.ig;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import br.com.tucanobrasil.sis.rede.Rede;
import br.com.tucanobrasil.sis.util.DBTable;
import br.com.tucanobrasil.sis.util.Ig;
import br.com.tucanobrasil.sis.util.O;
import br.com.tucanobrasil.sis.util.P;
import br.com.tucanobrasil.sis.util.Time;


public class Visual extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private DBTable tabHorarios;
	private DefaultTableModel modtabHoras;
	private String[] cols = new String[]{"Data", "Dia", "EM", "SM", "ET", "ST", "EM A.", "SM A.", "ET A.", "ST A.", "EE", "SE", "Total", "Total A.", "Dif.", "VA", "VR", "VT","Justificativa", "id"};
	private String idFun;
	private JLabel lblTotalDeHoras, lblTotalDeHorasAjust;
	
	public Visual(String idFun){
						
		this.idFun = idFun;
				
		janela();
		criaTabelas();
		popTab();
	
		setVisible(true);
	}
	
	private void janela(){
		setTitle("Histórico Ponto");
		setSize(1024, 600);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(new ImageIcon("./Icons/32x32.png").getImage());
		getContentPane().setBackground(new Color(0, 51, 90));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener((WindowListener) new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent event) {
	        	esc();
	        }
	    });	
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnVoltar = new JButton("");
		btnVoltar.setToolTipText("Voltar (esc)");
		btnVoltar.setIcon(new ImageIcon("./Icons/iconfinder_arrow-back_326518.png"));
		menuBar.add(btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esc();
			}
		});	
		
		JButton btnJustificativas = new JButton("");
		btnJustificativas.setToolTipText("Mostrar as justificativas");
		btnJustificativas.setIcon(new ImageIcon("./Icons/iconJustificativas.png"));
		menuBar.add(btnJustificativas);
		btnJustificativas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new Solicitacoes(idFun, 1);
			}
		});
		
		
		
		JLabel lblFunc = new JLabel(P.capitalizeAll(Rede.post("PEGANOMEFHIST" , idFun)));
		lblFunc.setForeground(Color.WHITE);
		lblFunc.setFont(new Font("Roboto", Font.BOLD, 15));
		lblFunc.setBounds(10, 11, 998, 14);
		getContentPane().add(lblFunc);
		
		JLabel lblSelecioneOIntervalo = new JLabel("Selecione o intervalo de tempo:");
		lblSelecioneOIntervalo.setForeground(Color.WHITE);
		lblSelecioneOIntervalo.setFont(new Font("Roboto", Font.BOLD, 15));
		lblSelecioneOIntervalo.setBounds(10, 508, 209, 18);
		getContentPane().add(lblSelecioneOIntervalo);
		
		Calendar cal = Calendar.getInstance(); 
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 01);
		cal.add(Calendar.MONTH, 0);
		
		JDateChooser dataIni = new JDateChooser();
		dataIni.setBounds(229, 507, 108, 20);
		dataIni.setDateFormatString("dd/MM/yyyy");
		dataIni.setFont(new Font("Roboto", Font.BOLD, 13));
		dataIni.setForeground(Color.BLACK);
		try {dataIni.setDate(P.STDT(Time.ameTBraz(Time.formatCalendar(cal))));
		} catch (ParseException e) {e.printStackTrace();}
		getContentPane().add(dataIni);
		cal.add(Calendar.MONTH,+1);

		JDateChooser dataFin = new JDateChooser();
		dataFin.setBounds(367, 507, 108, 20);
		dataFin.setDateFormatString("dd/MM/yyyy");
		dataFin.setFont(new Font("Roboto", Font.BOLD, 13));
		dataFin.setForeground(Color.BLACK);
		try {dataFin.setDate(P.STDT(Time.ameTBraz(Time.formatCalendar(cal))));
		} catch (ParseException e) {e.printStackTrace();}
		getContentPane().add(dataFin);
		
		JLabel lbla = new JLabel("á");
		lbla.setForeground(Color.WHITE);
		lbla.setFont(new Font("Roboto", Font.BOLD, 15));
		lbla.setBounds(347, 508, 8, 18);
		getContentPane().add(lbla);
		
		lblTotalDeHoras = new JLabel("Total de horas:");
		lblTotalDeHoras.setForeground(Color.WHITE);
		lblTotalDeHoras.setFont(new Font("Roboto", Font.BOLD, 15));
		lblTotalDeHoras.setBounds(522, 508, 209, 18);
		getContentPane().add(lblTotalDeHoras);
		
		lblTotalDeHorasAjust = new JLabel("Total de horas ajustado:");
		lblTotalDeHorasAjust.setForeground(Color.WHITE);
		lblTotalDeHorasAjust.setFont(new Font("Roboto", Font.BOLD, 15));
		lblTotalDeHorasAjust.setBounds(741, 508, 267, 18);
		getContentPane().add(lblTotalDeHorasAjust);
		
		JButton btnSearch = new JButton("");
		btnSearch.setIcon(new ImageIcon("./Icons/iconfinder_67_111124.png"));
		btnSearch.setBounds(485, 505, 25, 25);
		getContentPane().add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popTab();
			}
		});
	}
	
	private void criaTabelas() {

		//Método que identa a celula para o lado direito
			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
			
		//Método que identa a célula para o centro	
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);	
		
		JScrollPane scrollHoras = new JScrollPane();
		scrollHoras.setBounds(10, 36, 998, 454);
		getContentPane().add(scrollHoras);	
		
		//Modelo padrão de tabela
			modtabHoras= new DefaultTableModel(cols, 0){
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int row, int column){  
			          return false;  
			      }
			};
		
		tabHorarios = new DBTable(modtabHoras, null);
		scrollHoras.setViewportView(tabHorarios);
		tabHorarios.getTableHeader().setFont(new Font("Roboto", Font.TRUETYPE_FONT, 15));
		tabHorarios.getTableHeader().setForeground(Color.BLACK);
		tabHorarios.getTableHeader().setBackground(new Color(240, 240, 240));
		tabHorarios.setFont(new Font("Roboto", Font.TRUETYPE_FONT, 16));
		tabHorarios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabHorarios.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tabHorarios.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tabHorarios.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		tabHorarios.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		tabHorarios.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		tabHorarios.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		tabHorarios.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
		tabHorarios.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
		tabHorarios.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
		tabHorarios.getColumnModel().getColumn(11).setCellRenderer(centerRenderer);
		tabHorarios.getColumnModel().getColumn(12).setCellRenderer(centerRenderer);
		tabHorarios.getColumnModel().getColumn(13).setCellRenderer(centerRenderer);
		tabHorarios.getColumnModel().getColumn(14).setCellRenderer(rightRenderer);
		tabHorarios.getColumnModel().getColumn(15).setCellRenderer(centerRenderer);
		tabHorarios.getColumnModel().getColumn(16).setCellRenderer(centerRenderer);
		tabHorarios.getColumnModel().getColumn(17).setCellRenderer(centerRenderer);
		tabHorarios.getColumnModel().getColumn(0).setPreferredWidth(90);
		tabHorarios.getColumnModel().getColumn(1).setPreferredWidth(40);
		tabHorarios.getColumnModel().getColumn(2).setPreferredWidth(43);
		tabHorarios.getColumnModel().getColumn(3).setPreferredWidth(43);
		tabHorarios.getColumnModel().getColumn(4).setPreferredWidth(43);
		tabHorarios.getColumnModel().getColumn(5).setPreferredWidth(43);
		tabHorarios.getColumnModel().getColumn(6).setPreferredWidth(50);
		tabHorarios.getColumnModel().getColumn(7).setPreferredWidth(50);
		tabHorarios.getColumnModel().getColumn(8).setPreferredWidth(48);
		tabHorarios.getColumnModel().getColumn(9).setPreferredWidth(48);
		tabHorarios.getColumnModel().getColumn(10).setPreferredWidth(48);
		tabHorarios.getColumnModel().getColumn(11).setPreferredWidth(48);
		tabHorarios.getColumnModel().getColumn(12).setPreferredWidth(55);
		tabHorarios.getColumnModel().getColumn(13).setPreferredWidth(65);
		tabHorarios.getColumnModel().getColumn(14).setPreferredWidth(58);
		tabHorarios.getColumnModel().getColumn(15).setPreferredWidth(30);
		tabHorarios.getColumnModel().getColumn(16).setPreferredWidth(29);
		tabHorarios.getColumnModel().getColumn(17).setPreferredWidth(29);
		tabHorarios.getColumnModel().getColumn(18).setPreferredWidth(200);	
		tabHorarios.removeColumn(tabHorarios.getColumnModel().getColumn(19));
	}
	
	void popTab() {
		Ig.eraseTab(modtabHoras);
		String[] dates = Ig.serDatas(Ig.getAllDates(getContentPane())).split(O.SOH);
		String datas = "", dataSp = "";
		String[] di = Time.brazTAme(dates[0]).split("-", -1);
		String[] di2 = Time.brazTAme(dates[1]).split("-", -1);
		
		Calendar ccc = Time.dateToCal(P.STI(di[0]), P.STI(di[1]) - 1, P.STI(di[2]));
		Calendar cc2 = Time.dateToCal(P.STI(di2[0]), P.STI(di2[1]) - 1, P.STI(di2[2]));
		Time.sumDay(ccc, -1);
		while(!Time.calToAme(ccc).equals(Time.calToAme(cc2))) {
			if(Rede.post("VRFDATAPONT", Time.calToAme(cc2) + O.SOH + idFun).equals("0")) {
				Time.sumDay(cc2, -1);			
				continue;
			}
			datas += Time.calToAme(cc2) + ";";
			dataSp += Time.calToBra(cc2) + ";";
			Time.sumDay(cc2, -1);			
		}	
				
		String idCarga = Rede.post("PEGAIDCARG", idFun);
		String total = "", totalAj = "";
		String[] dados = Rede.post("PUXRELD", idFun + O.SOH + datas).split(O.SOH);
		String[] dts = dataSp.split(";");
		String[][] horario = new String[7][9];
		Boolean[][] vavr = new Boolean[7][2];
		boolean usaVt = P.strTBool(Rede.post("UVT" , idFun));
		String[] dadosCarga = Rede.post("PEGACARGAHOR", idCarga).split(";");
		String[] dadosVaVr = Rede.post("VRFDVAVR", idCarga).split(";");	
		for(int z = 0; z < 7; z++) {
			for(int k = 0; k < 9; k++) {
				horario[z][k] = dadosCarga[z+(k*7)];
			}
		}		

		for(int z = 0; z < 7; z++) {
			for(int k = 0; k < 2; k++) {
				vavr[z][k] = P.strTBool(dadosVaVr[z+(k*7)]);
			}
		}
		
		String[] dtMudCarg = Rede.post("PEGADTMUDN", idFun).split(";");		
		String idCargAnt = idCarga;
		
		for(int i = 0, j = 0, b = 0; j < dts.length; i+=18, j++) {
						
			Date date1 = null, date2 = null, date3 = null;
			String dt = "98/99/9999", dtC = "99/99/9999";
			if(dtMudCarg.length > b){
				dt = dtMudCarg[b+1];
				if(dtMudCarg.length > b+2){
					dtC = dtMudCarg[b+3];
				}
			}
			try {
				date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dts[j]);
				date1 =new SimpleDateFormat("dd/MM/yyyy").parse(dt);
				date3 =new SimpleDateFormat("dd/MM/yyyy").parse(dtC);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if(dtMudCarg.length > 1 && (!date2.before(date3) && date2.after(date1)) && dtMudCarg.length > b+2) {
				b+=2;
			}

			
			if(!idCargAnt.equals(idCarga)) {
				idCarga = dtMudCarg[b];
				
				dadosCarga = Rede.post("PEGACARGAHOR", idCarga).split(";");
				dadosVaVr = Rede.post("VRFDVAVR", idCarga).split(";");
				for(int z = 0; z < 7; z++) {
					for(int k = 0; k < 9; k++) {
						horario[z][k] = dadosCarga[z+(k*7)];
					}
				}		
	
				for(int z = 0; z < 7; z++) {
					for(int k = 0; k < 2; k++) {
						vavr[z][k] = P.strTBool(dadosVaVr[z+(k*7)]);
					}
				}
				idCargAnt = idCarga;
			}
			
			String[] nums = dts[j].split("/");
			String dw = P.capitalize(Time.shortDW(P.STI(nums[2]), P.STI(nums[1]), P.STI(nums[0])));
			String[] row = new String[20];
			String em = dados[i+0],sm = dados[i+1],et = dados[i+2],st = dados[i+3];
			String ema = dados[i+4],sma = dados[i+5],eta = dados[i+6],sta = dados[i+7];
			String ee = dados[i+8],se = dados[i+9];		
			String totaj = dados[i+11];			
			String manha = Time.diffHours(em, sm);
			String tarde = Time.diffHours(et, st);
			String especial = Time.diffHours(ee, se);		
			String tot = Time.hourRH(Time.calcHoursList(new String[] {manha, tarde}));			
			String almoco = Time.diffHours(sm, et);							
			String difamo = almoco.replace(":", "");	

			if(totaj.split("").length < 4) {
				if(!ema.equals("") || !sma.equals("") || !eta.equals("") || !sta.equals("") || !ee.equals("") || !se.equals("")) {
					String uem = em, usm = sm, uet = et, ust = st;
					if(!ema.equals("")){ uem = ema; }
					if(!sma.equals("")){ usm = sma; }
					if(!eta.equals("")){ uet = eta; }
					if(!sta.equals("")){ ust = sta; }
					manha = Time.diffHours(uem, usm);
					tarde = Time.diffHours(uet, ust);
					totaj = Time.hourRH(Time.calcHoursList(new String[] {manha, tarde}));
					totaj = Time.hourRH(Time.calcHoursList(new String[] {totaj, especial}));
				}
				else {
					totaj = tot;
				}
			}
						
			int horas = P.STI(totaj.split(":")[0]);

			for(int y = -2, z = 0; y < row.length-2; y++, z++) {
				if(y == -2) {row[z] = dts[j];}
				if(y == -1) {row[z] = dw;}
				if(y >= 0) {
					row[z] = dados[i+y];
					if((y == 1) && (P.STI(difamo) < 60 && P.STI(difamo) > 30)) {
						row[z] = "<html><font color = red>" + dados[i+y];
					}
					if((y == 2) && (P.STI(difamo) < 60 && P.STI(difamo) > 30)) {
						row[z] = "<html><font color = red>" + dados[i+y];
					}
					if(y == 10) {row[z] = tot;}
					if(y == 11) {row[z] = totaj;}
					if(y == 12) {						
						String dif = Time.diffHours(horario[Time.DWI(dw)][4], totaj);
						row[z] = dif;
					}
					if((y == 13) && dados[i+y].split("").length < 2) {
						int horasMin = P.STI(horario[Time.DWI(dw)][5].split(":")[0]), horasMax = P.STI(horario[Time.DWI(dw)][6].split(":")[0]); 
						double va = 0;			
						if(vavr[Time.DWI(dw)][0]) {
							if(horas >= horasMin && horas < horasMax) {va = 0.5;}
							else if(horas >= horasMax){va = 1;}
						}
						row[z] = va +""; 						
					}
					if((y == 14) && dados[i+y].split("").length < 2) {
						int horasMin = P.STI(horario[Time.DWI(dw)][7].split(":")[0]);
						double vr = 0;						
						if(horas >= horasMin && vavr[Time.DWI(dw)][1]) {vr = 1.0;}
						row[z] = vr +""; 
					}
					if(y == 15 && dados[i+y].split("").length < 2) {
						double vt = 0;		
						if(horas >= 1 && usaVt) {vt = 1.0;}
						row[z] = vt +""; 
					}
				}
			}
			total = Time.hourRH(Time.calcHoursList(new String[] {total, tot}));
			totalAj =  Time.hourRH(Time.calcHoursList(new String[] {totalAj, totaj}));
			modtabHoras.addRow(row);
		}		
		lblTotalDeHoras.setText("Total de horas: " + total + " horas");
		lblTotalDeHorasAjust.setText("Total de horas ajustado: " +  totalAj + " horas");		
	}
	
	public void esc() {

		dispose();
	}

	
}
