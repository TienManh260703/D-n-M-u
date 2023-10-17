/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author manhnt
 */
public class NhanVien {
    private String maNhanVien;
    private String matKhau;
    private String hoTen;
    private boolean vaiTro;

    public NhanVien() {
    }

    public NhanVien(String maNhanVien, String matKhau, String hoTen, boolean vaiTro) {
        this.maNhanVien = maNhanVien;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.vaiTro = vaiTro;
    }

    public NhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }
    
    public Object [] rowDataNV (){
        return new Object[] {maNhanVien, matKhau ,hoTen , vaiTro?"Trưởng phòng": "Nhân Viên"};
    }

    @Override
    public String toString() {
        return  maNhanVien;
    }
}
