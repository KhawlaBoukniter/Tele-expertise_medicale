package com.example.expertise_medicale.servlets;

import com.example.expertise_medicale.dao.UserDAO;
import com.example.expertise_medicale.models.Generaliste;
import com.example.expertise_medicale.models.Infirmier;
import com.example.expertise_medicale.models.Specialiste;
import com.example.expertise_medicale.models.User;

import com.example.expertise_medicale.models.enums.Role;
import com.example.expertise_medicale.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/signupServlet")
public class SignupServlet extends HttpServlet {
    private UserService userService = new UserService<>(User.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String roleParam = request.getParameter("role");

        if(userService.existsByEmail(email)) {
            request.setAttribute("errorMessage", "Email déjà utilisé");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        Role role = Role.valueOf(roleParam);
        LocalDateTime now = LocalDateTime.now();

        switch (role) {
            case GENERALISTE:
                Generaliste generaliste = new Generaliste();
                generaliste.setNom(nom);
                generaliste.setPrenom(prenom);
                generaliste.setEmail(email);
                generaliste.setPassword(password);
                generaliste.setRole(role);
                generaliste.setCreated_at(now);
                generaliste.setUpdated_at(now);
                generaliste.setListe_patients("");
                userService.add(generaliste);
                break;

            case SPECIALISTE:
                Specialiste specialiste = new Specialiste();
                specialiste.setNom(nom);
                specialiste.setPrenom(prenom);
                specialiste.setEmail(email);
                specialiste.setPassword(password);
                specialiste.setRole(role);
                specialiste.setCreated_at(now);
                specialiste.setUpdated_at(now);
                specialiste.setCrenaux("");
                specialiste.setListe_demandes("");
                specialiste.setTarif(0.0);
                specialiste.setDuree_consultation(30);
                userService.add(specialiste);
                break;

            case INFIRMIER:
                Infirmier infirmier = new Infirmier();
                infirmier.setNom(nom);
                infirmier.setPrenom(prenom);
                infirmier.setEmail(email);
                infirmier.setPassword(password);
                infirmier.setRole(role);
                infirmier.setCreated_at(now);
                infirmier.setUpdated_at(now);
                infirmier.setListe_patients("");
                userService.add(infirmier);
                break;

            default:
                request.setAttribute("errorMessage", "Rôle inconnu");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
                return;
        }

        response.sendRedirect("login.jsp");
    }
}
