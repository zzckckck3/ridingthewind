package com.ringdingdong.ridingthewind.controller;

import com.ringdingdong.ridingthewind.model.MemberDto;
import com.ringdingdong.ridingthewind.model.service.MemberService;
import com.ringdingdong.ridingthewind.model.service.MemberServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
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

	@GetMapping("/login")
	public String login() {
		return "member/signin";
	}


	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}


	@GetMapping("/check/{memberId}")
	public ResponseEntity<Integer> idCheck(@PathVariable("memberId") String memberId) throws Exception {
//        logger.debug("idCheck memberId : {}", memberId);
//        int cnt = memberService.idCheck(memberId);
//        return cnt + "";
		try {
			int cnt = memberService.idCheck(memberId);
			return ResponseEntity.ok(cnt);
		} catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}


	@PostMapping("/join")
	public ResponseEntity<Void> join(MemberDto memberDto, String mobile1, String mobile2, String mobile3, Model model) {
		logger.debug("memberDto info : {}", memberDto);
//        try {
//            String phone = mobile1 + "-" + mobile2 + "-" + mobile3;
//            memberDto.setMemberPhone(phone);
//            memberService.joinMember(memberDto);
//            return "redirect:/member/login";
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
//            return "error/error";
//        }
		try{
			String phone = mobile1 + "-" + mobile2 + "-" + mobile3;
			memberDto.setMemberPhone(phone);
			memberService.joinMember(memberDto);
			return ResponseEntity.ok().build();
		} catch (Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

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

// 마이페이지 이동 메서드
//	@GetMapping(value="/viewinfo")
//	public String viewinfo(Model model, HttpSession session){
//
//		UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute("loginUser");
//
//		User user = userService.searchUser(userSessionDto.getUserId());
//		model.addAttribute("userinfo", user);
//		return "user/viewinfo";
//	}

	@PostMapping(value="/update")
	public ResponseEntity<?> update(@RequestParam Map<String, String> map,@ModelAttribute MemberDto memberDto) throws Exception {
//		System.out.println("update들어옴");
//		MemberDto member = new MemberDto();
//		member.setMemberId("qwerqwer");
//		member.setMemberPassword("aaaaaaa");
//		member.setEmailId("bbbbbbb");
//		member.setEmailDomain("naver.com");
//		System.out.println("memberService리턴값"+memberService.updateMember(member));
		if(memberService.updateMember(memberDto) == 1){
			return ResponseEntity.ok().build();
		}else{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

//	@PostMapping(value="/update")
//	public String update(@RequestParam Map<String, String> map,@ModelAttribute UserDto userDto){
//		int result = userService.updateUser(userDto);
//		return "redirect:/user/info";
//	}

//    @GetMapping("/list")
//    public String list() {
//        return "redirect:/assets/list.html";
//    }




}