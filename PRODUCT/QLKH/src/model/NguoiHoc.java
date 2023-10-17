package model;

import Utils.XDate;
import java.util.Date;

/**
 *
 * @author manhnt
 */
public class NguoiHoc {

    private String maNguoiHoc;
    private String hoTen;
    private boolean gioiTinh;
    private Date ngaySinh;
    private String dienThoai;
    private String email;
    private String ghiChu;
    private NhanVien nguoiTao;
    private Date ngayDangKy;

    public NguoiHoc() {
    }

    public NguoiHoc(String maNguoiHoc) {
        this.maNguoiHoc = maNguoiHoc;
    }

    public NguoiHoc(String maNguoiHoc, String hoTen) {
        this.maNguoiHoc = maNguoiHoc;
        this.hoTen = hoTen;
    }

    public NguoiHoc(String maNguoiHoc, String hoTen, boolean gioiTinh, Date ngaySinh, String dienThoai, String email, String ghiChu, NhanVien nguoiTao, Date ngayDangKy) {
        this.maNguoiHoc = maNguoiHoc;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.dienThoai = dienThoai;
        this.email = email;
        this.ghiChu = ghiChu;
        this.nguoiTao = nguoiTao;
        this.ngayDangKy = ngayDangKy;
    }

    public NguoiHoc(String maNguoiHoc, String hoTen, boolean gioiTinh, Date ngaySinh, String dienThoai, String email, String ghiChu, NhanVien nguoiTao) {
        this.maNguoiHoc = maNguoiHoc;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.dienThoai = dienThoai;
        this.email = email;
        this.ghiChu = ghiChu;
        this.nguoiTao = nguoiTao;
    }

    public NguoiHoc(String maNguoiHoc, String hoTen, boolean gioiTinh, Date ngaySinh, String dienThoai, String email) {
        this.maNguoiHoc = maNguoiHoc;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.dienThoai = dienThoai;
        this.email = email;
    }

    public String getMaNguoiHoc() {
        return maNguoiHoc;
    }

    public void setMaNguoiHoc(String maNguoiHoc) {
        this.maNguoiHoc = maNguoiHoc;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public NhanVien getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(NhanVien nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Date getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public Object[] rowDataNH() {
        return new Object[]{
            maNguoiHoc,
            hoTen,
            gioiTinh ? "Nam" : "Nữ",
            XDate.toString(ngaySinh, "dd-MM-yyyy"),
            dienThoai,
            email,
            ghiChu,
            nguoiTao.getMaNhanVien(),
            XDate.toString(ngayDangKy, "dd-MM-yyyy")
        };
    }

    public Object[] rowDataNH2() {
        return new Object[]{
            maNguoiHoc,
            hoTen,
            gioiTinh ? "Nam" : "Nữ",
            XDate.toString(ngaySinh, "dd-MM-yyyy"),
            dienThoai,
            email
        };
    }
}
