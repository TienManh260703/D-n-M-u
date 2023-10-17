/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import java.util.List;
import model.NhanVien;
import responsitory.NhanVienResponsitory;

/**
 *
 * @author manhnt
 */
public class NhanVienService {

    private final NhanVienResponsitory responsitory = new NhanVienResponsitory();

    public List<NhanVien> getAll() {
        return responsitory.getAll();
    }

    public NhanVien login(String maNhanVien) {
        return responsitory.login(maNhanVien);
    }

    public int updateMatKhau(NhanVien nv) {
        return responsitory.updateMatKhau(nv);
    }

    public int insertNV(NhanVien nv) {
        return responsitory.insertNV(nv);
    }

    public int deleteNV(String maNhanVien) {
        return responsitory.deleteNV(maNhanVien);
    }

    public int updateNV(NhanVien nv) {
        return responsitory.updateNV(nv);
    }

    public int checkMaNhanVien(String maNhanVien) {
        return responsitory.checkMaNhanVien(maNhanVien);
    }
}
