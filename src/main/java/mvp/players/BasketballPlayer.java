package mvp.players;

import mvp.readers.PropertyReader;
import mvp.sports.Sports;

public class BasketballPlayer extends Player {
    private static final Integer SCORED_POINTS_COEFFICIENT;
    private static final Integer REBOUND_COEFFICIENT;
    private static final Integer ASSIST_COEFFICIENT;

    private Integer scoredPoints;
    private Integer rebounds;
    private Integer assists;

    static {
        String filePath = "src/main/resources/configuration/player_coefficients.properties";
        SCORED_POINTS_COEFFICIENT = Integer.parseInt(PropertyReader.readProperties(filePath,
                "scored_points"));
        REBOUND_COEFFICIENT = Integer.parseInt(PropertyReader.readProperties(filePath,
                "rebound"));
        ASSIST_COEFFICIENT = Integer.parseInt(PropertyReader.readProperties(filePath,
                "assist"));
    }

    public BasketballPlayer(String name,
                            String nickname,
                            Integer number,
                            String teamName,
                            Integer scoredPoints,
                            Integer rebounds,
                            Integer assists) {
        super(Sports.BASKETBALL, name, nickname, number, teamName);
        this.scoredPoints = scoredPoints;
        this.rebounds = rebounds;
        this.assists = assists;
    }

    @Override
    public Integer countPlayerPoints() {
        return scoredPoints*SCORED_POINTS_COEFFICIENT + rebounds*REBOUND_COEFFICIENT + assists*ASSIST_COEFFICIENT;
    }

    public Integer getScoredPoints() {
        return scoredPoints;
    }

    public void setScoredPoints(Integer scoredPoints) {
        this.scoredPoints = scoredPoints;
    }

    public Integer getRebounds() {
        return rebounds;
    }

    public void setRebounds(Integer rebounds) {
        this.rebounds = rebounds;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    @Override
    public String toString() {
        return "BasketballPlayer{" +
                "sport=" + sport +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\''+
                ", number=" + number +
                ", teamName='" + teamName + '\'' +
                ", scoredPoints=" + scoredPoints +
                ", rebounds=" + rebounds +
                ", assists=" + assists +
                '}';
    }
}