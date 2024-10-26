package org.example.logic;

import org.example.api.FighterInMemoryDAO;
import org.example.api.IFighterDAO;
import org.example.domain.Fighter;
import org.example.util.TournamentMenuOptions;
import org.example.commond.MessagesCommondConstans;
import org.example.util.TournamentViewConstants;
import org.example.view.TournamentView;

import java.util.*;

public class Tournament {

    private final IFighterDAO fighterDAO;
    private final Scanner scanner;
    private final Random random;

    public Tournament() {
        this.fighterDAO = new FighterInMemoryDAO();
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }

    public void launchTournamentManagerOptions() {
        int option;
        List<Fighter> fightersAvailable = fighterDAO.listAllFighter();
        List<Fighter> fightersSelected = new ArrayList<>();
        TournamentMenuOptions optionSelected = null;
        boolean printMenu = true;

        do {
            if (printMenu) {
                TournamentView.showFightersMenu(fightersAvailable, fightersSelected);
            }
            System.out.print(MessagesCommondConstans.CHOOSE_AN_OPTION.getMessage());

            try {
                option = scanner.nextInt();
                scanner.nextLine();

                optionSelected = TournamentMenuOptions.fromOption(option);

                if (optionSelected != null) {
                    handleOption(optionSelected, fightersAvailable, fightersSelected);
                    printMenu = true;
                } else {
                    System.out.println(MessagesCommondConstans.OPTION_INVALID.getMessage());
                    printMenu = false;
                }

            } catch (InputMismatchException e) {
                System.out.println(MessagesCommondConstans.OPTION_INVALID.getMessage());
                scanner.nextLine();
                printMenu = false;
            }

        } while (optionSelected != TournamentMenuOptions.CANCEL);
    }

    private void handleOption(TournamentMenuOptions optionSelected, List<Fighter> fightersAvailable, List<Fighter> fightersSelected) {
        switch (optionSelected) {
            case START_TOURNAMENT:
                startTournament(fightersSelected);
                resetFightersHealth(fightersSelected);
                break;
            case ADD_FIGHTER:
                addFighterParticipating(fightersAvailable, fightersSelected);
                break;
            case CANCEL:
                System.out.println(MessagesCommondConstans.GOOD_BYE.getMessage());
                break;
            default:
                System.out.println(MessagesCommondConstans.OPTION_INVALID.getMessage());
        }
    }

    private void addFighterParticipating(List<Fighter> fightersAvailable, List<Fighter> fightersSelected) {
        if (fightersAvailable.isEmpty()) {
            System.out.println(TournamentViewConstants.FIGHTER_AVAILABLE_EMPTY.getMessage());
            return;
        }
        int option = selectedFighter(fightersAvailable);
        if (option >= 0) {
            moveFighterAvailableToSelected(fightersAvailable, fightersSelected, option);
        }
    }

    private int selectedFighter(List<Fighter> fightersAvailable) {
        int option = -1;
        final int OPTION_CANCEL = fightersAvailable.size();
        final int MIN_OPTION = 0;
        final int MAX_OPTION = fightersAvailable.size() - 1;

        System.out.println();
        TournamentView.showMenuAddFighter(fightersAvailable);

        do {
            System.out.print(MessagesCommondConstans.CHOOSE_AN_OPTION.getMessage());
            try {
                option = scanner.nextInt();
                scanner.nextLine();
                if (option == OPTION_CANCEL) {
                    System.out.println(TournamentViewConstants.NO_FIGHTER_ADDED_MESSAGE.getMessage());
                } else if (option < MIN_OPTION || option > MAX_OPTION) {
                    System.out.println(MessagesCommondConstans.OPTION_INVALID.getMessage());
                }
            } catch (InputMismatchException e) {
                System.out.println(MessagesCommondConstans.OPTION_INVALID.getMessage());
                scanner.nextLine();
            }
        } while ((option < MIN_OPTION || option > MAX_OPTION) && option != OPTION_CANCEL);

        return option == OPTION_CANCEL ? -1 : option;
    }

    private void moveFighterAvailableToSelected(List<Fighter> fightersAvailable, List<Fighter> fightersSelected, int option) {
        Fighter fighterSelected = fightersAvailable.remove(option);
        fightersSelected.add(fighterSelected);
        System.out.println(String.format(TournamentViewConstants.ADD_FIGHTER_MENU_TITLE.getMessage(), fighterSelected.getName()));
        System.out.println();
    }

    public void startTournament(List<Fighter> fightersSelected) {
        if (!isFighterListSizePowerOfTwo(fightersSelected)) {
            System.out.println(TournamentViewConstants.NUMBER_FIGTHER_IS_NOT_VALID.getMessage());
            System.out.println();
            return;
        }

        List<Fighter> fightersWinner;
        List<Fighter> fighters = new ArrayList<>(fightersSelected);

        do {
            TournamentView.showResumeConfrontation(fighters);
            do {
                fightersWinner = runSingleRound(fighters);
            } while (!fighters.isEmpty());
            fighters = fightersWinner;
            resetFightersHealth(fighters);
        } while (fightersWinner.size() != 1);

        TournamentView.showWinnerTournament(fightersWinner.get(0));
        System.out.println();
    }

    private List<Fighter> runSingleRound(List<Fighter> fighters) {
        List<Fighter> winners = new ArrayList<>();
        Fighter fighterWinner;

        while (!fighters.isEmpty()) {
            Fighter fighterOne = fighters.remove(random.nextInt(fighters.size()));
            Fighter fighterTwo = fighters.remove(random.nextInt(fighters.size()));
            TournamentView.showHeadConfrontation(fighterOne, fighterTwo);
            fighterWinner = Fight.getWinner(fighterOne, fighterTwo);
            TournamentView.showWinnerConfrontation(fighterWinner);
            winners.add(fighterWinner);
        }
        return winners;
    }

    public void resetFightersHealth(List<Fighter> fighters) {
        fighters.forEach(Fighter::resetHealth);
    }

    public boolean isFighterListSizePowerOfTwo(List<Fighter> fightersSelected) {
        int fightersSelectedCant = fightersSelected.size();
        return fightersSelectedCant > 0 && (fightersSelectedCant & (fightersSelectedCant - 1)) == 0;
    }
}
