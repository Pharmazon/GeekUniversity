package ru.shcheglov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.shcheglov.client.CalcClient;
import ru.shcheglov.client.ClockClient;
import ru.shcheglov.dto.DateDTO;
import ru.shcheglov.dto.NumberDTO;
import ru.shcheglov.dto.ResultDTO;

/**
 * @author Alexey Shcheglov
 * @version dated 28.01.2019
 */

@RefreshScope
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired(required = false)
    private ClockClient clockClient;

    @Autowired(required = false)
    private CalcClient calcClient;

    @Value("${info.developer.email}")
    private String email;

    @RequestMapping(value = "/pingJSON", produces = "application/json")
    public ResultDTO pingJson() {
        return new ResultDTO();
    }

    @RequestMapping(value = "/pingXML", produces = "application/xml")
    public ResultDTO pingXml() {
        return new ResultDTO();
    }

    @RequestMapping("/email")
    public String email() {
        return email;
    }

    @RequestMapping(value = "/sum", produces = "application/xml")
    public NumberDTO sum(
            @RequestParam(value = "a") final Double a,
            @RequestParam(value = "b") final Double b
    ) {
        return calcClient.sum(a, b);
    }

    @RequestMapping(value = "/date", produces = "application/xml")
    public DateDTO date() {
        return clockClient.date();
    }

}
