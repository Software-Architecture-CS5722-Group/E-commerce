package com.groupjn.userservice.kieker.pipes;

import com.groupjn.userservice.kieker.common.ArraySerializer;
import kieker.common.configuration.Configuration;
import kieker.common.record.IMonitoringRecord;
import kieker.monitoring.writer.AbstractMonitoringWriter;

public class MyPipeWriter extends AbstractMonitoringWriter {

    public static final String CONFIG_PROPERTY_NAME_PIPE_NAME = MyPipeWriter.
            class.getName() + ".pipeName";
    private volatile MyPipe pipe;
    private final String pipeName;
    private final ArraySerializer arraySerializer = new ArraySerializer();

    public MyPipeWriter(final Configuration configuration) {
        super(configuration);
        this.pipeName = configuration.getStringProperty(
                CONFIG_PROPERTY_NAME_PIPE_NAME);;
    }

    @Override
    public void onStarting() {
        this.pipe = MyNamedPipeManager.getInstance().acquirePipe(this.pipeName);
    }

    @Override
    public void writeMonitoringRecord(IMonitoringRecord iMonitoringRecord) {
        try {
            // Just write the content of the record into the pipe.
            iMonitoringRecord.serialize(this.arraySerializer);
            this.pipe.put(new PipeData(iMonitoringRecord.getLoggingTimestamp(),
                    this.arraySerializer.getObjectArray(), iMonitoringRecord.getClass()));
            this.arraySerializer.clear();
        } catch (final InterruptedException e) {
            throw new IllegalStateException("Should not be thrown", e);
        }
    }

    @Override
    public void onTerminating() {
        // nothing to do
    }
}
