package com.xin.easyextension.core;

import java.util.Arrays;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2018-03-26
* @version 1.0
* @Description
*/
public class ClassInfo {
	private String thisClass;
	private String superClass;
	private String interfaceName [];
	
	
	public String getThisClass() {
		return thisClass;
	}
	public void setThisClass(String thisClass) {
		this.thisClass = thisClass;
	}
	public String getSuperClass() {
		return superClass;
	}
	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}
	public String[] getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String[] interfaceName) {
		this.interfaceName = interfaceName;
	}
	
	@Override
	public String toString() {
		return "ClassInfo [thisClass=" + thisClass + ", superClass=" + superClass + ", interfaceName="
				+ Arrays.toString(interfaceName) + "]";
	}
	
	
}
