package org.springframework.samples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.model.MemberInfo;
import org.springframework.samples.service.MemberService;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatorImpl implements Authenticator {
	
	private MemberService memberService;
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public void authenticate(String id, String password) {
		MemberInfo memberInfo = memberService.getMemberInfoByEmail(id);
		if (!memberInfo.matchPassword(password)) { // password가 일치하지 않을 때
			throw new AuthenticationException("invalid id "+id);
		}
	}

}
