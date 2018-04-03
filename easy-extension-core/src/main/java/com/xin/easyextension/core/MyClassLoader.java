package com.xin.easyextension.core;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyClassLoader extends ClassLoader {

    private String root;

    protected Class<?> findClass(String name) throws ClassNotFoundException {
    	System.out.println("find class "+name);
        byte[] classData = loadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] loadClassData(String className) {
        String fileName = root + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
        try {
            InputStream ins = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = 0;
            while ((length = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }
    
    public static String readUserInput(String prompt) throws IOException {
        //先定义接受用户输入的变量  
        String result;  
         // 输出提示文字  
        System.out.print(prompt);  
        InputStreamReader is_reader = new InputStreamReader(System.in);  
        result = new BufferedReader(is_reader).readLine();  
        return result;
   }  

    public static void main(String[] args){
        try {
        	Class<?> last=null;
        	while(true){
        		MyClassLoader classLoader = new MyClassLoader();
        	    classLoader.setRoot("extensions\\class");
    			String result=readUserInput("请输入:");
    			Class<?> testClass = classLoader.loadClass("com.logitech.CustomeAction2");
    			System.out.println(testClass.getClassLoader());
    			if(testClass==last) {
    				System.out.println("class eq");
    			}
    			last=testClass;
                Object object = testClass.newInstance();
                System.out.println(object);
        	}
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}