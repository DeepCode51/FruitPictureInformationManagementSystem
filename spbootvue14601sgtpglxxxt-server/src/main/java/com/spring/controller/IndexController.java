package com.spring.controller;

import com.alibaba.fastjson.*;
import com.spring.util.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制器
 */
@Controller
public class IndexController extends BaseController {

    // 首页
    @RequestMapping(value = { "/", "index" })
    public String Index() {
        List<Map> bhtList = Query.make("lunbotu").order("id desc").limit(5).select();
        assign("bhtList", bhtList);

        List<Map> shuiguotupianlist1 = Query.make("shuiguotupian").limit(100).order("id desc").select();
        assign("shuiguotupianlist1", shuiguotupianlist1);
        if (isAjax()) {
            return json();
        }
        return "index";
    }
}
