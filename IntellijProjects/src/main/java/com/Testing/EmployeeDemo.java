package com.Testing;

import java.sql.*;

public class EmployeeDemo {
// testing gettting data from DB in this demo
    public static void main(String[] args){
        // var to hold creds
        String dbURL = "jdbc:oracle:thin:@trkdb.cmtkalryuwt4.us-east-2.rds.amazonaws.com:1521:EMPDB";
        String username = "admin";
        String password = "Java2021";

        try{
            //create conneciton here with driver manager
            Connection connection = DriverManager.getConnection(dbURL, username, password);

            // create statment in a string to pass
            String sql = "SELECT * FROM user WHERE role = 'employee'";

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);
            // while theres data output to console
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
