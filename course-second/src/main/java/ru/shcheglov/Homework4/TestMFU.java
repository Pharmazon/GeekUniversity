/*******************************************************************************
 * Java Core 2.
 * Homework 4. Multithreading 1
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Mar 05, 2018
 ******************************************************************************/

package ru.shcheglov.Homework4;

import ru.shcheglov.Homework4.thirdtask.MFU;
import ru.shcheglov.Homework4.thirdtask.PrintThread;
import ru.shcheglov.Homework4.thirdtask.ScanThread;

//3. Написать класс МФУ, на котором возможны одновременная печать и сканирование
// документов, при этом нельзя одновременно печатать два документа или сканировать
// (при печати в консоль выводится сообщения "отпечатано 1, 2, 3,... страницы",
// при сканировании тоже самое только "отсканировано...", вывод в консоль все
// также с периодом в 50 мс.)

public class TestMFU {

    public static void main(String[] args) {

        final MFU mfu = new MFU();

        final int pagesToScan = 30;
        final int pagesToPrint = 15;

        for (int i = 1; i <= 5; i++) {
            new PrintThread("PrintDoc" + i, mfu, pagesToPrint);
            new ScanThread("ScanDoc" + i, mfu, pagesToScan);
        }
    }
}
