package com.assignment.kakaopay.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class MoneyDividend {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String token;

    @Column
    private Long dividend;

    @Column
    private String userId;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "allotment_id")
    private MoneyAllotment moneyAllotment;

    @Builder
    public MoneyDividend(Long id, String token, Long dividend, String userId, MoneyAllotment moneyAllotment) {
        this.id = id;
        this.token = token;
        this.dividend = dividend;
        this.userId = userId;
        this.moneyAllotment = moneyAllotment;
    }
    

}
