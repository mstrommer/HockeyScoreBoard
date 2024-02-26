package oop.classes;

import java.util.Collections;

public class HockeyScoreboard {
     
    public HockeyScoreboard(String teamA, String teamB, GameTime gameTime) {
   
    }

    public HockeyScoreboard(String teamA, String teamB) {
   
    }

    public String getScoreBoard() {
           
            }

    private String getPenaltiesString(int penalties) {
        String penaltiesString = String.join("", Collections.nCopies(penalties, "P"));
        if (penaltiesString.isEmpty()) {
            penaltiesString = " ";
        }
        return String.format("%1$-3s", penaltiesString).replace(' ', '*');
    }

    public void scoreGoalTeamA() {
           
    }

    public void scoreGoalTeamB() {
        this.teamBGoals++;
    }

    public void setPenaltyTeamA() {   
        
    }

    public void setPenaltyTeamB() {
           
    }

    public void clearPenaltyTeamA() {
   
    }

    public void clearPenaltyTeamB() {
           
    }

    public void start() {
           
    }

    public void pause() {
           
    }

    public void resume() {
           
    }


}
