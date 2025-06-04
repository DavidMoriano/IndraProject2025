package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.entities.Inscripcion;
import com.model.ModelDatabase;

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

    public List<Inscripcion> getAllInscription(int id) {
        List<Inscripcion> list = modelDB.getEnrollmentList(id);
        return list;
    }

    public String cancelEnrollment(int idInsc) {
        Boolean valid = modelDB.cancelEnrollment(idInsc);
        if (valid) {
            return "Cancelación completada";
        } else {
            return "Error al cancelar la inscripción.";
        }
    }
}
