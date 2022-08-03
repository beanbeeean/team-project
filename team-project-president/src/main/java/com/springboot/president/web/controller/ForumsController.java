package com.springboot.president.web.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.president.config.auth.PrincipalDetails;
import com.springboot.president.service.ForumsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ForumsController {

	private final ForumsService forumsService;
	
	
	
	@GetMapping("/forums/list")
	public Object getForumsPage(@RequestParam int page) {
		return forumsService.getForumsPage(page);
	}
	
	@GetMapping("/best_forums/list")
	public Object getBestForumsByAgreeCount(@RequestParam String startDate, @RequestParam String endDate) {
		return forumsService.getBestForumsByAgreeCount(startDate,endDate);
	}
	@GetMapping("/best_forums/reply")
	public Object getBestForumsByReplyCount(@RequestParam String startDate, @RequestParam String endDate) {
		return forumsService.getBestForumsByReplyCount(startDate,endDate);
	}
	
	@GetMapping("/forums/reply")
	public Object getReplyList(@RequestParam int forums_id,@RequestParam int page) {
		return forumsService.getReplyByForumsId(forums_id,page);
		
	}
	
	@GetMapping("/forums/selection")
	public int forumsAgree(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam int forums_id, @RequestParam int choose) {
		int insertCheck = 5;
		// 로그인 하지 않았을 때
		if(principalDetails == null) {
			insertCheck = 5;
		}else {
			insertCheck = forumsService.forumsAgree(principalDetails, forums_id, choose);
			
		}
		return insertCheck;
	}
	
}
