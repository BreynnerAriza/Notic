package org.example.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum MainMenuOptions {

    START_NEW_TOURNAMENT(1, "Iniciar un nuevo torneo"),
    EXIT(2, "salir");

    private final int option;
    private final String description;

    public static MainMenuOptions fromOption(int option) {
        return Arrays.stream(MainMenuOptions.values())
                .filter(mainMenuOption -> mainMenuOption.option == option)
                .findFirst().orElse(null);
    }

    public static String getMainOptionsPrint(){
        return Arrays.stream(MainMenuOptions.values())
                .map(menuOption ->  menuOption.option + ". " + menuOption.description )
                .collect(Collectors.joining("\n"));
    }

}
