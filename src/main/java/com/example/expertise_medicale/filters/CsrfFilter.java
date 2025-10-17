package com.example.expertise_medicale.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.UUID;

@WebFilter("/*")
public class CsrfFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();

        if (session.getAttribute("csrfToken") == null) {
            session.setAttribute("csrfToken", UUID.randomUUID().toString());
        }

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String tokenForm = request.getParameter("csrfToken");
            String tokenSession = (String) session.getAttribute("csrfToken");

            if (tokenForm == null || !tokenForm.equals(tokenSession)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "CSRF token invalide");
                return;
            }
        }

        chain.doFilter(req, res);
    }
}
