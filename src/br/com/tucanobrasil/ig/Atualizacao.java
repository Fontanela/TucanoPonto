/*    */ package br.com.tucanobrasil.ig;
/*    */ 
/*    */ import br.com.tucanobrasil.M;
/*    */ import br.com.tucanobrasil.sis.util.Zip;
/*    */ import java.awt.Color;
/*    */ import java.awt.Container;
/*    */ import java.awt.Font;
/*    */ import java.io.IOException;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JProgressBar;
/*    */ 
/*    */ public class Atualizacao extends JFrame
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public JProgressBar pb;
/*    */   public JLabel rot;
/*    */   public static int percent;
/*    */   
/*    */   public Atualizacao()
/*    */   {
/* 25 */     janela();
/*    */     
/* 27 */     this.rot = new JLabel("<html><b>0%");
/* 28 */     this.rot.setFont(new Font("Arial", 0, 14));
/* 29 */     this.rot.setBounds(251, 10, 170, 21);
/* 30 */     getContentPane().add(this.rot);
/*    */     
/* 32 */     this.pb = new JProgressBar();
/* 33 */     this.pb.setBounds(10, 10, 511, 26);
/*    */     
/* 35 */     getContentPane().add(this.pb);
/*    */     
/* 37 */     Timer t = new Timer();
/* 38 */     t.schedule(new TimerTask() {
/*    */       public void run() {
/* 40 */         Atualizacao.this.pb.setValue(Atualizacao.percent);
/* 41 */         Atualizacao.this.rot.setText("<html><b>" + Atualizacao.percent + "%");
/*    */         
/* 43 */         if (Atualizacao.percent == 100) {
/* 44 */           Zip.unZipIt("tucpon.zip", M.SYSDIR);
/* 45 */           Atualizacao.this.dispose();
/*    */           try
/*    */           {
/* 48 */             Runtime.getRuntime().exec("cmd /c java -jar " + M.CNAME);
/*    */           } catch (IOException e) {
/* 50 */             e.printStackTrace();
/*    */           }
/* 52 */           System.exit(0);
/*    */         }
/*    */       }
/* 55 */     }, 33L, 33L);
/*    */     
/* 57 */     setVisible(true);
/*    */   }
/*    */   
/*    */   private void janela() {
/* 61 */     setTitle("Tucano Ponto - Baixando atualização");
/* 62 */     setSize(540, 78);
/* 63 */     getContentPane().setLayout(null);
/* 64 */     setLocationRelativeTo(null);
/* 65 */     setResizable(false);
/*    */     
/* 67 */     setIconImage(new ImageIcon("32x32.png").getImage());
/* 68 */     getContentPane().setBackground(new Color(255, 255, 181));
/*    */   }
/*    */ }


/* Location:              C:\TucanoPonto\Ponto\tucpon.jar!\br\com\tucanobrasil\ig\Atualizacao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */