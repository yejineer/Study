package org.springframework.samples.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.samples.controller.MemberRegistRequest;
import org.springframework.samples.model.Address;
import org.springframework.samples.model.MemberInfo;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	private int nextMemberId = 0;
	private Map<String, MemberInfo> memberMap = new HashMap<String, MemberInfo>();

	public MemberService() {
		memberMap.put("m1", new MemberInfo("m1", "Yejin Lee", "yejin@gmail.com", "111111", "010-1111-1111", new Address("01520", "Seoul", "Korea"), "Singer", "Departure", 15, "내부", false));
		memberMap.put("m2", new MemberInfo("m2", "대박이", "daebak@naver.com", "222222", "010-2222-2222", new Address("08290", "Busan", "Korea"), "OneManBand", "멍멍!", 20, "외부", true));
		nextMemberId = 3;
	}

	public List<MemberInfo> getMembers() {
		return new ArrayList<MemberInfo>(memberMap.values());
	}

	public MemberInfo getMemberInfo(String memberId) {
		return memberMap.get(memberId);
	}

	public MemberInfo getMemberInfoByEmail(String email) {
		for (MemberInfo mi : memberMap.values()) {
			if (mi.getEmail().equals(email))
				return mi;
		}
		return null;
	}
	
	public String registerNewMember(MemberRegistRequest memRegReq) {
		MemberInfo mi = new MemberInfo(
							"m" + nextMemberId,
							memRegReq.getName(),
							memRegReq.getEmail(), 
							memRegReq.getPassword(),
							memRegReq.getPhone(),
							memRegReq.getAddress(),
							memRegReq.getType(),
							memRegReq.getSong(),
							Integer.valueOf(memRegReq.getTime()),
							memRegReq.getPlace(),
							memRegReq.isFirstTime());
		nextMemberId++;
		memberMap.put(mi.getId(), mi);
		return mi.getId();
	}
	
	/* 멤버 delete 메소드 생성해야 함!!*/
	public void deleteMember(String memberId) {
		memberMap.remove(memberId);
	}

}
