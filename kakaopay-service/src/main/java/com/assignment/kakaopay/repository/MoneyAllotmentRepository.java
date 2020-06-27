package com.assignment.kakaopay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assignment.kakaopay.domain.MoneyAllotment;

@Repository
public interface MoneyAllotmentRepository extends JpaRepository<MoneyAllotment, Long> {
    MoneyAllotment findByToken(String token);
    
    @Query(value = ""
            + "SELECT ID, AMOUNT, DIVIDE, ROOM_ID, USER_ID, TOKEN, CREATED_AT, UPDATED_AT"
            + "  FROM MONEY_ALLOTMENT"
            + " WHERE TOKEN = ?1"
            + "   AND USER_ID != ?2"
            + "   AND ROOM_ID = ?3"
            + "   AND CREATED_AT > DATEADD('MINUTE', -10, NOW())"
            + " LIMIT 1"
            + "", nativeQuery = true)
    MoneyAllotment findEnableAllotment(String token, String userId, String roomId);
    
    @Query(value = ""
            + "SELECT ID, AMOUNT, DIVIDE, ROOM_ID, USER_ID, TOKEN, CREATED_AT, UPDATED_AT"
            + "  FROM MONEY_ALLOTMENT"
            + " WHERE TOKEN = ?1"
            + "   AND USER_ID != ?2"
            + "   AND ROOM_ID = ?3"
            + "   AND CREATED_AT > DATEADD('DAY', -7, NOW())"
            + " LIMIT 1"
            + "", nativeQuery = true)
    MoneyAllotment findUserAllotment(String token, String userId, String roomId);
}
