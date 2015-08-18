package com.push11.data.repository;


import com.push11.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Company.class, idClass = String.class)
public interface CompanyRepository extends MongoRepository<Company, String> {

    public Company findByCompanyId(String companyId);

    @Query("{company_name : ?0}")
    public Company findByQueryCompanyName(String companyName);

}
