package ru.shcheglov.dto;

public class ResultDTO {

    private static boolean success;

    {
        setSuccess(true);
    }

    private static void setSuccess(boolean success) {
        ResultDTO.success = success;
    }

}
