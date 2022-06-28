package de.exxcellent.challenge;

import de.exxcellent.challenge.pojo.DayWeather;
import de.exxcellent.challenge.pojo.FootballTeam;
import de.exxcellent.challenge.reader.AbstractDataReader;
import de.exxcellent.challenge.reader.CsvReader;
import de.exxcellent.challenge.reader.JsonReader;
import de.exxcellent.challenge.service.DifferenceCalculator;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Locale;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    public static boolean validateArguments(String... args) {
        if( args.length != 2 && args.length != 3 ){
            System.err.println(
                    "2 or 3 arguments has to be passed:\n" +
                            "mvn exec:java <fileType> <fileName> [<dataType>]\n" +
                            "\t<fileType>: --football or --weather\n" +
                            "\t<fileName>: name of the file to be processed inside \"resources/exxcellent/challenge\" (i.e. \"weather.csv\")" +
                            "\t<dataType>: --json or --csv (default: --csv)"
            );
            return false;
        }
        return true;
    }

    /**
     * Print the entity with the smallest spread.
     */
    @SneakyThrows
    public static <T> void getEntityWithSmallestSpread(AbstractDataReader dataReader, Class<T> entityType, String fileName) {
        final DifferenceCalculator differenceCalculator = new DifferenceCalculator();
        if( entityType.equals(DayWeather.class) ){
            List<DayWeather> dayWeatherList = dataReader.generateEntitiesFromFile(DayWeather.class, fileName);
            final DayWeather dayWeatherEntityWithSmallestTempSpread =
                    differenceCalculator.findEntityWithSmallestDifference(dayWeatherList, DayWeather::getMinTemp, DayWeather::getMaxTemp);
            String dayWithSmallestTempSpread = String.valueOf(dayWeatherEntityWithSmallestTempSpread.getDay());
            System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);
        }else if( entityType.equals(FootballTeam.class) ){
            List<FootballTeam> footballTeamList = dataReader.generateEntitiesFromFile(FootballTeam.class, fileName);
            final FootballTeam footballTeamEntityWithSmallestGoalSpread =
                    differenceCalculator.findEntityWithSmallestDifference(footballTeamList, FootballTeam::getGoals, FootballTeam::getGoalsAllowed);
            String teamWithSmallestGoalSpread = footballTeamEntityWithSmallestGoalSpread.getTeam();
            System.out.printf("Team with smallest goal spread: %s%n", teamWithSmallestGoalSpread);
        }
    }

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    @SneakyThrows
    public static void main(String... args) {
        if( !validateArguments(args) ){
            return;
        }
        final AbstractDataReader dataReader;
        if( args.length == 3 && args[2].toLowerCase().contains("--json") ){
            dataReader = new JsonReader();
        }else{
            dataReader = new CsvReader();
        }
        String fileType = args[0];
        String fileName = args[1];
        switch( fileType.toLowerCase() ){
            case "--football":
                getEntityWithSmallestSpread(dataReader,FootballTeam.class,fileName);
                break;
            case "--weather":
                getEntityWithSmallestSpread(dataReader,DayWeather.class,fileName);
        }
    }
}
