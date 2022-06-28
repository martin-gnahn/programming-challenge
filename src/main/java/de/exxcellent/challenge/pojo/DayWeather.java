package de.exxcellent.challenge.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonPropertyOrder({
    "day", "maxTemp", "minTemp", "avgTemp"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class DayWeather {
    private int day;
    @JsonProperty("MxT")
    private int maxTemp;
    @JsonProperty("MnT")
    private int minTemp;
    @JsonProperty("AvT")
    private int avgTemp;
}
