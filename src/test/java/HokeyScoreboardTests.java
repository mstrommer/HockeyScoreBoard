import oop.classes.HockeyScoreboard;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class HokeyScoreboardTests {

    @Test
    public void scoreGoalTeamA_teamAScoresAGoal_goalOfTeamAIsShownOnScoreboard() {
        String teamA = "Capitals";
        String teamB = "KAC";
        HockeyScoreboard instanceUnderTest = new HockeyScoreboard(teamA, teamB);
        String oneToZero = "1:0";

        instanceUnderTest.scoreGoalTeamA();

        String board = instanceUnderTest.getScoreBoard();
        assertThat(board).contains(oneToZero);
    }

    @Test
    public void scoreGoalTeamA_teamBScoresAGoal_goalOfTeamBIsShownOnScoreboard() {
        String teamA = "Capitals";
        String teamB = "KAC";
        HockeyScoreboard instanceUnderTest = new HockeyScoreboard(teamA, teamB);
        String zeroToOne = "0:1";

        instanceUnderTest.scoreGoalTeamB();

        String board = instanceUnderTest.getScoreBoard();
        assertThat(board).contains(zeroToOne);
    }

    @Test
    public void getScoreboard_gameNotStarted_displaysUnStartedGame() {
        String teamA = "Capitals";
        String teamB = "KAC";
        HockeyScoreboard instanceUnderTest = new HockeyScoreboard(teamA, teamB);
        String teams = String.format("%s : %s", teamA, teamB);
        String initialScore = "0:0";

        String board = instanceUnderTest.getScoreBoard();
        assertThat(board).contains(teams).contains(initialScore);
    }

    @Test
    public void setPenaltyTeamA_penaltyIsDisplayedForTeamA() {
        String teamA = "Capitals";
        String teamB = "KAC";
        String penalty = "P**:***";
        HockeyScoreboard instanceUnderTest = new HockeyScoreboard(teamA, teamB);

        instanceUnderTest.setPenaltyTeamA();

        String board = instanceUnderTest.getScoreBoard();
        assertThat(board).contains(penalty);
    }

    @Test
    public void setPenaltyTeamB_penaltyIsDisplayedForTeamB() {
        String teamA = "Capitals";
        String teamB = "KAC";
        String penalty = "***:P**";
        HockeyScoreboard instanceUnderTest = new HockeyScoreboard(teamA, teamB);

        instanceUnderTest.setPenaltyTeamB();

        String board = instanceUnderTest.getScoreBoard();
        assertThat(board).contains(penalty);
    }

    @Test
    public void getScoreBoard_setPenalties_displayedInScoreboard() {
        String teamA = "Capitals";
        String teamB = "KAC";
        String penalty = "PP*:P**";
        HockeyScoreboard instanceUnderTest = new HockeyScoreboard(teamA, teamB);

        instanceUnderTest.setPenaltyTeamB();
        instanceUnderTest.setPenaltyTeamA();
        instanceUnderTest.setPenaltyTeamA();

        String board = instanceUnderTest.getScoreBoard();
        assertThat(board).contains(penalty);
    }

    @Test
    public void clearPenalty_removeABeforeSetPenalty_displayedInScoreboard() {
        String teamA = "Capitals";
        String teamB = "KAC";
        String penalty = "P**:***";
        HockeyScoreboard instanceUnderTest = new HockeyScoreboard(teamA, teamB);

        instanceUnderTest.setPenaltyTeamB();
        instanceUnderTest.setPenaltyTeamA();
        instanceUnderTest.setPenaltyTeamA();
        instanceUnderTest.clearPenaltyTeamA();
        instanceUnderTest.clearPenaltyTeamB();

        String board = instanceUnderTest.getScoreBoard();
        assertThat(board).contains(penalty);
    }

    @Test
    public void clearPenalty_noPenaltiesSet_penaltyCounterRemainsUnchanged() {
        String teamA = "Capitals";
        String teamB = "KAC";
        String penalty = " : ";
        HockeyScoreboard instanceUnderTest = new HockeyScoreboard(teamA, teamB);

        instanceUnderTest.clearPenaltyTeamA();
        instanceUnderTest.clearPenaltyTeamB();

        String board = instanceUnderTest.getScoreBoard();
        assertThat(board).contains(penalty);
    }

    @Test
    public void setPenalty_alreadyThreePenaltiesSet_penaltyCounterRemainsUnchanged() {
        String teamA = "Capitals";
        String teamB = "KAC";
        String penalty = "PPP:PPP";
        String wrongPenalty = "PPPP:PPPP";
        HockeyScoreboard instanceUnderTest = new HockeyScoreboard(teamA, teamB);

        instanceUnderTest.setPenaltyTeamA();
        instanceUnderTest.setPenaltyTeamA();
        instanceUnderTest.setPenaltyTeamA();
        instanceUnderTest.setPenaltyTeamA();
        instanceUnderTest.setPenaltyTeamB();
        instanceUnderTest.setPenaltyTeamB();
        instanceUnderTest.setPenaltyTeamB();
        instanceUnderTest.setPenaltyTeamB();

        String board = instanceUnderTest.getScoreBoard();
        assertThat(board).contains(penalty).doesNotContain(wrongPenalty);
    }

    @Test
    public void getScoreBoard_startPauseResumeGame_displaysGameTimeInScoreboard() throws InterruptedException {
        String teamA = "Capitals";
        String teamB = "KAC";
        int elapsedTime = 3;
        String elapsedTimeString = String.format("00:%02d", elapsedTime);

        HockeyScoreboard instanceUnderTest = new HockeyScoreboard(teamA, teamB);

        instanceUnderTest.start();
        Thread.sleep(2000);
        instanceUnderTest.pause();
        instanceUnderTest.resume();
        Thread.sleep(1000);

        String board = instanceUnderTest.getScoreBoard();
        assertThat(board).contains(elapsedTimeString);
    }
}
