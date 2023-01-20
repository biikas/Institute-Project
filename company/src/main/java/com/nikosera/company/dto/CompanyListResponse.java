package com.nikosera.company.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompanyListResponse {
    private List<CompanyResponse> company;
}
