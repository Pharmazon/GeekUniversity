package ru.cloud.storage.common.message;

import ru.cloud.storage.common.Commands;

import java.util.List;

public class FileListMsg extends AbstractMsg {
    private List<String> files;
    private String path;

    public List<String> getFiles() {
        return files;
    }

    public String getPath() {
        return path;
    }

    public FileListMsg(List<String> files, String path) {
        super.setCommand(Commands.FILELIST);
        this.files = files;
        this.path = path;
    }
}
