/*******************************************************************************
 * Java Core 2.
 * Homework 3. File read/write operations
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Feb 28, 2018
 ******************************************************************************/

package ru.shcheglov.Homework3;

import ru.shcheglov.Homework3.conf.Settings;
import ru.shcheglov.Homework3.utils.FileOperations;

public class TestJoin {

    public static void main(String[] args) {
        FileOperations.joinTXTFiles(Settings.URL2_IN, Settings.URL2_OUT);
    }
}
