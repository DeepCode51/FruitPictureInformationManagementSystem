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

@Service("LiuyanbanService")
public class LiuyanbanServiceImpl implements LiuyanbanService {

    @Resource
    private LiuyanbanMapper dao;

    /**
     * 获取所有数据
     * @return
     */
    public List<Liuyanban> select() {
        return dao.select(null);
    }

    /**
     * 根据Liuyanban类搜索数据
     * @param y
     * @return
     */
    public List<Liuyanban> select(Liuyanban y) {
        return dao.select(y);
    }

    /**
     * 根据主键获取留言板一行数据
     * @param id
     * @return
     */
    public Liuyanban find(Object id) {
        return dao.selectByPrimaryKey(id);
    }

    /**
     * 根据留言板获取留言板类条件查询一行数据
     * @param id
     * @return
     */
    public Liuyanban findEntity(Liuyanban id) {
        return dao.selectOne(id);
    }

    /**
     * 搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Liuyanban> selectPage(Liuyanban obj, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        List<Liuyanban> list = select(obj);
        PageInfo<Liuyanban> pageInfo = new PageInfo(list);
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
    public List<Liuyanban> selectPageExample(Example obj, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        List<Liuyanban> list = dao.selectByExample(obj);
        PageInfo<Liuyanban> pageInfo = new PageInfo(list);
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
     * 插入留言板     * @param y
     * @return
     */
    public int insert(Liuyanban y) {
        return dao.insertSelective(y);
    }

    /**
     * 更新留言板     * @param y
     * @return
     */
    public int update(Liuyanban y) {
        return dao.updateByPrimaryKeySelective(y);
    }
}
