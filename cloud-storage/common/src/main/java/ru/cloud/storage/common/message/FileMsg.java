package ru.cloud.storage.common.message;

import ru.cloud.storage.common.util.MD5Checksum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileMsg extends AbstractMsg {
    private String source;
    private String destination;
    private String filename;
    private long size;
    private String crc;
    private int bufferSize;
    private boolean isRequestFileList;

    public int getBufferSize() {
        return bufferSize;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getFilename() {
        return filename;
    }

    public long getSize() {
        return size;
    }

    public String getCrc() {
        return crc;
    }

    public boolean isRequestFileList() {
        return isRequestFileList;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public FileMsg(Path src, Path dst, boolean isRequestFileList) {
        this.source = src.toString();
        this.destination = dst.toString();
        this.filename = src.subpath(src.getNameCount() - 1, src.getNameCount()).toString();
        this.crc = MD5Checksum.getMD5Checksum(src);
        this.isRequestFileList = isRequestFileList;
        try {
            this.size = Files.size(src);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
