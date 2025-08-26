package com.embarkx.firstjobapp.job.impl;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobRepository;
import com.embarkx.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class JobServiceImpl implements JobService {

//    private List<Job> jobs=new ArrayList<>();

    JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository)
    {
        this.jobRepository=jobRepository;
    }
//    private Long nextId=1L;
    @Override
    public void createJob(Job job) {
//        job.setId(nextId++);
//        jobs.add(job);
//        return "Job created successfully";
        jobRepository.save(job);

    }

    @Override
    public List<Job> findAll()
    {

//        return jobs;
        return jobRepository.findAll();

    }

    @Override
    public Job getJobById(Long id)
    {
//        return jobs.stream().
//                filter(job->job.getId().equals(id)).
//                findFirst().orElse(null);
        //traditional way
        //for(Job job:jobs)
        //{
        //    if(job.getId().equals(id))
        //        return job;
        //}
        //return null;
        return jobRepository.findById(id).orElse(null);
    }
//    @Override
//    public void deleteJob(Job job)
//    {
//        jobs.remove(job);
//    }

//    @Override
//    public boolean deleteById(Long id)
//    {
//        Job job=getJobById(id);
//        if(job!=null)
//        {
//            jobs.remove(job);
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
    @Override
    public boolean deleteById(Long id)
    {
//        Iterator<Job> iterator=jobs.iterator();
//        while(iterator.hasNext())
//        {
//            Job job=iterator.next();
//            if(job.getId().equals(id))
//            {
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
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
//        Iterator<Job> iterator=jobs.iterator();
//        while(iterator.hasNext())
//        {
//            Job currentJob=iterator.next();
//            if(currentJob.getId().equals(id))
//            {
//                currentJob.setTitle(job.getTitle());
//                currentJob.setDescription(job.getDescription());
//                currentJob.setMinSalary(job.getMinSalary());
//                currentJob.setMaxSalary(job.getMaxSalary());
//                currentJob.setLocation(job.getLocation());
//                return true;
//            }
//        }
//        return false;
//        try{
//            jobRepository.save(job);
//            return true;
//        }
//        catch(Exception e)
//        {
//            return false;
//        }
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
