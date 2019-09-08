/*
 *  Java Core 2.
 *  Homework 6. TestNG framework
 *
 *  @author Alexey Shcheglov
 *  @link https://github.com/Pharmazon
 *  @version dated Mar 10, 2018
 */

package ru.shcheglov.Homework6.conf;

public class URL {

    private static final String DB_HOST = "localhost";

    private static final String DB_PORT = "5432";

    public static final String DB_URL = "jdbc:postgresql://" +
            DB_HOST + ":" +
            DB_PORT + "/" +
            Settings.DB_NAME + "?" +
            "verifyServerCertificate=false" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&amp"+
            "&serverTimezone=UTC";
}
