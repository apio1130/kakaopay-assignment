package com.assignment.kakaopay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.kakaopay.domain.MoneyAllotment;
import com.assignment.kakaopay.domain.MoneyDividend;
import com.assignment.kakaopay.repository.MoneyAllotmentRepository;
import com.assignment.kakaopay.repository.MoneyDividendRepository;
import com.assignment.kakaopay.service.model.DividendRequest;
import com.assignment.kakaopay.service.model.DividendResponse;
import com.assignment.kakaopay.service.model.User;

@Service
public class ReceiveMoneyServiceImpl implements ReceiveMoneyService {
    
    private MoneyAllotmentRepository allotmentRepository;
    
    private MoneyDividendRepository dividendRepository;
    
    @Autowired
    public ReceiveMoneyServiceImpl(MoneyAllotmentRepository allotmentRepository,
            MoneyDividendRepository dividendRepository) {
        this.allotmentRepository = allotmentRepository;
        this.dividendRepository = dividendRepository;
    }

    @Override
    public DividendResponse moneyDividend(DividendRequest request, User user) {
        DividendResponse result = new DividendResponse();
        String token = request.getToken();
        MoneyAllotment moneyAllotment = allotmentRepository.findEnableAllotment(token, user.getUserId(), user.getRoomId());
        
        if (moneyAllotment != null && moneyAllotment.getRoomId().equals(user.getRoomId()) && !moneyAllotment.getUserId().equals(user.getUserId())) {
            MoneyDividend beforeDividend = dividendRepository.findByTokenByUserId(token, user.getUserId());
            if (beforeDividend == null) {
                MoneyDividend moneyDividend = dividendRepository.findEnableDividendByToken(token);
                if (moneyDividend != null) {
                    moneyDividend.setUserId(user.getUserId());
                    dividendRepository.save(moneyDividend);
                    result.setDividend(moneyDividend.getDividend());
                }
            }
        }
        
        return result;
    }

}
