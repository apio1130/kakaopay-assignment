package com.assignment.kakaopay.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.kakaopay.common.CommonUtils;
import com.assignment.kakaopay.domain.MoneyAllotment;
import com.assignment.kakaopay.domain.MoneyDividend;
import com.assignment.kakaopay.repository.MoneyAllotmentRepository;
import com.assignment.kakaopay.repository.MoneyDividendRepository;
import com.assignment.kakaopay.service.model.AllotmentRequest;
import com.assignment.kakaopay.service.model.AllotmentResponse;
import com.assignment.kakaopay.service.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SendMoneyServiceImpl implements SendMoneyService {

    private MoneyAllotmentRepository allotmentRepository;
    
    private MoneyDividendRepository dividendRepository;
    

    @Autowired
    public SendMoneyServiceImpl(MoneyAllotmentRepository allotmentRepository,
            MoneyDividendRepository dividendRepository) {
        this.allotmentRepository = allotmentRepository;
        this.dividendRepository = dividendRepository;
    }
    
    @Transactional
    @Override
    public AllotmentResponse moneyAllotment(AllotmentRequest request, User user) {
        String token = CommonUtils.generateToken();
        log.info("generateToken : {}", token);
        
        MoneyAllotment allotment = MoneyAllotment.builder().amount(request.getMoney())
                .divide(request.getPeopleCount()).token(token)
                .userId(user.getUserId()).roomId(user.getRoomId()).build();
        log.info("insert data info : {}", allotment);
        allotmentRepository.save(allotment);
        log.info("inserted data info : {}", allotment);
        long[] divideMoney = CommonUtils.divideMoney(request.getMoney(), request.getPeopleCount().intValue());
        for (int i = 0; i < divideMoney.length; i++) {
            dividendRepository.save(
                    MoneyDividend.builder().token(token).dividend(divideMoney[i]).moneyAllotment(allotment).build());
        }
        
        return AllotmentResponse.builder().token(token).build();
    }

}
