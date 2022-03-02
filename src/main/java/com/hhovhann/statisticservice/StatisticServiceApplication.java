package com.hhovhann.statisticservice;

import com.hhovhann.statisticservice.service.CommandLineParserService;
import com.hhovhann.statisticservice.service.DataProcessingService;
import com.hhovhann.statisticservice.service.FileTransformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@SpringBootApplication
public class StatisticServiceApplication implements CommandLineRunner {

    @Autowired
    DataProcessingService dataProcessingService;

    @Autowired
    FileTransformationService fileTransformationService;

    public static void main(String[] args) {
        log.info("Statistic Service Application Started");
        SpringApplication.run(StatisticServiceApplication.class, args);
        log.info("Statistic Service Application Finished");
    }

    @Override
    public void run(String... args) {
        if (args.length == 0) {
            log.warn("No arguments are provided");
            return;
        }
        // Parse arguments data from CLI
        CommandLineParserService clp = new CommandLineParserService(args);
        // Read input file and transform statistics based on provided operations
        List<Object> statistics = dataProcessingService.doStatisticProcess(clp);
        // Write statistic data to output file
        fileTransformationService.writeTo("output" + "/" + clp.getArgumentValues("output"), statistics);
    }
}
