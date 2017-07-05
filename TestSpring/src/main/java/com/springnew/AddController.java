package com.springnew;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddController {
	
	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest req, HttpServletResponse res)
	//public String add(HttpServletRequest req, HttpServletResponse res)
	{
		int i = Integer.parseInt(req.getParameter("par1"));
		int j = Integer.parseInt(req.getParameter("par2"));
		int k = i+j;
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("display.jsp");
		mv.addObject("result",k);
		return mv;
		//return "Hello";
	}

}
