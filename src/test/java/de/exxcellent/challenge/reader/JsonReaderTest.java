package de.exxcellent.challenge.reader;

import de.exxcellent.challenge.pojo.DayWeather;
import de.exxcellent.challenge.pojo.FootballTeam;
import lombok.SneakyThrows;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonReaderTest {

    private final AbstractDataReader dataReader = new JsonReader();

    @SneakyThrows
    @Test
    void extractFootballTeamEntitiesFromString() {
        final String jsonString =
                "[" +
                    "    {\n" +
                    "      \"Team\": \"Team1\",\n" +
                    "      \"Games\": 0,\n" +
                    "      \"Wins\": 0,\n" +
                    "      \"Losses\": 0,\n" +
                    "      \"Draws\": 0,\n" +
                    "      \"Goals\": 100,\n" +
                    "      \"Goals Allowed\": 80,\n" +
                    "      \"Points\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"Team\": \"Team2\",\n" +
                    "      \"Games\": 0,\n" +
                    "      \"Wins\": 0,\n" +
                    "      \"Losses\": 0,\n" +
                    "      \"Draws\": 0,\n" +
                    "      \"Goals\": 120,\n" +
                    "      \"Goals Allowed\": 60,\n" +
                    "      \"Points\": 0\n" +
                    "    }" +
                "]";
        List<FootballTeam> footballTeamList = dataReader.generateEntitiesFromString(FootballTeam.class,jsonString);
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
        final String jsonString =
                "[" +
                    "    {\n" +
                    "      \"Day\": 1,\n" +
                    "      \"MxT\": 88,\n" +
                    "      \"MnT\": 59,\n" +
                    "      \"AvT\": 74,\n" +
                    "      \"AvDP\": 53.8,\n" +
                    "      \"1HrP TPcpn\": 0,\n" +
                    "      \"PDir\": 280,\n" +
                    "      \"AvSp\": 9.6,\n" +
                    "      \"Dir\": 270,\n" +
                    "      \"MxS\": 17,\n" +
                    "      \"SkyC\": 1.6,\n" +
                    "      \"MxR\": 93,\n" +
                    "      \"Mn\": 23,\n" +
                    "      \"R AvSLP\": 1004.5\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"Day\": 2,\n" +
                    "      \"MxT\": 79,\n" +
                    "      \"MnT\": 63,\n" +
                    "      \"AvT\": 71,\n" +
                    "      \"AvDP\": 46.5,\n" +
                    "      \"1HrP TPcpn\": 0,\n" +
                    "      \"PDir\": 330,\n" +
                    "      \"AvSp\": 8.7,\n" +
                    "      \"Dir\": 340,\n" +
                    "      \"MxS\": 23,\n" +
                    "      \"SkyC\": 3.3,\n" +
                    "      \"MxR\": 70,\n" +
                    "      \"Mn\": 28,\n" +
                    "      \"R AvSLP\": 1004.5\n" +
                    "    }" +
                "]";
        List<DayWeather> dayWeatherList = dataReader.generateEntitiesFromString(DayWeather.class,jsonString);
        assertThat(dayWeatherList.size()).isEqualTo(2);
        assertThat(dayWeatherList)
                .extracting(DayWeather::getDay,DayWeather::getMaxTemp,DayWeather::getMinTemp,DayWeather::getAvgTemp)
                .containsExactly(
                        Tuple.tuple(1,88,59,74),
                        Tuple.tuple(2,79,63,71)
                );
    }

}
