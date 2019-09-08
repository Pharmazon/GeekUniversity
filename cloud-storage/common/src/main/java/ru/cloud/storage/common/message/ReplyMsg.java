package ru.cloud.storage.common.message;

import ru.cloud.storage.common.Commands;

public class ReplyMsg extends AbstractMsg {

    public ReplyMsg(Commands reply) {
        super.setCommand(reply);
    }

    @Override
    public String toString() {
        return getCommand();
    }
}
