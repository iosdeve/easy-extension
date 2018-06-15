package com.xin.easyextension.service.imp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xin.easyextension.common.CommonUtil;
import com.xin.easyextension.common.MultipartFileParam;
import com.xin.easyextension.core.ClassInfo;
import com.xin.easyextension.core.ExecuteExtension;
import com.xin.easyextension.core.IDecompressFileHandler;
import com.xin.easyextension.core.JARDecompressionTool;
import com.xin.easyextension.domain.App;
import com.xin.easyextension.domain.LibFile;
import com.xin.easyextension.domain.NodeTable;
import com.xin.easyextension.mapper.AppMapper;
import com.xin.easyextension.mapper.LibFileMapper;
import com.xin.easyextension.mapper.NodeTableMapper;
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
	Logger LOG=LoggerFactory.getLogger(AppService.class);
	@Autowired
	AppMapper appDao;
	
	@Autowired
	LibFileMapper fileDao;
	
	@Autowired
	NodeTableMapper nodetableDao;

	@Override
	public int save(App app) throws IOException {
		LOG.info("save app:"+app);
		app.setCreated(new Date());
		app.setAppPath("extensions/"+app.getAppName());
		int row=appDao.insert(app);
		for(LibFile lib : app.getLibs()) {
			lib.setAppId(app.getId());
			lib.setCreated(new Date());
			fileDao.insert(lib);
			
			for(NodeTable nodeTable : lib.getExecuteClasses()) {
				nodeTable.setReferId(lib.getId());
				nodeTable.setCreated(new Date());
				nodetableDao.insert(nodeTable);
			}
		}
		File folder=new File("extensions/"+app.getAppName());
		if(!folder.exists()) {
			folder.mkdirs();
		}
		for(LibFile lib : app.getLibs()) {
			String path="extensions/"+app.getAppName()+"/"+lib.getFileName();
			File file=new File(path);
			File srcFile=new File("upload/"+lib.getName());
			FileUtils.copyFile(srcFile, file);
		}
		return row;
	}

	@Override	
	public int update(App app) throws IOException {
		app.setLastUpdated(new Date());
		app.setAppPath("extensions/"+app.getAppName());
		int updateCount=appDao.updateByPrimaryKey(app);
		if(updateCount==0) {
			LOG.debug("app id:"+app.getId()+ " not found, can't update");
			throw new RuntimeException("数据更新失败：Id"+app.getId());
		}
		
		List<LibFile> noChangeLibs=new ArrayList<>();
		List<LibFile> oldLibs=get(app.getId()).getLibs();
		
		for(LibFile lib : app.getLibs()) {
			if(lib.getId()!=null && lib.getId()>0) {
				for(LibFile lf : oldLibs) {
					if(lf.getId()==lib.getId()) {
						noChangeLibs.add(lf);
					}
				}
				continue;
			}
			lib.setAppId(app.getId());
			lib.setCreated(new Date());
			fileDao.insert(lib);
			
			for(NodeTable nodeTable : lib.getExecuteClasses()) {
				nodeTable.setReferId(lib.getId());
				nodeTable.setCreated(new Date());
				nodetableDao.insert(nodeTable);
			}
			
			String path="extensions/"+app.getAppName()+"/"+lib.getFileName();
			File file=new File(path);
			File srcFile=new File("upload/"+lib.getName());
			FileUtils.copyFile(srcFile, file);
		}
		oldLibs.removeAll(noChangeLibs);
		for(LibFile lf : oldLibs) {
			fileDao.deleteByPrimaryKey(lf.getId());
			for(NodeTable nt : lf.getExecuteClasses()) {
				nodetableDao.deleteByPrimaryKey(nt.getId());
			}
			String path="extensions/"+app.getAppName()+"/"+lf.getFileName();
			CommonUtil.deleteFile(new File(path));
		}
		return updateCount;
	}

	@Override
	public int delete(Integer id) {
		App app=appDao.selectByPrimaryKey(id);
		nodetableDao.deleteByReferId(id);
		fileDao.deleteByAppId(id);
		int affectedRow=appDao.deleteByPrimaryKey(id);
		if(affectedRow>0) {
			String filePath=app.getAppPath();
			File appFile=new File(filePath);
			if(appFile.exists()) {
				CommonUtil.deleteFile(appFile);
			}
		}
		return affectedRow;

	}

	@Override
	public App get(Integer id) {
		return appDao.selectByPrimaryKey(id);

	}
	
	public LibFile uploadLibFile(MultipartFileParam uploadParam) throws IOException {
		String fileName=uploadParam.getFileName();
		int pointIndex=fileName.lastIndexOf(".");
		String extension=fileName.substring(pointIndex, fileName.length());
		String name=CommonUtil.getID()+extension;
		File uploadFile=new File("upload/"+name);
		FileUtils.writeByteArrayToFile(uploadFile, uploadParam.getFileItem().get());
		LibFile lib=new LibFile();
		lib.setFileName(fileName);
		lib.setName(name);
		lib.setFilePath(uploadFile.getPath());
		int fileSize=(int)uploadParam.getFileItem().getSize();
		lib.setFilesize(fileSize);
		lib.setCreated(new Date());
		if(fileName.endsWith("jar")) {
			List<String> clsNames=findExecuteClassFile(uploadFile.getAbsolutePath(), "com/xin/easyextension/core/IExtension");
			for(String cls : clsNames) {
				NodeTable nt=new NodeTable();
				nt.setName(cls);
				lib.getExecuteClasses().add(nt);
			}
		}
		return lib;
	}
	
	public List<String> findExecuteClassFile(String jarFilePath, String targetInterface) {
		List<String> classNames=new ArrayList<>();
		JARDecompressionTool.decompressClassFile(jarFilePath, new IDecompressFileHandler() {
			@Override
			public void decompressFile(String fileName, ClassInfo classInfo) {
				if(classInfo!=null) {
					String [] interfaces=classInfo.getInterfaceName();
					for(String itf : interfaces) {
						if(itf.equals(targetInterface)) {
							classNames.add(fileName.replaceAll("/", "."));
							break;
						}
					}
				}
			}
		});
		return classNames;
	}

	
	public List<App> queryAllApps(){
		return appDao.selectAll();
	}
	
	public void runApp(Integer id) {
		App app=appDao.selectByPrimaryKey(id);
		ExecuteExtension executeExt=new ExecuteExtension();
		try {
			executeExt.execute(app.getAppName(),app.getActionClass().replaceAll("\\.class", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
