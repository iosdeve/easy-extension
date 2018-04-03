package com.xin.easyextension.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.log4j.Logger;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2017-12-14
* @version 1.0
* @Description
*/
public class StandaloneClassLoader extends URLClassLoader {
	static Logger LOG=Logger.getLogger(StandaloneClassLoader.class);
	
	final static String BASE_DIR = System.getProperty("user.dir") + File.separator + "extensions" + File.separator;

	public StandaloneClassLoader(String extensionName) {
		super(new URL[] {}, null);
		tryLoadJarInDir(BASE_DIR+extensionName);
	}

	
	@Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        LOG.debug("load class "+name);
        if(name.equals(IExtension.class.getName())){
        	return IExtension.class;
        }
        return super.loadClass(name);
    }
	
	@Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
        	
            return super.findClass(name);
        } catch(ClassNotFoundException e) {
            return StandaloneClassLoader.class.getClassLoader().loadClass(name);
        }
    }
	
	private void tryLoadJarInDir(String dirPath) {
        File dir = new File(dirPath);
        // 自动加载目录下的jar包
        if (dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".jar")) {
                    this.addURL(file);
                }
            }
        }
    }

    private void addURL(File file) {
        try {
            super.addURL(new URL("file", null, file.getCanonicalPath()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
