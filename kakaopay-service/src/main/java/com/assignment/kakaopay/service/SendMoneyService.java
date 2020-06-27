package com.assignment.kakaopay.service;

import com.assignment.kakaopay.service.model.AllotmentRequest;
import com.assignment.kakaopay.service.model.AllotmentResponse;
import com.assignment.kakaopay.service.model.User;

public interface SendMoneyService {
    public AllotmentResponse moneyAllotment(AllotmentRequest request, User user);
}
