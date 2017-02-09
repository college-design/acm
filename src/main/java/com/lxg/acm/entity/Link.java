package com.lxg.acm.entity;

import java.io.Serializable;

/**
 * 链接实体类
 * Created by 刘雪岗 on 2017/2/9.
 */
public class Link implements Serializable{

    private int id; // 主键id

    private String name; // 链接名称

    private String url; // 连接地址

    private String type; // 链接类型

    public Link() {
    }

    public Link(int id, String name, String url, String type) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
