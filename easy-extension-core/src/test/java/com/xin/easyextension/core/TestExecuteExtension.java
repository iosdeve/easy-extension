package com.xin.easyextension.core;

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
}