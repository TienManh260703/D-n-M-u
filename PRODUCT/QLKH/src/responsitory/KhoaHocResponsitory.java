/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import Utils.XDate;
import interface_iplm.KhoaHoc_itf;
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
import model.ChuyenDe;
import model.KhoaHoc;
import model.NhanVien;

/**
 *
 * @author manhnt
 */
public class KhoaHocResponsitory implements KhoaHoc_itf {

    private String sql;
    private Statement stm;
    private PreparedStatement pstm;
    private Connection con;
    private ResultSet rs;

    public List<KhoaHoc> getAll() {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            sql = "SELECT MAKH , MACD , HOCPHI , THOILUONG , NGAYTAO , NGAYKG , GHICHU , NGUOITAO FROM KHOAHOC";
            con = DBContext.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                ChuyenDe chuyenDe = new ChuyenDe(rs.getString("MACD"));
                NhanVien nhanVien = new NhanVien(rs.getString("NGUOITAO"));
                KhoaHoc khoaHoc = new KhoaHoc(
                        rs.getInt("MAKH"),
                        chuyenDe,
                        rs.getFloat("HOCPHI"),
                        rs.getInt("THOILUONG"),
                        rs.getDate("NGAYKG"),
                        rs.getDate("NGAYTAO"),
                        rs.getString("GHICHU"),
                        nhanVien);
                list.add(khoaHoc);
            }
            return list;
        } catch (SQLException ex) {
            return null;
        }

    }

    public List<KhoaHoc> getKhoaHocByIDCD(ChuyenDe cd) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            sql = "SELECT MAKH , MACD , HOCPHI , THOILUONG , NGAYTAO , NGAYKG , GHICHU , NGUOITAO FROM KHOAHOC WHERE MACD = ?";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, cd.getMaChuyenDe());
            rs = pstm.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString("NGUOITAO"));
                KhoaHoc kh = new KhoaHoc(
                        rs.getInt("MAKH"),
                        cd,
                        rs.getFloat("HOCPHI"),
                        rs.getInt("THOILUONG"),
                        rs.getDate("NGAYKG"),
                        rs.getDate("NGAYTAO"),
                        rs.getString("GHICHU"),
                        nv
                );
                list.add(kh);
            }
            return list;
        } catch (SQLException ex) {        
            return null;
        }
    }

    @Override
    public int insertKH(KhoaHoc khoaHoc) {
        try {
            sql = "INSERT INTO KHOAHOC (MACD , HOCPHI , THOILUONG ,  NGAYKG  , GHICHU ,  NGUOITAO) VALUES (? , ?, ? ,? , ?  , ?)";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, khoaHoc.getMaChuyenDe().getMaChuyenDe());
            pstm.setFloat(2, khoaHoc.getHocPhi());
            pstm.setInt(3, khoaHoc.getThoiLuong());
            pstm.setString(4, XDate.toString(khoaHoc.getNgayKhaiGiang(), "MM-dd-yyyy"));
            pstm.setString(5, khoaHoc.getGhiChu());
            pstm.setString(6, khoaHoc.getNguoiTao().getMaNhanVien());
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhoaHocResponsitory.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int updateKH(KhoaHoc khoaHoc) {
        try {
            sql = " UPDATE KHOAHOC SET NGAYKG = ? , GHICHU = ? WHERE MAKH = ? ";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, XDate.toString(khoaHoc.getNgayKhaiGiang(), "MM-dd-yyyy"));
            pstm.setString(2, khoaHoc.getGhiChu());
            pstm.setInt(3, khoaHoc.getMaKhoaHoc());
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            return -1;
        }
    }

    @Override
    public int deleteKH(int maKhoaHoc) {
        try {
            sql = "DELETE FROM KHOAHOC WHERE MAKH = ?";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, maKhoaHoc);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            return -1;
        }
    }
    
    public List<Integer> getAllYears (){
        try {
            List<Integer> list = new ArrayList<>();
            sql = "SELECT DISTINCT YEAR(NGAYKG) AS NAM FROM KHOAHOC ORDER BY NAM DESC";
            con = DBContext.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {                
                list.add(rs.getInt("NAM"));
            }
            return list;
        } catch (SQLException ex) {
            return null;
        }
    }
}
