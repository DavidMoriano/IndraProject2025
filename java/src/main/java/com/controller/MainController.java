package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.SecurePassword.HassPassword;
import com.entities.Categoria;
import com.entities.Evento;
import com.entities.Organizador;
import com.entities.Usuario;
import com.model.ModelDatabase;

public class MainController {
    private ModelDatabase modelDB;

    public MainController() throws ClassNotFoundException, SQLException, IOException {
        this.modelDB = new ModelDatabase();

    }

    public Usuario login(Usuario user) {
        Usuario loginUser = modelDB.validLoginUser(user);
        if (HassPassword.checkPassword(user.getPassword(), loginUser.getPassword())) {
            return loginUser;
        } else {
            return null;
        }
    }

    public Usuario userRegister(Usuario user) {
        Boolean validUser = modelDB.validUserNameToRegister(user);
          if (validUser) {
            Boolean valid = modelDB.insertUser(user);
            if (valid) {
                return user;
            } 
            return null;
        } else {
            return null;
        }
    }

    public List<Evento> showEventList(int idCategoria, Integer idOrganizador) {
        List<Evento> showList = modelDB.getShowList(idCategoria, idOrganizador);
        return showList;
    }

    public List<Categoria> showCategoryList() {
        List<Categoria> categoryList = modelDB.getCategoryList();
        return categoryList;
    }

    public Organizador loginOrg(Organizador orgLogin) {
        Organizador orgDB = modelDB.validLoginOrg(orgLogin.getNombre());
        String infoDB = orgDB.getInformacion_contacto();
        String infoOrgLogin = orgLogin.getInformacion_contacto();
        if (infoDB.equals(infoOrgLogin)) {
            return orgDB;
        } else {
            return null;
        }
    }

    public Organizador orgRegister(Organizador org) {
        Boolean validOrg = modelDB.validOrgNameToRegister(org);
        if (validOrg) {
            Boolean valid = modelDB.insertOrg(org);
            if (valid) {
                return org;
            } 
            return null;
        } else {
            return null;
        }
    }
}