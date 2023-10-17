/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import Utils.Auth;
import Utils.MsgBox;
import Utils.XDate;
import Utils.XImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.NguoiHoc;
import service.NguoiHocService;

/**
 *
 * @author manhnt
 */
public class ViewNguoiHoc extends javax.swing.JDialog {

    private final NguoiHocService service = new NguoiHocService();
    private DefaultTableModel dtm = new DefaultTableModel();
    private static List<NguoiHoc> listNguoiHoc = new ArrayList<>();
    private static int index = -1;

    /**
     * Creates new form ViewNguoiHoc
     */
    public ViewNguoiHoc(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();

    }

    void init() {
        setIconImage(XImage.getAppicon());
        setLocationRelativeTo(null);
        setTitle("EduSys - Quản lý Người học");
        listNguoiHoc = service.getAll();
        fillToTbl(listNguoiHoc);
        updateSataus();
    }

    private void fillToTbl(List<NguoiHoc> list) {
        dtm = (DefaultTableModel) this.tblHienThi.getModel();
        dtm.setRowCount(0);
        for (NguoiHoc nh : list) {
            dtm.addRow(nh.rowDataNH());
        }
    }

    private void showData(int index) {
        NguoiHoc nh = listNguoiHoc.get(index);
        txtDienThoai.setText(nh.getDienThoai());
        txtEmail.setText(nh.getEmail());
        txtGhiChu.setText(nh.getGhiChu());
        txtHoTen.setText(nh.getHoTen());
        txtMaNH.setText(nh.getMaNguoiHoc());
        txtNgaySinh.setText(XDate.toString(nh.getNgaySinh(), "dd-MM-yyyy"));
        rdoFemale.setSelected(!nh.isGioiTinh());
        rdoMale.setSelected(nh.isGioiTinh());

    }

    private void setForm() {
        index = tblHienThi.getSelectedRow();
        updateSataus();
        if (index != -1) {
            showData(index);
            tabs.setSelectedIndex(0);
        }
    }

    private void clearForm() {
        txtDienThoai.setText("");
        txtEmail.setText("");
        txtGhiChu.setText("");
        txtHoTen.setText("");
        txtMaNH.setText("");
        txtNgaySinh.setText("");
        rdoMale.setSelected(true);
        index = -1;
        updateSataus();
    }

    private void first() {
        index = 0;
        showData(index);
        tblHienThi.setRowSelectionInterval(index, index);
        updateSataus();
    }

    private void prev() {
        if (index > 0) {
            index--;
            showData(index);
            tblHienThi.setRowSelectionInterval(index, index);
            updateSataus();
        }

    }

    private void next() {
        if (index < tblHienThi.getRowCount() - 1) {
            index++;
            showData(index);
            tblHienThi.setRowSelectionInterval(index, index);
            updateSataus();
        }
    }

    private void last() {
        index = tblHienThi.getRowCount() - 1;
        showData(index);
        tblHienThi.setRowSelectionInterval(index, index);
        updateSataus();
    }

    private void updateSataus() {
        boolean edit = (this.index >= 0);
        boolean first = (this.index == 0);
        boolean last = (this.index == tblHienThi.getRowCount() - 1);

        txtMaNH.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnLast.setEnabled(edit && !last);
        btnNext.setEnabled(edit && !last);
    }

    private NguoiHoc getForm() {
        String maNguoiHoc = txtMaNH.getText().toUpperCase().trim();
        String tenNguoiHoc = txtHoTen.getText().trim();
        String ngaySinh = txtNgaySinh.getText().trim();
        String dienThoai = txtDienThoai.getText().trim();
        String email = txtEmail.getText().toLowerCase().trim();
        String ghiChu = txtGhiChu.getText().trim();
        boolean gioiTinh = rdoMale.isSelected() ? true : false;
        Date date = null;

        if (Utils.Validate.isEmpty(maNguoiHoc)) {
            MsgBox.aleart(this, "Mã Người Học không được để trống");
            txtMaNH.requestFocus();
            return null;
        } else {
            if (!Utils.Validate.isMaHocVien(maNguoiHoc)) {
                MsgBox.aleart(this, "Mã Người Học sai định dạng PH12322 ");
                txtMaNH.requestFocus();
                return null;
            }

        }

        if (Utils.Validate.isEmpty(tenNguoiHoc)) {
            MsgBox.aleart(this, "Tên Người Học không được để trống");
            txtHoTen.requestFocus();
            return null;
        } else {
            if (!Utils.Validate.isName(tenNguoiHoc)) {
                MsgBox.aleart(this, "Tên Người Học phải là các chữ cái ");
                txtHoTen.requestFocus();
                return null;
            }
        }

        if (Utils.Validate.isEmpty(dienThoai)) {
            MsgBox.aleart(this, "Số điện thoại không được để trống");
            txtDienThoai.requestFocus();
            return null;
        } else {
            if (!Utils.Validate.isPhoneNumber(dienThoai)) {
                MsgBox.aleart(this, "Số điện thoại phải là các số và bắt đâu 0 và 10 chữ số");
                txtDienThoai.requestFocus();
                return null;
            }
        }
        if (Utils.Validate.isEmpty(ngaySinh)) {
            MsgBox.aleart(this, "Ngày sinh không được để trống");
            txtNgaySinh.requestFocus();
            return null;
        } else {
            if (!Utils.Validate.isDate(ngaySinh)) {
                MsgBox.aleart(this, "Ngày sinh sại định dang dd-MM-yyyy");
                txtNgaySinh.requestFocus();
                return null;
            } else {
                if (!Utils.Validate.isDate(ngaySinh)) {
                    MsgBox.aleart(this, "Ngày Sinh sai định dạng dd-MM-yyyy");
                    txtNgaySinh.requestFocus();
                    return null;
                }
                try {
                    XDate.toDate(ngaySinh, "dd-MM-yyyy");
                } catch (Exception e) {
                    MsgBox.aleart(this, "Ngày hoặc Tháng hoặc Năm sai ");
                    txtNgaySinh.requestFocus();
                    return null;
                }
            }
        }
        date = XDate.convertDateFormat(ngaySinh, "MM-dd-yyyy");
        if (Utils.Validate.isEmpty(email)) {
            MsgBox.aleart(this, "Email không được để trống");
            txtEmail.requestFocus();
            return null;
        } else {
            if (!Utils.Validate.isEmail(email)) {
                MsgBox.aleart(this, "Email sai định dạng");
                txtEmail.requestFocus();
                return null;
            }
        }
        return new NguoiHoc(maNguoiHoc, tenNguoiHoc, gioiTinh, date, dienThoai, email, ghiChu, Auth.nv);

    }

    private void search() {
        String text = txtTim.getText().trim();
        if (Utils.Validate.isEmpty(text)) {
            MsgBox.aleart(this, "Hẫy điền thông tin cần tìm kiếm");
            return;
        }
        try {
            listNguoiHoc = service.searchNH(text);
            fillToTbl(listNguoiHoc);
        } catch (Exception e) {
            MsgBox.aleart(this, "Lỗi tìm kiếm");
        }
    }

    private void insert() {
        NguoiHoc nh = getForm();
        if (nh == null) {
            return;
        }

        if (service.checkNH(nh.getMaNguoiHoc()) == 1) {
            MsgBox.aleart(this, "Mã Người học đã tồn tại");
            return;
        }
        int ketQua = service.insertNH(nh);
        if (ketQua == 1) {
            MsgBox.aleart(this, "Thêm thành người học : " + nh.getMaNguoiHoc());
            listNguoiHoc = service.getAll();
            fillToTbl(listNguoiHoc);
            clearForm();
        } else {
            MsgBox.aleart(this, "Thêm thất bại người học : " + nh.getMaNguoiHoc());
        }
    }

    private void update() {
        NguoiHoc nguoiHoc = getForm();
        if(nguoiHoc == null){
            return;
        }
        int ketQua = service.updateNH(nguoiHoc);
       if (ketQua == 1) {
            MsgBox.aleart(this, "Sửa thành công người học : " + nguoiHoc.getMaNguoiHoc());
            listNguoiHoc = service.getAll();
            fillToTbl(listNguoiHoc);
            clearForm();
        } else {
            MsgBox.aleart(this, "Sửa thất bại người học : " + nguoiHoc.getMaNguoiHoc());
        }
    }

    private void delete() {
        String maNguoiHoc = txtMaNH.getText().trim();
        int ketQua = service.deleteNH(maNguoiHoc);
        if (ketQua == 1) {
            MsgBox.aleart(this, "Xóa thành công người học : " + maNguoiHoc);
            listNguoiHoc = service.getAll();
            fillToTbl(listNguoiHoc);
            clearForm();
        } else {
            MsgBox.aleart(this, "Người học đã có khóa học không thể xóa : " + maNguoiHoc);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGender = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lblMaNH = new javax.swing.JLabel();
        txtMaNH = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        lblMaNH1 = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        rdoMale = new javax.swing.JRadioButton();
        rdoFemale = new javax.swing.JRadioButton();
        lblNgaySinh = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        lblNgaySinh1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblNgaySinh2 = new javax.swing.JLabel();
        txtDienThoai = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblTile = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHienThi = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("QUẢN LÝ NGƯỜI HỌC");

        lblMaNH.setText("Mã người học");

        lblMaNH1.setText("Họ và Tên");

        lblGioiTinh.setText("Giới tính");

        btnGender.add(rdoMale);
        rdoMale.setSelected(true);
        rdoMale.setText("Nam");

        btnGender.add(rdoFemale);
        rdoFemale.setText("Nữ");

        lblNgaySinh.setText("Ngày sinh");

        lblNgaySinh1.setText("Địa chỉ email");

        lblNgaySinh2.setText("Điện thoại");

        jLabel2.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMoi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdoMale, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblNgaySinh2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(125, 125, 125)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgaySinh)
                                    .addComponent(txtEmail)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNgaySinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(txtMaNH)
                            .addComponent(txtHoTen)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaNH)
                                    .addComponent(lblMaNH1))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(29, 29, 29))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblMaNH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMaNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblMaNH1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGioiTinh)
                    .addComponent(lblNgaySinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoMale)
                    .addComponent(rdoFemale)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgaySinh2)
                    .addComponent(lblNgaySinh1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        tabs.addTab("CẬP NHẬT", jPanel1);

        lblTile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTile.setText("TÌM KIẾM");

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnTim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tblHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ NH", "HỌ VÀ TÊN", "GIỚI TÍNH", "NGÀY SINH", "ĐIỆN THOẠI", "EMAIL", "GHI CHÚ", "NGƯỜI TẠO", "NGAY ĐĂNG KÝ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHienThiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHienThi);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTile)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(178, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabs.addTab("DANH SÁCH", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        search();
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

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

    private void tblHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHienThiMouseClicked
        if (evt.getClickCount() == 2) {
            setForm();
        }
    }//GEN-LAST:event_tblHienThiMouseClicked

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
            java.util.logging.Logger.getLogger(ViewNguoiHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewNguoiHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewNguoiHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewNguoiHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewNguoiHoc dialog = new ViewNguoiHoc(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup btnGender;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblMaNH;
    private javax.swing.JLabel lblMaNH1;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblNgaySinh1;
    private javax.swing.JLabel lblNgaySinh2;
    private javax.swing.JLabel lblTile;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblHienThi;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNH;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
