/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.ChuyenDe;
import model.KhoaHoc;
import responsitory.ChuyenDeResponsitory;

/**
 *
 * @author manhnt
 */
public class ChuyenDeService {

    private final ChuyenDeResponsitory responsitory = new ChuyenDeResponsitory();

    public List<ChuyenDe> getAll() {
        return responsitory.getAll();
    }

    
    public int insertCD(ChuyenDe cd) {
        return responsitory.insertCD(cd);
    }

    public int updateCD(ChuyenDe cd) {
        return responsitory.updateCD(cd);
    }

    public int deleteCD(String maChuyenDe) {
        return responsitory.deleteCD(maChuyenDe);
    }

    public int checkChuyenDe(String maChuyenDe) {
        return responsitory.checkChuyenDe(maChuyenDe);
    }
}
