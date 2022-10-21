package mvp.facade;

import mvp.players.Player;

import java.util.*;

public class PointsCounter {
    private Map<Player,Integer> playerGlobalMap;

    {
        playerGlobalMap = new HashMap<>();
    }

    public void matchResult(List<Player> playerList){
        Map<Player,Integer> matchStats = new HashMap<>();
        Map<String, Integer> teams = new HashMap<>();
        // add new players and teams
        for (Player player : playerList) {
            matchStats.put(player, 0);
            matchStats.put(player,player.countPlayerPoints());
            if(!teams.containsKey(player.getTeamName()))
                teams.put(player.getTeamName(),0);
        }
        // calculate match stats
        for(Map.Entry<String, Integer> teamName : teams.entrySet())
            for (Map.Entry<Player, Integer> playersScore : matchStats.entrySet())
                if (teamName.getKey().equals(playersScore.getKey().getTeamName()))
                    teams.put(teamName.getKey(), teams.get(teamName.getKey()) + matchStats.get(playersScore.getKey()));
        // max result
        List<String> teamList = new ArrayList<>(teams.keySet());
        String winner = "";
        if(teams.get(teamList.get(0)) > teams.get(teamList.get(1)))
            winner = teamList.get(0);
        else
            winner = teamList.get(1);
        if(!winner.isEmpty())
            for(Map.Entry<Player, Integer> playersScore : matchStats.entrySet())
                if(winner.equals(playersScore.getKey().getTeamName()))
                    matchStats.put(playersScore.getKey(),playersScore.getValue() + 10);
        // clone match stats
        for(Map.Entry<Player, Integer> playersScore : matchStats.entrySet()) {
            if (!playerGlobalMap.containsKey(playersScore.getKey())) {
                try {
                    if (isNicknameUnique(playersScore.getKey()))
                        playerGlobalMap.put(playersScore.getKey(), playersScore.getValue());
                } catch (Exception e) {
                    System.out.println("\tThere are 2 more players with the same nickname : " + playersScore.getKey().getNickname()
                            + "\n\tMVP won’t be calculated");
                    System.exit(0);
                }
            }
            else
                playerGlobalMap.put(playersScore.getKey(),playerGlobalMap.get(playersScore.getKey()) + playersScore.getValue());
        }
    }

    public boolean isNicknameUnique(Player player) throws Exception {
        for(Map.Entry<Player, Integer> playersScore : playerGlobalMap.entrySet())
            if(!playersScore.equals(player) && playersScore.getKey().getNickname().equals(player.getNickname()))
                throw new Exception();
        return true;
    }

    public Map<Player,Integer> mvpPlayer(){
        int maxPoints = 0;
        Player tempPlayer = null;
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("    Sport   |     Name     |    Nickname   |   Number    |   Team name   |  Scored points   |");
        System.out.println("--------------------------------------------------------------------------------------------");
        for(Map.Entry<Player, Integer> playersScore : playerGlobalMap.entrySet()) {
            System.out.printf("%-12s| %-13s| %-14s| %-12d| %-14s| %-17d|\n",
                    playersScore.getKey().getSports(),playersScore.getKey().getName(),playersScore.getKey().getNickname(),
                    playersScore.getKey().getNumber(),playersScore.getKey().getTeamName(),playersScore.getValue());
            if(playersScore.getValue() > maxPoints) {
                maxPoints = playersScore.getValue();
                tempPlayer = playersScore.getKey();
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println();
        return Map.of(tempPlayer,maxPoints);
    }
}
