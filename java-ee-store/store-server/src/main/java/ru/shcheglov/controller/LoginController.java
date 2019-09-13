package ru.shcheglov.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.shcheglov.service.AuthService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * @author Alexey Shcheglov
 * @version dated 10.02.2019
 */

@Getter
@Setter
@ViewScoped
@ManagedBean
@URLMapping(
        id = "login",
        pattern = "/login",
        viewId = "/WEB-INF/faces/login.xhtml"
)
public class LoginController {

    @NotNull
    private String login = "";

    @NotNull
    private String password = "";

    @Inject
    private AuthService authService;

    @NotNull
    public String auth() {
        final boolean check = authService.openSession(login, password);
        if (check) return "admin-index";
        else return "login";
    }

}
