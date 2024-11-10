package com.spring.service;

import com.spring.entity.Pinglun;
import java.util.*;
import tk.mybatis.mapper.entity.Example;

public interface PinglunService {
    /**
     * 获取所有数据
     * @return
     */
    public List<Pinglun> select();

    /**
     * 根据Pinglun类搜索数据
     * @param y
     * @return
     */
    public List<Pinglun> select(Pinglun y);

    /**
     * 根据主键获取评论一行数据
     * @param id
     * @return
     */
    public Pinglun find(Object id);

    /**
     * 根据评论获取评论类条件查询一行数据
     * @param id
     * @return
     */
    public Pinglun findEntity(Pinglun id);

    /**
     * 搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Pinglun> selectPage(Pinglun obj, int page, int pageSize);

    /**
     * 根据Example搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Pinglun> selectPageExample(Example obj, int page, int pageSize);

    /**
     * 根据主键删除一行数据
     * @param id
     * @return
     */
    public int delete(Object id);

    /**
     * 插入评论     * @param y
     * @return
     */
    public int insert(Pinglun y);

    /**
     * 更新评论     * @param y
     * @return
     */
    public int update(Pinglun y);
}
