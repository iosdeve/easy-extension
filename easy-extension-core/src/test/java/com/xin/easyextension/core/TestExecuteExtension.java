package com.xin.easyextension.core;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2017-12-14
* @version 1.0
* @Description
*/
public class TestExecuteExtension {

	@Test
	public void testExcuteExtension() {
		ExecuteExtension executeExt=new ExecuteExtension();
		try {
			executeExt.execute("app1","com.logitech.CustomeExtension");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDecompressJar() {
		boolean find []= {false};
		ClassInfo clsInfo[]=new ClassInfo[1];
		String targetFileName="com/logitech/blackpearl/BlackPearlEventAction.class";
		JARDecompressionTool.decompressClassFile("extensions/blackpearl.jar", new IDecompressFileHandler() {
			
			@Override
			public void decompressFile(String fileName, ClassInfo classInfo) {
				if(fileName.endsWith("class")) {
					if(targetFileName.equals(fileName)) {
						find[0]=true;
						clsInfo[0]=classInfo;
					}	
				}
			}
		});
		Assert.assertTrue("Not found class:"+targetFileName, find[0]);
		Assert.assertEquals(targetFileName+ "interface count should be 2", 2, clsInfo[0].getInterfaceName().length);
		System.out.println(clsInfo[0]);
		
	}
	
	@Test
	public void testFindspecificClass() {
		List<String> interfaceCls=new ArrayList<String>();
		JARDecompressionTool.decompressClassFile("extensions/app1/ext.jar", new IDecompressFileHandler() {
			
			@Override
			public void decompressFile(String fileName, ClassInfo classInfo) {
				if(classInfo!=null) {
					String [] interfaces=classInfo.getInterfaceName();
					for(String itf : interfaces) {
						interfaceCls.add(itf);
					}
				}
			}
		});
		
		assertThat("interface count greater than 0",interfaceCls.size(), greaterThan(0));
		Assert.assertThat("No any class instance of IExtension",interfaceCls, Matchers.hasItem("com/xin/easyextension/core/IExtension"));
	}
}