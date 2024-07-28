package com.chace.serverManagement.Model.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static com.chace.serverManagement.Model.utils.Globals.PAGE_SIZE;
import static com.chace.serverManagement.Model.utils.Globals.PAGE_INDEX;

public class Utils {
  public static Pageable pagingSorting() {
    return PageRequest.of(PAGE_INDEX, PAGE_SIZE, Sort.by("id").descending().and(Sort.by("creationDate").descending()).and(Sort.by("lastModifiedDate").descending()));
  }

  public static Pageable pagingSorting(Integer startIndex, Integer nbRows) {
    return PageRequest.of(Optional.ofNullable(startIndex).orElse(PAGE_INDEX),
                          Optional.ofNullable(nbRows).orElse(PAGE_SIZE),
                          Sort.by("id").descending().and(Sort.by("creation_date").descending()).and(Sort.by("last_modified_date").descending()));
  }
}
