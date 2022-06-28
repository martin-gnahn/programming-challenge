package de.exxcellent.challenge.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonPropertyOrder({
    "team", "games", "wins", "losses", "draws",
    "goals", "goalsAllowed", "points"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class FootballTeam {
    private String team;
    private int games, wins, losses, draws, goals;
    @JsonProperty("Goals Allowed")
    private int goalsAllowed;
    private int points;
}