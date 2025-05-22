package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import entities.Evento;
import entities.Organizador;
import entities.Ubicacion;
import entities.Usuario;
import model.ModelDatabase;

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
    
}
