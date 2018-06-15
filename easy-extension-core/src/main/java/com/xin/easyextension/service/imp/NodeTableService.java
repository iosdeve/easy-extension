package com.xin.easyextension.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xin.easyextension.domain.NodeTable;
import com.xin.easyextension.mapper.NodeTableMapper;
import com.xin.easyextension.service.INodeTableService;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2018-06-08
* @version 1.0
* @Description
*/

@Service
public class NodeTableService implements INodeTableService {
	Logger LOG=LoggerFactory.getLogger(NodeTableService.class);
	@Autowired
	NodeTableMapper dao;
	
	@Override
	public int save(NodeTable entity) {
		return dao.insert(entity);
	}

	@Override
	public int update(NodeTable entity) {
		int updateCount=dao.updateByPrimaryKey(entity);
		if(updateCount==0) {
			LOG.info("entity id:"+entity.getId()+ " not found, can't update");
			throw new RuntimeException("数据更新失败：Id"+entity.getId());
		}
		return updateCount;
	}

	@Override
	public int delete(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public NodeTable get(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

}
