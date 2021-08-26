package com.groupjn.orderservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.groupjn.orderservice.builder.Email;
import com.groupjn.orderservice.builder.EmailBuilder;
import com.groupjn.orderservice.common.Payment;
import com.groupjn.orderservice.common.TransactionRequest;
import com.groupjn.orderservice.common.TransactionResponse;
import com.groupjn.orderservice.entity.Order;
import com.groupjn.orderservice.service.OrderService;
import com.groupjn.orderservice.template.UserNotificatonMessage;
import kieker.analysis.AnalysisController;
import kieker.analysis.IAnalysisController;
import kieker.analysis.exception.AnalysisConfigurationException;
import kieker.analysis.plugin.filter.forward.TeeFilter;
import kieker.analysis.plugin.reader.filesystem.FSReader;
import kieker.common.configuration.Configuration;
import kieker.common.record.controlflow.OperationExecutionRecord;
import kieker.monitoring.annotation.OperationExecutionMonitoringProbe;
import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    private final IMonitoringController MONITORING_CONTROLLER =
            MonitoringController.getInstance();

    private final IAnalysisController analysisInstance = new AnalysisController();

    @PostMapping("/bookOrder")
    @OperationExecutionMonitoringProbe
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest) throws JsonProcessingException {

        String header = "User";
        String message = "Your order has been placed see below order details \n" +
                ""+transactionRequest.getOrder().toString();
        final long tin = MONITORING_CONTROLLER.getTimeSource().getTime();
        UserNotificatonMessage userNotificatonMessage = new UserNotificatonMessage(header,message);
        Email email = new EmailBuilder()
                .addRecipient("john@Doe.com")
                .setMainText(userNotificatonMessage.buildNotification())
                .setGreeting("Hi John!")
                .setClosing("Regards")
                .setTitle("Builder pattern resources")
                .build();
        email.send();
        TransactionResponse transactionResponse =  orderService.saveOrder(transactionRequest);
        final long tout = MONITORING_CONTROLLER.getTimeSource().getTime();
        final OperationExecutionRecord e = new OperationExecutionRecord(
                "public User " + OrderController.class.getName()+ ".bookOrder(...)",
                OperationExecutionRecord.NO_SESSION_ID,
                OperationExecutionRecord.NO_TRACE_ID,
                tin, tout, "myHost",
                OperationExecutionRecord.NO_EOI_ESS,
                OperationExecutionRecord.NO_EOI_ESS);
        MONITORING_CONTROLLER.newMonitoringRecord(e);
        return transactionResponse;
    }

    @GetMapping("/analyse/{dirname}")
    public String analyse(@PathVariable String dirname) throws AnalysisConfigurationException {

        // Set filesystem monitoring log input directory for our analysis
        final Configuration fsReaderConfig = new Configuration();
        fsReaderConfig.setProperty(FSReader.CONFIG_PROPERTY_NAME_INPUTDIRS, "/home/"+dirname);
        final FSReader reader = new FSReader(fsReaderConfig, analysisInstance);

        // Create and register a simple output writer.
        final Configuration teeFilterConfig = new Configuration();
        teeFilterConfig.setProperty(TeeFilter.CONFIG_PROPERTY_NAME_STREAM,
                TeeFilter.CONFIG_PROPERTY_VALUE_STREAM_STDOUT);
        final TeeFilter teeFilter = new TeeFilter(teeFilterConfig, analysisInstance);

        // Connect the output of the reader with the input of the filter.
        analysisInstance.connect(reader, FSReader.OUTPUT_PORT_NAME_RECORDS,
                teeFilter, TeeFilter.INPUT_PORT_NAME_EVENTS);

        // Start the analysis
        analysisInstance.run();
        return "Done";
    }



}
