/*******************************************************************************
 * Java Core 2.
 * Homework 2. Java+mySQL CRUDe.
 * Class: URL
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Feb 25, 2018
 ******************************************************************************/

package ru.shcheglov.Homework2.conf;

public class URL {

    private static final String DB_HOST = "localhost";

    private static final String DB_PORT = "3306";

    public static final String DB_URL = "jdbc:mysql://" +
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
