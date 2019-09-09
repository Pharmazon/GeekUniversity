package ru.shcheglov.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shcheglov.dto.NumberDTO;
import ru.shcheglov.dto.ResultDTO;

/**
 * @author Alexey Shcheglov
 * @version dated 28.01.2019
 */

@RequestMapping(value = "/api/calc")
@FeignClient(name = "cluster-microservice-api")
public interface CalcClient {

    @RequestMapping(value = "/ping", produces = "application/json")
    ResultDTO ping();

    @RequestMapping(value = "/sum", produces = "application/json")
    NumberDTO sum(
            @RequestParam(value = "a") final Double a,
            @RequestParam(value = "b") final Double b
    );

}
