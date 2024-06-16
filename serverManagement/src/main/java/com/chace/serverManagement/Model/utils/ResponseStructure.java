package com.chace.serverManagement.Model.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/* This class will be used as the structure of all the responses sent back to the frontEnd */
@Data // combines @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor annots in the class
@SuperBuilder // generates code required to be able to instantiable "Obj" this way : Obj.builder().gender("M").build();
@JsonInclude(NON_NULL)  // for The output Obj to include only the properties that are not null **
public class ResponseStructure {

  protected LocalDateTime timeStamp;
  protected int statusCode;   /* will hold the actual number of the status : 200, 404, 500, ... */
  protected HttpStatus status;
  protected String message;
  protected Map<?, ?> data;

  /* these fields will only be populated if the response in an error */
  protected String developerMessage; // ** this can be null if the response is a success
  protected String reason;    // ** this can be null if the response is a success


  /* ----- ADD PAGINATION TO SERVER MANAGEMENT ----------------- */
  /* in case of pagination*/
  protected Map<?, ?> items;
  private Integer totalNumberOfItems;
//  private Pagination pagination;

}
