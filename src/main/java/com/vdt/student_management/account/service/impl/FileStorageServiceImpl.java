package com.vdt.student_management.account.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.vdt.student_management.account.service.FileStorageService;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

  Cloudinary cloudinary;

  @Override
  public String uploadFile(MultipartFile file) {
    try {
      File uploadFile = File.createTempFile("temp",file.getOriginalFilename());
      file.transferTo(uploadFile);

      var uploadResult = cloudinary.uploader().upload(uploadFile, Map.of());
      return uploadResult.get("secure_url").toString();
    } catch (IOException e) {
      throw new AppException(ErrorCode.FILE_UPLOAD_FAIL);
    }

  }

  @Override
  public String deleteFile(String publicId) {
    try {
      var result = cloudinary.uploader().destroy(publicId, Map.of());
      return result.get("result").toString();
    } catch (IOException e) {
      throw new AppException(ErrorCode.FILE_DELETE_FAIL);
    }
  }

}
