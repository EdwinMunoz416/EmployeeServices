package com.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDaoImpl {

    public static boolean validate(String name, String pass) {
        // returns validation status
        boolean status = false;

        try {
            // load connection to my DB
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con =
                    DriverManager.getConnection("jdbc:oracle:thin:@mydb1.cmtkalryuwt4.us-east-2.rds.amazonaws.com:1521:EMPDB", "admin", "Java2021");
            // prepared statement with SQL statement to select name and password
            PreparedStatement ps = con.prepareStatement("select * from userreg where name=? and pass=?");
            // sets string  param 1 and 2 to the name and password
            ps.setString(1, name);
            ps.setString(2, pass);


            // execute my result set and if data is found, next
            ResultSet rs = ps.executeQuery();


                status = rs.next();



            // exception catch
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }



}
