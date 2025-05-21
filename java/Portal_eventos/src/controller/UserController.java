package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import entities.Inscripcion;
import entities.Usuario;
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

    public List<Inscripcion> getAllInscription(Usuario user) {
        System.out.println(user.getId_usuario());
        List<Inscripcion> list = modelDB.getEnrollmentList(user.getId_usuario());
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
