package mvp.players;

import mvp.readers.PropertyReader;
import mvp.sports.Sports;

public class HandballPlayer extends Player {
    private static final Integer GOAL_MADE_COEFFICIENT;
    private static final Integer GOAL_RECEIVED_COEFFICIENT;

    private Integer goalMade;
    private Integer goalReceived;

    static {
        String filePath = "src/main/resources/configuration/player_coefficients.properties";
        GOAL_MADE_COEFFICIENT = Integer.parseInt(PropertyReader.readProperties(filePath,
                "goal_made"));
        GOAL_RECEIVED_COEFFICIENT = Integer.parseInt(PropertyReader.readProperties(filePath,
                "goal_received"));
    }

    public HandballPlayer(String name,
                          String nickname,
                          Integer number,
                          String teamName,
                          Integer goalMade,
                          Integer goalReceived) {
        super(Sports.HANDBALL, name, nickname, number, teamName);
        this.goalMade = goalMade;
        this.goalReceived = goalReceived;
    }

    @Override
    public Integer countPlayerPoints() {
        return goalMade*GOAL_MADE_COEFFICIENT + goalReceived*GOAL_RECEIVED_COEFFICIENT;
    }

    public Integer getGoalMade() {
        return goalMade;
    }

    public void setGoalMade(Integer goalMade) {
        this.goalMade = goalMade;
    }

    public Integer getGoalReceived() {
        return goalReceived;
    }

    public void setGoalReceived(Integer goalReceived) {
        this.goalReceived = goalReceived;
    }

    @Override
    public String toString() {
        return "HandballPlayer{" +
                "sport=" + sport +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", number=" + number +
                ", teamName='" + teamName + '\'' +
                ", goalMade=" + goalMade +
                ", goalReceived=" + goalReceived +
                '}';
    }
}