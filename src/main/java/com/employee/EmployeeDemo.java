package com.employee;

import java.sql.*;

public class EmployeeDemo {

    public static void main(String[] args){
        String dbURL = "jdbc:oracle:thin:@mydb1.cmtkalryuwt4.us-east-2.rds.amazonaws.com:1521:EMPDB";
        String username = "admin";
        String password = "Java2021";

        try{
            Connection connection = DriverManager.getConnection(dbURL, username, password);


            String sql = "SELECT * FROM userreg WHERE role = 'employee'";

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);
            while(result.next())
            {
                String name = result.getString("name");
                String pass = result.getString("pass");
                String role = result.getString("role");
                String id = result.getString("id");

                System.out.println("Employee " + id + ":" + " "+ name + " - " + pass + " - " + role );
            }
            connection.close();

        } catch(SQLException e){
            System.out.println("oops");

        }
    }
}
