package oop.classes;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Lets monitor a hockey game !");
        String teamA = "Chicago Blackhawks";
        String teamB = "etroit Red Wings";
        HockeyScoreboard scoreboard = new HockeyScoreboard(teamA, "D" + teamB);
        System.out.println("The game starts");
        scoreboard.start();
        System.out.println(scoreboard.getScoreBoard());
        Thread.sleep(4000);
        System.out.println(String.format("Unfair tackling of %s", teamA));
        scoreboard.setPenaltyTeamA();
        System.out.println(scoreboard.getScoreBoard());
        Thread.sleep(7000);
        System.out.println(String.format("Goal of %s", teamB));
        scoreboard.scoreGoalTeamB();
        System.out.println(scoreboard.getScoreBoard());
        Thread.sleep(7000);
        scoreboard.clearPenaltyTeamA();
        System.out.println(scoreboard.getScoreBoard());
    }
}