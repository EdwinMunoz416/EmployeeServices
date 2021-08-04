package com.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class SubmitRequestController extends HttpServlet {

    public SubmitRequestController() {
        super();
    }

    // in this servlet, used to submit request ** ADD BUTTON TO TAKE TO A INSERT FORM ***
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");

        String dbURL = "jdbc:oracle:thin:@mydb1.cmtkalryuwt4.us-east-2.rds.amazonaws.com:1521:EMPDB";
        String username = "admin";
        String password = "Java2021";



        try{

            // DUMMY DATA AT THE MOMENT... ** ADD A BUTTON TO INSERT DATA TO REIMBURSEMENTS
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String sql = "SELECT * FROM userreg WHERE role = 'employee'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            writer.println("<table border=1 width=50% height=50%>");
            writer.println("<tr><th>NAME</th><th>PASSWORD</th><th>ROLE</th><th>ID</th><th>PENDING REIMBURSEMENT REQUESTS</th><tr>");

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
