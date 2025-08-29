package com.embarkx.firstjobapp.job;

import com.embarkx.firstjobapp.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:5173") // Allow frontend React app
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // 🔹 Get all jobs
    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    // 🔹 Get job by ID
    @GetMapping("/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job != null) {
            return ResponseEntity.ok(job);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Add a new job
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);

        Company company = job.getCompany();
        if (company == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Company is required for a job");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Job created successfully");
    }

    // 🔹 Update a job
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job job) {
        boolean updated = jobService.updateById(id, job);
        if (updated) {
            return ResponseEntity.ok("Job updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }
    }

    // 🔹 Delete a job
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        boolean deleted = jobService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok("Job deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }
    }
}
