package views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import controller.MainController;
import controller.OrgController;
import controller.UserController;
import entities.Evento;
import entities.Inscripcion;
import entities.Organizador;
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
                default:
                    break;
            }
        } while (option != 0);
    }

    private void newEvent(Organizador org) {
        TerminalUtils.out("CREAR NUEVO EVENTO");
        TerminalUtils.out("==================");
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
        TerminalUtils.out("Introduce la opción a escoger: ");
        option = TerminalUtils.getInt();
        return option;
    }
}
