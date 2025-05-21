package views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import controller.MainController;
import controller.UserController;
import entities.Categoria;
import entities.Evento;
import entities.Inscripcion;
import entities.Usuario;
import utils.TerminalUtils;

public class UserView {
    private UserController userController;
    private MainController mainController;

    public UserView() throws ClassNotFoundException, SQLException, IOException {
        this.userController = new UserController();
        this.mainController = new MainController();
    }

    public void listToDo(Usuario user) {
        int option = -1;
        do {
            option = menu();

            switch (option) {
                case 0:
                    TerminalUtils.out("Volviendo al inicio...");
                    break;
                case 1:
                    String enrollmentResult = completeEnrollment(user);
                    TerminalUtils.out(enrollmentResult);
                    break;
                case 2:
                    String enrollmentCancel = cancelEnrollment(user);
                    TerminalUtils.out(enrollmentCancel);
                    break;
                case 3:
                    listEnrollment(user);
                    break;
                case 4:
                    showProfile(user);
                    break;
                default:
                    break;
            }
        } while (option != 0);
    }

    private void showProfile(Usuario user) {
        TerminalUtils.out("PERFIL PERSONAL");
        TerminalUtils.out("===============");
        TerminalUtils.out("Nombre de usuario: " + user.getNombre());
        TerminalUtils.out("Correo electrónico: " + user.getEmail());
    }

    private void listEnrollment(Usuario user) {
        List<Inscripcion> listInscripcions = userController.getAllInscription(user.getId_usuario());
        List<Evento> showList = mainController.showEventList(0, null);
        if (listInscripcions.isEmpty()) {
            TerminalUtils.out("Lista de inscripciones vacía.");
        } else {
            TerminalUtils.out("Lista de inscripciones para el usuario " + user.getNombre());
            TerminalUtils.out(Inscripcion.getHeader());
            for (Inscripcion i : listInscripcions) {
                for (Evento e : showList) {
                    if (i.getId_evento() == e.getId_evento())
                        TerminalUtils.out(i.toString() + e.getNombre());
                }
            }

        }
    }

    private String cancelEnrollment(Usuario user) {
        List<Inscripcion> listInscripcions = userController.getAllInscription(user.getId_usuario());
        List<Evento> showList = mainController.showEventList(0, null);
        TerminalUtils.out("Indica la inscripcion que desea borrar.");
        TerminalUtils.out(Inscripcion.getHeader());
        for (Inscripcion i : listInscripcions) {
            for (Evento e : showList) {
                if (i.getId_evento() == e.getId_evento())
                    TerminalUtils.out(i.toString() + e.getNombre());
            }
        }
        int idInsc = TerminalUtils.getInt();
        String result = userController.cancelEnrollment(idInsc);
        return result;
    }

    private int menu() {
        int option = -1;
        TerminalUtils.out("VISTA DE USUARIO");
        TerminalUtils.out("================");
        TerminalUtils.out("0. Salir.");
        TerminalUtils.out("1. Apuntarse a eventos.");
        TerminalUtils.out("2. Cancelar la inscripción de un evento.");
        TerminalUtils.out("3. Listar todos los eventos a los que estés inscrito.");
        TerminalUtils.out("4. Mostrar datos personales.");
        TerminalUtils.out("Introduce la opción a escoger: ");
        option = TerminalUtils.getInt();
        return option;
    }

    private String completeEnrollment(Usuario user) {
        List<Categoria> categoryList = mainController.showCategoryList();
        TerminalUtils.out("Lista de categorias");
        TerminalUtils.out(Categoria.getHeader());
        for (Categoria c : categoryList) {
            TerminalUtils.out(c.toString());
        }

        TerminalUtils.out("Elige la categoria");
        int idCategoria = TerminalUtils.getInt();
        List<Evento> showList = mainController.showEventList(idCategoria, null);

        TerminalUtils.out("Lista de eventos");
        TerminalUtils.out(Evento.getHeader());
        String fechaEvento = "";
        for (Evento e : showList) {
            TerminalUtils.out(e.toString());
        }

        TerminalUtils.out("Elige el evento al que quieras apuntarte.");
        int idEvento = TerminalUtils.getInt();

        for (Evento e : showList) {
            if (idEvento == e.getId_evento()) {
                fechaEvento = e.getFecha();
            }
        }
        String resultEnrollment = userController.enrollment(idEvento, user.getId_usuario(), fechaEvento);
        return resultEnrollment;
    }
}
