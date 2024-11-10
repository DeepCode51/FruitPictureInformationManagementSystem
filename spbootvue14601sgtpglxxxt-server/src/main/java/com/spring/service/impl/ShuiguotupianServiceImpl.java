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

@Service("ShuiguotupianService")
public class ShuiguotupianServiceImpl implements ShuiguotupianService {

    @Resource
    private ShuiguotupianMapper dao;

    /**
     * 获取所有数据
     * @return
     */
    public List<Shuiguotupian> select() {
        return dao.select(null);
    }

    /**
     * 根据Shuiguotupian类搜索数据
     * @param y
     * @return
     */
    public List<Shuiguotupian> select(Shuiguotupian y) {
        return dao.select(y);
    }

    /**
     * 根据主键获取水果图片一行数据
     * @param id
     * @return
     */
    public Shuiguotupian find(Object id) {
        return dao.selectByPrimaryKey(id);
    }

    /**
     * 根据水果图片获取水果图片类条件查询一行数据
     * @param id
     * @return
     */
    public Shuiguotupian findEntity(Shuiguotupian id) {
        return dao.selectOne(id);
    }

    /**
     * 搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Shuiguotupian> selectPage(Shuiguotupian obj, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        List<Shuiguotupian> list = select(obj);
        PageInfo<Shuiguotupian> pageInfo = new PageInfo(list);
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
    public List<Shuiguotupian> selectPageExample(Example obj, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        List<Shuiguotupian> list = dao.selectByExample(obj);
        PageInfo<Shuiguotupian> pageInfo = new PageInfo(list);
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
     * 插入水果图片     * @param y
     * @return
     */
    public int insert(Shuiguotupian y) {
        return dao.insertSelective(y);
    }

    /**
     * 更新水果图片     * @param y
     * @return
     */
    public int update(Shuiguotupian y) {
        return dao.updateByPrimaryKeySelective(y);
    }
}
