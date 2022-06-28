package de.exxcellent.challenge;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void runWithNoArguments() {
        App.main();
        assertThat(errContent.toString()).isNotEmpty();
    }

    @Test
    void runWithNonExistingFile() {
        assertThatThrownBy(() -> {
            App.main("--weather", "nonExistingFile.csv");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void runFootballCSV() {
        App.main("--football", "football.csv");
        assertThat(
            String.format("Team with smallest goal spread: %s%n","Aston_Villa")
        ).isEqualTo(outContent.toString());
        assertThat(errContent.toString()).isEmpty();
    }

    @Test
    void runWeatherCSV() {
        App.main("--weather", "weather.csv");
        assertThat(
                String.format("Day with smallest temperature spread: %s%n","14")
        ).isEqualTo(outContent.toString());
        assertThat(errContent.toString()).isEmpty();
    }

    @Test
    void runFootballJSON() {
        App.main("--football", "football.json","--json");
        assertThat(
                String.format("Team with smallest goal spread: %s%n","Aston_Villa")
        ).isEqualTo(outContent.toString());
        assertThat(errContent.toString()).isEmpty();
    }

    @Test
    void runWeatherJSON() {
        App.main("--weather", "weather.json","--json");
        assertThat(
                String.format("Day with smallest temperature spread: %s%n","14")
        ).isEqualTo(outContent.toString());
        assertThat(errContent.toString()).isEmpty();
    }


}