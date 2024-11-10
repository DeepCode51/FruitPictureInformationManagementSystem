package com.spring.service;

import com.spring.entity.Lunbotu;
import java.util.*;
import tk.mybatis.mapper.entity.Example;

public interface LunbotuService {
    /**
     * 获取所有数据
     * @return
     */
    public List<Lunbotu> select();

    /**
     * 根据Lunbotu类搜索数据
     * @param y
     * @return
     */
    public List<Lunbotu> select(Lunbotu y);

    /**
     * 根据主键获取轮播图一行数据
     * @param id
     * @return
     */
    public Lunbotu find(Object id);

    /**
     * 根据轮播图获取轮播图类条件查询一行数据
     * @param id
     * @return
     */
    public Lunbotu findEntity(Lunbotu id);

    /**
     * 搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Lunbotu> selectPage(Lunbotu obj, int page, int pageSize);

    /**
     * 根据Example搜索分页
     * @param obj
     * @param page
     * @param pageSize
     * @return
     */
    public List<Lunbotu> selectPageExample(Example obj, int page, int pageSize);

    /**
     * 根据主键删除一行数据
     * @param id
     * @return
     */
    public int delete(Object id);

    /**
     * 插入轮播图     * @param y
     * @return
     */
    public int insert(Lunbotu y);

    /**
     * 更新轮播图     * @param y
     * @return
     */
    public int update(Lunbotu y);
}
