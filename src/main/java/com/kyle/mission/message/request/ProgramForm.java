package com.kyle.mission.message.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class ProgramForm {

    private String name;

    private String theme;

    private String prgm_desc;

    private String prgm_detail;

    private String region;
}