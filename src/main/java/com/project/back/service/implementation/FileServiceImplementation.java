package com.project.back.service.implementation;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.back.service.FileService;

@Service
public class FileServiceImplementation implements FileService {
    
    @Value("${file.url}")
    private String fileUrl;
    
    @Value("${file.path}")
    private String filePath;

    @Override
    public String upload(MultipartFile file) {
        if (file.isEmpty()) {
            return null; // 파일이 비어있는 경우
        }

        // 파일 저장 경로 확인 및 디렉토리 생성
        File directory = new File(filePath);
        if (!directory.exists()) {
            boolean isCreated = directory.mkdirs(); // 경로가 없으면 생성
            if (!isCreated) {
                return null; // 경로 생성 실패 시 null 반환
            }
        }

        // 파일 이름 및 저장 경로 설정
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName != null ? originalFileName.substring(originalFileName.lastIndexOf(".")) : "";
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extension;
        String savePath = filePath + saveFileName;

        try {
            file.transferTo(new File(savePath)); // 파일 저장
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 파일 저장 실패 시 null 반환
        }

        // 저장된 파일의 URL 반환
        return fileUrl + saveFileName;
    }

    @Override
    public Resource getFile(String fileName) {
        try {
            // 파일을 Resource로 불러오기
            return new UrlResource("file:" + filePath + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
