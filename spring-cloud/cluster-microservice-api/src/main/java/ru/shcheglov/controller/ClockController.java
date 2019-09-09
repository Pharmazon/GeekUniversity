package ru.shcheglov.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shcheglov.dto.DateDTO;
import ru.shcheglov.dto.ResultDTO;

import java.util.Date;

/**
 * @author Alexey Shcheglov
 * @version dated 28.01.2019
 */

@RestController
@RequestMapping("/api/clock")
public class ClockController {

    @RequestMapping(value = "/ping", produces = "application/json")
    public ResultDTO ping() {
        return new ResultDTO();
    }

    @RequestMapping(value = "/date", produces = "application/json")
    public DateDTO date() {
        return new DateDTO(new Date());
    }
}
