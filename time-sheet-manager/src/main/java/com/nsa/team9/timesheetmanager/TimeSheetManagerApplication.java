package com.nsa.team9.timesheetmanager;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class TimeSheetManagerApplication {
    public static void main(final String[] args) {
        log.debug("Starting app");
        SpringApplication.run(TimeSheetManagerApplication.class, args);
    }
}
