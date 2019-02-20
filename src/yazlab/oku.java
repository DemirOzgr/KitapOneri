package yazlab;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import static java.awt.AlphaComposite.Clear;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class oku extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    Object[] kolon = {"ISBN", "Kitap Adı", "Yazar", "Yıl", "Yayın Evi", "URLS", "URLM", "URLL"};
    Object[] satir = new Object[8];
    private String kullaniciadi = "root";
    private String parola = "";
    private String host = "127.0.0.1";
    private String db = "yazlab1";
    private int port = 3306;
    private Connection conn = null;
    private ResultSet rs = null;
    private Statement pst = null;
    int limit = 0;
    int limits = 10;
    ArrayList<String> resim = new ArrayList<>();

    public oku() {

        String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.db;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("mysql connector yok");
            e.printStackTrace();
        }
        try {
            this.conn = (Connection) DriverManager.getConnection(url, kullaniciadi, parola);
        } catch (SQLException e) {
            System.out.println("Baglanti basarisiz");
            e.printStackTrace();
        }
        if (conn != null) {
            System.out.println("basardik");
        } else {
            System.out.println("basarisiz");
        }
        initComponents();
        jScrollPane1.setViewportView(jTable1);
        jTable1.setModel(model);
        model.setColumnIdentifiers(kolon);
        jButton4.setEnabled(false);
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 250));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("OKUMALIK GETIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("<- Onceki");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Sonraki ->");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("KITABI OKU");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(43, 43, 43)
                .addComponent(jButton2)
                .addGap(44, 44, 44)
                .addComponent(jButton3)
                .addGap(58, 58, 58)
                .addComponent(jButton4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        model.getDataVector().removeAllElements();
        jTable1.repaint();
        ArrayList<String> yeni = new ArrayList<>();
        try {
            Statement ac = (Statement) conn.createStatement();
            String sq2 = "SELECT * FROM books LIMIT " + limit + " , " + limits + " ";
            ResultSet sonuc2 = ac.executeQuery(sq2);
            while (sonuc2.next()) {
                yeni.add(sonuc2.getString("ISBN"));
                resim.add(sonuc2.getString("ImageURLL"));
                satir[0] = sonuc2.getString("ISBN");
                satir[1] = sonuc2.getString("BookTitle");
                satir[2] = sonuc2.getString("BookAuthor");
                satir[3] = sonuc2.getString("YearOfPublication");
                satir[4] = sonuc2.getString("Publisher");
                satir[5] = sonuc2.getString("ImageURLS");
                satir[6] = sonuc2.getString("ImageURLM");
                satir[7] = sonuc2.getString("ImageURLL");
                model.addRow(satir);
            }
        } catch (SQLException ex) {
            Logger.getLogger(oku.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        model.getDataVector().removeAllElements();
        model.setRowCount(0);
        jTable1.repaint();
        ArrayList<String> yeni = new ArrayList<>();

        limit = limit + 10;
        try {
            Statement ac = (Statement) conn.createStatement();
            String sq2 = "SELECT * FROM books LIMIT " + limit + " , " + limits + " ";
            ResultSet sonuc2 = ac.executeQuery(sq2);
            while (sonuc2.next()) {
                yeni.add(sonuc2.getString("ISBN"));
                resim.add(sonuc2.getString("ImageURLL"));
                satir[0] = sonuc2.getString("ISBN");
                satir[1] = sonuc2.getString("BookTitle");
                satir[2] = sonuc2.getString("BookAuthor");
                satir[3] = sonuc2.getString("YearOfPublication");
                satir[4] = sonuc2.getString("Publisher");
                satir[5] = sonuc2.getString("ImageURLS");
                satir[6] = sonuc2.getString("ImageURLM");
                satir[7] = sonuc2.getString("ImageURLL");
                model.addRow(satir);
            }
        } catch (SQLException ex) {
            Logger.getLogger(oku.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        jButton4.setEnabled(true);
        URL url;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String yeniadres = jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString();

        try {
            url = new URL(yeniadres);
            BufferedImage buff = null;
            buff = ImageIO.read(url);
            Image image = buff;
            ImageIcon icon = new ImageIcon(image.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
            jLabel1.setIcon(icon);
            jLabel1.setText(yeniadres);
        } catch (MalformedURLException ex) {
            Logger.getLogger(oku.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(oku.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        model.getDataVector().removeAllElements();
        model.setRowCount(0);
        jTable1.repaint();
        ArrayList<String> yeni = new ArrayList<>();

        limit = limit - 10;
        try {
            Statement ac = (Statement) conn.createStatement();
            String sq2 = "SELECT * FROM books LIMIT " + limit + " , " + limits + " ";
            ResultSet sonuc2 = ac.executeQuery(sq2);
            while (sonuc2.next()) {
                yeni.add(sonuc2.getString("ISBN"));
                resim.add(sonuc2.getString("ImageURLL"));
                satir[0] = sonuc2.getString("ISBN");
                satir[1] = sonuc2.getString("BookTitle");
                satir[2] = sonuc2.getString("BookAuthor");
                satir[3] = sonuc2.getString("YearOfPublication");
                satir[4] = sonuc2.getString("Publisher");
                satir[5] = sonuc2.getString("ImageURLS");
                satir[6] = sonuc2.getString("ImageURLM");
                satir[7] = sonuc2.getString("ImageURLL");
                model.addRow(satir);
            }
        } catch (SQLException ex) {
            Logger.getLogger(oku.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(oku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(oku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(oku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(oku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new oku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}