package com.assignment.kakaopay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.kakaopay.common.aop.UserInfo;
import com.assignment.kakaopay.service.SendMoneyService;
import com.assignment.kakaopay.service.model.AllotmentRequest;
import com.assignment.kakaopay.service.model.AllotmentResponse;
import com.assignment.kakaopay.service.model.ResultMessage;
import com.assignment.kakaopay.service.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SendMoneyController {

    private SendMoneyService sendMoneyService;

    @Autowired
    public SendMoneyController(SendMoneyService sendMoneyService) {
        this.sendMoneyService = sendMoneyService;
    }

    @PostMapping("/monies/allotment")
    public ResponseEntity<ResultMessage> requestMoneyAllotment(@RequestBody AllotmentRequest request,
            @UserInfo User user) {
        if (user.getUserId() == null || user.getUserId().isEmpty()) {
            throw new IllegalArgumentException("사용자 정보가 없습니다.");
        }
        if (user.getRoomId() == null || user.getRoomId().isEmpty()) {
            throw new IllegalArgumentException("대화방 정보가 없습니다.");
        }
        log.info("userInfo : {}", user);
        
        AllotmentResponse response = sendMoneyService.moneyAllotment(request, user);
        
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setData(response);
        resultMessage.setMessage(HttpStatus.OK.getReasonPhrase());
        
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }

}
