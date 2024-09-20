package com.project.back.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.back.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ImageController {
    
    private final FileService fileService;

    // 파일 업로드
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String url = fileService.upload(file);
        if (url == null) {
            return "파일 업로드 실패: 저장 경로를 확인하세요."; // 파일 업로드 실패 시 메시지 반환
        }
        return url;
    }

    // 파일 다운로드
    @GetMapping(value = "/file/{fileName}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    public Resource getFile(@PathVariable("fileName") String fileName) {
        Resource resource = fileService.getFile(fileName);
        if (resource == null) {
            // 파일이 없으면 에러 처리
            throw new RuntimeException("파일을 찾을 수 없습니다: " + fileName);
        }
        return resource;
    }
}
