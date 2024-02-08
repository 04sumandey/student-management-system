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

@WebServlet("/user")
public class User extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("login Success");

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
}
