package com.ringdingdong.ridingthewind.controller;

import com.ringdingdong.ridingthewind.enumerate.ResponseResult;
import com.ringdingdong.ridingthewind.model.MemberDto;
import com.ringdingdong.ridingthewind.model.service.JwtServiceImpl;
import com.ringdingdong.ridingthewind.model.service.MemberService;
import com.ringdingdong.ridingthewind.model.service.MemberServiceImpl;
//import com.ringdingdong.ridingthewind.util.MailSendUtil;
import com.ringdingdong.ridingthewind.util.MailSendUtil;
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
import javax.xml.ws.Response;
import java.util.*;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

	private final Logger logger = LoggerFactory.getLogger(MemberController.class);


	@Autowired
	private MailSendUtil mailSendUtil;

	@Autowired
	private MemberService memberService;

//	@Autowired
//	private MailSendUtil mailSendUtil;

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
	public ResponseEntity<?> idCheck(@PathVariable("memberId") String memberId) throws Exception {

		try {
			int cnt = memberService.idCheck(memberId);
			if(cnt == 0){
				return ResponseEntity.ok().body(ResponseResult.SUCCESS.name());
			}else{
				return ResponseEntity.ok().body(ResponseResult.FAIL.name());
			}
		} catch (Exception e){
			return ResponseEntity.ok().body(ResponseResult.FAIL.name());
		}
	}


	@PostMapping("/join")
	public ResponseEntity<Void> join(@RequestBody Map<String, String> map) {
		logger.debug("memberDto info : {}", map);
		HttpStatus status = null;
        MemberDto memberDto = new MemberDto();
		memberDto.setMemberId(map.get("id"));
		memberDto.setMemberPassword(map.get("password"));
		memberDto.setMemberName(map.get("name"));
		memberDto.setMemberPhone(map.get("phoneNumber"));
		memberDto.setEmailId(map.get("emailId"));
		memberDto.setEmailDomain(map.get("emailDomain"));
		memberDto.setBirth(map.get("birth"));
		memberDto.setNickname(map.get("nickname"));
		memberDto.setRole(map.get("role"));
		try {
            memberService.joinMember(memberDto);
			status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
//		try{
//			String phone = mobile1 + "-" + mobile2 + "-" + mobile3;
//			memberDto.setMemberPhone(phone);
//			memberService.joinMember(memberDto);
//			return ResponseEntity.ok().build();
//		} catch (Exception e){
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//		}
		return new ResponseEntity<>(status);
	}
	@PostMapping(value="/update")
	public ResponseEntity<?> update(@RequestBody Map<String, String> map) throws Exception {
		HttpStatus status = null;
		MemberDto memberDto = new MemberDto();
		memberDto.setMemberId(map.get("id"));
		memberDto.setMemberName(map.get("name"));
		memberDto.setMemberPhone(map.get("phoneNumber"));
		memberDto.setEmailId(map.get("emailId"));
		memberDto.setEmailDomain(map.get("emailDomain"));
		memberDto.setBirth(map.get("birth"));
		memberDto.setNickname(map.get("nickname"));
		try {
			memberService.updateMember(memberDto);
			status = HttpStatus.ACCEPTED;
		}catch (Exception e){
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(status);

	}
	@DeleteMapping("/delete/{memberId}")
	public ResponseEntity<?> delete(@PathVariable String memberId) {
		HttpStatus status = null;
		try {
			memberService.deleteMember(memberId);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(status);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> map) {
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

	@GetMapping("/info/{userid}")
	public ResponseEntity<?> info(@PathVariable("userid") String memberId){
		Map<String, Object>  resultMap = new HashMap<>();

		resultMap.put("message", ResponseResult.FAIL.name());

		try{
			MemberDto result = memberService.findMemberId(memberId);
			if(result != null){
				resultMap.put("message", ResponseResult.SUCCESS.name());
				resultMap.put("data", result); // MemberDto를 'data'라는 키로 추가

			}
		}finally {
			return ResponseEntity.ok().body(resultMap);
		}
	}

	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userId){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			memberService.deleteRefreshToken(userId);
			resultMap.put("message",ResponseResult.SUCCESS.name());
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", ResponseResult.FAIL.name());
			status = HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@GetMapping("/sendmail/{email}")
	public ResponseEntity<?> sendMail(@PathVariable("email") String email) {
		HttpStatus status = null;
		String mailcode = null;
		try {
			mailcode = mailSendUtil.joinEmail(email);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return ResponseEntity.status(status).body(mailcode);
	}

	@GetMapping("/pwdcheck")
	public ResponseEntity<?> pwdcheck(@RequestParam Map<String, String> map){
		String status = ResponseResult.FAIL.name();
		try {
			int result = memberService.passwordCheck(map);
			if(result == 1){
				status = ResponseResult.SUCCESS.name();
			}
		} catch (Exception e){

		}
		return ResponseEntity.ok().body(status);
	}

	@PostMapping("/setpwd")
	public ResponseEntity<?> setpwd(@RequestParam Map<String, String> map){
		String status = ResponseResult.FAIL.name();
		try {
			memberService.setPassword(map);
		}catch (Exception e){

		}
		return ResponseEntity.ok().body(status);
	}

	@GetMapping("/following/{userId}")
	public ResponseEntity<?> following(@PathVariable("userId") String userId){
		System.out.println(userId);
		ArrayList<String> list = null;
		try{
			list =  memberService.getFollowingList(userId);

		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(list);
	}
	@GetMapping("/follower/{userId}")
	public ResponseEntity<?> follower(@PathVariable("userId") String userId) {

		System.out.println(userId);
		ArrayList<String> list = null;
		try {
			list = memberService.getFollowerList(userId);
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(list);
	}

	// 현재 userId를 로그인한 유저가 팔로우하고 있는지 확인함
	@GetMapping("/followcheck/{from}/{to}")
	public ResponseEntity<?> followcheck(@PathVariable("from") String from, @PathVariable("to") String to) {
		Map<String, String> map = new HashMap<>();
		map.put("from", from);
		map.put("to", to);
		String data = ResponseResult.FAIL.name();
		try {
			if(memberService.checkFollow(map) == 1) data = ResponseResult.SUCCESS.name();
			return ResponseEntity.ok(data);
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/followcheck")
	public ResponseEntity<?> deleteFollow(@RequestParam Map<String, String> map){
		System.out.println(map.toString());
		try{
			int result = memberService.deleteFollow(map);
			return ResponseEntity.ok().build();
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/followcheck")
	public ResponseEntity<?> insertFollow(@RequestBody Map<String, String> map){
		try{
			int result = memberService.insertFollow(map);
			return ResponseEntity.ok().build();
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/followerlist")
	public ResponseEntity<?> getFollowerRankList(){
		System.out.println("get리스트 들어옴");
		try{
			ArrayList<String> list = memberService.getFollowerRankList();
			return ResponseEntity.ok().body(list);
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//	@GetMapping("/followerlist")
//	public ResponseEntity<?> foll

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