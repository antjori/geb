package pt.devhub.antjori.cicd.oac.api;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.Base64Utils;

import pt.devhub.antjori.cicd.oac.OpenApiCollectorApplication;
import pt.devhub.antjori.cicd.oac.model.spotify.album.SpotifyAlbums;
import pt.devhub.antjori.cicd.oac.model.spotify.artist.SpotifyArtists;
import pt.devhub.antjori.cicd.oac.model.spotify.response.SpotifySearchResponse;
import pt.devhub.antjori.cicd.oac.model.spotify.track.SpotifyTracks;
import pt.devhub.antjori.cicd.oac.service.spotify.SpotifyService;

/**
 * Test class for {@link OpenApiCollectorControllerV1} where will be depicted
 * the integration tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = OpenApiCollectorApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class OpenApiCollectorControllerV1IT {

    /**
     * Common request mapping URL path.
     */
    private static final String REQUEST_MAPPING = "/oac/v1";

    @Value(value = "${security.user.name}")
    private String user;

    @Value(value = "${security.user.password}")
    private String password;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SpotifyService spotifyService;

    // =======
    // SPOTIFY
    // =======

    @Before
    public void setup() {

    }

    @Test
    public void testSearchSpotifyAlbums() throws Exception {
        // given
        MockHttpServletRequestBuilder get = get(REQUEST_MAPPING + "/api/spotify")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString((user + ":" + password).getBytes()))
                .param("q", "Eminem").param("type", "album");

        SpotifySearchResponse response = new SpotifySearchResponse();
        response.setAlbums(new SpotifyAlbums());

        when(spotifyService.search(anyString(), anyString())).thenReturn(response);

        // when
        ResultActions resultActions = mvc.perform(get).andDo(print());

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
        resultActions.andExpect(jsonPath("$").exists());
        resultActions.andExpect(jsonPath("$.albums").exists());
    }

    @Test
    public void testSearchSpotifyArtists() throws Exception {
        // given
        MockHttpServletRequestBuilder get = get(REQUEST_MAPPING + "/api/spotify")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString((user + ":" + password).getBytes()))
                .param("q", "Eminem").param("type", "artist");

        SpotifySearchResponse response = new SpotifySearchResponse();
        response.setArtists(new SpotifyArtists());

        when(spotifyService.search(anyString(), anyString())).thenReturn(response);

        // when
        ResultActions resultActions = mvc.perform(get).andDo(print());

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
        resultActions.andExpect(jsonPath("$").exists());
        resultActions.andExpect(jsonPath("$.artists").exists());
    }

    @Test
    public void testSearchSpotifyTracks() throws Exception {
        // given
        MockHttpServletRequestBuilder get = get(REQUEST_MAPPING + "/api/spotify")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString((user + ":" + password).getBytes()))
                .param("q", "Eminem").param("type", "track");

        SpotifySearchResponse response = new SpotifySearchResponse();
        response.setTracks(new SpotifyTracks());

        when(spotifyService.search(anyString(), anyString())).thenReturn(response);

        // when
        ResultActions resultActions = mvc.perform(get).andDo(print());

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
        resultActions.andExpect(jsonPath("$").exists());
        resultActions.andExpect(jsonPath("$.tracks").exists());
    }
}