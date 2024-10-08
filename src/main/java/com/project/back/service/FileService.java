package com.project.back.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    // 파일 업로드
    String upload(MultipartFile file);

    // 파일 가져오기
    Resource getFile(String fileName);

}
