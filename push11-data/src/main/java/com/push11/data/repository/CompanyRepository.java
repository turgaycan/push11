package com.push11.data.repository;


import com.push11.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

public interface CompanyRepository extends MongoRepository<Company, String> {

    public Company findByCompanyId(String companyId);

    @Query("{name : ?0}")
    public Company findByQueryCompanyName(String companyName);

}
