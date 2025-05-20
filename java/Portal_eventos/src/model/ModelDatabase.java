package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import databaseConfig.DatabaseConnection;
import entities.Evento;
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
		String query = "SELECT nombre, password, email FROM usuarios WHERE nombre like ?";

		try {
			PreparedStatement ps2 = connection.prepareStatement(query);

			ps2.setString(1, user.getNombre());

			ResultSet rs = ps2.executeQuery();

			if (rs.next()) {
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

	public Boolean validUserName(Usuario user) {
		String query = "SELECT count(*) FROM usuarios where nombre like ?";

		try {
			PreparedStatement ps2 = connection.prepareStatement(query);
			ps2.setString(1, user.getNombre());

			ResultSet rs = ps2.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count == 0) {
					return true;
				}
			}
			rs.close();
			ps2.close();
			return false;
		} catch (Exception e) {
			return false;
		}
	}

    public List<Evento> getShowList() {
		String query = "SELECT id_evento, nombre, fecha, duracion, estado, id_categoria, id_organizador, id_ubicacion FROM eventos;";

		try {
			List <Evento> eventsList = new ArrayList<>();	
			PreparedStatement ps2 = connection.prepareStatement(query);
			ResultSet rs = ps2.executeQuery();

			while (rs.next()) {
				Evento e = new Evento();
				e.setId_evento(rs.getInt(1));
				e.setNombre(rs.getString(2));
				e.setFecha(rs.getString(3));
				e.setDuracion(rs.getInt(4));
				e.setEstado(rs.getString(5));
				e.setId_categoria(rs.getInt(6));
				e.setId_organizador(rs.getInt(7));
				e.setId_ubicacion(rs.getInt(8));

				eventsList.add(e);
			}
			return eventsList;
		} catch (Exception e) {
			return null;
		}

    }

}
