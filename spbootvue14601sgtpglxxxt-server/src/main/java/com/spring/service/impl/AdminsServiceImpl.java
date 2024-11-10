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

@Service("AdminsService")
public class AdminsServiceImpl implements AdminsService {

    @Resource
    private AdminsMapper dao;

    public Admins login(String username, String password) {
        Admins user = new Admins();
        user.setUsername(username);
        user.setPwd(password);

        return this.dao.login(user);
    }

    public boolean updatePassword(int id, String newPassword) {
        Admins user = new Admins();
        user.setId(id);
        user.setPwd(newPassword);
        int i = this.dao.updateByPrimaryKeySelective(user);
        return i == 1;
    }

    /**
     * 获取所有数据
     * @return
     */
    public List<Admins> select() {
        return dao.select(null);
    }

    /**
     * 根据Admins类搜索数据
     * @param y
     * @return
     */
    public List<Admins> select(Admins y) {
        return dao.select(y);
    }

    /**
     * 根据主键获取管理员一行数据
     * @param id
     * @return
     */
    public Admins find(Object id) {
        return dao.selectByPrimaryKey(id);
    }

    /**
     * 根据管理员获取管理员类条件查询一行数据
     * @param id
     * @return
     */
    public Admins findEntity(Admins id) {
        return dao.selectOne(id);
    }

    /**
     * 搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Admins> selectPage(Admins obj, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        List<Admins> list = select(obj);
        PageInfo<Admins> pageInfo = new PageInfo(list);
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
    public List<Admins> selectPageExample(Example obj, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        List<Admins> list = dao.selectByExample(obj);
        PageInfo<Admins> pageInfo = new PageInfo(list);
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
     * 插入管理员     * @param y
     * @return
     */
    public int insert(Admins y) {
        return dao.insertSelective(y);
    }

    /**
     * 更新管理员     * @param y
     * @return
     */
    public int update(Admins y) {
        return dao.updateByPrimaryKeySelective(y);
    }
}
