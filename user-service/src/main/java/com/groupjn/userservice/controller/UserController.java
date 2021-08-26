package com.groupjn.userservice.controller;


import com.groupjn.userservice.entity.User;
import com.groupjn.userservice.factory.CreateUserFactory;
import com.groupjn.userservice.repository.UserRepository;
import com.groupjn.userservice.service.RoleServiceImpl;
import kieker.analysis.AnalysisController;
import kieker.analysis.IAnalysisController;
import kieker.analysis.exception.AnalysisConfigurationException;
import kieker.common.record.controlflow.OperationExecutionRecord;
import kieker.common.record.system.CPUUtilizationRecord;
import kieker.common.record.system.MemSwapUsageRecord;
import kieker.monitoring.annotation.OperationExecutionMonitoringProbe;
import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;
import kieker.monitoring.sampler.sigar.ISigarSamplerFactory;
import kieker.monitoring.sampler.sigar.SigarSamplerFactory;
import kieker.monitoring.sampler.sigar.samplers.CPUsDetailedPercSampler;
import kieker.monitoring.sampler.sigar.samplers.MemSwapUsageSampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import com.sun.management.OperatingSystemMXBean;


import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleServiceImpl roleService;



    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private static OperatingSystemMXBean h = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();;


    private final IMonitoringController MONITORING_CONTROLLER =
            MonitoringController.getInstance();

    //private final ISigarSamplerFactory sigarFactory = SigarSamplerFactory.INSTANCE;

    private final IAnalysisController analysisInstance = new AnalysisController();


    //private final SigarProxy sigar = new Sigar();

    @PostMapping("/createUser")
    @OperationExecutionMonitoringProbe
    public User createUser(@RequestBody User createUserDTO) throws Exception{

        final long tin = MONITORING_CONTROLLER.getTimeSource().getTime();
        User user;
        CreateUserFactory createUserFactory = new CreateUserFactory(createUserDTO,repository,roleService);
       user =  createUserFactory.createUser();
        final long tout = MONITORING_CONTROLLER.getTimeSource().getTime();

        final OperationExecutionRecord e = new OperationExecutionRecord(
              "public User " + UserController.class.getName()+ ".createUser(...)",
        OperationExecutionRecord.NO_SESSION_ID,
                OperationExecutionRecord.NO_TRACE_ID,
                tin, tout, "myHost",
                OperationExecutionRecord.NO_EOI_ESS,
                OperationExecutionRecord.NO_EOI_ESS);
        MONITORING_CONTROLLER.newMonitoringRecord(e);
        return  user;
    }

    @GetMapping("/analyse/{dirname}")
    public String analyse(@PathVariable String dirname) throws AnalysisConfigurationException {




        //final CPUsDetailedPercSampler cpuSampler =  sigarFactory.createSensorCPUsDetailedPerc();
        //final MemSwapUsageSampler memSwapSampler =
        //sigarFactory.createSensorMemSwapUsage();
        //final DiskUsageSampler diskUsageSampler = sigarFactory.createSensorDiskUsage();
        final long offset = 1; // start after 2 seconds
        final long period = 5; // monitor every 5 seconds
        //SigarProxy sp = ;

        final MemSwapUsageRecord r = new MemSwapUsageRecord(
                MONITORING_CONTROLLER.getTimeSource().getTime(), MONITORING_CONTROLLER.getHostname(), h.getTotalSwapSpaceSize(), h.getFreeSwapSpaceSize(),h.getTotalPhysicalMemorySize(),
                h.getFreePhysicalMemorySize(), h.getCommittedVirtualMemorySize(),h.getFreeSwapSpaceSize());
        final CPUUtilizationRecord c = new CPUUtilizationRecord(MONITORING_CONTROLLER.getTimeSource().getTime(), MONITORING_CONTROLLER.getHostname(),
                "INTEL-CHIP",h.getProcessCpuLoad(),h.getSystemLoadAverage(),h.getAvailableProcessors(), 0.0,0.0,h.getSystemCpuLoad(),h.getProcessCpuTime());

//        MONITORING_CONTROLLER.schedulePeriodicSampler(
//                cpuSampler, offset, period, TimeUnit.SECONDS);
//        MONITORING_CONTROLLER.schedulePeriodicSampler(
//                memSwapSampler, offset, period, TimeUnit.SECONDS);
//        MONITORING_CONTROLLER.schedulePeriodicSampler(
//                diskUsageSampler, offset, period, TimeUnit.SECONDS
//        );
        MONITORING_CONTROLLER.newMonitoringRecord(r);
        MONITORING_CONTROLLER.newMonitoringRecord(c);;
        return "done";
    }


    private String getReadableTime(Long nanos){

        long tempSec    = nanos/(1000*1000*1000);
        long sec        = tempSec % 60;
        long min        = (tempSec /60) % 60;
        long hour       = (tempSec /(60*60)) % 24;
        long day        = (tempSec / (24*60*60)) % 24;

        return String.format("%dd %dh %dm %ds", day,hour,min,sec);

    }


    @GetMapping("/allUser")
    @OperationExecutionMonitoringProbe
    public List<User> users()  {

        final long tin = MONITORING_CONTROLLER.getTimeSource().getTime();

        List<User>  users =  repository.findAll();

        final long tout = MONITORING_CONTROLLER.getTimeSource().getTime();
        final OperationExecutionRecord e = new OperationExecutionRecord(
                "public List<User> " + CreateUserFactory.class.getName()+ ".users(...)",
                OperationExecutionRecord.NO_SESSION_ID,
                OperationExecutionRecord.NO_TRACE_ID,
                tin, tout, "myHost",
                OperationExecutionRecord.NO_EOI_ESS,
                OperationExecutionRecord.NO_EOI_ESS);
        MONITORING_CONTROLLER.newMonitoringRecord(e);

        //Gathering CPU and Memory information
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

        return users;
    }


    @GetMapping("/allUser-spaghetti")
    public List<User> users_Spaghetti() {
        final long tin = MONITORING_CONTROLLER.getTimeSource().getTime();
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        List<User> users;
        users= new ArrayList<>();
        System.out.println("Sql Query");
        String sql;
        sql = "SELECT * FROM user";
        List<Map<String, Object>> rows;
        rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            User obj;
            obj = new User();
            obj.setId(((int) row.get("id")));
            obj.setFirstName((String) row.get("firstName"));
            obj.setLastName((String) row.get("lastName"));
            obj.setEmail((String) row.get("email"));
            obj.setPhoneNumber((String) row.get("phoneNumber"));
            users.add(obj);
        }
        final long tout= MONITORING_CONTROLLER.getTimeSource().getTime();
        final OperationExecutionRecord e = new OperationExecutionRecord(
                "public " + UserController.class.getName()+ ".List<User> usersSpaghetti()",
                OperationExecutionRecord.NO_SESSION_ID,
                OperationExecutionRecord.NO_TRACE_ID,
                tin, tout, "myHost",
                OperationExecutionRecord.NO_EOI_ESS,
                OperationExecutionRecord.NO_EOI_ESS);
        MONITORING_CONTROLLER.newMonitoringRecord(e);
        return users;
    }


    @GetMapping("/allUser-clean")
    public List<User> users_Clean() {
        final long tin = MONITORING_CONTROLLER.getTimeSource().getTime();
        List<User> users = repository.findAll();
        final long tout= MONITORING_CONTROLLER.getTimeSource().getTime();
        final OperationExecutionRecord e = new OperationExecutionRecord(
                "public " + UserController.class.getName()+ ".List<User> usersClean()",
                OperationExecutionRecord.NO_SESSION_ID,
                OperationExecutionRecord.NO_TRACE_ID,
                tin, tout, "myHost",
                OperationExecutionRecord.NO_EOI_ESS,
                OperationExecutionRecord.NO_EOI_ESS);
        MONITORING_CONTROLLER.newMonitoringRecord(e);
        return users;
    }



}
