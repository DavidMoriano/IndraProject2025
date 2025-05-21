package views;

import controller.OrgController;
import entities.Organizador;
import utils.TerminalUtils;

public class OrgView {
    private OrgController orgController;
    private OrgView orgView;

    public OrgView() {
        this.orgController = new OrgController();
        this.orgView = new OrgView();
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
                    
                    break;
                case 2:

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
