package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import databaseConfig.DatabaseConnection;
import entities.Categoria;
import entities.Evento;
import entities.Inscripcion;
import entities.Organizador;
import entities.Usuario;

public class ModelDatabase {

	private Connection connection;

	public ModelDatabase() throws ClassNotFoundException, SQLException, IOException {

		this.connection = DatabaseConnection.getConnection();
	}

	public Boolean insertUser(Usuario user) {
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
		String query = "SELECT id_usuario, nombre, password, email FROM usuarios WHERE nombre like ?";

		try {
			PreparedStatement ps2 = connection.prepareStatement(query);

			ps2.setString(1, user.getNombre());

			ResultSet rs = ps2.executeQuery();

			if (rs.next()) {
				int idDb = rs.getInt(1);
				String nameDb = rs.getString(2);
				String password = rs.getString(3);
				String emailDb = rs.getString(4);

				Usuario userDB = new Usuario();
				userDB.setId_usuario(idDb);
				userDB.setNombre(nameDb);
				userDB.setPassword(password);
				userDB.setEmail(emailDb);

				return userDB;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public Boolean validUserNameToRegister(Usuario user) {
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

	public List<Evento> getShowList(int idCategoria) {
		String query = "SELECT id_evento, nombre, fecha, duracion, estado, id_categoria, id_organizador, id_ubicacion FROM eventos WHERE \n"
				+
				"CASE WHEN ? = 0 then true else id_categoria = ? end";

		try {
			List<Evento> eventsList = new ArrayList<>();
			PreparedStatement ps2 = connection.prepareStatement(query);
			ps2.setInt(1, idCategoria);
			ps2.setInt(2, idCategoria);

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

	public List<Categoria> getCategoryList() {
		List<Categoria> categoryList = new ArrayList<>();
		String query = "SELECT id_categoria, nombre FROM categorias";
		try {
			PreparedStatement ps2 = connection.prepareStatement(query);
			ResultSet rs = ps2.executeQuery();
			while (rs.next()) {
				Categoria c = new Categoria();
				c.setId_categoria(rs.getInt(1));
				c.setNombre(rs.getString(2));

				categoryList.add(c);
			}
			return categoryList;
		} catch (Exception e) {
			return null;
		}
	}

	public Organizador validLoginOrg(String nombre) {
		String query = "SELECT nombre, informacion_contacto FROM organizadores WHERE nombre like ?";

		try {
			PreparedStatement ps2 = connection.prepareStatement(query);

			ps2.setString(1, nombre);

			ResultSet rs = ps2.executeQuery();

			if (rs.next()) {
				String nameDb = rs.getString(1);
				String info = rs.getString(2);

				Organizador org = new Organizador();

				org.setNombre(nameDb);
				org.setInformacion_contacto(info);
				return org;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public Boolean insertOrg(Organizador org) {
		try {
			String query = "INSERT INTO organizadores (nombre, informacion_contacto) value (?, ?)";
			PreparedStatement ps1 = connection.prepareStatement(query);

			ps1.setString(1, org.getNombre());
			ps1.setString(2, org.getInformacion_contacto());

			ps1.executeUpdate();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public Boolean validOrgNameToRegister(Organizador org) {
		String query = "SELECT count(*) FROM organizadores where nombre like ?";

		try {
			PreparedStatement ps2 = connection.prepareStatement(query);
			ps2.setString(1, org.getNombre());

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

	public Boolean completeEnrollment(int idEvento, int id, String fechaEvento) {
		try {
			String query = "INSERT INTO inscripciones (id_evento, id_usuario, fecha_inscripcion) values (?, ?, concat(?, ' ', curtime()))";
			PreparedStatement ps1 = connection.prepareStatement(query);

			ps1.setInt(1, idEvento);
			ps1.setInt(2, id);
			ps1.setString(3, fechaEvento);
			ps1.executeUpdate();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public List<Inscripcion> getEnrollmentList(int id_usuario) {
		String query = "SELECT id_inscripcion, eventos.id_evento, fecha_inscripcion from inscripciones inner join eventos on inscripciones.id_evento = eventos.id_evento where inscripciones.id_usuario = ?";
		List<Inscripcion> list = new ArrayList<>();
		try {
			PreparedStatement ps2 = connection.prepareStatement(query);
			ps2.setInt(1, id_usuario);
			ResultSet rs = ps2.executeQuery();
			while (rs.next()) {
				Inscripcion i = new Inscripcion();
				i.setId_inscripcion(rs.getInt(1));
				i.setId_evento(rs.getInt(2));
				i.setFecha_inscripcion(rs.getString(3));
				list.add(i);
			}
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public Boolean cancelEnrollment(int idInsc) {
		String query = "DELETE FROM inscripciones where id_inscripcion = ?";
		 try {
			PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idInsc);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Error al cancelar la inscripción: " + e.getMessage());
            return false;
        }
	}

}
