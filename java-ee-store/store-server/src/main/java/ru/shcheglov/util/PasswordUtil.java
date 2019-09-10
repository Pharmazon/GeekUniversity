package ru.shcheglov.util;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

public class PasswordUtil {

    @SneakyThrows
    public static String encrypt(@NotNull final String password) {
        final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        final byte[] bytes = password.getBytes(StandardCharsets.UTF_8);
        final byte[] hex = messageDigest.digest(bytes);
        return DatatypeConverter.printHexBinary(hex);
    }
}
