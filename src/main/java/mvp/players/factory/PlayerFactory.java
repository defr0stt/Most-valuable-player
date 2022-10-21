package mvp.players.factory;

import mvp.players.Player;

public interface PlayerFactory {
    Player createPlayer(String name, String nickname, Integer number, String teamName, Integer ... points);
}