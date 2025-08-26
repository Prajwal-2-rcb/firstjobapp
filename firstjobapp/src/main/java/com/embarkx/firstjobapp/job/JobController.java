package com.embarkx.firstjobapp.job;
import com.embarkx.firstjobapp.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class JobController {


    private JobService jobservice;

    public JobController(JobService jobservice) {
        this.jobservice = jobservice;
    }


//    private List<Job> jobs=new ArrayList<>();

//    @GetMapping("/jobs")
//    public List<Job> findAll() {
//        return jobservice.findAll();
//    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll() {
        return new ResponseEntity<>(jobservice.findAll(), HttpStatus.OK);
    }

//    @PostMapping("/jobs")
//    public String createJob(@RequestBody Job job) {
//        jobservice.createJob(job);
//        return "Job created successfully";
//    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobservice.createJob(job);
        Company company=job.getCompany();
        if(company==null)
        {
            return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity<>("Job created successfully",HttpStatus.CREATED);
        }

    }

    //    @GetMapping("/jobs/{id}")
//    public Job findById(@PathVariable Long id)
//    {
//
//        Job job= jobservice.getJobById(id);
//        if (job!=null)
//        {
//            return job;
//        }
//        else{
//            return new Job(1L,"TestJob","TestJob Description","1000","2000","TestLocation");
//        }
//    }
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id) {

        Job job = jobservice.getJobById(id);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

//    @DeleteMapping("/jobs/{id}")
//    public ResponseEntity<String> DeleteById(@PathVariable Long id){
//        Job job=jobservice.getJobById(id);
//        jobservice.deleteJob(job);
//        return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
//
//    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> DeleteById(@PathVariable Long id){
        boolean deleted=jobservice.deleteById(id);
        if(deleted)
            return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Job not found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id,@RequestBody Job job)
    {
        boolean updated=jobservice.updateById(id,job);
        if(updated)
            return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Job not found",HttpStatus.NOT_FOUND);
    }

}
