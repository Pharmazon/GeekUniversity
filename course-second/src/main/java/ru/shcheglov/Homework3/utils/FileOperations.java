/*******************************************************************************
 * Java Core 2.
 * Homework 3. File read/write operations
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Feb 28, 2018
 ******************************************************************************/

package ru.shcheglov.Homework3.utils;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class FileOperations {

    public static void joinTXTFiles(String urlIn, String urlOut) {

        long time1 = System.currentTimeMillis();
        List<InputStream> list = new ArrayList<>();
        File[] filesList = null;
        InputStream in = null;
        InputStream joined = null;
        int i = 0;
        BufferedWriter bw = null;

        try {
            filesList = getTXTFilesFromFolder(urlIn);
            for (File file: filesList) {
                in = new FileInputStream(file);
                list.add(in);
            }

            Enumeration<InputStream> en = Collections.enumeration(list);
            joined = new SequenceInputStream(en);

            File file = new File(urlOut);
            file.mkdirs();
            bw = new BufferedWriter(new FileWriter(Paths.get(urlOut,"out.txt").toString()));
            while ((i = joined.read()) != -1) {
                bw.write(i);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } finally {
            try {
                in.close();
                bw.close();
                joined.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(filesList.length + " TXT files were joined successfully!");
        long time2 = System.currentTimeMillis();
        System.out.println("Executed in " + (time2 - time1) + " ms.");
    }

    public static File[] getTXTFilesFromFolder(String url) {
        File folder = new File(url);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        Memory.getUsedMemoryKb();
        return files;
    }
}
