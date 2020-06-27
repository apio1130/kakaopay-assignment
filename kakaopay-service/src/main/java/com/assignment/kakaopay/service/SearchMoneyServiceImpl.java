package com.assignment.kakaopay.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.kakaopay.domain.MoneyAllotment;
import com.assignment.kakaopay.domain.MoneyDividend;
import com.assignment.kakaopay.repository.MoneyAllotmentRepository;
import com.assignment.kakaopay.repository.MoneyDividendRepository;
import com.assignment.kakaopay.service.model.AllotmentInfo;
import com.assignment.kakaopay.service.model.User;


@Service
public class SearchMoneyServiceImpl implements SearchMoneyService {

    private MoneyAllotmentRepository allotmentRepository;
    
    private MoneyDividendRepository dividendRepository;

    @Autowired
    public SearchMoneyServiceImpl(MoneyAllotmentRepository allotmentRepository,
            MoneyDividendRepository dividendRepository) {
        this.allotmentRepository = allotmentRepository;
        this.dividendRepository = dividendRepository;
    }

    @Override
    public AllotmentInfo allotmentInfo(String token, User user) {
        MoneyAllotment moneyAllotment = allotmentRepository.findUserAllotment(token, user.getUserId(), user.getRoomId());
        List<MoneyDividend> list = new ArrayList<>();
        if (moneyAllotment != null) {
            list = dividendRepository.findByMoneyAllotment(moneyAllotment);
        }
        
        return new AllotmentInfo(moneyAllotment, list);
    }
}

