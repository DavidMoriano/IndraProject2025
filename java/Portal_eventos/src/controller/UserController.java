package controller;

import java.io.IOException;
import java.sql.SQLException;

import model.ModelDatabase;

public class UserController {
    private ModelDatabase modelDB;

    public UserController() throws ClassNotFoundException, SQLException, IOException {
        this.modelDB = new ModelDatabase();
    }

    public String enrollment(int idEvento, int id, String fechaEvento) {
        Boolean valid = modelDB.completeEnrollment(idEvento, id, fechaEvento);
        if (valid) {
            return "Inscripción terminada";
        } else {
            return "Error al realizar la inscripción.";
        }
    }
}
