package ru.shcheglov.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.shcheglov.dto.ResultDTO;

/**
 * @author Alexey Shcheglov
 * @version dated 28.01.2019
 */

@RefreshScope
@RestController
@RequestMapping("/api/info")
public class InfoController {

    @Value("${info.developer.email}")
    private String email;

    @RequestMapping(value = "/ping", produces = "application/json")
    public ResultDTO ping() {
        return new ResultDTO();
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public String email() {
        return email;
    }
}
