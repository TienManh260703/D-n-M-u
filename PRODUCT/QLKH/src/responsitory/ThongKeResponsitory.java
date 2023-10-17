/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.DBContext;

/**
 *
 * @author manhnt
 */
public class ThongKeResponsitory {

    private String sql;
    private Statement stm;
    private ResultSet rs;
    private PreparedStatement pstm;
    private Connection con;
    private CallableStatement callStm;

    public List<Object[]> thongKeLuongNH() {
        List<Object[]> list = new ArrayList<>();
        try {
            sql = "{CALL SP_LUONGNGUOIHOC}";
            con = DBContext.getConnection();
            callStm = con.prepareCall(sql);
            rs = callStm.executeQuery();
            while (rs.next()) {
                Object[] rowData = new Object[]{
                    rs.getInt("NAM"),
                    rs.getInt("SOLUONG"),
                    rs.getString("DAUTIEN"),
                    rs.getString("CUOICUNG")
                };
                list.add(rowData);
            }
            return list;
        } catch (SQLException ex) {    
            return null;
        }
    }

    public List<Object[]> thongKeDiem(int maKhoaHoc) {
        List<Object[]> list = new ArrayList<>();
        try {
            sql = "{CALL SP_BANGDIEM(?) }";
            con = DBContext.getConnection();
            callStm = con.prepareCall(sql);
            callStm.setInt(1, maKhoaHoc);
            rs = callStm.executeQuery();
            while (rs.next()) {
                Object[] bangDiem = new Object[]{
                    rs.getString("MA_NG_HOC"),
                    rs.getString("HOTEN"),
                    rs.getFloat("DIEM")
                };
                list.add(bangDiem);
            }
            return list;
        } catch (SQLException ex) {     
            return null;
        }
    }

    public List<Object[]> thongKeDoanhThu(int nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            sql = "{CALL SP_THONGKE_DOANHTHU (?)}";
            con = DBContext.getConnection();
            callStm = con.prepareCall(sql);
            callStm.setInt(1, nam);
            rs = callStm.executeQuery();
            while (rs.next()) {
                Object[] doangThu = new Object[]{
                    rs.getString("CHUYENDE"),
                    rs.getInt("SOKH"),
                    rs.getInt("SOHV"),
                    rs.getFloat("THAPNHAT"),
                    rs.getFloat("CAONHAT"),
                    rs.getFloat("TRUNGBINH"),
                    rs.getFloat("DOANHTHU")
                };
                list.add(doangThu);
            }
            return list;
        } catch (SQLException ex) {
            return null;
        }
    }

    public List<Object[]> thongKeDiemCD() {
        List<Object[]> list = new ArrayList<>();
        try {
            sql = " {CALL SP_THONGKEDIEM }";
            con = DBContext.getConnection();
            callStm = con.prepareCall(sql);
            rs = callStm.executeQuery();
            while (rs.next()) {
                Object[] diemCD = new Object[]{
                    rs.getString("CHUYENDE"),
                    rs.getInt("SOHV"),
                    rs.getFloat("THAPNHAT"),
                    rs.getFloat("CAONHAT"),
                    rs.getFloat("TRUNGBINH")
                };
                list.add(diemCD);
            }
            return list;
        } catch (SQLException ex) {
            return null;
        }
    }
}
