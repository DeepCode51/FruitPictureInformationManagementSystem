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

@Service("YonghuService")
public class YonghuServiceImpl implements YonghuService {

    @Resource
    private YonghuMapper dao;

    public Yonghu login(String username, String password) {
        Yonghu user = new Yonghu();
        user.setYonghuming(username);
        user.setMima(password);

        return this.dao.login(user);
    }

    public boolean updatePassword(int id, String newPassword) {
        Yonghu user = new Yonghu();
        user.setId(id);
        user.setMima(newPassword);
        int i = this.dao.updateByPrimaryKeySelective(user);
        return i == 1;
    }

    /**
     * 获取所有数据
     * @return
     */
    public List<Yonghu> select() {
        return dao.select(null);
    }

    /**
     * 根据Yonghu类搜索数据
     * @param y
     * @return
     */
    public List<Yonghu> select(Yonghu y) {
        return dao.select(y);
    }

    /**
     * 根据主键获取用户一行数据
     * @param id
     * @return
     */
    public Yonghu find(Object id) {
        return dao.selectByPrimaryKey(id);
    }

    /**
     * 根据用户获取用户类条件查询一行数据
     * @param id
     * @return
     */
    public Yonghu findEntity(Yonghu id) {
        return dao.selectOne(id);
    }

    /**
     * 搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Yonghu> selectPage(Yonghu obj, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        List<Yonghu> list = select(obj);
        PageInfo<Yonghu> pageInfo = new PageInfo(list);
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
    public List<Yonghu> selectPageExample(Example obj, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        List<Yonghu> list = dao.selectByExample(obj);
        PageInfo<Yonghu> pageInfo = new PageInfo(list);
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
     * 插入用户     * @param y
     * @return
     */
    public int insert(Yonghu y) {
        return dao.insertSelective(y);
    }

    /**
     * 更新用户     * @param y
     * @return
     */
    public int update(Yonghu y) {
        return dao.updateByPrimaryKeySelective(y);
    }
}
