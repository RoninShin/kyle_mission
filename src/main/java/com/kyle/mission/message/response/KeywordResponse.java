package com.kyle.mission.message.response;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeywordResponse {

    private String region;

    private String keyword;

    private BigInteger count;

    private int program;
}