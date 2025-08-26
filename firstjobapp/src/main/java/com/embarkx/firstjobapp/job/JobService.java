package com.embarkx.firstjobapp.job;
import java.util.List;

public interface JobService {
       List<Job> findAll();
       void createJob(Job job);

    Job getJobById(Long id);
//    void deleteJob(Job job);


    boolean deleteById(Long id);

    boolean updateById(Long id, Job job);
}
