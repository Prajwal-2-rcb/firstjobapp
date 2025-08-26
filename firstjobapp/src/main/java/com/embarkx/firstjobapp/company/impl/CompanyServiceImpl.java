package com.embarkx.firstjobapp.company.impl;

import com.embarkx.firstjobapp.company.Company;
import com.embarkx.firstjobapp.company.CompanyRepository;
import com.embarkx.firstjobapp.company.CompanyService;
import com.embarkx.firstjobapp.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository)
    {
        this.companyRepository=companyRepository;
    }

    @Override
    public List<Company> getAllcompanies() {
        return companyRepository.findAll();
    }


   @Override
    public boolean updateCompany(Company company,Long id) {
        Optional<Company> company1=companyRepository.findById(id);
        if(company1.isPresent())
        {
           Company currentCompany=company1.get();
           currentCompany.setName(company.getName());
           currentCompany.setDescription(company.getDescription());
           companyRepository.save(currentCompany);
           return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteById(Long id)
    {
        Company company=getCompanyById(id);
        if(company!=null)
        {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id)
    {
       return companyRepository.findById(id).orElse(null);
    }


    

    
    
  

}
