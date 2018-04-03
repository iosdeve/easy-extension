package com.xin.easyextension.service;

import com.xin.easyextension.domain.App;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2018-03-28
* @version 1.0
* @Description
*/
public interface IAppService {
	public int save(App app);
	public int update(App app);
	public int delete(Integer id);
	public App get(Integer id);
}
