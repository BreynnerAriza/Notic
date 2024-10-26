package org.example.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum TournamentMenuOptions {

    START_TOURNAMENT(1, "Iniciar torneo"),
    ADD_FIGHTER(2, "Inscribir luchador"),
    CANCEL(3, "Cancelar");

    private final int option;
    private final String description;

    public static TournamentMenuOptions fromOption(int option) {
        return Arrays.stream(TournamentMenuOptions.values())
                .filter(tournamentMenuOptions -> tournamentMenuOptions.option == option)
                .findFirst().orElse(null);
    }

    public static String getTournamentOptionsPrint(){
        return Arrays.stream(TournamentMenuOptions.values()).map(
                mainTournamentOption -> mainTournamentOption.option + ". " + mainTournamentOption.description
        ).collect(Collectors.joining("\n"));
    }

}
