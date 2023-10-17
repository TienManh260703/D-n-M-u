/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import Utils.XDate;
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
import model.NguoiHoc;
import model.NhanVien;

/**
 *
 * @author manhnt
 */
public class NguoiHocResponsitory {

    private String sql;
    private Statement stm;
    private ResultSet rs;
    private PreparedStatement pstm;
    private Connection con;

    public List<NguoiHoc> getAll() {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            sql = "SELECT MANH , HOTEN , GIOITINH , NGAYSINH , DIENTHOAI , EMAIL , GHICHU , NGUOITAO , NGAYDK FROM NGUOIHOC";
            con = DBContext.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString("NGUOITAO"));
                NguoiHoc nh = new NguoiHoc(
                        rs.getString("MANH"),
                        rs.getString("HOTEN"),
                        rs.getBoolean("GIOITINH"),
                        rs.getDate("NGAYSINH"),
                        rs.getString("DIENTHOAI"),
                        rs.getString("EMAIL"),
                        rs.getString("GHICHU"),
                        nv,
                        rs.getDate("NGAYDK"));
                list.add(nh);
            }
            return list;
        } catch (SQLException ex) {     
            return null;
        }
    }

    public List<NguoiHoc> getNotInKH(int makh, String keyword) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            sql = """
                              SELECT  MANH , HOTEN , NGAYSINH , GIOITINH , DIENTHOAI , EMAIL FROM NGUOIHOC
                              WHERE  HOTEN LIKE ? AND MANH NOT IN (SELECT MANH FROM HOCVIEN WHERE MAKH = ? )""";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1,"%" +keyword +"%");
            pstm.setInt(2, makh);
            rs = pstm.executeQuery();
            while (rs.next()) {
                NguoiHoc nh = new NguoiHoc(
                        rs.getString("MANH"),
                        rs.getString("HOTEN"),
                        rs.getBoolean("GIOITINH"),
                        rs.getDate("NGAYSINH"),
                        rs.getString("DIENTHOAI"),
                        rs.getString("EMAIL"));
                list.add(nh);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(NguoiHocResponsitory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<NguoiHoc> searchNH(String text) {
        List<NguoiHoc> listSearch = new ArrayList<>();
        try {
            sql = " SELECT  MANH , HOTEN , GIOITINH , NGAYSINH , DIENTHOAI , EMAIL , GHICHU , NGUOITAO , NGAYDK FROM NGUOIHOC WHERE MANH like ? or HOTEN like ?";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, "%" + text + "%");
            pstm.setString(2, "%" + text + "%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString("NGUOITAO"));
                NguoiHoc nh = new NguoiHoc(
                        rs.getString("MANH"),
                        rs.getString("HOTEN"),
                        rs.getBoolean("GIOITINH"),
                        rs.getDate("NGAYSINH"),
                        rs.getString("DIENTHOAI"),
                        rs.getString("EMAIL"),
                        rs.getString("GHICHU"),
                        nv,
                        rs.getDate("NGAYDK"));
                listSearch.add(nh);
            }
            return listSearch;
        } catch (SQLException ex) {   
            return null;
        }
    }

    public int insertNH(NguoiHoc nh) {
        try {
            sql = "INSERT INTO NGUOIHOC (MANH , HOTEN , GIOITINH , NGAYSINH , DIENTHOAI , EMAIL , GHICHU , NGUOITAO ) "
                    + "VALUES (? ,? ,? ,? ,? ,? ,? , ?)";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, nh.getMaNguoiHoc());
            pstm.setString(2, nh.getHoTen());
            pstm.setInt(3, nh.isGioiTinh() ? 1 : 0);
            pstm.setString(4, XDate.toString(nh.getNgaySinh(), "MM-dd-yyyy"));
            pstm.setString(5, nh.getDienThoai());
            pstm.setString(6, nh.getEmail());
            pstm.setString(7, nh.getGhiChu());
            pstm.setString(8, nh.getNguoiTao().getMaNhanVien());
            return pstm.executeUpdate();
        } catch (SQLException ex) {        
            return -1;
        }
    }

    public int deleteNH(String maNguoiHoc) {
        try {
            sql = "DELETE FROM NGUOIHOC WHERE MANH = ?";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, maNguoiHoc);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
           
            return -1;
        }
    }

    public int updateNH(NguoiHoc nh) {
        try {
            sql = "UPDATE NGUOIHOC SET HOTEN  = ?, GIOITINH = ? , NGAYSINH  = ?, DIENTHOAI = ? , EMAIL = ? , GHICHU = ? WHERE MANH =?";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setString(1, nh.getHoTen());
            pstm.setInt(2, nh.isGioiTinh() ? 1 : 0);
            pstm.setString(3, XDate.toString(nh.getNgaySinh(), "MM-dd-yyyy"));
            pstm.setString(4, nh.getDienThoai());
            pstm.setString(5, nh.getEmail());
            pstm.setString(6, nh.getGhiChu());
            pstm.setString(7, nh.getMaNguoiHoc());
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NguoiHocResponsitory.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int checkNH(String maNguoiHoc) {
        int row = 0;
        try {
            sql = "SELECT MANH , HOTEN , GIOITINH , NGAYSINH , DIENTHOAI , EMAIL , GHICHU , NGUOITAO , NGAYDK FROM NGUOIHOC WHERE MANH = ?";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, maNguoiHoc);
            rs = pstm.executeQuery();
            while (rs.next()) {
                row++;
                break;
            }
        } catch (SQLException ex) {      
            return -1;
        }
        return row;
    }
}
