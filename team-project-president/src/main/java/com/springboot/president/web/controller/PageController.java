package com.springboot.president.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.president.config.auth.PrincipalDetails;
import com.springboot.president.service.ForumsService;
import com.springboot.president.service.IndexApiService;
import com.springboot.president.web.dto.IndexTableRespDto;
import com.springboot.president.web.dto.PetitionReqDto;
import com.springboot.president.web.dto.ReplyReplyReqDto;
import com.springboot.president.web.dto.ReplyReqDto;
import com.springboot.president.service.PetitionService;
import com.springboot.president.web.dto.BoardPetitionRespDto;
import com.springboot.president.web.dto.ForumsReplyReqDto;
import com.springboot.president.web.dto.ForumsReqDto;
import com.springboot.president.web.dto.ForumsRespDto;
import com.springboot.president.web.dto.GetPetitionRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PageController {
	
	private final IndexApiService indexApiService;
	private final PetitionService petitionService;
	private final ForumsService forumsService;

	@GetMapping({"/", "/index"})
	public String indexForm(Model model) throws Exception {
		
		IndexTableRespDto indexTableRespDto = indexApiService.getIndexApiTotal();
        
		model.addAttribute("indexTableRespDto", indexTableRespDto);
		
		return "index";
	}
	@GetMapping("/auth/signin")
	public String siginForm() {
		return "auth/signin";
	}
	@GetMapping("/petitions/Step2")
	public String petitionsStep2Form() {
		return "petitions/petitions_step2";
	}
	@GetMapping("/petitions")
	public String petitionsForm() {
		return "petitions/petitions";
	}
	@PostMapping("/petitions/Step2/write")
	public String petitionWrite(@AuthenticationPrincipal PrincipalDetails principalDetails, PetitionReqDto petitionReqDto, Model model) {
		boolean insertCheck = petitionService.insertPetition(principalDetails, petitionReqDto);
		model.addAttribute("insertCheck",insertCheck);
		return "/petitions/petitions_mypage";
	}
	@GetMapping("/petitions/Mypage")
	public String petitionsMypageForm() {
		return "petitions/petitions_mypage";
	}
	
	@GetMapping("/petitions/reco")
	public String recoPetitionsForm() {
		return "petitions/reco_petitions";
	}
	
	@GetMapping("/petitions/{petition_id}")
	public String petitionsBoardForm(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model ,@PathVariable int petition_id) {
		BoardPetitionRespDto boardPetitionRespDto = petitionService.BoardPetitionByPetitionid(principalDetails,petition_id);
		model.addAttribute("boardPetitionRespDto", boardPetitionRespDto);
		return "petitions/petitions_board";
	}
	
	@PostMapping("/petitions/{petition_id}/reply_write")
	public String petitionWrite(@AuthenticationPrincipal PrincipalDetails principalDetails, ReplyReqDto replyReqDto, @PathVariable int petition_id) {
		boolean replyResult = petitionService.insertPetitionReply(principalDetails, replyReqDto, petition_id);
		return "petitions/petitions";
	}
	
	@GetMapping("petitions/Step1")
	public String petitionsStep1Form() {
		return "petitions/petitions_step1";
	}
	
	@GetMapping("/petitions/Temp/{petition_id}")
	public String pettiionsMypageTemp(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model,@PathVariable int petition_id) {
		BoardPetitionRespDto boardPetitionRespDto = petitionService.BoardPetitionByPetitionid(principalDetails, petition_id);
		model.addAttribute("boardPetitionRespDto", boardPetitionRespDto);
		return "petitions/petitions_mypage_board";
	}
	
	@GetMapping("/forums")
	public String forumsForm() {
		return "forums/forums";
	}
	@GetMapping("/forums/suggest")
	public String forumsSuggestForm() {
		return "forums/forums_suggest";
	}

	@PostMapping("/forums/suggest/write")
	public String forumWrite(@AuthenticationPrincipal PrincipalDetails principalDetails,Model model, ForumsReqDto forumsReqDto) {
		boolean insertCheck = forumsService.insertForums(principalDetails, forumsReqDto);
		model.addAttribute("insertCheck",insertCheck);
		return "forums/forums";
	}
	
	@GetMapping("/forums/{forums_id}")
	public String forumsBoardForm(@AuthenticationPrincipal PrincipalDetails principalDetails,Model model, @PathVariable int forums_id) {
		ForumsRespDto forumsRespDto = forumsService.getForumsByForumsId(principalDetails, forums_id);
		model.addAttribute("forumsRespDto", forumsRespDto);
		return "forums/forums_board";
	}
	
	@PostMapping("/forums/reply_write/{forums_id}")
	public String forumsReplyWrite(@AuthenticationPrincipal PrincipalDetails principalDetails, ForumsReplyReqDto forumsReplyReqDto, @PathVariable int forums_id) {
		boolean replyResult = forumsService.insertForumsReply(principalDetails, forumsReplyReqDto, forums_id);
		return "forums/best_forums";
	}
	@GetMapping("/best_forums")
	public String bestForumsForm() {
		return "forums/best_forums";
	}

	@PostMapping("/forums/reply_write/list/{reply_id}")
	public String replyReplyWrite(@AuthenticationPrincipal PrincipalDetails principalDetails, ReplyReplyReqDto replyReplyReqDto,@PathVariable int reply_id) {
		boolean replyResult = forumsService.insertReplyReply(principalDetails, replyReplyReqDto, reply_id);
		return "forums/best_forums";
	}
	
	@GetMapping("/Search")
	public String searchForm() {
		return "search/search";
	}
	
	//청와대 조직도
	@GetMapping("/about/organization")
	public String organizationForm() {
		return "organization/organization";
	}
	@GetMapping("/about/government-organization")
	public String organization_govForm() {
		return "organization/organization_gov";
	}
	@GetMapping("/about/history")
	public String historyForm() {
		return "organization/history";
	}
	//오시는 길
	@GetMapping("/about/location-desk")
	public String locationForm() {
		return "location/location";
	}
	@GetMapping("/about/location-yeonpung")
	public String history2Form() {
		return "location/location2";
	}
	@GetMapping("/about/location-shihwa")
	public String history3Form() {
		return "location/location3";
	}
	@GetMapping("/about/location-chunchugwan")
	public String history4Form() {
		return "location/location4";
	}
	
	//오시는 길

}
