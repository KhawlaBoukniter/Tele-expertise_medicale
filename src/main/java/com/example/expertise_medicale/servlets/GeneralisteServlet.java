package com.example.expertise_medicale.servlets;

import com.example.expertise_medicale.models.Generaliste;
import com.example.expertise_medicale.models.enums.Role;
import com.example.expertise_medicale.services.GeneralisteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "GeneralisteServlet", value = "/generalistes")
public class GeneralisteServlet extends HttpServlet {
    private GeneralisteService generalisteService  = new GeneralisteService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Generaliste> generalistes = generalisteService.findAll();
        request.setAttribute("generalistes", generalistes);
        request.getRequestDispatcher("generalistes.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Generaliste generaliste = new Generaliste();
        generaliste.setNom(nom);
        generaliste.setPrenom(prenom);
        generaliste.setEmail(email);
        generaliste.setPassword(password);
        generaliste.setRole(Role.GENERALISTE);
        generaliste.setCreated_at(LocalDateTime.now());
        generalisteService.add(generaliste);
        response.sendRedirect("generalistes");
    }
}
