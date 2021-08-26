package com.groupjn.userservice.kieker.monitor;

import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;
import kieker.monitoring.sampler.sigar.ISigarSamplerFactory;
import kieker.monitoring.sampler.sigar.SigarSamplerFactory;
import kieker.monitoring.sampler.sigar.samplers.CPUsDetailedPercSampler;
import kieker.monitoring.sampler.sigar.samplers.MemSwapUsageSampler;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MonitorRecord {

    /*public static final IMonitoringController MONITORING_CONTROLLER =
            MonitoringController.getInstance();

    public static void monitorTimeRecord(MyResponseTimeRecord timeRecord){
        MONITORING_CONTROLLER.newMonitoringRecord(timeRecord);
    }

    public static void monitorCpuUsageRecord() throws InterruptedException {
        System.out.println("Monitoring CPU and Mem/Swap for 30 seconds in 5 seconds steps with an offset of two seconds");
        final ISigarSamplerFactory sigarFactory = SigarSamplerFactory.INSTANCE;
        final CPUsDetailedPercSampler cpuSampler =
                sigarFactory.createSensorCPUsDetailedPerc();
        final MemSwapUsageSampler memSwapSampler =
                sigarFactory.createSensorMemSwapUsage();
        final long offset = 2; // start after 2 seconds
        final long period = 5; // monitor every 5 seconds
        MONITORING_CONTROLLER.schedulePeriodicSampler(
                cpuSampler, offset, period, TimeUnit.SECONDS);
        MONITORING_CONTROLLER.schedulePeriodicSampler(
                memSwapSampler, offset, period, TimeUnit.SECONDS);
        Thread.sleep(30000);
        System.out.println("Terminating");
        MONITORING_CONTROLLER.terminateMonitoring();
    }*/
}
