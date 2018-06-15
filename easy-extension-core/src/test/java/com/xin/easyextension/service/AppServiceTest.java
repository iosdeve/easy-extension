package com.xin.easyextension.service;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xin.easyextension.domain.App;
import com.xin.easyextension.domain.LibFile;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2018-03-28
* @version 1.0
* @Description
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AppServiceTest{
	@Autowired
	IAppService appService;
	
	@Autowired
	ILibFileService libService;
	

	@Test
	public void testBaseFunctionOfAppService() throws Exception {
		App app=new App();
		app.setActionClass("com.logi.MyClass");
		app.setAppName("CustomApp");
		app.setAppPath("aa/bb/cc");
		app.setUserid(100);
		app.setCreated(new Date());
		appService.save(app);
		assertThat(app.getAppName()+" failed to save",app.getId(), greaterThan(0));

		App app2=appService.get(app.getId());
		assertNotNull("Get App id: "+app.getId()+"failed",app2);
		
		
		LibFile lib=new LibFile();
		lib.setAppId(app2.getId());
		lib.setFileName("abc.xlsx");
		lib.setName("000001.xlsx");
		lib.setFilesize(512);
		lib.setUserid(99);
		lib.setCreated(new Date());
		
		
		LibFile lib2=new LibFile();
		lib2.setAppId(app2.getId());
		lib2.setFileName("bbc.xlsx");
		lib2.setName("000002.xlsx");
		lib2.setFilesize(1024);
		lib2.setUserid(99);
		lib2.setCreated(new Date());
		
		libService.save(lib);
		assertThat(lib.getFileName()+" failed to save",lib.getId(), greaterThan(0));
		libService.save(lib2);
		assertThat(lib2.getFileName()+" failed to save",lib2.getId(), greaterThan(0));
		
		appService.delete(app.getId());
		App app3=appService.get(app.getId());
		assertNull("App id: "+app.getId()+" failed to delete",app3);
		
		libService.delete(lib.getId());
		libService.delete(lib2.getId());
	}
	
}
