package com.assignment.kakaopay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.kakaopay.common.aop.UserInfo;
import com.assignment.kakaopay.service.ReceiveMoneyService;
import com.assignment.kakaopay.service.model.DividendRequest;
import com.assignment.kakaopay.service.model.DividendResponse;
import com.assignment.kakaopay.service.model.ResultMessage;
import com.assignment.kakaopay.service.model.User;

@RestController
public class ReceiveMoneyController {
    
    private ReceiveMoneyService receiveMoneyService;
    
    @Autowired
    public ReceiveMoneyController(ReceiveMoneyService receiveMoneyService) {
        this.receiveMoneyService = receiveMoneyService;
    }

    @PutMapping("/monies/dividend")
    public ResponseEntity<ResultMessage> moneyDividend (@RequestBody DividendRequest request , @UserInfo User user) {
        if (user.getUserId() == null || user.getUserId().isEmpty()) {
            throw new IllegalArgumentException("사용자 정보가 없습니다.");
        }
        if (user.getRoomId() == null || user.getRoomId().isEmpty()) {
            throw new IllegalArgumentException("대화방 정보가 없습니다.");
        }
        
        DividendResponse response =  receiveMoneyService.moneyDividend(request, user);
        
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setData(response);
        resultMessage.setMessage(HttpStatus.OK.getReasonPhrase());
        
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }
}
