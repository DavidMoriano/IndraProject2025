package views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import controller.MainController;
import entities.Categoria;
import entities.Evento;
import entities.Organizador;
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
                    Usuario userSignUp = registerUser();
                    if (userSignUp != null) {
                        TerminalUtils.out("Registro completado correctamente\n");
                        break;
                    } else {
                        TerminalUtils.out("Error al registrar usuario\n");
                        break;
                    }

                case 2:
                    Usuario userLogin = login();
                    if (userLogin != null) {
                        TerminalUtils.out("Bienvenido " + userLogin.getNombre() + "\n");
                        userView.listToDo(userLogin);
                    } else {
                        TerminalUtils.out("Error al realizar el login del usuario\n");
                    }
                    break;
                case 3:
                    showAllEvents();
                    break;
                case 4:
                    Organizador orgRegister = registerOrg();
                    if (orgRegister != null) {
                        TerminalUtils.out("Registro completado correctamente\n");
                    } else {
                        TerminalUtils.out("Error al registrar organizador\n");
                        
                    }
                    break;
                case 5:
                    Organizador orgLogin = loginOrg();
                    if (orgLogin != null) {
                        TerminalUtils.out("Bienvenido organizador " + orgLogin.getNombre() + "\n");
                    } else {
                        TerminalUtils.out("Error al realizar el login del organizador\n");
                    }
                    break;
                default:
                    TerminalUtils.out("Error en la opción introducida\n");
                    break;
            }

        } while (option != 0);
    }

    private Organizador registerOrg() {
        TerminalUtils.out("Registrar nuevo organizador");
        TerminalUtils.out("==========================\n");
        Organizador org = new Organizador();

        System.out.print("Introduce el nombre de organizador: ");
        String nombreOrg = TerminalUtils.getString();
        org.setNombre(nombreOrg);

        System.out.print("Introduce la informacion de contacto: ");
        String info = TerminalUtils.getString();

        System.out.print("Introduce de nuevo informacion de contacto: ");
        String info1 = TerminalUtils.getString();

        if (info.equals(info1)) {
            org.setInformacion_contacto(info);
        }
        Organizador orgSignUp = mainController.orgRegister(org);
        return orgSignUp;
    }

    private Organizador loginOrg() {
        TerminalUtils.out("Login");
        TerminalUtils.out("=====\n");
        Organizador orgLogin = new Organizador();

        System.out.println("Introduce el nombre de administrador: ");
        String loginName = TerminalUtils.getString();
        orgLogin.setNombre(loginName);

        System.out.println("Introduce una informacion de contacto: ");
        String infoContacto = TerminalUtils.getString();
        orgLogin.setInformacion_contacto(infoContacto);

        Organizador orgToUse = mainController.loginOrg(orgLogin);
        return orgToUse;
    }

    private int menu() {
        int option = -1;
        TerminalUtils.out("Projecto Indra");
        TerminalUtils.out("==============");
        TerminalUtils.out("0. Salir");
        TerminalUtils.out("1. Registrar usuario");
        TerminalUtils.out("2. Login usuario");
        TerminalUtils.out("3. Listar eventos por categorías");
        TerminalUtils.out("4. Registrar organizador");
        TerminalUtils.out("5. Login organizador");
        TerminalUtils.out("Introduce la opción a escoger: ");
        option = TerminalUtils.getInt();
        return option;
    }

    private Usuario login() {
        TerminalUtils.out("Login");
        TerminalUtils.out("=====\n");
        Usuario userLogin = new Usuario();

        System.out.print("Introduce el nombre de usuario: ");
        String loginName = TerminalUtils.getString();
        userLogin.setNombre(loginName);

        System.out.print("Introduce la contraseña: ");
        String loginPassword = TerminalUtils.getString();
        userLogin.setPassword(loginPassword);

        Usuario toUseLogin = mainController.login(userLogin);
        return toUseLogin;
    }

    private Usuario registerUser() {
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
        Usuario userSignUp = mainController.userRegister(user);
        return userSignUp;
    }

    private void showAllEvents() {
        List<Categoria> categoryList = mainController.showCategoryList();
        TerminalUtils.out("Lista de categorias");
        TerminalUtils.out(Categoria.getHeader());
        for (Categoria c : categoryList) {
            TerminalUtils.out(c.toString());
        }

        TerminalUtils.out("Elige la categoria");
        int idCategoria = TerminalUtils.getInt();
        List<Evento> showList = mainController.showEventList(idCategoria);

        TerminalUtils.out("Lista de eventos");
        TerminalUtils.out(Evento.getHeader());
        for (Evento e : showList) {
            TerminalUtils.out(e.toString());
        }
    }

}
