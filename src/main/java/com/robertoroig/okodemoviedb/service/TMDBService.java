package com.robertoroig.okodemoviedb.service;

import java.util.Collections;

import javax.annotation.PostConstruct;

import com.uwetrottmann.tmdb2.entities.BaseResultsPage;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class TMDBService {
  private final String SEARCH_ENDPOINT = "/search/movie";

  private WebClient webClient;

  @Value("${tmdb.api.key}")
  private String apiKey;

  @Value("${tmdb.api.baseUrl}")
  private String baseUrl;

  public Mono<MovieResultsPage> searchMovie(String query, @Nullable String language, @Nullable Long page) {
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
    queryParams.add("query", query);
    queryParams.add("language", (language != null) ? language : "en-US");
    queryParams.add("page", (page != null) ? Long.toString(page) : "1");
    return makeGet(SEARCH_ENDPOINT, queryParams, MovieResultsPage.class);
  }

  private <T> Mono<T> makeGet(String endpoint, MultiValueMap<String, String> queryParams, Class<T> responseType) {
    return webClient.method(HttpMethod.GET)
        .uri(builder -> builder.path(endpoint).queryParam("api_key", apiKey).queryParams(queryParams).build())
        .retrieve().bodyToMono(responseType);
  }

  @PostConstruct
  private void initWebClient() {
    this.webClient = WebClient.builder().baseUrl(baseUrl).defaultHeaders(httpHeaders -> {
      httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }).defaultUriVariables(Collections.singletonMap("url", baseUrl)).build();
  }
}
