package com.java.moudle.common.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.java.moudle.common.message.JsonResult;
import com.java.until.UUIDUtil;
import com.java.until.ftpup.UpUtils;

/**
 * <br>
 * <b>功能：</b>CustomerController<br>
 * <b>作者：</b>blt<br>
 * <b>版权所有：<b>版权所有(C) 2016，blt<br>
 */
@RestController
@RequestMapping("commontools")
public class CommonToolsController extends BaseController {

	@Value("${ftpUrl}")
    private String ftpUrl;

    /**
	 * @Description: 上传图片，保存到ftp上
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("saveImage")
	public JsonResult saveImage(HttpServletRequest request, HttpServletResponse response) {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("fileData");
			if(file != null) {
				String originalName = file.getOriginalFilename();
				String puf = originalName.substring(originalName.lastIndexOf("."));
				String pngName = UUIDUtil.getUUID()+puf;
				//ftp上传
				boolean upload = UpUtils.upload(file.getInputStream(), "/certificate/", pngName);
				if(upload) {
					return jsonResult(ftpUrl+"/certificate/"+pngName);
				}else {
					return jsonResult(null, 10000, "上传的文件失败");
				}
			}else {
				return jsonResult(null, 10000, "请选择上传的文件");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误"); 
		}
	}
	
}
