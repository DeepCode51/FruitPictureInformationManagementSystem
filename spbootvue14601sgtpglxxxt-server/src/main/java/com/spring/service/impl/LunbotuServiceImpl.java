package com.spring.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.dao.*;
import com.spring.entity.*;
import com.spring.service.*;
import com.spring.util.*;
import java.util.*;
import java.util.*;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service("LunbotuService")
public class LunbotuServiceImpl implements LunbotuService {

    @Resource
    private LunbotuMapper dao;

    /**
     * 获取所有数据
     * @return
     */
    public List<Lunbotu> select() {
        return dao.select(null);
    }

    /**
     * 根据Lunbotu类搜索数据
     * @param y
     * @return
     */
    public List<Lunbotu> select(Lunbotu y) {
        return dao.select(y);
    }

    /**
     * 根据主键获取轮播图一行数据
     * @param id
     * @return
     */
    public Lunbotu find(Object id) {
        return dao.selectByPrimaryKey(id);
    }

    /**
     * 根据轮播图获取轮播图类条件查询一行数据
     * @param id
     * @return
     */
    public Lunbotu findEntity(Lunbotu id) {
        return dao.selectOne(id);
    }

    /**
     * 搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Lunbotu> selectPage(Lunbotu obj, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        List<Lunbotu> list = select(obj);
        PageInfo<Lunbotu> pageInfo = new PageInfo(list);
        PageInfoUtil pageInfoUtil = new PageInfoUtil(page);
        pageInfoUtil.setPageInfo(Request.getRequest(), pageInfo.getTotal(), pageSize);
        return list;
    }

    /**
     * 根据Example搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Lunbotu> selectPageExample(Example obj, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        List<Lunbotu> list = dao.selectByExample(obj);
        PageInfo<Lunbotu> pageInfo = new PageInfo(list);
        PageInfoUtil pageInfoUtil = new PageInfoUtil(page);
        pageInfoUtil.setPageInfo(Request.getRequest(), pageInfo.getTotal(), pageSize);
        return list;
    }

    /**
     * 根据主键删除一行数据
     * @param id
     * @return
     */
    public int delete(Object id) {
        return dao.deleteByPrimaryKey(id);
    }

    /**
     * 插入轮播图     * @param y
     * @return
     */
    public int insert(Lunbotu y) {
        return dao.insertSelective(y);
    }

    /**
     * 更新轮播图     * @param y
     * @return
     */
    public int update(Lunbotu y) {
        return dao.updateByPrimaryKeySelective(y);
    }
}
