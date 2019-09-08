package ru.cloud.storage.common.message;

import ru.cloud.storage.common.Commands;

import java.io.Serializable;

public abstract class AbstractMsg implements Serializable {
    private Commands command;

    public String getCommand() {
        return "/" + command.toString();
    }

    public void setCommand(Commands command) {
        this.command = command;
    }

    public boolean equals(Commands command) {
        return this.command.equals(command);
    }
}
