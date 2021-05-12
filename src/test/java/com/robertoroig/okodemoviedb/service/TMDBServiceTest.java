package com.robertoroig.okodemoviedb.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.uwetrottmann.tmdb2.entities.MovieResultsPage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TMDBServiceTest {
  @Autowired
  private TMDBService service;

  @Test
  void testMovieSearch() {
    MovieResultsPage movies = service.searchMovie("avatar", null, null).block();
    assertTrue(movies != null);
    assertTrue(movies.page == 1);
    assertTrue(movies.total_results == 39);
  }
}
