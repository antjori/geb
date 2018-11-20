package pt.devhub.antjori.cicd.oac.model.spotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.ToString;

/**
 * Mapping for Spotify's track item.
 */
@Data
@ToString
@JsonInclude(value = Include.NON_NULL)
public class SpotifyTrack {

}
