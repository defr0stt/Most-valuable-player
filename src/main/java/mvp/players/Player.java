package mvp.players;

import mvp.sports.Sports;

import java.util.Objects;

public abstract class Player {
    protected Sports sport;
    protected String name;
    protected String nickname;
    protected Integer number;
    protected String teamName;

    protected Player(Sports sport, String name, String nickname, Integer number, String teamName) {
        this.sport = sport;
        this.name = name;
        this.nickname = nickname;
        this.number = number;
        this.teamName = teamName;
    }

    public abstract Integer countPlayerPoints();

    public Sports getSports() {
        return sport;
    }

    public void setSports(Sports sports) {
        this.sport = sports;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return sport == player.sport && name.equals(player.name) && nickname.equals(player.nickname) && number.equals(player.number) && teamName.equals(player.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sport, name, nickname, number, teamName);
    }
}