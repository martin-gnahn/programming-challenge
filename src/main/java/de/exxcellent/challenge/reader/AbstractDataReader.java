package de.exxcellent.challenge.reader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

public abstract class AbstractDataReader {

    /**
     * converts an input stream of a to a list with entity objects
     */
    protected abstract <T> List<T> readValue(Class<T> type, InputStream in) throws IOException;

    /**
     * reads a specific file and generates a list of entity objects
     * @param type is the entity class.
     * @param fileName is the name of the file which is read.
     */
    public <T> List<T> generateEntitiesFromFile(Class<T> type, String fileName) throws IOException {
        String filePath = Path.of("de","exxcellent","challenge",fileName).toString();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(filePath)) {
            return readValue(type, in);
        }
    }

    /**
     * reads a specific file and generates a list of entity objects
     * @param type is the entity class.
     * @param data is the string containing the data which is used.
     */
    public <T> List<T> generateEntitiesFromString(Class<T> type, String data) throws IOException {
        try (InputStream in = new ByteArrayInputStream(data.getBytes()) ) {
            return readValue(type, in);
        }
    }

}
