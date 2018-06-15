package com.xin.easyextension.service;

import com.xin.easyextension.domain.NodeTable;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2018-06-08
* @version 1.0
* @Description
*/
public interface INodeTableService {
	public int save(NodeTable entity);
	public int update(NodeTable entity);
	public int delete(Integer id);
	public NodeTable get(Integer id);
}
