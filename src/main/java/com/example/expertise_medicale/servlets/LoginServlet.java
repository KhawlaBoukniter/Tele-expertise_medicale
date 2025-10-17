package com.example.expertise_medicale.servlets;

import com.example.expertise_medicale.models.Patient;
import com.example.expertise_medicale.models.User;
import com.example.expertise_medicale.models.enums.Role;
import com.example.expertise_medicale.services.AuthService;
import com.example.expertise_medicale.services.PatientService;
import com.example.expertise_medicale.services.SpecialisteService;
import com.example.expertise_medicale.services.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private AuthService authService;
    private PatientService patientService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        UserService<User> userService = new UserService<>(User.class);
        this.authService = new AuthService(userService);
        this.patientService = new PatientService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email =  request.getParameter("email");
        String password = request.getParameter("password");

        User user = authService.login(email, password);

        if (user != null) {
            request.getSession().setAttribute("user", user);
            List<Patient> patients = patientService.findByToday();

            if (user.getRole().equals(Role.GENERALISTE)) {
                    response.sendRedirect(request.getContextPath() + "/generaliste?action=list");
            } else if (user.getRole().equals(Role.INFIRMIER)) {
                request.getSession().setAttribute("patients", patients);
                request.getRequestDispatcher("/patients.jsp").forward(request, response);

            } else if (user.getRole().equals(Role.SPECIALISTE)) {
                response.sendRedirect("specialiste");

            } else {
                request.setAttribute("error", "Role invalide");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else  {
            request.setAttribute("error", "Identifiants incorrects");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }


    }
}
