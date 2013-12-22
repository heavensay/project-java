package com.myhexin.web;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhexin.entity.PctMetal;
import com.myhexin.service.ProductService;


/**
 * 
 */
@Controller
@RequestMapping("/product")
public class ProductController  extends BaseController{

	@Autowired
	ProductService productService;

	/**
	 * 理财专区->贵金属
	 * @return
	 */
	@RequestMapping(value = "/queryPctMetal",method=RequestMethod.GET)
	@ResponseBody
	public List<PctMetal> queryPctMetal(String pctcode){
		List<PctMetal> list = productService.queryPctMetal(pctcode);
		return list;
	}
	
	/**
	 * 理财专区->活动列表
	 * @return
	 */
	@RequestMapping(value = "/insertPctMetal",method=RequestMethod.GET)
	@ResponseBody
	public String insertPctMetal(String pctcode,String pctname){
		PctMetal pctMetal = new PctMetal();
		pctMetal.setPctcode("code1");
		pctMetal.setPctcode("name1");
		pctMetal.setInputtime(new Timestamp(System.currentTimeMillis()));
		
		productService.insertPctMetal(pctMetal);
		return "insert pctmetal ok";
	}
	
}
