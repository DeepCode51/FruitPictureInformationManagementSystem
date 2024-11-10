package com.spring.controller;

import com.alibaba.fastjson.*;
import com.jntoo.db.DB;
import com.jntoo.db.QueryMap;
import com.jntoo.db.model.Options;
import com.jntoo.db.utils.Convert;
import com.jntoo.db.utils.StringUtil;
import com.spring.entity.*;
import com.spring.service.*;
import com.spring.util.*;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录更新密码控制器
 */
@Controller
public class UserController extends BaseController {

    @Resource
    private AdminsService adminsService;

    @Resource
    private YonghuService yonghuService;

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/login")
    public String Index() {
        return "login";
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping("/logout")
    public String Logout() {
        request.getSession().invalidate();
        return showSuccess("退出成功", "./");
    }

    /**
     * 验证登录用户
     * @param isAdmin
     * @param username
     * @param pwd
     * @param cx
     * @return
     */
    protected String authLoginUser(boolean isAdmin, String username, String pwd, String cx) {
        if (username == null || "".equals(username)) {
            return showError("账号不允许为空");
        }
        if (pwd == null || "".equals(pwd)) {
            return showError("密码不允许为空");
        }
        if (cx == null) {
            return showError("请选中登录类型");
        }
        String random;
        // 获取 token方式的验证码值
        if (isAjax() && request.getParameter("captchToken") != null) {
            random = DESUtil.decrypt("CaptchControllerPassword", request.getParameter("captchToken"));
        } else {
            random = (String) request.getSession().getAttribute("random");
        }
        String pagerandom = request.getParameter("pagerandom") == null ? "" : request.getParameter("pagerandom");
        if (request.getParameter("a") != null && !pagerandom.equals(random)) {
            return showError("验证码不正确", 20);
        }
        String table = "";

        if (cx.equals("管理员")) {
            table = "admins";
            Admins user = adminsService.login(username, pwd);
            if (user == null) {
                return showError("用户名或密码错误");
            }
            session.setAttribute("id", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("cx", cx);
            session.setAttribute("login", cx);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("pwd", user.getPwd());
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(user));
            jsonObject.put("cx", session.getAttribute("cx"));
            jsonObject.put("username", session.getAttribute("username"));
            jsonObject.put("login", session.getAttribute("login"));
            assign("session", jsonObject);
        }
        if (cx.equals("用户")) {
            table = "yonghu";
            Yonghu user = yonghuService.login(username, pwd);
            if (user == null) {
                return showError("用户名或密码错误");
            }
            session.setAttribute("id", user.getId());
            session.setAttribute("username", user.getYonghuming());
            session.setAttribute("cx", cx);
            session.setAttribute("login", cx);
            session.setAttribute("yonghuming", user.getYonghuming());
            session.setAttribute("mima", user.getMima());
            session.setAttribute("xingming", user.getXingming());
            session.setAttribute("xingbie", user.getXingbie());
            session.setAttribute("shouji", user.getShouji());
            session.setAttribute("youxiang", user.getYouxiang());
            session.setAttribute("touxiang", user.getTouxiang());
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(user));
            jsonObject.put("cx", session.getAttribute("cx"));
            jsonObject.put("username", session.getAttribute("username"));
            jsonObject.put("login", session.getAttribute("login"));
            assign("session", jsonObject);
        }

        if (session.getAttribute("username") == null) {
            return showError("账号或密码错误");
        }

        assign("token", createToken(table, session.getAttribute("id"), session.getAttribute("cx"), session.getAttribute("login"), session.getAttribute("username")));

        if (this.isAjax()) {
            return json();
        } else {
            String referer = request.getParameter("referer");
            if (referer == null) {
                if (isAdmin) {
                    referer = "./main.do";
                } else {
                    referer = "./";
                }
            }
            return showSuccess("登录成功", referer);
        }
    }

    @Resource
    protected JwtTokenUtils jwtTokenUtils;

    public String createToken(Object table, Object id, Object cx, Object login, Object username) {
        HashMap tokenMap = new HashMap();
        tokenMap.put("tab", table);
        tokenMap.put("id", id);
        tokenMap.put("cx", cx);
        tokenMap.put("username", username);
        tokenMap.put("login", login);
        return jwtTokenUtils.generateToken(id, tokenMap);
    }

    /**
     * 使用已有token 登录
     *
     * @return
     */
    @RequestMapping("/tokenLogin")
    public String tokenLogin() {
        String token = request.getParameter("token");
        HashMap result = new HashMap();
        Map session1 = new HashMap();
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
                    session1.putAll(map);
                    session1.putAll(data);
                    result.putAll(map);
                    return null;
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result.isEmpty()) {
            HttpSession session = request.getSession();
            session.invalidate();
            return jsonError("token 无效");
        }
        // 生成新的token
        token = jwtTokenUtils.generateToken(result.get("id"), result);
        assign("token", token);
        assign("session", session1);
        return json();
    }

    /**
     * 后台主页面
     * @return
     */
    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    /**
     * 后台初始页面
     * @return
     */
    @RequestMapping("/sy")
    public String sy() {
        return "sy";
    }

    /**
     * 不一定有
     * @return
     */
    @RequestMapping("/mygo")
    public String mygo() {
        return "mygo";
    }

    /**
     * 头部页面
     * @return
     */
    @RequestMapping("/top")
    public String top() {
        return "top";
    }

    /**
     * 验证登录页面
     * @return
     */
    @RequestMapping("/authLogin")
    public String authLogin() {
        String username = Request.get("username");
        String pwd = Request.get("pwd");
        String cx = Request.get("cx");
        return authLoginUser(false, username, pwd, cx);
    }

    /**
     * 验证后台登录
     * @return
     */
    @RequestMapping("/authAdminLogin")
    public String authAdminLogin() {
        String username = Request.get("username");
        String pwd = Request.get("pwd");
        String cx = Request.get("cx");
        return authLoginUser(true, username, pwd, cx);
    }

    /**
     * 修改登录密码页面
     * @return
     */
    @RequestMapping("/mod")
    public String mod() {
        return "mod";
    }

    /**
     * 保存修改密码
     * @return
     */
    @RequestMapping("/editPassword")
    public String editPassword() {
        String username = request.getSession().getAttribute("username").toString();
        String cx = request.getSession().getAttribute("login").toString();
        String oldPassword = Request.get("oldPassword");
        String newPwd = Request.get("newPwd");
        String newPwd2 = Request.get("newPwd2");

        if (!newPwd.equals(newPwd2)) {
            return showError("两次密码不一致");
        }

        if (cx.equals("管理员")) {
            Admins user = adminsService.login(username, oldPassword);
            if (user == null) {
                return showError("原密码不正确");
            }
            adminsService.updatePassword(user.getId(), newPwd);
        }
        if (cx.equals("用户")) {
            Yonghu user = yonghuService.login(username, oldPassword);
            if (user == null) {
                return showError("原密码不正确");
            }
            yonghuService.updatePassword(user.getId(), newPwd);
        }
        return showSuccess("修改密码成功", "./mod.do");
    }

    @RequestMapping("/db/select")
    public String select(@RequestBody Map<String, Object> data) {
        String sql = String.valueOf(data.get("sql"));
        String type = String.valueOf(data.get("type"));
        List binds = null;
        if (data.containsKey("binds") && data.get("binds") instanceof List) {
            binds = (List) data.get("binds");
        } else {
            binds = new ArrayList();
        }

        Object[] datas = binds.toArray();

        if ("select".equals(type)) {
            return showSuccess(DB.select(sql, datas));
        } else {
            return showSuccess(DB.find(sql, datas));
        }
    }

    @PostMapping("/db/query")
    public String query(@RequestBody Map map) {
        if (!map.containsKey("name")) {
            return showError("找不到相关名称");
        }
        if (!map.containsKey("options") && !(map.get("options") instanceof Map)) {
            return showError("找不到相关配置");
        }
        if (!map.containsKey("func")) {
            return showError("找不到引用");
        }

        JSONObject object = new JSONObject();
        object.putAll((Map) map.get("options"));

        QueryMap queryWrapper = DB.name(map.get("name").toString());
        queryWrapper.setOptions(object.toJavaObject(Options.class));
        String func = map.get("func").toString().toLowerCase();
        Object result = null;
        List args = (List) map.get("args");
        try {
            if (func.equals("select")) {
                result = queryWrapper.select();
            } else if (func.equals("find")) {
                if (args == null) {
                    result = queryWrapper.find();
                } else {
                    result = queryWrapper.find(args.get(0));
                }
            } else if (func.equals("count")) {
                if (args == null) {
                    result = queryWrapper.count();
                } else {
                    result = queryWrapper.count(String.valueOf(args.get(0)));
                }
            } else if (func.equals("avg")) {
                result = queryWrapper.avg(String.valueOf(args.get(0)));
            } else if (func.equals("sum")) {
                result = queryWrapper.sum(String.valueOf(args.get(0)));
            } else if (func.equals("max")) {
                result = queryWrapper.max(String.valueOf(args.get(0)));
            } else if (func.equals("min")) {
                result = queryWrapper.min(String.valueOf(args.get(0)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return showError(e.getMessage());
        }
        return showSuccess(result);
    }
}
