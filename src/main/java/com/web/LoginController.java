package com.web;

import com.employee.EmployeeDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginController extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");


        PrintWriter out = response.getWriter();
        // gets the uname and pword from loginDao
        String n=request.getParameter("username");
        String p=request.getParameter("password");


        // if login passes then send to employee homepage, else redirect to login again
        if(EmployeeDaoImpl.validate(n, p) ){
            RequestDispatcher rd=request.getRequestDispatcher("employee-welcome.html");
            rd.forward(request,response);

        }

        else{
            out.print("Sorry username or password error");
            RequestDispatcher rd=request.getRequestDispatcher("index.html");
            rd.include(request,response);
        }

        out.close();
    }
}
