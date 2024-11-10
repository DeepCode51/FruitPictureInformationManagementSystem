package com.spring.service;

import com.spring.entity.Admins;
import java.util.*;
import tk.mybatis.mapper.entity.Example;

public interface AdminsService {
    public Admins login(String username, String password);

    public boolean updatePassword(int id, String newPassword);

    /**
     * 获取所有数据
     * @return
     */
    public List<Admins> select();

    /**
     * 根据Admins类搜索数据
     * @param y
     * @return
     */
    public List<Admins> select(Admins y);

    /**
     * 根据主键获取管理员一行数据
     * @param id
     * @return
     */
    public Admins find(Object id);

    /**
     * 根据管理员获取管理员类条件查询一行数据
     * @param id
     * @return
     */
    public Admins findEntity(Admins id);

    /**
     * 搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Admins> selectPage(Admins obj, int page, int pageSize);

    /**
     * 根据Example搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Admins> selectPageExample(Example obj, int page, int pageSize);

    /**
     * 根据主键删除一行数据
     * @param id
     * @return
     */
    public int delete(Object id);

    /**
     * 插入管理员     * @param y
     * @return
     */
    public int insert(Admins y);

    /**
     * 更新管理员     * @param y
     * @return
     */
    public int update(Admins y);
}
