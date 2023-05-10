package com.ringdingdong.ridingthewind.controller;

import com.ringdingdong.ridingthewind.model.MemberDto;
import com.ringdingdong.ridingthewind.model.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "/member")

public class MemberController {
	
	private final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private MemberService memberService;

	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	@GetMapping("/join")
	public String join() {
		return "member/signup";
	}
	
	@GetMapping("/check/{memberId}")
	@ResponseBody
	public String idCheck(@PathVariable("memberId") String memberId) throws Exception {
		logger.debug("idCheck memberId : {}", memberId);
		int cnt = memberService.idCheck(memberId);
		return cnt + "";
	}

	
	@PostMapping("/join")
	public String join(MemberDto memberDto, String mobile1, String mobile2, String mobile3, Model model) {
		logger.debug("memberDto info : {}", memberDto);
		try {
			String phone = mobile1 + "-" + mobile2 + "-" + mobile3;
			memberDto.setMemberPhone(phone);
			memberService.joinMember(memberDto);
			return "redirect:/member/login";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
			return "error/error";
		}
	}
	
	@GetMapping("/login")
	public String login() {
		return "member/signin";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, @RequestParam(name = "saveid", required = false) String saveid, Model model, HttpSession session, HttpServletResponse response) {
		logger.debug("login map : {}", map);
		
		try {
			MemberDto memberDto = memberService.loginMember(map);
			if(memberDto != null) {
				session.setAttribute("memberinfo", memberDto);
				
				Cookie cookie = new Cookie("memberInfo", map.get("memberId"));
				cookie.setPath("/");
				if("ok".equals(saveid)) {
					cookie.setMaxAge(60*60*24*365*40);
				} else {
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);
				return "redirect:/";
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return "member/signin";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
			return "error/error";
		}
	}
	

	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	
//	@GetMapping("/list")
//	public String list() {
//		return "redirect:/assets/list.html";
//	}
	
}
