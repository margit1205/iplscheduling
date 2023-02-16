package com.example.ipl2;

public class Team {
    public String teamName;
    public String homeGround;
    public Boolean isPlaying;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getHomeGround() {
        return homeGround;
    }

    public void setHomeGround(String homeGround) {
        this.homeGround = homeGround;
    }

    public Boolean getPlaying() {
        return isPlaying;
    }

    public void setPlaying(Boolean playing) {
        isPlaying = playing;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", homeGround='" + homeGround + '\'' +
                ", isPlaying=" + isPlaying +
                '}';
    }
}
