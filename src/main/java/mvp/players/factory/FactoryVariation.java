package mvp.players.factory;

import mvp.players.Player;
import mvp.sports.Sports;

public class FactoryVariation {
    private FactoryVariation(){}

    public static Player playerVariation(Sports sports, String[] playerInformation) {
        return switch (sports) {
            case HANDBALL -> sports.createPlayer(playerInformation[0],playerInformation[1],
                    Integer.parseInt(playerInformation[2]),playerInformation[3],
                    Integer.parseInt(playerInformation[4]),Integer.parseInt(playerInformation[5]));
            case BASKETBALL -> sports.createPlayer(playerInformation[0],playerInformation[1],
                    Integer.parseInt(playerInformation[2]),playerInformation[3],
                    Integer.parseInt(playerInformation[4]),Integer.parseInt(playerInformation[5]),
                    Integer.parseInt(playerInformation[6]));
        };
    }
}