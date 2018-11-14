package pt.devhub.antjori.cicd.oac.model.spotify;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Mapping for Spotify's album item.
 */
@Data
@JsonInclude(value = Include.NON_NULL)
public class SpotifyAlbumItem {

    /**
     * The type of the album: one of 'album', 'single', or 'compilation'.
     */
    @JsonProperty(value = "album_type")
    private String albumType;

    /**
     * The markets in which the album is available: ISO 3166-1 alpha-2 country
     * codes. Note that an album is considered available in a market when at least 1
     * of its tracks is available in that market.
     */
    @JsonProperty(value = "available_markets")
    private List<String> availableMarkets;

    /**
     * Known external URLs for this album.
     */
    @JsonProperty(value = "external_urls")
    private List<ExternalUrl> externalUrls;

    /**
     * A link to the Web API endpoint providing full details of the album.
     */
    private String href;

    /**
     * The Spotify ID for the album.
     */
    private String id;

    /**
     * The cover art for the album in various sizes, widest first.
     */
    private List<Image> images;

    /**
     * The name of the album.
     */
    private String name;

    /**
     * The object type: 'album'.
     */
    private String type;

    /**
     * The Spotify URI for the album.
     */
    private String uri;

    /**
     * Mapping for Spotify's album item external URL.
     */
    @Data
    @JsonInclude(value = Include.NON_NULL)
    private static final class ExternalUrl {

        private String spotify;
    }

    /**
     * Mapping class for the image of Spotify's album item. The cover art for the
     * album in various sizes, widest first.
     */
    @Data
    @JsonInclude(value = Include.NON_NULL)
    private static final class Image {

        /**
         * The image height in pixels. If unknown: null or not returned.
         */
        private int height;

        /**
         * The source URL of the image.
         */
        private String url;

        /**
         * The image width in pixels. If unknown: null or not returned.
         */
        private int width;
    }

}