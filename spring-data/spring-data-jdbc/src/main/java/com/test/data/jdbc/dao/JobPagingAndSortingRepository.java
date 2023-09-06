package com.test.data.jdbc.dao;

import com.test.data.jdbc.entity.Job;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JobPagingAndSortingRepository extends PagingAndSortingRepository<Job, Long> {
}
