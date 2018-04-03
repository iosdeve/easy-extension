package com.xin.easyextension.service;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.xin.dataset.XlsDataSetBeanFactory;
import com.xin.easyextension.domain.App;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2018-03-28
* @version 1.0
* @Description
*/

@SpringApplicationContext({"classpath:applicationContext.xml"})
public class AppServiceTest extends UnitilsJUnit4 {
	@SpringBean("appService")
	IAppService appService;
	
	@Test
	@DataSet("app.xls")
	public void testLoad() {
		
	}
	
	@Test
	@ExpectedDataSet(value="app_save.xls")
	public void testSave() throws Exception {
		App app = XlsDataSetBeanFactory.createBean(AppServiceTest.class, "app_save.xls", "app", App.class);
		appService.save(app);
	}

}
