package de.exxcellent.challenge.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.exxcellent.challenge.pojo.DayWeather;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonReader extends AbstractDataReader {

    final private ObjectMapper mapper = new ObjectMapper();

    /**
     *  converts an input stream of a json file to a list with entity objects
     */
    @Override
    protected <T> List<T> readValue(Class<T> type, InputStream in) throws IOException {
        List<T> entityList = Arrays.asList(
            (T[]) mapper.readValue(in, Array.newInstance(type, 0).getClass())
        );
        return entityList;
    }

}
