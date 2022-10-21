package mvp.players.factory;

import mvp.players.BasketballPlayer;
import mvp.players.Player;

public class BasketballPlayerFactory implements PlayerFactory {
    @Override
    public Player createPlayer(String name,
                               String nickname,
                               Integer number,
                               String teamName,
                               Integer... points) {
        return new BasketballPlayer(name,nickname,number,teamName,points[0],points[1],points[2]);
    }
}