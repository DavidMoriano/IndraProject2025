package views;

import java.util.Scanner;
import controller.MainController;

public class MainView {
    private Scanner sc;
    private MainController mainController;

    public MainView() {
        this.sc = new Scanner(System.in);
        this.mainController = new MainController();
    }

    public void start() {
        int option = -1;

        do {
            System.out.println("Projecto Indra");
            System.out.println("==============");
            System.out.println("0. Salir");
            System.out.println("1. Registrar");
            System.out.println("2. Login");
            System.out.print("Introduce la opción a escoger: ");

            if (sc.hasNextInt()) {
                option = sc.nextInt();
                sc.nextLine();
            } else {
                sc.nextLine();
                System.out.println("Por favor, introduce un número válido.\n");
                continue;
            }

            switch (option) {
                case 0:
                    System.out.println("Ha salido del programa");
                    break;

                case 1:
                    System.out.println("¿Quiere registrar usuario (1) o Organizador (2)?");
                    int i = sc.nextInt();

                    if(i == 1)  {
                        
                    }if (i == 2) {

                    } else {
                        System.out.println("Ha ocurrido un error al registrar");
                    }

                    System.out.println("Registrar nuevo usuario");
                    System.out.println("=======================\n");

                    System.out.print("Introduce el nombre de usuario: ");
                    String nombreUser = sc.nextLine();

                    System.out.print("Introduce la contraseña: ");
                    String password = sc.nextLine();

                    System.out.print("Introduce de nuevo la contraseña: ");
                    String password1 = sc.nextLine();

                    if (password.equals(password1)) {
                        mainController.register(option);
                    } else {
                        System.out.println("Las contraseñas no coinciden.\n");
                    }
                    break;

                case 2:
                    System.out.println("Login");
                    System.out.println("=====\n");

                    System.out.print("Introduce el nombre de usuario: ");
                    String loginName = sc.nextLine();

                    System.out.print("Introduce la contraseña: ");
                    String loginPassword = sc.nextLine();

                    mainController.login();
                    break;

                default:
                    System.out.println("Error en la opción introducida\n");
                    break;
            }

        } while (option != 0 || option != 2);

        sc.close();
    }
}
