/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import Utils.Auth;
import Utils.MsgBox;
import Utils.XImage;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import model.ChuyenDe;
import service.ChuyenDeService;

/**
 *
 * @author manhnt
 */
public class ViewChuyenDe2 extends javax.swing.JDialog {
    private JFileChooser fileChoose = new JFileChooser();
    private DefaultTableModel dtm = new DefaultTableModel();
    private ChuyenDeService service = new ChuyenDeService();
    private static int index = -1;

    /**
     * Creates new form ViewChuyeDe2
     */
    public ViewChuyenDe2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }
    void init() {
        setIconImage(XImage.getAppicon());
        setLocationRelativeTo(null);
        setTitle("EduSys - Quản lý chuyên đề");
        fillToTable(service.getAll());
        updateSataus();
    }
    private void fillToTable(List<ChuyenDe> list) {
        dtm = (DefaultTableModel) this.tblChuyenDe.getModel();
        dtm.setRowCount(0);
        for (ChuyenDe cd : list) {
            dtm.addRow(cd.rowDataCD());
        }
    }

    private void showData(int index) {
        ChuyenDe cd = service.getAll().get(index);
        txtMaChuyenDe.setText(cd.getMaChuyenDe());
        txtHocPhi.setText(cd.getHocPhi() + "");
        txtMoTa.setText(cd.getMoTa());
        txtTenChuyenDe.setText(cd.getTenChuyenDe());
        txtThoiLuong.setText(cd.getThoiLuong() + "");
        if (cd.getHinh() != null) {
            lblHinh1.setToolTipText(cd.getHinh());
            lblHinh1.setIcon(XImage.read(cd.getHinh()));
        }
    }

    private void setForm() {
        index = tblChuyenDe.getSelectedRow();
        updateSataus();
        if (index != -1) {
            showData(index);
          
        }
    }

    private void clearForm() {
        txtHocPhi.setText("");
        txtMaChuyenDe.setText("");
        txtMoTa.setText("");
        txtTenChuyenDe.setText("");
        txtThoiLuong.setText("");
        lblHinh1.setIcon(null);
        index = -1;
        updateSataus();
    }

    private ChuyenDe getForm() {
        String maChuyenDe = txtMaChuyenDe.getText().trim();
        String tenChuyenDe = txtTenChuyenDe.getText().trim();
        String hocPhi = txtHocPhi.getText().trim();
        String thoiLuong = txtThoiLuong.getText().trim();
        String moTa = txtMoTa.getText().trim();
        String hinh = lblHinh1.getToolTipText();
        float hocPhi2 = 0;

        if (Utils.Validate.isEmpty(maChuyenDe)) {
            MsgBox.aleart(this, "Mã chuyên đề không được để trống");
            txtMaChuyenDe.requestFocus();
            return null;
        } else if (Utils.Validate.checkLength(maChuyenDe, 5)) {
            MsgBox.aleart(this, "Mã chuyên đề chỉ gồm 5 kí tự");
            txtMaChuyenDe.requestFocus();
            return null;
        } 

        if (Utils.Validate.isEmpty(tenChuyenDe)) {
            MsgBox.aleart(this, "Tên chuyên đề không được để trống");
            txtTenChuyenDe.requestFocus();
            return null;
        } else if (Utils.Validate.checkLength(tenChuyenDe, 50)) {
            MsgBox.aleart(this, "Tên chuyên đề dài nhất 50 kí tự");
            txtTenChuyenDe.requestFocus();
            return null;
        }

        if (Utils.Validate.isEmpty(thoiLuong)) {
            MsgBox.aleart(this, "Thời lượng không được để trống");
            txtThoiLuong.requestFocus();
            return null;
        } else if (!Utils.Validate.isNumber(thoiLuong)) {
            MsgBox.aleart(this, "Thời lượng phải là số");
            txtThoiLuong.requestFocus();
            return null;
        }

        if (Utils.Validate.isEmpty(hocPhi)) {
            MsgBox.aleart(this, "Học phí không được để trống");
            txtHocPhi.requestFocus();
            return null;
        } else {
            try {
                hocPhi2 = Float.parseFloat(hocPhi);
            } catch (Exception e) {
                MsgBox.aleart(this, "Học phí phải là số");
                txtHocPhi.requestFocus();
                return null;
            }
        }

        if (Utils.Validate.isEmpty(moTa)) {
            MsgBox.aleart(this, "Học phí không được để trống");
            txtMoTa.requestFocus();
            return null;
        }
        return new ChuyenDe(maChuyenDe, tenChuyenDe, hocPhi2, Integer.parseInt(thoiLuong), hinh, moTa);
    }

    private void insert() {
        ChuyenDe cd = getForm();

        if (cd == null) {
            return;
        }
        if (service.checkChuyenDe(cd.getMaChuyenDe()) == 1) {
            MsgBox.aleart(this, "Mã chuyên đề " + cd.getMaChuyenDe() + " đã tồn tại");
            return;
        }

        if (service.insertCD(cd) == 1) {
            fillToTable(service.getAll());
            MsgBox.aleart(this, "Thêm thành công 1 chuyên đề mới");
            clearForm();
        } else {
            MsgBox.aleart(this, "Thêm chuyên đề thất bại");
        }
    }

    private void delete() {
        String maChuyenDe = txtMaChuyenDe.getText().trim();
        if(!Auth.isManager()){
             MsgBox.aleart(this, "Chỉ Trưởng phòng mới được phép xóa");
             return;
        }
        int ketQua = service.deleteCD(maChuyenDe);
        switch (ketQua) {
            case 1:
                fillToTable(service.getAll());
                MsgBox.aleart(this, "Xóa thành công chuyen de : " + maChuyenDe);
                clearForm();
                break;
            case 0:
                MsgBox.aleart(this, "Chuyên dê không tồn tại");
                clearForm();
                break;
            default:
                MsgBox.aleart(this, "Chuyên đề đã có khóa học không thẻ xóa : " + maChuyenDe);
                break;
        }
    }

    private void update() {
        ChuyenDe cd = getForm();
        if (cd == null) {
            return;
        }
        int ketQua = service.updateCD(cd);
        switch (ketQua) {
            case 1 -> {
                fillToTable(service.getAll());
                MsgBox.aleart(this, "Sửa thành công chuyen de : " + cd.getMaChuyenDe());
                clearForm();
            }
            case 0 -> {
                MsgBox.aleart(this, "Chuyên đề không tồn tại");
                clearForm();
            }
            default ->
                MsgBox.aleart(this, "Sửa thất bại : " + cd.getMaChuyenDe());
        }
    }

    private void getAnh() {
        if (fileChoose.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChoose.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lblHinh1.setIcon(icon);
            lblHinh1.setToolTipText(file.getName());
        }
    }

    private void first() {
        index = 0;
        showData(index);
        tblChuyenDe.setRowSelectionInterval(index, index);
        updateSataus();
    }

    private void prev() {
        if (index > 0) {
            index--;
            showData(index);
            tblChuyenDe.setRowSelectionInterval(index, index);
            updateSataus();
        }

    }

    private void next() {
        if (index < tblChuyenDe.getRowCount() - 1) {
            index++;
            showData(index);
            tblChuyenDe.setRowSelectionInterval(index, index);
            updateSataus();
        }
    }

    private void last() {
        index = tblChuyenDe.getRowCount() - 1;
        showData(index);
        tblChuyenDe.setRowSelectionInterval(index, index);
        updateSataus();
    }

    private void updateSataus() {
        boolean edit = (this.index >= 0);
        boolean first = (this.index == 0);
        boolean last = (this.index == tblChuyenDe.getRowCount() - 1);
        lblHinh1.setToolTipText("");
        txtMaChuyenDe.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnLast.setEnabled(edit && !last);
        btnNext.setEnabled(edit && !last);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTile = new javax.swing.JLabel();
        lblLogo1 = new javax.swing.JLabel();
        lblHinh1 = new javax.swing.JLabel();
        lblMCD1 = new javax.swing.JLabel();
        txtMaChuyenDe = new javax.swing.JTextField();
        lblTenCD1 = new javax.swing.JLabel();
        txtTenChuyenDe = new javax.swing.JTextField();
        lblThoiLuong1 = new javax.swing.JLabel();
        txtThoiLuong = new javax.swing.JTextField();
        lblHocPhi1 = new javax.swing.JLabel();
        txtHocPhi = new javax.swing.JTextField();
        lblMoTa1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChuyenDe = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTile.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTile.setForeground(new java.awt.Color(51, 0, 255));
        lblTile.setText("QUẢN LÝ CHUYÊN ĐỀ");

        lblLogo1.setText("Hình logo");

        lblHinh1.setText("Ảnh");
        lblHinh1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinh1MouseClicked(evt);
            }
        });

        lblMCD1.setText("Mã chuyên đề");

        lblTenCD1.setText("Tên chuyên đề");

        lblThoiLuong1.setText("Thời lượng (giờ)");

        lblHocPhi1.setText("Học phí");

        lblMoTa1.setText("Mô tả chuyên đề :");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnFirst.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrev.setText("<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        tblChuyenDe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ CD", "TÊN CD", "HỌC PHÍ", "THỜI LƯỢNG", "NGÀY TẠO", "HÌNH", "MÔ TẢ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChuyenDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblChuyenDeMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblChuyenDe);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblTile, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(588, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMoTa1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLogo1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(btnThem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMoi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnFirst)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPrev)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNext)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLast))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblHinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtThoiLuong, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenChuyenDe, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMCD1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTenCD1)
                                            .addComponent(lblThoiLuong1)
                                            .addComponent(lblHocPhi1)))
                                    .addComponent(txtMaChuyenDe, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1))
                        .addGap(32, 32, 32))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTile, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLogo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMCD1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTenCD1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblThoiLuong1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblHocPhi1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblMoTa1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblHinh1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinh1MouseClicked
        getAnh();
    }//GEN-LAST:event_lblHinh1MouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblChuyenDeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChuyenDeMousePressed
        if (evt.getClickCount() == 2) {
            setForm();
        }
    }//GEN-LAST:event_tblChuyenDeMousePressed

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
            java.util.logging.Logger.getLogger(ViewChuyenDe2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewChuyenDe2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewChuyenDe2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewChuyenDe2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewChuyenDe2 dialog = new ViewChuyenDe2(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinh1;
    private javax.swing.JLabel lblHocPhi1;
    private javax.swing.JLabel lblLogo1;
    private javax.swing.JLabel lblMCD1;
    private javax.swing.JLabel lblMoTa1;
    private javax.swing.JLabel lblTenCD1;
    private javax.swing.JLabel lblThoiLuong1;
    private javax.swing.JLabel lblTile;
    private javax.swing.JTable tblChuyenDe;
    private javax.swing.JTextField txtHocPhi;
    private javax.swing.JTextField txtMaChuyenDe;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenChuyenDe;
    private javax.swing.JTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables
}
