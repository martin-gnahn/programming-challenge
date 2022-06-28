package de.exxcellent.challenge.reader;

import de.exxcellent.challenge.App;
import de.exxcellent.challenge.pojo.DayWeather;
import de.exxcellent.challenge.pojo.FootballTeam;
import lombok.SneakyThrows;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class CsvReaderTest {

    private final AbstractDataReader dataReader = new CsvReader();

    @SneakyThrows
    @Test
    void extractFootballTeamEntitiesFromString() {
        final String csvString =
            "Team,Games,Wins,Losses,Draws,Goals,Goals Allowed,Points\n" +
            "Team1,0,0,0,0,100,80,0\n" +
            "Team2,0,0,0,0,120,60,0";
        List<FootballTeam> footballTeamList = dataReader.generateEntitiesFromString(FootballTeam.class,csvString);
        assertThat(footballTeamList.size()).isEqualTo(2);
        assertThat(footballTeamList)
                .extracting(FootballTeam::getTeam,FootballTeam::getGoals,FootballTeam::getGoalsAllowed)
                .containsExactly(
                    Tuple.tuple("Team1",100,80),
                    Tuple.tuple("Team2",120,60)
                );
    }

    @SneakyThrows
    @Test
    void extractWeatherDayEntitiesFromString() {
        final String csvString =
                "Day,MxT,MnT,AvT,AvDP,1HrP TPcpn,PDir,AvSp,Dir,MxS,SkyC,MxR,Mn,R AvSLP\n" +
                "1,88,59,74,53.8,0,280,9.6,270,17,1.6,93,23,1004.5\n" +
                "2,79,63,71,46.5,0,330,8.7,340,23,3.3,70,28,1004.5";
        List<DayWeather> dayWeatherList = dataReader.generateEntitiesFromString(DayWeather.class,csvString);
        assertThat(dayWeatherList.size()).isEqualTo(2);
        assertThat(dayWeatherList)
                .extracting(DayWeather::getDay,DayWeather::getMaxTemp,DayWeather::getMinTemp,DayWeather::getAvgTemp)
                .containsExactly(
                    Tuple.tuple(1,88,59,74),
                    Tuple.tuple(2,79,63,71)
                );
    }


}