package com.xin.easyextension.core;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.tinylcy.ClassFile;
import org.tinylcy.ClassReader;
import org.tinylcy.constantpool.ConstantPool;

public class JARDecompressionTool {
	
	public static void decompressClassFile(String jarFile, IDecompressFileHandler compressFileHandler) {
		JarFile jf = null;
		try{
			jf =  new JarFile(jarFile);
			for (Enumeration<JarEntry> e = jf.entries(); e.hasMoreElements();) {
                JarEntry je = (JarEntry) e.nextElement();
                if(compressFileHandler!=null) {
                	String fileName=je.getName();
                	boolean isClsFile=fileName.endsWith("class");
                	if(isClsFile==false) {
                		continue;
                	}
                	InputStream in = jf.getInputStream(je);
                	ClassFile classFile = ClassReader.read(in);
        	        ConstantPool constantPool = new ConstantPool(classFile.constantPoolCount.getValue());
        	        constantPool.setCpInfo(classFile.cpInfo);
        	        ClassInfo classInfo=new ClassInfo();
        	        classInfo.setThisClass(ClassReader.getConstantClassInfoValue(constantPool, classFile.thisClass.getValue()));
        	        classInfo.setSuperClass(ClassReader.getConstantClassInfoValue(constantPool, classFile.superClass.getValue()));
        	        int arrayCount=classFile.interfacesCount.getValue();
        	        String interfaceName[]=new String[arrayCount];
        	        for (int i = 0; i < classFile.interfacesCount.getValue(); i++) {
        	        	interfaceName[i]=ClassReader.getConstantClassInfoValue(constantPool, classFile.interfaces[i].getValue());
        	        }
        	        classInfo.setInterfaceName(interfaceName);
                	compressFileHandler.decompressFile(fileName, classInfo);
                	in.close();
                }
            }  
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(jf!=null){  
                try {  
                    jf.close();            
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
		}
	}
    /** 
     * 解压并删除jar文件 
     */  
    public static synchronized void decompress(String fileName,String outputPath){  
          
            if (!outputPath.endsWith(File.separator)) {  
                outputPath += File.separator;  
            }  
            File dir = new File(outputPath);  
            if (!dir.exists()) {  
                dir.mkdirs();  
            }  
            JarFile jf = null;  
        try{  
            jf =  new JarFile(fileName);  
            for (Enumeration<JarEntry> e = jf.entries(); e.hasMoreElements();) {  
                JarEntry je = (JarEntry) e.nextElement();  
                String outFileName = outputPath + je.getName();  
                File f = new File(outFileName);  
                if(je.isDirectory()){  
                    if(!f.exists()){  
                        f.mkdirs();  
                    }  
                }else{  
                    File pf = f.getParentFile();  
                    if(!pf.exists()){  
                        pf.mkdirs();  
                    }  
                    InputStream in = jf.getInputStream(je);  
                    OutputStream out = new BufferedOutputStream(  
                            new FileOutputStream(f));  
                    byte[] buffer = new byte[2048];  
                    int nBytes = 0;  
                    while ((nBytes = in.read(buffer)) > 0) {  
                        out.write(buffer, 0, nBytes);  
                    }  
                    out.flush();  
                    out.close();  
                    in.close();  
                }  
            }  
        }catch(Exception e){  
            System.out.println("解压"+fileName+"出错---"+e.getMessage());  
        }finally{  
            if(jf!=null){  
                try {  
                    jf.close();  
                    File jar = new File(jf.getName());  
                    if(jar.exists()){  
                        jar.delete();  
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
}  