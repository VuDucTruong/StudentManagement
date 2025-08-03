package com.vdt.student_management.account.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

  String uploadFile(MultipartFile file);

  String deleteFile(String publicId);

}
