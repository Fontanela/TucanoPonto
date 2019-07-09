 package br.com.tucanobrasil.ig;
 import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import br.com.tucanobrasil.sis.lgraf.LRealArea;
 
 public class Solicitacao extends javax.swing.JFrame implements java.awt.KeyEventDispatcher
 {
   private static final long serialVersionUID = 1L;
   private JLabel rotAgr;
   private JLabel rotDesc;
   private JLabel rotSen;
   private br.com.tucanobrasil.sis.lgraf.LData texData;
   private LRealArea texDesc;
   private LRealArea texOut;
   private javax.swing.JButton botLog;
   private JLabel label;
   private JLabel rotSol;
   private JRadioButton radOp1;
   private JRadioButton radOp2;
   private JRadioButton radOp3;
   private JLabel label_5;
   private JTextField texEM;
   private JTextField texSM;
   private JTextField texET;
   private JTextField texST;
   private javax.swing.ButtonGroup bg;
   javax.swing.JScrollPane barOut;
   
   public void envSol()
   {
     if (!this.texData.valido) {
      br.com.tucanobrasil.sis.util.Ig.advertencia("Insira a data em um formato correto.");
      return;
     }
     
     int tipo = 0;
    String corrigir = "";
    String outra = "";
    if (this.st[1]) {
       tipo = 1;
      corrigir = this.texEM.getText() + ";" + this.texSM.getText() + ";" + this.texET.getText() + ";" + this.texST.getText();
     }
    else if (this.st[2]) {
      tipo = 2;
      outra = this.texOut.getText();
     }
     
    String pac = "SCP" + this.texData.getText() + "\001" + this.texDesc.getText() + "\001" + tipo + "\001" + corrigir + "\001" + outra;
    if (br.com.tucanobrasil.sis.rede.Rede.httpPost(pac).equals("PCS")) {
      dispose();
      br.com.tucanobrasil.sis.util.Ig.sucesso("Solicitação enviada com sucesso! Aguarde a correção pelo RH.");
       
       new Solicitacoes(br.com.tucanobrasil.M.ID);
     }
   }
   
 
 
   public Solicitacao()
   {
     janela();
     criarRotulos();
     criarTextos();
     criarBotoes();
     geral();
     
    adiComps();
     proParal();
     
     br.com.tucanobrasil.M.manager.addKeyEventDispatcher(this);
     
     setVisible(true);
   }
   
   private void janela() {
     setTitle("Tucano Ponto - Registro");
    setSize(624, 448);
     getContentPane().setLayout(null);
     setLocationRelativeTo(null);
     setResizable(false);
     setDefaultCloseOperation(3);
     setIconImage(new javax.swing.ImageIcon("32x32.png").getImage());
     getContentPane().setBackground(new java.awt.Color(255, 255, 181));
   }
   
   private void criarRotulos() {
     this.rotAgr = new JLabel("Data: " + br.com.tucanobrasil.sis.util.Tempo.formatoBrasil());
    this.rotAgr.setBounds(433, 386, 170, 14);
    this.rotAgr.setFont(new java.awt.Font("Arial", 0, 14));
     
    this.rotDesc = new JLabel("Data ocorrido:");
     this.rotDesc.setBounds(10, 101, 99, 18);
    this.rotDesc.setFont(new java.awt.Font("Arial", 0, 14));
     
     this.rotSen = new JLabel("Descrição da ocorrência:");
     this.rotSen.setBounds(10, 131, 167, 18);
     this.rotSen.setFont(new java.awt.Font("Arial", 0, 14));
   }
   
   private void criarTextos() {
     this.texData = new br.com.tucanobrasil.sis.lgraf.LData();
     this.texData.setBounds(110, 98, 493, 23);
    this.texData.setFont(new java.awt.Font("Arial", 0, 14));
     this.texData.addKeyListener(new java.awt.event.KeyListener() { public void keyTyped(java.awt.event.KeyEvent arg0) {}
       
       public void keyReleased(java.awt.event.KeyEvent arg0) {}
      public void keyPressed(java.awt.event.KeyEvent arg0) { if (arg0.getKeyCode() == 10) {
           Solicitacao.this.texDesc.requestFocus();
           return;
         }
         
       }
    });
    this.texDesc = new LRealArea();
     this.texDesc.setFont(new java.awt.Font("Arial", 0, 14));
    this.texDesc.addKeyListener(new java.awt.event.KeyListener() { public void keyTyped(java.awt.event.KeyEvent arg0) {}
       
       public void keyReleased(java.awt.event.KeyEvent arg0) {}
     public void keyPressed(java.awt.event.KeyEvent arg0) { if (arg0.getKeyCode() == 10) {}
 
       }
       
 
     });
     javax.swing.JScrollPane barDes = new javax.swing.JScrollPane(this.texDesc);
    barDes.setBounds(177, 126, 426, 51);
     
    getContentPane().add(barDes);
     
    this.texEM = new JTextField();
    this.texEM.setFont(new java.awt.Font("Arial", 0, 14));
     this.texEM.setBounds(202, 236, 68, 23);
    getContentPane().add(this.texEM);
     
    this.texSM = new JTextField();
    this.texSM.setFont(new java.awt.Font("Arial", 0, 14));
    this.texSM.setBounds(315, 235, 68, 23);
    getContentPane().add(this.texSM);
     
    this.texET = new JTextField();
    this.texET.setFont(new java.awt.Font("Arial", 0, 14));
     this.texET.setBounds(425, 233, 68, 23);
getContentPane().add(this.texET);
     
     this.texST = new JTextField();
     this.texST.setFont(new java.awt.Font("Arial", 0, 14));
     this.texST.setBounds(535, 233, 68, 23);
     getContentPane().add(this.texST);
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
   private void criarBotoes()
   {
     this.botLog = new javax.swing.JButton("Solicitar Correção");
    this.botLog.setBounds(408, 332, 195, 43);
     
    this.botLog.addActionListener(new java.awt.event.ActionListener() {
       public void actionPerformed(java.awt.event.ActionEvent arg0) {
        Solicitacao.this.envSol();
       }
     });
   }
   
   private void geral()
   {
    JLabel label_1 = new JLabel("Ocorrências / Solicitação Ponto:");
     label_1.setFont(new java.awt.Font("Serif", 1, 18));
     label_1.setBounds(180, 63, 250, 24);
    getContentPane().add(label_1);
     
     this.rotSol = new JLabel("Solicitação:");
     this.rotSol.setFont(new java.awt.Font("Arial", 0, 14));
    this.rotSol.setBounds(10, 186, 83, 18);
    getContentPane().add(this.rotSol);
     
     this.radOp1 = new JRadioButton("Apenas justificativa. Não desejo solicitar nada.");
     this.radOp1.setSelected(true);
     this.radOp1.setFont(new java.awt.Font("Arial", 0, 14));
     this.radOp1.setBounds(10, 210, 317, 18);
     getContentPane().add(this.radOp1);
     
     this.radOp2 = new JRadioButton("Corrigir ponto para:");
     this.radOp2.setSelected(true);
     this.radOp2.setFont(new java.awt.Font("Arial", 0, 14));
   this.radOp2.setBounds(10, 238, 155, 18);
    getContentPane().add(this.radOp2);
     
    this.radOp3 = new JRadioButton("Outra:");
    this.radOp3.setSelected(true);
    this.radOp3.setFont(new java.awt.Font("Arial", 0, 14));
     this.radOp3.setBounds(10, 270, 68, 18);
     getContentPane().add(this.radOp3);
     
   this.bg = new javax.swing.ButtonGroup();
     this.bg.add(this.radOp1);
     this.bg.add(this.radOp2);
   this.bg.add(this.radOp3);
     
    JLabel label_3 = new JLabel("EM:");
    label_3.setFont(new java.awt.Font("Arial", 0, 14));
    label_3.setBounds(170, 238, 29, 18);
     getContentPane().add(label_3);
     
    JLabel label_4 = new JLabel("SM:");
     label_4.setFont(new java.awt.Font("Arial", 0, 14));
     label_4.setBounds(283, 237, 29, 18);
     getContentPane().add(label_4);
     
 
     this.label_5 = new JLabel("ET:");
    this.label_5.setFont(new java.awt.Font("Arial", 0, 14));
    this.label_5.setBounds(393, 235, 29, 18);
     getContentPane().add(this.label_5);
     
     this.texOut = new LRealArea();
    this.texOut.setFont(new java.awt.Font("Arial", 0, 14));
    this.texOut.setLayout(null);
     
     this.barOut = new javax.swing.JScrollPane(this.texOut);
     this.barOut.setBounds(92, 270, 511, 51);
     
     getContentPane().add(this.barOut);
     
     JLabel label_2 = new JLabel("ST:");
     label_2.setFont(new java.awt.Font("Arial", 0, 14));
    label_2.setBounds(503, 235, 29, 18);
     getContentPane().add(label_2);
   }
   
   private void adiComps() {
     getContentPane().add(this.rotAgr);
    getContentPane().add(this.rotDesc);
     getContentPane().add(this.rotSen);
     
     getContentPane().add(this.texData);
     
     getContentPane().add(this.botLog);
     
    this.label = new JLabel(new javax.swing.ImageIcon("tucsma.png"));
     this.label.setOpaque(true);
     this.label.setBackground(java.awt.Color.BLACK);
     this.label.setBounds(219, 11, 181, 41);
     getContentPane().add(this.label);
   }
   
   boolean[] st = new boolean[3];
   
   private void proParal() { new java.util.Timer().schedule(new java.util.TimerTask() {
       public void run() {
         Solicitacao.this.rotAgr.setText("Data: " + br.com.tucanobrasil.sis.util.Tempo.formatoBrasil());
         
         if ((Solicitacao.this.st[0] != Solicitacao.this.radOp1.isSelected()) || (Solicitacao.this.st[1] != Solicitacao.this.radOp2.isSelected()) || (Solicitacao.this.st[2] != Solicitacao.this.radOp3.isSelected())) {
           Solicitacao.this.st[0] = Solicitacao.this.radOp1.isSelected();
          Solicitacao.this.st[1] = Solicitacao.this.radOp2.isSelected();
          Solicitacao.this.st[2] = Solicitacao.this.radOp3.isSelected();
           
           if (Solicitacao.this.st[0]) {
             Solicitacao.this.texEM.setEnabled(false);Solicitacao.this.texSM.setEnabled(false);
             Solicitacao.this.texET.setEnabled(false);Solicitacao.this.texST.setEnabled(false);
             
             Solicitacao.this.texOut.setEditable(false);Solicitacao.this.texOut.setEnabled(false);Solicitacao.this.texOut.setBackground(new java.awt.Color(240, 240, 240));
           }
          else if (Solicitacao.this.st[1]) {
             Solicitacao.this.texEM.setEnabled(true);Solicitacao.this.texSM.setEnabled(true);
             Solicitacao.this.texET.setEnabled(true);Solicitacao.this.texST.setEnabled(true);
             
             Solicitacao.this.texOut.setEditable(false);Solicitacao.this.texOut.setEnabled(false);Solicitacao.this.texOut.setBackground(new java.awt.Color(240, 240, 240));
           }
           else {
             Solicitacao.this.texEM.setEnabled(false);Solicitacao.this.texSM.setEnabled(false);
             Solicitacao.this.texET.setEnabled(false);Solicitacao.this.texST.setEnabled(false);
             
            Solicitacao.this.texOut.setEditable(true);Solicitacao.this.texOut.setEnabled(true);Solicitacao.this.texOut.setBackground(java.awt.Color.WHITE);
           }
         }
       }
    }, 33L, 33L);
   }
   
   public boolean dispatchKeyEvent(java.awt.event.KeyEvent e)
   {
    if ((e.getID() == 402) && 
       (e.getKeyCode() == 27)) {
      br.com.tucanobrasil.M.manager.removeKeyEventDispatcher(this);
      dispose();
       new Visual();
     }
     
    return false;
   }
 }

