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
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadWriteFile {

    public static byte[] readAllBytesFromFile(String sourceFile) {
        InputStream in = null;
        byte[] bytes = null;
        int bt = 0;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            in = new FileInputStream(sourceFile);
            while (true) {
                bt = in.read();
                if (bt == -1) break;
                out.write(bt);
            }
            bytes = out.toByteArray();
            System.out.println(bytes.length + " bytes was read from file '" + sourceFile + "'");
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
        Memory.getUsedMemoryKb();
        return bytes;
    }

    public static byte[] readAllBytesFromFile(Path sourceFile) {
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(sourceFile);
            System.out.println(bytes.length + " bytes was read from file '" + sourceFile + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Memory.getUsedMemoryKb();
        return bytes;
    }

    public static void writeBytesToFile(String destinationFile, byte[] bytes) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File(destinationFile), true);
            out.write(bytes);
            out.write(Character.LINE_SEPARATOR);
            System.out.println(bytes.length + " bytes was written to file '" + destinationFile + "'");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Memory.getUsedMemoryKb();
    }


}
