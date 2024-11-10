package com.spring.entity;

import com.spring.util.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "liuyanban")
public class Liuyanban implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(generator = "JDBC") // 自增的主键映射
    @Id
    @Column(name = "id", insertable = false)
    private Integer id;

    @Column(name = "xingming")
    private String xingming;

    @Column(name = "lianxidianhua")
    private String lianxidianhua;

    @Column(name = "liuyanneirong")
    private String liuyanneirong;

    @Column(name = "liuyanren")
    private String liuyanren;

    @Column(name = "huifuneirong")
    private String huifuneirong;

    @Column(name = "liuyanzhuangtai")
    private String liuyanzhuangtai;

    @Column(name = "addtime")
    private String addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    public String getLianxidianhua() {
        return lianxidianhua;
    }

    public void setLianxidianhua(String lianxidianhua) {
        this.lianxidianhua = lianxidianhua;
    }

    public String getLiuyanneirong() {
        return liuyanneirong;
    }

    public void setLiuyanneirong(String liuyanneirong) {
        this.liuyanneirong = liuyanneirong;
    }

    public String getLiuyanren() {
        return liuyanren;
    }

    public void setLiuyanren(String liuyanren) {
        this.liuyanren = liuyanren;
    }

    public String getHuifuneirong() {
        return huifuneirong;
    }

    public void setHuifuneirong(String huifuneirong) {
        this.huifuneirong = huifuneirong;
    }

    public String getLiuyanzhuangtai() {
        return liuyanzhuangtai;
    }

    public void setLiuyanzhuangtai(String liuyanzhuangtai) {
        this.liuyanzhuangtai = liuyanzhuangtai;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
