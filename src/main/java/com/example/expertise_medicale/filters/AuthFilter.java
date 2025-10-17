package com.example.expertise_medicale.filters;

import com.example.expertise_medicale.models.User;
import com.example.expertise_medicale.models.enums.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI();
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && (path.endsWith("login") || path.endsWith("login.jsp") ||
                path.endsWith("signup") || path.endsWith("signup.jsp"))) {

            switch (user.getRole()) {
                case SPECIALISTE -> response.sendRedirect(request.getContextPath() + "/specialiste");
                case GENERALISTE -> response.sendRedirect(request.getContextPath() + "/generaliste?action=list");
                case INFIRMIER -> response.sendRedirect(request.getContextPath() + "/patients.jsp");
                default -> response.sendRedirect(request.getContextPath() + "/");
            }
            return;
        }

        if (path.endsWith("login") || path.endsWith("login.jsp") || path.endsWith("signup") || path.endsWith("signup.jsp")) {
            chain.doFilter(request, response);
            return;
        }

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if (path.contains("/specialiste") && !user.getRole().equals(Role.SPECIALISTE)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if (path.contains("/generaliste") && !user.getRole().equals(Role.GENERALISTE)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if (path.contains("/patient") && !user.getRole().equals(Role.INFIRMIER)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void destroy() { }
}
