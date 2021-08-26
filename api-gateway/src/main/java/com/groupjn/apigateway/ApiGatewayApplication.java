package com.groupjn.apigateway;

import kieker.analysis.AnalysisController;
import kieker.analysis.IAnalysisController;
import kieker.analysis.plugin.filter.forward.TeeFilter;
import kieker.analysis.plugin.reader.filesystem.FSReader;
import kieker.common.configuration.Configuration;
import kieker.common.record.controlflow.OperationExecutionRecord;
import kieker.monitoring.annotation.OperationExecutionMonitoringProbe;
import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;



@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class ApiGatewayApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}




