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
		memberMap.put("m1", new MemberInfo("m1", "Scott", "scott@yahoo.com", "scott", "010-1111-1111", new Address("01520", "Seoul", "Korea"), "Singer", "ABC", 25, "서울", false));
		memberMap.put("m2", new MemberInfo("m2", "Peter", "peter@hotmail.com", "peter", "010-1111-1111", new Address("08290", "Busan", "Korea"), "Singer", "DEF", 30, "경기", true));
		memberMap.put("m3", new MemberInfo("m3", "Jain", "jain@gmail.com", "jain", "010-1111-1111", new Address("04730", "Incheon", "Korea"), "Singer", "GHI", 13, "제주", false));
		nextMemberId = 4;
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
							memRegReq.getArea(),
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
