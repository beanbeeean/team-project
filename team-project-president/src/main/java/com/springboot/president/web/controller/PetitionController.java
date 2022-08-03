package com.springboot.president.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.president.config.auth.PrincipalDetails;
import com.springboot.president.service.PetitionService;
import com.springboot.president.web.dto.PetitionReqDto;
import com.springboot.president.web.dto.ReplyReqDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PetitionController {
	
	private final PetitionService petitionService;
	

	@GetMapping("/petitions/wait")
	public Object getWaitPetition() {
		return petitionService.GetWaitPetition();
	}
	
	@GetMapping("/petitions/board")
	public Object getPetitionBykategorie(@RequestParam String kategorie, @RequestParam int only) {
		return petitionService.GetPetitionByKategorie(kategorie, only);
	}
	
	
	@GetMapping("/petitions/Mypage/List")
	public Object petitionsMypageForm(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		return petitionService.GetPetitionByid(principalDetails);
	}
	@GetMapping("/petitions/total")
	public Object getPetitionBykategorieAndOrder(@RequestParam String kategorie,@RequestParam int only,@RequestParam int page,@RequestParam int order) {
		return petitionService.GetPetitionBykategorieAndOrder(kategorie, only, page, order);
	}
	
	@GetMapping("/petitions/list")
	public Object getPetitionByAgreeCount(@RequestParam int page) {
		return petitionService.GetPetitionByAgreeCount(page);
	}
	@GetMapping("/petitions/reply")
	public Object getReplyList(@RequestParam int petition_id,@RequestParam int page) {
		return petitionService.getReplyByPetitionId(petition_id,page);
		
	}
	
	@GetMapping("petitions/step1/search")
	public Object petitionsStep1Form(@RequestParam String searchString) {
		return petitionService.GetPetitionByTitle(searchString);
	}
	


}
