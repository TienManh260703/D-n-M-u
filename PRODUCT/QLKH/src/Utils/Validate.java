/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author manhnt
 */
public class Validate {

    private static String REGEX_NAME = "^[a-zA-Z\\p{L}\\s]+$";
    private static String REGEX_MASV = "^PH\\d{5}$";
    private static String REGEX_MACD = "^CD\\d{3}$";
    private static String REGEX_NUMBER = "^[0-9]+$";
    private static String REGEX_SDT = "^0\\d{9}$";
    private static String REGEX_DATE = "^\\d{2}-\\d{2}-\\d{4}$";
    private static String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static boolean isEmpty(String str) {
        return str.isEmpty();
    }

    public static boolean checkLength(String str, int length) {
        return str.length() > length;
    }

    public static boolean isName(String str) {
        return str.matches(REGEX_NAME);
    }

    public static boolean isChuyenDe(String str) {
        return str.matches(REGEX_MACD);
    }

    public static boolean isNumber(String str) {
        return str.matches(REGEX_NUMBER);
    }

    public static boolean isMaHocVien(String str) {
        return str.matches(REGEX_MASV);
    }

    public static boolean isPhoneNumber(String str) {
        return str.matches(REGEX_SDT);
    }

    public static boolean isDate(String str) {
        return str.matches(REGEX_DATE);
    }

    public static boolean isEmail(String str) {
        return str.matches(REGEX_EMAIL);
    }
}
