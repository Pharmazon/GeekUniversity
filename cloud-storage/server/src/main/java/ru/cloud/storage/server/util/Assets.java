package ru.cloud.storage.server.util;

import ru.cloud.storage.server.service.AuthService;

import java.nio.file.Path;

public class Assets {
    private static final Assets ourInstance = new Assets();

    public static Assets getInstance() {
        return ourInstance;
    }

    private AuthService authService;
    private Path serverPath;
    private int bufferSize;

    public AuthService getAuthService() {
        return authService;
    }

    public Path getServerPath() {
        return serverPath;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setServerPath(Path serverPath) {
        this.serverPath = serverPath;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    private Assets() {
        this.authService = new AuthService();
        authService.connect();
    }
}
