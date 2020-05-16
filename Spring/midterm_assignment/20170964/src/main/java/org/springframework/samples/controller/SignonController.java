package org.springframework.samples.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignonController {

	@Autowired
	private Authenticator authenticator;
	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator = authenticator;
	}
	
	@RequestMapping("/members/signon")
	public String submit(@Valid @ModelAttribute("signonCommand") SignonCommand signonCommand,
			BindingResult result, HttpSession session, Model model,
			@RequestParam(value="forwardAction", required=false) String forwardAction) {
		model.addAttribute("signonCommand", signonCommand);
		model.addAttribute("signonForwardAction", forwardAction);
		if (result.hasErrors())
			return "member/signonForm";
		
		try {
			authenticator.authenticate(signonCommand.getId(), signonCommand.getPassword()); // password가 일치하지 않을 때
			session.setAttribute("signonCommand", signonCommand);
		} catch (AuthenticationException ex) {
			result.reject("invalidIdOrPassword", new Object[] {signonCommand.getId()}, null);
			return "member/signonForm";
		}
		
		return "redirect:" + forwardAction;	
	}


}
