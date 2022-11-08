/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author admin
 */
public class DateTimeHelper {

    public static float getDaystoCurrent(Date d) {
        java.util.Date e = new java.util.Date(d.getTime());
        java.util.Date current = new java.util.Date();
        long diff = current.getTime() - e.getTime();
        return ((float) diff / (1000 * 60 * 60 * 24));
    }

    public static Date toDate(String value, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(value);
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date toDateUtil(java.sql.Date d) {
        Date x = new java.sql.Date(d.getTime());
        x = removeTime(x);
        return x;
    }

    public static java.sql.Date toDateSql(Date d) {
        d = removeTime(d);
        return new java.sql.Date(d.getTime());
    }

    public static int getDayOfWeek(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }

    public static Date addDays(Date d, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    public static ArrayList<java.sql.Date> getDateList(java.sql.Date from, java.sql.Date to) {
        ArrayList<java.sql.Date> dates = new ArrayList<>();
        int days = 0;
        Date e_from = toDateUtil(from);
        Date e_to = toDateUtil(to);
        while (true) {
            Date d = DateTimeHelper.addDays(e_from, days);
            dates.add(toDateSql(d));
            days++;
            if (d.compareTo(e_to) >= 0) {
                break;
            }
        }
        return dates;
    }

    public static String getDayNameOfWeek(java.sql.Date s) {
        Date d = toDateUtil(s);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                return "Sun";
            case 2:
                return "Mon";
            case 3:
                return "Tue";
            case 4:
                return "Wed";
            case 5:
                return "Thu";
            case 6:
                return "Fri";
            case 7:
                return "Sat";
        }
        return "Error";
    }

    public static int compare(java.sql.Date a, java.sql.Date b) {
        Date e_a = toDateUtil(a);
        Date e_b = toDateUtil(b);
        System.out.println(a + " " + b + " " + e_a.compareTo(e_b));
        return e_a.compareTo(e_b);
    }
}
