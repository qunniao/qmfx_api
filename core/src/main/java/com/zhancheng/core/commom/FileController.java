package com.zhancheng.core.commom;

import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project FileController
 * @date 2019/9/29 18:04
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件上传")
public class FileController {

    @Verify
    @PostMapping("/addFile")
    @ApiOperation(value = "上传图片")
    @ApiImplicitParam(name = "files", value = "上传文件集合", required = true)
    public R addImage(List<MultipartFile> files) {
        return R.ok(FileUtils.uploadImage(files));
    }
}
