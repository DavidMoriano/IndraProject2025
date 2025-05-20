package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseConfig.DatabaseConnection;
import entities.Usuario;

public class ModelDatabase {

private Connection connection;
	
	public ModelDatabase() throws ClassNotFoundException, SQLException, IOException {
		
		this.connection = DatabaseConnection.getConnection();
	}

    public Boolean validUser(Usuario user) {
        try {
			String query = "INSERT INTO usuarios (nombre, email, password) value (?, ?, ?)";
			PreparedStatement ps1 = connection.prepareStatement(query);
			
			ps1.setString(1, user.getNombre());
			ps1.setString(2, user.getEmail());
			ps1.setString(3, user.getPassword());
			
			ps1.executeUpdate();
		} catch (Exception e) {
			return false;
		}
		
		return true;
    }

    public Usuario validLoginUser(Usuario user) {
        String query = "SELECT nombre, password, email FROM usuario WHERE nombre like ?";
		
		try {
			PreparedStatement ps2 = connection.prepareStatement(query);

			ps2.setString(1, user.getNombre());
			
			ResultSet rs = ps2.executeQuery();
			
			if (rs.next()) {
	            System.out.println("Si encontrado");
				String nameDb = rs.getString(1);
				String password = rs.getString(2);
				String emailDb = rs.getString(3);

				Usuario userDB = new Usuario();
                userDB.setNombre(nameDb);
                userDB.setPassword(password);
                userDB.setEmail(emailDb);

				return user;
			} else {
	            return null;
	        }
		} catch (Exception e) {
            return null;
		}
    }
    
}
