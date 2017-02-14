package com.lxg.acm.entity;

import java.util.Date;

/**
 * 考试实体类
 * @author Administrator
 *
 */
public class Contest {

	Long cid; // 比赛id
	String title; // 比赛名称
	Date startTime; // 比赛开始时间
	Date endTime;   // 比赛结束时间
	char defunct;  // 是否结束
	String description; // 描述
//	String address;  // 比赛地址=========================
    int status; // 状态

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public char getDefunct() {
		return defunct;
	}

	public void setDefunct(char defunct) {
		this.defunct = defunct;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}

}
