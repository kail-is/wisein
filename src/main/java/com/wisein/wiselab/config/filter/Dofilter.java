package com.wisein.wiselab.config.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@WebFilter(urlPatterns= "*")
public class Dofilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(new RequestHeaderWrapper((HttpServletRequest)request), response);
	}
	
	public static class RequestHeaderWrapper extends HttpServletRequestWrapper  {

		public RequestHeaderWrapper(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			
			String value = null;
			if(name == null) return null;
			switch (name) {
			case "viewAddr":
				value = super.getRequestURI();
				break;
			default:
				value = super.getParameter(name);
				break;
			}
			return value;
		}
		
		@Override
		public Map<String, String[]> getParameterMap() {
			
			Map<String, String[]> map = new HashMap<String, String[]>(super.getParameterMap());
			String[] addr = {super.getRequestURI()};
			map.put("viewAddr",addr );
			return map;
		}

		@Override
		public Enumeration<String> getParameterNames() {
			return new Enumeration<String>() {
				private String[] arr = (String[])(getParameterMap().keySet().toArray(new String[0]));
				private int index = 0;
				@Override
				public boolean hasMoreElements() {
					return index < arr.length;
				}
				@Override
				public String nextElement() {
					return arr[index++];
				}
			};
		}

		@Override
		public String[] getParameterValues(String name) {
			
			if(name == null) return null;
			switch (name) {
			case "viewAddr":
				String[] addr = {super.getRequestURI()};
				return addr;
			default:
				 return super.getParameterValues(name);
			}
		}
		
		
	}

}
