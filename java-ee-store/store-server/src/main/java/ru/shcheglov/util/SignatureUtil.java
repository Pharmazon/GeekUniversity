package ru.shcheglov.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Nullable;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

public class SignatureUtil {

    public static String sign(
            @Nullable final Object value,
            @Nullable final String salt,
            @Nullable final Integer cycle
    ) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final String json = objectMapper.writeValueAsString(value);
            return sign(json, salt, cycle);
        } catch (final JsonProcessingException e) {
            return null;
        }
    }

    private static String sign(
            @Nullable final String value,
            @Nullable final String salt,
            @Nullable final Integer cycle
    ) {
        if (value == null || salt == null || cycle == null) return null;
        String result = value;
        for (int i = 0; i < cycle; i++) {
            result = PasswordUtil.encrypt(salt + result + salt);
        }
        return result;
    }
}
