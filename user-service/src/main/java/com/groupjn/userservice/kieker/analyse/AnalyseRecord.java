package com.groupjn.userservice.kieker.analyse;

import com.groupjn.userservice.kieker.RecordType;
import com.groupjn.userservice.kieker.filters.MyPipeReader;
import com.groupjn.userservice.kieker.filters.MyResponseTimeFilter;
import com.groupjn.userservice.kieker.filters.MyResponseTimeOutputPrinter;
import com.groupjn.userservice.kieker.filters.StdOutDumpConsumer;
import kieker.analysis.AnalysisController;
import kieker.analysis.IAnalysisController;
import kieker.analysis.analysisComponent.AbstractAnalysisComponent;
import kieker.analysis.exception.AnalysisConfigurationException;
import kieker.analysis.plugin.filter.forward.TeeFilter;
import kieker.analysis.plugin.reader.filesystem.FSReader;
import kieker.common.configuration.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class AnalyseRecord {

    // Create Kieker.Analysis instance
    private static final IAnalysisController analysisInstance
            = new AnalysisController();

    private static final Configuration fsReaderConfig = new Configuration();

    private static final Configuration teeFilterConfig = new Configuration();

    @Value(value = "${inputDirectory}")
    private static String inputDirectory;

    private static RecordType TYPE;

    public String getInputDirectory() {
        return inputDirectory;
    }

    public static void setInputDirectory(String inputDirectory) {
        AnalyseRecord.inputDirectory = inputDirectory;
    }

    public RecordType getTYPE() {
        return TYPE;
    }

    public static void setTYPE(RecordType TYPE) {
        AnalyseRecord.TYPE = TYPE;
    }

    public static void runAnalyseRecord() throws AnalysisConfigurationException, IOException {

        // Set filesystem monitoring log input directory for our analysis
        fsReaderConfig.setProperty(FSReader.CONFIG_PROPERTY_NAME_INPUTDIRS, inputDirectory);
        final FSReader reader = new FSReader(fsReaderConfig, analysisInstance);

        if(TYPE == RecordType.BASIC){

            // Create and register a simple output writer.
            teeFilterConfig.setProperty(TeeFilter.CONFIG_PROPERTY_NAME_STREAM,
                    TeeFilter.CONFIG_PROPERTY_VALUE_STREAM_STDOUT);
            final TeeFilter teeFilter = new TeeFilter(teeFilterConfig,
                    analysisInstance);
            // Connect the output of the reader with the input of the filter.
            analysisInstance.connect(reader, FSReader.OUTPUT_PORT_NAME_RECORDS,
                    teeFilter, TeeFilter.INPUT_PORT_NAME_EVENTS);
            // Start the analysis

        } else if(TYPE == RecordType.PERIODIC){
            final StdOutDumpConsumer consumer = new StdOutDumpConsumer(new Configuration(), analysisInstance);
            // Connect both components.
            analysisInstance.connect(reader, FSReader.OUTPUT_PORT_NAME_RECORDS,
                    consumer, StdOutDumpConsumer.INPUT_PORT_NAME);


        } else if(TYPE == RecordType.PIPE){

            // Configure and register the reader
            final Configuration readerConfig = new Configuration();
            readerConfig.setProperty(MyPipeReader.CONFIG_PROPERTY_NAME_PIPE_NAME, "somePipe");
            final MyPipeReader myreader = new MyPipeReader(readerConfig, analysisInstance);

            // Configure, register, and connect the response time filter
            final Configuration filterConfig = new Configuration();
            final long rtThresholdNanos =
                    TimeUnit.NANOSECONDS.convert(1900, TimeUnit.MICROSECONDS);
            filterConfig.setProperty( // configure threshold of 1.9 milliseconds:
                    MyResponseTimeFilter.CONFIG_PROPERTY_NAME_TS_NANOS,
                    Long.toString(rtThresholdNanos));
            final MyResponseTimeFilter filter = new MyResponseTimeFilter(filterConfig, analysisInstance);
            analysisInstance.connect(myreader, MyPipeReader.OUTPUT_PORT_NAME, filter, MyResponseTimeFilter.INPUT_PORT_NAME_RESPONSE_TIMES);

            // Configure, register, and connect the filter printing *valid* response times
            final Configuration validOutputConfig = new Configuration();
            validOutputConfig.setProperty(MyResponseTimeOutputPrinter.CONFIG_PROPERTY_NAME_VALID_OUTPUT, Boolean.toString(true));
            validOutputConfig.setProperty(AbstractAnalysisComponent.CONFIG_NAME, "Print valid");
            final MyResponseTimeOutputPrinter validPrinter = new MyResponseTimeOutputPrinter(validOutputConfig, analysisInstance);
            analysisInstance.connect(filter, MyResponseTimeFilter.OUTPUT_PORT_NAME_RT_VALID, validPrinter, MyResponseTimeOutputPrinter.INPUT_PORT_NAME_EVENTS);

            // Configure, register, and connect the filter printing *invalid* response times
            final Configuration invalidOutputConfig = new Configuration();
            invalidOutputConfig.setProperty(MyResponseTimeOutputPrinter.CONFIG_PROPERTY_NAME_VALID_OUTPUT, Boolean.toString(false));
            invalidOutputConfig.setProperty(AbstractAnalysisComponent.CONFIG_NAME, "Print invalid");
            final MyResponseTimeOutputPrinter invalidPrinter = new MyResponseTimeOutputPrinter(invalidOutputConfig, analysisInstance);
            analysisInstance.connect(filter, MyResponseTimeFilter.OUTPUT_PORT_NAME_RT_EXCEED, invalidPrinter, MyResponseTimeOutputPrinter.INPUT_PORT_NAME_EVENTS);

            analysisInstance.saveToFile(new File("/Users/d/Desktop/kieker/out.kax"));
            // Start the analysis.
        }
        // Start the analysis
        analysisInstance.run();

    }



}
