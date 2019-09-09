package ru.shcheglov.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.shcheglov.dto.NumberDTO;
import ru.shcheglov.dto.ResultDTO;

/**
 * @author Alexey Shcheglov
 * @version dated 28.01.2019
 */

@RestController
@RequestMapping("/api/calc")
public class CalcController {

    @RequestMapping(value = "/ping", produces = "application/json")
    public ResultDTO ping() {
        return new ResultDTO();
    }

    @RequestMapping(value = "/sum", produces = "application/json")
    public NumberDTO sum(
            @RequestParam(value = "a") final Double a,
            @RequestParam(value = "b") final Double b
    ) {
        return new NumberDTO(a + b);
    }

}
