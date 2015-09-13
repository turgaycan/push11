package com.push11.data.repository;


import com.push11.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CompanyRepository extends MongoRepository<Company, String> {

    @Query("{name : ?0}")
    Company findByQueryCompanyName(String companyName);

}
