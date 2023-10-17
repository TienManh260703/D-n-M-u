/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manhnt
 */
public class XDate {

    private static SimpleDateFormat formater = new SimpleDateFormat();

    public static Date toDate(String date, String pattern) {
        try {
            formater.setLenient(false);
            formater.applyPattern(pattern);
            return formater.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Chuyển Date sang toString
    public static String toString(Date date, String pattern) {
        formater.setLenient(false);
        formater.applyPattern(pattern);
        return formater.format(date);
    }

    public static Date now() {
        return new Date();
    }

    public static Date addDays(Date date, int days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

    public static Date convertDateFormat(String str, String pattern) {

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat(pattern);
            inputFormat.setLenient(false);
            Date date = inputFormat.parse(str);
            String formattedDate = outputFormat.format(date);
            return outputFormat.parse(formattedDate);
        } catch (ParseException ex) {
            Logger.getLogger(XDate.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
}
