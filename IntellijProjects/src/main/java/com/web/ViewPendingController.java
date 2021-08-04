package com.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


// in this servlet it shows all pending requets for employees
public class ViewPendingController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // set response type and declare Print writer
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");
        // connection string variable declaration
        String dbURL = "jdbc:oracle:thin:@mydb1.cmtkalryuwt4.us-east-2.rds.amazonaws.com:1521:EMPDB";
        String username = "admin";
        String password = "Java2021";

        // in the try catch, select all employees that have a reimbursement request
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String sql = "SELECT * FROM userreg WHERE rpending = 'pending'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            // table for only name of employees and id that have a pending req  for the manager to view
            writer.println("<table border=1 width=50% height=50%>");
            writer.println("<tr><th>NAME</th><th>ID</th><tr>");
            // creates a table with only name and id to show manager who has pending requests
            while (result.next()) {

                String rpendingname = result.getString("name");
                String rpendingid = result.getString("id");

                writer.println("<tr><td>" + rpendingname + "</td><td>" + rpendingid + "</td></tr>");
            }//System.out.println("Employee " + id + ":" + " "+ name + " - " + pass + " - " + role );
            writer.println("</table>");
            writer.println("</html></body>");
            connection.close();
        } catch (SQLException e) {
            System.out.println("oops");
        }
    }
}

