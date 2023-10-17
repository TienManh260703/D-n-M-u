
package model;

import Utils.XDate;
import java.util.Date;

/**
 *
 * @author manhnt
 */
public class KhoaHoc {

    private int maKhoaHoc;
    private ChuyenDe maChuyenDe;
    private float hocPhi;
    private int thoiLuong;
    private Date ngayKhaiGiang;
    private Date ngayTao;
    private String ghiChu;
    private NhanVien nguoiTao;

    public KhoaHoc() {
    }

    public KhoaHoc(int maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public KhoaHoc(ChuyenDe maChuyenDe, float hocPhi, int thoiLuong, Date ngayDangKy, Date ngayTao, String ghiChu, NhanVien nguoiTao) {
        this.maChuyenDe = maChuyenDe;
        this.hocPhi = hocPhi;
        this.thoiLuong = thoiLuong;
        this.ngayKhaiGiang = ngayDangKy;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
        this.nguoiTao = nguoiTao;
    }

    public KhoaHoc(int maKhoaHoc, ChuyenDe maChuyenDe, float hocPhi, int thoiLuong, Date ngayKhaiGiang, Date ngayTao, String ghiChu, NhanVien nguoiTao) {
        this.maKhoaHoc = maKhoaHoc;
        this.maChuyenDe = maChuyenDe;
        this.hocPhi = hocPhi;
        this.thoiLuong = thoiLuong;
        this.ngayKhaiGiang = ngayKhaiGiang;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
        this.nguoiTao = nguoiTao;
    }

    public KhoaHoc(ChuyenDe maChuyenDe, float hocPhi, int thoiLuong, Date ngayKhaiGiang, String ghiChu, NhanVien nguoiTao) {
        this.maChuyenDe = maChuyenDe;
        this.hocPhi = hocPhi;
        this.thoiLuong = thoiLuong;
        this.ngayKhaiGiang = ngayKhaiGiang;
        this.ghiChu = ghiChu;
        this.nguoiTao = nguoiTao;
    }



   

    public int getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(int maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public ChuyenDe getMaChuyenDe() {
        return maChuyenDe;
    }

    public void setMaChuyenDe(ChuyenDe maChuyenDe) {
        this.maChuyenDe = maChuyenDe;
    }

    public float getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(float hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public Date getNgayKhaiGiang() {
        return ngayKhaiGiang;
    }

    public void setNgayKhaiGiang(Date ngayKhaiGiang) {
        this.ngayKhaiGiang = ngayKhaiGiang;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
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

    public Object[] rowDataKH() {
        return new Object[]{
            maKhoaHoc, thoiLuong, hocPhi, XDate.toString(ngayKhaiGiang, "dd-MM-yyyy"), nguoiTao.getMaNhanVien(), XDate.toString(ngayTao, "dd-MM-yyyy") , ghiChu
        };
    }

    @Override
    public String toString() {
        return maChuyenDe.getMaChuyenDe() +  " ( " + XDate.toString(ngayTao, "dd-MM-yyyy" ) + " )";
    } 
    
    public String toString2(String chuyenDe) {
        return chuyenDe + " " +maKhoaHoc +" (" + XDate.toString(ngayTao, "dd-MM-yyyy") + ")" ;
    } 
    
}
