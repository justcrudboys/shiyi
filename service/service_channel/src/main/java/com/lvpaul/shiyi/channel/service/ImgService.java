package com.lvpaul.shiyi.channel.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImgService {
    public String uploadChannelImg(MultipartFile file);
}
