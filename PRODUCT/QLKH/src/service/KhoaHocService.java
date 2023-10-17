/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.ChuyenDe;
import model.KhoaHoc;
import responsitory.KhoaHocResponsitory;

/**
 *
 * @author manhnt
 */
public class KhoaHocService {

    private KhoaHocResponsitory responsitory = new KhoaHocResponsitory();

    public List<KhoaHoc> getAll() {
        return responsitory.getAll();
    }

    public List<KhoaHoc> getKhoaHocByID(ChuyenDe cd) {
        return responsitory.getKhoaHocByIDCD(cd);
    }

    public int insertKH(KhoaHoc kh) {
        return responsitory.insertKH(kh);
    }

    public int deleteKH(int maKhoaHoc) {
        return responsitory.deleteKH(maKhoaHoc);
    }

    public int update(KhoaHoc khoaHoc) {
        return responsitory.updateKH(khoaHoc);
    }
    
     public List<Integer> getAllYears (){
         return responsitory.getAllYears();
     }
}
