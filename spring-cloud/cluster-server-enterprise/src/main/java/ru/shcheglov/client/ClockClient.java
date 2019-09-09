package ru.shcheglov.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shcheglov.dto.DateDTO;
import ru.shcheglov.dto.ResultDTO;

/**
 * @author Alexey Shcheglov
 * @version dated 28.01.2019
 */

@RequestMapping(value = "/api/clock")
@FeignClient(name = "cluster-microservice-api")
public interface ClockClient {

    @RequestMapping(value = "/ping", produces = "application/json")
    ResultDTO ping();

    @RequestMapping(value = "/date", produces = "application/json")
    DateDTO date();
}
