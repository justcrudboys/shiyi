package com.lvpaul.shiyi.user.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public String uploadAvatar(MultipartFile file);
}
