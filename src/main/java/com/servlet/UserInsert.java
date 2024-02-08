package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/FormData")
public class UserInsert extends HttpServlet {
    private static final long serialVersionUID =1L;

    public UserInsert(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name =req.getParameter("name");
        String phNo = req.getParameter("phonNo");
        String email =req.getParameter("email");

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.print("<html><body>");
        out.print("<h2>Details enroiled</h><br>");
        out.print("Full Name"+name+"<br>");
        out.print("Phone No"+phNo+"<br>");
        out.print("Email"+email+"<br>");
        out.print("</body></html>");

    }
}
