package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.Interest;
import com.ssafy.happyhouse.model.dto.Member;
import com.ssafy.happyhouse.model.service.JwtService;
import com.ssafy.happyhouse.model.service.MemberService;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/user")
public class MemberContorller {

	public static final Logger logger = LoggerFactory.getLogger(MemberContorller.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private MemberService memberService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody Member member) throws Exception {
		logger.debug("...............signUp: {}",member);
		if (memberService.signUp(member)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> map) throws Exception {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			Member loginUser = memberService.login(map);
			System.out.println(loginUser);
			if (loginUser != null) {
				String token = jwtService.create("id", loginUser.getId(), "access-token");
				logger.debug("로그인 토큰 정보: {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패: {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> userInfo(@PathVariable String id, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				Member member = memberService.getMember(id);
				resultMap.put("userInfo", member);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Member member) throws Exception { // id와 name은 수정되지 않음
		System.out.println(member);
		if (memberService.update(member)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) throws Exception {
		if (memberService.delete(id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/interest/{id}")
	public ResponseEntity<List<Interest>> interestList(@PathVariable String id) throws Exception {
		return new ResponseEntity<List<Interest>>(memberService.interestList(id), HttpStatus.OK);
	}

	@PostMapping("/interest")
	public ResponseEntity<String> addInterest(@RequestBody Map<String, String> map) throws Exception {
		if (memberService.addInterest(map)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/interest/remove")
	public ResponseEntity<String> removeInterest(@RequestBody Map<String, String> map) throws Exception {
		if (memberService.removeInterest(map)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
