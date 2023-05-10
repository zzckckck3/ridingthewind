package com.ringdingdong.ridingthewind.controller;

import com.ringdingdong.ridingthewind.model.MemberDto;
import com.ringdingdong.ridingthewind.model.service.PersonalTripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/mypage")

public class PersonalTripController {
	
	private final Logger logger = LoggerFactory.getLogger(PersonalTripController.class);
	
	private PersonalTripService personalTripService;

	public PersonalTripController(PersonalTripService personalTripService) {
		super();
		this.personalTripService = personalTripService;
	}

	@GetMapping("/info")
	public String mypage() {
		return "member/mypage";
	}
	
	@GetMapping("/edit")
	public String edit() {
		return "member/updatememberinfo";
	}
	
	@PostMapping("/edit")
	public String edit(MemberDto memberDto, Model model) {
		logger.debug("memberDto info : {}", memberDto);
		
		try {
			personalTripService.editMember(memberDto);
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
			return "error/error";
		}
	}
	
	@GetMapping("/withdrawal")
	public String withdrawal() {
		return "member/withdrawal";
	}
	
	@PostMapping("/withdrawal/{memberId}/{memberPassword}")
	public String withdrawal(@PathVariable("memberId") String memberId, @PathVariable("memberPassword") String memberPassword, HttpSession session) {
		logger.debug("memberId info : {}, memberPassword info : {}", memberId, memberPassword);
		
		try {
			personalTripService.deleteMember(memberId, memberPassword);
			session.invalidate();
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
}


