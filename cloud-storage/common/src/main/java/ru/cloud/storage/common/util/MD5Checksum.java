package ru.cloud.storage.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Checksum {

    public static byte[] createChecksum(Path filename) {
        InputStream fis = null;

        try {
            fis = new FileInputStream(filename.toFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] buffer = new byte[1024];
        MessageDigest complete = null;

        try {
            complete = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        int numRead = 0;
        do {
            try {
                numRead = fis != null ? fis.read(buffer) : 0;
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (numRead > 0) {
                if (complete != null) {
                    complete.update(buffer, 0, numRead);
                }
            }
        } while (numRead != -1);

        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return complete != null ? complete.digest() : new byte[0];
    }

    public static String getMD5Checksum(Path filename) {
        byte[] b = createChecksum(filename);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            result.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}