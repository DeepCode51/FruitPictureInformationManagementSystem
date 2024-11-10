package com.spring.interceptor;

import com.jntoo.db.DB;
import com.jntoo.db.utils.Convert;
import com.jntoo.db.utils.StringUtil;
import com.spring.util.JwtTokenUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    private JwtTokenUtils jwtTokenUtils;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");

        // 在这里设置跨域请求处理
        if (!StringUtil.isNullOrEmpty(request.getHeader("Origin"))) {
            response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.addHeader("Access-Control-Allow-Methods", "POST, GET ,OPTIONS, PUT,DELETE");
            response.addHeader("Access-Control-Allow-Headers", "x-requested-with,token,content-type,auth-token,app-http,Authorization");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Max-Age", Integer.toString(86400 * 7));
            if ("OPTIONS".equals(request.getMethod().toUpperCase())) { // 这个方法只是试探，所以需要直接返回信息
                PrintWriter writer = null;
                try {
                    writer = response.getWriter();
                    writer.println("ok");
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }

        try {
            jwtTokenUtils.getClaimFromToken(
                token,
                map -> {
                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(300);
                    if (
                        StringUtil.isNullOrEmpty(map.get("tab")) ||
                        StringUtil.isNullOrEmpty(map.get("id")) ||
                        StringUtil.isNullOrEmpty(map.get("cx")) ||
                        StringUtil.isNullOrEmpty(map.get("username")) ||
                        StringUtil.isNullOrEmpty(map.get("login"))
                    ) {
                        session.invalidate();
                        return null;
                    }
                    String table = Convert.toStr(map.get("tab"));
                    String id = Convert.toStr(map.get("id"));
                    Map<String, Object> data = DB.name(table).find(id);
                    if (data == null || data.isEmpty()) {
                        session.invalidate();
                        return null;
                    }
                    session.setAttribute("cx", map.get("cx"));
                    session.setAttribute("login", map.get("login"));
                    session.setAttribute("username", map.get("username"));
                    session.setAttribute("login", map.get("login"));
                    for (Map.Entry<String, Object> entry : data.entrySet()) {
                        session.setAttribute(entry.getKey(), entry.getValue());
                    }
                    return null;
                }
            );
        } catch (Exception e) {}
        return true;
        //如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //         System.out.println("执行了TestInterceptor的postHandle方法");
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //        System.out.println("执行了TestInterceptor的afterCompletion方法");
    }
}
