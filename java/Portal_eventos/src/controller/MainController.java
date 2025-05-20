package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import entities.Evento;
import entities.Usuario;
import model.ModelDatabase;

public class MainController {
    private ModelDatabase modelDB;

    public MainController() throws ClassNotFoundException, SQLException, IOException {
        this.modelDB = new ModelDatabase();
    }

    public String login(Usuario user) {
        Usuario loginUser = modelDB.validLoginUser(user);
        if (loginUser.equals(user)) {
           return "Bienvenido " + user.getNombre();
        } else {
           return "Error al hacer login";
        }
    }

    public String userRegister(Usuario user) {
        Boolean validUser = modelDB.validUserName(user);
        Boolean valid = modelDB.validUser(user);
        if (valid && validUser) {
           return "Registrado correctamente";
        } else {
           return "Error al registrar";
        }
    }

    public List<Evento> showEventList() {
        List<Evento> showList = modelDB.getShowList();
        return showList;
    }
}
