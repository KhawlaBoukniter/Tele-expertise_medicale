package com.example.expertise_medicale.services;

import com.example.expertise_medicale.dao.GeneralisteDAO;
import com.example.expertise_medicale.models.Generaliste;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class GeneralisteService extends UserService<Generaliste>{

    public GeneralisteService() {
        super(Generaliste.class);
    }


}
