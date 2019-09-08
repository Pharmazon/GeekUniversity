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
import ru.shcheglov.Homework3.utils.ReadWriteFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class TestReadWrite {

    public static void main(String[] args) {
        byte[] array1 = ReadWriteFile.readAllBytesFromFile(Settings.URL1_IN);
        System.out.println("Bytes1=" + Arrays.toString(array1));

        System.out.println("---------------------------------------------------------------------------");
        Path path = Paths.get(Settings.URL1_IN);
        byte[] array2 = ReadWriteFile.readAllBytesFromFile(path);
        System.out.println("Bytes2=" + Arrays.toString(array2));

        System.out.println("---------------------------------------------------------------------------");
        ReadWriteFile.writeBytesToFile(Settings.URL1_OUT, array1);
    }
}
