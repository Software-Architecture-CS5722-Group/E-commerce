package com.groupjn.userservice.kieker.pipes;

import kieker.common.record.IMonitoringRecord;

public class PipeData {
    private final long loggingTimestamp;
    private final Object[] recordData;
    private final Class<? extends IMonitoringRecord> recordType;

    public PipeData(final long loggingTimestamp, final Object[] recordData, final Class<? extends IMonitoringRecord> recordType) {
        this.loggingTimestamp = loggingTimestamp;
        this.recordData = recordData; // in real settings we would clone
        this.recordType = recordType;
    }

    public final long getLoggingTimestamp() {
        return this.loggingTimestamp;
    }

    public final Object[] getRecordData() {
        return this.recordData; // in real settings we would clone
    }

    public Class<? extends IMonitoringRecord> getRecordType() {
        return this.recordType;
    }
}
