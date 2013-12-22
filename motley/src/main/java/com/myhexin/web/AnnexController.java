package com.myhexin.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhexin.entity.PermissionDTO;
import com.myhexin.entity.TResource;
import com.myhexin.entity.TResourceTreeDTO;
import com.myhexin.entity.User;
import com.myhexin.persistent.Page;
import com.myhexin.service.UserService;

/**
 * 附件上传下载 
 * @author admin 2013-3-5 上午09:50:56
 */
@Controller
@RequestMapping("/annex")
public class AnnexController {

	@RequestMapping(method=RequestMethod.GET,value="/download")
	public void download(@RequestParam("fileName")  
		    String fileName, HttpServletRequest request, HttpServletResponse respons) throws IOException{
		OutputStream  os = null;
		InputStream is = null;
		try{
			
			is = (this.getClass().getClassLoader().getResourceAsStream(fileName));

			int fileLength = 0;
			os = respons.getOutputStream();
			byte[] bs = new byte[1024];
			int count = 0;
			while((count=is.read(bs))!=-1){
				os.write(bs);
				fileLength = fileLength+count;
			}
			System.out.println(fileLength);
			respons.setContentType("application/x-msdownload;");
			respons.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			respons.setHeader("Content-Length", String.valueOf(fileLength));
			

		}catch(Exception e){
			System.out.println(e);
		}finally{
			os.close();
			is.close();
		}
	}
	
}
