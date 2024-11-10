package com.spring.util;

import com.jntoo.db.utils.*;
import com.spring.config.Configure;
import javax.servlet.http.HttpServletRequest;

/**
 * 操作类
 */
public class Info extends InfoUtil {

    /**
     * 下载内容
     * @param content 待下载的内容
     * @return 返回下载后的内容
     */
    public static String download(String content) {
        return DownloadRemoteImage.run(content, Configure.UPLOAD_DIR, "./upload"); // 将下载的内容保存在网站中
    }
}
