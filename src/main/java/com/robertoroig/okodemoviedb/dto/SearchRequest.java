package com.robertoroig.okodemoviedb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchRequest {
  private String query;
  private Long page;
}
