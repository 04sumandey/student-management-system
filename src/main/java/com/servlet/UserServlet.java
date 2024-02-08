package com.servlet;


import com.Dao.UserDao;
import com.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/regester")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private com.model.User user;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws SecurityException, IOException {
        String idStr=request.getParameter("id");
        String name =request.getParameter("name");
        String phNo = request.getParameter("phNo");
        String email = request.getParameter("email");

        int id=Integer.parseInt(idStr);

        user=new User(id,name,phNo,email);

    }

}
