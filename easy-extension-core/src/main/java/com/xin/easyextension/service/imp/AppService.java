package com.xin.easyextension.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xin.easyextension.domain.App;
import com.xin.easyextension.mapper.AppMapper;
import com.xin.easyextension.service.IAppService;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2018-03-28
* @version 1.0
* @Description
*/

@Service
public class AppService implements IAppService {
	
	@Autowired
	AppMapper appDao;

	@Override
	public int save(App app) {
		return appDao.insert(app);
	}

	@Override
	public int update(App app) {
		int updateCount=appDao.updateByPrimaryKey(app);
		if(updateCount==0) {
			throw new RuntimeException("数据已被其它修改，更新失败：Id"+app.getId());
		}
		return updateCount;
	}

	@Override
	public int delete(Integer id) {
		return appDao.deleteByPrimaryKey(id);

	}

	@Override
	public App get(Integer id) {
		return appDao.selectByPrimaryKey(id);

	}

}
