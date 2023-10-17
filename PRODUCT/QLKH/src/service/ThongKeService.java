/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import responsitory.ThongKeResponsitory;

/**
 *
 * @author manhnt
 */
public class ThongKeService {

    private final ThongKeResponsitory responsitory = new ThongKeResponsitory();

    public List<Object[]> thongKeLuongNH() {
        return responsitory.thongKeLuongNH();
    }

    public List<Object[]> thongKeDiem(int maKhoaHoc) {
        return responsitory.thongKeDiem(maKhoaHoc);
    }

    public List<Object[]> thongKeDoanhThu(int nam) {
        return responsitory.thongKeDoanhThu(nam);
    }

    public List<Object[]> thongKeDiemCD() {
        return responsitory.thongKeDiemCD();
    }
}
