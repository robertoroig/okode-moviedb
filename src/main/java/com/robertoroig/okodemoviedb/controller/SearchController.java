package com.robertoroig.okodemoviedb.controller;

import com.robertoroig.okodemoviedb.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
  @Autowired
  private SearchService searchService;

  // public SearchResponse search(@RequestBody SearchRequest request) {

  // }
}
