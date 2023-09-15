package com.sample.microservices.uploadfiles.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileInfo {
  private String name;
  private String url;
}
