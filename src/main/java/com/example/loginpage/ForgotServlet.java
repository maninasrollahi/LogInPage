package com.example.loginpage;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet("/Forgot")
public class ForgotServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        try {
            ResultSet rs = ConnectionToDB.Find_User(request.getParameter("email"), request.getParameter("username"));
            if (rs.next()) {
                session.setAttribute("email", request.getParameter("email"));
                session.setAttribute("username", request.getParameter("username"));
                request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
            }
            else{
                PrintWriter pw = response.getWriter();
                response.setContentType("text/html");
                pw.println("<center>email and username not found</center>");
                request.getRequestDispatcher("Forgot.jsp").include(request,response);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
