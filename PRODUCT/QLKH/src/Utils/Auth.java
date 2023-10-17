/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import model.NhanVien;

/**
 *
 * @author manhnt
 */
public class Auth {

    // Đối tượng chưa nhân viên sau đăng nhập
    public static NhanVien nv = null;

    public static void clear() {
        Auth.nv = null;
    }

    // Kiểm tra đăng nhập hay chưa
    public static boolean isLogin() {
        return Auth.nv != null;
    }

    public static boolean isManager() {
        return Auth.isLogin() && nv.isVaiTro();
    }
}
