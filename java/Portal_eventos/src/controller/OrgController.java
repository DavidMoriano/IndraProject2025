package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
    
}
