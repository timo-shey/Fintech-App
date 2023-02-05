package com.example.FintechApplication.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor

public class PageResponse<T> {
  private Integer pageNumber;
  private Integer resultsPerPage;
  private Integer totalResults;
  private Double totalPages;
  private List<T> items;

  public PageResponse(Integer pageNumber, Integer resultsPerPage, Integer totalResults, List<T> items){
    this.pageNumber = pageNumber;
    this.resultsPerPage = resultsPerPage;
    this.totalResults = totalResults;
    this.items = items;
    this.totalPages = calTotalPages();
  }

  private Double calTotalPages() {
    return Math.ceil(Double.valueOf(totalResults) / (Double.valueOf(resultsPerPage)));
  }
  //TO-DO: Methode for calculatin the total number of pages

}
