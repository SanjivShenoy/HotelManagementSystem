/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roombookings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 *
 * @author Manish
 */
public class WaitingList {
    Connection c;
    ResultSet rs;
    Statement st;
    StringBuffer list;
    Date inDate,outDate;
    int roomId;
    
    WaitingList (int roomId) {
        this.roomId = roomId;
        c = null;
        st = null;
        try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/postgres",
            "postgres", "manish");
     
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         st = c.createStatement();
         String sql = "SELECT date_string FROM datelist WHERE id = "+ roomId +";";
         rs = st.executeQuery(sql);
         rs.next();
         String list1 = rs.getString("date_string");
         list.append(list1);
        } catch (Exception e) {
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         //System.exit(0);
        }
    }
    
    private boolean checkFull (int x) {
        char c = list.charAt(x);
        if(c == '3') {
            return true;
        }
        else {
            return false;
        }
    }
    
    boolean isWait (Date inDate, Date outDate) {
        this.inDate = inDate;
        this.outDate = outDate;
        
        SimpleDateFormat fd = new SimpleDateFormat("d MMM, yyyy");
        String d1 = fd.format(inDate);
        String d2 = fd.format(outDate);
        
        int day1 = changeDate(d1);
        int day2 = changeDate(d2);
        
        for(int i = day1; i <= day2 ; i++) {
            boolean flag = checkFull(i);
            if(flag)
                return true;
        }
        return false;
    }
    
    void fillDate (Date inDate, Date outDate, String userName) {
        this.inDate = inDate;
        this.outDate = outDate;
        
        SimpleDateFormat fd = new SimpleDateFormat("d MMM, yyyy");
        String d1 = fd.format(inDate);
        String d2 = fd.format(outDate);
        
        int day1 = changeDate(d1);
        int day2 = changeDate(d2);
        
        for(int i = day1; i <= day2; i++) {
            char c = list.charAt(i);
            switch(c) {
                case '0':
                    list.setCharAt(i, '1');
                case '1':
                    list.setCharAt(i, '2');
                case '2':
                    list.setCharAt(i, '3');
            }  
        }
        
        String up = "UPDATE datelist SET date_string = '" + list + "' WHERE room_id = " + roomId + ";";
        String user = "INSERT INTO roombookings VALUES ();"
        try{
        st.executeUpdate(up);
        st.executeUpdate(user);
        c.commit();
        } catch (Exception e) {
            System.out.println("Update Failed");
        }
        
        
    }
    
    private int changeDate (String s) {
        int i = 0;
        int date = 0;
        String Month;
        String yearString;
        int year;
        int DateAsInt=0;
        while(s.charAt(i) >= 48 && s.charAt(i)<58)
        {
            date = date*10 + (s.charAt(i)-48);
            i++;
        }
        i++;
        Month = s.substring(i, i+3);
        i+=5;
        yearString = s.substring(i, i+4);
        
        year = Integer.parseInt(yearString);
        
        System.out.println(""+date+" "+Month+" "+year);
        
        DateAsInt+=date;
        switch(Month)
        {
            case "Dec":
                DateAsInt+=30;
            case "Nov":
                DateAsInt+=31;
            case "Oct":
                DateAsInt+=30;
            case "Sep":
                DateAsInt+=31;
            case "Aug":
                DateAsInt+=31;
            case "Jul":
                DateAsInt+=30;
            case "Jun":
                DateAsInt+=31;
            case "May":
                DateAsInt+=30;
            case "Apr":
                DateAsInt+=31;
            case "Mar":
                DateAsInt+=28;
            case "Feb":
                DateAsInt+=31;
            case "Jan":
                DateAsInt+=0;
        }
        if(year == 2019)
            DateAsInt += 365;
        System.out.println(DateAsInt);
        return DateAsInt;
    }
}
