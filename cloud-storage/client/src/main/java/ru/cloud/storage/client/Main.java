package ru.cloud.storage.client;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.cloud.storage.client.util.Assets;
import ru.cloud.storage.client.util.WindowManager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Assets.getInstance().setStage(primaryStage);
        readPropertiesFromFile();
        WindowManager.showLogin();
    }

    private void readPropertiesFromFile() {
        try (Reader in = new InputStreamReader(this.getClass().getResourceAsStream("/client.properties"))) {
            Properties pr = new Properties();
            pr.load(in);

            int propBufferSize = Integer.parseInt(pr.getProperty("buffer_size"));
            int propServerPort = Integer.parseInt(pr.getProperty("server_port"));
            String propServerIp = String.valueOf(pr.getProperty("server_ip"));
            String propHome = String.valueOf(pr.getProperty("home_directory"));

            Assets.getInstance().setBufferSize(propBufferSize);
            Assets.getInstance().setHome(propHome);
            Assets.getInstance().setServerIp(propServerIp);
            Assets.getInstance().setServerPort(propServerPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
