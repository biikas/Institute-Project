package com.nikosera.company.service;

import com.nikosera.common.builder.ResponseBuilder;
import com.nikosera.common.constant.MsgConstant;
import com.nikosera.common.dto.GenericResponse;
import com.nikosera.company.dto.CompanyListResponse;
import com.nikosera.company.dto.CompanyResponse;
import com.nikosera.entity.Company;
import com.nikosera.repository.repository.core.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public GenericResponse companyList() {
        List<Company> companyList = companyRepository.getAllByActive();

        List<CompanyResponse> companyResponseList = companyList.stream().map(company -> {
            CompanyResponse response = new CompanyResponse();
            response.setAddress(company.getContactAddress());
            response.setCode(company.getCode());
            response.setNumber(company.getContactNumber());
            response.setLogoUrl(company.getLogoUrl());
            response.setName(company.getName());
            return response;
        }).collect(Collectors.toList());

        CompanyListResponse companyListResponse = new CompanyListResponse();
        companyListResponse.setCompany(companyResponseList);
        return ResponseBuilder.buildSuccessMessage(companyListResponse, MsgConstant.SuccessResponse.SUCCESS_FETCH_LIST);
    }
}
