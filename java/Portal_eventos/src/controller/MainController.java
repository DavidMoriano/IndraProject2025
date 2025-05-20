package controller;

public class MainController {

    public void login() {
        System.out.println("Bienvenido usuario\n");

        System.out.println("¿Qué quiere hacer?");

        
    }

    public void register(int option) {
        System.out.println("Registrado correctamente\n");
        if (option == 1) {
            login();
        } else {
            System.out.println("Error al registrar");
        }
    }
    
}
