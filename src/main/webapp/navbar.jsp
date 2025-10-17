<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav>
    <div style="display: flex; align-items: baseline; justify-content: end">

        <!-- Bouton Déconnexion -->
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
            <button type="submit" class="btn btn-light btn-sm fw-semibold rounded-pill px-3">
                Déconnexion
            </button>
        </form>
    </div>
</nav>
