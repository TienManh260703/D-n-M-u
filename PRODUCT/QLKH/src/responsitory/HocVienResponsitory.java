/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.DBContext;
import model.HocVien;
import model.KhoaHoc;
import model.NguoiHoc;

/**
 *
 * @author manhnt
 */
public class HocVienResponsitory {

    private String sql;
    private Statement stm;
    private ResultSet rs;
    private PreparedStatement pstm;
    private Connection con;

    public List<HocVien> getAllByKhoaHoc(int maKhoaHoc) {
        List<HocVien> list = new ArrayList<>();
        try {
            sql = "SELECT MAHV , MAKH ,  NGUOIHOC.MANH AS MANGUOI_H , HOTEN , DIEM FROM HOCVIEN \n"
                    + "JOIN NGUOIHOC ON HOCVIEN.MANH = NGUOIHOC.MANH "
                    + "WHERE MAKH = ?";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, maKhoaHoc);
            rs = pstm.executeQuery();
            while (rs.next()) {
                KhoaHoc khoaHoc = new KhoaHoc(rs.getInt("MAKH"));
                NguoiHoc nguoiHoc = new NguoiHoc(rs.getString("MANGUOI_H"), rs.getString("HOTEN"));
                HocVien hocVien = new HocVien(
                        rs.getInt("MAHV"),
                        khoaHoc,
                        nguoiHoc,
                        rs.getFloat("DIEM"));
                list.add(hocVien);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(HocVienResponsitory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int insertHV(HocVien hocVien) {
        try {
            sql = " INSERT INTO HOCVIEN  ( MAKH ,MANH , DIEM ) VALUES ( ?,? ,? ) ";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, hocVien.getMaKhoaHoc().getMaKhoaHoc());
            pstm.setString(2, hocVien.getMaNguoiHoc().getMaNguoiHoc());
            pstm.setFloat(3, hocVien.getDiem());
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            return -1;
        }

    }
    
    public int deleteHV (int maHocVien){
        try {
            sql = " DELETE FROM HOCVIEN WHERE MAHV = ?";
            con =  DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, maHocVien);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            return -1;
        }
    }
    
    public int updateHV (float  diem , int maHocVien){
        try {
            sql = "UPDATE HOCVIEN SET DIEM = ? WHERE MAHV = ?";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setFloat(1, diem);
            pstm.setInt(2, maHocVien);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            return -1;
        }
        
    }
}
