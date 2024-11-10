package com.spring.controller;

import com.alibaba.fastjson.JSON;
import com.spring.config.Configure;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传控制器
 */
@Controller
public class UploadController extends BaseController {

    /**
     * 上传文件
     * @param fujian
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/upload_re")
    public String Upload(MultipartFile fujian) throws Exception {
        String fileName = fujian.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;
        String filePath = getFolder();

        try {
            File file = new File(getPhysicalPath(filePath) + "/" + fileName);
            fujian.transferTo(file);
            request.setAttribute("url", Configure.FILE_PREFIX + filePath + "/" + fileName);
            if (isAjax()) {
                return jsonResult(request.getAttribute("url"));
            }
            return "upload";
        } catch (Exception e) {
            return showError(e.getMessage());
        }
    }

    /**
     * 上传文件
     * @param upfile
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/umeditor")
    @ResponseBody
    public String umeditors(MultipartFile upfile) throws Exception {
        String fileName = upfile.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;
        String filePath = getFolder();

        try {
            File file = new File(getPhysicalPath(filePath) + "/" + fileName);
            upfile.transferTo(file);
            String url = Configure.FILE_PREFIX + filePath + "/" + fileName;
            Map map = new HashMap();
            map.put("name", file.getName());
            map.put("originalName", upfile.getName());
            map.put("size", file.getTotalSpace());
            map.put("state", "SUCCESS");
            map.put("type", upfile.getContentType());
            map.put("url", url);

            return JSON.toJSONString(map);
        } catch (Exception e) {
            return showError(e.getMessage());
        }
    }

    private String getFolder() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        String path = formater.format(new Date());
        File dir = new File(this.getPhysicalPath(path));
        if (!dir.exists()) {
            try {
                dir.mkdirs();
            } catch (Exception e) {
                return "";
            }
        }
        return path;
    }

    /**
     * 根据传入的虚拟路径获取物理路径
     *
     * @param path
     * @return
     */
    private String getPhysicalPath(String path) {
        String realPath = Configure.UPLOAD_DIR + path;
        String absolutePath = new File(realPath).getAbsolutePath();
        return absolutePath;
    }
}
