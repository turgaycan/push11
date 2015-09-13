package com.push11.data.service;

import com.push11.data.repository.CompanyRepository;
import com.push11.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company saveEntity(Company company){
        return companyRepository.save(company);
    }

    public Company findById(String companyId){
        return companyRepository.findOne(companyId);
    }
}
