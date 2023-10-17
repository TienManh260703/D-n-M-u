/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.DBContext;
import model.ChuyenDe;


/**
 *
 * @author manhnt
 */
public class ChuyenDeResponsitory {

    private String sql;
    private Statement stm;
    private PreparedStatement pstm;
    private Connection con;
    private ResultSet rs;

    public List<ChuyenDe> getAll() {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            sql = "SELECT MACD , TENCD , HOCPHI ,THOILUONG , NGAYTAO , HINH , MOTA FROM CHUYENDE";
            con = DBContext.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                ChuyenDe CD = new ChuyenDe(
                        rs.getString("MACD"),
                        rs.getString("TENCD"),
                        rs.getFloat("HOCPHI"),
                        rs.getInt("THOILUONG"),
                        rs.getDate("NGAYTAO"),
                        rs.getString("HINH"),
                        rs.getString("MOTA"));
                list.add(CD);
            }
            return list;
        } catch (SQLException ex) {
            return null;
        }
    }

       
    public int insertCD(ChuyenDe cd) {
        try {
            sql = "INSERT INTO CHUYENDE (MACD , TENCD , HOCPHI , THOILUONG , HINH , MOTA) VALUES ( ? , ? , ? , ? , ? , ?)";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, cd.getMaChuyenDe());
            pstm.setString(2, cd.getTenChuyenDe());
            pstm.setFloat(3, cd.getHocPhi());
            pstm.setInt(4, cd.getThoiLuong());
            pstm.setString(5, cd.getHinh());
            pstm.setString(6, cd.getMoTa());
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            return -1;
        }
    }

    public int updateCD(ChuyenDe cd) {
        try {
            sql = "UPDATE CHUYENDE SET TENCD = ? , HOCPHI = ?, THOILUONG = ?,  HINH = ?, MOTA = ? WHERE MACD = ?";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, cd.getTenChuyenDe());
            pstm.setFloat(2, cd.getHocPhi());
            pstm.setInt(3, cd.getThoiLuong());
            pstm.setString(4, cd.getHinh());
            pstm.setString(5, cd.getMoTa());
            pstm.setString(6, cd.getMaChuyenDe());
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            return -1;
        }
    }

    public int deleteCD(String maChuyenDe) {
        try {
            sql = "DELETE FROM CHUYENDE WHERE MACD = ?";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, maChuyenDe);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            return -1;
        }

    }

    public int checkChuyenDe(String maChuyenDe) {
        int row = 0;
        try {
            sql = "SELECT MACD , TENCD , HOCPHI ,THOILUONG , NGAYTAO , HINH , MOTA FROM CHUYENDE WHERE MACD = ?";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, maChuyenDe);
            rs = pstm.executeQuery();
            while (rs.next()) {
                row++;
                break;
            }
            return row;
        } catch (SQLException ex) {
            return -1;
        }
    }
}
