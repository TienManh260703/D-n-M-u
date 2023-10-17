/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import model.NhanVien;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.DBContext;

/**
 *
 * @author manhnt
 */
public class NhanVienResponsitory {

    private String sql;
    private Statement stm;
    private ResultSet rs;
    private PreparedStatement pstm;
    private Connection con;

    public List<NhanVien> getAll() {
        List<NhanVien> listNhanVien = new ArrayList<>();
        try {
            sql = "select MANHANVIEN , MATKHAU , HOTEN , VAITRO from NHANVIEN";
            con = DBContext.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString("MANHANVIEN"), rs.getString("MATKHAU"), rs.getString("HOTEN"), rs.getBoolean("VAITRO"));
                listNhanVien.add(nv);
            }
            return listNhanVien;
        } catch (SQLException ex) {       
            return null;
        }
    }

    public NhanVien login(String maNhanVien) {
        try {
            sql = "select MANHANVIEN , MATKHAU , HOTEN , VAITRO from NHANVIEN where MANHANVIEN = ? ";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, maNhanVien);
            rs = pstm.executeQuery();
            while (rs.next()) {
                NhanVien nv
                        = new NhanVien(
                                rs.getString("MANHANVIEN"),
                                rs.getString("MATKHAU"),
                                rs.getString("HOTEN"),
                                rs.getBoolean("VAITRO"));
                return nv;
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }

    public int updateMatKhau(NhanVien nv) {
        try {
            sql = " UPDATE NHANVIEN Set MATKHAU = ? WHERE MANHANVIEN = ? ";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, nv.getMatKhau());
            pstm.setString(2, nv.getMaNhanVien());
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            return -1;
        }
    }

    public int insertNV(NhanVien nv) {
        try {
            sql = " INSERT INTO NHANVIEN (MANHANVIEN , MATKHAU , HOTEN , VAITRO) VALUES (? ,? , ? , ?) ";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, nv.getMaNhanVien());
            pstm.setString(2, nv.getMatKhau());
            pstm.setString(3, nv.getHoTen());
            pstm.setInt(4, nv.isVaiTro() ? 1 : 0);
            return pstm.executeUpdate();
        } catch (SQLException ex) {          
            return -1;
        }
    }

    public int deleteNV(String maNhanVien) {
        try {
            sql = "DELETE FROM NHANVIEN WHERE MANHANVIEN = ?";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, maNhanVien);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            return -1;
        }
    }

    public int updateNV(NhanVien nv)  {
        try {
            sql = "UPDATE NHANVIEN SET MATKHAU = ? , HOTEN = ? , VAITRO = ? WHERE MANHANVIEN = ? ";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, nv.getMatKhau());
            pstm.setString(2, nv.getHoTen());
            pstm.setInt(3, nv.isVaiTro() ? 1 : 0);
            pstm.setString(4, nv.getMaNhanVien());
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            return -1;
        }
    }

    public int checkMaNhanVien(String maNhanVien) {
        int rowData = 0;
        try {
            sql = "SELECT MANHANVIEN , MANHANVIEN , HOTEN , VAITRO FROM NHANVIEN WHERE MANHANVIEN = ?";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, maNhanVien);
            rs = pstm.executeQuery();
            while (rs.next()) {
                rowData++;
            }
            return rowData;
        } catch (SQLException ex) {         
            return -1;
        }
    }
}
