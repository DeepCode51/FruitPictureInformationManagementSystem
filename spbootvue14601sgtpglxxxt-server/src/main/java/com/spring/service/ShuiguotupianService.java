package com.spring.service;

import com.spring.entity.Shuiguotupian;
import java.util.*;
import tk.mybatis.mapper.entity.Example;

public interface ShuiguotupianService {
    /**
     * 获取所有数据
     * @return
     */
    public List<Shuiguotupian> select();

    /**
     * 根据Shuiguotupian类搜索数据
     * @param y
     * @return
     */
    public List<Shuiguotupian> select(Shuiguotupian y);

    /**
     * 根据主键获取水果图片一行数据
     * @param id
     * @return
     */
    public Shuiguotupian find(Object id);

    /**
     * 根据水果图片获取水果图片类条件查询一行数据
     * @param id
     * @return
     */
    public Shuiguotupian findEntity(Shuiguotupian id);

    /**
     * 搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Shuiguotupian> selectPage(Shuiguotupian obj, int page, int pageSize);

    /**
     * 根据Example搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Shuiguotupian> selectPageExample(Example obj, int page, int pageSize);

    /**
     * 根据主键删除一行数据
     * @param id
     * @return
     */
    public int delete(Object id);

    /**
     * 插入水果图片     * @param y
     * @return
     */
    public int insert(Shuiguotupian y);

    /**
     * 更新水果图片     * @param y
     * @return
     */
    public int update(Shuiguotupian y);
}
