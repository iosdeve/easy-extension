package com.xin.easyextension.core;

import org.apache.log4j.Logger;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2017-12-14
* @version 1.0
* @Description
*/
public class ExecuteExtension {
	static Logger LOG=Logger.getLogger(ExecuteExtension.class);
	private StandaloneClassLoader classLoader;
	
	public void execute(String extensionName, String className) throws Exception {
		ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
		classLoader = new StandaloneClassLoader(extensionName);
		//临时更改 ClassLoader
        Thread.currentThread().setContextClassLoader(classLoader);
        Class<IExtension> extensionCls=(Class<IExtension>) classLoader.loadClass(className);
		IExtension extension=extensionCls.newInstance();
		extension.doAction();
		Thread.currentThread().setContextClassLoader(currentClassLoader);
	}
}
