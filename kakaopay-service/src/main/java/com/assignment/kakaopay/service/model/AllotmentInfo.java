package com.assignment.kakaopay.service.model;

import java.util.List;

import com.assignment.kakaopay.domain.MoneyAllotment;
import com.assignment.kakaopay.domain.MoneyDividend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AllotmentInfo {
    
    private MoneyAllotment allotment;
    
    private List<MoneyDividend> dividends;
    
}
