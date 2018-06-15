package com.xin.easyextension.service;

import com.xin.easyextension.domain.LibFile;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2018-03-28
* @version 1.0
* @Description
*/
public interface ILibFileService {
	public int save(LibFile entity);
	public int update(LibFile entity);
	public int delete(Integer id);
	public LibFile get(Integer id);
}
