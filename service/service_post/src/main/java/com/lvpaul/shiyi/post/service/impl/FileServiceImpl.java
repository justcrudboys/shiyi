package com.lvpaul.shiyi.post.service.impl;

import com.lvpaul.shiyi.post.service.FileService;
import com.lvpaul.shiyi.utils.file.cos.COSFileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    @Value("${tencent.cos.file.region}")
    private String region;

    @Value("${tencent.cos.file.secretid}")
    private String secretId;

    @Value("${tencent.cos.file.secretkey}")
    private String secretKey;

    @Value("${tencent.cos.file.bucketname}")
    private String bucketName;
    @Override
    public String uploadFile(MultipartFile file) {
        String url = COSFileUtil.upload(file,region,bucketName,secretId,secretKey);
        return url;
    }
}
