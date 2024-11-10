package com.spring.service;

import com.spring.entity.Yonghu;
import java.util.*;
import tk.mybatis.mapper.entity.Example;

public interface YonghuService {
    public Yonghu login(String username, String password);

    public boolean updatePassword(int id, String newPassword);

    /**
     * 获取所有数据
     * @return
     */
    public List<Yonghu> select();

    /**
     * 根据Yonghu类搜索数据
     * @param y
     * @return
     */
    public List<Yonghu> select(Yonghu y);

    /**
     * 根据主键获取用户一行数据
     * @param id
     * @return
     */
    public Yonghu find(Object id);

    /**
     * 根据用户获取用户类条件查询一行数据
     * @param id
     * @return
     */
    public Yonghu findEntity(Yonghu id);

    /**
     * 搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Yonghu> selectPage(Yonghu obj, int page, int pageSize);

    /**
     * 根据Example搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Yonghu> selectPageExample(Example obj, int page, int pageSize);

    /**
     * 根据主键删除一行数据
     * @param id
     * @return
     */
    public int delete(Object id);

    /**
     * 插入用户     * @param y
     * @return
     */
    public int insert(Yonghu y);

    /**
     * 更新用户     * @param y
     * @return
     */
    public int update(Yonghu y);
}
