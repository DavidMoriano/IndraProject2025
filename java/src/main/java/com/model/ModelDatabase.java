package com.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SecurePassword.HassPassword;
import com.databaseConfig.DatabaseConnection;
import com.entities.Categoria;
import com.entities.Evento;
import com.entities.Inscripcion;
import com.entities.Organizador;
import com.entities.Ubicacion;
import com.entities.Usuario;

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
			ps1.setString(3, HassPassword.hashPassword(user.getPassword()));

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

	public List<Evento> getShowList(int idCategoria, Integer idOrganizador) {
		String query = "SELECT id_evento, nombre, fecha, duracion, estado, id_categoria, id_organizador, id_ubicacion FROM eventos WHERE "
				+
				"(? = 0 OR id_categoria = ?) AND (? IS NULL OR id_organizador = ?)";

		try {
			List<Evento> eventsList = new ArrayList<>();
			PreparedStatement ps2 = connection.prepareStatement(query);

			ps2.setInt(1, idCategoria);
			ps2.setInt(2, idCategoria);
			if (idOrganizador == null) {
				ps2.setNull(3, java.sql.Types.INTEGER);
				ps2.setNull(4, java.sql.Types.INTEGER);
			} else {
				ps2.setInt(3, idOrganizador);
				ps2.setInt(4, idOrganizador);
			}

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
		String query = "SELECT id_organizador, nombre, informacion_contacto FROM organizadores WHERE nombre like ?";

		try {
			PreparedStatement ps2 = connection.prepareStatement(query);

			ps2.setString(1, nombre);

			ResultSet rs = ps2.executeQuery();

			if (rs.next()) {
				int idDb = rs.getInt(1);
				String nameDb = rs.getString(2);
				String info = rs.getString(3);

				Organizador org = new Organizador();

				org.setId_organizadores(idDb);
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

	public List<Usuario> getAllUsers() {
		List<Usuario> userList = new ArrayList<>();
		String query = "SELECT id_usuario, nombre FROM usuarios";
		try {
			PreparedStatement ps2 = connection.prepareStatement(query);
			ResultSet rs = ps2.executeQuery();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId_usuario(rs.getInt(1));
				u.setNombre(rs.getString(2));
				userList.add(u);
			}
			return userList;
		} catch (Exception e) {
			return null;
		}

	}

	public List<Evento> getEventsOrgs(Organizador org) {
		List<Evento> list = new ArrayList<>();
		String query = "SELECT id_evento, nombre, fecha, duracion, estado, id_categoria, id_organizador, id_ubicacion from eventos where id_organizador = ?";

		try {
			PreparedStatement ps2 = connection.prepareStatement(query);
			ps2.setInt(1, org.getId_organizadores());
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
				list.add(e);
			}

			return list;
		} catch (Exception e) {
			return null;
		}

	}

	public List<Ubicacion> getLocationList() {
		List<Ubicacion> list = new ArrayList<>();

		String query = "SELECT id_ubicacion, tipo, direccion from ubicaciones";
		try {
			PreparedStatement ps2 = connection.prepareStatement(query);
			ResultSet rs = ps2.executeQuery();

			while (rs.next()) {
				Ubicacion u = new Ubicacion();
				u.setId_ubicacion(rs.getInt(1));
				u.setTipo(rs.getString(2));
				u.setDireccion(rs.getString(3));
				list.add(u);
			}

			return list;
		} catch (Exception e) {
			return null;
		}

	}

	public boolean createEvent(Evento even) {
		String query = "INSERT INTO eventos (nombre, fecha, duracion, estado, id_categoria, id_organizador, id_ubicacion) values (?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps2 = connection.prepareStatement(query);

			ps2.setString(1, even.getNombre());
			ps2.setString(2, even.getFecha());
			ps2.setInt(3, even.getDuracion());
			ps2.setString(4, even.getEstado());
			ps2.setInt(5, even.getId_categoria());
			ps2.setInt(6, even.getId_organizador());
			ps2.setInt(7, even.getId_ubicacion());

			ps2.executeUpdate();

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean showCancel(int idEvento) {
		String query = "UPDATE eventos SET estado = 'cancelado' WHERE id_evento = ?";
		try (PreparedStatement ps2 = connection.prepareStatement(query)) {
			ps2.setInt(1, idEvento);
			int rowsUpdated = ps2.executeUpdate();
			return rowsUpdated > 0;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean editEvent(Evento eToEdit) {
		String query = "UPDATE eventos SET nombre = ?, fecha = ?, duracion = ?, id_categoria = ?, id_ubicacion = ? WHERE id_evento = ?";

		try {
			PreparedStatement ps2 = connection.prepareStatement(query);
			ps2.setString(1, eToEdit.getNombre());
			ps2.setString(2, eToEdit.getFecha());
			ps2.setInt(3, eToEdit.getDuracion());
			ps2.setInt(4, eToEdit.getId_categoria());
			ps2.setInt(5, eToEdit.getId_ubicacion());
			ps2.setInt(6, eToEdit.getId_evento());

			ps2.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addUbi(Ubicacion uToEdit) {
		String query2 = "INSERT INTO ubicaciones (tipo, direccion) VALUES (?, ?)";
		String tipo = uToEdit.getTipo();
		try {
			PreparedStatement ps3 = connection.prepareStatement(query2);
			ps3.setString(1, uToEdit.getTipo());
			if (tipo.equals("presencial")) {
				ps3.setString(2, uToEdit.getDireccion());
			} else {
				ps3.setString(2, "");
			}
			ps3.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
