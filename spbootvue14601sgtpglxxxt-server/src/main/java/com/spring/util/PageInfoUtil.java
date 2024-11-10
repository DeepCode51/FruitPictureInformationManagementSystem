package com.spring.util;

import com.jntoo.db.utils.CollectUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 创建分页信息
 */
public class PageInfoUtil {

    private int page = -1;

    public PageInfoUtil() {}

    public PageInfoUtil(int page) {
        this.page = page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPageInfo(HttpServletRequest request, CollectUtil collect) {
        request.setAttribute("page", collect);
        request.setAttribute("totalCount", collect.getTotalRows());
    }

    public void setPageInfo(HttpServletRequest request, long total, long pagesize) {
        String urlRule = getRequestUrlPath();
        CollectUtil collect = new CollectUtil(page, total, pagesize);
        collect.setUrlRule(urlRule);
        setPageInfo(request, collect);
    }

    public String getRequestUrlPath() {
        HttpServletRequest request = Request.getRequest();
        String queryString = request.getQueryString();
        if (queryString == null) {
            queryString = "";
        }
        StringBuffer buffer = new StringBuffer(queryString.length() + 16);
        String requestURI = request.getRequestURI();
        buffer.append(requestURI).append("?");
        Map<String, String[]> param = request.getParameterMap();
        String name = "";
        String value = "";
        boolean isSearchPage = false;
        int page = -1;

        for (Map.Entry<String, String[]> entry : param.entrySet()) {
            try {
                name = entry.getKey();
                String[] values = entry.getValue();

                if (name.equals("page")) {
                    page = Integer.valueOf(values[0]).intValue();
                    buffer.append(name).append("=").append("{page}").append("&");
                    isSearchPage = true;
                } else if (null == values) {
                    buffer.append(name).append("=").append("&");
                } else if (values.length > 1) {
                    for (int i = 0; i < values.length; i++) { //用于请求参数中有多个相同名称
                        value = URLEncoder.encode(values[i], "UTF-8");
                        buffer.append(name).append("=").append(value).append("&");
                    }
                    //value = value.substring(0, value.length() - 1);
                } else {
                    value = URLEncoder.encode(values[0], "UTF-8");
                    buffer.append(name).append("=").append(value).append("&"); //用于请求参数中请求参数名唯一
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (this.page == -1) {
            this.page = page;
        }

        this.page = Math.max(this.page, 1);

        if (!isSearchPage) {
            buffer.append("page={page}&");
        }
        String result = buffer.toString();
        String urlRule = result.substring(0, result.length() - 1);
        return urlRule;
    }
}
