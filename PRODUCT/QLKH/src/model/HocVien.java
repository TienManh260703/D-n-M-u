/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author manhnt
 */
public class HocVien {

    private int maHocVien;
    private KhoaHoc maKhoaHoc;
    private NguoiHoc maNguoiHoc;
    private Float diem;

    public HocVien() {
    }

    public HocVien(int maHocVien, KhoaHoc maKhoaHoc, NguoiHoc maNguoiHoc, Float diem) {
        this.maHocVien = maHocVien;
        this.maKhoaHoc = maKhoaHoc;
        this.maNguoiHoc = maNguoiHoc;
        this.diem = diem;
    }

    public HocVien(KhoaHoc maKhoaHoc, NguoiHoc maNguoiHoc) {
        this.maKhoaHoc = maKhoaHoc;
        this.maNguoiHoc = maNguoiHoc;
    }

    public HocVien(KhoaHoc maKhoaHoc, NguoiHoc maNguoiHoc, Float diem) {
        this.maKhoaHoc = maKhoaHoc;
        this.maNguoiHoc = maNguoiHoc;
        this.diem = diem;
    }

  

    public int getMaHocVien() {
        return maHocVien;
    }

    public void setMaHocVien(int maHocVien) {
        this.maHocVien = maHocVien;
    }

    public KhoaHoc getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(KhoaHoc maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public NguoiHoc getMaNguoiHoc() {
        return maNguoiHoc;
    }

    public void setMaNguoiHoc(NguoiHoc maNguoiHoc) {
        this.maNguoiHoc = maNguoiHoc;
    }

    

    public Float getDiem() {
        return diem;
    }

    public void setDiem(Float diem) {
        this.diem = diem;
    }

    public Object[] rowDataHV(int index) {
        return new Object[]{
           index, maHocVien, maNguoiHoc.getMaNguoiHoc(), maNguoiHoc.getHoTen() , diem
        };
    }

}
