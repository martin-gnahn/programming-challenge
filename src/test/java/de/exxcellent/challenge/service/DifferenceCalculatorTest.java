package de.exxcellent.challenge.service;

import de.exxcellent.challenge.App;
import de.exxcellent.challenge.pojo.DayWeather;
import de.exxcellent.challenge.pojo.FootballTeam;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferenceCalculatorTest {

    private final DifferenceCalculator differenceCalculator = new DifferenceCalculator();

    @BeforeEach
    void setUp() {

    }

    @Test
    void checkIfAnEmptyListReturnsNull() {
        List<DayWeather> emptyList = List.of();
        assertThat(
            differenceCalculator.findEntityWithSmallestDifference(
                    emptyList,DayWeather::getMinTemp,DayWeather::getMaxTemp
            )
        ).isEqualTo(null);
    }


    @Test
    void findDayWeatherWithSmallestDifference() {
        List<DayWeather> dayWeatherList = List.of(
            new DayWeather(1,100,20,60),
            new DayWeather(2,80,40,60),
            new DayWeather(3,60,60,60),
            new DayWeather(4,70,50,60),
            new DayWeather(5,70,60,65)
        );
        assertThat(
            differenceCalculator.findEntityWithSmallestDifference(
                    dayWeatherList,DayWeather::getMinTemp,DayWeather::getMaxTemp
            ).getDay()
        ).isEqualTo(3);
    }

    @Test
    void findFootballTeamWithSmallestDifference() {
        List<FootballTeam> footballTeamList = List.of(
            new FootballTeam("Team1",0,0,0,0,10,2,0),
            new FootballTeam("Team2",0,0,0,0,2,10,0),
            new FootballTeam("Team3",0,0,0,0,4,6,0),
            new FootballTeam("Team4",0,0,0,0,6,4,0),
            new FootballTeam("Team5",0,0,0,0,5,5,0)
        );
        assertThat(
            differenceCalculator.findEntityWithSmallestDifference(
                    footballTeamList,FootballTeam::getGoals,FootballTeam::getGoalsAllowed
            ).getTeam()
        ).isEqualTo("Team5");
    }

}
