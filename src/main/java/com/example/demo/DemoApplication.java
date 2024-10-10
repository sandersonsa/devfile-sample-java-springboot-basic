package com.example.demo;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class DemoApplication {

    private static final Log LOG = LogFactory.getLog(DemoApplication.class);

    @RequestMapping("/demo")
    String home(@RequestHeader Map<String, String> headers) {
        LOG.info("\n\n-- -- -- -- -- -- -- -- --- -- -- -- -- -- -- -- -- --- ");
        headers.forEach((key, value) -> {
            LOG.info(String.format("#Header '%s' = %s", key, value));
        });        
        // add sleep time before response
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello World!";
    }

    @PostMapping(path = "/introspect", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void test(@RequestParam Map<String, String> params) {
        for (Map.Entry<?, ?> entry : params.entrySet()) {
            System.out.printf("%-15s : %s%n", entry.getKey(), entry.getValue());
       }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}