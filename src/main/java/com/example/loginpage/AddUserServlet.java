package com.example.loginpage;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int b = ConnectionToDB.Add_User(request.getParameter("Email"),request.getParameter("username"),request.getParameter("password"));
        if (b>0) {
            int a = 1;
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(a);
            request.setAttribute("val", arr);
            request.getRequestDispatcher("Index.jsp").forward(request, response);
        }
        else {
            int a = 1;
            ArrayList<Integer>arr = new ArrayList<>();
            arr.add(a);
            request.setAttribute("data",arr);
            request.getRequestDispatcher("AddUser.jsp").forward(request,response);
        }
    }
}
