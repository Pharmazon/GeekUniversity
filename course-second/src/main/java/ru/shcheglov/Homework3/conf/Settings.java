/*******************************************************************************
 * Java Core 2.
 * Homework 3. File read/write operations
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Feb 28, 2018
 ******************************************************************************/

package ru.shcheglov.Homework3.conf;

import java.nio.file.Paths;

public class Settings {

    private static final String PATH = Paths.get("src","main","resources").toString();

    private static final String TASK1_FOLDER = Paths.get(PATH,"files_task1").toString();

    private static final String TASK2_FOLDER = Paths.get(PATH,"files_task2").toString();

    private static final String TASK3_FOLDER = Paths.get(PATH,"files_task3").toString();

    public static final String URL1_IN = Paths.get(TASK1_FOLDER,"file1.txt").toString();

    public static final String URL1_OUT = Paths.get(TASK1_FOLDER,"out.txt").toString();

    public static final String URL2_IN = TASK2_FOLDER;

    public static final String URL2_OUT = Paths.get(TASK2_FOLDER,"out").toString();

    public static final String URL3_IN = Paths.get(TASK3_FOLDER,"file.txt").toString();
}
