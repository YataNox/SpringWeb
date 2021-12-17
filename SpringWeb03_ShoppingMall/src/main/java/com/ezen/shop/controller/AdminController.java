package com.ezen.shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.shop.dto.Paging;
import com.ezen.shop.dto.ProductVO;
import com.ezen.shop.service.AdminService;
import com.ezen.shop.service.ProductService;
import com.ezen.shop.service.QnaService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class AdminController {
	@Autowired
	AdminService as;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	ProductService ps;
	
	@Autowired
	QnaService qs;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String admin() {
		return "admin/adminLoginForm";
	}
	
	@RequestMapping(value="/adminLogin")
	public ModelAndView adminLogin(HttpServletRequest request, @RequestParam("workId") String workId,
			@RequestParam("workPwd") String workPwd) {
		ModelAndView mav = new ModelAndView();
		int result = as.workerCheck(workId, workPwd);
		
		if(result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("workId", workId);
			mav.setViewName("redirect:/productList");
		}else if(result == 0) {
			mav.addObject("message", "비밀번호를 확인하세요.");
			mav.setViewName("admin/adminLoginForm");
		}else if(result == -1) {
			mav.addObject("message", "아이디를 확인하세요.");
			mav.setViewName("admin/adminLoginForm");
		}else {
			mav.addObject("message", "알수없는 이유로 로그인이 실패.");
			mav.setViewName("admin/adminLoginForm");
		}
		return mav;
	}
	
	@RequestMapping(value="/productList")
	public ModelAndView product_list(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		if(id == null)
			mav.setViewName("redirect:/admin");
		else {
			int page = 1;
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			}else if(session.getAttribute("page") != null) {
				page = (Integer)session.getAttribute("page");
			}else {
				page = 1;
				session.removeAttribute("page");
			}
			
			String key = "";
			if(request.getParameter("key") != null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			}else if(session.getAttribute("key") != null) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			}
			
			Paging paging = new Paging();
			paging.setPage(page);
			
			int count = as.getAllCount("product", "name", key);
			paging.setTotalCount(count);
			
			List<ProductVO> productList = as.listProduct(paging, key);
			
			request.setAttribute("paging", paging);
			request.setAttribute("key", key);
			mav.addObject("productList", productList);
			mav.setViewName("admin/product/productList");
		}
		return mav;
	}
	
	@RequestMapping(value="/adminProductDetail")
	public ModelAndView product_detail(HttpServletRequest request, @RequestParam("pseq") int pseq) {
		ModelAndView mav = new ModelAndView();
		
		ProductVO pvo = ps.getProduct(pseq);
		String kindList[] = {"0", "Heels", "Boots", "Sandals", "Slipers", "Shckers", "Sale"};
		int index = Integer.parseInt(pvo.getKind());
		
		mav.addObject("productVO", pvo);
		mav.addObject("kind", kindList[index]);
		mav.setViewName("admin/product/productDetail");
		
		return mav;
	}
	
	@RequestMapping(value="/productWriteForm")
	public ModelAndView product_wirte_form(HttpServletRequest request) {
		String kindList[] = {"Heels", "Boots", "Sandals", "Slipers", "Shckers", "Sale"};
		ModelAndView mav = new ModelAndView();
		mav.addObject("kindList", kindList);
		mav.setViewName("admin/product/productWriteForm");
		return mav;
	}
	
	@RequestMapping(value="/productWrite", method=RequestMethod.POST)
	public String product_write(HttpServletRequest request) {
		String savePath = context.getRealPath("resources/product_images");
		
		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			ProductVO pvo = new ProductVO();
			pvo.setKind(multi.getParameter("kind"));
			pvo.setName(multi.getParameter("name"));
			pvo.setContent(multi.getParameter("content"));
			pvo.setImage(multi.getFilesystemName("image"));
			pvo.setPrice1(Integer.parseInt(multi.getParameter("price1")));
			pvo.setPrice2(Integer.parseInt(multi.getParameter("price2")));
			pvo.setPrice3(Integer.parseInt(multi.getParameter("price3")));
			as.insertProduct(pvo);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return "redirect:/productList";
	}
	
	@RequestMapping(value="/productUpdateForm")
	public ModelAndView product_Update_form(HttpServletRequest request, @RequestParam("pseq") int pseq) {
		String kindList[] = {"Heels", "Boots", "Sandals", "Slipers", "Shckers", "Sale"};
		ModelAndView mav = new ModelAndView();
		ProductVO pvo = ps.getProduct(pseq);
		
		mav.addObject("productVO", pvo);
		mav.addObject("kindList", kindList);
		mav.setViewName("admin/product/productUpdate");
		return mav;
	}
	
	@RequestMapping(value="/productUpdate", method=RequestMethod.POST)
	public String product_update(HttpServletRequest request) {
		String savePath = context.getRealPath("resources/product_images");
		int pseq = 0;
		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			ProductVO pvo = new ProductVO();
			pseq = Integer.parseInt(multi.getParameter("pseq"));
			pvo.setPseq(pseq);
			pvo.setKind(multi.getParameter("kind"));
			pvo.setName(multi.getParameter("name"));
			pvo.setContent(multi.getParameter("content"));
			pvo.setPrice1(Integer.parseInt(multi.getParameter("price1")));
			pvo.setPrice2(Integer.parseInt(multi.getParameter("price2")));
			pvo.setPrice3(Integer.parseInt(multi.getParameter("price3")));
			pvo.setBestyn(multi.getParameter("bestyn"));
			pvo.setUseyn(multi.getParameter("useyn"));
			String image = multi.getFilesystemName("image");
			if(image == null) {
				image = multi.getParameter("oldImage");
			}
			pvo.setImage(image);
			as.updateProduct(pvo);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return "redirect:/adminProductDetail?pseq=" + pseq;
	}
}
