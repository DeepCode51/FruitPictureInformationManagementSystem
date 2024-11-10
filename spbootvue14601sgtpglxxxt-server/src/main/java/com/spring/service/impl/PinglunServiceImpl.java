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

@Service("PinglunService")
public class PinglunServiceImpl implements PinglunService {

    @Resource
    private PinglunMapper dao;

    /**
     * 获取所有数据
     * @return
     */
    public List<Pinglun> select() {
        return dao.select(null);
    }

    /**
     * 根据Pinglun类搜索数据
     * @param y
     * @return
     */
    public List<Pinglun> select(Pinglun y) {
        return dao.select(y);
    }

    /**
     * 根据主键获取评论一行数据
     * @param id
     * @return
     */
    public Pinglun find(Object id) {
        return dao.selectByPrimaryKey(id);
    }

    /**
     * 根据评论获取评论类条件查询一行数据
     * @param id
     * @return
     */
    public Pinglun findEntity(Pinglun id) {
        return dao.selectOne(id);
    }

    /**
     * 搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Pinglun> selectPage(Pinglun obj, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        List<Pinglun> list = select(obj);
        PageInfo<Pinglun> pageInfo = new PageInfo(list);
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
    public List<Pinglun> selectPageExample(Example obj, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        List<Pinglun> list = dao.selectByExample(obj);
        PageInfo<Pinglun> pageInfo = new PageInfo(list);
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
     * 插入评论     * @param y
     * @return
     */
    public int insert(Pinglun y) {
        return dao.insertSelective(y);
    }

    /**
     * 更新评论     * @param y
     * @return
     */
    public int update(Pinglun y) {
        return dao.updateByPrimaryKeySelective(y);
    }
}
