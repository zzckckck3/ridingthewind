package com.ringdingdong.ridingthewind.controller;

import com.ringdingdong.ridingthewind.enumerate.ResponseResult;
import com.ringdingdong.ridingthewind.model.MemberDto;
import com.ringdingdong.ridingthewind.model.service.JwtServiceImpl;
import com.ringdingdong.ridingthewind.model.service.MemberService;
import com.ringdingdong.ridingthewind.model.service.MemberServiceImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static sun.security.x509.CertificateX509Key.KEY;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

	private final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
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
	public ResponseEntity<?> login(@RequestBody Map<String, String> map, @RequestParam(name = "saveid", required = false) String saveid, HttpSession session, HttpServletResponse response) {
		logger.debug("login map : {}", map);
		Map<String, Object>  resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto memberDto = memberService.loginMember(map);
			if(memberDto != null) {
				String accessToken = jwtService.createAccessToken("memberId", memberDto.getMemberId());
				String refreshToken = jwtService.createRefreshToken("memberId", memberDto.getMemberId());

				memberService.saveRefreshToken(memberDto.getMemberId(), refreshToken);

				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);

				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", ResponseResult.SUCCESS.name());

				status = HttpStatus.ACCEPTED;

			} else {
				resultMap.put("message", ResponseResult.FAIL.name());
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	// 토큰 만료 확인
	public boolean isTokenValid(String jwt,Key KEY) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
//	public Boolean getExpToken(String jwt) {
//		try {
//			Jws<Claims> claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt);
//
//			Date exp = claims.getBody().getExpiration();
//			Date now = new Date();
//			if (exp.after(now)) {
//				System.out.println(7);
//				return true;
//			}
//			return false;
//		} catch (Exception e) {
//			return false;
////			UnauthorizedException("Illegal Token");
//		}
////		throw new NullArgumentException("Token is NULL");
//	}

	public String getUserUID(String jwt) throws RuntimeException{
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt);
			return String.valueOf(claims.getBody().get("uid"));
		} catch (Exception e) {
			return null;
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