package com.example.demo.controller;

import com.example.demo.tools.Respond;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 上传图片
 *
 * @author 搬砖的码农
 * @date 2023/03/23
 * @email
 **/
@Controller
@CrossOrigin(origins = "*")
public class FileController {
    @PostMapping("/fileImage")
    @ResponseBody
    public String fileImage(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        if (file.isEmpty()) {
            return "错误";
        }

        //获取原来的文件和后缀
        String fileName = null;
        String originalFilename = file.getOriginalFilename();

        //获取照片后缀名
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf('.'));

        //使用uuid
        fileName = UUID.randomUUID().toString()+suffixName;

        //拼接地址给到前端
        String path = "http://image.com/"+fileName;

        try {
            file.transferTo(new File("/Users/huangdaxian/Desktop/pay/static/"+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}
