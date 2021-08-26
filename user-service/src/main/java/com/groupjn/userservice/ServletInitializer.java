package com.groupjn.userservice;

import com.groupjn.userservice.factory.CreateUserFactory;
import kieker.common.record.controlflow.OperationExecutionRecord;
import kieker.monitoring.annotation.OperationExecutionMonitoringProbe;
import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

         SpringApplicationBuilder  springApplicationBuilder =  application.sources(UserServiceApplication.class);
         return springApplicationBuilder;

    }

}

