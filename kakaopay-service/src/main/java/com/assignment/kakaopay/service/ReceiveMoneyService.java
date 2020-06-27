package com.assignment.kakaopay.service;

import com.assignment.kakaopay.service.model.DividendRequest;
import com.assignment.kakaopay.service.model.DividendResponse;
import com.assignment.kakaopay.service.model.User;

public interface ReceiveMoneyService {

    DividendResponse moneyDividend(DividendRequest request, User user);

}
