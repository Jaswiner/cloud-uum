package com.jaswine.uum.filter;//package com.lanswon.uum.filter;
//
//import com.lanswon.commons.core.json.JacksonUtil;
//import com.lanswon.commons.core.password.PasswordRegex;
//import com.lanswon.commons.web.wrapper.CustomRequestWrapper;
//import com.lanswon.UserPojo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * 新增用户Filter
// *
// * @author : Jaswine
// * @date : 2020-05-05 14:33
// **/
//@Slf4j
//@Component
//@WebFilter(urlPatterns = "/user/",filterName = "userAddFilter")
//public class UserAddFilter implements Filter {
//
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		log.info("Register UserAddFilter...");
//	}
//
//	@Override
//	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//		CustomRequestWrapper requestWrapper = new CustomRequestWrapper((HttpServletRequest) servletRequest);
//		UserPojo user = JacksonUtil.json2Bean(requestWrapper.getBodyString(), UserPojo.class);
//
//		// 1.校验密码是否符合规范
//		//if (!user.getPassword().matches(PasswordRegex.CHAR_NUM_8)){
//		//	log.info("密码不符合规范");
//		//
//		//}
//		// 2.校验用户名是否重复
//		log.info(user.toString());
//
//		filterChain.doFilter(requestWrapper,servletResponse);
//	}
//
//	@Override
//	public void destroy() {
//
//	}
//}
