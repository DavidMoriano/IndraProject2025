package views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import controller.MainController;
import controller.OrgController;
import controller.UserController;
import entities.Categoria;
import entities.Evento;
import entities.Inscripcion;
import entities.Organizador;
import entities.Ubicacion;
import entities.Usuario;
import utils.TerminalUtils;

public class OrgView {
    private OrgController orgController;
    private MainController mainController;
    private UserController userController;

    public OrgView() throws ClassNotFoundException, SQLException, IOException {
        this.orgController = new OrgController();
        this.mainController = new MainController();
        this.userController = new UserController();
    }

    public void listToDo(Organizador org) {
        int option = -1;
        do {
            option = menu();

            switch (option) {
                case 0:
                    TerminalUtils.out("Volviendo al inicio...");
                    break;
                case 1:
                    showListUser(org);
                    break;
                case 2:
                    newEvent(org);
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    showCreatedEvents(org);
                    break;
                case 6:
                    showPersonalData(org);
                    break;
                default:
                    break;
            }
        } while (option != 0);
    }

    private void showPersonalData(Organizador org) {
        TerminalUtils.out("PERFIL PERSONAL");
        TerminalUtils.out("===============");
        TerminalUtils.out("Id del organizador : " + org.getId_organizadores());
        TerminalUtils.out("Nombre de organizador : " + org.getNombre());
        TerminalUtils.out("Información de contacto : " + org.getInformacion_contacto());
        TerminalUtils.out("\n");
    }

    private void showCreatedEvents(Organizador org) {
        List<Evento> showList = orgController.getOrgList(org);
        TerminalUtils.out("LISTA DE EVENTOS DEL ORGANIZADOR " + org.getNombre());
        TerminalUtils.out("==========================================");
        TerminalUtils.out(Evento.getHeader());
        for(Evento e : showList) {
            TerminalUtils.out(e.toString());
        }
        TerminalUtils.out("\n");
    }

    private void newEvent(Organizador org) {
        TerminalUtils.out("CREAR NUEVO EVENTO");
        TerminalUtils.out("==================");    
        Evento e = new Evento();
        List<Categoria> categoryList = mainController.showCategoryList();
        TerminalUtils.out(Categoria.getHeader());
        for (Categoria c : categoryList) {
            TerminalUtils.out(c.toString());
        }
        TerminalUtils.out("Indica primero a que categoría pertenece el evento a crear: ");
        int idCategoria = TerminalUtils.getInt();
        e.setId_categoria(idCategoria);

        List<Ubicacion> whereList = orgController.getLocations();
        TerminalUtils.out(Ubicacion.getHeader());
        for (Ubicacion u : whereList) {
            TerminalUtils.out(u.toString());
        }
        TerminalUtils.out("Indica en que ubicación será el evento");
        int idUbicacion = TerminalUtils.getInt();  
        e.setId_ubicacion(idUbicacion);
        
        TerminalUtils.out("Indica el nombre del evento");
        String nombre = TerminalUtils.getString();
        e.setNombre(nombre);

        TerminalUtils.out("Indica la duración del evento");
        int duracion = TerminalUtils.getInt();
        e.setDuracion(duracion);
        e.setId_organizador(org.getId_organizadores());

        TerminalUtils.out("Indica el estado en el que se encontrará el evento");
        String estado = TerminalUtils.getString();
        e.setEstado(estado);

        TerminalUtils.out("Indica la fecha del evento (YYYY-MM-DD)");
        String fecha = TerminalUtils.getString();
        e.setFecha(fecha);

        String result = orgController.createEvent(e);
        TerminalUtils.out(result);
    }

    private void showListUser(Organizador org) {
        List<Evento> showList = mainController.showEventList(0, org.getId_organizadores());
        List<Usuario> userList = orgController.showUserList();

        if (!userList.isEmpty()) {
            for (Evento e : showList) {
                if (e.getId_organizador() == org.getId_organizadores()) {
                    for (Usuario u : userList) {
                        List<Inscripcion> enrollmentList = userController.getAllInscription(u.getId_usuario());
                        for (Inscripcion i : enrollmentList) {
                            if (i.getId_evento() == e.getId_evento()) {
                                TerminalUtils.out("Nombre del evento: " + e.getNombre());
                                TerminalUtils.out("========================================================");
                                TerminalUtils.out(String.format("%-10s %-20s", "Id", "Nombre usuario"));
                                TerminalUtils.out(String.format("%-10s %-20s", u.getId_usuario(), u.getNombre()));
                                TerminalUtils.out("\n");
                            }
                        }
                    }

                }
            }
        } else {
            TerminalUtils.out("Lista de usuarios vacía");
        }

    }

    private int menu() {
        int option = -1;
        TerminalUtils.out("0. Salir.");
        TerminalUtils.out("1. Mostrar la lista de los usuarios por evento.");
        TerminalUtils.out("2. Crear evento.");
        TerminalUtils.out("3. Cancelar evento.");
        TerminalUtils.out("4. Modificar evento.");
        TerminalUtils.out("5. Mostrar eventos creados.");
        TerminalUtils.out("6. Mostrar datos personales.");
        TerminalUtils.out("\n");
        TerminalUtils.out("Introduce la opción a escoger: ");
        option = TerminalUtils.getInt();
        return option;
    }
}
