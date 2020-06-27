package com.assignment.kakaopay.service;

import com.assignment.kakaopay.service.model.AllotmentInfo;
import com.assignment.kakaopay.service.model.User;

public interface SearchMoneyService {

    AllotmentInfo allotmentInfo(String token, User user);

}
