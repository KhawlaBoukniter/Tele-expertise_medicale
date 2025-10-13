package com.example.expertise_medicale.services;

import com.example.expertise_medicale.dao.GeneralisteDAO;
import com.example.expertise_medicale.dao.UserDAO;
import com.example.expertise_medicale.models.Generaliste;
import com.example.expertise_medicale.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserService<T extends User> {
    private final UserDAO<T> userDAO;

    public UserService(Class<T> entityClass) {
        this.userDAO = new UserDAO<>(entityClass);
    }

    public void add(T user) {
        String salt = BCrypt.gensalt();
        String hashed = BCrypt.hashpw(user.getPassword(), salt);
        user.setPassword(hashed);
        userDAO.add(user);
    }

    public void update(T user) {
        userDAO.update(user);
    }

    public void delete(T user){ userDAO.delete(user);}

    public List<T> findAll(){ return userDAO.findAll(); }

    public User findByEmailPassword(String email, String password) {
        return findAll().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email) && BCrypt.checkpw(password, user.getPassword()))
                .findFirst().orElse(null);
    }

    public Boolean existsByEmail(String email) {
        return findAll().stream().anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    public T findById(Long id) {
        return findAll().stream().filter(u -> id.equals(u.getId())).findFirst().orElse(null);
    }
}
