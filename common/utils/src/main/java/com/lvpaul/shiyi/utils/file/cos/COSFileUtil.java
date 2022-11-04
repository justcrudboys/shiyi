package com.lvpaul.shiyi.utils.file.cos;

import com.alibaba.fastjson.JSON;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

public class COSFileUtil {
    public static String upload(MultipartFile file,String regionName,String bucketName,String secretId,String secretKey) {

        // 1 初始化用户身份信息（secretId, secretKey）
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);

        // 2 设置 bucket 的地域
        Region region = new Region(regionName);
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        try{
            // 指定要上传的文件
            InputStream inputStream = file.getInputStream();
            // 指定文件将要存放的存储桶
            // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
            String key = UUID.randomUUID().toString().replaceAll("-","")+ file.getOriginalFilename();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucketName, key, inputStream,objectMetadata);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            String url = "https://"+bucketName+"."+"cos"+"."+regionName+".myqcloud.com"+"/"+key;
            return url;
        } catch (Exception clientException) {
            return null;
        }finally {
            //关闭客户端
            cosClient.shutdown();
        }
    }
}
