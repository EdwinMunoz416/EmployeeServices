package com.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class AllDetailsController extends HttpServlet {
    public AllDetailsController() {
        super();
    }

    // this servlet will use a doPOst method to display all employee details to html employee-details
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");

        String dbURL = "jdbc:oracle:thin:@mydb1.cmtkalryuwt4.us-east-2.rds.amazonaws.com:1521:EMPDB";
        String username = "admin";
        String password = "Java2021";



        try{
            // establish connection and create sql string with only employee details
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String sql = "SELECT * FROM User WHERE role = 'employee'";
            Statement statement = connection.createStatement();
            // create result set to execute my query
            ResultSet result = statement.executeQuery(sql);
            // print writer assists in creating my table as well as passing sql to the html
            // table attributes CHANGE **
            writer.println("<table border=1 width=50% height=50%>");
            // Table headers
            writer.println("<tr><th>NAME</th><th>PASSWORD</th><th>ROLE</th><th>ID</th><th>PENDING REIMBURSEMENT REQUESTS</th><tr>");

            while(result.next())
            {
                String name = result.getString("name");
                String pass = result.getString("pass");
                String role = result.getString("role");
                String id = result.getString("id");
                String rpending = result.getString("rpending");
                // places the variables into my table
                writer.println("<tr><td>" + name + "</td><td>" + pass + "</td><td>" + role + "</td><td>" + id + "</td><td>" + rpending + "</td></tr>");
            }  //System.out.println("Employee " + id + ":" + " "+ name + " - " + pass + " - " + role );
                writer.println("</table>");
                writer.println("</html></body>");
                connection.close();



        } catch(SQLException e){
            System.out.println("oops");

        }


    }
}
