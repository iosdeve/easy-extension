package com.xin.easyextension.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xin.easyextension.domain.LibFile;
import com.xin.easyextension.mapper.LibFileMapper;
import com.xin.easyextension.service.ILibFileService;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2018-05-24
* @version 1.0
* @Description
*/

@Service
public class LibFileService implements ILibFileService {
	Logger LOG=LoggerFactory.getLogger(LibFileService.class);
	@Autowired
	LibFileMapper dao;
	
	@Override
	public int save(LibFile entity) {
		return dao.insert(entity);
	}

	@Override
	public int update(LibFile entity) {
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
	public LibFile get(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

}
