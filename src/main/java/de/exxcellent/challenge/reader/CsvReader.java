package de.exxcellent.challenge.reader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CsvReader extends AbstractDataReader {

    final private CsvMapper mapper = new CsvMapper();

    /**
     * converts an input stream of a csv file to a list with entity objects
     */
    @Override
    protected  <T> List<T> readValue(Class<T> type, InputStream in) throws IOException {
        CsvSchema csvSchema = mapper
                .enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE)
                .typedSchemaFor(type)
                .withHeader()
                .withColumnSeparator(',')
                .withComments();
        MappingIterator<T> it = mapper
                .readerWithTypedSchemaFor(type)
                .with(csvSchema)
                .readValues(in);
        return it.readAll();
    }

}
