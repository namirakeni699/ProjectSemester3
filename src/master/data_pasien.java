/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import koneksi.koneksi;

/**
 *
 * @author COMPUTER
 */
public class data_pasien extends javax.swing.JFrame {

    /**
     * Creates new form data_pasien
     */
    public data_pasien() {
        initComponents();
        generatePasienID();
       
// Mengatur nilai ID yang dihasilkan pada komponen txt_idpasien
    }
    private void generatePasienID() {
    String generatedID = "";
    String query = "SELECT MAX(id_pasien) FROM data_pasien"; // Mengambil ID pasien tertinggi dari database

    try {
        Connection connection = koneksi.getKoneksi();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String maxID = resultSet.getString(1); // Mengambil ID pasien tertinggi
            if (maxID != null) {
                int lastNumber = Integer.parseInt(maxID.substring(6)); // Mengambil angka terakhir
                int newNumber = lastNumber + 1;
                generatedID = String.format("00-00-%02d", newNumber); // Format ulang ID
                txt_idpasien.setText(generatedID);
            } else {
                generatedID = "00-00-01";
                txt_idpasien.setText(generatedID);// Jika belum ada data pasien
            }
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

}

    private void tambahData() {
    String id_pasien = txt_idpasien.getText();
    String nama_pasien = txt_namapasien.getText();
    String jenis_kelamin = (String) jenisKelaminComboBox.getSelectedItem();
    String agama = txt_agama.getText();
    String tgl_lahir = txt_tgllahir.getText();
    String riwayat_penyakit = txt_riwayatsakit1.getText();
    String status = (String) cb_status.getSelectedItem();
    String nama_pasangan = txt_namapasangan.getText();
    int usia = Integer.parseInt(txt_usia.getText());
    String alamat = txt_alamat.getText();
    String pekerjaan = txt_pekerjaan.getText();
    String pendidikan = txt_pekerjaan.getText();
    String gol_darah = txt_goldarah.getText();
    String telp_ortu = txt_telportu.getText();
    String pekerjaan_ortu = txt_kerjaortu.getText();
    String telp_pasien = txt_telpon.getText();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
  
    
    String alergi_obat = txt_alergi.getText();
  
  
    // Memeriksa apakah ada input yang kosong
    if (id_pasien.isEmpty() || nama_pasien.isEmpty() || jenis_kelamin.isEmpty() || agama.isEmpty() || tgl_lahir.isEmpty() || riwayat_penyakit.isEmpty() || status.isEmpty() || nama_pasangan.isEmpty() || usia == 0 || alamat.isEmpty() || pekerjaan.isEmpty() || pendidikan.isEmpty() || gol_darah.isEmpty() || telp_ortu.isEmpty() || pekerjaan_ortu.isEmpty() || telp_pasien.isEmpty() || alergi_obat.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Harap Lengkapi Data Terlebih Dahulu!");
        return;
    }

    // Panggil koneksi
    Connection connection = koneksi.getKoneksi();

    // Query untuk memasukkan data
    String query = "INSERT INTO data_pasien (id_pasien, nama_pasien, jenis_kelamin, agama, tgl_lahir, riwayat_penyakit, status, nama_pasangan, usia, alamat, pekerjaan, pendidikan, gol_darah, telp_ortu, pekerjaan_ortu, telp_pasien,  alergi_obat) " +
                   "VALUES ('"+id_pasien+"', '"+nama_pasien+"', '"+jenis_kelamin+"', '"+agama+"', '"+tgl_lahir+"', '"+riwayat_penyakit+"', '"+status+"', '"+nama_pasangan+"', "+usia+", '"+alamat+"', '"+pekerjaan+"', '"+pendidikan+"', '"+gol_darah+"', '"+telp_ortu+"', '"+pekerjaan_ortu+"', '"+telp_pasien+"', '"+alergi_obat+"')";

    try {
        // Menyiapkan statement untuk dieksekusi
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate(query);
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
         generatePasienID();
    } catch(SQLException | HeadlessException e) {
        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
        System.out.print(e);
    } finally {
        // Lakukan apa pun yang perlu dilakukan setelah penyimpanan data
        
    }
}
    private void editData() {
    String id_pasien = txt_idpasien.getText();
    String nama_pasien = txt_namapasien.getText();
    String jenis_kelamin = (String) jenisKelaminComboBox.getSelectedItem();
    String agama = txt_agama.getText();
    String tgl_lahir = txt_tgllahir.getText();
    String riwayat_penyakit = txt_riwayatsakit1.getText();
    String status = (String) cb_status.getSelectedItem();
    String nama_pasangan = txt_namapasangan.getText();
    int usia = Integer.parseInt(txt_usia.getText());
    String alamat = txt_alamat.getText();
    String pekerjaan = txt_pekerjaan.getText();
    String pendidikan = txt_pendidikan.getText(); // Perbaiki nama komponen
    String gol_darah = txt_goldarah.getText();
    String telp_ortu = txt_telportu.getText();
    String pekerjaan_ortu = txt_kerjaortu.getText();
    String telp_pasien = txt_telpon.getText();
   
    String alergi_obat = txt_alergi.getText();
  
   

    // Memeriksa apakah ada input yang kosong
    if (id_pasien.isEmpty() || nama_pasien.isEmpty() || jenis_kelamin.isEmpty() || agama.isEmpty() || tgl_lahir.isEmpty() || riwayat_penyakit.isEmpty() || status.isEmpty() || nama_pasangan.isEmpty() || usia == 0 || alamat.isEmpty() || pekerjaan.isEmpty() || pendidikan.isEmpty() || gol_darah.isEmpty() || telp_ortu.isEmpty() || pekerjaan_ortu.isEmpty() || telp_pasien.isEmpty() || alergi_obat.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Harap Lengkapi Data Terlebih Dahulu!");
        return;
    }

    // Panggil koneksi
    Connection connection = koneksi.getKoneksi();

    // Query untuk memperbarui data
    String query = "UPDATE data_pasien SET " +
                   "nama_pasien = ?, jenis_kelamin = ?, agama = ?, tgl_lahir = ?, riwayat_penyakit = ?, status = ?, nama_pasangan = ?, usia = ?, alamat = ?, pekerjaan = ?, pendidikan = ?, gol_darah = ?, telp_ortu = ?, pekerjaan_ortu = ?, telp_pasien = ?, alergi_obat = ?" +
                   "WHERE id_pasien = ?";

    try {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nama_pasien);
        preparedStatement.setString(2, jenis_kelamin);
        preparedStatement.setString(3, agama);
        preparedStatement.setString(4, tgl_lahir);
        preparedStatement.setString(5, riwayat_penyakit);
        preparedStatement.setString(6, status);
        preparedStatement.setString(7, nama_pasangan);
        preparedStatement.setInt(8, usia);
        preparedStatement.setString(9, alamat);
        preparedStatement.setString(10, pekerjaan);
        preparedStatement.setString(11, pendidikan); // Perbaiki nama kolom
        preparedStatement.setString(12, gol_darah);
        preparedStatement.setString(13, telp_ortu);
        preparedStatement.setString(14, pekerjaan_ortu);
        preparedStatement.setString(15, telp_pasien);
        preparedStatement.setString(16, alergi_obat);
        preparedStatement.setString(17, id_pasien); // Gunakan id pasien untuk memperbarui data yang sesuai

        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    } catch(SQLException e) {
        JOptionPane.showMessageDialog(null, "Data Gagal Diperbarui: " + e.getMessage());
    } finally {
        // Lakukan apa pun yang perlu dilakukan setelah penyimpanan data
    }
}
private void resetFields() {
    txt_namapasien.setText("");
    txt_agama.setText("");
    txt_tgllahir.setText("");
    txt_riwayatsakit1.setText("");
    txt_namapasangan.setText("");
    txt_usia.setText("");
    txt_alamat.setText("");
    txt_pekerjaan.setText("");
    txt_pendidikan.setText("");
    txt_goldarah.setText("");
    txt_telportu.setText("");
    txt_kerjaortu.setText("");
    txt_telpon.setText("");
 
    txt_alergi.setText("");
  
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_idpasien = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        txt_tgllahir = new javax.swing.JTextField();
        txt_agama = new javax.swing.JTextField();
        txt_namapasien = new javax.swing.JTextField();
        txt_goldarah = new javax.swing.JTextField();
        txt_telpon = new javax.swing.JTextField();
        txt_pendidikan = new javax.swing.JTextField();
        txt_pekerjaan = new javax.swing.JTextField();
        txt_kerjaortu = new javax.swing.JTextField();
        txt_telportu = new javax.swing.JTextField();
        txt_namapasangan = new javax.swing.JTextField();
        txt_usia = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        cb_status = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jenisKelaminComboBox = new javax.swing.JComboBox<>();
        txt_riwayatsakit1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_alergi = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_idpasien.setEditable(false);
        txt_idpasien.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_idpasien.setText("\n");
        txt_idpasien.setBorder(null);
        getContentPane().add(txt_idpasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 320, 40));

        txt_alamat.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_alamat.setText("\n");
        txt_alamat.setBorder(null);
        getContentPane().add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, 320, 40));

        txt_tgllahir.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_tgllahir.setText("\n");
        txt_tgllahir.setBorder(null);
        getContentPane().add(txt_tgllahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 320, 40));

        txt_agama.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_agama.setText("\n");
        txt_agama.setBorder(null);
        getContentPane().add(txt_agama, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 320, 40));

        txt_namapasien.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_namapasien.setText("\n");
        txt_namapasien.setBorder(null);
        txt_namapasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namapasienActionPerformed(evt);
            }
        });
        getContentPane().add(txt_namapasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 320, 40));

        txt_goldarah.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_goldarah.setText("\n");
        txt_goldarah.setBorder(null);
        getContentPane().add(txt_goldarah, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, 320, 30));

        txt_telpon.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_telpon.setText("\n");
        txt_telpon.setBorder(null);
        getContentPane().add(txt_telpon, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 430, 320, 30));

        txt_pendidikan.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_pendidikan.setText("\n");
        txt_pendidikan.setBorder(null);
        getContentPane().add(txt_pendidikan, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 350, 320, 40));

        txt_pekerjaan.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_pekerjaan.setText("\n");
        txt_pekerjaan.setBorder(null);
        getContentPane().add(txt_pekerjaan, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 270, 320, 40));

        txt_kerjaortu.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_kerjaortu.setBorder(null);
        getContentPane().add(txt_kerjaortu, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 200, 320, 30));

        txt_telportu.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_telportu.setBorder(null);
        txt_telportu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_telportuActionPerformed(evt);
            }
        });
        getContentPane().add(txt_telportu, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 110, 320, 40));

        txt_namapasangan.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_namapasangan.setBorder(null);
        getContentPane().add(txt_namapasangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 590, 320, 40));

        txt_usia.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_usia.setText("\n");
        txt_usia.setBorder(null);
        getContentPane().add(txt_usia, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 320, 40));

        btnTambah.setBackground(new java.awt.Color(255, 255, 255));
        btnTambah.setText(".");
        btnTambah.setBorder(null);
        btnTambah.setBorderPainted(false);
        btnTambah.setContentAreaFilled(false);
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        getContentPane().add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 580, 170, 50));

        btnEdit.setBackground(new java.awt.Color(255, 255, 255));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText(".\n");
        btnEdit.setBorderPainted(false);
        btnEdit.setContentAreaFilled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        getContentPane().add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 580, 180, 50));

        cb_status.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cb_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Belum Menikah", "Menikah", "Cerai Hidup", "Cerai Mati", " " }));
        getContentPane().add(cb_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 580, 340, 50));

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText(".\n");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 50, 60));

        jenisKelaminComboBox.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jenisKelaminComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan", " " }));
        getContentPane().add(jenisKelaminComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 340, 50));

        txt_riwayatsakit1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_riwayatsakit1.setText("\n");
        txt_riwayatsakit1.setBorder(null);
        getContentPane().add(txt_riwayatsakit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 500, 320, 40));

        txt_alergi.setColumns(20);
        txt_alergi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_alergi.setRows(5);
        txt_alergi.setBorder(null);
        jScrollPane2.setViewportView(txt_alergi);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 290, 330, 100));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/input_pasien.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         master.table_pasien h = new table_pasien();
        h.setVisible(true);
     this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        tambahData();
        resetFields();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        editData();
    }//GEN-LAST:event_btnEditActionPerformed

    private void txt_namapasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namapasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namapasienActionPerformed

    private void txt_telportuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telportuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_telportuActionPerformed

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
            java.util.logging.Logger.getLogger(data_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_pasien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnEdit;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cb_status;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jenisKelaminComboBox;
    public static javax.swing.JTextField txt_agama;
    public static javax.swing.JTextField txt_alamat;
    public static javax.swing.JTextArea txt_alergi;
    public static javax.swing.JTextField txt_goldarah;
    public static javax.swing.JTextField txt_idpasien;
    public static javax.swing.JTextField txt_kerjaortu;
    public static javax.swing.JTextField txt_namapasangan;
    public static javax.swing.JTextField txt_namapasien;
    public static javax.swing.JTextField txt_pekerjaan;
    public static javax.swing.JTextField txt_pendidikan;
    public static javax.swing.JTextField txt_riwayatsakit1;
    public static javax.swing.JTextField txt_telpon;
    public static javax.swing.JTextField txt_telportu;
    public static javax.swing.JTextField txt_tgllahir;
    public static javax.swing.JTextField txt_usia;
    // End of variables declaration//GEN-END:variables
}
