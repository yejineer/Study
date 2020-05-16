package org.springframework.samples.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

public class SignonInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
			throws Exception {
		SignonCommand signonCommandSession= 
			(SignonCommand) WebUtils.getSessionAttribute(request, "signonCommand");
		if (signonCommandSession == null || !signonCommandSession.getId().equals(request.getParameter("email"))) {
			
			String url = request.getRequestURL().toString(); 
			String query = request.getQueryString();
			ModelAndView modelAndView = new ModelAndView("member/signonForm");

			SignonCommand signonCommand = new SignonCommand();
			signonCommand.setId(request.getParameter("email"));
			modelAndView.addObject("signonCommand", signonCommand);

			if (query != null) {
				modelAndView.addObject("signonForwardAction", url+"?"+query); //로그인하고 어디로 가야할지. (log-in)이 필요한 기능들이 많으니까
				System.out.println("url+\"?\"+query: " + url+"?"+query);
			}
			else {
				modelAndView.addObject("signonForwardAction", url);
				System.out.println("url+\"?\"+query: " + url);
			}
			throw new ModelAndViewDefiningException(modelAndView);
		}
		else {
			return true;
		}
	}
}
