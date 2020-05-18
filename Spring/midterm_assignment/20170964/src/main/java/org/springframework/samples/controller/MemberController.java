package org.springframework.samples.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.model.MemberInfo;
import org.springframework.samples.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/index")
	public String members(Model model) {
		List<MemberInfo> members = memberService.getMembers();
		model.addAttribute("members", members);
		return "index";
	}

	@RequestMapping("/members/detail")
	public String memberDetail(@RequestParam String email, Model model) {
		MemberInfo mi = memberService.getMemberInfoByEmail(email);
		if (mi == null) {
			return "member/memberNotFound";
		}
		model.addAttribute("member", mi);
		return "member/memberDetail";
	}

	@RequestMapping("/members/delete")
	public String memberDelete(@RequestParam String email, HttpSession session) {
		MemberInfo mi = memberService.getMemberInfoByEmail(email);
		if (mi == null) {
			return "member/memberNotFound";
		}
		memberService.deleteMember(mi.getId());
		session.invalidate();
		return "redirect:/index";
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

}
