package com.nsa.team9.timesheetmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

public class TimeSheetManagerApplication {
    static final Logger LOG = LoggerFactory.getLogger(TimeSheetManagerApplication.class);

    public static void main(String[] args) {
        LOG.debug("Starting app");
        SpringApplication.run(TimeSheetManagerApplication.class, args);
    }

}
