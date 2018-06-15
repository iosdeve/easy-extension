package com.xin.easyextension.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class App {
    private Integer id;

    @NotEmpty(message="程序名称不能为空")
    @Pattern(regexp = "^[a-zA-z]\\w{2,15}$", message="字母、数字、下划线组成，字母开头，3-16位")
    private String appName;
    
    private String appDesc;

    @NotEmpty(message="没有找到实现IExtension接口的类")
    private String actionClass;

    private String appPath;

    private Integer userid;

    private Date created;

    private Date lastUpdated;
    
    @NotEmpty(message="没有上传相关jar文件")
    private List<LibFile> libs=new ArrayList<LibFile>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getActionClass() {
        return actionClass;
    }

    public void setActionClass(String actionClass) {
        this.actionClass = actionClass == null ? null : actionClass.trim();
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath == null ? null : appPath.trim();
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
    
	public String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}
	
	

	public List<LibFile> getLibs() {
		return libs;
	}

	public void setLibs(List<LibFile> libs) {
		this.libs = libs;
	}

	@Override
	public String toString() {
		return "App [id=" + id + ", appName=" + appName + ", actionClass=" + actionClass + ", appPath=" + appPath
				+ ", userid=" + userid + ", created=" + created + ", lastUpdated=" + lastUpdated + "]";
	}
    
    
}