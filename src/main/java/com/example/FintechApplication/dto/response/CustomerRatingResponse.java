
package com.example.FintechApplication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerRatingResponse {
  private String firstName;
  private String lastName;
  private String alias;
  private Integer rating;
}
