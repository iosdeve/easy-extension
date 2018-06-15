package com.xin.easyextension.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xin.easyextension.common.MultipartFileParam;
import com.xin.easyextension.domain.App;
import com.xin.easyextension.domain.LibFile;
import com.xin.easyextension.service.IAppService;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2018-04-04
* @version 1.0
* @Description
*/

@Controller
public class AppController {

	@Autowired
	IAppService appService;
	
	
	@RequestMapping("uploadfile")
	@ResponseBody
	public Object uploadFile(HttpServletRequest request) throws Exception {
		MultipartFileParam param=MultipartFileParam.parse(request);
		LibFile lib=appService.uploadLibFile(param);
		return  JSONObject.toJSON(lib);
	}
	
	@RequestMapping("saveApp2")
	@ResponseBody
	public Object saveApp2(@RequestBody @Validated App app, BindingResult br) {
		if(br.hasErrors()) {
			List<String> errorMessage=new ArrayList<String>();
			List<FieldError> fielderrors=br.getFieldErrors();
			for(FieldError error : fielderrors) {
				errorMessage.add(error.getDefaultMessage());
			}
			Map<String, List<String>> errorMap=new HashMap<String,List<String>>();
			errorMap.put("error", errorMessage);
			return JSONObject.toJSON(errorMap);
		}
		String str=JSONObject.toJSONString(app);
		try {
			if(app.getId()!=null && app.getId()>0) {
				appService.update(app);
			}else {
				appService.save(app);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(str);
		return JSONObject.toJSON(app);
	}
	
	@RequestMapping("apps")
	@ResponseBody
	public Object allApps() {
		List<App> apps=appService.queryAllApps();
		return JSONObject.toJSON(apps);
	}
	
	@RequestMapping("app/{id}")
	@ResponseBody
	public Object getApp(@PathVariable int id) {
		App app=appService.get(id);
		return JSONObject.toJSON(app);
	}
	
	@RequestMapping("runapp/{id}")
	@ResponseBody
	public Object runApp(@PathVariable int id) {
		appService.runApp(id);
		return JSONObject.toJSON(id);
	}
	
	@RequestMapping("deleteapp/{id}")
	@ResponseBody
	public Object deleteApp(@PathVariable int id) {
		int rowId=0;
		if( id>0) {
			rowId=appService.delete(id);
		}
		return JSONObject.toJSON(rowId);
	}
	
	@RequestMapping("test")
	@ResponseBody
	public Object test(@Validated App app, BindingResult br) {
		System.out.println(app);
		List<ObjectError> errors=br.getAllErrors();
		List<FieldError> fielderrors=br.getFieldErrors();
		for(ObjectError error : errors) {
			System.out.println(error.getObjectName());
			System.out.println(error.getDefaultMessage());
		}

		for(FieldError error : fielderrors) {
			
			System.out.println(error.getDefaultMessage());
			System.out.println(error.getField());
		}
		return "succ";

	}
}

