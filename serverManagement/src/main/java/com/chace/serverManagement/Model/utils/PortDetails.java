package com.chace.serverManagement.Model.utils;

import com.chace.serverManagement.Model.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class PortDetails implements Serializable {

  /**
   * Universal version identifier for a Serializable class.
   * Deserialization uses this number to ensure that a loaded class
   * corresponds exactly to a serialized object.
   * If no match is found, then an InvalidClassException is thrown.
   */
  private final static long serialVersionUID = 1L;

  private String ipAddress;
  private String name;
  private String memory;
  private String type;
  private String imageUrl;
  private Status status;
}
