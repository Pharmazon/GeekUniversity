/*******************************************************************************
 * Java Core 2.
 * Homework 4. Multithreading 1
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Mar 05, 2018
 ******************************************************************************/

package ru.shcheglov.Homework4.secondtask;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class File {

    public synchronized static void writeToFile(String text) {
        final String path = Paths.get("src","main", "resources", "out.txt").toString();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path,true));
            bw.write(text);
            bw.write(Character.LINE_SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
