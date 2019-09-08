package ru.cloud.storage.common.message;

import ru.cloud.storage.common.Commands;

public class AuthMsg extends AbstractMsg {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public AuthMsg(String login, String password) {
        super.setCommand(Commands.AUTH);
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "/" + Commands.AUTH + " " + login + " " + password;
    }

}
