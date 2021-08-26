package com.groupjn.userservice.kieker.filters;


import com.groupjn.userservice.kieker.common.ArrayDeserializer;
import com.groupjn.userservice.kieker.pipes.MyNamedPipeManager;
import com.groupjn.userservice.kieker.pipes.MyPipe;
import com.groupjn.userservice.kieker.pipes.PipeData;
import kieker.analysis.IProjectContext;
import kieker.analysis.plugin.annotation.OutputPort;
import kieker.analysis.plugin.annotation.Plugin;
import kieker.analysis.plugin.annotation.Property;
import kieker.analysis.plugin.reader.AbstractReaderPlugin;
import kieker.common.configuration.Configuration;
import kieker.common.record.AbstractMonitoringRecord;
import kieker.common.record.IMonitoringRecord;

@Plugin(
        name = "Pipe reader",
        description = "Reads records from a configured pipe",
        outputPorts = { @OutputPort(
                name = MyPipeReader.OUTPUT_PORT_NAME,
                description = "Outputs any received record",
                eventTypes = { IMonitoringRecord.class })
        },
        configuration = { @Property(
                name = MyPipeReader.CONFIG_PROPERTY_NAME_PIPE_NAME,
                defaultValue = "kieker-pipe")
        })
public class MyPipeReader extends AbstractReaderPlugin {

    public static final String OUTPUT_PORT_NAME = "outputPort";
    public static final String CONFIG_PROPERTY_NAME_PIPE_NAME = "pipeName";

    private final String pipeName;
    private volatile MyPipe pipe;
    private final ArrayDeserializer deserializer = new ArrayDeserializer();

    public MyPipeReader(final Configuration configuration, final IProjectContext projectContext) {
        super(configuration, projectContext);
        this.pipeName = configuration.getStringProperty(MyPipeReader.CONFIG_PROPERTY_NAME_PIPE_NAME);
        try {
            this.pipe = MyNamedPipeManager.getInstance().acquirePipe(this.pipeName);
        } catch (final Exception ex) {
            this.logger.error("Failed to acquire pipe '{}'", this.pipeName, ex);
        }
    }

    @Override
    public boolean read() {
        try {
            // Wait max. 4 seconds for the next data.
            PipeData data = this.pipe.poll(4);
            while (data != null) {
                // Create new record, init from received array ...
                this.deserializer.receiveData(data.getRecordData());
                final IMonitoringRecord record = // throws MonitoringRecordException:
                        AbstractMonitoringRecord.createFromDeserializer(data.getRecordType().getCanonicalName(),
                                deserializer);
                record.setLoggingTimestamp(data.getLoggingTimestamp());
                // ...and delegate the task of delivering to the super class.
                super.deliver(MyPipeReader.OUTPUT_PORT_NAME, record);
                // next turn
                data = this.pipe.poll(4);
            }
        } catch (final Exception e) {
            return false; // signal error
        }
        return true;
    }

    @Override
    public Configuration getCurrentConfiguration() {
        final Configuration configuration = new Configuration(null);

        configuration.setProperty(MyPipeReader.CONFIG_PROPERTY_NAME_PIPE_NAME, this.pipeName);

        return configuration;
    }

    @Override
    public void terminate(final boolean error) {
        // nothing to do
    }
}
