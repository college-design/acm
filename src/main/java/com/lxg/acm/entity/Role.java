package com.lxg.acm.entity;

import java.io.Serializable;

public class Role implements Serializable {

    private Long rid; // 用户角色标识
    private String type; // 用户角色类型
    private Long uid; // 用户标识
    private String username; // 用户名

    public Role() {
    }

    public Role(Long rid, String type, Long uid, String username) {
        this.rid = rid;
        this.type = type;
        this.uid = uid;
        this.username = username;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
