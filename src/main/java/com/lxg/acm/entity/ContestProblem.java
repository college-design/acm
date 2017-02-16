package com.lxg.acm.entity;

/**
 * Created by 刘雪岗 on 2017/2/16.
 */
public class ContestProblem {
    private Long cpid;
    private Long cid;
    private Long pid;
    private String title;
    private Integer num;

    public ContestProblem(Long cpid, Long cid, Long pid, String title, Integer num) {
        this.cpid = cpid;
        this.cid = cid;
        this.pid = pid;
        this.title = title;
        this.num = num;
    }

    public ContestProblem() {
    }

    public Long getCpid() {
        return cpid;
    }

    public void setCpid(Long cpid) {
        this.cpid = cpid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
