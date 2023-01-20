package com.nikosera.company.dto;

import lombok.Data;

@Data
public class CompanyResponse {
    private String name;
    private String code;
    private String address;
    private String number;
    private String logoUrl;
}
