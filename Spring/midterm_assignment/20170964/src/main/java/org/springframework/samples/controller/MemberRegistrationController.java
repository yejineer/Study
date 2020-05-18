package org.springframework.samples.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.model.Address;
import org.springframework.samples.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("regReq")
public class MemberRegistrationController {
	private static final String MEMBER_REGISTRATION_FORM_STEP1 
								= "member/registrationFormStep1";
	private static final String MEMBER_REGISTRATION_FORM_STEP2 
								= "member/registrationFormStep2";
	private static final String MEMBER_REGISTRATION_FORM_STEP3 
								= "member/registrationFormStep3";
	private static final String MEMBER_REGISTRATION_DONE 
								= "member/registrationFormDone";
	
	@Autowired
	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@ModelAttribute("regReq") 			// request handler methods 보다 먼저 호출되는 accessor method
	public MemberRegistRequest formBacking(HttpServletRequest request) {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			MemberRegistRequest memRegReq = new MemberRegistRequest();
			Address address = new Address("", "서울", "");		// Address 객체 생성 및 초기화
			memRegReq.setAddress(address);
			memRegReq.setTime("20");
			return memRegReq;
		}
		else return new MemberRegistRequest();
	}
	
	@RequestMapping(value="/member/register/step1") // step1 요청
	public String step1() {
		return MEMBER_REGISTRATION_FORM_STEP1;
	}
	
	@RequestMapping(value="/member/register/step2", method = RequestMethod.POST) // step1 -> step2 이동
	public String step2(
			@Valid @ModelAttribute("regReq") MemberRegistRequest memRegReq,
			BindingResult bindingResult, Model model) {		
		System.out.println("command 객체: " + memRegReq);
		checkDuplicateId(memRegReq.getEmail(), bindingResult); // ID 중복 검사
		/* 두 암호가 일치하는 여부 검사를 위한 아래 코드 추가 필요 */
		if (!memRegReq.isSamePasswordConfirmPassword()) {
			bindingResult.rejectValue("confirmPassword", "notSame");
		}
		if (bindingResult.hasErrors()) {
			System.out.println("error있음");
			return MEMBER_REGISTRATION_FORM_STEP1;
		}
//		return "member/registered";
		return MEMBER_REGISTRATION_FORM_STEP2;
	}
	
	@RequestMapping(value="/member/register/step2", method = RequestMethod.GET) // step3 -> step2 이동
	public String step2FromStep3(
			@ModelAttribute("regReq") MemberRegistRequest memRegReq,
			BindingResult bindingResult, Model model) {		
		return MEMBER_REGISTRATION_FORM_STEP2;
	}
	
	@RequestMapping(value="/member/register/step3", method = RequestMethod.POST) // step2 -> step3 이동
	public String step3(
			@Valid @ModelAttribute("regReq") MemberRegistRequest memRegReq,
			BindingResult bindingResult, Model model) {		
		System.out.println("command 객체: " + memRegReq);
		
		if (!memRegReq.getTime().trim().isEmpty()) { // 공연 시간을 기입했을 때
			try {
				if (Integer.valueOf(memRegReq.getTime()) > 30) { // String이므로 int형 변환 필요
					bindingResult.rejectValue("time", "timeLimit");
				}
			} catch (Exception e) {
				bindingResult.rejectValue("time", "notNumber");
			}
		} else { // 공연 시간을 기입하지 않았을 때 (""일 때)
			bindingResult.rejectValue("time", "required");
		}
		if (memRegReq.getSong().trim().isEmpty()) {
			bindingResult.rejectValue("song", "required");
		}
		if (memRegReq.getPlace() == null || memRegReq.getPlace().trim().isEmpty()) {
			bindingResult.rejectValue("place", "required");
		}
		
		if (bindingResult.hasErrors()) {
			System.out.println("error있음");
			return MEMBER_REGISTRATION_FORM_STEP2;
		}

		return MEMBER_REGISTRATION_FORM_STEP3;
	}
	
	@RequestMapping(value="/member/register/done", method = RequestMethod.POST)
	public String done(
			@ModelAttribute("regReq") MemberRegistRequest memRegReq,
			Model model, SessionStatus sessionStatus) {	 // step3 -> step4 이동
		String mid = memberService.registerNewMember(memRegReq);
		model.addAttribute("memberId", mid);
		Date registerDate = new Date();
		model.addAttribute("registerDate", registerDate);
		sessionStatus.setComplete();
		return MEMBER_REGISTRATION_DONE;
	}

	private void checkDuplicateId(String email, BindingResult errors) {
		if (memberService.getMemberInfoByEmail(email) != null) {
			errors.rejectValue("email", "duplicate");
		}
	}
	
	@ModelAttribute("typeCodes")
	public List<String> referenceData1() {	// accessor method
		List<String> typeCodes = new ArrayList<String>();
		typeCodes.add("Instrumentalist");
		typeCodes.add("Singer");
		typeCodes.add("PoeticJuggler");
		typeCodes.add("OneManBand");
		return typeCodes;	// view에 전달됨
	}
	
	@ModelAttribute("places")
	public String[] referenceData() {
		return new String[] { "내부 무대", "외부 무대"};
	}

}
