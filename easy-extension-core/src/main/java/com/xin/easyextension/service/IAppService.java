package com.xin.easyextension.service;

import java.io.IOException;
import java.util.List;

import com.xin.easyextension.common.MultipartFileParam;
import com.xin.easyextension.domain.App;
import com.xin.easyextension.domain.LibFile;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2018-03-28
* @version 1.0
* @Description
*/
public interface IAppService {
	public int save(App app) throws Exception;
	public int update(App app)throws Exception;
	public int delete(Integer id);
	public App get(Integer id);
	
	public List<App> queryAllApps();
	
	public LibFile uploadLibFile(MultipartFileParam uploadParam) throws IOException ;
	public List<String> findExecuteClassFile(String jarFilePath, String targetInterface);
	
	public void runApp(Integer id);
}
