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
import ru.shcheglov.Homework3.utils.Page;

public class TestPage {

    public static void main(String[] args) {
        Page.getPageFromTXTFile(Settings.URL3_IN,5000,1800);
    }
}
