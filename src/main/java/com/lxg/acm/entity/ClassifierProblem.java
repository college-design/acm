package com.lxg.acm.entity;

public class ClassifierProblem {
    private Long cpid;// 分类问题关联标识
    private Long pid;//问题标识
    private Long cid;//分类标识

    public ClassifierProblem() {
    }

    public ClassifierProblem(Long cpid, Long pid, Long cid) {
        this.cpid = cpid;
        this.pid = pid;
        this.cid = cid;
    }

    public Long getCpid() {
        return cpid;
    }

    public void setCpid(Long cpid) {
        this.cpid = cpid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }
}
