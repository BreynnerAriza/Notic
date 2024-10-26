package org.example.util;

public enum TournamentViewConstants {

    TITLE_FIGHTERS_AVAILABLE("LUCHADORES DISPONIBLES"),
    TITLE_FIGHTERS_SELECTED("LUCHADORES SELECCIONADOS"),
    MENU_BORDER("----------------------------------------------"),
    TOURNAMENT_MENU_TITLE("|       CONFIGURACION DEL TORNEO QUE PELEARÁ     |"),
    ADD_FIGHTER_MENU_TITLE("|   AGREGAR LUCHADOR PARTICIPANTE AL TORNEO  |"),
    FIGHTER_AVAILABLE_EMPTY("No hay mas luchadores diponibles"),
    FIGHTER_SELECTED_EMPTY("No se han seleccionado luchadores"),
    CONFRONTATION_TITLE("EN LA SIGUIENTE RONDA PARTICIPARAN"),
    OPTION_CANCEL_MESSAGE(". Cancelar"),
    MESSAGE_WIN_FIGHTER_CONFRONTATION("El ganador del enfrentamiento es: %s"),
    MESSAGE_WIN_TOURNAMENT("El ganador del torneo es: %s"),
    NO_FIGHTER_ADDED_MESSAGE("No se agregó ningún luchador"),
    FIGHTER_ADD_MESSAGE("Se agregó a %Ss con éxito al torneo"),
    NUMBER_FIGTHER_IS_NOT_VALID("El numero de luchadores seleccionados debe ser potencia de dos")
    ;

    private final String message;

    TournamentViewConstants(String value) {
        this.message = value;
    }

    public String getMessage() {
        return message;
    }

}
