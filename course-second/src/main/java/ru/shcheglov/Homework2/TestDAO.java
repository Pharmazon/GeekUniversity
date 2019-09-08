package ru.shcheglov.Homework2;

/*******************************************************************************
 * Java Core 2.
 * Homework 2. Java+mySQL CRUDe.
 * Class: TestDAO
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Feb 25, 2018
 ******************************************************************************/

import ru.shcheglov.Homework2.dao.GoodsDAO;
import ru.shcheglov.Homework2.utils.EntryThread;
import ru.shcheglov.Homework2.utils.Messages;

public class TestDAO {

    public static void main(String[] args) {

        GoodsDAO goodsDAO = new GoodsDAO();
        goodsDAO.clearTable();
        goodsDAO.disconnect();

        System.out.println(Messages.PROGRESS_ADDING_ENTRIES);

        long time1 = System.currentTimeMillis();

        EntryThread thread1 = new EntryThread(1, 2500);
        EntryThread thread2 = new EntryThread(2501, 5000);
        EntryThread thread3 = new EntryThread(5001, 7500);
        EntryThread thread4 = new EntryThread(7501, 10000);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long time2 = System.currentTimeMillis();

        System.out.println(Messages.ENTRIES_ADDED);
        System.out.println("Выполнено за " + (time2 - time1) / 1000 + " сек.");
    }
}
