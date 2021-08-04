package com.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ViewResolvedController extends HttpServlet {
    public ViewResolvedController() {
        super();
    }

        // in this servlet it will view all resolved reimbursments
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");
        // connection variable connection
        String dbURL = "jdbc:oracle:thin:@mydb1.cmtkalryuwt4.us-east-2.rds.amazonaws.com:1521:EMPDB";
        String username = "admin";
        String password = "Java2021";



        try{
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String sql = "SELECT * FROM userreg WHERE rpending = 'complete'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            writer.println("<table border=1 width=50% height=50%>");
            writer.println("<tr><th>NAME</th><th>PASSWORD</th><th>ROLE</th><th>ID</th><th>COMPLETED REQUESTS</th><tr>");

            while(result.next())
            {
                String name = result.getString("name");
                String pass = result.getString("pass");
                String role = result.getString("role");
                String id = result.getString("id");
                String rpending = result.getString("rpending");

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
