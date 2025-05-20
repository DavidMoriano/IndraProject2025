package views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import controller.MainController;
import entities.Usuario;
import utils.TerminalUtils;

public class MainView {
    private Scanner sc;
    private MainController mainController;

    public MainView() throws ClassNotFoundException, SQLException, IOException {
        this.sc = new Scanner(System.in);
        this.mainController = new MainController();
    }

    public void start() {
        int option = -1;

        do {
            TerminalUtils.out("Projecto Indra");
            TerminalUtils.out("==============");
            TerminalUtils.out("0. Salir");
            TerminalUtils.out("1. Registrar");
            TerminalUtils.out("2. Login");
            TerminalUtils.out("3. Listar eventos por categorías");
            TerminalUtils.out("Introduce la opción a escoger: ");

            if (sc.hasNextInt()) {
                option = sc.nextInt();
                sc.nextLine();
            } else {
                sc.nextLine();
                TerminalUtils.out("Por favor, introduce un número válido.\n");
                continue;
            }

            switch (option) {
                case 0:
                    TerminalUtils.out("Ha salido del programa");
                    break;

                case 1:
                    register();
                    break;

                case 2:
                    login();
                    break;

                default:
                    TerminalUtils.out("Error en la opción introducida\n");
                    break;
            }

        } while (option != 0);

        sc.close();
    }

    private void login() {
        TerminalUtils.out("Login");
        TerminalUtils.out("=====\n");
        Usuario userLogin = new Usuario();

        System.out.print("Introduce el nombre de usuario: ");
        String loginName = sc.nextLine();
        userLogin.setNombre(loginName);

        System.out.print("Introduce la contraseña: ");
        String loginPassword = sc.nextLine();
        userLogin.setPassword(loginPassword);

        mainController.login(userLogin);
    }

    private void register() {
        TerminalUtils.out("Registrar nuevo usuario");
        TerminalUtils.out("=======================\n");
        Usuario user = new Usuario();

        System.out.print("Introduce el nombre de usuario: ");
        String nombreUser = sc.nextLine();
        user.setNombre(nombreUser);

        System.out.print("Introduce el email: ");
        String email = sc.nextLine();
        user.setEmail(email);

        System.out.print("Introduce la contraseña: ");
        String password = sc.nextLine();

        System.out.print("Introduce de nuevo la contraseña: ");
        String password1 = sc.nextLine();
        if (password.equals(password1)) {
            user.setPassword(password);
            mainController.userRegister(user);
        } else {
            TerminalUtils.out("Las contraseñas no coinciden.\n");
        }
    }
}
