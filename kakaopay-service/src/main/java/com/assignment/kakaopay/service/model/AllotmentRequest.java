package com.assignment.kakaopay.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AllotmentRequest {

    private Long money;
    
    private Long peopleCount;
}
