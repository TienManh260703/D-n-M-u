/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import javax.swing.JOptionPane;

/**
 *
 * @author manhnt
 */
public class TienIch {
    public static  void ketThuc (){
       int hoi = JOptionPane.showConfirmDialog(null, "Bạn muốn kết thúc ứng dụng ? ", "Thông Báo", JOptionPane.YES_NO_OPTION);
       if(hoi == JOptionPane.YES_OPTION){
           System.exit(0);
       }
    }
}
