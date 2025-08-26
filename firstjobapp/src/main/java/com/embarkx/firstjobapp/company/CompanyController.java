package com.embarkx.firstjobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyservice;
    public CompanyController(CompanyService companyservice)
    {
        this.companyservice=companyservice;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllcompanies() {
        return new ResponseEntity<>(companyservice.getAllcompanies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@org.springframework.web.bind.annotation.PathVariable Long id)
    {
        Company company=companyservice.getCompanyById(id);
        if(company!=null)
        {
            return new ResponseEntity<>(company,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

  @PostMapping
    public ResponseEntity<String> creatCompany(@RequestBody Company company)
    {
        companyservice.createCompany(company);
        return new ResponseEntity<>("Company created successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id)
    {
        boolean deleted=companyservice.deleteById(id);
        if(deleted)
            return new ResponseEntity<>("Company deleted successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company)
    {
        boolean updated=companyservice.updateCompany(company,id);
        if(updated)
            return new ResponseEntity<>("Company updated successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
    }


}
