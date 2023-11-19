package com.myhexin.web;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myhexin.entity.PermissionDTO;
import com.myhexin.entity.TResource;
import com.myhexin.entity.TResourceTreeDTO;
import com.myhexin.entity.User;
import com.myhexin.persistent.Page;
import com.myhexin.service.UserService;

/**
 * 类xss漏洞模拟服务：内部用户WEB层
 */
@Controller
@RequestMapping("/xss")
public class XssController {


    /**
     * @return
     */
    @RequestMapping(value = "/jumpXssView", method = RequestMethod.GET)
//	@ResponseBody
    public ModelAndView jumpXssView(String content) {
        HashMap map = new HashMap();
        map.put("content", content);
        System.out.println(content + "=======");
        ModelAndView mv = new ModelAndView("redirect:/page/xss-test.jsp", map);
        return mv;
    }

    /**
     * @return
     */
    @RequestMapping(value = "/jumpForwardXssView", method = RequestMethod.GET)
//	@ResponseBody
    public ModelAndView jumpForwardXssView(String content) {
        HashMap map = new HashMap();
        map.put("content", content);
        System.out.println(content + "=======");
        ModelAndView mv = new ModelAndView("/page/xss-test", map);
        return mv;
    }

    /**
     * @return
     */
    @RequestMapping(value = "/xssout", method = RequestMethod.GET)
    @ResponseBody
    public String xssout(String content) {
        System.out.println(content + "===xssout===");
        return content + "天下abc";
    }


    /**
     * @return
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String xssout(/*String fileNames,*/ HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();

        String uploadDir = request.getSession().getServletContext()
                .getRealPath("/")
                + "uploadtmp";
        File file = new File(uploadDir);

        if (!file.exists()) {
            file.mkdir();
        }

        String fileName = null;
        int i = 0;
        for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet()
                .iterator(); it.hasNext(); i++) {

            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();

            fileName = mFile.getOriginalFilename();

//            String storeName = fileName;
//            String noZipName = uploadDir + storeName;  
//            String zipName = zipName(noZipName);  

            // 上传成为压缩文件  
//            ZipOutputStream outputStream = new ZipOutputStream(  
//                    new BufferedOutputStream(new FileOutputStream(zipName)));  
//            outputStream.putNextEntry(new ZipEntry(fileName));
            FileOutputStream outputStream = new FileOutputStream(new File(uploadDir + "/" + fileName));
//            outputStream.putNextEntry(new ZipEntry(fileName));  
//            outputStream.setEncoding("GBK");  

            FileCopyUtils.copy(mFile.getInputStream(), outputStream);

//            Map<String, Object> map = new HashMap<String, Object>();  
//            // 固定参数值对  
//            map.put(FileOperateUtil.REALNAME, zipName(fileName));  
//            map.put(FileOperateUtil.STORENAME, zipName(storeName));  
//            map.put(FileOperateUtil.SIZE, new File(zipName).length());  
//            map.put(FileOperateUtil.SUFFIX, "zip");  
//            map.put(FileOperateUtil.CONTENTTYPE, "application/octet-stream");  
//            map.put(FileOperateUtil.CREATETIME, new Date());  
//  
//            // 自定义参数值对  
//            for (String param : params) {  
//                map.put(param, values.get(param)[i]);  
//            }  
//  
//            result.add(map);  
//        }  
//        return result;  
        }
        return "upload ok";
    }
}
