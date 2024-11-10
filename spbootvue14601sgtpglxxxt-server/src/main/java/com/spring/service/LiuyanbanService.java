package com.spring.service;

import com.spring.entity.Liuyanban;
import java.util.*;
import tk.mybatis.mapper.entity.Example;

public interface LiuyanbanService {
    /**
     * 获取所有数据
     * @return
     */
    public List<Liuyanban> select();

    /**
     * 根据Liuyanban类搜索数据
     * @param y
     * @return
     */
    public List<Liuyanban> select(Liuyanban y);

    /**
     * 根据主键获取留言板一行数据
     * @param id
     * @return
     */
    public Liuyanban find(Object id);

    /**
     * 根据留言板获取留言板类条件查询一行数据
     * @param id
     * @return
     */
    public Liuyanban findEntity(Liuyanban id);

    /**
     * 搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Liuyanban> selectPage(Liuyanban obj, int page, int pageSize);

    /**
     * 根据Example搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Liuyanban> selectPageExample(Example obj, int page, int pageSize);

    /**
     * 根据主键删除一行数据
     * @param id
     * @return
     */
    public int delete(Object id);

    /**
     * 插入留言板     * @param y
     * @return
     */
    public int insert(Liuyanban y);

    /**
     * 更新留言板     * @param y
     * @return
     */
    public int update(Liuyanban y);
}
