/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.HocVien;
import responsitory.HocVienResponsitory;

/**
 *
 * @author manhnt
 */
public class HocVienService {

    private HocVienResponsitory responsitory = new HocVienResponsitory();

    public List<HocVien> getAllByKhoaHoc(int maKhoaHoc) {
        return responsitory.getAllByKhoaHoc(maKhoaHoc);
    }

    public int insertHV(HocVien hocVien) {
        return responsitory.insertHV(hocVien);
    }

    public int deleteHV(int maHocVien) {
        return responsitory.deleteHV(maHocVien);
    }

    public int updateHV(float diem, int maHocVien) {
        return responsitory.updateHV(diem, maHocVien);
    }
}
