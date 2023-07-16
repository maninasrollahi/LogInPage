package com.example.loginpage;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/changepass")
public class ChangePassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        int a = ConnectionToDB.ChangePass(request.getParameter("email"),request.getParameter("username"),request.getParameter("password"));
        if (a>0){
            request.getSession(false).invalidate();
            int b = 1;
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(b);
            request.setAttribute("val",arr);request.getRequestDispatcher("Index.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
