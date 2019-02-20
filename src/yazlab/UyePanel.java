package yazlab;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.Desktop;
import java.io.File;
import static java.lang.Math.sqrt;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UyePanel extends javax.swing.JFrame {

    ArrayList<String> populer = new ArrayList<>();
    ArrayList<String> eniyi = new ArrayList<>();
    ArrayList<String> populerisim = new ArrayList<>();
    ArrayList<String> eniyiisim = new ArrayList<>();
    ArrayList<String> enyeni = new ArrayList<>();
    ArrayList<String> ilkoyisbn = new ArrayList<>();
    ArrayList<Integer> ilkoy = new ArrayList<>();
    ArrayList<String> sonrakioyisbn = new ArrayList<>();
    ArrayList<Integer> sonrakioy = new ArrayList<>();
    ArrayList<String> sonrakiuser = new ArrayList<>();
    ArrayList<String> kitaptut = new ArrayList<>();
    ArrayList<Integer> oytut = new ArrayList<>();
    ArrayList<Double> ortalama = new ArrayList<>();
    ArrayList<String> tut = new ArrayList<>();
    ArrayList<String> öneri = new ArrayList<>();
    ArrayList<String> öneriadi = new ArrayList<>();
    private String kullaniciadi = "root";
    private String parola = "";
    private String host = "127.0.0.1";
    private String db = "yazlab1";
    private int port = 3306;
    private Connection conn = null;
    private ResultSet rs = null;
    private Statement pst = null;

    public UyePanel() {
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
        jButton27.setEnabled(false);
        jButton28.setEnabled(false);
        jButton29.setEnabled(false);
        jButton30.setEnabled(false);
        jButton31.setEnabled(false);
        try {
            Statement ac = (Statement) conn.createStatement();
            String sq = "SELECT ISBN,count(BookRating) FROM bookratings WHERE BookRating='" + 10 + "' group by ISBN Having count(BookRating)=1 LIMIT 200,30";
            ResultSet sonuc = ac.executeQuery(sq);
            while (sonuc.next()) {
                eniyi.add(sonuc.getString("ISBN"));

            }
            for (int i = 0; i < eniyi.size(); i++) {
                String sq1 = "SELECT BookTitle,ISBN from books WHERE ISBN='" + eniyi.get(i) + "'";
                ResultSet sonuc1 = ac.executeQuery(sq1);
                while (sonuc1.next()) {
                    eniyiisim.add(sonuc1.getString("BookTitle"));
                }
            }
            jLabel3.setText(eniyiisim.get(0));
            jLabel4.setText(eniyiisim.get(1));
            jLabel5.setText(eniyiisim.get(2));
            jLabel6.setText(eniyiisim.get(3));
            jLabel7.setText(eniyiisim.get(4));
            jLabel8.setText(eniyiisim.get(5));
            jLabel9.setText(eniyiisim.get(6));
            jLabel10.setText(eniyiisim.get(7));
            jLabel11.setText(eniyiisim.get(8));
            jLabel12.setText(eniyiisim.get(9));
            String sq2 = "SELECT ISBN,count(BookRating) from bookratings Group By ISBN Having count(BookRating)>500 LIMIT 10";
            ResultSet sonuc2 = ac.executeQuery(sq2);
            while (sonuc2.next()) {
                populer.add(sonuc2.getString("ISBN"));
            }
            for (int i = 0; i < populer.size(); i++) {
                String sq3 = "SELECT BookTitle,ISBN from books WHERE ISBN='" + populer.get(i) + "'";
                ResultSet sonuc3 = ac.executeQuery(sq3);
                while (sonuc3.next()) {
                    populerisim.add(sonuc3.getString("BookTitle"));
                }
            }
            jLabel13.setText(populerisim.get(0));
            jLabel14.setText(populerisim.get(1));
            jLabel15.setText(populerisim.get(2));
            jLabel16.setText(populerisim.get(3));
            jLabel17.setText(populerisim.get(4));
            jLabel18.setText(populerisim.get(5));
            jLabel19.setText(populerisim.get(6));
            jLabel20.setText(populerisim.get(7));
            jLabel21.setText(populerisim.get(8));
            jLabel22.setText(populerisim.get(9));

            int ilanID = 1;
            String sq4 = "SELECT COUNT(*) FROM books";
            ResultSet sonucID = ac.executeQuery(sq4);
            while (sonucID.next()) {
                ilanID = sonucID.getInt(1);
            }
            ilanID = ilanID - 5;
            String sq5 = "SELECT * from books WHERE Number between'" + ilanID + "' and 999999";
            ResultSet sonuc4 = ac.executeQuery(sq5);
            while (sonuc4.next()) {
                enyeni.add(sonuc4.getString("BookTitle"));
            }
            jLabel24.setText(enyeni.get(0));
            jLabel25.setText(enyeni.get(1));
            jLabel26.setText(enyeni.get(2));
            jLabel27.setText(enyeni.get(3));
            jLabel28.setText(enyeni.get(4));

            int no = 0;
            String sq6 = "SELECT * from songiren";
            ResultSet sonuc5 = ac.executeQuery(sq6);
            while (sonuc5.next()) {
                no = sonuc5.getInt(1);
            }
            String sq7 = "SELECT * from bookratings WHERE UserID='" + no + "'";
            ResultSet sonuc6 = ac.executeQuery(sq7);
            while (sonuc6.next()) {
                ilkoyisbn.add(sonuc6.getString("ISBN"));
                ilkoy.add(sonuc6.getInt("BookRating"));
            }
            double ort1 = 0;
            for (int i = 0; i < 10; i++) {
                ort1 = (ilkoy.get(i) * ilkoy.get(i)) + ort1;
            }
            ort1 = sqrt(ort1);
            double ort2 = 0;
            double ort3 = 0;
            double toplam = 0;
            for (int i = 0; i < 10; i++) {
                String sq10 = "SELECT * from bookratings WHERE ISBN='" + ilkoyisbn.get(i) + "'";
                ResultSet sonuc9 = ac.executeQuery(sq10);
                while (sonuc9.next()) {
                    tut.add(sonuc9.getString("UserID"));
                }
            }
            System.out.println(tut.size());
            for (int i = 0; i < 10; i++) {
                String sq8 = "SELECT * from bookratings WHERE ISBN='" + ilkoyisbn.get(i) + "'";
                ResultSet sonuc7 = ac.executeQuery(sq8);
                while (sonuc7.next()) {
                    sonrakiuser.add(sonuc7.getString("UserID"));
                    sonrakioyisbn.add(sonuc7.getString("ISBN"));
                    sonrakioy.add(sonuc7.getInt("BookRating"));
                }
                if (sonrakioyisbn.size() != 0) {
                    for (int j = 0; j < sonrakiuser.size(); j++) {
                        String sq9 = "SELECT * from bookratings WHERE UserID='" + sonrakiuser.get(j) + "'";
                        ResultSet sonuc8 = ac.executeQuery(sq9);
                        while (sonuc8.next()) {
                            kitaptut.add(sonuc8.getString("ISBN"));
                            oytut.add(sonuc8.getInt("BookRating"));
                        }
                        for (int k = 0; k < oytut.size(); k++) {
                            ort3 = (oytut.get(k) * oytut.get(k)) + ort3;
                        }
                        ort3 = sqrt(ort3);
                        for (int k = 0; k < kitaptut.size(); k++) {
                            if (ilkoyisbn.contains(kitaptut.get(k))) {
                                String sql1 = "SELECT * from bookratings WHERE ISBN='" + kitaptut.get(k) + "' AND UserID= '" + no + "'";
                                ResultSet sonuc9 = ac.executeQuery(sql1);
                                int sonoy = 0;
                                while (sonuc9.next()) {
                                    sonoy = sonuc9.getInt("BookRating");
                                }
                                ort2 = (oytut.get(k) * sonoy) + ort2;
                                toplam = ort2 / (ort1 + ort3);
                                for (int m = 0; m < tut.size(); m++) {
                                    ortalama.add(m, toplam);
                                    break;
                                }
                            }
                        }

                    }
                    kitaptut.clear();
                    oytut.clear();

                }
                sonrakiuser.clear();
                sonrakioyisbn.clear();
            }

            System.out.println("--------SIRALAMA YAPIYORUZ----------- ");

            for (int i = 0; i < tut.size() - 1; i++) {
                for (int j = 0; j < tut.size() - 1; j++) {
                    if (ortalama.get(j) < ortalama.get(j + 1)) {
                        double yakala = ortalama.get(j);
                        ortalama.set(j, ortalama.get(j + 1));
                        ortalama.set(j + 1, yakala);
                        String yakala2 = tut.get(j);
                        tut.set(j, tut.get(j + 1));
                        tut.set(j + 1, yakala2);

                    }
                }
            }
            for (int i = 10; i >= 7; i--) {
                String sql2 = "SELECT * from bookratings WHERE UserID='" + tut.get(0) + "'and BookRating='" + i + "'";
                ResultSet sonucc = ac.executeQuery(sql2);
                while (sonucc.next() && öneri.size() < 5) {
                    öneri.add(sonucc.getString("ISBN"));
                }
            }
            System.out.println(öneri);
            String label = "jLabel3";
            for (int i = 0; i < öneri.size(); i++) {
                String sql3 = "SELECT * from books WHERE ISBN='" + öneri.get(i) + "'";
                ResultSet sonucc1 = ac.executeQuery(sql3);
                while (sonucc1.next()) {
                    öneriadi.add(sonucc1.getString("BookTitle"));
                }

            }
            if (öneriadi.size() > 0) {
                jLabel30.setText(öneriadi.get(0));
                jButton27.setEnabled(true);
            }
            if (öneriadi.size() > 1) {
                jLabel31.setText(öneriadi.get(1));
                jButton28.setEnabled(true);
            }
            if (öneriadi.size() > 2) {
                jLabel32.setText(öneriadi.get(2));
                jButton29.setEnabled(true);
            }
            if (öneriadi.size() > 3) {
                jLabel33.setText(öneriadi.get(3));
                jButton30.setEnabled(true);
            }
            if (öneriadi.size() > 4) {
                jLabel34.setText(öneriadi.get(4));
                jButton31.setEnabled(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UyePanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(600, 250));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("En İyi Kitaplar");

        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("En Popüler Kitaplar");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setToolTipText("");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jLabel3.setText("jLabel3");

        jLabel4.setText("jLabel4");

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        jLabel7.setText("jLabel7");

        jLabel8.setText("jLabel8");

        jLabel9.setText("jLabel9");

        jLabel10.setText("jLabel10");

        jLabel11.setText("jLabel11");

        jLabel12.setText("jLabel12");

        jLabel13.setText("jLabel13");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel14.setText("jLabel14");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel15.setText("jLabel15");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel16.setText("jLabel16");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel17.setText("jLabel17");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel18.setText("jLabel18");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel19.setText("jLabel19");
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel20.setText("jLabel20");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel21.setText("jLabel21");
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel22.setText("jLabel22");
        jLabel22.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jButton21.setText("Bütün Kitaplar");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel23.setForeground(new java.awt.Color(0, 0, 204));
        jLabel23.setText("En Yeni Kitaplar");

        jLabel24.setText("jLabel24");

        jLabel25.setText("jLabel25");

        jLabel26.setText("jLabel26");

        jLabel27.setText("jLabel27");

        jLabel28.setText("jLabel28");

        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jLabel29.setForeground(new java.awt.Color(0, 0, 204));
        jLabel29.setText("Sizin İçin Önerdiğimiz Kitaplar");

        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel32.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel33.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel34.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addGap(507, 507, 507)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)
                        .addGap(4, 4, 4)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(501, 501, 501)
                        .addComponent(jLabel13)
                        .addGap(0, 0, 0)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(590, 590, 590)
                        .addComponent(jLabel14)
                        .addGap(0, 0, 0)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)
                        .addGap(4, 4, 4)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(590, 590, 590)
                        .addComponent(jLabel15)
                        .addGap(0, 0, 0)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5)
                        .addGap(4, 4, 4)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)
                        .addGap(4, 4, 4)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(501, 501, 501)
                        .addComponent(jLabel16)
                        .addGap(0, 0, 0)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addGap(4, 4, 4)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(501, 501, 501)
                        .addComponent(jLabel17)
                        .addGap(0, 0, 0)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(590, 590, 590)
                        .addComponent(jLabel18)
                        .addGap(0, 0, 0)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8)
                        .addGap(4, 4, 4)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(590, 590, 590)
                        .addComponent(jLabel19)
                        .addGap(0, 0, 0)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9)
                        .addGap(4, 4, 4)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10)
                        .addGap(4, 4, 4)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(495, 495, 495)
                        .addComponent(jLabel20)
                        .addGap(0, 0, 0)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel11)
                        .addGap(4, 4, 4)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(495, 495, 495)
                        .addComponent(jLabel21)
                        .addGap(0, 0, 0)
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel12)
                        .addGap(4, 4, 4)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(495, 495, 495)
                        .addComponent(jLabel22)
                        .addGap(0, 0, 0)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel23)
                        .addGap(496, 496, 496)
                        .addComponent(jLabel29))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel24)
                        .addGap(4, 4, 4)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(485, 485, 485)
                        .addComponent(jLabel30)
                        .addGap(10, 10, 10)
                        .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel25)
                        .addGap(4, 4, 4)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(485, 485, 485)
                        .addComponent(jLabel31)
                        .addGap(10, 10, 10)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel26)
                        .addGap(4, 4, 4)
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(485, 485, 485)
                        .addComponent(jLabel32)
                        .addGap(10, 10, 10)
                        .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel27)
                        .addGap(4, 4, 4)
                        .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(485, 485, 485)
                        .addComponent(jLabel33)
                        .addGap(10, 10, 10)
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel28)
                        .addGap(4, 4, 4)
                        .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(485, 485, 485)
                        .addComponent(jLabel34)
                        .addGap(10, 10, 10)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton21)))
                .addContainerGap(791, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addComponent(jLabel2))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton1))
                    .addComponent(jLabel13)
                    .addComponent(jButton13))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jButton14))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jButton2)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton12)))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jButton3))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jButton4))
                    .addComponent(jLabel16)
                    .addComponent(jButton11))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton5))
                    .addComponent(jLabel17)
                    .addComponent(jButton15))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jButton16))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jButton6)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jButton18))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jButton7)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton8))
                    .addComponent(jLabel20)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton19)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jButton9))
                    .addComponent(jLabel21)
                    .addComponent(jButton20))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jButton17)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jButton10))))
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel29))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel24))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jButton22))
                    .addComponent(jLabel30)
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel25))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jButton23))
                    .addComponent(jLabel31)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel26))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jButton24))
                    .addComponent(jLabel32)
                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jButton25))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jButton26))))
                .addGap(72, 72, 72)
                .addComponent(jButton21)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        new oku().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        try {
            Desktop.getDesktop().open(new File("d:\\oku\\Gozlerini Simsiki Kapat - John Verdon.pdf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton31ActionPerformed

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
            java.util.logging.Logger.getLogger(UyePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UyePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UyePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UyePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UyePanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
