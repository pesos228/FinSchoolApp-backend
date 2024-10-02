package com.finchool.server.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageService {

    String store(MultipartFile file);
    List<String> getAllFilenames() throws IOException;
    Resource loadAsResource(String filename);

}
