package com.embarkx.firstjobapp.company;

import com.embarkx.firstjobapp.job.Job;

import java.util.List;

public interface CompanyService {


    List<Company> getAllcompanies();
    boolean updateCompany(Company company,Long id);
    void createCompany(Company company);
    Company getCompanyById(Long id);
    boolean deleteById(Long id);


}
