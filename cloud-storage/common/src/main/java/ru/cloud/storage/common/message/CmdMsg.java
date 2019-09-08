package ru.cloud.storage.common.message;

import ru.cloud.storage.common.Commands;

public class CmdMsg extends AbstractMsg {
    private Object[] attachment;

    public Object[] getAttachment() {
        return attachment;
    }

    public CmdMsg(Commands command, Object... attachment) {
        super.setCommand(command);
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object item : attachment) {
            sb.append(" ").append(item);
        }
        return (super.getCommand() + sb.toString()).trim();
    }
}
