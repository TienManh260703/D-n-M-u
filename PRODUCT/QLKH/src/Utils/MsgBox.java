/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author manhnt
 */
public class MsgBox {

    // Hiển thị thông báo 
    // parent cửa sổ chứa thông báo
    // 
    public static void aleart(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Hệ thông quản lý đào tạo", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *
     * @param parent
     * @param message
     * @return
     */
    public static boolean confirm(Component parent, String message) {
        int hoi = JOptionPane.showConfirmDialog(parent, message, "Hệ thông quản lý đào tạo",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return hoi == JOptionPane.YES_OPTION;
    }

    public static String pormpt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message, "Hệ thông quản lý đào tạo", 
                JOptionPane.INFORMATION_MESSAGE);
    }

}
