package com.test.data.jdbc;

import com.test.data.jdbc.dao.JobPagingAndSortingRepository;
import com.test.data.jdbc.entity.Job;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class JobCommandLineRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobCommandLineRunner.class);

    @Resource
    private JobPagingAndSortingRepository jobPagingAndSortingRepository;

    @Override
    public void run(String... args) throws Exception {
        for (Job job : jobPagingAndSortingRepository.findAll(Pageable.ofSize(10))) {
            LOGGER.info("job item: {}", job);
        }
    }
}
