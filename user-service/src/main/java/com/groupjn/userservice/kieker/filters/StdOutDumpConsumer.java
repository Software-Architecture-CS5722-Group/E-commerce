package com.groupjn.userservice.kieker.filters;

import kieker.analysis.IProjectContext;
import kieker.analysis.plugin.annotation.InputPort;
import kieker.analysis.plugin.annotation.Plugin;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.common.configuration.Configuration;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.system.CPUUtilizationRecord;
import kieker.common.record.system.MemSwapUsageRecord;
import kieker.tools.util.LoggingTimestampConverter;

@Plugin
public class StdOutDumpConsumer extends AbstractFilterPlugin {

    public static final String INPUT_PORT_NAME = "newMonitoringRecord";

    public StdOutDumpConsumer(Configuration configuration, IProjectContext projectContext) {
        super(configuration, projectContext);
    }

    @InputPort(
            name = StdOutDumpConsumer.INPUT_PORT_NAME,
            eventTypes = { IMonitoringRecord.class })
    public void newMonitoringRecord(final Object record) {
        if (record instanceof CPUUtilizationRecord) {
            final CPUUtilizationRecord cpuUtilizationRecord =
                    (CPUUtilizationRecord) record;

            final String hostname = cpuUtilizationRecord.getHostname();
            final String cpuId = cpuUtilizationRecord.getCpuID();
            final double utilizationPercent = cpuUtilizationRecord.getTotalUtilization() * 100;

            System.out
                    .println(String.format(
                            "%s: [CPU] host: %s ; cpu-id: %s ; utilization: %3.2f %%",
                            LoggingTimestampConverter
                                    .convertLoggingTimestampToUTCString(cpuUtilizationRecord
                                            .getTimestamp()),
                            hostname, cpuId, utilizationPercent));
        } else if (record instanceof MemSwapUsageRecord) {
            final MemSwapUsageRecord memSwapUsageRecord =
                    (MemSwapUsageRecord) record;

            final String hostname = memSwapUsageRecord.getHostname();
            final double memUsageMB = memSwapUsageRecord.getMemUsed() / (1024d * 1024d);
            final double swapUsageMB = memSwapUsageRecord.getSwapUsed() / (1024d * 1024d);

            System.out
                    .println(String.format(
                            "%s: [Mem/Swap] host: %s ; mem usage: %s MB ; swap usage: %s MB",
                            LoggingTimestampConverter
                                    .convertLoggingTimestampToUTCString(memSwapUsageRecord
                                            .getTimestamp()),
                            hostname, memUsageMB, swapUsageMB));
        } // else Unexpected record type
    }

    @Override
    public Configuration getCurrentConfiguration() {
        return new Configuration();
    }
}
