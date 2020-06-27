package com.assignment.kakaopay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assignment.kakaopay.domain.MoneyAllotment;
import com.assignment.kakaopay.domain.MoneyDividend;

@Repository
public interface MoneyDividendRepository extends JpaRepository<MoneyDividend, Long> {
    
    List<MoneyDividend> findByToken(String token);
    
    @Query(value = "SELECT ID, CREATED_AT, DIVIDEND, TOKEN, UPDATED_AT, USER_ID, ALLOTMENT_ID " + 
                   "  FROM MONEY_DIVIDEND " + 
                   " WHERE USER_ID IS NULL " + 
                   "   AND TOKEN = ?1 " + 
                   " LIMIT 1 ", nativeQuery = true)
    MoneyDividend findEnableDividendByToken(String token);
    
    @Query(value = "SELECT ID, CREATED_AT, DIVIDEND, TOKEN, UPDATED_AT, USER_ID, ALLOTMENT_ID " + 
            "  FROM MONEY_DIVIDEND " + 
            " WHERE TOKEN = ?1 " + 
            "   AND USER_ID = ?2 " + 
            " LIMIT 1 ", nativeQuery = true)
    MoneyDividend findByTokenByUserId(String token, String userId);
    
    List<MoneyDividend> findByMoneyAllotment(MoneyAllotment moneyAllotment);
    
}
