/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_iplm;


import model.KhoaHoc;

/**
 *
 * @author manhnt
 */
public interface KhoaHoc_itf {
    int insertKH(KhoaHoc khoaHoc);
    int updateKH(KhoaHoc khoaHoc) ;
    int deleteKH(int maKhoaHoc) ;
}
