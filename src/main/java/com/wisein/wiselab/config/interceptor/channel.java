package com.wisein.wiselab.config.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class channel implements HandlerInterceptor {


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		String[] url = request.getRequestURI().split("/");
		try {

			EsideYn a = EsideYn.valueOf(url[url.length - 1]);
			if(modelAndView != null){
				modelAndView.addObject("side_gubun", a.getSideYn());
			}else{
				//response.setHeader("side_gubun", a.getSideYn());
			}
		}catch (Exception e){
			System.out.println(e.toString());
		}
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}

