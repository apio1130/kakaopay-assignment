package com.assignment.kakaopay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.kakaopay.common.aop.UserInfo;
import com.assignment.kakaopay.service.SearchMoneyService;
import com.assignment.kakaopay.service.model.AllotmentInfo;
import com.assignment.kakaopay.service.model.ResultMessage;
import com.assignment.kakaopay.service.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SearchMoneyController {

    private SearchMoneyService searchMoneyService;
    
    @Autowired    
    public SearchMoneyController(SearchMoneyService searchMoneyService) {
        this.searchMoneyService = searchMoneyService;
    }

    @GetMapping("/monies/allotment/{token}")
    public ResponseEntity<ResultMessage> test(@PathVariable String token, @UserInfo User user) {
        if (user.getUserId() == null || user.getUserId().isEmpty()) {
            throw new IllegalArgumentException("사용자 정보가 없습니다.");
        }
        if (user.getRoomId() == null || user.getRoomId().isEmpty()) {
            throw new IllegalArgumentException("대화방 정보가 없습니다.");
        }
        log.info("userInfo : {}", user);
        
        AllotmentInfo response = searchMoneyService.allotmentInfo(token, user);
        
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setData(response);
        resultMessage.setMessage(HttpStatus.OK.getReasonPhrase());
        
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }
    
}
