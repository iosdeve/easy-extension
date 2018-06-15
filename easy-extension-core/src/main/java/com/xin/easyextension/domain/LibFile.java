package com.xin.easyextension.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibFile {
    private Integer id;

    private Integer appId;

    private String fileName;

    private String name;

    private Integer filesize;

    private String filePath;

    private Integer userid;

    private Date created;

    private Date lastUpdated;
    
    private List<NodeTable> executeClasses=new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

	public List<NodeTable> getExecuteClasses() {
		return executeClasses;
	}

	public void setExecuteClasses(List<NodeTable> executeClasses) {
		this.executeClasses = executeClasses;
	}

	@Override
	public String toString() {
		return "LibFile [id=" + id + ", appId=" + appId + ", fileName=" + fileName + ", name=" + name + ", filesize="
				+ filesize + ", filePath=" + filePath + ", userid=" + userid + "]";
	}
    
    
}