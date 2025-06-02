package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.entities.Evento;
import com.entities.Organizador;
import com.entities.Ubicacion;
import com.entities.Usuario;
import com.model.ModelDatabase;

public class OrgController {
    private ModelDatabase modelDatabase;

    public OrgController() throws ClassNotFoundException, SQLException, IOException {
        this.modelDatabase = new ModelDatabase();
    }

    public List<Usuario> showUserList() {
        List<Usuario> userList = modelDatabase.getAllUsers();
        return userList;
    }

    public List<Evento> getOrgList(Organizador org) {
        List<Evento> list = modelDatabase.getEventsOrgs(org);
        return list;
    }

    public List<Ubicacion> getLocations() {
        List<Ubicacion> list = modelDatabase.getLocationList();
        return list;
    }

    public String createEvent(Evento e) {
        boolean valid = modelDatabase.createEvent(e);

        if (valid) {
            return "Evento creado correctamente";
        } else {
            return "Error al crear evento.";
        }
    }

    public String showCancel(int idEvento) {
        boolean valid = modelDatabase.showCancel(idEvento);
        if (valid) {
            return "Cancelación del evento correcta.";
        } else {
            return "Error al cancelar el evento";
        }
    }

    public String editEvent(Evento eToEdit) {
        boolean valid = modelDatabase.editEvent(eToEdit);

        if (valid) {
            return "Evento editado correctamente";
        } else {
            return "Error al editar un evento";
        }
    }

    public String newUbi(Ubicacion uToEdit) {
        boolean valid = modelDatabase.addUbi(uToEdit);

        if (valid) {
            return "Ubicacion añadida correctamente";
        } else {
            return "Error al editar un evento";
        }
    }

}
