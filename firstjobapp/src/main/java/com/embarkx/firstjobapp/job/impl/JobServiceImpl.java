package com.embarkx.firstjobapp.job.impl;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobRepository;
import com.embarkx.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class JobServiceImpl implements JobService {



    JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository)
    {
        this.jobRepository=jobRepository;
    }

    @Override
    public void createJob(Job job) {

        jobRepository.save(job);

    }

    @Override
    public List<Job> findAll()
    {


        return jobRepository.findAll();

    }

    @Override
    public Job getJobById(Long id)
    {

        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id)
    {

        try{
            jobRepository.deleteById(id);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }

    }

    @Override
    public boolean updateById(Long id,Job job)
    {

        Optional<Job> optionalJob=jobRepository.findById(id);
        if(optionalJob.isPresent())
        {
            Job currentJob=optionalJob.get();
            currentJob.setTitle(job.getTitle());
            currentJob.setDescription(job.getDescription());
            currentJob.setMinSalary(job.getMinSalary());
            currentJob.setMaxSalary(job.getMaxSalary());
            currentJob.setLocation(job.getLocation());
            jobRepository.save(currentJob);
            return true;
        }
        else
        {
            return false;
        }
    }



}
