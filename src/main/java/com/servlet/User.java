package com.servlet;

import com.Dao.UserDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.script.ScriptContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet("/user/*")
public class User extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idStr=req.getPathInfo();
            System.out.println("path: "+idStr);

            int id;
            if (idStr!=null){
                id=Integer.parseInt(idStr.split("/")[1]);
            }else {
                resp.getWriter().println("Invalid Id");
                return;
            }

            UserDao dao = new UserDao();

            com.model.User user= dao.getUser(id);
            if (user==null){
                resp.getWriter().println("User does not exists");
                return;
            }

            resp.setContentType("application/json");
            resp.getWriter().println(objectMapper.writeValueAsString(user));

        }catch (Exception e){
            e.printStackTrace();
            resp.getWriter().write("Error reading or processing get data "+ e.getMessage());

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            StringBuilder requestBody = new StringBuilder();
            String line;

            // Read the request body line by line
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }

            System.out.println("Line" +requestBody);

            // Map the request body to a Java object using Jackson
            com.model.User user = objectMapper.readValue(requestBody.toString(), com.model.User.class);

            // Now, 'user' contains the mapped data
            System.out.println("Received POST data as Java object: Name :" + user.getName() +"  Email : "+ user.getEmail());

            UserDao dao = new UserDao();
            dao.insertUser(user);

            // Send a response back to the client
            response.getWriter().write("POST data received and processed successfully");
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
            response.getWriter().write("Error reading or processing POST data");
        }
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr=request.getPathInfo();
            int id;
            if (idStr!=null){
                id=Integer.parseInt(idStr.split("/")[1]);
            }else {
                response.getWriter().println("Invalid Id");
                return;
            }

            UserDao dao=new UserDao();
            com.model.User existingUser = dao.getUser(id);

            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder requestBody = new StringBuilder();
            String line;

            // Read the request body line by line
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }

            System.out.println("Line" +requestBody);

            // Map the request body to a Java object using Jackson
            com.model.User updatedUserData = objectMapper.readValue(requestBody.toString(), com.model.User.class);

            existingUser.setName(updatedUserData.getName());
            existingUser.setEmail(updatedUserData.getEmail());
            existingUser.setPhNo(updatedUserData.getPhNo());

            Boolean success=dao.updateUser(existingUser);

            if (success){
                response.setContentType("application/json");
                response.getWriter().println(objectMapper.writeValueAsString(existingUser));
            }else {
                response.getWriter().print("Unable to Update");
            }

        } catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("Error reading or processing PUT data "+ e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // change it to path variable
       try{
        String idStr=request.getPathInfo();
        int id;
        if (idStr!=null){
            id=Integer.parseInt(idStr.split("/")[1]);
            System.out.println(id);

            UserDao dao=new UserDao();
            boolean success = dao.deleteUser(id);
            String message;

            if (success){
                response.setStatus(HttpServletResponse.SC_OK);
                message = "Resourse with id " + id + "deleted successfull";
            }else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                message = "Resourse with id " + id + "deleted unsuccessfull";
            }

            System.out.println(message);
            PrintWriter out = response.getWriter();
            out.print(message);
            out.flush();

        }else {
            response.getWriter().println("Invalid Id");
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.print("Please provide an ID");
            out.flush();
            return;
        }
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("Error reading or processing Delete data "+ e.getMessage());
            System.out.println(e.toString());
        }


    }

    }

