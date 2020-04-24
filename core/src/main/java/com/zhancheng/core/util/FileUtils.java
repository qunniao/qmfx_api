package com.zhancheng.core.util;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author x
 */
public class FileUtils {

    /**
     * endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
     */

    private static String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";

    private static String bucketDomain = "https://zc-distribution.oss-cn-hangzhou.aliyuncs.com";
    private static String accessKeyId = "LTAI8VoT591CDwLd";
    private static String accessKeySecret = "fK8hu4R3k1y24EFsrlLDpuRsIM8O6U";
    private static String bucketName = "zc-distribution";
    private static String JPG = "jpg";
    private static String PNG = "png";
    private static String GIF = "gif";

    /**
     * 上传图片
     *
     * @return
     */
    public static String uploadImage(List<MultipartFile> images) {

        if (ObjectUtil.isEmpty(images)) {
            return null;
        }
        List<String> list = addFile(images);
        String strip = "";
        if (ObjectUtil.isNotEmpty(list)) {
            strip = StringUtils.strip(list.toString(), "[]");
        }

        return strip;
    }

    public static List<String> addFile(List<MultipartFile> imageList) {

        List<String> list = new ArrayList<>();

        if (ObjectUtil.isEmpty(imageList)) {
            return null;
        }

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        for (MultipartFile file : imageList) {
            // 获取文件名
            String fileName = file.getOriginalFilename();

            String fileType = fileName.substring(fileName.lastIndexOf("."));
            System.err.println(fileType);
            // 文件存储入OSS，Object的名称为uuid
            String uuid = IdUtil.simpleUUID();
            InputStream inputStream = null;
            try {
                inputStream = file.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 添加
            list.add(bucketDomain + "/" + uuid + fileType);
            // 上传图片
            ossClient.putObject(bucketName, uuid + fileType, inputStream);
        }

        ossClient.shutdown();

        return list;
    }

    /**
     * 上传文件流
     *
     * @param bytes 文件byte数组
     * @return 文件名称
     */
    public static String addInputStream(byte[] bytes) {
        InputStream stream = new ByteArrayInputStream(bytes);
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String uuid = IdUtil.simpleUUID();
        ossClient.putObject(bucketName, uuid + ".jpg", stream);
        // 关闭OSSClient。
        ossClient.shutdown();

        return bucketDomain + "/" + uuid + ".jpg";
    }

    public static boolean deleteFile(String filePath) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        boolean exist = ossClient.doesObjectExist(bucketName, filePath);
        if (!exist) {
            System.out.println("文件不存在,filePath=" + filePath);
            return false;
        }
        System.out.println("删除文件,filePath=" + filePath);
        ossClient.deleteObject(bucketName, filePath);
        ossClient.shutdown();
        return true;
    }

    public static String verifyImage(MultipartFile files) {

        String type = "";
        try {
            type = FileTypeUtil.getType(files.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (JPG.equals(type) || PNG.equals(type) || GIF.equals(type)) {
            return type;
        } else {
            throw new MyException(CodeMsg.IMAGE_MISMATCHING);
        }
    }


}
