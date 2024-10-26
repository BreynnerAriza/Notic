package org.example.view;

import org.example.domain.Fighter;
import org.example.util.TournamentMenuOptions;
import static org.example.util.TournamentViewConstants.*;

import java.util.List;

public class TournamentView {

    private TournamentView() {}

    public static void showFightersMenu(List<Fighter> fightersAvailable, List<Fighter> fightersSelected) {
        System.out.println(MENU_BORDER.getMessage());
        System.out.println(TOURNAMENT_MENU_TITLE.getMessage());
        System.out.print(getMenuFightersAvailable(fightersAvailable));
        System.out.print(getMenuFightersSelected(fightersSelected));
        System.out.println(MENU_BORDER.getMessage());
        System.out.println(TournamentMenuOptions.getTournamentOptionsPrint());
        System.out.println(MENU_BORDER.getMessage());
    }

    public static String getMenuFightersAvailable(List<Fighter> fightersAvailable) {
        return fightersAvailable.isEmpty() ?
                MENU_BORDER.getMessage() + "\n" + FIGHTER_AVAILABLE_EMPTY.getMessage() + "\n" :
                getMenuFighters(TITLE_FIGHTERS_AVAILABLE.getMessage(), fightersAvailable);
    }

    public static String getMenuFightersSelected(List<Fighter> fightersSelected) {
        return fightersSelected.isEmpty() ?
                MENU_BORDER.getMessage() + "\n" + FIGHTER_SELECTED_EMPTY.getMessage() + "\n" :
                getMenuFighters(TITLE_FIGHTERS_SELECTED.getMessage(), fightersSelected);
    }

    private static String getMenuFighters(String title, List<Fighter> fighters) {
        return MENU_BORDER.getMessage() + "\n" +
                title + "\n" +
                MENU_BORDER.getMessage() + "\n" +
                getStringFighters(fighters);
    }

    private static String getStringFighters(List<Fighter> fighters) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int indexFighter = 0; indexFighter < fighters.size(); indexFighter++) {
            stringBuilder.append(indexFighter).append(". ").append(fighters.get(indexFighter)).append("\n");
        }
        return stringBuilder.toString();
    }

    public static void showMenuAddFighter(List<Fighter> fightersAvailable) {
        final int OPTION_CANCEL = fightersAvailable.size();
        System.out.println(MENU_BORDER.getMessage());
        System.out.println(ADD_FIGHTER_MENU_TITLE.getMessage());
        System.out.println(MENU_BORDER.getMessage());
        System.out.print(getMenuFightersAvailable(fightersAvailable));
        System.out.println(OPTION_CANCEL + OPTION_CANCEL_MESSAGE.getMessage());
        System.out.println(MENU_BORDER.getMessage());
    }

    public static void showResumeConfrontation(List<Fighter> fighters) {
        System.out.println(MENU_BORDER.getMessage());
        System.out.println(CONFRONTATION_TITLE.getMessage());
        System.out.println(MENU_BORDER.getMessage());
        System.out.println(getStringNamesFighter(fighters));
    }

    public static String getStringNamesFighter(List<Fighter> fighters) {
        StringBuilder stringBuilder = new StringBuilder();
        fighters.forEach(fighter -> stringBuilder.append(fighter.getName().toUpperCase()).append(", "));
        return stringBuilder.toString();
    }

    public static void showWinnerConfrontation(Fighter fighter){
        System.out.println(
                String.format(MESSAGE_WIN_FIGHTER_CONFRONTATION.getMessage(), fighter.getName().toUpperCase())
        );
    }

    public static void showWinnerTournament(Fighter fighter){
        System.out.println(
                String.format(MESSAGE_WIN_TOURNAMENT.getMessage(), fighter.getName().toUpperCase())
        );
    }

    public static void showHeadConfrontation(Fighter fighterOne, Fighter fighterTwo){
        System.out.println(MENU_BORDER.getMessage());
        System.out.println(fighterOne.getName().toUpperCase() + " VS " + fighterTwo.getName().toUpperCase());
        System.out.println(MENU_BORDER.getMessage());
    }

}
