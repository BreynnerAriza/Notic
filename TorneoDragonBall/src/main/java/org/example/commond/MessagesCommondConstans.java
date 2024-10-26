package org.example.commond;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MessagesCommondConstans {

    OPTION_INVALID("Opcion invalida"),
    CHOOSE_AN_OPTION("Eliga una opcion: "),
    GOOD_BYE("Hasta luego!!")
    ;

    private final String message;

}
