package ru.shcheglov;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alexey Shcheglov
 * @version dated 28.01.2019
 */

@RefreshScope
@RestController
public class TestController {

    @Value("${developer.email}")
    private String email;

    @RequestMapping(value = "/test")
    public String test() {
        return email;
    }
}
