package com.ashish.airbnbclone.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageResponse<T> {
  private List<T> list;
  private int page;
  private int size;
  private long totalElements;
  private int totalPages;
  private boolean hasNext;
}
