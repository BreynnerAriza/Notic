package org.example;

import org.example.logic.Tournament;
import org.example.util.MainMenuOptions;
import org.example.commond.MessagesCommondConstans;
import org.example.view.MainView;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.util.MainMenuOptions.EXIT;

public class Main {

    public static void main(String[] args) {
        launchTournament();
    }

    public static void launchTournament() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        MainMenuOptions optionSelected = null;
        MainView.showMenuTournament();
        do {
            try {
                System.out.print(MessagesCommondConstans.CHOOSE_AN_OPTION.getMessage());
                option = scanner.nextInt();
                scanner.nextLine();
                optionSelected = MainMenuOptions.fromOption(option);

                if(optionSelected != null) {
                    handleOption(optionSelected);
                }else{
                    System.out.println(MessagesCommondConstans.OPTION_INVALID.getMessage());
                }

            } catch (InputMismatchException e) {
                System.out.println(MessagesCommondConstans.OPTION_INVALID.getMessage());
                scanner.nextLine();
            }

        } while (optionSelected != EXIT);
        scanner.close();
    }

    public static void handleOption(MainMenuOptions optionSelected){
        switch (optionSelected) {
            case START_NEW_TOURNAMENT:
                System.out.println();
                new Tournament().launchTournamentManagerOptions();
                MainView.showMenuTournament();
                break;
            case EXIT:
                System.out.println(MessagesCommondConstans.GOOD_BYE.getMessage());
                break;
        }
    }

}
