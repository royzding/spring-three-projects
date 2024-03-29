package com.sample.microservices.uploadfiles.service;

import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFilesService {

  public void save(String fileType, MultipartFile file);

  public Resource load(String filename);

  public void deleteAll();

  public Stream<Path> loadAll();
  
  Set<PosixFilePermission> getFilePermissions();
  
}
