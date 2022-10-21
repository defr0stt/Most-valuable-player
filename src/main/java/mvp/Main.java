package mvp;

import mvp.facade.PointsCounter;
import mvp.facade.SportFacade;
import mvp.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // files to read
        String[] basketballFiles = {
                "src/main/resources/csv_files/basketball/game1_b.csv",
                "src/main/resources/csv_files/basketball/game2_b.csv",
                "src/main/resources/csv_files/basketball/game3_b.csv",
        };
        String[] handballFiles = {
                "src/main/resources/csv_files/handball/game1_h.csv",
                "src/main/resources/csv_files/handball/game2_h.csv",
                "src/main/resources/csv_files/handball/game3_h.csv",
        };

        // main actions
        SportFacade sportFacade = SportFacade.getInstance();
        sportInformation(sportFacade, basketballFiles);
        sportFacade.setPointsCounter(new PointsCounter());
        sportInformation(sportFacade, handballFiles);
    }

    public static void sportInformation(SportFacade sportFacade, String[] filesToRead){
        List<List<Player>> matches = new ArrayList<>();
        for(String sportFile : filesToRead)
            matches.add(sportFacade.viewResults(sportFile));
        for (List<Player> playerList : matches)
            sportFacade.calculateMatchResult(playerList);
        Map<Player, Integer> mvpPlayerMap = sportFacade.mvpPlayer();
        Player mvp = mvpPlayerMap.entrySet().iterator().next().getKey();
        System.out.println("MVP : " + mvp.getName() + " with nickname " + mvp.getNickname() +
                " has " + mvp.getNumber() + " number, plays in " + mvp.getTeamName() + " and scored " +
                mvpPlayerMap.entrySet().iterator().next().getValue() + " points");
        System.out.println();
    }
}
