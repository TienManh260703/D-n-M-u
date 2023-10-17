/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.NguoiHoc;
import responsitory.NguoiHocResponsitory;

/**
 *
 * @author manhnt
 */
public class NguoiHocService {

    private NguoiHocResponsitory responsitory = new NguoiHocResponsitory();

    public List<NguoiHoc> getAll() {
        return responsitory.getAll();
    }

    public List<NguoiHoc> searchNH(String text) {
        return responsitory.searchNH(text);
    }

    public int insertNH(NguoiHoc nh) {
        return responsitory.insertNH(nh);
    }

    public List<NguoiHoc> getNotInKH(int makh, String keyword) {
        return responsitory.getNotInKH(makh, keyword);
    }

    public int checkNH(String maNguoiHoc) {
        return responsitory.checkNH(maNguoiHoc);
    }

    public int deleteNH(String maNguoiHoc) {
        return responsitory.deleteNH(maNguoiHoc);
    }

    public int updateNH(NguoiHoc nh) {
        return responsitory.updateNH(nh);
    }
}
