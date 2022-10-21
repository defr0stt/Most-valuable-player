package mvp.facade;

import mvp.players.Player;
import mvp.readers.CsvReader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class SportFacade {
    private static SportFacade sportFacade;
    private PointsCounter pointsCounter;

    {
        pointsCounter = new PointsCounter();
    }

    private SportFacade(){}

    public static SportFacade getInstance() {
        if(sportFacade == null)
            sportFacade = new SportFacade();
        return sportFacade;
    }

    public List<Player> viewResults(String filePath){
            List<Player> playerList = CsvReader.readCsv(filePath);
            // for file name
            Path path = Paths.get(filePath);
            System.out.println("\tFile " +  path.getFileName().toString() + " successfully downloaded");
            return playerList;
    }

    public void calculateMatchResult(List<Player> playerList){
        pointsCounter.matchResult(playerList);
    }

    public Map<Player, Integer> mvpPlayer(){
        return pointsCounter.mvpPlayer();
    }

    public PointsCounter getPointsCounter() {
        return pointsCounter;
    }

    public void setPointsCounter(PointsCounter pointsCounter) {
        this.pointsCounter = pointsCounter;
    }
}
