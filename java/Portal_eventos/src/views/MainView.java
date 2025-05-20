package views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import controller.MainController;
import entities.Evento;
import entities.Usuario;
import utils.TerminalUtils;

public class MainView {
    private MainController mainController;
    private UserView userView;

    public MainView() throws ClassNotFoundException, SQLException, IOException {
        this.mainController = new MainController();
        this.userView = new UserView();
    }

    public void start() {
        int option = -1;

        do {
            option = menu();

            switch (option) {
                case 0:
                    TerminalUtils.out("Ha salido del programa");
                    break;

                case 1:
                    String resultResgister = register();
                    TerminalUtils.out(resultResgister + "\n");
                    userView.listToDo();
                    break;

                case 2:
                    String resultLogin = login();
                    TerminalUtils.out(resultLogin + "\n");
                    break;
                case 3:
                    showAllEvents();
                    break;
                default:
                    TerminalUtils.out("Error en la opción introducida\n");
                    break;
            }

        } while (option != 0);
    }

    private int menu() {
        int option = -1;
            TerminalUtils.out("Projecto Indra");
            TerminalUtils.out("==============");
            TerminalUtils.out("0. Salir");
            TerminalUtils.out("1. Registrar");
            TerminalUtils.out("2. Login");
            TerminalUtils.out("3. Listar eventos por categorías");
            TerminalUtils.out("Introduce la opción a escoger: ");
            option = TerminalUtils.getInt();
            return option;
    }

    private String login() {
        TerminalUtils.out("Login");
        TerminalUtils.out("=====\n");
        Usuario userLogin = new Usuario();

        System.out.print("Introduce el nombre de usuario: ");
        String loginName = TerminalUtils.getString();
        userLogin.setNombre(loginName);

        System.out.print("Introduce la contraseña: ");
        String loginPassword = TerminalUtils.getString();
        userLogin.setPassword(loginPassword);

        return mainController.login(userLogin);
    }

    private String register() {
        TerminalUtils.out("Registrar nuevo usuario");
        TerminalUtils.out("=======================\n");
        Usuario user = new Usuario();

        System.out.print("Introduce el nombre de usuario: ");
        String nombreUser = TerminalUtils.getString();
        user.setNombre(nombreUser);

        System.out.print("Introduce el email: ");
        String email = TerminalUtils.getString();
        user.setEmail(email);

        System.out.print("Introduce la contraseña: ");
        String password = TerminalUtils.getString();

        System.out.print("Introduce de nuevo la contraseña: ");
        String password1 = TerminalUtils.getString();
        if (password.equals(password1)) {
            user.setPassword(password);
        } 
        return mainController.userRegister(user);
    }

    private void showAllEvents() {
        List<Evento> showList = mainController.showEventList();
        TerminalUtils.out("Lista de eventos");
        TerminalUtils.out(Evento.getHeader());
        for (Evento e : showList) {
            TerminalUtils.out(e.toString());
        }
    }

}


