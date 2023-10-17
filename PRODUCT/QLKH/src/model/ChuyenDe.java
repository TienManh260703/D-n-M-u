/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author manhnt
 */
public class ChuyenDe {

    private String maChuyenDe;
    private String tenChuyenDe;
    private float hocPhi;
    private int thoiLuong;
    private Date ngayTao;
    private String hinh;
    private String moTa;

    public ChuyenDe() {
    }

    public ChuyenDe(String maChuyenDe, String tenChuyenDe, float hocPhi, int thoiLuong, Date ngayTao, String hinh, String moTa) {
        this.maChuyenDe = maChuyenDe;
        this.tenChuyenDe = tenChuyenDe;
        this.hocPhi = hocPhi;
        this.thoiLuong = thoiLuong;
        this.ngayTao = ngayTao;
        this.hinh = hinh;
        this.moTa = moTa;
    }

    public ChuyenDe(String maChuyenDe, String tenChuyenDe, float hocPhi, int thoiLuong, String hinh, String moTa) {
        this.maChuyenDe = maChuyenDe;
        this.tenChuyenDe = tenChuyenDe;
        this.hocPhi = hocPhi;
        this.thoiLuong = thoiLuong;
        this.hinh = hinh;
        this.moTa = moTa;
    }

    public ChuyenDe(String maChuyenDe) {
        this.maChuyenDe = maChuyenDe;
    }

    public String getMaChuyenDe() {
        return maChuyenDe;
    }

    public void setMaChuyenDe(String maChuyenDe) {
        this.maChuyenDe = maChuyenDe;
    }

    public String getTenChuyenDe() {
        return tenChuyenDe;
    }

    public void setTenChuyenDe(String tenChuyenDe) {
        this.tenChuyenDe = tenChuyenDe;
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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Object[] rowDataCD() {
        return new Object[]{
            maChuyenDe,
            tenChuyenDe,
            hocPhi,
            thoiLuong,
            ngayTao,
            hinh,
            moTa};
    }

    @Override
    public String toString() {
        return tenChuyenDe;
    }

}
