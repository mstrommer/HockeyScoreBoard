import oop.classes.GameTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.*;

public class GameTimeTests {

    @Test
    public void start_clockIsAtZero_gameIsStarted() throws InterruptedException {
        GameTime instanceUnderTest = new GameTime();

        instanceUnderTest.start();
        Thread.sleep(100);

        assertThat(instanceUnderTest.getTime()).isGreaterThan(0L);
    }

    @Test
    public void start_clockWasAlreadyRunning_clockIsStartedFromNew() throws InterruptedException {
        GameTime instanceUnderTest = new GameTime();

        instanceUnderTest.start();
        Thread.sleep(200);
        instanceUnderTest.pause();
        instanceUnderTest.start();
        Thread.sleep(100);

        assertThat(instanceUnderTest.getTime()).isBetween(100L, 200L);
    }

    @Test
    public void pause_callPauseAfterStart_clockPauses() throws InterruptedException {
        GameTime instanceUnderTest = new GameTime();

        instanceUnderTest.start();
        Thread.sleep(100);
        instanceUnderTest.pause();
        Thread.sleep(200);

        assertThat(instanceUnderTest.getTime()).isBetween(100L, 200L);
    }

    @Test
    public void resume_callResumeAfterPause_clockResumes() throws InterruptedException {
        GameTime instanceUnderTest = new GameTime();

        instanceUnderTest.start();
        Thread.sleep(100);
        instanceUnderTest.pause();
        instanceUnderTest.resume();
        Thread.sleep(100);

        assertThat(instanceUnderTest.getTime()).isGreaterThanOrEqualTo(200L);
    }

    @ParameterizedTest
    @MethodSource("getTimeSettingsForTest")
    public void toString_gameWasStarted_gameTimeIsDisplayedCorrectly(int elapsedSeconds, String expectedGameTime) {
        GameTime instanceUnderTest = new GameTime(elapsedSeconds);

        String gameTimeString = instanceUnderTest.toString();

        assertThat(gameTimeString).contains(expectedGameTime);
    }

    private static Stream<Arguments> getTimeSettingsForTest() {
        return Stream.of(
                Arguments.of(34, "0:34"),
                Arguments.of(211, "03:31"),
                Arguments.of(182, "03:02"),
                Arguments.of(1800, "30:00"),
                Arguments.of(18000, "00:00"));
    }
}
