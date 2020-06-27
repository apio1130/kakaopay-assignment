package com.assignment.kakaopay.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class MoneyAllotment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private String roomId;

    @Column
    private Long amount;

    @Column
    private Long divide;

    @Column
    private String token;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public MoneyAllotment(Long id, String userId, String roomId, Long amount, Long divide, String token) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.amount = amount;
        this.divide = divide;
        this.token = token;
    }

}
