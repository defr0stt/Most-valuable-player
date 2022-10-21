package mvp.players.factory;

import mvp.players.HandballPlayer;
import mvp.players.Player;

public class HandballPlayerFactory implements PlayerFactory {
    @Override
    public Player createPlayer(String name,
                               String nickname,
                               Integer number,
                               String teamName,
                               Integer... points) {
        return new HandballPlayer(name,nickname,number,teamName,points[0],points[1]);
    }
}
