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

public class Page {

    public static void getPageFromTXTFile(String url, int pagenum, int pageSize) {
        long time1 = System.currentTimeMillis();
        Reader in = null;
        char[] chars = new char[pageSize];
        int i = 0;
        try {
            in = new BufferedReader(new FileReader(url), pageSize);
            while (true) {
                i++;
                int count = in.read(chars, 0, pageSize);
                if (i == pagenum) {
                    System.out.println("Page: " + pagenum);
                    System.out.println("---PAGE STARTS--------------------------------------------------------------");
                    System.out.println(String.valueOf(chars));
                    System.out.println("---PAGE ENDS----------------------------------------------------------------");
                    System.out.println(count + " chars from page " + pagenum + " were read.");
                    break;
                }
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        long time2 = System.currentTimeMillis();
        System.out.println("Executed in " + (time2 - time1) + " ms.");
        Memory.getUsedMemoryKb();
    }
}
