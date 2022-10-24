package mvp.sports;

import mvp.players.BasketballPlayer;
import mvp.players.HandballPlayer;
import mvp.players.Player;
import mvp.players.factory.PlayerFactory;

public enum Sports implements PlayerFactory {
    BASKETBALL {
        @Override
        public Player createPlayer(String name, String nickname, Integer number, String teamName, Integer... points) {
            return new BasketballPlayer(name,nickname,number,teamName,points[0],points[1],points[2]);
        }
    },
    HANDBALL {
        @Override
        public Player createPlayer(String name, String nickname, Integer number, String teamName, Integer... points) {
            return new HandballPlayer(name,nickname,number,teamName,points[0],points[1]);
        }
    }
}