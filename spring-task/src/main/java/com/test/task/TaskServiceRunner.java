package com.test.task;

import com.test.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TaskServiceRunner implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceRunner.class);

    private final TaskService taskService;

    public TaskServiceRunner(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("start task service");
        taskService.run();
        LOGGER.info("end task service");
    }
}
