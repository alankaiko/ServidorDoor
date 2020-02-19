package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laudoecia.api.domain.QuartzCronScheduler;

public interface CronJobRepository extends JpaRepository<QuartzCronScheduler, Long>{

}
