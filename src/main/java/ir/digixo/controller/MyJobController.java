package ir.digixo.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class MyJobController {

    final private JobLauncher jobLauncher;
    final private Job firstJob;

    public MyJobController(JobLauncher jobLauncher, Job firstJob) {
        this.jobLauncher = jobLauncher;
        this.firstJob = firstJob;
    }

    //http://localhost:8080/job/start/firstjob
    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable("jobName") String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("mytime", System.currentTimeMillis())
                .toJobParameters();
        if (jobName.equals("firstjob")) {
            jobLauncher.run(firstJob, jobParameters);
            return "Job started!!!";
        } else {
            return "Job didn't start!!!";
        }

    }
}
