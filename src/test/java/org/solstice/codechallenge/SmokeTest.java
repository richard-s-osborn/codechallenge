package org.solstice.codechallenge;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class SmokeTest {

    @Ignore
    @Test
    public void smokeTest() {
        RestTemplate restTemplate = new RestTemplate();

        String homePage = restTemplate.getForObject(url("/"), String.class);

        assertThat(homePage, containsString("Please select one of the following links:"));

        String setupPage = restTemplate.getForObject(url("/setup"), String.class);

        assertThat(setupPage, containsString("Bob"));

        String contactPage = restTemplate.getForObject(url("/codingchallenge"), String.class);

        assertThat(contactPage, containsString("Bob"));
    }

    private String url(String path) {
        String baseUrl = "http://localhost:8080";
        String envUrl = System.getenv("CODING_CHALLENGE_URL");

        if (envUrl != null && !envUrl.isEmpty()) {
            baseUrl = envUrl;
        }

        return baseUrl + path;
    }
}
