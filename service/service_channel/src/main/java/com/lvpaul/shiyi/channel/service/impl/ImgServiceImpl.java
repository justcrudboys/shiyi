package com.lvpaul.shiyi.channel.service.impl;

import com.lvpaul.shiyi.channel.service.ImgService;
import com.lvpaul.shiyi.utils.file.cos.COSFileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImgServiceImpl implements ImgService {
    @Value("${tencent.cos.file.region}")
    private String region;

    @Value("${tencent.cos.file.secretid}")
    private String secretId;

    @Value("${tencent.cos.file.secretkey}")
    private String secretKey;

    @Value("${tencent.cos.file.bucketname}")
    private String bucketName;
    @Override
    public String uploadChannelImg(MultipartFile file) {
        String url = COSFileUtil.upload(file,region,bucketName,secretId,secretKey);
        return url;
    }
}
