package com.views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.controller.MainController;
import com.controller.OrgController;
import com.controller.UserController;
import com.entities.Categoria;
import com.entities.Evento;
import com.entities.Inscripcion;
import com.entities.Organizador;
import com.entities.Ubicacion;
import com.entities.Usuario;
import com.utils.TerminalUtils;

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
                    TerminalUtils.out("\n");
                    break;
                case 1:
                    showListUser(org);
                    break;
                case 2:
                    newEvent(org);
                    TerminalUtils.out("\n");
                    break;
                case 3:
                    String result = showCancel(org);
                    TerminalUtils.out(result + "\n");
                    break;
                case 4:
                    String result1 = editEvent(org);
                    TerminalUtils.out(result1 + "\n");
                    break;
                case 5:
                    showCreatedEvents(org);
                    TerminalUtils.out("\n");
                    break;
                case 6:
                    showPersonalData(org);
                    TerminalUtils.out("\n");
                    break;
                default:
                    break;
            }
        } while (option != 0);
    }

    private String editEvent(Organizador org) {
        int opcion = 0;
        Evento eToEdit = new Evento();
        Ubicacion uToEdit = new Ubicacion();
        do {
            List<Evento> showList = mainController.showEventList(0, org.getId_organizadores());
            TerminalUtils.out("LISTA DE EVENTOS");
            TerminalUtils.out("================");
            TerminalUtils.out(Evento.getHeader());
            for (Evento e : showList) {
                TerminalUtils.out(e.toString());
            }
            TerminalUtils.out("Introduce el evento que quiere modificar");
            int idEvento = TerminalUtils.getInt();

            for (Evento e : showList) {
                if (idEvento == e.getId_evento()) {
                    eToEdit.setDuracion(e.getDuracion());
                    eToEdit.setNombre(e.getNombre());
                    eToEdit.setEstado(e.getEstado());
                    eToEdit.setFecha(e.getFecha());
                    eToEdit.setId_categoria(e.getId_categoria());
                    eToEdit.setId_evento(idEvento);
                    eToEdit.setId_organizador(org.getId_organizadores());
                    eToEdit.setId_ubicacion(e.getId_ubicacion());
                }
            }

            TerminalUtils.out("CAMPOS A EDITAR DE LOS EVENTOS");
            TerminalUtils.out("==============================");
            TerminalUtils.out("1. Nombre");
            TerminalUtils.out("2. Fecha");
            TerminalUtils.out("3. Duración");
            TerminalUtils.out("4. Categoría");
            TerminalUtils.out("5. Ubicación");

            TerminalUtils.out("Introduce qué campo quiere editar");
            opcion = TerminalUtils.getInt();
            switch (opcion) {
                case 0:
                    TerminalUtils.out("Saliendo de la opción editar...");
                    break;
                case 1:
                    TerminalUtils.out("EDITANDO NOMBRE");
                    TerminalUtils.out("===============");

                    TerminalUtils.out("Nombre antiguo : " + eToEdit.getNombre());
                    TerminalUtils.out("Introduce el nuevo nombre ");
                    String newName = TerminalUtils.getString();
                    eToEdit.setNombre(newName);

                    break;
                case 2:
                    TerminalUtils.out("EDITANDO FECHA");
                    TerminalUtils.out("==============");

                    TerminalUtils.out("Fecha antigua : " + eToEdit.getFecha());
                    TerminalUtils.out("Introduce la nueva fecha (YYYY-MM-DD)");
                    String fecha = TerminalUtils.getString();
                    eToEdit.setFecha(fecha);
                    break;
                case 3:
                    TerminalUtils.out("EDITANDO DURACIÓN");
                    TerminalUtils.out("=================");

                    TerminalUtils.out("Duracion antigua : " + eToEdit.getDuracion());
                    TerminalUtils.out("Introduce la nueva duracion (horas)");
                    int duracion = TerminalUtils.getInt();
                    eToEdit.setDuracion(duracion);
                    break;
                case 4:
                    TerminalUtils.out("EDITANDO CATEGORÍA");
                    TerminalUtils.out("==================");

                    List<Categoria> categoryList = mainController.showCategoryList();
                    TerminalUtils.out("Lista de categorías\n");
                    String nombreCategoria = "";
                    for (Categoria c : categoryList) {
                        TerminalUtils.out(c.toString());
                        if (eToEdit.getId_categoria() == c.getId_categoria()) {
                            nombreCategoria = c.getNombre();
                        }
                    }

                    TerminalUtils
                            .out("Antigua categoria : " + nombreCategoria + " de Id : " + eToEdit.getId_categoria());
                    TerminalUtils.out("Introduce la nueva id de categoria : ");
                    int id_categoria = TerminalUtils.getInt();
                    eToEdit.setId_categoria(id_categoria);
                    break;
                case 5:
                    TerminalUtils.out("EDITANDO UBICACIÓN");
                    TerminalUtils.out("==================");

                    List<Ubicacion> ubiList = orgController.getLocations();
                    TerminalUtils.out("Lista de ubicaciones");
                    TerminalUtils.out(Ubicacion.getHeader());
                    String tipoUbi = "";
                    String direccionUbi = "";
                    for (Ubicacion u : ubiList) {
                        TerminalUtils.out(u.toString());
                        if (eToEdit.getId_ubicacion() == u.getId_ubicacion()) {
                            tipoUbi = u.getTipo();
                        }
                        if (!tipoUbi.equals("online")) {
                            direccionUbi = u.getDireccion();
                        }

                        if (u.getId_ubicacion() == eToEdit.getId_ubicacion()) {
                            uToEdit.setDireccion(u.getDireccion());
                            uToEdit.setId_ubicacion(eToEdit.getId_ubicacion());
                            uToEdit.setTipo(u.getTipo());
                        }
                    }

                    TerminalUtils.out("Antiguo tipo de ubicacion : " + tipoUbi);
                    if (tipoUbi.equals("presencial")) {
                        TerminalUtils.out("Dirección de la ubicacion : " + direccionUbi);
                    }

                    TerminalUtils.out("Quiere modificar el tipo de ubicacion (S/N)");
                    String opcionTipo = TerminalUtils.getString();
                    switch (opcionTipo.toUpperCase()) {
                        case "S":
                            if (tipoUbi.equals("presencial")) {
                                tipoUbi = "online";
                            } else {
                                tipoUbi = "presencial";
                                TerminalUtils.out("Introduce la dirección: ");
                                String newDireccion = TerminalUtils.getString();
                                uToEdit.setDireccion(newDireccion);
                            }
                            uToEdit.setTipo(tipoUbi);
                            eToEdit.setId_ubicacion(uToEdit.getId_ubicacion());
                            break;
                        case "N":
                            String newDireccion = "";
                            if (tipoUbi.equals("presencial")) {
                                TerminalUtils.out("Introduce la nueva dirección");
                                newDireccion = TerminalUtils.getString();
                            }
                            uToEdit.setDireccion(newDireccion);

                            break;
                        default:
                            TerminalUtils.out("Opción no válida. Por favor, ingrese 'S' o 'N'.");
                            break;
                    }
                    eToEdit.setId_ubicacion(uToEdit.getId_ubicacion());
                    break;

                default:
                    TerminalUtils.out("Error en la opción introducida");
                    break;
            }
            TerminalUtils.out("¿Quieres modificar otro evento? ");
            TerminalUtils.out("0. No");
            TerminalUtils.out("1. Si");
            opcion = TerminalUtils.getInt();
        } while (opcion != 0);
        String result2 = "";
        if (uToEdit.getId_ubicacion() != 0) {
            result2 = orgController.newUbi(uToEdit);
        }
        String result = orgController.editEvent(eToEdit);
        return result + result2;
    }

    private String showCancel(Organizador org) {
        List<Evento> showList = mainController.showEventList(0, org.getId_organizadores());
        TerminalUtils.out(Evento.getHeader());
        for (Evento e : showList) {
            TerminalUtils.out(e.toString());
        }
        TerminalUtils.out("\nIntroduce el id del evento que desea cancelar");
        int idEvento = TerminalUtils.getInt();

        String result = orgController.showCancel(idEvento);
        return result;
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
        for (Evento e : showList) {
            TerminalUtils.out(e.toString());
        }
        TerminalUtils.out("\n");
    }

    private void newEvent(Organizador org) {
        TerminalUtils.out("CREAR NUEVO EVENTO");
        TerminalUtils.out("==================");
        Evento e = new Evento();
        List<Categoria> categoryList = mainController.showCategoryList();
        TerminalUtils.out(Categoria.getHeaderForEvents());
        for (Categoria c : categoryList) {
            TerminalUtils.out(c.toString());
        }
        TerminalUtils.out("\nIndica primero a que categoría pertenece el evento a crear: ");
        int idCategoria = TerminalUtils.getInt();
        e.setId_categoria(idCategoria);

        List<Ubicacion> whereList = orgController.getLocations();
        TerminalUtils.out(Ubicacion.getHeader());
        for (Ubicacion u : whereList) {
            TerminalUtils.out(u.toString());
        }
        TerminalUtils.out("\nIndica en que ubicación será el evento");
        int idUbicacion = TerminalUtils.getInt();
        e.setId_ubicacion(idUbicacion);

        TerminalUtils.out("\nIndica el nombre del evento");
        String nombre = TerminalUtils.getString();
        e.setNombre(nombre);

        TerminalUtils.out("\nIndica la duración del evento (en horas)");
        int duracion = TerminalUtils.getInt();
        e.setDuracion(duracion);
        e.setId_organizador(org.getId_organizadores());

        TerminalUtils.out("\nIndica el estado en el que se encontrará el evento (activo/cancelado)");
        String estado = TerminalUtils.getString();
        e.setEstado(estado);

        TerminalUtils.out("\nIndica la fecha del evento (YYYY-MM-DD)");
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