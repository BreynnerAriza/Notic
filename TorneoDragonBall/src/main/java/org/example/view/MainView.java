package org.example.view;

import org.example.util.MainMenuOptions;

public class MainView {

    private static final String MENU_BORDER = "----------------------------------------------";
    private static final String MENU_TITLE = "|       BIENVENIDO AL TORNEO DEL PODER       |";

    private MainView() {
    }

    public static void showMenuTournament() {
        System.out.println(MENU_BORDER);
        System.out.println(MENU_TITLE);
        System.out.println(MENU_BORDER);
        System.out.println(MainMenuOptions.getMainOptionsPrint());
        System.out.println(MENU_BORDER);
    }

}
